package main.p4_3d.model;

public class GameBoard {

    private int[][][] board;//  array in 3d
    private int px, py, pz;
    private int currentPlayer;// variable player

    public  GameBoard(int px, int py, int pz){
        // create a custom
        this.px = px; // nb of array
        this.py = py; // nb of lines
        this.pz = pz; // nb of columns
        currentPlayer = 1;
        board = new int[px][py][pz];
        initializeBoard();
    }

    public void initializeBoard(){
        // init all case of game grid to 0
        for(int x = 0; x < px; x++){
            for (int y = 0; y < py; y++){
                for (int z = 0; z < pz; z++){
                    board[x][y][z] = 0;
                }
            }
        }
    }

    public void printBoard() {
        // print state of board
        for (int x = 0; x < px; x++) {
            for (int y = 0; y < py; y++) {
                for (int z = 0; z < pz; z++) {
                    System.out.print(board[x][y][z] + " ");
                }
                System.out.println(); // new axe line (axe y)
            }
            System.out.println(); // new axe level (axe x)
        }
    }

    public void switchPlayer(){
        // switch player
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    public int getCurrentPlayer(){
        // return value of currentPlayer
        return currentPlayer;
    }

    public boolean isMoveValid(int px, int py, int pz){
        // check coordinate are valid
        if(px < 0 || px >= board.length || py < 0 || py >= board[px].length || pz < 0 || pz >= board[px][py].length){
            // coordinate out of zone
            return false;
        }
        // if board[px][py][pz] != 0 => false
       return board[px][py][pz] == 0;
    }

    public boolean playMove(int px, int py, int pz){
        // change state of case and deal if a case is already play or out of zone
        if(isMoveValid(px,py,pz)){
            board[px][py][pz] = currentPlayer;
            return true;
        }  else{
            return false;
        }
    }

    public boolean gameIsWin(){
       // check if game is win or not
        if(checkDiagonalLines() || checkColumnLines() || checkHorizontalLines() || checkVerticalLines()){
            return true;
        }
        return false;
    }

    private boolean checkDiagonalLines(){
        // Checking diagonal lines
        for ( int x = 0; x < px ; x++) {
            for (int y = 0; y < py; y++) {
                if (y == 0) {
                    for (int z = 0; z < pz; z++) {
                        if (z == 0) {
                            // Diagonal 3D left to right
                            if (checkFourInARow(board[x-x][y][z], board[x-x+1][y+1][z+1], board[x-x+2][y+2][z+2], board[x-x+3][y+3][z+3])) {
                                return true;
                            }
                            // Diagonal classic left to right
                            if (checkFourInARow(board[x][y][z],board[x][y+1][z+1],board[x][y+2][z+2],board[x][y+3][z+3])){
                                return true;
                            }
                        }
                        if (z == 3) {
                            // Diagonal 3D right to left
                            if (checkFourInARow(board[x-x][y][z], board[x-x+1][y+1][z-1], board[x-x+2][y+2][z-2], board[x-x+3][y+3][z-3])) {
                                return true;
                            }
                            // Diagonal classic right to left
                            if (checkFourInARow(board[x][y][z],board[x][y+1][z-1],board[x][y+2][z-2],board[x][y+3][z-3])){
                                return true;
                            }
                        }
                    }
                }
                // Reverse Diagonal
                if(y == 3){
                    for (int z = 0; z < pz; z++){
                        if(z == 0){
                            // Reverse Diagonal 3D left to right
                            if (checkFourInARow(board[x-x][y][z],board[x-x+1][y-1][z+1],board[x-x+2][y-2][z+2],board[x-x+3][y-3][z+3])){
                                return true;
                            }
                            // Reverse Diagonal classic left to right
                            if(checkFourInARow(board[x][y][z],board[x][y-1][z+1],board[x][y-2][z+2],board[x][y-3][z+3])){
                                return true;
                            }
                        }
                        if(z == 3){
                            // Reverse Diagonal 3D right to left
                            if (checkFourInARow(board[x-x][y][z],board[x-x+1][y-1][z-1],board[x-x+2][y-2][z-2],board[x-x+3][y-3][z-3])){
                                return true;
                            }
                            // Reverse Diagonal 3D right to left
                            if(checkFourInARow(board[x][y][z],board[x][y-1][z-1],board[x][y-2][z-2],board[x][y-3][z-3])){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkColumnLines(){
        // Checking 3D columns
        for (int x = 0; x < px - 3; x++) {
            for (int y = 0; y < py ; y++) {
                for (int z = 0; z < pz ; z++) {
                    if (checkFourInARow(board[x][y][z], board[x+1][y][z], board[x+2][y][z], board[x+3][y][z])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalLines(){
        // Horizontal lines
        for (int x = 0; x < px; x++) {
            for (int y = 0; y < py; y++) {
                for (int z = 0; z < pz -3; z++) {
                    // Horizontal 3D left to right
                    if (checkFourInARow(board[x-x][y][z],board[x-x+1][y][z+1],board[x-x+2][y][z+2],board[x-x+3][y][z+3])){
                        return true;
                    }
                    // Horizontal 3D right to left
                    if(checkFourInARow(board[x-x+3][y][z],board[x-x+2][y][z+1],board[x-x+1][y][z+2],board[x-x][y][z+3])){
                        return true;
                    }
                    // Checking classic horizontal lines
                    if (checkFourInARow(board[x][y][z], board[x][y][z+1], board[x][y][z+2], board[x][y][z+3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkVerticalLines(){
        // Vertical lines
        for (int x = 0; x < px ; x++){
            for ( int y = 0; y < py -3 ; y++){
                for ( int z = 0 ; z < pz; z++){
                    // Vertical 3D up to down
                    if (checkFourInARow(board[x-x][y][z], board[x-x+1][y+1][z],board[x-x+2][y+2][z],board[x-x+3][y+3][z])){
                        return true;
                    }
                    // Vertical 3D down to up
                    if(checkFourInARow(board[x-x+3][y][z],board[x-x+2][y+1][z],board[x-x+1][y+2][z],board[x-x][y+3][z])){
                        return true;
                    }
                    // Checking classic vertical lines
                    if (checkFourInARow(board[x][y][z], board[x][y+1][z], board[x][y+2][z], board[x][y+3][z])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkFourInARow(int a, int b, int c , int d){
        // checks if all line values are identical
        return a == b && b == c && c == d && a != 0;
    }
}
