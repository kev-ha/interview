package logic;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounter {
	
	// List of Delimiter enum
	private enum Delimiter {
		SPACE(" "),
		NEWLINE("\n");
		
		private String value;
		
		public String getValue() {
			return this.value;
		}
		
		private Delimiter (String value) {
			this.value = value;
		}
	}

	public HashMap<String, Integer> populateWordsMap (String input) {
		// Data structure of choice is HashMap since it stores unique values as key
		HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
		
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
		
		return wordsMap;
	}
	
	protected HashMap<String, Integer> sortMapDescendingByValue(HashMap<String, Integer> map) {
		// Create a sorted map
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		// Populate the sorted map with words frequency in descending order
		map.entrySet().stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		
		return sortedMap;
	}
	
	/**
	 * Print the sortedMap word frequency separated by the word.
	 * Example: 2 test
	 * @param map
	 */
	public String getResult (HashMap<String, Integer> map) {
		final StringBuilder sb = new StringBuilder();
		map.entrySet().forEach(word -> {
			sb.append(String.format("%d %s%s", word.getValue(), word.getKey(), Delimiter.NEWLINE.getValue()));
		});
		
		return sb.toString();
	}
	
	/**
	 * Get the word frequency output given an input String.
	 * @param input
	 */
	public String getWordFrequency (String input) {
		// Populate the words to unsorted map
		HashMap<String, Integer> unsortedMap = populateWordsMap(input);
		
		// Sort the unsortedMap into descending order based on value
		HashMap<String, Integer> sortedMap = sortMapDescendingByValue(unsortedMap);

		// Print the sorted map
		String result = getResult(sortedMap);
		
		return result;
	}
}
