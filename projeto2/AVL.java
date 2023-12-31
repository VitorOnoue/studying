import java.util.ArrayList;

public class AVL extends BST {

    private int nElem; // Número de elementos (nós) na árvore

    public AVL() {
        super();
    }

    public int getNElem() {
        return nElem;
    }

    public void setNElem(int nElem) {
        this.nElem = nElem;
    }

    /* rotação tipo LL */
    private Node rotacaoLL(Node desbA) {
        Node aux;

        aux = desbA.getLeft();

        if (desbA.getParent() != null) { /* verifica se desb n o a raiz */
            if (desbA.getParent().getLeft() == desbA)
                desbA.getParent().setLeft(aux);
            else
                desbA.getParent().setRight(aux);
        }

        aux.setParent(desbA.getParent());
        desbA.setLeft(aux.getRight());

        if (desbA.getLeft() != null)
            desbA.getLeft().setParent(desbA);

        aux.setRight(desbA);
        desbA.setParent(aux);

        return (aux);
    }

    /* rotação tipo RR */
    private Node rotacaoRR(Node desbA) {
        Node aux;

        aux = desbA.getRight();
        if (desbA.getParent() != null) /* verifica se desb n o a raiz */
            if (desbA.getParent().getLeft() == desbA)
                desbA.getParent().setLeft(aux);
            else
                desbA.getParent().setRight(aux);

        aux.setParent(desbA.getParent());
        desbA.setRight(aux.getLeft());
        if (desbA.getRight() != null)
            desbA.getRight().setParent(desbA);
        aux.setLeft(desbA);
        desbA.setParent(aux);

        return (aux);
    }

    /* rotação tipo LR */
    private Node rotacaoLR(Node desbA) {
        desbA.setLeft(rotacaoRR(desbA.getLeft()));
        return (rotacaoLL(desbA));
    }

    /* rotação tipo RL */
    private Node rotacaoRL(Node desbA) {
        desbA.setRight(rotacaoLL(desbA.getRight()));
        return (rotacaoRR(desbA));
    }

    private boolean flagInsercao;

    public void insert(Node node) {
        flagInsercao = false;
        setRoot(insereNoAVL(getRoot(), node.getData()));
        setNElem(getNElem() + 1);
    }

