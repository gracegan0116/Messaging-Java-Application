public class Available{
    private int recordNumber = -1;
    private Available next = null;
    
    public Available(){
	recordNumber = -1;
	next = null;
    }
    
    //next in Record is an int this is an object and they are private//
    public Available(int n){
	recordNumber = n;
	next = null;
    }
    public int getRecordNumber(){
	return recordNumber;
    }
    public Available getNext(){
	return next;
    }
    public void setNext(Available q){
	next = q;
    }
    public String toString(){
	return "" + recordNumber;
    }
}
