public class RetrieveAvailableListTest{
    public static void main(String[]args){
	int error = FileIO.retrieveAvailableList("_available3.txt");
	System.out.println(Globals.AvailableList);
    }
}