    private Node insereNoAVL(Node noAtual, ProgramaNetFlix data) {
        if (noAtual != null) { // se arvore vazia/chegar no fim de uma branch
            if (noAtual.getData().getId().equals(data.getId())) {
                return noAtual;
            }
            int comp = data.getId().compareTo(noAtual.getData().getId());
            if (comp < 0) {
                noAtual.setLeft(insereNoAVL(noAtual.getLeft(), data));
                noAtual.getLeft().setParent(noAtual);
                if (flagInsercao) {
                    switch (noAtual.getFb()) {
                        case -1:
                            noAtual.setFb(0);
                            flagInsercao = false;
                            break;
                        case 0:
                            noAtual.setFb(1);
                            break;
                        case 1:
                            if ((noAtual.getLeft()).getFb() == 1) {
                                noAtual = rotacaoLL(noAtual);
                                // Arrumando os fatores após a rota o
                                noAtual.setFb(0);
                                (noAtual.getRight()).setFb(0);
                            } else {
                                noAtual = rotacaoLR(noAtual);
                                // Arrumando os fatores após a rotação
                                if (noAtual.getFb() == 0) { // 1o Caso
                                    (noAtual.getRight()).setFb(0);
                                    (noAtual.getLeft()).setFb(0);
                                } else if (noAtual.getFb() == 1) { // 2o Caso
                                    (noAtual.getLeft()).setFb(0);
                                    (noAtual.getRight()).setFb(-1);
                                } else { // 3o Caso
                                    (noAtual.getLeft()).setFb(1);
                                    (noAtual.getRight()).setFb(0);
                                }
                                noAtual.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                    }
                }
            } else {
                noAtual.setRight(insereNoAVL(noAtual.getRight(), data));
                noAtual.getRight().setParent(noAtual);
                if (flagInsercao) {
                    switch (noAtual.getFb()) {
                        case -1:
                            if (noAtual.getRight().getFb() == -1) {
                                noAtual = rotacaoRR(noAtual);
                                // Arrumando os fatores após a rotação
                                noAtual.setFb(0);
                                (noAtual.getLeft()).setFb(0);
                            } else if (noAtual.getRight().getFb() == 1) {
                                noAtual = rotacaoRL(noAtual);
                                // Arrumando os fatores após a rotação
                                if (noAtual.getFb() == 0) { // 1o Caso
                                    (noAtual.getRight()).setFb(0);
                                    (noAtual.getLeft()).setFb(0);
                                } else if (noAtual.getFb() == -1) { // 2o Caso
                                    (noAtual.getRight()).setFb(0);
                                    (noAtual.getLeft()).setFb(-1);
                                } else { // 3o Caso
                                    (noAtual.getRight()).setFb(-1);
                                    (noAtual.getLeft()).setFb(0);
                                }
                                noAtual.setFb(0);
                            } else {
                                noAtual.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                        case 0:
                            noAtual.setFb(-1);
                            break;
                        case 1:
                            noAtual.setFb(0);
                            flagInsercao = false;
                            break;

                    }
                }
            }
        } else {
            noAtual = new Node(data, null, null, null, 0);
            flagInsercao = true;
        }
        return (noAtual);
    }

    private boolean flagRemove;

    public boolean remove(String id) {
        flagRemove = false;
        if (getRoot() == null) {
            System.out.println("Erro na remoção, a árvore está vazia !");
            return false;
        } else if (search(id) == null) {
            System.out.println("Erro na remoção, elemento não existente !");
            return false;
        } else {
            setRoot(removeNoAVL(getRoot(), id));
            setNElem(getNElem() - 1);
            return true;
        }
    }

    private Node removeNoAVL(Node noAtual, String id) {
        int comp = noAtual.getData().getId().compareTo(id);
        if (comp > 0) {
            noAtual.setLeft(removeNoAVL(noAtual.getLeft(), id));
            if (flagRemove)
                noAtual = balanceamentoEsquerdo(noAtual);
        } else if (comp < 0) {
            noAtual.setRight(removeNoAVL(noAtual.getRight(), id));
            if (flagRemove)
                noAtual = balanceamentoDireito(noAtual);
        } else { // Encontrou o nó a ser removido
            if (noAtual.getRight() == null) {
                if (noAtual.getLeft() != null) { // Escolhe o nó à esquerda como substituto
                    noAtual.getLeft().setParent(noAtual.getParent());
                }
                noAtual = noAtual.getLeft();
                flagRemove = true;
            } else if (noAtual.getLeft() == null) {
                if (noAtual.getRight() != null) { // Escolhe o nó à direita como substituto
                    noAtual.getRight().setParent(noAtual.getParent());
                }
                noAtual = noAtual.getRight();
                flagRemove = true;
            } else { // Busca o elemento mais à direita do nó esquerdo
                noAtual.setLeft(buscaRemove(noAtual.getLeft(), noAtual));
                // Se necessário efetua balanceamento (Esquerdo pois a função
                // busca_remove foi para o nó esquerdo)
                if (flagRemove) {
                    noAtual = balanceamentoEsquerdo(noAtual);
                }
            }
        }

        return noAtual;
    }

    private Node balanceamentoEsquerdo(Node no) {
        switch (no.getFb()) {
            case 1:
                no.setFb(0);
                break;
            case 0:
                no.setFb(-1);
                flagRemove = false;
                break;
            case -1:
                Node subDir = no.getRight();
                int fb = subDir.getFb();
                if (fb <= 0) {
                    subDir = rotacaoRR(no);
                    if (fb == 0) {
                        no.setFb(-1);
                        subDir.setFb(1);
                        flagRemove = false;
                    } else {
                        no.setFb(0);
                        subDir.setFb(0);
                    }
                    no = subDir;
                } else {
                    no = rotacaoRL(no);
                    if (no.getFb() == 0) {
                        (no.getRight()).setFb(0);
                        (no.getLeft()).setFb(0);
                    } else if (no.getFb() == -1) {
                        no.setFb(0);
                        (no.getRight()).setFb(0);
                        (no.getLeft()).setFb(1);
                    } else {
                        no.setFb(0);
                        (no.getRight()).setFb(-1);
                        (no.getLeft()).setFb(0);
                    }
                }
        }
        return (no);
    }

    private Node balanceamentoDireito(Node no) {
        switch (no.getFb()) {
            case -1:
                no.setFb(0);
                break;
            case 0:
                no.setFb(1);
                flagRemove = false;
                break;
            case 1:
                Node subEsq = no.getLeft();
                int fb = subEsq.getFb();
                if (fb >= 0) {
                    subEsq = rotacaoLL(no);
                    if (fb == 0) {
                        no.setFb(1);
                        subEsq.setFb(-1);
                        flagRemove = false;
                    } else {
                        no.setFb(0);
                        subEsq.setFb(0);
                    }
                    no = subEsq;
                } else {
                    no = rotacaoLR(no);
                    if (no.getFb() == 0) {
                        (no.getRight()).setFb(0);
                        (no.getLeft()).setFb(0);
                    } else if (no.getFb() == 1) {
                        no.setFb(0);
                        (no.getRight()).setFb(-1);
                        (no.getLeft()).setFb(0);
                    } else {
                        no.setFb(0);
                        (no.getRight()).setFb(0);
                        (no.getLeft()).setFb(1);
                    }
                }
        }
        return (no);
    }

    private Node buscaRemove(Node noAtual, Node noChave) {
        Node noRemovido;
        if (noAtual.getRight() != null) {
            noAtual.setRight(buscaRemove(noAtual.getRight(), noChave));
            if (flagRemove) {
                noAtual = balanceamentoDireito(noAtual);
            }
        } else {
            noChave.setData(noAtual.getData());
            noRemovido = noAtual;
            noAtual = noAtual.getLeft();
            if (noAtual != null) {
                noAtual.setParent(noRemovido.getParent());
            }
            flagRemove = true;
            noRemovido = null;
        }
        return (noAtual);
    }

    public void bubbleSort(ArrayList<Float> x, ArrayList<String> y, int sort_type) {
        int size = x.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                boolean troca = sort_type == 1 ? x.get(j) < x.get(j + 1) : x.get(j) > x.get(j + 1);
                if (troca) {
                    Float aux_x = x.get(j);
                    x.set(j, x.get(j + 1));
                    x.set(j + 1, aux_x);
                    String aux_y = y.get(j);
                    y.set(j, y.get(j + 1));
                    y.set(j + 1, aux_y);
                }
            }
        }
    }

