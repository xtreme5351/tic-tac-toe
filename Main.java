package com.company;

// import java.util.Arrays;
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

    private String[][] inputUserMove(String[][] board, int playerNo){
        System.out.println("Enter your move: ");
        int ans = userInp.nextInt();
        String place;
        if (playerNo == 1){
            place = "X";
        } else {
            place = "O";
        }
        switch (ans) {
            case 0 -> board[0][0] = place;
            case 1 -> board[0][1] = place;
            case 2 -> board[0][2] = place;
            case 3 -> board[1][0] = place;
            case 4 -> board[1][1] = place;
            case 5 -> board[1][2] = place;
            case 6 -> board[2][0] = place;
            case 7 -> board[2][1] = place;
            case 8 -> board[2][2] = place;
            default -> System.out.println("=== INVALID MOVE ===");
        }
        return board;
    }

    public static void main(String[] args) {
        String[][] firstBoard = game.inputUserMove(game.startGame(), 1);
        game.printBoard(firstBoard);
        game.printBoard(game.inputUserMove(firstBoard, 2));
    }
}
