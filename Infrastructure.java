package com.company;

import java.util.Scanner;

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