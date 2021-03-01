package activities;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) throws Exception {

		String[][] board = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = " ";
			}
		}
		int turn = 1;
		boolean start = false;
		String typeOne = "";
		String typeTwo = "";

		Scanner sc = new Scanner(System.in);

		while (start == false) {
			System.out.println("Input command:");
			String ans = sc.nextLine();
			String[] ansArr = ans.split(" ");
			if (ansArr.length == 3) {
				start = true;
				typeOne = ansArr[1];
				typeTwo = ansArr[2];
			} else if (ansArr[0].toLowerCase().contains("exit")) {
				System.exit(0);
			} else {
				System.out.println("Bad parameters!");
			}
		}

		printMatch(board);

		while (turn < 10) {

			if ((turn % 2) == 0) {
				
				if (typeTwo.contains("easy")) {
					generateAIOEasy(board);
					yWins(board);
					draw(board);
					turn++;
				}

				if (typeTwo.contains("medium")) {
					generateAIOMedium(board);
					yWins(board);
					draw(board);
					turn++;
				}
				
				if (typeTwo.contains("hard")) {
					generateAIOHard(board);
					yWins(board);
					draw(board);
					turn++;
				}

				if (typeTwo.contains("user")) {
					System.out.println("Enter the coordinates:");
					String one = sc.next();
					String two = sc.next();
					if (isNumeric(one) && isNumeric(two)) {
						if (validateNumber(Integer.parseInt(one), Integer.parseInt(two))) {
							System.out.println("Coordinates should be from 1 to 3!");
						} else {
							if (validationPlaceOPlayer(Integer.parseInt(one), Integer.parseInt(two), board)) {
								yWins(board);
								draw(board);
								turn++;
							}
							else {
								return;
							}
						}
					} else {
						System.out.println("You should enter numbers!");
					}
				}

			}

			else if ((turn % 2) != 0) {

				if (typeOne.contains("easy")) {
					generateAIXEasy(board);
					xWins(board);
					draw(board);
					turn++;
				}

				if (typeOne.contains("medium")) {
					generateAIXMedium(board);
					xWins(board);
					draw(board);
					turn++;
				}
				
				if (typeOne.contains("hard")) {
					generateAIXHard(board);
					xWins(board);
					draw(board);
					turn++;
				}

				if (typeOne.contains("user")) {
					System.out.println("Enter the coordinates:");
					String one = sc.next();
					String two = sc.next();
					if (isNumeric(one) && isNumeric(two)) {
						
						if (validateNumber(Integer.parseInt(one), Integer.parseInt(two))) {
							System.out.println("Coordinates should be from 1 to 3!");
						} else {
							if (validationPlaceXPlayer(Integer.parseInt(one), Integer.parseInt(two), board)) {
								xWins(board);
								draw(board);
								turn++;
							}
							else {
								return;
							}
						}
					} else {
						System.out.println("You should enter numbers!");
					}
				}
			}

		}

	} // end main

	public static boolean xWins(String[][] board) {
		if (((board[0][0].contains("X") && board[0][1].contains("X") && board[0][2].contains("X"))
				|| (board[1][0].contains("X") && board[1][1].contains("X") && board[1][2].contains("X"))
				|| (board[2][0].contains("X") && board[2][1].contains("X") && board[2][2].contains("X"))
				|| (board[0][0].contains("X") && board[1][1].contains("X") && board[2][2].contains("X"))
				|| (board[0][2].contains("X") && board[1][1].contains("X") && board[2][0].contains("X"))
				|| (board[0][0].contains("X") && board[1][0].contains("X") && board[2][0].contains("X"))
				|| (board[0][1].contains("X") && board[1][1].contains("X") && board[2][1].contains("X"))
				|| (board[0][2].contains("X") && board[1][2].contains("X") && board[2][2].contains("X")))
				&& !((board[0][0].contains("0") && board[0][1].contains("0") && board[0][2].contains("0"))
						|| (board[1][0].contains("0") && board[1][1].contains("0") && board[1][2].contains("0"))
						|| (board[2][0].contains("0") && board[2][1].contains("0") && board[2][2].contains("0"))
						|| (board[0][0].contains("0") && board[1][1].contains("0") && board[2][2].contains("0"))
						|| (board[0][2].contains("0") && board[1][1].contains("0") && board[2][0].contains("0"))
						|| (board[0][0].contains("0") && board[1][0].contains("0") && board[2][0].contains("0"))
						|| (board[0][1].contains("0") && board[1][1].contains("0") && board[2][1].contains("0"))
						|| (board[0][2].contains("0") && board[1][2].contains("0") && board[2][2].contains("0")))) {
			System.out.println("X wins");
			System.exit(0);
			return true;
		}
		return false;
	} // end x wins

	public static boolean yWins(String[][] board) {
		if (((board[0][0].contains("0") && board[0][1].contains("0") && board[0][2].contains("0"))
				|| (board[1][0].contains("0") && board[1][1].contains("0") && board[1][2].contains("0"))
				|| (board[2][0].contains("0") && board[2][1].contains("0") && board[2][2].contains("0"))
				|| (board[0][0].contains("0") && board[1][1].contains("0") && board[2][2].contains("0"))
				|| (board[0][2].contains("0") && board[1][1].contains("0") && board[2][0].contains("0"))
				|| (board[0][0].contains("0") && board[1][0].contains("0") && board[2][0].contains("0"))
				|| (board[0][1].contains("0") && board[1][1].contains("0") && board[2][1].contains("0"))
				|| (board[0][2].contains("0") && board[1][2].contains("0") && board[2][2].contains("0")))
				&& !((board[0][0].contains("X") && board[0][1].contains("X") && board[0][2].contains("X"))
						|| (board[1][0].contains("X") && board[1][1].contains("X") && board[1][2].contains("X"))
						|| (board[2][0].contains("X") && board[2][1].contains("X") && board[2][2].contains("X"))
						|| (board[0][0].contains("X") && board[1][1].contains("X") && board[2][2].contains("X"))
						|| (board[0][2].contains("X") && board[1][1].contains("X") && board[2][0].contains("X"))
						|| (board[0][0].contains("X") && board[1][0].contains("X") && board[2][0].contains("X"))
						|| (board[0][1].contains("X") && board[1][1].contains("X") && board[2][1].contains("X"))
						|| (board[0][2].contains("X") && board[1][2].contains("X") && board[2][2].contains("X")))) {
			System.out.println("O wins");
			System.exit(0);
			return true;
		}
		return false;
	} // end o wins

	public static boolean emptyBoard(String[][] board) {
		int counter = 0;
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[i][j].contains(" ")) {
					counter++;
				}
			}
		}
		if (counter == 0) {
			return true;
		}
		return false;
	}

	public static boolean draw(String[][] board) {
		if (!xWins(board) && !yWins(board) && emptyBoard(board)) {
			System.out.println("Draw");
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validationPlaceX(int one, int two, String[][] board) {
		if (board[one - 1][two - 1].contains(" ")) {
			board[one - 1][two - 1] = "X";
			return true;
		}
		return false;
	}

	public static boolean validationPlaceXPlayer(int one, int two, String[][] board) {
		if (board[one - 1][two - 1].contains(" ")) {
			board[one - 1][two - 1] = "X";
			printMatch(board);
			return true;
		} else {
			System.out.println("This cell is occupied! Choose another one!");
		}
		return false;
	}

	public static boolean validationPlaceO(int one, int two, String[][] board) {
		if (board[one - 1][two - 1].contains(" ")) {
			board[one - 1][two - 1] = "O";
			return true;
		}
		return false;
	}

	public static boolean validationPlaceOPlayer(int one, int two, String[][] board) {
		if (board[one - 1][two - 1].contains(" ")) {
			board[one - 1][two - 1] = "O";
			return true;
		} else {
			System.out.println("This cell is occupied! Choose another one!");
		}
		return false;
	}

	public static boolean validateNumber(Integer one, Integer two) {
		if ((one == 1 || one == 2 || one == 3) && (two == 1 || two == 2 || two == 3)) {
			return false;
		} else {
			return true;
		}
	}

	public static void printMatch(String[][] board) {

		System.out.println("---------");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (j == 0) {
					System.out.print("| " + board[i][j]);
				} else if (j == 2) {
					System.out.print(" " + board[i][j] + " |\n");
				} else {
					System.out.print(" " + board[i][j]);
				}
			}
		}
		System.out.println("---------");
	}

	public static int[] randomNumbers() {
		int one = (int) Math.ceil(Math.random() * 10);
		int two = (int) Math.ceil(Math.random() * 10);

		while (one > 3 || one == 0) {
			one = (int) Math.ceil(Math.random() * 10);
		}

		while (two > 3 || two == 0) {
			two = (int) Math.ceil(Math.random() * 10);
		}

		int[] arr = { one, two };

		return arr;
	}

	public static void generateAIXEasy(String[][] board) {

		int[] arr = randomNumbers();

		if (validationPlaceX(arr[0], arr[1], board)) {
			System.out.println("Making move level \"easy\"");
			printMatch(board);
		} else {
			generateAIXEasy(board);
		}
	}

	public static void generateAIXMedium(String[][] board) {
		// Beginning horizontal logic
		if (board[0][0].contains("O") && board[0][1].contains("O") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "X";
			printMatch(board);
			return;
		} else if (board[0][1].contains("O") && board[0][2].contains("O") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "X";
			printMatch(board);
			return;
		} else if (board[1][0].contains("O") && board[1][1].contains("O") && board[1][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[1][2] = "X";
			printMatch(board);
			return;
		} else if (board[1][1].contains("O") && board[1][2].contains("O") && board[1][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[1][0] = "X";
			printMatch(board);
			return;
		} else if (board[2][0].contains("O") && board[2][1].contains("O") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "X";
			printMatch(board);
			return;
		} else if (board[2][1].contains("O") && board[2][2].contains("O") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "X";
			printMatch(board);
			return;
		} // End horizontal logic
			// Beginning vertical logic
		if (board[0][0].contains("O") && board[1][0].contains("O") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "X";
			printMatch(board);
			return;
		} else if (board[2][0].contains("O") && board[1][0].contains("O") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "X";
			printMatch(board);
			return;
		} else if (board[0][1].contains("O") && board[1][1].contains("O") && board[0][1].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][1] = "X";
			printMatch(board);
			return;
		} else if (board[2][1].contains("O") && board[1][1].contains("O") && board[0][1].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][1] = "X";
			printMatch(board);
			return;
		} else if (board[0][2].contains("O") && board[1][2].contains("O") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "X";
			printMatch(board);
			return;
		} else if (board[2][2].contains("O") && board[1][2].contains("O") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "X";
			printMatch(board);
			return;
		} // End vertical logic
			// Beginning diagonal logic
		if (board[0][0].contains("O") && board[1][1].contains("O") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "X";
			printMatch(board);
			return;
		} else if (board[2][2].contains("O") && board[1][1].contains("O") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "X";
			printMatch(board);
			return;
		} else if (board[0][2].contains("O") && board[1][1].contains("O") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "X";
			printMatch(board);
			return;
		} else if (board[2][0].contains("O") && board[1][1].contains("O") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "X";
			printMatch(board);
			return;
		}

		int[] arr = randomNumbers();

		if (validationPlaceX(arr[0], arr[1], board)) {
			System.out.println("Making move level \"medium\"");
			printMatch(board);
		} else {
			generateAIXMedium(board);
		}
	}

	public static void generateAIOEasy(String[][] board) {

		int[] arr = randomNumbers();

		if (validationPlaceO(arr[0], arr[1], board)) {
			System.out.println("Making move level \"easy\"");
			printMatch(board);
		} else {
			generateAIOEasy(board);
		}
	}

	public static void generateAIOMedium(String[][] board) {

		// Beginning horizontal logic
		if (board[0][0].contains("X") && board[0][1].contains("X") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "O";
			printMatch(board);
			return;
		} else if (board[0][1].contains("X") && board[0][2].contains("X") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "O";
			printMatch(board);
			return;
		} else if (board[1][0].contains("X") && board[1][1].contains("X") && board[1][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[1][2] = "O";
			printMatch(board);
			return;
		} else if (board[1][1].contains("X") && board[1][2].contains("X") && board[1][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[1][0] = "O";
			printMatch(board);
			return;
		} else if (board[2][0].contains("X") && board[2][1].contains("X") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "O";
			printMatch(board);
			return;
		} else if (board[2][1].contains("X") && board[2][2].contains("X") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "O";
			printMatch(board);
			return;
		} // End horizontal logic
			// Beginning vertical logic
		if (board[0][0].contains("X") && board[1][0].contains("X") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "O";
			printMatch(board);
			return;
		} else if (board[2][0].contains("X") && board[1][0].contains("X") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "O";
			printMatch(board);
			return;
		} else if (board[0][1].contains("X") && board[1][1].contains("X") && board[2][1].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][1] = "O";
			printMatch(board);
			return;
		} else if (board[2][1].contains("X") && board[1][1].contains("X") && board[0][1].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][1] = "O";
			printMatch(board);
			return;
		} else if (board[0][2].contains("X") && board[1][2].contains("X") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "O";
			printMatch(board);
			return;
		} else if (board[2][2].contains("X") && board[1][2].contains("X") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "O";
			printMatch(board);
			return;
		} // End vertical logic
			// Beginning diagonal logic
		if (board[0][0].contains("X") && board[1][1].contains("X") && board[2][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][2] = "O";
			printMatch(board);
			return;
		} else if (board[2][2].contains("X") && board[1][1].contains("X") && board[0][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][0] = "O";
			printMatch(board);
			return;
		} else if (board[0][2].contains("X") && board[1][1].contains("X") && board[2][0].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[2][0] = "O";
			printMatch(board);
			return;
		} else if (board[2][0].contains("X") && board[1][1].contains("X") && board[0][2].contains(" ")) {
			System.out.println("Making move level \"medium\"");
			board[0][2] = "O";
			printMatch(board);
			return;
		}

		int[] arr = randomNumbers();

		if (validationPlaceO(arr[0], arr[1], board)) {
			System.out.println("Making move level \"medium\"");
			printMatch(board);
		} else {
			generateAIOMedium(board);
		}
	}
	
	public static void generateAIOHard(String[][] board) {
		
		Integer bestScore = -1000;
		String[][] arr = board;
		int[] finalMove = { 0 , 0 };
		
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (arr[i][j].contains(" ")) {
					arr[i][j] = "O";
					int score = minimaxO(arr, 0, false);
					if (score > bestScore) {
						bestScore = score;
						finalMove[0] = i;
						finalMove[1] = j;
					}
				}
			}
		}
		
		if (validationPlaceO(finalMove[0] + 1, finalMove[1] + 1, board)) {
			System.out.println("Making move level \"hard\"");
			printMatch(board);
		} 
	}
	
	public static int minimaxO(String[][] board, int move, boolean goingTrue) {

		boolean xWin = xWins(board);
		boolean oWin = yWins(board);
		
		if (xWin == true || oWin == true) {
			if (oWin == true) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		if (goingTrue) {
			Integer bestScore = -1000;
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board.length - 1; j++) {
					if (board[i][j].contains(" ")) {
						board[i][j] = "O";
						int score = minimaxO(board, move + 1, false);
						if (score > bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}
		else {
			Integer bestScore = 1000;
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board.length - 1; j++) {
					if (board[i][j].contains(" ")) {
						board[i][j] = "X";
						int score = minimaxO(board, move + 1, true);
						if (score < bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}
	}
	
public static void generateAIXHard(String[][] board) {
		
		Integer bestScore = -1000;
		String[][] arr = board;
		int[] finalMove = { 0 , 0 };
		
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (arr[i][j].contains(" ")) {
					arr[i][j] = "X";
					int score = minimaxO(arr, 0, false);
					if (score > bestScore) {
						bestScore = score;
						finalMove[0] = i;
						finalMove[1] = j;
					}
				}
			}
		}
		
		if (validationPlaceX(finalMove[0] + 1, finalMove[1] + 1, board)) {
			System.out.println("Making move level \"hard\"");
			printMatch(board);
		} 
	}
	
	public static int minimaxX(String[][] board, int move, boolean goingTrue) {

		boolean xWin = xWins(board);
		boolean oWin = yWins(board);
		
		if (xWin == true || oWin == true) {
			if (oWin == true) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		if (goingTrue) {
			Integer bestScore = -1000;
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board.length - 1; j++) {
					if (board[i][j].contains(" ")) {
						board[i][j] = "X";
						int score = minimaxX(board, move + 1, false);
						if (score > bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}
		else {
			Integer bestScore = 1000;
			for (int i = 0; i < board.length - 1; i++) {
				for (int j = 0; j < board.length - 1; j++) {
					if (board[i][j].contains(" ")) {
						board[i][j] = "O";
						int score = minimaxX(board, move + 1, true);
						if (score < bestScore) {
							bestScore = score;
						}
					}
				}
			}
			return bestScore;
		}
	}
}
