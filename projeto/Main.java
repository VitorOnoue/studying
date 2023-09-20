/*
Participantes:
Bruno Gustavo Rocha - 32215029
Pedro Nogueira Ribeiro - 31842232
Vitor Kenzo Koga Onoue - 32246315
*/
/*
.trim().replaceAll("\\s+", ""); = remove espaços no começo, meio e fim
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    BinaryTree bt;
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
          bt = new BinaryTree();
          ValidaArvore(arvore);
          break;

        case 3:
          System.out.println("Exibindo a árvore binária");
          bt.preOrderTraversal();
          bt.postOrderTraversal();
          bt.inOrderTraversal();
          break;

        case 4:
          System.out.println("Calculando sua expressão: ");
          bt.result();
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
    int count = 0;
    if(str == ""){
      return false;
    }
    Deque<Character> s = new ArrayDeque<Character>();
    if(isOp(str.charAt(0)) || isOp(str.charAt(str.length()-1))){
      return false;
    }
    for(int i = 0; i < str.length(); i++){
      char x = str.charAt(i);
      if(x == '('){
        if(str.charAt(i+1) == ')'){
          return false;
        }
        s.push(x);
      }
      else if(x == ')'){
        if(!s.isEmpty()){
          char y = s.pop();
          if(y != '('){
            return false;
          }
        }
        else{
          return false;
        }
      }
      else if(isOp(x)){
        count++;
        if(isOp(str.charAt(i-1))){
          return false;
        }
      }
      else if(Character.isDigit(x) || x == '.'){
        continue;
      }
      else{
        return false;
      }
    }
    if(!s.isEmpty() || count == 0){
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