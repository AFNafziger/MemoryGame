package edu.wm.cs.cs301.memorygame;

public class LatinAlphabet implements Alphabet {
    @Override //https://www.geeksforgeeks.org/overriding-in-java/
    public char[] toCharArray() {
        //returning the 'Latin' alphabet
    	return new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N','O','P', 'Q','R','S', 'T','U','V', 'W','X','Y', 'Z','a','b','c','d','e','f'};
    }
}