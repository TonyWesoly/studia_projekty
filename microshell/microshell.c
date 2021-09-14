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

void addFileToPath(char path[MAX_USER_PATH], char file[MAX_WORD_LENGHT]) {
  int pathLenght = strlen(path);
  if (path[pathLenght - 1] != '/') {
    strcat(path, "/");
  }
  strcat(path, file);
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
    free(vector->data[i]);
  }
  free(vector->data);
}

void colorBlue() { printf("\e[34m"); }
void colorGreen() { printf("\e[32m"); }
void colorReset() {
  printf("\e[0m");
  fprintf(stderr, "\e[0m");
}
void colorRed() {
  printf("\e[91m");
  fprintf(stderr, "\e[91m");
}

bool isNumberOfArgumentsCorrect(Vector commands, int min, int max) {
  char command[MAX_WORD_LENGHT];
  strcpy(command, commands.data[0]);
  if (commands.size > max) {
    colorRed();
    fprintf(stderr, "%s: too many arguments\n", command);
    colorReset();
    return false;
  } else if (commands.size < min) {
    colorRed();
    fprintf(stderr, "%s: too few arguments\n", command);
    colorReset();
    return false;
  } else {
    return true;
  }
}

void goBackDir(char userPath[MAX_USER_PATH]) {
  int charToRemove = 1;
  char newPath[MAX_USER_PATH] = "";
  int i = 0;
  for (i = strlen(userPath) - 1; i >= 0; i--) {
    if (userPath[i] == '/') {
      break;
    }
    charToRemove++;
  }
  if (charToRemove >= strlen(userPath)) {
    charToRemove = strlen(userPath) - 1;
  }
  memmove(newPath, userPath, strlen(userPath) - charToRemove);
  strcpy(userPath, newPath);
}

void addSlashAtTheEnd(char path[MAX_USER_PATH]) {
  if (path[strlen(path) - 1] != '/') {
    strcat(path, "/");
  }
}

void copyFile(char from[MAX_USER_PATH], char to[MAX_USER_PATH]) {
  const int BUFFER_SIZE = 4096;
  struct stat perm, fileType;
  stat(to, &fileType);
  stat(from, &perm);
  if (S_ISDIR(fileType.st_mode)) {
    addFileToPath(to, strrchr(from, '/'));
  }
  int fileTo = creat(to, perm.st_mode), fileFrom = open(from, O_RDONLY);
  int num = 0;
  char buffer[BUFFER_SIZE];
  while ((num = read(fileFrom, &buffer, BUFFER_SIZE)) > 0) {
    write(fileTo, &buffer, num);
  }
  close(fileTo);
  close(fileFrom);
}

void copyDir(char pathFrom[MAX_USER_PATH], char pathTo[MAX_USER_PATH]) {
  struct stat perm;
  DIR *dir;
  struct dirent *entry;
  stat(pathFrom, &perm);
  mkdir(pathTo, perm.st_mode);
  char fileFrom[MAX_USER_PATH];
  char fileTo[MAX_USER_PATH];
  dir = opendir(pathFrom);
  while ((entry = readdir(dir)) != NULL) {
    if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
      strcpy(fileFrom, pathFrom);
      strcpy(fileTo, pathTo);
      addFileToPath(fileTo, entry->d_name);
      addFileToPath(fileFrom, entry->d_name);
      stat(fileFrom, &perm);
      if (S_ISDIR(perm.st_mode)) {
        copyDir(fileFrom, fileTo);
      } else if (S_ISREG(perm.st_mode)) {
        copyFile(fileFrom, fileTo);
      } else {
        colorRed();
        fprintf(stderr, "cp: wrong file type");
        colorReset();
      }
    }
  }
  closedir(dir);
}

bool doesTheFileExist(char *file) {
  if (access(file, F_OK) == 0) {
    return true;
  } else {
    return false;
  }
}

void copy(Vector commands, char path[MAX_USER_PATH]) {
  char from[MAX_USER_PATH], to[MAX_USER_PATH];
  if (!isNumberOfArgumentsCorrect(commands, 3, 4)) {
    return;
  }
  if (strcmp(commands.data[1], "-r") == 0) {
    if (!isNumberOfArgumentsCorrect(commands, 4, 4)) {
      return;
    }
    strcpy(from, path);
    strcpy(to, path);
    addFileToPath(from, commands.data[2]);
    addFileToPath(to, commands.data[3]);
    if (!doesTheFileExist(from)) {
      colorRed();
      fprintf(stderr, "cp: file doesnt exist\n");
      colorReset();
      return;
    }
    copyDir(from, to);
  } else {
    if (!isNumberOfArgumentsCorrect(commands, 3, 3)) {
      return;
    }
    struct stat fileType;
    strcpy(from, path);
    strcpy(to, path);
    addFileToPath(from, commands.data[1]);
    addFileToPath(to, commands.data[2]);
    if (!doesTheFileExist(from)) {
      colorRed();
      fprintf(stderr, "cp: file doesnt exist\n");
      colorReset();
      return;
    }
    stat(from, &fileType);
    if (S_ISDIR(fileType.st_mode)) {
      colorRed();
      fprintf(stderr, "cp: -r not specified\n");
      colorReset();
    } else {
      copyFile(from, to);
    }
  }
}

void removeWithError(char fileNamePath[MAX_USER_PATH]) {
  if (remove(fileNamePath) == -1) {
    colorRed();
    fprintf(stderr, "rm: cannot remove %s\n", fileNamePath);
    colorReset();
  }
}

