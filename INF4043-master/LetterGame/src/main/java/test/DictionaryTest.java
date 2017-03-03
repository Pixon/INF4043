package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import fr.esiea.unique.binome.name.dictionary.Dictionary;

public class DictionaryTest {

	@Test
	public void TestDictionary1() {
		Dictionary dictio = new Dictionary();
		
		if (dictio.wordsList[0] != "goéland") {
			fail("Dictionnaire mal chargé");
		}

		if (dictio.wordsList[1] != "élève") {
			fail("Dictionnaire  de remplacement mal chargé");
		}
		if (dictio.wordsList[2] != "à") {
			fail("Dictionnaire  de remplacement mal chargé");
		}
		if (dictio.wordsList[3] != "boite") {
			fail("Dictionnaire  de remplacement mal chargé");
		}
		if (dictio.wordsList[4] != "prout") {
			fail("Dictionnaire  de remplacement mal chargé");
		}
		
	}
	
	
	@Test
	public void TestDictionary2() {
		String inputWord ="accompagnateurs";
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(new File("").getAbsolutePath() + "\\src\\main\\resources\\dico.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier dictionnaire n'a pas été trouvé. Un dictionnaire de test a été créé.");
			dictionary = new Dictionary();
		}
		
		if  (dictionary.isValidWord(inputWord)) {
			System.out.println("Correct");
		}
		else {
			fail("Dictionnaire complet mal charge");
		}
		
		inputWord ="interpellateurs";
		if  (dictionary.isValidWord(inputWord)) {
			System.out.println("Correct");
		}
		else {
			fail("Dictionnaire complet mal charge");
		}
		inputWord ="zob";
		if  (dictionary.isValidWord(inputWord)) {
			System.out.println("Correct");
		}
		else {
			fail("Dictionnaire complet mal charge");
		}
		
		inputWord ="a";
		if  (dictionary.isValidWord(inputWord)) {
			System.out.println("Correct");
		}
		else {
			fail("Dictionnaire complet mal charge");
		}
	}
	
@Test
public void replaceFrenchCharacterTest() {
	
	String s ="à";
	String a="à";
		System.out.println(s);
	a = Dictionary.replaceFrenchCharacter(s);
	System.out.println(a);
	if (s != a ) {
		System.out.println("Parfait");
	}
	else {
		fail("Probleme de remplacement des accents");
		}
	
	s="é";
	a="é";
	
	System.out.println(s);
	a = Dictionary.replaceFrenchCharacter(s);
	System.out.println(a);
	if (s != a ) {
		System.out.println("Parfait");
	}
	else {
		fail("Probleme de remplacement des accents");
		}
	
	s="è";
	a="è";
	
	System.out.println(s);
	a = Dictionary.replaceFrenchCharacter(s);
	System.out.println(a);
	if (s != a ) {
		System.out.println("Parfait");
	}
	else {
		fail("Probleme de remplacement des accents");
		}
	
	s="æ";
	a="ae";
	
	System.out.println(s);
	a = Dictionary.replaceFrenchCharacter(s);
	System.out.println(a);
	if (s != a ) {
		System.out.println("Parfait");
	}
	else {
		fail("Probleme de remplacement des accents");
		}

}
}
