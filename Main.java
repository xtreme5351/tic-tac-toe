package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Main game = new Main();
    private static final Infrastructure setup = new Infrastructure();
    private static final Scanner userInp = new Scanner(System.in);
    private static final Logic gameLogic = new Logic();

    public void printBoard(String[][] board){
        for (String[] row: board) {
            System.out.println("| " + row[0] + " | " + row[1] + " | " + row[2] +  " |");
        }
    }

    private boolean validateSpace(String board, String move, String inverse){
        return !board.equals(move) && !board.equals(inverse);
    }

    private String[][] inputUserMove(String[][] board, boolean isPlayerOne){
        System.out.println("Enter your move: ");
        int ans = userInp.nextInt();
        String place;
        String inverse;
        if (isPlayerOne){
            place = "O";
            inverse = "X";
        } else {
            place = "X";
            inverse = "O";
        }
        int[] location = setup.generateLocation(board, ans);
        if(!game.validateSpace(board[location[0]][location[1]], place, inverse)){
            setup.printHeader("Space already taken, try again buddy");
            return game.inputUserMove(board, isPlayerOne);
        }
        board[location[0]][location[1]] = place;
        return board;
    }

    private void checkLogic(Object[] logic){
        boolean player = (boolean) logic[0];
        String token = (String) logic[1];
        if(player && token.equals("Draw")){
            setup.printHeader("ITS A DRAW");
            System.exit(1);
        } else if (player && !token.equals(".")){
            setup.printHeader("PLAYER " + token + " WINS");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        String[][] board = setup.startGame();
        for (int i = 0; i < 5; i++) {
            System.out.println("Player 1's turn");
            game.printBoard(game.inputUserMove(board, true));
            game.checkLogic(gameLogic.performLogic(board));
            System.out.println("Player 2's turn");
            game.printBoard(game.inputUserMove(board, false));
            game.checkLogic(gameLogic.performLogic(board));
        }
        System.out.println(Arrays.deepToString(board));
    }
}

class Logic {
    private static final Logic logic = new Logic();
    private static final Infrastructure inf= new Infrastructure();

    private Object[] discoverPlaces(String[][] board){
        int whiteSpaces = 9;
        for (String[] row : board) {
            for (String element: row){
                if (!element.equals(" ")){
                    whiteSpaces -= 1;
                }
            }
        }
        if(whiteSpaces < 2){
            inf.printHeader("No more places");
            return new Object[] {true, "Draw"};
        }
        return new Object[] {false, "."};
    }

    private Object[] checkXY(String[][] board){
        int sameChar = 0;
        for (int horizontal = 0; horizontal < board[0].length; horizontal++){
            String columnToken = board[0][horizontal];
            String verticalToken = board[horizontal][0];
            if(columnToken.equals(board[1][horizontal]) && columnToken.equals(board[2][horizontal]) && !columnToken.equals(" ")){
                sameChar += 1;
            }
            if(verticalToken.equals(board[horizontal][1]) && verticalToken.equals(board[horizontal][2]) && !verticalToken.equals(" ")){
                sameChar += 1;
            }
            if (sameChar == 1){
                return new Object[] {true, columnToken};
            }
        }
        return new Object[] {false, "."};
    }

    private Object[] checkDiagonals(String[][] board){
        String current = board[0][0];
        int leftCounter = 0;
        // left downward diagonal
        for (int i = 0; i < 3; i++){
            String c = board[i][i];
            if (c.equals(current)){
                leftCounter += 1;
            }
        }
        if (leftCounter == 3 && !current.equals(" ")){
            return new Object[] {true, current};
        }
        // right upward diagonal
        int rightCounter = 0;
        for (int j = 0; j < 3; j ++){
            if (j == 0){
                current = board[0][2];
            }
            String d = board[j][2 - j];
            if (d.equals(current)){
                rightCounter += 1;
            }
        }
        if (rightCounter == 3 && !current.equals(" ")){
            return new Object[] {true, current};
        }
        return new Object[] {false, "."};
    }

    public Object[] performLogic(String[][] gameBoard){
        // Order is Diagonal, Column, Row, Spaces
        Object[][] checked = {{false, "."}, {false, "."}, {false, "."}};
        checked[0] = logic.checkDiagonals(gameBoard);
        checked[1] = logic.checkXY(gameBoard);
        checked[2] = logic.discoverPlaces(gameBoard);
        for(int i = 0; i < 3; i++){
            if((Boolean) checked[i][0]){
                return checked[i];
            }
        }
        return new Object[] {false, "."};
    }
}

class Infrastructure {
    public static final Scanner inp = new Scanner(System.in);
    public static final Main game = new Main();
    public static final Infrastructure inf = new Infrastructure();

    public int[] generateLocation(String[][] board, int move){
        // ty dad :p
        final int divisor = board.length;
        int x = move / divisor;
        int y = move % divisor;
        // System.out.println(divisor+ "|" + x + "|" + y);
        return new int[] {x, y};
    }

    public void printHeader(String str){
        System.out.println("==========================");
        System.out.println(str);
        System.out.println("==========================");
    }

    public void tutorial(){
        String[][] fakeBoard = {{"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"}};
        System.out.println("Enter the number corresponding to the position to enter your move, when asked.");
        System.out.println("The board is laid out like below:");
        game.printBoard(fakeBoard);
        System.out.println("E.g. if you want to place your move on square 9, type 9");
        System.out.println("==========================");
    }

    public String[][] startGame(){
        System.out.println("==========================");
        inf.printHeader("WELCOME TO TIC TAC TOE");
        inf.printHeader("Made by Yours truly");
        System.out.println("Tutorial? y/n");
        String ans = inp.nextLine();
        if(ans.equals("y")){
            inf.printHeader("TUTORIAL");
            inf.tutorial();
        } else if (!ans.equals("n")){
            inf.printHeader("Holy shit, ur dumb");
            System.exit(-1);
        }
        inf.printHeader("GAME STARTING");
        return new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    }
}
