package test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.esiea.unique.binome.name.dictionary.LetterBag;

public class LetterBagTest {

	
	@Test
	public void TestletterBag() {
		char a;
		int temp;
		int Tab[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		LetterBag letterBag = new LetterBag();
		
		for (int i=0; i<10000;i++) {
			a = LetterBag.getNextLetter();
			temp = (int) ( a - 'a');
			Tab[temp]= Tab[temp]+1;
		}
		
		for (int j=0; j<26 ; j++) {
			if (Tab[j]==0) {
				fail("La fonction ne crée pas toutes les lettre de l'alphabet");
			}
		}
	}
}
		

		
	
		
	 
	 

