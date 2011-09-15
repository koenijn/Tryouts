package nl.atos.a512451.tryouts.katas;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Kata0001Test {
	
	@Test
	public void testGenerateWordsHoi() {
		List<List<String>> words = Kata0001.generateWords("hoi", 1);
		
		assertNotNull(words);
		assertTrue(words.size() > 0);
		assertTrue(words.get(0).size() > 0);
		assertEquals("hoi", words.get(0).get(0));
	}

	@Test
	public void testGenerateWordsIoh() {
		List<List<String>> words = Kata0001.generateWords("ioh", 1);
		
		assertNotNull(words);
		assertTrue(words.size() > 0);
		assertTrue(words.get(0).size() > 0);
		assertEquals("hoi", words.get(0).get(0));
	}

	@Test
	public void testGenerateWordsNoWords() {
		List<List<String>> words = Kata0001.generateWords("toh", 1);
		
		assertNull(words);
	}

	@Test
	public void testGenerateWordsNoWords2() {
		List<List<String>> words = Kata0001.generateWords("imorakh", 3);
		
		assertNull(words);
	}

	@Test
	public void testGenerateWordsNoWords3() {
		List<List<String>> words = Kata0001.generateWords("ixorakh", 2);
		
		assertNull(words);
	}	
	
	@Test
	public void testGenerateWordsHoiMark() {
		List<List<String>> words = Kata0001.generateWords("imorakh", 2);
		
		assertNotNull(words);
		assertTrue(words.size() > 0);
		assertTrue(words.get(0).size() > 1);
		assertEquals("hoi", words.get(0).get(0));
		assertEquals("mark", words.get(0).get(1));
	}
	
	@Test
	public void testGenerateWordsHalloMark() {
		List<List<String>> words = Kata0001.generateWords("hallomark", 2);
		
		assertNotNull(words);
		assertTrue(words.size() > 0);
		assertTrue(words.get(0).size() > 1);
		assertEquals("mark", words.get(0).get(0));
		assertEquals("hallo", words.get(0).get(1));
	}

	@Test
	public void testGenerateWordsHoiPotTop() {
		List<List<String>> words = Kata0001.generateWords("pot", 1);
		
		assertNotNull(words);
		assertTrue(words.size() > 1);
		assertTrue(words.get(0).size() > 0);
		assertEquals("pot", words.get(0).get(0));
		assertTrue(words.get(1).size() > 0);
		assertEquals("top", words.get(1).get(0));
	}
	
	@Test
	public void testGenerateWordsHoiPotTop2() {
		List<List<String>> words = Kata0001.generateWords("hoipot", 2);
		
		assertNotNull(words);
		assertTrue(words.size() > 1);
		assertTrue(words.get(0).size() > 1);
		assertEquals("hoi", words.get(0).get(0));
		assertEquals("pot", words.get(0).get(1));
		assertTrue(words.get(1).size() > 1);
		assertEquals("hoi", words.get(0).get(0));
		assertEquals("pot", words.get(0).get(1));
	}
	
	@Test
	public void testGenerateWordsPotPot() {
		List<List<String>> words = Kata0001.generateWords("potpot", 2);
		
		assertNotNull(words);
		assertTrue(words.size() == 1);
		assertTrue(words.get(0).size() > 1);
		assertEquals("pot", words.get(0).get(0));
		assertEquals("top", words.get(0).get(1));
	}
}
