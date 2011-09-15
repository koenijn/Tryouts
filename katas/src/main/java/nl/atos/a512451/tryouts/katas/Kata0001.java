package nl.atos.a512451.tryouts.katas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Kata0001 {
	
	private static final List<String> dictionary = Arrays.asList(new String[]{"hallo", "mark", "hoi", "xxxx", "pot", "top"});
	
	public static List<List<String>> generateWords(final String letters, final int wordCount) {
		List<String> words = new LinkedList<String>(dictionary);
		return getMatches(words, letters, wordCount);
	}
	
	public static List<List<String>> getMatches(final List<String> words, final String letters, final int wordCount) {
		List<List<String>> matches = new LinkedList<List<String>>();
		List<String> iterWords = new LinkedList<String>(words);
		
		int wordLength = letters.length() / wordCount;
		
		for (String word : iterWords) {
			if (word.length() == wordLength) {
				words.remove(word);
				
				String remainingLetters = letters;
				
				boolean isMatch = true;
				for (int i = 0; i < word.length(); i++){
					String s = word.substring(i, i+1);
					if (remainingLetters.contains(s)) {
						remainingLetters = remainingLetters.replaceFirst(s, "");
					}
					else {
						isMatch = false;
						break;
					}
				}
				
				if (isMatch) {
					
					if (wordCount == 1) {
						List<String> match = new LinkedList<String>();
						match.add(word);
						matches.add(match);
					}
					else {
						List<List<String>> subMatches = getMatches(words, remainingLetters, wordCount-1);
						
						if (subMatches != null && subMatches.size() > 0) {
							for (List<String> subMatch : subMatches) {
								for (String matchedWord : subMatch) {
									words.remove(matchedWord);
								}
								List<String> match = new LinkedList<String>();
								match.add(word);
								match.addAll(subMatch);
								matches.add(match);
							}
						}
					}
				}
			}
		}
		
		return matches.size() != 0 ? matches : null;
	}
	
}
