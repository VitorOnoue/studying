public class BTNodeDor extends BTNode {

  public BTNodeDor() {
		super("", null);
	}

  public BTNodeDor(String data) {
		super(data, null);
	}

  @Override
  public float visitar() {
    return 0f;
  }
}