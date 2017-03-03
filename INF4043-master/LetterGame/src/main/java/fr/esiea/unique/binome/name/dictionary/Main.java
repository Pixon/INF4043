package fr.esiea.unique.binome.name.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		
		char char1='a';
		char char2='a';
		System.out.println(char1 >  char2);
		System.out.println("Bienvenue sur le Jeu des mots");
		
		
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(new File("").getAbsolutePath() + "\\src\\main\\resources\\dico.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier dictionnaire n'a pas été trouvé. Un dictionnaire de test a été créé.");
			dictionary = new Dictionary();
		}
		
		Board board = new Board(dictionary);
		
		board.run();
	}
}
