import java.util.Random;
import java.util.Scanner;

public class tictactoe {

    static char[] square = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static String winCondition = "";
    static String winSquares = "";
    static Scanner scanner = new Scanner(System.in);
    static char playerXMark = 'X';
    static char playerOMark = 'O';

    public static void main(String[] args) {
        title();
        display();
        chooseGameMode();
    }

    static void title() {
        System.out.println("*********************************************");
        System.out.println("             Tic-Tac-Toe Game                ");
        System.out.println("*********************************************");
        System.out.println("            Player X vs Player O             ");
        System.out.println("*********************************************\n");
    }

    static void display() {
        System.out.println();
        System.out.println("     |     |     ");
        System.out.println("  " + square[1] + "  |  " + square[2] + "  |  " + square[3] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[4] + "  |  " + square[5] + "  |  " + square[6] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + square[7] + "  |  " + square[8] + "  |  " + square[9] + "  ");
        System.out.println("     |     |     \n");
    }

    static void chooseGameMode() {
        System.out.println("Choose game mode:");
        System.out.println("1. Two Player");
        System.out.println("2. Play against Computer");
        System.out.print("Enter 1 or 2: ");
        int mode = scanner.nextInt();
        scanner.nextLine();

        setCustomMarks();

        if (mode == 1) {
            gameChoice(true);
        } else if (mode == 2) {
            gameChoice(false);
        } else {
            System.out.println("Invalid choice. Exiting game.");
        }
    }

    static void setCustomMarks() {
        System.out.print("Enter custom mark for Player X: ");
        playerXMark = getValidMark();
        System.out.print("Enter custom mark for Player O: ");
        playerOMark = getValidMark();
    }

    static char getValidMark() {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1 && !Character.isWhitespace(input.charAt(0))) {
                return input.charAt(0);
            } else {
                System.out.print("Invalid mark. Please enter a single, non-whitespace character: ");
            }
        }
    }

    static void gameChoice(boolean isTwoPlayer) {
        int choice;
        char xo;
        boolean playerX = true;

        for (int i = 1; i <= 9; ++i) {
            playerX = (i % 2 != 0);
            if (playerX) {
                xo = playerXMark;
                System.out.print("\nPlayer X, enter a number: ");
                choice = scanner.nextInt();
            } else {
                xo = playerOMark;
                if (isTwoPlayer) {
                    System.out.print("\nPlayer O, enter a number: ");
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Computer's turn: ");
                    choice = computerChoice();
                    System.out.println(choice);
                }
            }

            if (choice >= 1 && choice <= 9) {
                if (square[choice] == (char) ('0' + choice)) {
                    square[choice] = xo;
                    display();

                    int result = checkWin();
                    if (result == 1) {
                        System.out.println((playerX ? "Player X" : "Player O") + " wins by " + winCondition + " (" + winSquares + ")!");
                        playAgain();
                        return;
                    } else if (result == -1) {
                        System.out.println("It's a draw!");
                        playAgain();
                        return;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                    --i;
                }
            } else {
                System.out.println("Invalid input. Try again.");
                --i;
            }
        }
    }

    static int computerChoice() {
        Random random = new Random();
        int choice;
        do {
            choice = random.nextInt(9) + 1;
        } while (square[choice] != (char) ('0' + choice));
        return choice;
    }

    static int checkWin() {
        if (square[1] == square[2] && square[2] == square[3]) {
            winCondition = "the top row";
            winSquares = "1, 2, 3";
            return 1;
        }
        if (square[4] == square[5] && square[5] == square[6]) {
            winCondition = "the middle row";
            winSquares = "4, 5, 6";
            return 1;
        }
        if (square[7] == square[8] && square[8] == square[9]) {
            winCondition = "the bottom row";
            winSquares = "7, 8, 9";
            return 1;
        }
        if (square[1] == square[4] && square[4] == square[7]) {
            winCondition = "the first column";
            winSquares = "1, 4, 7";
            return 1;
        }
        if (square[2] == square[5] && square[5] == square[8]) {
            winCondition = "the second column";
            winSquares = "2, 5, 8";
            return 1;
        }
        if (square[3] == square[6] && square[6] == square[9]) {
            winCondition = "the third column";
            winSquares = "3, 6, 9";
            return 1;
        }
        if (square[1] == square[5] && square[5] == square[9]) {
            winCondition = "the diagonal from top left to bottom right";
            winSquares = "1, 5, 9";
            return 1;
        }
        if (square[3] == square[5] && square[5] == square[7]) {
            winCondition = "the diagonal from top right to bottom left";
            winSquares = "3, 5, 7";
            return 1;
        }

        for (int i = 1; i <= 9; i++) {
            if (square[i] == (char) ('0' + i)) {
                return 0;
            }
        }
        return -1;
    }

    static void playAgain() {
        System.out.print("Do you want to play again? (y/n): ");
        char playAgain = scanner.next().charAt(0);
        if (playAgain == 'y' || playAgain == 'Y') {
            resetBoard();
            display();
            chooseGameMode();
        } else {
            System.out.println("Thank you for playing!");
        }
    }

    static void resetBoard() {
        for (int i = 1; i <= 9; i++) {
            square[i] = (char) ('0' + i);
        }
    }
}