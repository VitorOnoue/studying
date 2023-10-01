#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int maior = 0;
int melhor[10];
int outro[10];
int pendrive = 0;

void pendrives(int size, int amount, int arqs[], int s[], int i, int j) {
  if (j == amount || size <= 0) { // se acabou os itens ou mochila cheia
    int total = 0;
    for (int k = 0; k < i; k++) {
      total += s[k];
    }
    if (total > maior && total <= pendrive) {
      maior = total;
      for (int k = 0; k < i; k++) {
        melhor[k] = s[k];
      }
    }
    return;
  } else if (arqs[j] <= size) { // ver se item menor que mochila
    s[i] = arqs[j];             // coloca na mochila
    pendrives(size - arqs[j], amount, arqs, s, i + 1,
              j + 1); // chama a funcao de novo, vendo o proximo item
    s[i] = 0;
  }
  pendrives(size, amount, arqs, s, i,
            j + 1); // chama a funcao de novo, vendo o proximo item
}

int main() {
  FILE *f; // file
  int max = 20;
  f = fopen("backup.in.txt", "r"); // abre arquivo read
  char row1[max];                  // linha 1 (testes)
  fgets(row1, max, f);             // pega linha 1

  for (int i = 0; i < atoi(row1); i++) { // atoi = transforma em int
    char row2[max];      // linha 2 (tamanho do backup, quantidade de arquivos)
    fgets(row2, max, f); // pega linha 2
    char *token1 = strtok(row2, " "); // strtok splita a string no delimiter
    char *token2 = strtok(
        NULL,
        " "); // deixando null apos a primeira vez, ele procede na mesma string
    printf("\n%d GB\n", atoi(token1));
    pendrive = atoi(token1) / 2; // tamanho pendrives
    int amount = atoi(token2);   // quantidade de arquivos

    int arqs[amount]; // arquivos

    for (int j = 0; j < amount; j++) {
      fgets(row2, max, f); // pega os valores dos arquivos
      int x = atoi(row2);
      arqs[j] = x;
    }
    int s[amount];
    pendrives(pendrive, amount, arqs, s, 0, 0);
    int flag = 0, outro_index = 0, soma = 0;
    for (int i = 0; i < amount; i++) {
      flag = 0;
      for (int j = 0; j < amount; j++) {
        if (arqs[i] == melhor[j]) {
          flag = 1;
          break;
        }
      }
      if (!flag) {
        outro[outro_index] = arqs[i];
        outro_index++;
        soma += arqs[i];
      }
    }
    if (soma > pendrive) {
      printf("\nImpossível gravar todos os arquivos nos pendrives.\n");
    }
    else {
      printf("Pendrive A (%d GB): \n", pendrive);
      for (int i = 0; i < 10; i++) {
        if (melhor[i] == 0) {
          break;
        }
        printf("%d GB\n", melhor[i]);
      }
      printf("\nPendrive B (%d GB): \n", pendrive);
      for (int i = 0; i < 10; i++) {
        if (outro[i] == 0) {
          break;
        }
        printf("%d GB\n", outro[i]);
      }
    }
    maior = 0;
  }
  printf("\n");
  fclose(f);
  return 0;
}
