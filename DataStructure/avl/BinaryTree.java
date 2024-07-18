//
//Árvore Binária - Exemplo de implementação em Java
//Copyright (C) 2023 André Kishimoto
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

//imports para a fila usada na levelOrderTraversalHelper().
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private BTNode root;

    public BinaryTree() {
        this(null);
    }

    public BinaryTree(BTNode root) {
        this.root = root;
    }

    public BTNode getRoot() {
        return root;
    }

    public void setRoot(BTNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getDegree() {
        return getDegreeHelper(root);
    }

    private int getDegreeHelper(BTNode node) {
        if (node == null || node.isLeaf()) {
            return 0;
        }

        int degree = node.getDegree();
        if (node.hasLeftChild())
            degree = Math.max(degree, getDegreeHelper(node.getLeft()));
        if (node.hasRightChild())
            degree = Math.max(degree, getDegreeHelper(node.getRight()));
        return degree;
    }

    public int getHeight() {
        if (isEmpty()) {
            return -1;
        }

        return root.getHeight();
    }

    public String inOrderTraversal() {
        return inOrderTraversalHelper(root);
    }

    private String inOrderTraversalHelper(BTNode node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(inOrderTraversalHelper(node.getLeft()));
        Estoque x = node.getData();
        sb.append("Id: " + x.getCodigoProduto() + " - nome: " + x.getNome() + " - quantidade: " + x.getQtde()
                + " - valor unitário: " + x.getValorUnitario() + "\n");
        sb.append(inOrderTraversalHelper(node.getRight()));

        return sb.toString();
    }

    public float calculaEstoqueTotal() {
        return calculaEstoqueTotalHelper(root);
    }

    private float calculaEstoqueTotalHelper(BTNode node) {
        if (node == null) {
            return 0;
        }

        float totalEstoque = 0;
        totalEstoque += calculaEstoqueTotalHelper(node.getLeft());
        Estoque x = node.getData();
        totalEstoque += x.getQtde() * x.getValorUnitario();
        totalEstoque += calculaEstoqueTotalHelper(node.getRight());

        return totalEstoque;
    }

    public String mostraProdutosMenoresQue(float valor) {
        return mostraProdutosMenoresQueHelper(root, valor);
    }

    private String mostraProdutosMenoresQueHelper(BTNode node, float valor) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        sb.append(mostraProdutosMenoresQueHelper(node.getLeft(), valor));
        Estoque x = node.getData();
        float valorProduto = x.getQtde();
        if (valorProduto < valor) {
            sb.append("Id: " + x.getCodigoProduto() + " - nome: " + x.getNome() + " - quantidade: " + x.getQtde()
                    + " - estoque (R$): " + x.getQtde() * x.getValorUnitario() + "\n");
        }
        sb.append(mostraProdutosMenoresQueHelper(node.getRight(), valor));
        return sb.toString();
    }

    public String preOrderTraversal() {
        return preOrderTraversalHelper(root);
    }

    private String preOrderTraversalHelper(BTNode node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Estoque x = node.getData();
        sb.append("Id: " + x.getCodigoProduto() + " - nome: " + x.getNome() + " - quantidade: " + x.getQtde()
                + " - valor unitário: " + x.getValorUnitario() + "\n");
        sb.append(preOrderTraversalHelper(node.getLeft()));
        sb.append(preOrderTraversalHelper(node.getRight()));

        return sb.toString();
    }

    public String postOrderTraversal() {
        return postOrderTraversalHelper(root);
    }

    private String postOrderTraversalHelper(BTNode node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(postOrderTraversalHelper(node.getLeft()));
        sb.append(postOrderTraversalHelper(node.getRight()));
        Estoque x = node.getData();
        sb.append("Id: " + x.getCodigoProduto() + " - nome: " + x.getNome() + " - quantidade: " + x.getQtde()
                + " - valor unitário: " + x.getValorUnitario() + "\n");

        return sb.toString();
    }

    public String levelOrderTraversal() {
        return levelOrderTraversalHelper(root);
    }

    private String levelOrderTraversalHelper(BTNode node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            BTNode visited = queue.remove();
            Estoque x = visited.getData();
            sb.append("Id: " + x.getCodigoProduto() + " - nome: " + x.getNome() + " - quantidade: " + x.getQtde()
                    + " - valor unitário: " + x.getValorUnitario() + "\n");

            if (visited.hasLeftChild()) {
                queue.add(visited.getLeft());
            }
            if (visited.hasRightChild()) {
                queue.add(visited.getRight());
            }
        }

        return sb.toString();
    }

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(BTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRec(node.getLeft()) + countNodesRec(node.getRight());
    }

    @Override
    public String toString() {
        return "BinaryTree - isEmpty(): " + isEmpty()
                + ", getDegree(): " + getDegree()
                + ", getHeight(): " + getHeight()
                + ", root => { " + root + " }";
    }
}