package logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WordCounterTest {
	
	WordCounter wc;
	
	HashMap<String, Integer> expectedMap;
	
	private final String input = "five five five five five four four four four three three three two two one";
	private final String expectedResult = "5 five\n4 four\n3 three\n2 two\n1 one\n";
	
	private final String anotherInput = "the cat is in the bag";

	@BeforeEach
	void setUp() throws Exception {
		wc = new WordCounter();
		
		expectedMap = new HashMap<String, Integer>();
		expectedMap.put("five", 5);
		expectedMap.put("four", 4);
		expectedMap.put("three", 3);
		expectedMap.put("two", 2);
		expectedMap.put("one", 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		wc = null;
	}

	@Test
	@DisplayName("Descending sorting logic on HashMap should work correctly")
	void testSortDescending() {
		HashMap<String, Integer> unsorted = new HashMap<String, Integer>();
		unsorted.put("three", 3);
		unsorted.put("five", 5);
		unsorted.put("one", 1);
		unsorted.put("four", 4);
		unsorted.put("two", 2);
		
		assertEquals(expectedMap, wc.sortMapDescendingByValue(unsorted));
	}
	
	@Test
	@DisplayName("It should populate the words HashMap from String input")
	void testPopulateWordsMap() {
		HashMap<String, Integer> map = wc.populateWordsMap(input);
		assertEquals(expectedMap, map);
	}
	
	@Test
	@DisplayName("It should correctly return the word frequency unique")
	void testGetWordFrequencyUnique() {
		assertEquals(expectedResult, wc.getWordFrequency(input));
	}
	
	@Test
	@DisplayName("It should correctly return the word frequency with repeated words, same frequency order does not matter")
	void testGetWordFrequencyRepeated() {
		assertTrue(wc.getWordFrequency(anotherInput).contains("2 the"));
		assertTrue(wc.getWordFrequency(anotherInput).contains("1 in"));
		assertTrue(wc.getWordFrequency(anotherInput).contains("1 cat"));
		assertTrue(wc.getWordFrequency(anotherInput).contains("1 bag"));
		assertTrue(wc.getWordFrequency(anotherInput).contains("1 is"));
	}
}
