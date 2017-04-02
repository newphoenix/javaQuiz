

public class TESTCLASS {
	
	
	public static boolean istPalindrom(char[] charArray){
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

	public static void main(String[] args) {
		String c = "hannah";
		
		long t1 = System.nanoTime();
		boolean result = istPalindrom(c.toCharArray());
		System.out.println((System.nanoTime() - t1));
		System.out.println(result);
		
		t1 = System.nanoTime();
		result = c.equals(new StringBuilder(c).reverse().toString());
		System.out.println((System.nanoTime() - t1));
		System.out.println(result);
	}

}



//https://gullele.com/find-the-pairs-that-makes-k-complementary-in-the-given-array-java-solution/

//package algorithm;
//
//import java.util.HashMap;
////import java.util.List;
//import java.util.Map;
//
///**
// * Algorithm to find the pairs making the K complementary in O(n) complexity
// * 
// * @author http://gullele.com
// * @see KComplementaryTest.java - a JUnit test for this class
// */
//public class KComplementary {
//    
//    private Map pairs;
//    
//    public static void main(String[] args) {
//        KComplementary kcomp = new KComplementary();
//        int[] numbers = new int[]{7, 1, 5, 6, 9, 3, 11, -1};
//        Integer[][] pairs = kcomp.getKComplementaryPairs(10, numbers);
//        
//        for (Integer[] thePairs : pairs) {
//            System.out.println(" Pairs are "+thePairs[0] + " and " + thePairs[1]);
//        }
//    }
//    
//    public KComplementary() {
//        this.pairs = new HashMap();
//    }
//    
//    /**
//     * An algorithm to find the pair from the given array that would sum up the given K
//     * 
//     * @note - the algorithm would be done in o(n). First it will run through the whole 
//     * numbers and creates a temporary list of pairs in HashMap with 
//     * (value, sum-value). 
//     * @param sum
//     * @param listOfIntegers
//     * @return
//     */
//    public Integer[][] getKComplementaryPairs(int sum, int[] listOfIntegers) {
//        
//        /*
//         * I could have used the ArrayList assuming the number of pairs we are getting 
//         * is not known, but giving it a little bit of thought, the number of pairs 
//         * is known in advance, at least the maximum we can get is known.
//         * By not using the Array list, the algorithm would run O(n) rather than
//         * O(n**2) since ArrayList.add would would be O(n) by itself 
//         */
//        //List complementaryPairs = new ArrayList();
//        Integer[][] complementaryPairs = new Integer[listOfIntegers.length][2];
//        //First fill up the pairs with the complementary numbers
//        for (int number : listOfIntegers) { //O(n) complexity
//            this.pairs.put(number, sum-number);
//        }
//        
//        //then filter out the pairs that don't have corresponding complementary number
//        int index = 0;
//        for (int number : listOfIntegers) { //O(n) complexity 
//            int complementary = sum - number;
//            //check if this key exists in the pairs
//            if ( this.pairs.containsKey(complementary) ) {
//                //Had I used array List this would have been used
//                //Integer[] comps = {number, complementary};
//                //complementaryPairs.add(comps); //this is O(n)
//                complementaryPairs[index][0] = number;
//                complementaryPairs[index][1] = complementary;
//                index ++;
//            }
//        }
//        //System.out.println(index);
//        //Now trim the array since we know the exact record
//        Integer[][] trimmed = new Integer[index][2];
//        
//        index = 0;
//        for (Integer[] item : complementaryPairs) { //O(n) complexity
//            if (item[0] != null) {
//                trimmed[index][0] = item[0];
//                trimmed[index][1] = item[1];
//            }
//            index++;
//        }
//    
//        //Total complexity O(n)+O(n)+O(n) ==> O(n)
//        
//        //had I used arrayList this would have been used
//        //return complementaryPairs.toArray(new Integer[0][0]);
//        return trimmed;
//    }
//}

//---------------------------------------------------------------------------
//---------------------------------------------------------------------------
//---------------------------------------------------------------------------
//---------------------------------------------------------------------------

//public static int foo_opt( int k, int[ ] A )
//{
//  Map<Long, Integer> C = new HashMap<Long, Integer>( );
//  for ( int i = 0; i < A.length; i++ )
//  {
//    final long complValue = ( ( long ) k ) - A[ i ];
//    final int tempValue = C.containsKey( complValue ) ? C.get( complValue ) : 0;
//    C.put( complValue, tempValue + 1 );
//  }
// 
//  int counter = 0;
//  for ( int i = 0; i < A.length; i++ )
//  {
//    final long value = A[ i ];
//    counter += C.containsKey( value ) ? C.get( value ) : 0;
//  }
// 
//  return counter;
//}



// http://www.baeldung.com/java-read-lines-large-file
// https://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
// http://stackoverflow.com/questions/14037404/java-read-large-text-file-with-70million-line-of-text