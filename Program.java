
// Jessica Qian, APCS Peterson 2
// Programming Assignment Chapter 12

import java.util.ArrayList;

public class Program {

	// In the main method, it creates the word list, the board, finds all the words
	// on the board, and prints out all the possible words
	public static void main(String[] args) {
		System.out.println("Welcome to Boggle, by Jessica Qian");

		int min = 3;
		int max = 8;
		WordList words = new WordList("WordList.txt", min, max);
		Board newBoard = new Board(words, 4);
		System.out.println(newBoard.toString());
		ArrayList<String> allWords = newBoard.find();
		
		
		
		
		
		
		
		
		// display responses
		int wordLength = max + 1;
		System.out.println("Found " + allWords.size() + " word(s)");
		for (String temp : allWords) {
			if (temp.length() != wordLength) {
				wordLength = temp.length();
				System.out.println();
				System.out.println(wordLength + " letter words");
			}
			System.out.println(temp);
		}
	}

}
