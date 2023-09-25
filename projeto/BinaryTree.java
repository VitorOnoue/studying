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

import java.util.Stack;


public class BinaryTree {

  private BTNode root;

  public BinaryTree(BTNode root) {
    this.root = root;
  }

  public BinaryTree(String rpn) {
    String split[] = rpn.split("\\s+");
    Stack<BTNode> s = new Stack<BTNode>();
    for(int i = 0; i < split.length; i++){
      if(isOp(split[i])){
        BTNode right = s.pop();
        BTNode left = s.pop();
        BTNodeDor op = new BTNodeDor(split[i].charAt(0), left, right);
        s.push(op);
      }
      else{
        BTNodeNdo n = new BTNodeNdo(Float.parseFloat(split[i]), null);
        s.push(n);
      }
    }
    root = s.pop();
  }

  public boolean isOp(String x){
    if(x.equals("+") || x.equals("-") || x.equals("/") || x.equals("*")){
      return true;
    }
    return false;
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

  public String inOrderTraversal() {
    return inOrderTraversalHelper(root);
  }

  private String inOrderTraversalHelper(BTNode node) {
    if(node == null){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    if(node instanceof BTNodeDor){
      BTNodeDor op = (BTNodeDor) node;
      sb.append(inOrderTraversalHelper(op.getLeft()));
      sb.append(op.getValue() + " ");
      sb.append(inOrderTraversalHelper(op.getRight()));
    }
    else if(node instanceof BTNodeNdo){
      BTNodeNdo n = (BTNodeNdo) node;
      sb.append(inOrderTraversalHelper(n.getLeft()));
      sb.append(n.getValue() + " ");
      sb.append(inOrderTraversalHelper(n.getRight()));
    }
    return sb.toString();
  }

	public String preOrderTraversal() {
		return preOrderTraversalHelper(root);
	}

	private String preOrderTraversalHelper(BTNode node) {
		if (node == null){
			return "";
		}

		StringBuilder sb = new StringBuilder();
		if(node instanceof BTNodeDor){
      BTNodeDor op = (BTNodeDor) node;
      sb.append(op.getValue() + " ");
		  sb.append(preOrderTraversalHelper(op.getLeft()));
		  sb.append(preOrderTraversalHelper(op.getRight()));
    }
    else if(node instanceof BTNodeNdo){
      BTNodeNdo n = (BTNodeNdo) node;
      sb.append(n.getValue() + " ");
		  sb.append(preOrderTraversalHelper(n.getLeft()));
		  sb.append(preOrderTraversalHelper(n.getRight()));
    }
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
    if(node instanceof BTNodeDor){
      BTNodeDor op = (BTNodeDor) node;
      sb.append(postOrderTraversalHelper(op.getLeft()));
		  sb.append(postOrderTraversalHelper(op.getRight()));
		  sb.append(op.getValue() + " ");
    }
    else if(node instanceof BTNodeNdo){
      BTNodeNdo n = (BTNodeNdo) node;
      sb.append(postOrderTraversalHelper(n.getLeft()));
		  sb.append(postOrderTraversalHelper(n.getRight()));
		  sb.append(n.getValue() + " ");
    }
		return sb.toString();
	}
}