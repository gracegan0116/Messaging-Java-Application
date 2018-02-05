import java.net.*;
import java.io.*;

public class NetIO {
    public static String myIPAddress() {
	String ipAddress = "";
	try {
	    InetAddress myComputer = InetAddress.getLocalHost();
	    ipAddress = myComputer.getHostAddress();
	}
	catch (Exception e) {
	    System.out.println("Error in getting ip address");
	}
	return ipAddress;
    }
    
    public static String myUserName() {
	String userName = "";
	try {
	    userName = System.getProperty("user.name");
	}
	catch (Exception e) {
	    System.out.println("Error in getting user name");
	}
	return userName;
    }
    
    public static int sendRequest(String message, String destinationIPAddress) {
	int errorCode = -1;
	try {
	    Socket me = new Socket(); /*requestiong channel*/
	    me.connect(new InetSocketAddress(destinationIPAddress, 5000), 10000);/*this connect channel to program, 10000 = time out*/
	    me.setSoTimeout(10000);
	    
	    DataOutputStream output = new DataOutputStream(me.getOutputStream());
	    output.writeUTF(message);
	    
	    DataInputStream input = new DataInputStream(me.getInputStream());
	    String confirmation = input.readUTF();
	    
	    if (isANumber(confirmation))
		errorCode = Integer.parseInt(confirmation);
	    
	    me.close();
	}
	catch (IOException e){
	    System.out.println("Error in sending message");
	}
	return errorCode;
    }
    
    public static String receiveRequest(){
	String request = "";
	int errorCode = -1;
	try{
	    ServerSocket server = new ServerSocket(5000,100);
	    
	    Socket me = server.accept();
	    me.setSoTimeout(10000);
	    
	    DataInputStream input = new DataInputStream (me.getInputStream());
	    request = input.readUTF();
	    
	    DataOutputStream output = new DataOutputStream(me.getOutputStream());
	    output.writeUTF("0");
	    
	    me.close();
	    server.close();
	    errorCode = 0;
	}
	catch(IOException e){
	    System.out.println("Error receiving messahe");
	}
	
	return request;
    }
    
    private static boolean isANumber(String s) {
	boolean result = true;
	for (int i = 0; i < s.length() && result == true; i++)
	    result = Character.isDigit(s.charAt(i));
	return result;
    }
	
    public static void main(String[]args){
	System.out.println(myIPAddress());
	System.out.println(myUserName());
    }
}
