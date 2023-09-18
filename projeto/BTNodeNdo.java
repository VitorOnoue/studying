public class BTNodeNdo extends BTNode{

  public BTNodeNdo() {
		super("", null);
	}

	public BTNodeNdo(String data) {
		super(data, null);
	}
  
  @Override
  public float visitar(){
    return 0f;
  }
}