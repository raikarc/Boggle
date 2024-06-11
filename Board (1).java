// Raika Roy Choudhury, APCS Peterson P2
// Programming Project #11; 01 April 2022
//
// This class uses ArrayLists to create the full playing Board of any size for Boggle
import java.util.*;

public class Board {
	private WordList wordlist;
	private String[][] board;
	
	// Board Constructor
	public Board(WordList wordlist, int boardsize) {
		this.wordlist = wordlist;
		board = new String[boardsize][boardsize];
		Random r = new Random();
		
		for (int x = 0; x < boardsize; x++) {
			for (int y = 0; y < boardsize; y++) {
				String word = wordlist.get(r.nextInt(wordlist.size()));
				board[x][y] = word.charAt(r.nextInt(word.length())) + "";
			}
		}
		
	}
	
	// toString 
	public String toString() {
		String words = new String();
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[0].length; k++) {
				words += board [i][k] + "  ";
			}
			words += "\n";
		}
		return words;
	}

	// This method calls the recursive method
	public ArrayList<String> find(){
		boolean[][] compl =  new boolean [board.length][board[0].length];
		String words = "";
		ArrayList<String> result = new ArrayList<>(); //returns array list
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board.length; k++) {
				find(i, k, result, words, compl);
			}	
		}
		
		Collections.sort(result, new WordComparator()); //returns error sometimes ??
		return result;	
	}
	
	// This method overloads the previous method
	public void find (int x, int y, ArrayList<String> result, String word, boolean[][] compl) {	
		// checks if ints are out of bounds
		if (x < 0 || y < 0 || x >= board.length || y >= board.length || compl[x][y]) {
			return;
		}
		word += board[x][y];
		compl[x][y] = true; //bread-crumbs
		
		// if found, add solution to list
		if(wordlist.contains(word) && !result.contains(word)) {
			result.add(word);	
		}		
		
		//
		for (int i = x-1; i >= 0 && i < board.length && i <= x + 1; i ++) {
			for (int k = y-1; k >= 0 && k < board.length && k <= y+1; k++) {
				if(!compl [i][k] && word.length() != wordlist.getLongestWordLength() ) {
					find(i, k, result, word, makeCopy(compl)); //recursive call
				}	
			}
		}
		
		compl[x][y] = false;
		word = word.substring(0, word.length()-1);
	}
	
	// This method makes a copy of the array
	public boolean[][] makeCopy(boolean[][]compl){
		 boolean[][] visited = new boolean[compl.length][compl[0].length];
	        for (int i = 0; i < compl.length; i++) {
	        	for (int k = 0; k < compl[0].length; k++) {
	                visited[i][k] = compl[i][k];
	            }
	        }
	        return visited;
	}

}
