package com.company;

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