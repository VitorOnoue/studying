/*
Bruno Gustavo Rocha – TIA: 32215029 
Pedro Nogueira Ribeiro – TIA: 31842232 
Vitor Kenzo Koga Onoue – TIA: 32246315 
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AVL avl = new AVL();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAVL Menu:");
            System.out.println("1. Inserir dados de um produto");
            System.out.println("2. Mostrar produtos em estoque");
            System.out.println("3. Procurar produto pelo nome");
            System.out.println("4. Calcular estoque total (R$)");
            System.out.println("5. Mostrar produtos abaixo de um valor");
            System.out.println("6. Sair");
            System.out.print("Entre com sua escolha: ");

            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr);
            System.out.print("\n");
            switch (choice) {
                case 1:
                    System.out.print("Coloque o id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Coloque o nome: ");
                    String nome = scanner.nextLine().toLowerCase();
                    System.out.print("Coloque a quantidade: ");
                    int qtde = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Coloque o preço unitário: ");
                    float valor = scanner.nextFloat();
                    scanner.nextLine();
                    Estoque p = new Estoque(id, nome, qtde, valor);
                    avl.insert(p);
                    break;
                case 2:
                    String ordem = avl.inOrderTraversal();
                    System.out.println(ordem);
                    break;
                case 3:
                    System.out.print("Digite o nome do produto pesquisado: ");
                    String nomeProcurado = scanner.nextLine().toLowerCase();
                    BTNode node = avl.searchByName(nomeProcurado);
                    Estoque found = node != null ? node.getData() : null;
                    if (found != null) {
                        System.out.println("Id: " + found.getCodigoProduto() + "\nNome: " + found.getNome()
                                + "\nQuantidade: " + found.getQtde() + "\nValor unitário: " + found.getValorUnitario()
                                + "\nValor total: " + found.getValorUnitario() * found.getQtde());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 4:
                    float estoqueTotal = avl.calculaEstoqueTotal();
                    System.out.println("Estoque Total: R$ " + estoqueTotal);
                    break;
                case 5:
                    System.out.print("Digite o valor de corte: ");
                    float valorProcurado = scanner.nextFloat();
                    scanner.nextLine();
                    String produtosMenoresQue = avl.mostraProdutosMenoresQue(valorProcurado);
                    if (produtosMenoresQue != "") {
                        System.out.print(produtosMenoresQue);
                    } else {
                        System.out.println("Não há produtos abaixo de R$ " + valorProcurado);
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}