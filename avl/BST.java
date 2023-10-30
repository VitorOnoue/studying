//
// Árvore de Busca Binária - Exemplo de implementação em Java
// Copyright (C) 2023 André Kishimoto
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

public class BST extends BinaryTree {

    public BST() {
        super();
    }

    public BST(BTNode root) {
        super(root);
    }

    public BTNode search(int id) {
        return searchHelper(super.getRoot(), id);
    }

    private BTNode searchHelper(BTNode node, int id) {
        if (node == null) {
            return null;
        }

        int diff = diffCompare(id, node.getData().getCodigoProduto());
        if (diff < 0) {
            return searchHelper(node.getLeft(), id);
        } else if (diff > 0) {
            return searchHelper(node.getRight(), id);
        } else {
            return node;
        }
    }

    public void insert(Estoque data) {
        super.setRoot(insertHelper(super.getRoot(), null, data));
    }

    private BTNode insertHelper(BTNode node, BTNode parent, Estoque data) {
        if (node == null) {
            return new BTNode(data, parent);
        }

        int diff = diffCompare(data.getCodigoProduto(), node.getData().getCodigoProduto());

        if (diff < 0) {
            node.setLeft(insertHelper(node.getLeft(), node, data));
        } else if (diff > 0) {
            node.setRight(insertHelper(node.getRight(), node, data));
        } else {
            // Nessa implementação, não é permitida a inserção de duplicatas na BST.
        }

        return node;
    }

    public void remove(int id) {
        super.setRoot(removeHelper(super.getRoot(), id));
    }

    private BTNode removeHelper(BTNode node, int id) {
        if (node == null) {
            return null;
        }

        int diff = diffCompare(id, node.getData().getCodigoProduto());

        if (diff < 0) {
            node.setLeft(removeHelper(node.getLeft(), id));
        } else if (diff > 0) {
            node.setRight(removeHelper(node.getRight(), id));
        } else {
            node = removeNode(node);
        }

        return node;
    }

    private BTNode removeNode(BTNode node) {
        if (node.isLeaf()) {
            return null;
        }

        if (!node.hasLeftChild()) {
            return node.getRight();
        } else if (!node.hasRightChild()) {
            return node.getLeft();
        } else {
            BTNode predecessor = predecessor(node.getData().getCodigoProduto());
            node.setData(predecessor.getData());
            node.setLeft(removeHelper(node.getLeft(), predecessor.getData().getCodigoProduto()));
        }

        return node;
    }

    public BTNode findMin() {
        return findMinHelper(super.getRoot());
    }

    private BTNode findMinHelper(BTNode node) {
        if (node == null) {
            return null;
        } else {
            while (node.hasLeftChild()) {
                node = node.getLeft();
            }
            return node;
        }
    }

    public BTNode findMax() {
        return findMaxHelper(super.getRoot());
    }

    private BTNode findMaxHelper(BTNode node) {
        if (node == null) {
            return null;
        } else {
            while (node.hasRightChild()) {
                node = node.getRight();
            }
            return node;
        }
    }

    public BTNode findPredecessor(int id) {
        return predecessor(id);
    }

    private BTNode predecessor(int id) {
        BTNode node = search(id);
        return node == null ? null : predecessorHelper(node);
    }

    private BTNode predecessorHelper(BTNode node) {
        if (node.hasLeftChild()) {
            return findMaxHelper(node.getLeft());
        } else {
            BTNode current = node;
            BTNode parent = node.getParent();

            while (parent != null && current == parent.getLeft()) {
                current = parent;
                parent = current.getParent();
            }

            return parent;
        }
    }

    public BTNode findSuccessor(int id) {
        return successor(id);
    }

    private BTNode successor(int id) {
        BTNode node = search(id);
        return node == null ? null : successorHelper(node);
    }

    private BTNode successorHelper(BTNode node) {
        if (node.hasRightChild()) {
            return findMinHelper(node.getRight());
        } else {
            BTNode current = node;
            BTNode parent = node.getParent();

            while (parent != null && current == parent.getRight()) {
                current = parent;
                parent = current.getParent();
            }

            return parent;
        }
    }

    private int diffCompare(int s1, int s2) {
        if (s1 > s2) {
            return 1;
        } else if (s1 < s2) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "BST - isEmpty(): " + isEmpty()
                + ", getDegree(): " + getDegree()
                + ", getHeight(): " + getHeight()
                + ", root => { " + super.getRoot().getData() + " }";
    }

}
