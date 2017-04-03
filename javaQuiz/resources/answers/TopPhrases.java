

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TopPhrases {

	private static final int PHRASES_NUMBER = 100000;
	private static final String V_BAR = "\\|";
	private static final int ONE = 1;
	
	private static final String COMMA = ",";
	private static final String DONE = "done";
	
	private static final String inputFileName = "lines.txt";
	private static final String outFileName = "output.txt";

	public static Map<String, Integer> top100k(String inputFileName) throws IOException {
		
		// if one phrase repeats more than 2.1 Billion times then replace Integer with Long
		Map<String, Integer> phrases = new LinkedHashMap<>();
						
		 try(InputStream inputStream = new FileInputStream(inputFileName);
		            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
		 
			 		// read line by line, parsing, adding to hash map and counting
		            String line;		            
		            while ((line = bufferedReader.readLine()) != null){

		                  String[] linePhrases = line.split(V_BAR);			              
			       
			              for (String phrase : linePhrases){
			         
			                if (phrases.containsKey(phrase)) {
			                    phrases.put(phrase, phrases.get(phrase)+ ONE);
			                } else {
			                    phrases.put(phrase, ONE);
			                }
			            }
		            }
		     		        }
		        catch (IOException ex){
		            throw ex;
		        }
		
			
		  //Sort the collection and get the 100000 phrases.
          phrases = phrases.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(PHRASES_NUMBER)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key,value)->key, LinkedHashMap::new));

    	return  phrases;	
	}

	
	public static void main(String[] args) throws Exception {

		Map<String, Integer> phrases = null;
		try {
			 // get phrases in hash map
			 phrases = TopPhrases.top100k(inputFileName);
		} catch (Exception ex) {
			throw ex;
		}
		
    	// write result to file
		try (Writer writer = Files.newBufferedWriter(Paths.get(outFileName))) {
			
			for (String key : phrases.keySet()) {
				writer.write((new StringBuilder(key)).append(COMMA).append(phrases.get(key))
					  .append(System.lineSeparator()).toString());
			}
		}
		
		System.out.println(DONE);
	}
}
