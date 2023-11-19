import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

public class Main {
    static int showCounter = 0;
    static int movieCounter = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        BST bst = new BST();
        AVL avl = new AVL();
        int choice = 0;
        while (choice != 8) {
            System.out.println("\nMenu:");
            System.out.println("1 - Ler dados de arquivo");
            System.out.println("2 - Cinco opções contendo métodos para análise de dados");
            System.out.println("3 - Inserir Programa");
            System.out.println("4 - Buscar Programa");
            System.out.println("5 - Remover Programa");
            System.out.println("6 - Exibir a Altura das Árvores");
            System.out.println("7 - Salvar dados em arquivo");
            System.out.println("8 - Encerrar a aplicação");

            System.out.print("\nDigite a opção desejada: ");
            choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Digite o nome do arquivo de dados (dataset): ");
                    String nomeArquivo = s.nextLine();
                    try {
                        Scanner dataset = new Scanner(new File(nomeArquivo));
                        dataset.nextLine();
                        while (dataset.hasNextLine()) {
                            String[] infos = splitter(dataset.nextLine());
                            boolean valid;
                            if (infos[3].equals("SHOW")) { // infos[3] = tipo do programa
                                valid = validateShow(infos);
                            } else {
                                valid = validateMovie(infos, 9); // campo 9 = temporadas
                            }
                            if (valid) {
                                ProgramaNetFlix pn = new ProgramaNetFlix(infos);
                                Node node = new Node(pn);
                                bst.insert(node);
                                avl.insert(node);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    System.out.println("****************");
                    System.out.println("Análise de dados");
                    System.out.println("****************");

                    System.out.println("1: Todas as séries com mais de 20 temporadas e suas notas IMDB");
                    System.out.println("2: ID dos programas produzidos fora dos estados unidos antes de 1960");
                    System.out.println("3: IMBD ID de series com mais de 200.000 votos IMDB e score maior que 75");
                    System.out.println(
                            "4: Nome de filmes do gênero drama com mais de 100 minutos de duração agrupados por certificação de idade");
                    System.out
                            .println("5: ID dos filmes com popularidade tmdb maior que 5000 e nota tmdb maior que 75");

                    int opcaoAnalise = s.nextInt();

                    switch (opcaoAnalise) {
                        case 1:
                            avl.showSeasonsImdb_score(avl.getRoot());
                            break;

                        case 2:
                            avl.notUSBefore1960(avl.getRoot());
                            break;

                        case 3:
                            avl.showImdbVotes_score(avl.getRoot());
                            break;

                        case 4:
                            avl.dramaMovies100min(avl.getRoot());
                            break;

                        case 5:
                            avl.moviePopularity_score(avl.getRoot());
                            break;

                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }

                    break;

                case 3:
                    System.out.print("Digite a categoria do programa (SHOW ou MOVIE): ");
                    String categoria = s.nextLine().toUpperCase();
                    System.out.print("\nDigite:\nO id do programa: ");
                    String id = s.nextLine();
                    if (categoria.equals("SHOW")) {
                        id = "ts" + id;
                    } else if (categoria.equals("MOVIE")) {
                        id = "tm" + id;
                    } else {
                        System.out.println("Categoria inválida.");
                        break;
                    }
                    String[] inserir = {id, "lorem ipsum", categoria, "lorem ipsum", "123", "lorem ipsum", "123", "[lorem, ipsum]", "[lorem, ipsum]", "123", "lorem ipsum", "12.3", "12.3", "12.3", "12.3"};
                    ProgramaNetFlix novoPrograma = new ProgramaNetFlix(inserir);
                    Node novoNode = new Node(novoPrograma);
                    bst.insert(novoNode);
                    avl.insert(novoNode);
                    System.out.println("Programa inserido com sucesso!");
                    break;

                case 4:
                    System.out.print("Digite o id de um programa para buscar: ");
                    String buscar = s.nextLine();
                    BST.Search busca_bst = bst.searchPrograma(buscar);
                    BST.Search busca_avl = avl.searchPrograma(buscar);
                    System.out.println("BST - Título: " + busca_bst.getTitulo());
                    System.out.println("BST - Comparações: " + busca_bst.getCount());
                    System.out.println("AVL - Título: " + busca_avl.getTitulo());
                    System.out.println("AVL - Comparações: " + busca_avl.getCount());
                    break;

                case 5:
                    System.out.print("Digite o id de um programa: ");
                    String remover = s.nextLine();
                    boolean bst_remove = bst.remove(remover);
                    boolean avl_remove = avl.remove(remover);
                    if (bst_remove) {
                        System.out.println("Programa removido da BST!");
                    }
                    if (avl_remove) {
                        System.out.println("Programa removido da AVL!");
                    }
                    break;

                case 6:
                    System.out.println("Altura BST: " + bst.getHeight());
                    System.out.println("Altura AVL: " + avl.getHeight());
                    break;

                case 7:
                    System.out.print("Informe o nome do arquivo que gostaria de salvar: ");
                    String filename = s.nextLine();
                    try {
                        File f = new File(filename);
                        try {
                            f.createNewFile();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        FileWriter w = new FileWriter(f);
                        StringBuilder sb = new StringBuilder();
                        avl.save(avl.getRoot(), sb);
                        w.write(sb.toString());
                        w.close();
                    } catch (IOException e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    break;

                case 8:
                    avl.inordertraversal(avl.getRoot());
                    System.out.println("\nEncerrando aplicação...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        s.close();
    }

    private static String[] splitter(String row) {
        String[] fields = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replaceAll("^\"|\"$", "");
        }
        return fields;
    }

    private static boolean validateShow(String[] infos) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].equals("")) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateMovie(String[] infos, int index) {
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].equals("") && i != index) {
                return false;
            }
        }
        return true;
    }
}