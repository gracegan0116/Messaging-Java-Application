public class Message {
    private char command = Globals.NULL;
    private String sender = Globals.STR_NULL;
    private String receiver = Globals.STR_NULL;
    private String dateTime = Globals.STR_NULL;
    private char marker = Globals.NULL;
    private String subject = Globals.STR_NULL;
    private char eosMarker = Globals.NULL;
    private String text = Globals.STR_NULL;
    
    public Message() {
	command = Globals.NULL;
	sender = Globals.STR_NULL;
	receiver = Globals.STR_NULL;
	dateTime = Globals.STR_NULL;
	marker = Globals.NULL;
	subject = Globals.STR_NULL;
	eosMarker = Globals.NULL;
	text = Globals.STR_NULL;
    }
    
    public void setMessage(String s) {
	command = s.charAt(Globals.COMMAND_POS);
	sender = s.substring(Globals.SENDER_POS, Globals.RECEIVER_POS);
	receiver = s.substring(Globals.RECEIVER_POS, Globals.DATE_TIME_POS);
	dateTime = s.substring(Globals.DATE_TIME_POS, Globals.MARKER_POS);
	marker = s.charAt(Globals.MARKER_POS);
	subject = s.substring(Globals.MARKER_POS + 1,
	s.indexOf(Globals.END_OF_SUBJECT_MARKER));
	eosMarker = s.charAt(s.indexOf(Globals.END_OF_SUBJECT_MARKER));
	text = s.substring(s.indexOf(Globals.END_OF_SUBJECT_MARKER) + 1);
    }
    
    public Message(String s) {
	setMessage(s);
    }
    
    public void readFromMessagesFile(int recordNumber) {
	String data = Globals.STR_NULL;
	Record record = new Record();
	
	do {
	    record.readFromMessagesFile(recordNumber);
	    data = data + record.getData();
	    recordNumber = record.getNext();
	}
	while (recordNumber != Globals.END_OF_MESSAGE);
	setMessage(data);
    }
    
    public void deleteFromMessagesFile(int recordNumber) {
	Record record = new Record();
	
	while (recordNumber != Globals.END_OF_MESSAGE) {
	    record.readFromMessagesFile(recordNumber);
	    record.deleteFromMessagesFile(recordNumber);
	    recordNumber = record.getNext();
	}
    }
    
    public void printFromMessagesFile(int recordNumber) {
	this.readFromMessagesFile(recordNumber);
	System.out.println(this);
    }
    
    public String toString() {
	return "Command: " + command + "\n" + "Sender: " + sender + "\n" +
		"Receiver: " + receiver + "\n" + "Date/Time: " + dateTime + "\n" +
		"Marker: " + marker + "\n" + "Subject: " + subject + "\n" +
		"EOS Marker: " + eosMarker + "\n" + "Message Text: " + text;
    }
    public String getMessage() {
	return command + sender + receiver + dateTime + marker + subject + eosMarker + text;
    }
    public int writeToMessagesFile(){
	String s = getMessage();
	int recordNumber = -1;
	int nextRecordNumber = -1;
	
	int startRecordNumber = Globals.AvailableList.getHead() == null? Globals.totalRecordsInMessageFile : Globals.AvailableList.getHead().getRecordNumber();

	Record record = new Record();
	
	while(s.length()>0){
	    if (Globals.AvailableList.getHead() == null){
		recordNumber = Globals.totalRecordsInMessageFile;
		if(s.length() <= Globals. RECORD_DATA_LEN){
		    record.setData(s, Globals.END_OF_MESSAGE);//sets the record//
		    record.writeToMessagesFile(recordNumber, Globals.APPEND);
		    s = Globals.STR_NULL;
		}
		else{
		    nextRecordNumber = recordNumber + 1;
		    record.setData(s.substring(0,Globals.RECORD_DATA_LEN), nextRecordNumber);
		    record.writeToMessagesFile(recordNumber, Globals.APPEND);
		    s = s.substring(Globals.RECORD_DATA_LEN, s.length());
		}
	    }
	    else{
		recordNumber = Globals.AvailableList.getNextRecord();
		if (s.length() <= Globals.RECORD_DATA_LEN){
		    record.setData(s, Globals.END_OF_MESSAGE);
		    record.writeToMessagesFile(recordNumber, Globals.MODIFY);
		    s = Globals.STR_NULL; //end of process//
		}
		else{
		    if(Globals.AvailableList.getHead() == null){
			nextRecordNumber = Globals.totalRecordsInMessageFile;
		    }
		    else{
			nextRecordNumber = Globals.AvailableList.getHead().getRecordNumber();
		    }
		    record.setData(s.substring(0,Globals.RECORD_DATA_LEN), nextRecordNumber);
		    record.writeToMessagesFile(recordNumber, Globals.MODIFY);
		    s = s.substring(Globals.RECORD_DATA_LEN); 
		}
	    }
	} 
	return startRecordNumber;   
    }
}
