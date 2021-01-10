package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final Main game = new Main();
    public static final Infrastructure setup = new Infrastructure();
    public static final Scanner userInp = new Scanner(System.in);

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
        int[] location = setup.generateLocation(ans);
        if(!game.validateSpace(board[location[0]][location[1]], place, inverse)){
            setup.printHeader("Space already taken, try again buddy");
            return game.inputUserMove(board, isPlayerOne);
        }
        board[location[0]][location[1]] = place;
        return board;
    }

    private void discoverPlaces(String[][] board){
        int whiteSpaces = 9;
        for (String[] row : board) {
            for (String element: row){
                if (!element.equals(" ")){
                    whiteSpaces -= 1;
                }
            }
        }
        if(whiteSpaces < 2){
            setup.printHeader("No more places remaining");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        String[][] board = setup.startGame();
        for (int i = 0; i < 5; i++) {
            System.out.println("Player 1's turn");
            game.printBoard(game.inputUserMove(board, true));
            System.out.println("Player 2's turn");
            game.printBoard(game.inputUserMove(board, false));
            game.discoverPlaces(board);
        }
        System.out.println(Arrays.deepToString(board));
    }
}

class Infrastructure {
    public static final Scanner inp = new Scanner(System.in);
    public static final Main game = new Main();
    public static final Infrastructure inf = new Infrastructure();

    public int[] generateLocation(int move){
        // I still hate this .____.
        switch(move){
            case 0 -> {
                return new int[] {0, 0};
            }
            case 1 -> {
                return new int[] {0, 1};
            }
            case 2 -> {
                return new int[] {0, 2};
            }
            case 3 -> {
                return new int[] {1, 0};
            }
            case 4 -> {
                return new int[] {1, 1};
            }
            case 5 -> {
                return new int[] {1, 2};
            }
            case 6 -> {
                return new int[] {2, 0};
            }
            case 7 -> {
                return new int[] {2, 1};
            }
            case 8 -> {
                return new int[] {2, 2};
            }
        }
        return new int[] {-1, -1};
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
