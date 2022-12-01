/* Program Purpose: the purpose of this lab is to recive an incomplete sudoku puzzle, validate it and solve it
 *
 *
 *
 * Team:
 * 	David H Smith IV
 * 	Sean Chambers
 * 	Durante Rodriguez
 * 	Phillip Shigeyama 
 *
 */


import java.util.*;

class Sudoku{
	
	//checks to see if the given sudoku puzzle is valid
	public static boolean check(char[][] puzzle){
		
		ArrayList<Character> colInts = new ArrayList<Character>();
		ArrayList<Character> rowInts = new ArrayList<Character>();

		//loops through the matrix
		for(int row = 0; row < puzzle.length; row++){
		
			for(int col = 0; col < puzzle[row].length; col++){
				
				//gets the char on row and opposite colum
				char rowChar = puzzle[row][col];
				char colChar = puzzle[col][row];
				
				//checks to see if that char has already been found in the row
				if(rowChar != '.'){
					if(rowInts.contains(rowChar)){
						return false;
					}
					rowInts.add(rowChar);
				}
				//checks to see if that char has already been found in the column
				if(colChar != '.'){
					if(colInts.contains(colChar)){
						return false;
					}
					colInts.add(colChar);
				}
			}

			rowInts.clear();
			colInts.clear();
		}

		//checks each submatrix
		int start = 0;
		for(int row = start; row < start+3; row += 3){
			for(int col = start; col < start+3; col += 3){
				//loops through the matrix
				for(row = 0; row < puzzle.length; row++){

					for(col = 0; col < puzzle[row].length; col++){

						//gets the char on row and opposite colum
						char rowChar = puzzle[row][col];
						char colChar = puzzle[col][row];

						//checks to see if that char has already been found in the row
						if(rowChar != '.'){
							if(rowInts.contains(rowChar)){
								return false;
							}
							rowInts.add(rowChar);
						}
						//checks to see if that char has already been found in the column
						if(colChar != '.'){
							if(colInts.contains(colChar)){
								return false;
							}
							colInts.add(colChar);
						}
					}

					rowInts.clear();
					colInts.clear();

				}
			}
		}	
		
		return true;
	}
	
	//prints the sudoku
	public static void printSudoku(char[][] puzzle){
		for(int row = 0; row < puzzle.length; row++){
			for(int col = 0; col < puzzle[row].length; col++){
				
				if((col != 0 && col != 8) && (col) % 3 == 0 ){
					System.out.print("|");
				}
				System.out.print(puzzle[row][col] + " ");
			}
			
			if((row != 0 && row !=  8 ) && (row + 1) % 3 == 0){
				System.out.print("\n- - - - - - - - - -");
			}

			System.out.println();
		 }
	}

	//wrapper method for sudoku puzzle
	public static void Sudoku(char[][] puzzle){
		if(puzzle == null || puzzle.length == 0){
			return;
		}

		solveSudoku(puzzle);
		
	}

	//finds a hole and returns the cordinates	
	public static int[] findHole(char[][] puzzle){
		int[] holeCords = new int[2];
		for(int row = 0; row < puzzle.length; row++){
			for(int col = 0; col < puzzle[row].length; col++){
				if(puzzle[row][col] == '.'){
					holeCords[0] = row;
					holeCords[1] = col;
					return holeCords;		
				}
			}
		}
		//sets cords to negative 1 if no holes are left	
		holeCords[0] = -1;
		holeCords[1] = -1;

		return holeCords;
	}

	//recursive solver for sudokupuzzle
	private static boolean solveSudoku(char[][] puzzle){

		//find a hole and set the cords
		int[] hole = findHole(puzzle);
		int row = hole[0];
		int col = hole[1];
		

		//if not hole was found the array is full
		if(hole[0] == -1 && hole[1] == -1){
			return true;
		}
	
		else{
			for(int n = 1; n <=9; n++){

				puzzle[row][col] = (char)(n + 48);

				if(check(puzzle) && solveSudoku(puzzle)){
					return true;
				}

				//remove the guess if its not valid
				puzzle[row][col] = '.';
			}
			return false;
		}
	}


	public static void main(String[] args){
		
		char[][] puzzle = SudokuP.puzzle();
		
		System.out.println("Unsolved Sudoku: ");
		printSudoku(puzzle);

		if(check(puzzle)){
			solveSudoku(puzzle);
			System.out.println("\nSolvedSudoku: ");
			printSudoku(puzzle);
		}

		else{
			System.out.println("The puzzle entered was not valid");
		}

	}
}
