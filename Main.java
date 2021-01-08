package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final Main game = new Main();
    public static final Scanner userInp = new Scanner(System.in);

    private void printBoard(String[][] board){
        for (String[] row: board) {
            System.out.println("| " + row[0] + " | " + row[1] + " | " + row[2] +  " |");
        }
    }

    private void printHeader(String str){
        System.out.println("==========================");
        System.out.println(str);
        System.out.println("==========================");
    }

    private void tutorial(){
        String[][] fakeBoard = {{"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"}};
        System.out.println("Enter the number corresponding to the position to enter your move, when asked.");
        System.out.println("The board is laid out like below:");
        game.printBoard(fakeBoard);
        System.out.println("E.g. if you want to place your move on square 9, type 9");
        System.out.println("==========================");
    }

    private String[][] startGame(){
        System.out.println("==========================");
        game.printHeader("WELCOME TO TIC TAC TOE");
        game.printHeader("Made by Yours truly");
        System.out.println("Tutorial? y/n");
        String ans = userInp.nextLine();
        if(ans.equals("y")){
            game.printHeader("TUTORIAL");
            game.tutorial();
        } else if (!ans.equals("n")){
            game.printHeader("Holy shit, ur dumb");
            System.exit(-1);
        }
        game.printHeader("GAME STARTING");
        return new String[][]{{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    }

    private boolean validateSpace(String board, String move, String inverse){
        return !board.equals(move) && !board.equals(inverse);
    }
    // I hate this bit, maybe an easier way to do this?
    private int[] generateLocation(int move){
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
        System.out.println(place);
        int[] location = game.generateLocation(ans);
        if(!game.validateSpace(board[location[0]][location[1]], place, inverse)){
            game.printHeader("Space already taken, try again buddy");
            game.printBoard(game.inputUserMove(board, isPlayerOne));
            return board;
        }
        board[location[0]][location[1]] = place;
        return board;
    }

    public static void main(String[] args) {
        String[][] board = game.startGame();
        System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < 5; i++) {
            System.out.println("Player 1's turn");
            game.printBoard(game.inputUserMove(board, true));
            System.out.println("Player 2's turn");
            game.printBoard(game.inputUserMove(board, false));
        }
    }
}
