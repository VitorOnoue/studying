public class Node {
    private ProgramaNetFlix data;
    private Node parent;
    private Node left;
    private Node right;
    private int fb;

    public Node() {
    }
    
    public Node(ProgramaNetFlix data, Node parent, Node left, Node right, int fb) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.fb = fb;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public int getDegree() {
        int degree = 0;

        if (hasLeftChild()) {
            ++degree;
        }

        if (hasRightChild()) {
            ++degree;
        }

        return degree;
    }

    public int getHeight() {
        if (isLeaf()) {
            return 0;
        }

        int height = 0;

        if (hasLeftChild()) {
            height = Math.max(height, left.getHeight());
        }

        if (hasRightChild()) {
            height = Math.max(height, right.getHeight());
        }

        return height + 1;
    }

    public ProgramaNetFlix getData() {
        return data;
    }

    public void setData(ProgramaNetFlix data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }
}
