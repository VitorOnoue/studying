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

  public void insert(Node node) {
    setRoot(insertHelper(getRoot(), node.getData()));
  }

  private Node insertHelper(Node node, ProgramaNetFlix data) {
    if (node == null) {
      return new Node(data, null, null, null, 0);
    }

    int comp = data.getId().compareTo(node.getData().getId());

    if (comp < 0) {
      node.setLeft(insertHelper(node.getLeft(), data));
    } else if (comp > 0) {
      node.setRight(insertHelper(node.getRight(), data));
    } else {

    }
    return node;
  }

  public Node search(String id) {
    return searchHelper(getRoot(), id);
  }

  private Node searchHelper(Node node, String id) {
    if (node == null) {
      return null;
    }

    int comp = id.compareTo(node.getData().getId());
    if (comp < 0) {
      return searchHelper(node.getLeft(), id);
    } else if (comp > 0) {
      return searchHelper(node.getRight(), id);
    } else {
      return node;
    }
  }

  public boolean remove(String id) {
    setRoot(removeHelper(getRoot(), id));
    return true;
  }

  private Node removeHelper(Node node, String id) {
    if (node == null) {
      return null;
    }

    int comp = id.compareTo(node.getData().getId());

    if (comp < 0) {
      node.setLeft(removeHelper(node.getLeft(), id));
    } else if (comp > 0) {
      node.setRight(removeHelper(node.getRight(), id));
    } else {
      node = removeNode(node);
    }
    return node;
  }

  private Node removeNode(Node node) {
    if (node.isLeaf()) {
      return null;
    }

    if (!node.hasLeftChild()) {
      return node.getRight();
    } else if (!node.hasRightChild()) {
      return node.getLeft();
    } else {
      Node predecessor = predecessor(node.getData().getId());
      node.setData(predecessor.getData());
      node.setLeft(removeHelper(node.getLeft(), predecessor.getData().getId()));
    }

    return node;
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

  public class Search {
    private String id;
    private String titulo;
    private int count;

    public Search(String id, String titulo, int count) {
      this.id = id;
      this.titulo = titulo;
      this.count = count;
    }

    public String getId() {
      return id;
    }

    public String getTitulo() {
      return titulo;
    }

    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }
  }
  
  public Search searchPrograma(String id) {
    Search s = new Search(id, "", 0);
    Node node = searchProgramaHelper(getRoot(), s);
    if (node != null) {
      s.setTitulo(node.getData().getTitulo());
    }
    return s;
  }

  private Node searchProgramaHelper(Node node, Search s) {
    s.setCount(s.getCount() + 1);
    if (node == null) {
      return null;
    }
    int comp = s.getId().compareTo(node.getData().getId());
    if (comp > 0) {
      return searchProgramaHelper(node.getRight(), s);
    } else if (comp < 0) {
      return searchProgramaHelper(node.getLeft(), s);
    }
    return node;
  }

  public void inordertraversal(Node root) {
    if (root != null) {
      inordertraversal(root.getLeft());
      System.out.print(root.getData().getId() + " ");
      inordertraversal(root.getRight());
    }
  }

  @Override
  public String toString() {
    return "BST - isEmpty(): " + isEmpty()
        + ", getHeight(): " + getHeight()
        + ", root => { " + getRoot().getData() + " }";
  }
}
