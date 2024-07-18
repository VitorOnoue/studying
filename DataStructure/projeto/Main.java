import java.util.Stack;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    String infixa = "", posfixa;
    BinaryTree bt = null;
    boolean x = true;
    while (x) {
      System.out.println("\n\n\n Árvore binária de expressão aritmética");
      System.out.println("1. Entrada da expressão aritmética na notação infixa");
      System.out.println("2. Criação da árvore binária de expressão aritmética");
      System.out.println("3. Exibição da árvore binária de expressão aritmética");
      System.out.println("4. Cálculo da expressão (realizando o percurso da árvore)");
      System.out.println("5. Encerramento do programa");

      int escolha = s.nextInt();
      s.nextLine();

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
          posfixa = converter(infixa);
          System.out.println(posfixa);
          bt = new BinaryTree(posfixa);
          break;

        case 3:
          System.out.println("Exibindo a árvore binária");
          System.out.println(bt.inOrderTraversal());
          System.out.println(bt.preOrderTraversal());
          System.out.println(bt.postOrderTraversal());
          break;

        case 4:
          System.out.println("Calculando sua expressão: ");
          System.out.println(bt.result());
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
    if(str.equals("")){
      System.out.println("Expressão vazia.");
      return false;
    };
    Stack<Character> s = new Stack<Character>();
    if(isOp(str.charAt(0)) || isOp(str.charAt(str.length()-1))){
      if(str.charAt(0) != '-'){
        System.out.println("Operador binário com apenas um valor.");
        return false;
      }
    }
    for(int i = 0; i < str.length(); i++){
      char x = str.charAt(i);
      if(x == '('){
        if(str.charAt(i+1) == ')'){
          System.out.println("Parênteses sem conteúdo.");
          return false;
        }
        s.push(x);
      }
      else if(x == ')'){
        if(!s.isEmpty()){
          s.pop();
        }
        else{
          System.out.println("Parênteses em ordem contrária, ou parênteses direito sem esquerdo.");
          return false;
        }
      }
      else if(isOp(x)){
        if(i != 0 && str.charAt(i) != '-'){
          count++;
          if(isOp(str.charAt(i-1))){
            System.out.println("Dois operadores seguidos.");
            return false;
          }
        }
      }
      else if(Character.isDigit(x) || x == '.'){
        continue;
      }
      else{
        System.out.println("Caracter inválido.");
        return false;
      }
    }
    if(!s.isEmpty()){
      System.out.println("Parênteses sem fechar.");
      return false;
    }
    if(count == 0){
      System.out.println("Sem operadores.");
      return false;
    }
    return true;
  }

  public static String converter(String str) {
    Stack<Character> s = new Stack<Character>();
    String rpn = "";
    for (int i = 0; i < str.length(); i++) {
      char x = str.charAt(i);
      if (Character.isDigit(x) || (i == 0 && x == '-')) {
        StringBuilder number = new StringBuilder();
        while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || (i == 0 && x == '-'))) {
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
    return false;
  }
}