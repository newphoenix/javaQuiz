MYSQL & JAVA QUESTIONS
======================

**Question 1:**

Write a query to rank order the following table in MySQL by votes, display the rank as one of the columns.
CREATE TABLE votes ( name CHAR(10), votes INT ); INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);


**Question 2:**

Write a function to capitalize the first letter of a word in a given string; Example: initcap(UNITED states Of AmERIca ) = United States Of America

**Question 3:**

Write a procedure in MySQL to split a column into rows using a delimiter.
CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag'); For (2), example rows would look like >> “3, white”, “3, Snow” ...


**Question 4:**

I have a table for bugs from a bug tracking software; let’s call the table “bugs”. The table has four columns (id, open_date, close_date, severity). 
On any given day a bug is open if the open_date is on or before that day and close_date is after that day. 
For example, a bug is open on “2012-01-01”, if it’s created on or before “2012-01-01” and closed on or after “2012-01-02”. I want a SQL to show number of bugs open for a range of dates.


**Question 5:**

Write an efficient algorithm to check if a string is a palindrome. A string is a palindrome if the string matches the reverse of string.
Example: 1221 is a palindrome but not 1121.


**Question 6:**

Write an efficient algorithm to find K-complementary pairs in a given array of integers. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j];


**Question 7:**

Given a large file that does not fit in memory (say 10GB), find the top 100000 most frequent phrases. The file has 50 phrases per line separated by a pipe (|). Assume that the phrases do not contain pipe.
Example line may look like: Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing .... The above line has 5 phrases in visible region.


**Notes:**
 1.Testing Mysql questions is provided in test/*.sql files, creating tables, data, function, procedures, queries, calling and executing are all included in the files.
 2.Testing a top phrases, required file named lines.txt and produces output.txt file, both files will location is in the path directory. Source code is available for
   changing file path or passing it as agrument. 


MYSQL
=====

**Question 1:**

Write a query to rank order the following table in MySQL by votes, display the rank as one of the columns.
CREATE TABLE votes ( name CHAR(10), votes INT ); INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);

**Answer:**

```sql
SELECT @rank := @rank + 1 as ranking, v.name, v.votes
FROM (SELECT @rank := 0 ) rt,votes v
ORDER BY v.votes DESC;
```

**Question 2:**

Write a function to capitalize the first letter of a word in a given string; Example: initcap(UNITED states Of AmERIca ) = United States Of America

**Answer:**

```sql
DROP FUNCTION IF EXISTS initcap;

CREATE FUNCTION initcap(str VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
    DETERMINISTIC
BEGIN
	DECLARE len INT;
	DECLARE i INT;

	SET len   = CHAR_LENGTH(str);
	SET str = LOWER(str);
	SET i = 0;

	WHILE (i < len) DO
		IF (MID(str,i,1) = ' ' OR i = 0) THEN

			IF (i < len) THEN

				SET str = CONCAT(
					LEFT(str,i),
					UPPER(MID(str,i + 1,1)),
					RIGHT(str,len - i - 1)
				);

			END IF;
		END IF;

		SET i = i + 1;
	END WHILE;

	RETURN str;
END ;
```


**Question 3:**

Write a procedure in MySQL to split a column into rows using a delimiter.
CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag'); For (2), example rows would look like >> “3, white”, “3, Snow” ...

**Answer:**

```sql
DROP PROCEDURE IF EXISTS split_row;

CREATE PROCEDURE split_row(delimiter varchar(1))
BEGIN

    DECLARE id1 INT(11) DEFAULT 0;
    DECLARE name1 varchar(50);
    DECLARE delimiter_number INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE value VARCHAR(50);
    DECLARE done INT(11) DEFAULT FALSE;
    DECLARE cur CURSOR FOR SELECT id, name FROM sometbl WHERE name != '' AND name is not null;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    DROP TEMPORARY TABLE IF EXISTS temp;
    CREATE TEMPORARY TABLE temp(id INT NOT NULL,name VARCHAR(50) NOT NULL) ENGINE=Memory;

    OPEN cur;

      read_loop: LOOP

        FETCH cur INTO id1, name1;

        IF done THEN
          LEAVE read_loop;
        END IF;


        SET delimiter_number = (SELECT LENGTH(name1)- LENGTH(REPLACE(name1, delimiter, ''))+1);
        SET i=1;

        WHILE i <= delimiter_number DO

          SET value =(SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(name1, delimiter, i),

          LENGTH(SUBSTRING_INDEX(name1, delimiter, i - 1)) + 1), delimiter, ''));

          INSERT INTO temp VALUES (id1, value);

          SET i = i + 1;
        END WHILE;

      END LOOP;

      SELECT * FROM temp;

    CLOSE cur;

  END ;
```


**Question 4:**

I have a table for bugs from a bug tracking software; let’s call the table “bugs”. The table has four columns (id, open_date, close_date, severity). 
On any given day a bug is open if the open_date is on or before that day and close_date is after that day. 
For example, a bug is open on “2012-01-01”, if it’s created on or before “2012-01-01” and closed on or after “2012-01-02”. I want a SQL to show number of bugs open for a range of dates.

```sql
CREATE PROCEDURE show_open_bugs(fromDate DATE, toDate DATE)

BEGIN
DECLARE number_of_bugs INT DEFAULT 0;
DROP TEMPORARY TABLE IF EXISTS tmp;
WHILE fromDate <= toDate DO
       SET number_of_bugs = (SELECT COUNT(id) FROM bugs
                             WHERE close_date IS NOT NULL AND DATE(open_date) <= fromDate AND DATE(close_date) > fromDate);                             
       CREATE TEMPORARY TABLE IF NOT EXISTS tmp(datum DATE, open_bugs INT) ENGINE=Memory;
       INSERT INTO tmp VALUES (fromDate, number_of_bugs);
       SET fromDate = ADDDATE(fromDate, INTERVAL 1 DAY);
END WHILE;
    SELECT  DATE_FORMAT(datum,'%Y-%m-%d') as 'date', open_bugs as 'Open bugs'  FROM tmp;
END;
```


NOW JAVA
========

**Question 5:**

Write an efficient algorithm to check if a string is a palindrome. A string is a palindrome if the string matches the reverse of string.
Example: 1221 is a palindrome but not 1121.


**Answer:**

```java
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
}
```


**Question 6:**

Write an efficient algorithm to find K-complementary pairs in a given array of integers. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j];

**Answer:**

```java
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
```


**Question 7:**

Given a large file that does not fit in memory (say 10GB), find the top 100000 most frequent phrases. The file has 50 phrases per line separated by a pipe (|). Assume that the phrases do not contain pipe.
Example line may look like: Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing .... The above line has 5 phrases in visible region.



**Answer:**

```java
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

```