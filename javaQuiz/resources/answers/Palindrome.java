import java.util.Arrays;
import java.util.List;

public class Palindrome {
	
	public static boolean isPalindrom(char[] charArray){
	    int i = 0;
	    int j = charArray.length - 1;
	    while (j > i) {
	        if (charArray[i] != charArray[j]) {
	            return false;
	        }
	        ++i;
	        --j;
	    }
	    return true;
	}
	
	
	// the first 5 strings are Palindrome, the last 2 are not.
	private static List<String> WORD_LIST = Arrays.asList("noon","level","Refer","Anna","121121","lambda","water");
	
	public static void main(String[] args){
		
		for(String word : WORD_LIST){
		boolean result = isPalindrom(word.toLowerCase().toCharArray());
		System.out.println(result);
	}

}

}
