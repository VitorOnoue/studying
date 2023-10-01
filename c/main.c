#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void prints(int s[], int amount) {
  for (int i = 0; i < amount; i++) {
    printf("%d\n", s[i]);
  }
}

void pendrives(int size, int amount, int arqs[], int s[], int i) {
  if (i == amount || size <= 0) {
    prints(s, amount);
  }
  for (int j = 0; j < amount; j++) {
    if (arqs[i] <= size) {
      s[i] = arqs[i];
      pendrives(size - s[i], amount, arqs, s, i + 1);
    }
  }
}

int main() {
  FILE *f; // file
  int max = 20;
  f = fopen("backup.in.txt", "r"); // abre arquivo read
  char row1[max];                  // linha 1 (testes)
  fgets(row1, max, f);             // pega linha 1
  printf("%d\n", atoi(row1));

  for (int i = 0; i < atoi(row1); i++) { // atoi = transforma em int
    char row2[max];      // linha 2 (tamanho do backup, quantidade de arquivos)
    fgets(row2, max, f); // pega linha 2
    printf("%s", row2);
    char *token1 = strtok(row2, " "); // strtok splita a string no delimiter
    char *token2 = strtok(
        NULL,
        " "); // deixando null ap-os a primeira vez, ele procede na mesma string
    int pens = atoi(token1) / 2; // tamanho pendrives
    int amount = atoi(token2);   // quantidade de arquivos

    int arqs[amount]; // arquivos

    for (int j = 0; j < amount; j++) {
      fgets(row2, max, f); // pega os valores dos arquivos
      int x = atoi(row2);
      arqs[j] = x;
    }
    pendrives(pens, amount, arqs, 0);
  }

  fclose(f);
  return 0;
}
