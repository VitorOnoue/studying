public class BTNodeNdo extends BTNode{
  
  private float value;
  private BTNode parent;

  public BTNodeNdo(float value, BTNode parent) {
		this.value = value;
    this.parent = parent;
	}


  public BTNode getParent() {
    return parent;
  }

  public void setParent(BTNode parent) {
    this.parent = parent;
  }

  @Override
  public float visitar() {
    return value;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }
 
}