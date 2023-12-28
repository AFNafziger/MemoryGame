package edu.wm.cs.cs301.memorygame;
import java.util.Scanner;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List; //https://www.homeandlearn.co.uk/java/write_to_textfile.html and https://www.geeksforgeeks.org/java-program-to-write-into-a-file/


public class MemoryGame {
    private GameBoard board;
    private Scanner menu_scan;
    private List<LeaderboardInfo> leaderboard;
    private String nameSelected;
    private int difficultyLevelSelected;
    
    public MemoryGame() {
    	menu_scan = new Scanner(System.in);
    	leaderboard = new ArrayList<>();

    	
        System.out.println("Welcome to the Memory Game! \n"); 
        
        
        
        System.out.println("HOW TO PLAY: \n The tiles each have a symbol on their face and come in pairs so that there are exactly two of every symbol. "
                + "\n To play the game, the tiles all start face down and then are shuffled randomly so that the location of any symbol "
                + "\n is not known. The player chooses two tiles by flipping them over. If their symbols match, the tiles are left face up "
                + "\n and the player’s turn continues. If they don’t match, the tiles are returned to the face-down position and the turn is over."
                + "\n The objective of the game is to reveal all pairs of tiles in the shortest number of turns. Good luck! \n"); 
        
        nameSelected = getName();

        String leaderboardPath = new File("resources/leaderboard.txt").getAbsolutePath();
        readLeaderboard(leaderboardPath);
        printLeaderboard();
        
        difficultyLevelSelected = selectDifficulty();
        //System.out.println(difficultyLevelSelected);
        int alphabetTypeSelected = selectAlphabet();
        //System.out.println(alphabetTypeSelected);
        
        if (difficultyLevelSelected == 1) {//MAYBE - make this more efficient? 
        	if (alphabetTypeSelected == 1) {
        		Alphabet latinAlphabet = new LatinAlphabet();
        		board = new GameBoard(3, 4, latinAlphabet);
                
        	} else {
        		Alphabet greekAlphabet = new GreekAlphabet();
        		board = new GameBoard(3, 4, greekAlphabet);
                
        	}
        } else if (difficultyLevelSelected == 2) {
        	if (alphabetTypeSelected == 1) {
        		Alphabet latinAlphabet = new LatinAlphabet();
        		board = new GameBoard(4, 7, latinAlphabet);
                
        	} else {
        		Alphabet greekAlphabet = new GreekAlphabet();
        		board = new GameBoard(4, 7, greekAlphabet);
              
        	}
        } else {
        	if (alphabetTypeSelected == 1) {
        		Alphabet latinAlphabet = new LatinAlphabet();
        		board = new GameBoard(7, 8, latinAlphabet);
                
        	} else {
        		Alphabet greekAlphabet = new GreekAlphabet();
        		board = new GameBoard(7, 8, greekAlphabet);
                
        	}
        }
        playTurn();
    }
    
    public void playTurn() {
        int turn = 1;
        boolean gameWon = false;

        while (!gameWon) {
            board.printBoard(turn);

            int[] tile1 = getPlayerInput("\n Enter the row and column of the first tile separated by a space: ");
            board.flipTile(tile1[0], tile1[1]);
            board.printBoard(turn);

            int[] tile2 = getPlayerInput("\n Enter the row and column of the second tile separated by a space: ");
            board.flipTile(tile2[0], tile2[1]);
            board.printBoard(turn);

            if (match(tile1[0], tile1[1], tile2[0], tile2[1])) {
                System.out.println("\n Tiles match! Go Again!");
                turn--;
            } else {
                System.out.println("\n Tiles don't match. Try again.");
                board.flipTileBack(tile1[0], tile1[1]);
                board.flipTileBack(tile2[0], tile2[1]);
                //board.printBoard(turn);
            }

            gameWon = checkIfOver();

            turn++;
        }

        gameResult(turn - 1);
    }
    
    
    private int selectDifficulty() {
        
        int difficultyLevelSelected = 0;

        while (difficultyLevelSelected < 1 ) {
            System.out.print("Select Difficulty (Easy, Medium, or Hard): ");
            String input = menu_scan.nextLine().trim().toLowerCase();

            if (input.equals("easy")) {
            	difficultyLevelSelected = 1;
            } else if (input.equals("medium")) {
            	difficultyLevelSelected= 2;
            } else if (input.equals("hard")) {
            	difficultyLevelSelected = 3;
            } else {
                System.err.println("Invalid difficulty entered. Please try again.");
            }
        }
        return difficultyLevelSelected;
    }
    private int selectAlphabet() {
 
        int alphabetType = 0;
        
        while (alphabetType < 1 ) {
        	System.out.print("Select an alphabet for the cards in the game (Latin or Greek): ");
            String input = menu_scan.nextLine().trim().toLowerCase();
        	
        	if (input.equals("latin")) {
            	alphabetType = 1;
            } else if (input.equals("greek")) {
            	alphabetType = 2;
            } else {
                System.err.println("Invalid alphabet entered. Please try again: ");
            }
        }

        
        return alphabetType;
    }
    
