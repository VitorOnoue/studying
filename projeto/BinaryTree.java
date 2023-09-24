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
        BTNodeDor op = new BTNodeDor(split[i].charAt(0), left, right, null);
        right.setParent(op);
        left.setParent(op);
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
}