void removeFull(char path[MAX_USER_PATH]) {
  struct stat type;
  struct dirent *entry;
  DIR *dir;
  char file[MAX_USER_PATH];
  stat(path, &type);
  if (S_ISREG(type.st_mode)) {
    removeWithError(path);
    return;
  }
  dir = opendir(path);
  while ((entry = readdir(dir)) != NULL) {
    if (strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0) {
      strcpy(file, path);
      addFileToPath(file, entry->d_name);
      removeFull(file);
    }
  }
  closedir(dir);
  removeWithError(path);
}

void rm(Vector commands, char userPath[MAX_USER_PATH]) {
  char filePath[MAX_USER_PATH];
  if (strcmp(commands.data[1], "-r") == 0) {
    int i = 2;
    for (; i < commands.size; i++) {
      strcpy(filePath, userPath);
      addFileToPath(filePath, commands.data[i]);
      removeFull(filePath);
    }
  } else {
    int i = 1;
    for (; i < commands.size; i++) {
      struct stat fileType;
      strcpy(filePath, userPath);
      addFileToPath(filePath, commands.data[i]);
      stat(filePath, &fileType);
      if (S_ISDIR(fileType.st_mode)) {
        colorRed();
        fprintf(stderr, "rm: -r not specified\n");
        colorReset();
      } else {
        removeWithError(filePath);
      }
    }
  }
}

void modifyPath(Vector commands, char userPath[MAX_USER_PATH]) {
  char cdArgument[MAX_WORD_LENGHT];
  if (commands.size == 1) {
    strcpy(cdArgument, "");
  } else {
    strcpy(cdArgument, commands.data[1]);
  }
  if (strcmp(cdArgument, ".") == 0) {
    return;
  } else if (strcmp(cdArgument, "..") == 0) {
    goBackDir(userPath);
  } else if (commands.size == 1) {
    strcpy(userPath, getenv("HOME"));
  } else if (cdArgument[0] == '~') {
    strcpy(userPath, getenv("HOME"));
    if (strlen(cdArgument) > 1) {
      char *restOfPath = strchr(cdArgument, '~');
      addFileToPath(userPath, restOfPath + 2);
    }
  } else if (cdArgument[0] != '/') {
    addFileToPath(userPath, cdArgument);
  } else {
    strcpy(userPath, cdArgument);
  }
}

void runProgram(Vector *arguments) {
  pushBack(arguments, NULL);
  char command[MAX_WORD_LENGHT];
  strcpy(command, arguments->data[0]);
  if (fork() == 0) {
    int statusCode = execvp(command, arguments->data);
    if (statusCode == -1) {
      colorRed();
      perror("exec");
      fprintf(stderr, "error number: %d\n", errno);
      colorReset();
      exit(EXIT_FAILURE);
    }
  } else {
    wait(NULL);
  }
}

void setPath(char oldPath[MAX_USER_PATH], char newPath[MAX_USER_PATH]) {
  DIR *dir;
  if ((dir = opendir(newPath)) == NULL) {
    colorRed();
    perror("cd");
    colorReset();
  } else {
    strcpy(oldPath, newPath);
    chdir(oldPath);
    closedir(dir);
  }
}

void changeDir(Vector commands, char userPath[MAX_USER_PATH]) {
  char newPath[MAX_USER_PATH];
  strcpy(newPath, userPath);
  if (!isNumberOfArgumentsCorrect(commands, 1, 2)) {
    return;
  }
  modifyPath(commands, newPath);
  setPath(userPath, newPath);
}

void help() {
  printf("*** Microshell Sop ***\n");
  printf("Autor: Antoni Paluszyński\n");
  printf("Funkcję obowiązkowe:\n");
  printf("\t-cd\n\t-help\n\t-exit\n\t-programy z PATH\n\t-komunikaty błędów\n");
  printf("Funkcję dodatkowe:\n");
  printf("\t-cp wraz z -r (recursive) oraz kopiowaniem uprawnień\n");
  printf("\t-rm wraz z -r (recursive) oraz usuwaniem wielu plików w jednej "
         "komędzie\n");
  printf("\t-obsługa kolorów\n");
  printf("\t-wyswietlanie obecnie zalogowanego użytkownika\n");
}

void runCommand(Vector *commands, char userPath[MAX_USER_PATH]) {
  char command[MAX_WORD_LENGHT];
  strcpy(command, commands->data[0]);
  colorReset();
  if (strcmp(command, "cd") == 0) {
    changeDir(*commands, userPath);
  } else if (strcmp(command, "cp") == 0) {
    copy(*commands, userPath);
  } else if (strcmp(command, "rm") == 0) {
    rm(*commands, userPath);
  } else if (strcmp(command, "help") == 0) {
    help();
  } else if (strcmp(command, "exit") == 0) {
    clearVector(commands);
    exit(EXIT_SUCCESS);
  } else {
    runProgram(commands);
  }
}

void printPrompt(char userPath[MAX_USER_PATH]) {
  printf("[");
  colorGreen();
  printf("%s", getlogin());
  colorReset();
  printf(":");
  colorBlue();
  printf("%s", userPath);
  colorReset();
  printf("]\n$ ");
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

void runShell(Vector *commands, char userPath[MAX_USER_PATH]) {
  printPrompt(userPath);
  getUserInput(commands);
  runCommand(commands, userPath);
}

void init(Vector *commands, char userPath[MAX_USER_PATH]) {
  setPath(userPath, "/");
  initVector(commands);
}

int main() {
  Vector commands;
  char userPath[MAX_USER_PATH];
  init(&commands, userPath);
  while (true) {
    runShell(&commands, userPath);
  }
  return 0;
}
