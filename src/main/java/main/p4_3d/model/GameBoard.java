package main.p4_3d.model;

public class GameBoard {

    private int[][][] board;//  array in 3d
    private int px, py, pz;
    private int currentPlayer;// variable player

    public  GameBoard(int px, int py, int pz){
        // create a custom
        this.px = px; // nb de tableau
        this.py = py; // nb de ligne
        this.pz = pz; // nb de colonne
        currentPlayer = 1;
        board = new int[px][py][pz];
        InitializeBoard();
    }

    public void InitializeBoard(){
        // init all case of game grid to 0
        for(int x = 0; x<board.length; x++){
            for (int y = 0; y< board[x].length;y++){
                for (int z = 0;z<board[x][y].length; z++){
                    board[x][y][z] = 0;
                }
            }
        }
    }

    public void PrintBoard() {
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

    public void SwitchPlayer(){
        // switch player
        if(currentPlayer == 1){
            currentPlayer = 2;
        }else {
            currentPlayer = 1;
        }
    }

    public int getCurrentPlayer(){
        // return value of currentPlayer
        return currentPlayer;
    }

    public boolean IsMoveValid(int px, int py, int pz){
        // check if the move is valid or not
        // check coordinate are valid
        if(px < 0 || px >= board.length || py < 0 || py >= board[px].length || pz < 0 || pz >= board[px][py].length){
            // coordinate out of zone
            return false;
        }

        if(board[px][py][pz] != 0){
            return false;
        }

        return true;
    }

    public void PlayMove(int px, int py, int pz){ // change state of case

        if(IsMoveValid(px,py,pz)){
            board[px][py][pz] = currentPlayer;

        }
    }

    public boolean GameIsWin(){
       // check if game is win or not
        return CheckLines();
    }

    public boolean CheckLines() {

        // Checking classic horizontal lines
        for (int x = 0; x < px; x++) {
            for (int y = 0; y < py; y++) {
                // do pz-3 to traverse a column axis once only
                for (int z = 0; z < pz - 3; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y][z+1], board[x][y][z+2], board[x][y][z+3])) {
                        return true;
                    }
                }
            }
        }

        // 3D rising horizontal lines
        for (int x = 0; x < px - 3; x++) {
            for (int y = 0; y < py; y++) {
                for ( int z = 0; z < pz - 3; z++) {
                    if (CheckFourInARow(board[x][y][z],board[x+1][y][z+1],board[x+2][y][z+2],board[x+3][y][z+3])){
                        return true;
                    }
                }
            }
        }

        // 3D descending horizontal lines
        for (int x = 3; x < px; x++) {
            for (int y = 0; y < py; y++) {
                for ( int z = 3; z < pz; z++) {
                    if (CheckFourInARow(board[x][y][z-3],board[x-1][y][z-2],board[x-2][y][z-1],board[x-3][y][z])){
                        return true;
                    }
                }
            }
        }

        // Checking classic vertical lines
        for (int x = 0; x < px; x++) {
            // on fait py - 3 pour parcourir une seule fois l'axe d'une ligne
            for (int y = 0; y < py - 3; y++) {
                for (int z = 0; z < pz; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y+1][z], board[x][y+2][z], board[x][y+3][z])) {
                        return true;
                    }
                }
            }
        }

        //3D rising vertical lines
        for (int x = 0; x < px - 3; x++){
            for ( int y = 3; y < py; y++){
                for ( int z = 0 ; z < pz; z++){
                    if (CheckFourInARow(board[x][y][z], board[x+1][y-1][z],board[x+2][y-2][z],board[x+3][y-3][z])){
                        return true;
                    }
                }
            }
        }

        // 3D vertical descending lines
        for (int x = 3; x < px; x++){
            for ( int y = 3; y < py; y++){
                for ( int z = 0 ; z < pz; z++){
                    if (CheckFourInARow(board[x][y][z], board[x-1][y-1][z],board[x-2][y-2][z],board[x-3][y-3][z])){
                        return true;
                    }
                }
            }
        }

        // Checking classic diagonal lines
        for (int x = 0; x < px; x++) {
            for (int y = 0; y < py - 3; y++) {
                for (int z = 0; z < pz - 3; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y+1][z+1], board[x][y+2][z+2], board[x][y+3][z+3])) {
                        return true;
                    }
                }
            }
        }

        //3D diagonal lines
        for ( int x = 0; x < px - 3; x++) {
            for (int y = 0; y < py; y++) {
                if (y == 0) {
                    for (int z = 0; z < pz; z++) { // 2 if z=0 z=3
                        if (z == 0) {
                            if (CheckFourInARow(board[x][y][z], board[x+1][y+1][z+1], board[x+2][y+2][z+2], board[x+3][y+3][z+3])) {
                                return true;
                            }
                        }
                        if (z == 3) {
                            if (CheckFourInARow(board[x][y][z], board[x+1][y+1][z-1], board[x+2][y+2][z-2], board[x+3][y+3][z-3])) {
                                return true;
                            }
                        }
                    }
                }
                if(y==3){
                    for (int z = 0; z < pz; z++){
                        if(z==0){
                            if (CheckFourInARow(board[x][y][z],board[x+1][y-1][z+1],board[x+2][y-2][z+2],board[x+3][y-3][z+3])){
                                return true;
                            }
                        }
                        if(z==3){
                            if (CheckFourInARow(board[x][y][z],board[x+1][y-1][z-1],board[x+2][y-2][z-2],board[x+3][y-3][z-3])){
                                return true;
                            }
                        }
                    }
                }
            }
        }

        // 3D diagonal descending lines


        // Checking 3D columns
        for (int x = 0; x < px - 3; x++) {
            for (int y = 0; y < py ; y++) {
                for (int z = 0; z < pz ; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x + 1][y][z], board[x + 2][y][z], board[x + 3][y][z])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean CheckFourInARow(int a, int b, int c , int d){
        // checks if all line values are identical
        return a == b && b == c && c == d && a != 0;
    }
}
