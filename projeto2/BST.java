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

public class BST {

  private Node root;

  public BST() {
    this(null);
  }

  public BST(Node root) {
    this.root = root;
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(Node root) {
    this.root = root;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public int getHeight() {
    if (isEmpty()) {
      return -1;
    }

    return root.getHeight();
  }

  public int countNodes() {
    return countNodesRec(root);
  }

  private int countNodesRec(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + countNodesRec(node.getLeft()) + countNodesRec(node.getRight());
  }

  public Node search(String id) {
    return searchHelper(getRoot(), id);
  }

  private Node searchHelper(Node node, String id) {
    if (node == null) {
      return null;
    }
    int comp = id.compareTo(node.getData().getId());
    if (comp > 0) {
      return searchHelper(node.getRight(), id);
    } else if (comp < 0) {
      return searchHelper(node.getLeft(), id);
    }
    return node;
  }

  public Node findMin() {
    return findMinHelper(getRoot());
  }

  private Node findMinHelper(Node node) {
    if (node == null) {
      return null;
    } else {
      while (node.hasLeftChild()) {
        node = node.getLeft();
      }
      return node;
    }
  }

  public Node findMax() {
    return findMaxHelper(getRoot());
  }

  private Node findMaxHelper(Node node) {
    if (node == null) {
      return null;
    } else {
      while (node.hasRightChild()) {
        node = node.getRight();
      }
      return node;
    }
  }

  public Node findPredecessor(String id) {
    return predecessor(id);
  }

  private Node predecessor(String id) {
    Node node = search(id);
    return node == null ? null : predecessorHelper(node);
  }

  private Node predecessorHelper(Node node) {
    if (node.hasLeftChild()) {
      return findMaxHelper(node.getLeft());
    } else {
      Node current = node;
      Node parent = node.getParent();

      while (parent != null && current == parent.getLeft()) {
        current = parent;
        parent = current.getParent();
      }

      return parent;
    }
  }

  public Node findSuccessor(String id) {
    return successor(id);
  }

  private Node successor(String id) {
    Node node = search(id);
    return node == null ? null : successorHelper(node);
  }

  private Node successorHelper(Node node) {
    if (node.hasRightChild()) {
      return findMinHelper(node.getRight());
    } else {
      Node current = node;
      Node parent = node.getParent();

      while (parent != null && current == parent.getRight()) {
        current = parent;
        parent = current.getParent();
      }

      return parent;
    }
  }

  @Override
  public String toString() {
    return "BST - isEmpty(): " + isEmpty()
        + ", getHeight(): " + getHeight()
        + ", root => { " + getRoot().getData() + " }";
  }
}
