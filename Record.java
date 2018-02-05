import java.io.*;
public class Record {
    private byte[] data = new byte[Globals.RECORD_DATA_LEN];
    private int next = Globals.END_OF_MESSAGE;
    
    public Record() {
	for (int i = 0; i < data.length; i++) {
	    data[i] = (byte) Globals.BLANK;
	}
	next = Globals.END_OF_MESSAGE;
    }
    
    public Record(String s, int nextRecord) {
	setData(s, nextRecord);
    }
    
    public String getData(){
	String dataS = "";
	for (int i = 0; i < data.length; i++) {
	    dataS = dataS + (char) data[i];
	}
	 return dataS;
    }
    
    public void setData(String txt, int nextRecord) {
	for (int i = 0; i < txt.length(); i++) {
	    data[i] = (byte) txt.charAt(i);
	}
	    for (int j = txt.length(); j < data.length; j++) {
		    data[j] = (byte) Globals.BLANK;
	    }
	    next = nextRecord;
    }
    
    public int getNext() {
	return next;
    }
    
    public int readFromMessagesFile(int recordNumber) {
	int process = -1;
	try {
	    Globals.msg.seek(Globals.RECORD_LEN * recordNumber);
	    
	    int bytes = Globals.msg.read(data);
	    
	    next = Globals.msg.readInt();
	    process = Globals.PROCESS_OK;
	}
	catch (IOException e) {
	    process = Globals.PROCESS_ERROR;
	} 
	return process;
    
    }
    
    public int writeToMessagesFile(int recordNumber, int mode)  {
	int process = -1;
	try {
	    Globals.msg.seek(Globals.RECORD_LEN * recordNumber);
	    
	    Globals.msg.write(data);  
	    Globals.msg.writeInt(next);
	    
	    if (mode == Globals.APPEND) {
		Globals.totalRecordsInMessageFile++;
	    }
	    process = Globals.PROCESS_OK;
	}
	catch (IOException e) {
	    process = Globals.PROCESS_ERROR;
	} 
	return process;
    }
    
    public void deleteFromMessagesFile(int recordNumber) {
	int error = readFromMessagesFile(recordNumber);
	
	if (error == Globals.PROCESS_OK) {
	    data[0] = (byte) Globals.DELETED;
	    error = writeToMessagesFile(recordNumber, Globals.MODIFY);
	    if (error == Globals.PROCESS_OK) {
		Globals.AvailableList.addRecord(recordNumber);
	    }
	    else {
		System.out.println("Error writing record in deleteFromMessages()");
	    }
	}
	else {
	    System.out.println("Error reading record in deleteFromMessages()");
	}
    }
    public String toString() {
	return getData() + next;
    }
    
    public void dumpData() {
	for (int i = 0; i < data.length; i++) {
	    if (data[i] == (byte) Globals.BLANK) 
		System.out.println(i + " $");
	    else 
		System.out.println(i +  " " + data[i]);
	}
    }
}
