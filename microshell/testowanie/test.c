#include <dirent.h>
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define WORD_LENGHT 30
#define MAX_PATH 1024

typedef struct Vector {
  int size;
  char **data;
} Vector;

void initVector(Vector *v) {
  v->size = 0;
  v->data = (char **)malloc(0);
}

void pushBack(Vector *commands, char *input) {
  commands->data =
      realloc(commands->data, sizeof(char *) * (commands->size + 1));
  commands->data[commands->size] = malloc(sizeof(char[WORD_LENGHT]));
  if (input == NULL) {
    commands->data[commands->size] = NULL;
  } else {
    strcpy(commands->data[commands->size], input);
  }
  commands->size++;
}

void getInputs(Vector *commands) {
  char *line = NULL;
  size_t len = 0;
  ssize_t nread;
  char *token;
  nread = getline(&line, &len, stdin);
  line[strcspn(line, "\n")] = 0;
  token = strtok(line, " ");
  commands->size = 0;
  while (token != NULL) {
    pushBack(commands, token);
    token = strtok(NULL, " ");
  }
  free(line);
}

void copyFile(char from[MAX_PATH], char to[MAX_PATH]) {
  const int BUFFER_SIZE = 1024;
  struct stat perm;
  stat(to, &perm);
  if (S_ISDIR(perm.st_mode)) {
    strcat(to, strrchr(from, '/'));
  }
  int fileTo = creat(to, perm.st_mode), fileFrom = open(from, O_RDONLY);
  int num = 0;
  char buffer[BUFFER_SIZE];
  stat(from, &perm);
  while ((num = read(fileFrom, &buffer, BUFFER_SIZE)) > 0) {
    write(fileTo, &buffer, num);
  }
  close(fileTo);
  close(fileFrom);
}

void copyDir(char pathFrom[MAX_PATH], char pathTo[MAX_PATH]) {
  struct stat perm;
  DIR *dir;
  struct dirent *entry;
  stat(pathFrom, &perm);
  mkdir(pathTo, perm.st_mode);
  char fileFrom[MAX_PATH];
  char fileTo[MAX_PATH];
  dir = opendir(pathFrom);
  while ((entry = readdir(dir)) != NULL) {
    if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
      strcpy(fileFrom, pathFrom);
      strcpy(fileTo, pathTo);
      strcat(fileFrom, "/");
      strcat(fileTo, "/");
      strcat(fileFrom, entry->d_name);
      strcat(fileTo, entry->d_name);
      stat(fileFrom, &perm);
      if (S_ISDIR(perm.st_mode)) {
        copyDir(fileFrom, fileTo);
      } else if (S_ISREG(perm.st_mode)) {
        copyFile(fileFrom, fileTo);
      } else {
        printf("cp: wrong file type");
      }
    }
  }
  closedir(dir);
}

void getFullFilePath(char file[WORD_LENGHT], char path[MAX_PATH],
                     char fullPath[MAX_PATH]) {
  strcpy(fullPath, path);
  strcat(fullPath, "/");
  strcat(fullPath, file);
}

void copy(Vector commands) {
  char from[MAX_PATH], to[MAX_PATH], cwd[MAX_PATH];
  getcwd(cwd, sizeof(cwd));
  if (strcmp(commands.data[1], "-r") == 0) {
    getFullFilePath(commands.data[2], cwd, from);
    getFullFilePath(commands.data[3], cwd, to);
    copyDir(from, to);
  } else {
    struct stat fileType;
    getFullFilePath(commands.data[1], cwd, from);
    getFullFilePath(commands.data[2], cwd, to);
    stat(to, &fileType);
    if (S_ISDIR(fileType.st_blocks)) {
      printf("cp: -r not specified\n");
    } else {
      copyFile(from, to);
    }
  }
}

int main() {
  int size = 0;
  char *input = malloc(sizeof(char[WORD_LENGHT]));
  Vector commands;
  initVector(&commands);
  /* pushBack(&commands, "asd"); */
  /* pushBack(&commands, "asd"); */
  /* commands.size = 0; */
  /* pushBack(&commands, "asd"); */
  /* pushBack(&commands, "asd"); */
  getInputs(&commands);
  getInputs(&commands);
  getInputs(&commands);
  printf("commands size = %d\n", commands.size);
  free(input);
  for (int i = 0; i < commands.size; i++) {
    free(commands.data[i]);
  }
  free(commands.data);
  return 0;
}
