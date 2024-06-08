// Jessica Qian, APCS Peterson 2
// Programming Assignment Chapter 12

import java.util.ArrayList;
import java.util.Random;

public class Board {
	public String[][] board;
	public WordList wordList;

	// Public constructor to make a board with given size, filled with
	// random letters
	public Board(WordList wordList, int size) {
		String[][] board = new String[size][size];
		this.wordList = wordList;
		Random rand = new Random();
        Populate(board, size, wordList, rand, 0, 0);
		/*board[0][0] = "T";
		board[0][1] = "E";
		board[0][2] = "A";
		board[0][3] = "M";
		board[1][0] = "S";
		board[1][1] = "Y";
		board[1][2] = "F";
		board[1][3] = "S";
		board[2][0] = "P";
		board[2][1] = "A";
		board[2][2] = "T";
		board[2][3] = "E";
		board[3][0] = "G";
		board[3][1] = "N";
		board[3][2] = "I";
		board[3][3] = "D"; 
		
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				int index = (int) (Math.random() * wordList.size());
				String temp = wordList.get(index);
				index = (int) (Math.random() * temp.length());
				board[i][j] = temp.substring(index, index + 1);
			}
		} */
	}
	
	public static void Populate(String[][] board, int size, WordList wordlist, Random rand, int x, int y) {
        String randWord = wordlist.get(rand.nextInt(wordlist.size()));
        String randLetter = "" + randWord.charAt(rand.nextInt(randWord.length()));
        board[x][y] = randLetter;
        if (x == size - 1) {
            if (y != size - 1) {
                Populate(board, size, wordlist, rand, 0, y+1);
            }
        }
        else {
            Populate(board, size, wordlist, rand, x+1, y);
        }
    }

	// find method which recursively finds all possible words using the wordlist
		public ArrayList<String> find() {
			WordComparator wc = new WordComparator();
			ArrayList<String> usableWords = new ArrayList<String>();
			boolean[][] used = new boolean[this.board.length][this.board.length];

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					used[i][j] = true;
					String path = this.board[i][j];

					if (wordList.contains(path)) {
						usableWords.add(path);
					}
					find(i, j, used, path, usableWords);
					used[i][j] = false;
				}
			}

			// sorts the words using wordcomparator based on length
			usableWords.sort(wc);

			return usableWords;
		}

		// other method to help find() with parameters
		private void find(int i, int j, boolean[][] used, String currentPath, ArrayList<String> usableWords) {
			if (currentPath.length() == this.wordList.getLongestWordLength()) {
				return;
			}

			for (int iIndex = i - 1; iIndex < i + 2; iIndex++) {
				if (!indexInRange(iIndex)) {
					continue;
				}
				for (int jIndex = j - 1; jIndex < j + 2; jIndex++) {
					if (!indexInRange(jIndex) || used[iIndex][jIndex]) {
						continue;
					}
					String path = currentPath + this.board[iIndex][jIndex];
					if (!usableWords.contains(path) && wordList.contains(path)) {
						usableWords.add(path);
					}
					used[iIndex][jIndex] = true;
					find(iIndex, jIndex, used, path, usableWords);
					used[iIndex][jIndex] = false;
				}
			}
		}

		// other method to check if a number is on the board
		private Boolean indexInRange(int index) {
			if (index >= 0 && index < this.board.length) {
				return true;
			}
			return false;
		}

		// toString method to help print the board
		public String toString() {
			String stringOfBoard = "";
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					stringOfBoard += board[i][j] + "\t";
				}
				stringOfBoard += "\n";
			}
			return stringOfBoard;
		}

	}

