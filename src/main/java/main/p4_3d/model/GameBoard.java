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

    public boolean PlayMove(int px, int py, int pz){ // change state of case and deal if a case is already play or out of zone

        if(IsMoveValid(px,py,pz)){
            board[px][py][pz] = currentPlayer;
            return true;
        } else if (!IsMoveValid(px,py,pz)) {
            board[px+1][py][pz]= currentPlayer;
            return true;
        } else{
            System.out.println("Vous ne pouvez plus jouer sur cette colonne.");
            return false;
        }
    }

    public boolean GameIsWin(){
       // check if game is win or not
        if(CheckLines()){
            System.out.println("Félicitation joueur n°"+getCurrentPlayer());
            return true;
        }
        return false;
    }

    public boolean CheckLines() {

            // Checking classic lines in all array
            for (int x = 0; x < px; x++) {
                for (int y = 0; y < py; y++) {
                    if(y==0){
                        // Checking classic vertical lines
                        for (int z = 0; z < pz; z++) {
                            if (CheckFourInARow(board[x][y][z], board[x][y+1][z], board[x][y+2][z], board[x][y+3][z])) {
                                return true;
                            }
                        }
                    }
                    // Checking classic horizontal lines
                    for (int z = 0; z < pz - 3; z++) {
                        if (CheckFourInARow(board[x][y][z], board[x][y][z+1], board[x][y][z+2], board[x][y][z+3])) {
                            return true;
                        }
                    }
                }
            }


            // 3D Horizontal lines
            for (int x = 0; x < px -3; x++) {
                for (int y = 0; y < py; y++) {
                    for (int z = 0; z < pz -3; z++) {
                        // Horizontal 3D left to right
                        if (CheckFourInARow(board[x][y][z],board[x+1][y][z+1],board[x+2][y][z+2],board[x+3][y][z+3])){
                            return true;
                        }
                        // Horizontal 3D right to left
                        if(CheckFourInARow(board[x+3][y][z],board[x+2][y][z+1],board[x+1][y][z+2],board[x][y][z+3])){
                            return true;
                        }
                    }
                }
            }

            // 3D vertical lines
            for (int x = 0; x < px -3 ; x++){
                for ( int y = 0; y < py -3 ; y++){
                    for ( int z = 0 ; z < pz; z++){
                        // Vertical 3D up to down
                        if (CheckFourInARow(board[x][y][z], board[x+1][y+1][z],board[x+2][y+2][z],board[x+3][y+3][z])){
                            return true;
                        }
                        // Vertical 3D down to up
                        if(CheckFourInARow(board[x+3][y][z],board[x+2][y+1][z],board[x+1][y+2][z],board[x][y+3][z])){
                            return true;
                        }
                    }
                }
            }


            // Checking diagonal lines
            for ( int x = 0; x < px ; x++) {
                for (int y = 0; y < py; y++) {
                    if (y == 0) {
                        for (int z = 0; z < pz; z++) {
                            if (z == 0) {
                                // Diagonal 3D left to right
                                if (CheckFourInARow(board[x-x][y][z], board[x-x+1][y+1][z+1], board[x-x+2][y+2][z+2], board[x-x+3][y+3][z+3])) {
                                    return true;
                                }
                                // Diagonal classic left to right x=0
                                if (CheckFourInARow(board[x-x][y][z],board[x-x][y+1][z+1],board[x-x][y+2][z+2],board[x-x][y+3][z+3])){
                                    return true;
                                }
                                // Diagonal classic left to right x!=0
                                if (CheckFourInARow(board[x][y][z],board[x][y+1][z+1],board[x][y+2][z+2],board[x][y+3][z+3])){
                                    return true;
                                }
                            }

                            if (z == 3) {
                                // Diagonal 3D right to left
                                if (CheckFourInARow(board[x-x][y][z], board[x-x+1][y+1][z-1], board[x-x+2][y+2][z-2], board[x-x+3][y+3][z-3])) {
                                    return true;
                                }
                                // Diagonal classic right to left x=0
                                if (CheckFourInARow(board[x-x][y][z],board[x-x][y+1][z-1],board[x-x][y+2][z-2],board[x-x][y+3][z-3])){
                                    return true;
                                }
                                // Diagonal classic right to left x!=0
                                if (CheckFourInARow(board[x][y][z],board[x][y+1][z-1],board[x][y+2][z-2],board[x][y+3][z-3])){
                                    return true;
                                }
                            }
                        }
                    }
                    // Reverse Diagonal 3D
                    if(y == 3){
                        for (int z = 0; z < pz; z++){
                            if(z == 0){
                                // Reverse Diagonal 3D left to right
                                if (CheckFourInARow(board[x-x][y][z],board[x-x+1][y-1][z+1],board[x-x+2][y-2][z+2],board[x-x+3][y-3][z+3])){
                                    return true;
                                }
                                // Reverse Diagonal classic left to right x=0
                                if(CheckFourInARow(board[x-x][y][z],board[x-x][y-1][z+1],board[x-x][y-2][z+2],board[x-x][y-3][z+3])){
                                    return true;
                                }
                                // Reverse Diagonal classic left to right x!=0
                                if(CheckFourInARow(board[x][y][z],board[x][y-1][z+1],board[x][y-2][z+2],board[x][y-3][z+3])){
                                    return true;
                                }
                            }
                            if(z == 3){
                                // Reverse Diagonal 3D right to left
                                if (CheckFourInARow(board[x-x][y][z],board[x-x+1][y-1][z-1],board[x-x+2][y-2][z-2],board[x-x+3][y-3][z-3])){
                                    return true;
                                }
                                // Reverse Diagonal 3D right to left x=0
                                if(CheckFourInARow(board[x-x][y][z],board[x-x][y-1][z-1],board[x-x][y-2][z-2],board[x-x][y-3][z-3])){
                                    return true;
                                }
                                // Reverse Diagonal 3D right to left x!=0
                                if(CheckFourInARow(board[x][y][z],board[x][y-1][z-1],board[x][y-2][z-2],board[x][y-3][z-3])){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

            // Checking 3D columns
            for (int x = 0; x < px - 3; x++) {
                for (int y = 0; y < py ; y++) {
                    for (int z = 0; z < pz ; z++) {
                        if (CheckFourInARow(board[x][y][z], board[x+1][y][z], board[x+2][y][z], board[x+3][y][z])) {
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
