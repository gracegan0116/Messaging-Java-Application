import hsa.*;
public class EmailServer{
    public static void main(String[]args){
	//FileIO.openMessagesFile("_messages.txt");
	//FileIO.closeMessagesFile();
	//System.out.println(Globals.totalRecordsInMessageFile);
	Tree senderIndex = new Tree(); //maintain information about the position of the record in the file according to the sender first//
	Message message = new Message();
	String identification = Globals.STR_NULL;
	int recordNumber = -1;
	TNode p = null;
	
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if (error == Globals.PROCESS_OK){
	    error = FileIO.retrieveAvailableList(Globals.AVAILABLE_LIST_FILE);
	    
	    if(error == Globals.PROCESS_OK){
		int command = -1;
		do{ 
		    System.out.println("Available List: ");
		    System.out.println(Globals.AvailableList);
		    
		    System.out.println("Server options: ");
		    System.out.println("1. Add message");
		    System.out.println("2. Delete message");
		    System.out.println("3. Print all messages");
		    System.out.println("4. Find message (sender Id + receiver Id + dateTime)");
		    System.out.println("5. Find message (receiver Id + sender Id + dateTime)");
		    System.out.println("6. Find messages from partial identification");
		    System.out.println("7. Rebuild available list");
		    System.out.println("8. Rebuild trees");
		    System.out.println("9. Breadth-first print of trees");
		    System.out.println("99. Quit");
		    System.out.println();
		    System.out.print("Option -> ");
		    command = Stdin.readInt();
		    
		    switch(command){
			case 1: 
			    //command//
			    System.out.print("Sender id: ");
			    String senderId = Stdin.readString();
			    
			    System.out.print("Receiver id: ");
			    String receiverId = Stdin.readString();
			    
			    System.out.print("Date (8 characters): ");
			    String dateTime = Stdin.readString();
			    //marker//
			    System.out.print("Subject: ");
			    String subject = Stdin.readLine();
			    //end of subject marker//
			    System.out.print("Message: ");
			    String text = Stdin.readLine(); //message//
			    
			    String entireMessage = Globals.SEND_MESSAGE + senderId + 
						    receiverId + dateTime + 
						    Globals.FIRST_RECORD_OF_MESSAGE + 
						    subject + Globals.END_OF_SUBJECT_MARKER + 
						    text;
						    
			    message.setMessage(entireMessage); //breaking up the strings into fields into the message object//
			    recordNumber = message.writeToMessagesFile();//tree contain record# where it begins --> pickup message//
			    
			    identification = senderId + receiverId + dateTime;
			    
			    p = new TNode(identification, recordNumber, null, null, null);
			    senderIndex.insertNode(p);
			    break;   
			    
			case 4:
			    System.out.print("Sender message identification (senderId + receiverId + dateTime): ");
			    identification = Stdin.readString();
			    
			    p = senderIndex.findNode(identification);
			    if (p != null){
				recordNumber = p.getRecordNumber();
				System.out.println("At record number: " + recordNumber);
				message.printFromMessagesFile(recordNumber);
			    }
			    else{
				System.out.println("identification not found");
			    }
		    }
			
		}while(command != 99);
		
		error = FileIO.saveAvailableList(Globals.AVAILABLE_LIST_FILE);
	    }
	    else{
		System.out.println("Error: Unable to open available list file");
	    }
	    
	    FileIO.closeMessagesFile();
	}
    }
}
