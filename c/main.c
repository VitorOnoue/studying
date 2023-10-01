#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int maior = 0;   // maior espaço ocupado
int melhor[10];  // combinacao do maior espaco ocupado
int segunda[10]; // combinacao do segundo pendrive
int pendrive;    // tamanho pendrives

void combinacao(int size, int qtd, int arqs[], int comb[], int i, int j) {
  if (j == qtd || size <= 0) { // se acabou os itens ou mochila cheia
    int espaco = 0;            // espaco ocupado pela combinacao
    for (int k = 0; k < i; k++) {
      espaco += comb[k]; // aumenta com os itens
    }
    if (espaco > maior && espaco <= pendrive) { // se o espaco novo for o maior, se torna o "novo" maior
      maior = espaco;
      for (int k = 0; k < i; k++) {
        melhor[k] = comb[k]; // a nova combinacao se torna a "nova" melhor
      }
    }
    return;
  } else if (arqs[j] <= size) { // ver se item menor que mochila
    comb[i] = arqs[j];          // coloca na mochila
    combinacao(size - arqs[j], qtd, arqs, comb, i + 1, j + 1); // chama a funcao de novo, vendo o proximo item
  }
  combinacao(size, qtd, arqs, comb, i, j + 1); // chama a funcao de novo, vendo o proximo item
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
    char *token2 = strtok(NULL, " "); // deixando null apos a primeira vez, ele procede na mesma string
    printf("\n%d GB\n", atoi(token1));

    pendrive = atoi(token1) / 2; // tamanho combinacao
    int qtd = atoi(token2);      // quantidade de arquivos
    int arqs[qtd], comb[qtd];    // arquivos e combinacao

    for (int j = 0; j < qtd; j++) {
      fgets(row2, max, f); // pega os valores dos arquivos
      arqs[j] = atoi(row2);
    }
    combinacao(pendrive, qtd, arqs, comb, 0, 0);
    int flag = 0, segunda_i = 0, soma = 0; // segunda_i = index combinacao pendrive 2
    // gerando pendrive 2 com os arquivos restantes
    for (int i = 0; i < qtd; i++) {
      flag = 0;
      for (int j = 0; j < qtd; j++) {
        if (arqs[i] == melhor[j]) { // se arquivo esta na melhor combinacao, ir pro proximo
          flag = 1;
          break;
        }
      }
      if (!flag) { // se nao estiver, colocar na segunda combinacao e somar
        segunda[segunda_i] = arqs[i];
        segunda_i++;
        soma += arqs[i];
      }
    }
    if (soma > pendrive) { // se os arquivos sao maiores que o pendrive
      printf("\nImpossível gravar todos os arquivos nos combinacao.\n");
    } else { // senao, printar os dois pendrives
      printf("Pendrive A (%d GB): \n", pendrive);
      for (int i = 0; i < 10; i++) {
        if (melhor[i] == 0) {
          break;
        }
        printf("%d GB\n", melhor[i]);
      }
      printf("\nPendrive B (%d GB): \n", pendrive);
      for (int i = 0; i < 10; i++) {
        if (segunda[i] == 0) {
          break;
        }
        printf("%d GB\n", segunda[i]);
      }
    }
    maior = 0;
  }
  fclose(f);
  return 0;
}