public class BTNodeDor extends BTNode {
  private char value;
  private BTNode left;
  private BTNode right;
  private BTNode parent;

  public BTNodeDor(char value, BTNode left, BTNode right, BTNode parent){
    this.value = value;
    this.left = left;
    this.right = right;
    this.parent = parent;
  }
  
  public BTNode getParent() {
    return parent;
  }

  public void setParent(BTNode parent) {
    this.parent = parent;
  }

  public BTNode getLeft() {
    return left;
  }

  public void setLeft(BTNode left) {
    this.left = left;
  }

  public BTNode getRight() {
    return right;
  }

  public void setRight(BTNode right) {
    this.right = right;
  }

  public char getValue() {
    return value;
  }

  public void setValue(char value) {
    this.value = value;
  } 

  @Override
  public float visitar(){
    switch(value){
      case '+':
        return left.visitar() + right.visitar();
      case '-':
        return left.visitar() - right.visitar();
      case '/':
        if(right.visitar() != 0){
          return left.visitar() / right.visitar();
        }
        else{
          throw new ArithmeticException("Divisão por zero");
        }
      case '*':
        return left.visitar() * right.visitar();
      default:
        return 0f;
    }
  }
}