    private String getName() {
        System.out.print("Enter a name to play: ");
        String nameSelected = menu_scan.nextLine();
        System.out.print("Good luck, " + nameSelected + "!\n\n");
        return nameSelected;
    }
    
    
    private int[] getPlayerInput(String prompt) {
        
        int[] input = new int[2]; // Array to store row and column
        
        while (true) {
            System.out.print(prompt);
            String inputStr = menu_scan.nextLine();
            
            // Split on the space
            String[] parts = inputStr.split(" ");
            
            if (parts.length != 2) {
                System.err.println("Invalid input. Please enter row and column separated by a space.");
                continue;
            }
            try {
                int row = Integer.parseInt(parts[0])-1;//https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/parseInt
                int col = Integer.parseInt(parts[1])-1;
                
                if (row >= 0 && row < board.returnRows() && col >= 0 && col < board.returnCols()) {
                    input[0] = row;
                    input[1] = col;
                    break;
                } else {
                    System.err.println("Invalid row or column outside the grid. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter row and column as integers separated by a space.");
            } 
        }
        return input;
    }
    
    private boolean match(int row1, int col1, int row2, int col2) {
        char symbol1 = board.returnSymbol(row1, col1);
        char symbol2 = board.returnSymbol(row2, col2);

        // Compare the symbols and return true if match
        return symbol1 == symbol2;
    }
    
    public static void main(String[] args) {
    	new MemoryGame();
    }
    
    private boolean checkIfOver() {
        // Check for face-down tiles
    	for (int row = 0; row < board.returnRows(); row++) {
            for (int col = 0; col < board.returnCols(); col++) {
            	boolean flipped = board.returnSymbolFlip(row, col);
                if (!flipped) {
                    return false; // There's a face-down tile with symbol 0, so the game is not over
                }
            }
        }
        return true; 
    }
    
    private void gameResult(int turn) {
        int fixedTurns = turn + 1;
        System.out.println("Congratulations! You won the game in " + fixedTurns + " turns.");
        
        addLeaderboard(nameSelected, fixedTurns, getDifficultyName());
        String leaderboardPath = new File("resources/leaderboard.txt").getAbsolutePath();
        writeLeaderboard(leaderboardPath);
        printLeaderboard();
        boolean response = askToPlayAgain(turn);
        if (response) {
            //String name = getName();
            new MemoryGame();
        }
    }
    
    private String getDifficultyName() {//maybe not needed -- maybe remove
        if (difficultyLevelSelected == 1) {
            return "Easy";
        } else if (difficultyLevelSelected == 2) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    public boolean askToPlayAgain(int turn) {
        System.out.print("\n Play again? (yes/no): ");
        String input = menu_scan.nextLine().trim().toLowerCase();
        if (input.equals("yes")) {
        	return true;
        } else {
        	return false;
        }
        }
//Methods for the leaderboard
    private void readLeaderboard(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int turns = Integer.parseInt(parts[1]);
                    String difficulty = parts[2];
                    leaderboard.add(new LeaderboardInfo(name, turns, difficulty));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to write the leaderboard to the file 
    private void writeLeaderboard(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (LeaderboardInfo entry : leaderboard) {
                writer.write(entry.getName() + "," + entry.getTurns() + "," + entry.getDifficulty());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printLeaderboard() {
        System.out.println("Leaderboard:\n");
        for (LeaderboardInfo entry : leaderboard) {
            System.out.println("Name: " + entry.getName() + ", Turns: " + entry.getTurns() + ", Difficulty: " + entry.getDifficulty());
        }
        System.out.println();
    }

    private void addLeaderboard(String name, int turns, String difficulty) {
        leaderboard.add(new LeaderboardInfo(name, turns, difficulty));
        writeLeaderboard("resources/leaderboard.txt"); // Save the updated leaderboard to the file WORKING
    }
}



