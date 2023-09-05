class Main{
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        for(int i = 1; i < 8; i++){
            if(i == 4){
                continue;
            }
            bst.insert(i);
        }
        System.out.println(bst.getHeight());
        bst.preOrderTraversal();
        System.out.println();
        bst.postOrderTraversal();
    }
}