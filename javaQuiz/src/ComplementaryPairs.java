import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplementaryPairs {

	private static final String OPEN_PARENTHESIS = "(";
	private static final String CLOSE_PARENTHESIS = ")";
	private static final String COMMA = ",";
	
	 private static List<Integer> processArray(int k, int[] array) {
	     
		   List<Integer> result = new ArrayList<>();
		   
	        if (array == null || array.length < 2) {
	            return result;
	        }
	        
	        //sort the array 
	        Arrays.sort(array);
	                
	        int left = 0;
	        int right = array.length-1;
	        
	        while (left < right) {
	            int sum = array[left] + array[right];
	            	            
	            if (sum == k) {	               
	                result.add(array[left]);
	                left++;
	                right--;	              
	            } else if (sum > k) {
	            	// right side contributed much more than the left, move the right
		            // side to the left to make the sum smaller
	                right--;
	            } else {
	            	 // left side didn't contribute enough to get to the k, move the left
		             // side to the right to make the sum bigger 
	                left++;
	            }
	        }
	        
	        return result;
	    }
	    

	 
	 
	public static void main(String[] args) {
			
		int[] array = new int[] { 3, 4, -1, 8, 2, 5, 6, 9,7};
        int k = 11;
		
		List<Integer> result = processArray(k, array);

		if (!result.isEmpty()) {
			for (Integer number : result) {
				System.out.println(
						new StringBuilder(OPEN_PARENTHESIS).append(number).append(COMMA).append(k - number).append(CLOSE_PARENTHESIS).toString());
			}
		} else {
			System.out.println("no result found");
		}
	}
}
