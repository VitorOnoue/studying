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
    String str;
    String infixa = "1.5+2*2";
    boolean x = true;
    BTNodeDor a = new BTNodeDor('+', null, null, null);
    BTNodeDor b = new BTNodeDor('*', null, null, a);
    a.setLeft(b);
    BTNodeDor z = new BTNodeDor('*', null, null, a);
    a.setRight(z);
    BTNodeNdo left1 = new BTNodeNdo(3, b);
    BTNodeNdo right1 = new BTNodeNdo(3, b);
    BTNodeNdo left2 = new BTNodeNdo(4, z);
    BTNodeNdo right2 = new BTNodeNdo(4, z);
    b.setLeft(left1);
    b.setRight(right1);
    z.setLeft(left2);
    z.setRight(right2);
    BinaryTree bt = new BinaryTree(a);
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
          str = converter(infixa);
          System.out.println(str);
          bt = new BinaryTree();
          break;

        case 3:
          System.out.println("Exibindo a árvore binária");
          break;

        case 4:
          System.out.println("Calculando sua expressão: ");
          float ge = bt.result();
          System.out.println(ge);
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

  public static String converter(String str) {
    Deque<Character> s = new ArrayDeque<Character>();
    String rpn = "";
    for (int i = 0; i < str.length(); i++) {
      char x = str.charAt(i);
      if (Character.isDigit(x)) {
        StringBuilder number = new StringBuilder();
        while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
          number.append(str.charAt(i));
          i++;
        }
        i--;
        rpn += number.toString() + " ";
      }
      else if (x == '(') {
        s.push(x);
      } 
      else if (x == ')') {
        while (!s.isEmpty() && s.peek() != '(') {
          rpn += s.pop() + " ";
        }
        if (!s.isEmpty() && s.peek() != '(') {
          throw new RuntimeException("Expressão inválida");
        } else {
          s.pop(); 
        }
      }
      else {
        while (!s.isEmpty() && prio(x) <= prio(s.peek())) {
          rpn += s.pop() + " ";
        }
        s.push(x);
      }
    }
    while (!s.isEmpty()) {
      rpn += s.pop() + " ";
    }
    return rpn;
  }

  private static int prio(char op) {
    switch (op) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
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