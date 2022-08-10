package logic;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounter {
	
	HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
	
	// Over engineering the " " space delimiter - definitely do not need something like this
	enum Delimiter {
		SPACE(" ");
		
		private String value;
		
		public String getValue() {
			return this.value;
		}
		
		private Delimiter (String value) {
			this.value = value;
		}
	}

	public void populateWordsMap (String input) {
		
		// Construct an array of words
		String[] words = input.split(Delimiter.SPACE.getValue());
		
		// Iterate the array to put the words into wordsMap
		for (String word : words) {
			// If it does not contain the key (word), create a new entry
			if (!wordsMap.containsKey(word)) {
				wordsMap.put(word, 1);
			}
			// Else we will increment the value + 1 for each occurrence in this loop
			else {
				wordsMap.put(word, wordsMap.get(word) + 1);
			}
		}
	}
	
	private HashMap<String, Integer> getWordsMap() {
		return this.wordsMap;
	}
	
	private HashMap<String, Integer> sortMapByWordOccurrences(HashMap<String, Integer> map) {
		// Create a sorted map
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		// Populate the sorted map with words frequency in descending order
		map.entrySet().stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(word -> sortedMap.put(word.getKey(), word.getValue()));
		
		return sortedMap;
	}
	
	/**
	 * Print the sortedMap word frequency separated by the word.
	 * Example: 2 test
	 * @param map
	 */
	public void printSortedMap (HashMap<String, Integer> map) {
		map.entrySet().forEach(word -> {
			System.out.println(String.format("%d %s", word.getValue(), word.getKey()));
		});
	}
	
	/**
	 * Get the word frequency output given an input String.
	 * @param input
	 */
	public void getWordFrequency (String input) {
		
		// Make sure we start with clean map
		getWordsMap().clear();
		
		// Populate the words to unsorted map
		populateWordsMap(input);
		
		// Print the sorted map
		printSortedMap(sortMapByWordOccurrences(getWordsMap()));
	}
}
