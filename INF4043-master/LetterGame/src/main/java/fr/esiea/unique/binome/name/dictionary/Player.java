package fr.esiea.unique.binome.name.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Player
{	
	char initLetter;
	ArrayList<String> words = new ArrayList<String>();
	boolean isBot;
	
	Player() {
	isBot=false;
	}
	
	Player(boolean isBot) {
	this.isBot=isBot;
	}

	
	
	void AddWord(String word)
	{
		words.add(word);
	}
	
	void RemoveWord(String word)
	{		
		words.remove(word);
	}
	
	int getPoints()
	{
		return words.size();
	}
}
