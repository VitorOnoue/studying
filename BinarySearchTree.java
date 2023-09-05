public class BinarySearchTree {
    private TreeNode root; //current node

    public BinarySearchTree(){
        root = null; //as you create a bst, there will be no nodes yet, so the current node is null
    }

    public boolean search(int value){
        return searchRec(root, value);
    }

    private boolean searchRec(TreeNode root, int value){
        if(root == null){ //if root is null, it means there are no nodes left to search for
            return false;
        }

        if(value == root.getValue()){ //value is found on the bst
            return true;
        }
        else if(value < root.getValue()){ //if the value is smaller than the current node's
            return searchRec(root.getLeft(), value); //go for the left node (left side will have smaller values)
        }
        else{ //if not smaller then greater
            return searchRec(root.getRight(), value); //go for the right node (right side will have greater values)
        }
    }

    public void inOrderTraversal(){
        inOrderTraversalRec(root);
    }

    private void inOrderTraversalRec(TreeNode root){ //print the values ascending
        if(root != null){ //if there is a node
            inOrderTraversalRec(root.getLeft()); /*go for the left node (this will happen until you reach the 
            smallest value; when it does, it will come back exactly in this line as the smallest value wont have any left/right)*/
            System.out.print(root.getValue() + " "); //print nodes value
            inOrderTraversalRec(root.getRight()); //go for the right node
        } //if the root is null, the branch reached its end
    }

    public void insert(int value){
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value){
        if(root == null){ //if theres no node, it means it reached the end of a branch, so create here
            root = new TreeNode(value);
            return root;
        }
        //the node will choose which direction it will take, and then, after reaching an end or the right place, it will come back "setting up its path"
        if(value < root.getValue()){ //if the value is smaller
            root.setLeft(insertRec(root.getLeft(), value)); //go for the left node
        }
        else if(value > root.getValue()){ //if greater
            root.setRight(insertRec(root.getRight(), value)); //go for the right node
        }
        
        return root; //if not smaller nor bigger, its equal
    }

    public void delete(int value){
        root = deleteRec(root, value);
    }

    private TreeNode deleteRec(TreeNode root, int value){
        if(root == null){ //if the tree is empty
            return root;
        }
        //the node will choose which direction it will take, and then, after reaching an end or the right place, it will come back "setting up its path"
        if(value < root.getValue()){ //if the value is smaller
            root.setLeft(deleteRec(root.getLeft(), value));
        }
        else if(value > root.getValue()){ //if greater
            root.setRight(deleteRec(root.getRight(), value));
        }
        else{ //if node found its place
            if(root.getLeft() == null){ //if there is no left node, then the right one will take the place
                return root.getRight();
            }
            else if(root.getRight() == null){ //same for right node
                return root.getLeft();
            }
            //if both left and right exists
            root.setValue(minValue(root.getRight())); //the smallest of the greater ones will take the place to preserve the tree
            root.setRight(deleteRec(root.getRight(), root.getValue())); //now its necessary to remove that value (as it just cloned itself)
        }
        return root;
    }

    private int minValue(TreeNode root){
        int minValue = root.getValue();
        while(root.getLeft() != null){
            minValue = root.getLeft().getValue();
            root = root.getLeft();
        }
        return minValue;
    }

    public void preOrderTraversal(){
        preOrderTraversalRec(root);
    }

    private void preOrderTraversalRec(TreeNode root){
        if(root == null){ //if end of the tree, do nothing
            return;
        }
        System.out.print(root.getValue() + " "); //print node's value
        preOrderTraversalRec(root.getLeft()); //go to left, therefore printing all left nodes
        preOrderTraversalRec(root.getRight()); //go to right, therefore printing all right nodes after the left ones
    }

    public void postOrderTraversal(){
        postOrderTraversalRec(root);
    }

    private void postOrderTraversalRec(TreeNode root){
        if(root == null){ //if end of the tree, do nothing
            return;
        }
        //just like preorder, by calling both left and right, the printing of all left and right nodes is guaranteed
        postOrderTraversalRec(root.getLeft());
        postOrderTraversalRec(root.getRight());
        System.out.print(root.getValue() + " "); //by calling print after both recursions, the printing will start from the bottom of the tree
    }

    public int getHeight(){
        return getHeightRec(root);
    }

    private int getHeightRec(TreeNode root){
        if(root == null){ //if end of the tree
            return 0;
        }
        else{
            int l = getHeightRec(root.getLeft()); //go to left, therefore reaching the left bottom
            int r = getHeightRec(root.getRight()); //go to right, therefore reaching the right bottom
            if (l > r){ //if-else to choose the greater bottom and incrementing 1
                return l + 1;
            }
            else{
                return r + 1;
            }
        }
    }

    public int getDegree(int value){ //degree = how many nodes it has associated
        int count = 0; //node counter
        TreeNode x = searchNode(root, value); //searching for the node that contains the value
        if(root == null){
            return -1; //treat it as a not found
        }
        count += x.getLeft() != null ? 1 : 0; //if that node has a left, +1
        count += x.getRight() != null ? 1 : 0; //if it has a right, +1
        return count;
    }

    private TreeNode searchNode(TreeNode root, int value){
        if(root == null || root.getValue() == value){ //if the tree is empty/
            return root;
        }
        if(value < root.getValue()){
            return searchNode(root.getLeft(), value);
        }
        else{
            return searchNode(root.getRight(), value);
        }
    }
}