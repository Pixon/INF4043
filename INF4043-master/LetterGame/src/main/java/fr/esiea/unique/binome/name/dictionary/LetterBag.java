package fr.esiea.unique.binome.name.dictionary;

import java.util.Random;

public class LetterBag {

		private static Random random = new Random();
		private static int count;
		private static int rand;
		private static char voy;
		
		static char getNextLetter() {
			count =random.nextInt(3);
			
			if (count==1){
			
			return (char) ('a' + random.nextInt(26));
			}
			else {
				rand = random.nextInt(6);
				if (rand ==0) {
					voy='a';
					return voy;
				}
				if (rand ==1) {
					voy='e';
					return voy;
				}
				if (rand ==2) {
					voy='i';
					return voy;
				}
				if (rand ==3) {
					voy='o';
					return voy;
				}
				if (rand ==4) {
					voy='u';
					return voy;
				}if (rand ==0) {
					voy='y';
					return voy;
				}
				
				
			}
			return (char) ('a' + random.nextInt(26));
		}
		
}