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
