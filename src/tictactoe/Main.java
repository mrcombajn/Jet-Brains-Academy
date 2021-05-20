package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String coordinatesY;
        String coordinatesX;
        char[][] matrix = createMatrix();
        char sign = 'X';
        showMatrix(matrix);


        boolean isCellOccupied;
        boolean gameWinned = false;
        do {
            System.out.print("Enter the coordinates: ");
            coordinatesY = scanner.next();
            coordinatesX = scanner.next();

            try {
                int y = Integer.parseInt(coordinatesY);
                int x = Integer.parseInt(coordinatesX);
                if(x <= 3 && x >= 1
                        && y <= 3 && y >= 1) {

                    isCellOccupied = checkFieldInMatrix(matrix, y - 1, x -1, sign);

                    if(!isCellOccupied) {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                    else {
                        sign = sign == 'X' ? 'O' : 'X';
                        showMatrix(matrix);

                        int gameStatus = checkMatrix(matrix);
                        if(gameStatus == 1) {
                            System.out.println("X wins");
                            gameWinned = true;
                        }
                        else if(gameStatus == 2) {
                            System.out.println("O wins");
                            gameWinned = true;
                        }
                        else if(gameStatus == 0) {
                            System.out.println("Draw");
                            gameWinned = true;
                        }
                    }

                }
                else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch(Exception e) {
                System.out.println("You should enter numbers!");
            }

        }while(!gameWinned);
    }

    /**
     * @param matrix game matrix
     * @return -1 for impossible
     *         0 for draw
     *         1 for X win
     *         2 for O win
     *         3 for Game is not finished
     */
    public static int checkMatrix(char[][] matrix) {
        int numberOfXs = 0;
        int numberOfOs = 0;
        boolean xWins = false;
        boolean oWins = false;

        for(char[] rows : matrix) {
            for(char value : rows) {
                if(value == 'X') {
                    numberOfXs++;
                }
                else if(value == 'O') {
                    numberOfOs++;
                }
            }
        }

        for(int i = 0; i < 3; i++) {
            int colSum = 0;
            colSum += matrix[0][i];
            colSum += matrix[1][i];
            colSum += matrix[2][i];
            if(colSum == 264) {
                xWins = true;
            } else if(colSum == 237) {
                oWins = true;
            }
        }

        for(int i = 0; i < 3; i++) {
            int rowSum = 0;
            rowSum += matrix[i][0];
            rowSum += matrix[i][1];
            rowSum += matrix[i][2];
            if(rowSum == 264) {
                xWins = true;
            } else if(rowSum == 237) {
                oWins = true;
            }
        }

        int leftDiagonal = matrix[0][0] + matrix[1][1] + matrix[2][2];
        int rightDiagonal = matrix[0][2] + matrix[1][1] + matrix[2][0];

        if(leftDiagonal == 264 || rightDiagonal == 264) {
            xWins = true;
        }
        else if(leftDiagonal == 237 || rightDiagonal == 237) {
            oWins = true;
        }


        if(Math.abs(numberOfXs - numberOfOs) > 1 || (xWins && oWins)) {
            return -1;
        }
        else if(xWins) {
            return 1;
        }
        else if(oWins) {
            return 2;
        }
        else if(numberOfXs + numberOfOs == 9) {
            return 0;
        }

        return 3;
    }

    public static char[][] createMatrix() {
        char[][] matrix = new char[3][3];
        for(char[] rows : matrix) {
            Arrays.fill(rows, ' ');
        }
        return matrix;
    }

    public static void showMatrix(char[][] matrix) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", matrix[0][0], matrix[0][1], matrix[0][2]);
        System.out.printf("| %c %c %c |\n", matrix[1][0], matrix[1][1], matrix[1][2]);
        System.out.printf("| %c %c %c |\n", matrix[2][0], matrix[2][1], matrix[2][2]);
        System.out.println("---------");
    }

    public static boolean checkFieldInMatrix(char[][] matrix, int coordinatesY, int coordinatesX, char sign) {
        if(matrix[coordinatesY][coordinatesX] == ' ') {
            matrix[coordinatesY][coordinatesX] = sign;
            return true;
        } else {
            return false;
        }
    }
}
