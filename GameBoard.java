package edu.wm.cs.cs301.memorygame;
import java.util.Random; //https://www.geeksforgeeks.org/java-util-random-class-java/

public class GameBoard {
	private char[][] board;
	private boolean[][] flipped;
	private int rows;
	private int cols;
	
	public GameBoard(int rows, int cols, Alphabet alphabet) {
		this.rows = rows;
		this.cols = cols;
		setupBoard(alphabet);
		
		flipped = new boolean[rows][cols]; //make the array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flipped[i][j] = false; //make 'em all false
            }
        }
	}
	//setting up the board at start of the game using selected alphabet
	private void setupBoard(Alphabet alphabet) {
		board = new char[rows][cols];
		char[] symbols = alphabet.toCharArray();
		int numOfPairs = rows * cols / 2;//finding the number of pairs the game will have
	
	
		scrambleTheSymbols(symbols);
		
		for(int i = 0; i < numOfPairs; i++){ //putting the alphabet/symbols into the 2d array
			char symbol = symbols[i];
			int count = 0;
			while (count < 2) { //do-while loop https://www.geeksforgeeks.org/java-do-while-loop-with-examples/
				int row, col; //storing row and col data
				do {
					row = new Random().nextInt(rows);
					col = new Random().nextInt(cols);
				} while (board[row][col] != 0);
				board[row][col] = symbol;
				count++;
		
			}
		}
	}
	private void scrambleTheSymbols(char[] array) { //randomizes all the symbols before adding to board
		Random random = new Random(); //Making new random
		for (int i= array.length-1; i > 0; i--) {
			int index = random.nextInt(i + 1);
            char tempVar = array[index];
            array[index] = array[i];
            array[i] = tempVar;
		}
	}
	public void printBoard(int turn) {
        System.out.println("\n Turn: " + turn);
        System.out.print("    ");
        for (int col = 0; col < cols; col++) {
            System.out.print((col + 1) + "   ");
        }

        System.out.println();

        for (int col = 0; col < cols; col++) {
            System.out.print("====");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < cols; j++) {
                if (flipped[i][j]) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print("? | ");
                }
            }
            System.out.println();
            for (int col = 0; col < cols; col++) {
                System.out.print("----");
            }
            System.out.println();
        }

        for (int col = 0; col < cols; col++) {
            System.out.print("====");
        }
	}
	
	//needed methods to get the rows and columns to varify user inputs 
	public int returnRows() {
	    return rows;
	}
	public int returnCols() {
	    return cols;
	}
	//might use later -- MAYBE DELETE
	public char returnSymbol(int row, int col) {
	    return board[row][col];
	}
	
	public boolean returnSymbolFlip(int row, int col) {
		return flipped[row][col];
	}
	
	//tiling fliping method -- DONE
	public boolean flipTile(int row, int col) {
	    // Check if tile is already flipped
	    if (flipped[row][col]) {
	        //System.err.println("Tile is already face-up.");
	        return false;
	    }
	    flipped[row][col] = true; //now we flip it bc it wasn't flipped before
	    return true;
	}
	public void flipTileBack(int row, int col) {
	    // Check if the tile is flipped already
	    if (flipped[row][col]) {
	        flipped[row][col] = false; // Flip it back
	    }
	}
	
}
