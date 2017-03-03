package fr.esiea.unique.binome.name.dictionary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Board {

	ArrayList<Character> Pot = new ArrayList<Character>();
	LetterBag letterBag = new LetterBag();
	Player players[] = new Player[2];
	Dictionary dictionary;
	int currentPlayer;

	Board(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	void Tirage() {
		int nbPlayers = 2;

		// Scanner sc = new Scanner(System.in);

		System.out.println("La Partie commence");

		// System.out.println("Indiquez le nombre de joueurs");

		// nbPlayers = sc.nextInt();
		Scanner sc = new Scanner(System.in);
		int mode = 0;
		System.out.println("Veuillez choisir votre mode de Jeu");
		System.out.println("1 : Joueur VS Joueur ");
		System.out.println("2 : Joueur VS IA ");
		Player playerTemp[];

		mode = sc.nextInt();
		if (mode == 1) {
			System.out.println("Vous Avez choisis Joueur VS Joueur ");
			playerTemp = new Player[nbPlayers];
			for (int j = 0; j < nbPlayers; j++) {
				playerTemp[j] = new Player();

			}
		} else {
			System.out.println("Vous Avez choisis Joueur VS IA ");
			playerTemp = new Player[nbPlayers];
			playerTemp[0] = new Player();
			playerTemp[1] = new Player(true);
			System.out.println("Vous êtes le joueur 1 ");

		}

		System.out.println("Tirage de la première lettre de chaque joueur");
		for (int i = 0; i < nbPlayers; i++) {
			playerTemp[i].initLetter = LetterBag.getNextLetter();
		}

		for (int i = 0; i < 2; i++) {
			if (playerTemp[0].initLetter == playerTemp[1].initLetter) {
				System.out.println("Les deux lettres sont les mêmes, on recommence le tirage :D");
			} else {
				Pot.add(playerTemp[0].initLetter);
				Pot.add(playerTemp[1].initLetter);
				System.out.println("Les lettres sont : " + playerTemp[0].initLetter + " " + playerTemp[1].initLetter);
				break;
			}
		}
		if (playerTemp[0].initLetter <= playerTemp[1].initLetter) {
			players[0] = playerTemp[0];
			players[1] = playerTemp[1];
			System.out.println("Le joueur 1 commence");
			currentPlayer = 0;
		} else {
			players[1] = playerTemp[0];
			players[0] = playerTemp[1];
			System.out.println("Le joueur 2 commence");
			currentPlayer = 1;
		}
	}

	void run() {
		Tirage();
		while (true) {
			if (players[currentPlayer].isBot) {
				RunBot();
			} else {
				RunPlayer();

			}
		}
	}
	// sc.close();

	void RunPlayer() {
		Scanner sc;

		System.out.println("Joueur " + (currentPlayer + 1));
		char lettre1 = LetterBag.getNextLetter();
		char lettre2 = LetterBag.getNextLetter();
		Pot.add(lettre1);
		Pot.add(lettre2);
		sc = new Scanner(System.in);
		String letters = "";
		for (int j = 0; j < Pot.size(); j++) {
			letters += Pot.get(j) + " ";
		}
		System.out.println("Les lettres sont : " + letters
				+ " Tapez 1 si vous avez un mot ? Appuyez sur une autre touche pour passer votre tour.");

		int value = 0;

		try {
			value = sc.nextInt();
		} catch (InputMismatchException e) {

		}
		System.out.println("Vous avez saisi : " + value);
		if (value != 1) {
			System.out.println("Joueur suivant ! ");

			currentPlayer = (currentPlayer + 1) % 2;
		} else {
			sc = new Scanner(System.in);
			System.out.println("Veuillez taper votre mot : ");
			String inputWord = sc.nextLine();
			System.out.println("Vous avez entré : " + inputWord);

			if (dictionary.isValidWord(inputWord)) {
				char[] potChar = new char[Pot.size()];

				for (int j = 0; j < Pot.size(); j++) {
					potChar[j] = Pot.get(j);
				}

				if (Dictionary.mayBeComposed(inputWord, potChar)) {
					for (int k = 0; k < inputWord.length(); k++) {
						for (int l = 0; l < Pot.size(); l++) {
							if (inputWord.toCharArray()[k] == Pot.get(l)) {
								Pot.remove(l);
								break;
							}
						}
					}

					System.out.println("Le mot est bon !!!!!!");

					players[currentPlayer].AddWord(inputWord);

					System.out.println("Le joueur " + (currentPlayer + 1) + " a " + players[currentPlayer].getPoints()
							+ " points");

					if (players[currentPlayer].getPoints() >= 10) {
						System.out.println("Le joueur " + (currentPlayer + 1) + " a gagné !");
						System.exit(0);
					}
				} else {
					System.out.println("Le mot ne peut pas être composé avec les lettres du pot, joueur suivant");

					currentPlayer = (currentPlayer + 1) % 2;
				}

			} else {
				System.out.println("Le mot n'a pas été trouvé, au joueur suivant");

				currentPlayer = (currentPlayer + 1) % 2;
			}
		}

	}

	void RunBot() {
		Random random = new Random();
		char[] Tab = new char[Pot.size()];
		String[] str;
		int rand;
		for (int i = 0; i < Pot.size(); i++) {
			
			Tab[i] = Pot.get(i);
		}
		str = dictionary.getWordsThatCanBeComposed(Tab);
		if (str.length == 0) {
			System.out.println("L'IA n'a trouvé aucun mot, à votre tour");
			currentPlayer = (currentPlayer + 1) % 2;
		} else {
			rand = random.nextInt(2);
			if (rand ==1) {
			
			System.out.println("L'IA a trouvé : " + str[0]);
			String word;
			word = str[0];
			players[currentPlayer].AddWord(word);
			System.out.println("L'IA  a " + players[currentPlayer].getPoints()
					+ " points");
			for (int k = 0; k < str[0].length(); k++) {
				for (int l = 0; l < Pot.size(); l++) {
					if (str[0].toCharArray()[k] == Pot.get(l)) {
						Pot.remove(l);
						
						
						break;
					}
				}
				}
			}
		}
	}
}
