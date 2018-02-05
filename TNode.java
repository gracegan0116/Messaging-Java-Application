public class TNode{
    private String id = "";
    private TNode left = null;
    private TNode right = null;
    private TNode parent = null;
    private int recordNumber = -1;
    //constructor//
    public TNode(){
	id = "";
	left = null;
	right = null;
	parent = null;
	recordNumber = -1;
    }
    public TNode(String i, int re, TNode l, TNode r, TNode p){
	id = i;
	recordNumber = re;
	left = l;
	right = r;
	parent = p;
    }
    //access methods//
    public String getId(){
	return id;
    }
    public TNode getLeft(){
	return left;
    }
    public TNode getRight(){
	return right;
    }
    public TNode getParent(){
	return parent;
    }
    public int getRecordNumber(){
	return recordNumber;
    }
    public void setId(String i){
	id = i;
    }
    public void setLeft(TNode l){
	left = l;
    }
    public void setRight(TNode r){
	right = r;
    }
    public void setParent(TNode p){
	parent = p;
    }
    public void setRecordNumber(int re){
	recordNumber = re;
    }
    public String toString(){
	if(this == null){
	    return "null";
	}
	else{
	    return "Id: " + id;
	}
    }
}
