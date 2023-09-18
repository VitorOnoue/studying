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
    String infixa;
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
          infixa = s.nextLine().trim().replaceAll("\\s+", "");
          boolean y = isValid(infixa);
          if(y){
            System.out.println("Expressão válida!");
          }
          else{
            System.out.println("Expressão inválida, por favor digite novamente.");
            infixa = "";
          }
          break;

        case 2:
          System.out.println("Criando árvore binária");
          // construir a arvore
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

  public static boolean isValid(String str){
    int par = 0;
    for(int i = 0; i < str.length(); i++){
      char x = str.charAt(i);
      System.out.println(x);
      if(x == '('){
        par++;
        continue;
      }
      else if(x == ')'){
        par--;
        continue;
      }
      else if(Character.isLetter(x)){
        System.out.println("letter");
        return false;
      }
      else if(x == '.'){
        if(!Character.isDigit(str.charAt(i-1)) && !Character.isDigit(str.charAt(i+1))){
          System.out.println("ponto perdido");
          return false;
        }
        continue;
      }
      else if(isOp(x)){
        if(i == 0 || i == str.length() - 1){
          System.out.println("operador sem dois");
          return false;
        }
      }
      if(!(isOp(x) || Character.isDigit(x))){
        System.out.println("operador invalido ou não é numero");
        return false;
      }
    }
    if(par != 0){
      System.out.println("parenteses nao fechou");
      return false;
    }
    return true;
  }
  
  public static boolean isOp(char x){
    if (x == '+' || x == '-' || x == '*' || x == '/' || x == '–'){
      return true;
    }
    else{
      return false;
    }
  }
}