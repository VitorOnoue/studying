//
// Árvore Binária - Exemplo de implementação em Java
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

// imports para a fila usada na levelOrderTraversalHelper(). 
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

  public float result(){
    if(root == null){
      throw new IllegalStateException("empty tree");
    }
    return root.visitar();
  }

  public boolean isEmpty() {
    return root == null;
  }

  public String postOrderTraversal() {
    return postOrderTraversalHelper(root);
}

  private String postOrderTraversalHelper(BTNode node) {
    if (node == null) {
      return "";
    }

    StringBuilder sb = new StringBuilder();

    if (node instanceof BTNodeDor) {
      BTNodeDor operatorNode = (BTNodeDor) node;
      sb.append(postOrderTraversalHelper(operatorNode.getLeft()));
      sb.append(postOrderTraversalHelper(operatorNode.getRight()));
      sb.append(operatorNode.getValue() + " ");
    } else if (node instanceof BTNodeNdo) {
      BTNodeNdo operandNode = (BTNodeNdo) node;
      sb.append(operandNode.getValue() + " ");
    }

    return sb.toString();
  }
}
