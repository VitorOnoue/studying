import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Main {
    static int showCounter = 0;
    static int movieCounter = 0;

    public static void main(String[] args) throws InterruptedException {
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
                            boolean valid = true;
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

                    System.out.println("1: As dez séries com melhores notas no IMDB que possuem mais de 5 temporadas");
                    System.out.println("2: Sinopses dos programas produzidos fora dos Estados Unidos antes de 1980");
                    System.out.println("3: IMDB ID de séries com mais de 200.000 votos e nota maior que 7.5 pelo IMDB");
                    System.out.println("4: Filmes de drama com mais de duas horas e meia de duração");
                    System.out.println("5: Os cinco programas de adolescentes (mais de 13 anos de idade) menos populares e com notas menores que 6 pelo TMDB");

                    System.out.print("\nDigite a opção desejada: ");
                    int opcaoAnalise = s.nextInt();

                    switch (opcaoAnalise) {
                        case 1:
                            ArrayList<Float> notas = new ArrayList<Float>();
                            ArrayList<String> titulos_series = new ArrayList<String>();
                            avl.showSeasonsImdb_score(avl.getRoot(), notas, titulos_series);
                            avl.bubbleSort(notas, titulos_series, 1);
                            for (int i = 0; i < 10; i++) {
                                System.out.println("Nome da série: " + titulos_series.get(i) + " - IMDB: " + notas.get(i));
                            }
                            break;

                        case 2:
                            avl.notUSBefore1980(avl.getRoot());
                            break;

                        case 3:
                            avl.showImdbVotes_score(avl.getRoot());
                            break;

                        case 4:
                            avl.docuMovies(avl.getRoot());
                            break;

                        case 5:
                            ArrayList<Float> popularidade = new ArrayList<Float>();
                            ArrayList<String> titulos_programas = new ArrayList<String>();
                            avl.ageTMDB(avl.getRoot(), popularidade, titulos_programas);
                            avl.bubbleSort(popularidade, titulos_programas, 0);
                            for (int i = 0; i < 5; i++) {
                                System.out.println("Título: " + titulos_programas.get(i) + " - Popularidade: " + popularidade.get(i));
                            }
                            break;

                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;

                case 3:
                    System.out.print("Digite a categoria do programa (SHOW ou MOVIE): ");
                    String categoria = s.nextLine().toUpperCase();
                    System.out.print("Digite o id do programa: ");
                    String id = s.nextLine();
                    if (categoria.equals("SHOW")) {
                        id = "ts" + id;
                    } else if (categoria.equals("MOVIE")) {
                        id = "tm" + id;
                    } else {
                        System.out.println("Categoria inválida.");
                        break;
                    }
                    String[] inserir = { id, "lorem ipsum", categoria, "lorem ipsum", "123", "lorem ipsum", "123",
                            "[lorem, ipsum]", "[lorem, ipsum]", "123", "lorem ipsum", "12.3", "12.3", "12.3", "12.3" };
                    ProgramaNetFlix novoPrograma = new ProgramaNetFlix(inserir);
                    Node novoNode = new Node(novoPrograma);
                    bst.insert(novoNode);
                    avl.insert(novoNode);
                    System.out.println("Programa inserido com sucesso!");
                    break;

                case 4:
                    System.out.print("Digite o id de um programa para buscar: ");
                    String buscar = s.nextLine();

                    long start = System.nanoTime();
                    BST.Search busca_bst = bst.searchPrograma(buscar);
                    long end = System.nanoTime();
                    long bst_time = end - start;
                    if (busca_bst.getTitulo().equals("")) {
                        System.out.println("Programa não encontrado na BST.");
                    } else {
                        System.out.println("BST - Título: " + busca_bst.getTitulo());
                        System.out.println("BST - Comparações: " + busca_bst.getCount() + " - Tempo de execução: " + bst_time);
                    }

                    start = System.nanoTime();
                    BST.Search busca_avl = avl.searchPrograma(buscar);
                    end = System.nanoTime();
                    long avl_time = end - start;
                    if (busca_avl.getTitulo().equals("")) {
                        System.out.println("Programa não encontrado na AVL.");  
                    } else {
                        System.out.println("AVL - Título: " + busca_avl.getTitulo());
                        System.out.println("AVL - Comparações: " + busca_avl.getCount() + " - Tempo de execução: " + avl_time);
                    }
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