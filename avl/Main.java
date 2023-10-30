import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        AVL avl = new AVL();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAVL Test Menu:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Traversals");
            System.out.println("5. Number of Nodes");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr);
            switch (choice) {
                case 1:
                    System.out.print("Enter the id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("\nEnter the name: ");
                    String nome = scanner.nextLine();
                    System.out.print("\nEnter the amount: ");
                    int qtde = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("\nEnter the unit price: ");
                    float valor = scanner.nextFloat();
                    scanner.nextLine();
                    Estoque p = new Estoque(id, nome, qtde, valor);
                    avl.insert(p);
                    break;
                case 2:
                    System.out.print("Enter id to delete: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    avl.remove(id);
                    break;
                case 3:
                    System.out.print("Enter id to search: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    boolean found = (avl.search(id) != null) ? true: false;
                    String yn = found ? "" : " not";
                    System.out.println("Product with id " + id + yn + " found.");
                    break;
                case 4:
                    System.out.println("Traversals:\n");
                    System.out.println("inOrder:\n\n");
                    System.out.println(avl.inOrderTraversal());
                    System.out.println("preOrder:\n\n");
                    System.out.println(avl.preOrderTraversal());
                    System.out.println("postOrder:\n\n");
                    System.out.println(avl.postOrderTraversal());
                    System.out.println("levelOrder:\n\n");
                    System.out.println(avl.levelOrderTraversal());
                    break;
                case 5:
                    System.out.println("Number of nodes: " + avl.countNodes());
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
