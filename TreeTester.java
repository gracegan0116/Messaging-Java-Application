public class TreeTester{
   /* testing insert and printing node/tree
    public static void main(String[]args){
	Tree tree = new Tree();
	
	TNode node = new TNode("34", null, null, null);
	tree.insertNode(node);
	
	node = new TNode("25", null, null, null);
	tree.insertNode(node);
	
	tree.insertNode(new TNode("46", null, null, null));
	tree.insertNode(new TNode("71", null, null, null));
	
	tree.printTree();
    }*/
    public static void main(String[]args){
	Tree tree = new Tree();
	//order matters//
	tree.insertNode(new TNode("127", null, null, null));
	tree.insertNode(new TNode("100", null, null, null));
	tree.insertNode(new TNode("095", null, null, null));
	tree.insertNode(new TNode("113", null, null, null));
	tree.insertNode(new TNode("260", null, null, null));
	tree.printTree();
	tree.deleteNode(tree.findNode("095"));
	tree.printTree();
	
	/*TNode p = tree.findNode("127");
	tree.deleteNode(p);
	tree.printTree(0);*/
    }
}
