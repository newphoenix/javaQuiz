package com.quiz;

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
