#define _GNU_SOURCE
#include <dirent.h>
#include <errno.h>
#include <fcntl.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define MAX_USER_PATH 256
#define MAX_WORD_LENGHT 60

typedef struct Vector {
  int size;
  char **data;
} Vector;

void initVector(Vector *vector) {
  vector->size = 0;
  vector->data = (char **)malloc(0);
}

void pushBack(Vector *vector, char *input) {
  vector->data = realloc(vector->data, sizeof(char *) * (vector->size + 1));
  vector->data[vector->size] = malloc(sizeof(char[MAX_WORD_LENGHT]));
  if (input == NULL) {
    vector->data[vector->size] = NULL;
  } else {
    strcpy(vector->data[vector->size], input);
  }
  vector->size++;
}

void clearVector(Vector *vector) {
  int i = 0;
  if (vector->data == NULL) {
    return;
  }
  for (i = 0; i < vector->size; i++) {
    printf("vector %s zostanie wyczyszczony\n", vector->data[i]);
    free(vector->data[i]);
    printf("vector[i] zostal wyczyszczony\n");
  }
  printf("vector zostanie wyczyszczony\n");
  free(vector->data);
  printf("vector został wyczyszczony\n");
}

void getUserInput(Vector *commands) {
  char *line = NULL;
  size_t len = 0;
  char *token;
  commands->size = 0;
  getline(&line, &len, stdin);
  line[strcspn(line, "\n")] = 0;
  token = strtok(line, " ");
  while (token != NULL) {
    pushBack(commands, token);
    token = strtok(NULL, " ");
  }
  free(line);
}

void addFileToPath(char path[MAX_USER_PATH], char file[MAX_WORD_LENGHT]) {
  int pathLenght = strlen(path);
  if (path[pathLenght - 1] != '/') {
    strcat(path, "/");
  }
  strcat(path, file);
  /* strcat(path, "/"); */
}

void rm(char path[MAX_USER_PATH]) {
  struct stat type;
  struct dirent *entry;
  DIR *dir;
  char file[MAX_USER_PATH];
  stat(path, &type);
  if (S_ISREG(type.st_mode)) {
    remove(path);
    return;
  }
  dir = opendir(path);
  while ((entry = readdir(dir)) != NULL) {
    if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
      strcpy(file, path);
      addFileToPath(file, entry->d_name);
      printf("file = %s\n", file);
      rm(file);
    }
  }
  closedir(dir);
  remove(path);
}

int main() {
  Vector vector;
  rm("/home/tonywesoly/Code/studies/sys/microshell/testowanie/testDir");
  /* initVector(&vector); */
  /* getUserInput(&vector); */
  /* getUserInput(&vector); */
  /* getUserInput(&vector); */
  /* clearVector(&vector); */
  return 0;
}
