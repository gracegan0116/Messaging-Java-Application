public class Utils {
    public static boolean isANumber(String s){
	boolean itIsANumber = true;
	//48-57 ascii value for numbers//
	for (int i = 0; i < s.length() && itIsANumber; i++){
	    itIsANumber = 48 <= s.charAt(i) && s.charAt(i) <= 57;
	}
	return itIsANumber;
    }
}
