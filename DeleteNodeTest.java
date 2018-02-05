public class DeleteNodeTest{
    public static void main(String[]args){
	/*case 1a
	Tree tree = new Tree();
	tree.insertNode(new TNode("127", null, null, null));
	
	tree.deleteNode(tree.findNode("127"));     
	tree.printTree(0);
	
	case 1b 
	Tree tree = new Tree();
	tree.insertNode(new TNode("90", null, null, null));
	tree.insertNode(new TNode("050", null, null, null));   
	tree.insertNode(new TNode("150", null, null, null));
	tree.insertNode(new TNode("20", null, null, null));
	tree.insertNode(new TNode("140", null, null, null));

	tree.printTree(0);
	tree.deleteNode(tree.findNode("150"));
	
	2a
	Tree tree = new Tree();
	tree.insertNode(new TNode("090", null, null, null));
	tree.insertNode(new TNode("050", null, null, null)); 
	
	tree.deleteNode(tree.findNode("090"));
	tree.printTree(0);
	
	2b
	Tree tree = new Tree();
	tree.insertNode(new TNode("150", null, null, null));
	tree.insertNode(new TNode("100", null, null, null)); 
	tree.insertNode(new TNode("170", null, null, null)); 
	tree.insertNode(new TNode("40", null, null, null)); 
	
	tree.deleteNode(tree.findNode("100"));
	tree.printTree(0);
	
	3a
	Tree tree = new Tree();
	tree.insertNode(new TNode("150", null, null, null));
	tree.insertNode(new TNode("190", null, null, null)); 
	
	tree.deleteNode(tree.findNode("150"));
	tree.printTree(0);
	
	3b
	Tree tree = new Tree();
	tree.insertNode(new TNode("150", null, null, null));
	tree.insertNode(new TNode("100", null, null, null)); 
	tree.insertNode(new TNode("170", null, null, null)); 
	tree.insertNode(new TNode("190", null, null, null)); 
	
	tree.deleteNode(tree.findNode("170"));
	tree.printTree(0);
	*/
	
	Tree tree = new Tree();
	tree.insertNode(new TNode("55", null, null, null));
	tree.insertNode(new TNode("40", null, null, null));
	tree.insertNode(new TNode("30", null, null, null));
	tree.insertNode(new TNode("50", null, null, null));  
	tree.insertNode(new TNode("20", null, null, null));
	tree.insertNode(new TNode("35", null, null, null));
	tree.insertNode(new TNode("32", null, null, null));  
	tree.insertNode(new TNode("31", null, null, null));
	tree.insertNode(new TNode("33", null, null, null));

	tree.printTree(0);
	System.out.println("\n");
	tree.deleteNode(tree.findNode("40"));
	tree.printTree(0);
    }
}
