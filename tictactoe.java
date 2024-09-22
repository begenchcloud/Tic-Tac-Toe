import java.util.Scanner;

public class tictactoe {

    static char[] square = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static String winCondition = "";
    static String winSquares = "";

    public static void main(String[] args) {
        title();
        display();

        gameChoice();
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
        System.out.println("     |     |     ");
    }

    static void gameChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        char xo;
        boolean playerX = true;

        for (int i = 1; i <= 9; ++i) {
            playerX = (i % 2 != 0);
            if (playerX) {
                xo = 'X';
                System.out.print("\nPlayer X, enter a number: ");
            } else {
                xo = 'O';
                System.out.print("\nPlayer O, enter a number: ");
            }

            choice = scanner.nextInt();

            if (choice >= 1 && choice <= 9) {
                if (square[choice] == (char) ('0' + choice)) {
                    square[choice] = xo;
                    display();

                    int result = checkWin();
                    if (result == 1) {
                        System.out.println((playerX ? "Player X" : "Player O") + " wins by " + winCondition + " (" + winSquares + ")!");
                        return;
                    } else if (result == -1) {
                        System.out.println("It's a draw!");
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

        boolean draw = true;
        for (int i = 1; i <= 9; i++) {
            if (square[i] == (char) ('0' + i)) {
                draw = false;
                break;
            }
        }
        if (draw) return -1;

        return 0;
    }
    
}
