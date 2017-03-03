package fr.esiea.unique.binome.name.dictionary;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Dictionary
{
	private String[] wordsList;

	public Dictionary()
	{
		wordsList = new String[5];
		wordsList[0] = "pierre";
		wordsList[1] = "feuille";
		wordsList[2] = "ciseau";
		wordsList[3] = "boite";
		wordsList[4] = "prout";
	}
	
	public Dictionary(String filePath) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File(filePath));
		
		int size = scan.nextInt();
		
		wordsList = new String[size];
		
		for (int i = 0; i < size; i++)
		{
			wordsList[i] = replaceFrenchCharacter(scan.next());
		}
		
		scan.close();
	}
	
	public String[] getWordsList()
	{
		return wordsList;
	}
	
	public boolean isValidWord(String word)
	{
		for (String arrayWord : wordsList)
		{
			if (arrayWord.equals(word))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean mayBeComposed(String word, char[] letters)
	{
		word = word.toLowerCase();
		word = replaceFrenchCharacter(word);
	
		boolean[] isUsed = new boolean[letters.length];
		Arrays.fill(isUsed, false);
		
		int charNotFound = 0;
		
		for (char c : letters)
		{
			if (c == '*')
			{
				charNotFound++;
			}
		}
		
		int wordLength = word.length();
		int lettersLength = letters.length;
		
		if (wordLength > lettersLength)
		{
			return false;
		}
		
		for (int i = 0; i < wordLength; i++)
		{
			charNotFound--;
			
			for (int j = 0; j < lettersLength; j++)
			{
				if (word.charAt(i) == letters[j] && !isUsed[j])
				{
					isUsed[j] = true;
					charNotFound++;
					break;
				}
			}
			
			if (charNotFound == -1)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static String replaceFrenchCharacter(String s)
	{	
		s = s.replace('à', 'a');
		s = s.replace('â', 'a');
		s = s.replace('ä', 'a');
		s = s.replace('ç', 'c');
		s = s.replace('é', 'e');
		s = s.replace('è', 'e');
		s = s.replace('ê', 'e');
		s = s.replace('ë', 'e');
		s = s.replace('î', 'i');
		s = s.replace('ï', 'i');
		s = s.replace('ô', 'o');
		s = s.replace('ö', 'o');
		s = s.replace('ù', 'u');
		s = s.replace('ü', 'u');
		s = s.replace('û', 'u');
		s = s.replace("œ", "oe");
		s = s.replace("æ", "ae");
		
		return s;
	}
	
	public String[] getWordsThatCanBeComposed(char[] letters)
	{
		String[] words;
		int size = 0;
	
		for (String word : wordsList)
		{
			if (mayBeComposed(word, letters))
			{
				size++;
			}
		}
		
		words = new String[size];
		
		int i = 0;
		
		for (String word : wordsList)
		{
			if (mayBeComposed(word, letters))
			{
				words[i] = word;
				i++;
			}
		}
		
		return words;
	}
	
	public static char[] getComposition(String word, char[] letters)
	{	
		char[] result = new char[word.length()];
	
		word = word.toLowerCase();
		word = replaceFrenchCharacter(word);
	
		boolean[] isUsed = new boolean[letters.length];
		Arrays.fill(isUsed, false);
		
		int wordLength = word.length();
		int lettersLength = letters.length;
		
		int nb = 0;
		
		for (int i = 0; i < wordLength; i++)
		{			
			for (int j = 0; j < lettersLength; j++)
			{
				if (word.charAt(i) == letters[j] && !isUsed[j])
				{
					isUsed[j] = true;
					result[nb] = letters[j];
					nb++;
					break;
				}
			}
		}		
		
		for (int i = nb; i < word.length(); i++)
		{
			result[i] = '*';
		}		
		
		return result;
	}
}