    public void showSeasonsImdb_score(Node root, ArrayList<Float> notas, ArrayList<String> titulos) {
        if (root != null) {
            showSeasonsImdb_score(root.getLeft(), notas, titulos);
            ProgramaNetFlix programa = root.getData();
            if (programa.getShow_type().equals("SHOW") && programa.getTemporadas() > 5) {
                notas.add(programa.getImdb_score());
                titulos.add(programa.getTitulo());
            }
            showSeasonsImdb_score(root.getRight(), notas, titulos);
        }
    }

    public void notUSBefore1980(Node root) {
        if (root != null) {
            ProgramaNetFlix programa = root.getData();
            String[] paises = programa.getProduction_countries();
            boolean contains = false;
            for (String str : paises) {
                if (str.equals("US")) {
                    contains = true;
                }
            }
            if (!contains && programa.getRelease_year() < 1980) {
                System.out.println(
                        "\nTítulo: " + programa.getTitulo() + " - Ano de produção: " + programa.getRelease_year()
                                + " - Tipo: " + programa.getShow_type() + "\nSinopse: " + programa.getDescricao());
            }
            notUSBefore1980(root.getLeft());
            notUSBefore1980(root.getRight());
        }
    }

    public void showImdbVotes_score(Node root) {
        if (root != null) {
            ProgramaNetFlix programa = root.getData();
            if (programa.getShow_type().equals("SHOW") && programa.getImdb_score() > 7.5
                    && programa.getImdb_votes() > 200000) {
                System.out.println("IMDB ID: " + programa.getImdb_id() + " - Votos IMDB: " + programa.getImdb_votes()
                        + " - Nota IMDB: " + programa.getImdb_score());
            }
            showImdbVotes_score(root.getLeft());
            showImdbVotes_score(root.getRight());
        }
    }

    public void docuMovies(Node root) {
        if (root != null) {
            ProgramaNetFlix programa = root.getData();
            String[] generos = programa.getGeneros();
            boolean contains = false;
            for (String str : generos) {
                if (str.equals("documentation")) {
                    contains = true;
                }
            }
            if (contains && programa.getRuntime() > 150) {
                Float horas = (float) programa.getRuntime() / 60;
                System.out.printf("Filme: %s - Duração: %.2f\n", programa.getTitulo(), horas);
            }
            docuMovies(root.getLeft());
            docuMovies(root.getRight());
        }
    }

    public void ageTMDB(Node root, ArrayList<Float> popularidade, ArrayList<String> titulos) {
        if (root != null) {
            ProgramaNetFlix programa = root.getData();
            ageTMDB(root.getLeft(), popularidade, titulos);
            ageTMDB(root.getRight(), popularidade, titulos);
            Float tmdb = programa.getTmdb_score();
            if (programa.getAge_certification().equals("PG-13") && tmdb < 6) {
                popularidade.add(programa.getTmdb_popularity());
                titulos.add(programa.getTitulo());
            }
        }
    }

    public void save(Node node, StringBuilder sb) {
        if (node != null) {
            save(node.getLeft(), sb);
            ProgramaNetFlix data = node.getData();
            String genres = "[", countries = "[";
            int i;
            if (data.getGeneros().length == 1) {
                genres += "'" + data.getGeneros()[0] + "']";
            } else {
                for (i = 0; i < data.getGeneros().length - 1; i++) {
                    genres += "'" + data.getGeneros()[i] + "', ";
                }
                genres += "'" + data.getGeneros()[i] + "']";
            }
            if (data.getProduction_countries().length == 1) {
                countries += "'" + data.getProduction_countries()[0] + "']";
            } else {
                for (i = 0; i < data.getProduction_countries().length - 1; i++) {
                    countries += "'" + data.getProduction_countries()[i] + "', ";
                }
                countries += "'" + data.getProduction_countries()[i] + "']";
            }
            sb.append(
                    data.getId() + ";" +
                            data.getTitulo() + ";" +
                            data.getShow_type() + ";" +
                            data.getDescricao() + ";" +
                            data.getRelease_year() + ";" +
                            data.getAge_certification() + ";" +
                            data.getRuntime() + ";" +
                            genres + ";" +
                            countries + ";" +
                            data.getTemporadas() + ";" +
                            data.getImdb_id() + ";" +
                            data.getImdb_score() + ";" +
                            data.getImdb_votes() + ";" +
                            data.getTmdb_popularity() + ";" +
                            data.getTmdb_score() + ";\n");
            save(node.getRight(), sb);
        }
    }
}
