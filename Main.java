/*
Participantes:
Bruno Gustavo Rocha - 32215029
Pedro Nogueira Ribeiro - 31842232
Vitor Kenzo Koga Onoue - 32246315
*/
/*
.trim().replaceAll("\\s+", ""); = remove espaços no começo, meio e fim
*/

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    boolean x = true;
    while (x) {
      System.out.println("\n\n\n Árvore binária de expressão aritmética");
      System.out.println("1. Entrada da expressão aritmética na notação infixa");
      System.out.println("2. Criação da árvore binária de expressão aritmética");
      System.out.println("3. Exibição da árvore binária de expressão aritmética");
      System.out.println("4. Cálculo da expressão (realizando o percurso da árvore)");
      System.out.println("5. Encerramento do programa");

      int escolha = s.nextInt();

      switch (escolha) {
        case 1:
          System.out.println("Escreva sua expressão aritmética: ");
          String infixa = s.nextLine();
          ValidaExpressao();
          break;

        case 2:
          System.out.println("Criando árvore binária");
          // contruir a arvore
          ValidaArvore(arvore);
          break;

        case 3:
          System.out.println("Exibindo a árvore binária");
          ImprimeArvore(arvore);
          break;

        case 4:
          System.out.println("Calculando sua expressão: ");
          CalculoExpressao(arvore);
          break;

        case 5:
          System.out.println("flw tmj");
          s.close();
          x = false;
          break;

        default:
          System.out.println("Por favor, escolha uma opção válida.");
          break;
      }
    }
  }
}