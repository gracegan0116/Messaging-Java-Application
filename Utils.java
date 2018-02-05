public class Utils{
    public static String generateRandomCode(int len){
	String code = "";
	for (int i = 0; i < len; i++){
	    code = code + (int)(Math.random()*10);
	}
	return code;
    }
    public static boolean isANumber(String s){
	boolean output = true;
	for (int i = 0; i < s.length(); i++){
	    int d = s.charAt(i)-48;
	    if (d<=9 && d>=0){
		output = true;
	    }
	    else{
		return false;
	    }
	}
	return output;
    }
    public static String leftPad(String text, int desiredLength, char paddingItem){
	 int length = text.length();
	 if (length < desiredLength){
	    for (int i = 0; i < desiredLength - length; i++){
		text = paddingItem + text;
	    }
	 }
	 else if (length == 0){
	    for (int i = 0; i < desiredLength; i++){
		text = paddingItem + text;
	    }
	 }
	 return text;  
    }
    public static String removeChars(String text, char c){
	String remove = Character.toString(c);
	String output = text.replaceAll(remove,"");
	return output;
    }
    public static void delay(int ms){
	try{
	    Thread.sleep(ms);
	}
	catch(InterruptedException ex){
	    Thread.currentThread().interrupt();
	    System.out.println("Process error in delay method.");
	}
    }
}
