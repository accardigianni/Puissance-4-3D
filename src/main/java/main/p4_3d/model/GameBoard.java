package main.p4_3d.model;

public class GameBoard {

    private int[][][] board;//  array in 3d
    private int px, py, pz;
    private int currentPlayer;// variable player

    public  GameBoard(int px, int py, int pz){
        this.px = px; // nb de tableau
        this.py = py; // nb de ligne
        this.pz = pz; // nb de colonne
        currentPlayer = 1;
        board = new int[px][py][pz];
        InitializeBoard();
    }

    public void InitializeBoard(){ // init game grid
        // init all case of game grid to 0
        for(int x = 0; x<board.length; x++){
            for (int y = 0; y< board[x].length;y++){
                for (int z = 0;z<board[x][y].length; z++){
                    board[x][y][z] = 0;
                }
            }
        }
    }

    public void SwitchPlayer(){ // switch player after a valid move
        if(currentPlayer == 1){
            currentPlayer = 2;
        }else {
            currentPlayer = 1;
        }
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean IsMoveValid(int px, int py, int pz){ // check if the move is valid or not
        // check coordinate are valid
        if(px < 0 || px >= board.length || py < 0 || py >= board[px].length || pz < 0 || pz >= board[px][py].length){
            return false; // coordinate out of zone
        }

        if(board[px][py][pz] != 0){
            return false; // check if the case is empty ( ==0)
        }

        return true;
    }

    public void PlayMove(int px, int py, int pz){ // change state of case

        if(IsMoveValid(px,py,pz)){
            board[px][py][pz] = currentPlayer;

        }
    }
    public boolean GameIsWin(){
       return CheckLines();
    }

    public boolean CheckLines() {
        // Vérification des lignes horizontales classiques
        for (int x = 0; x < px; x++) {
            for (int y = 0; y < py; y++) {
                // on fait pz-3 pour parcourir une seule fois l'axe d'une colonne
                for (int z = 0; z < pz - 3; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y][z + 1], board[x][y][z + 2], board[x][y][z + 3])) {
                        return true;
                    }
                }
            }
        }

        // Vérification des lignes verticales classiques
        for (int x = 0; x < px; x++) {
            // on fait py - 3 pour parcourir une seule fois l'axe d'une ligne
            for (int y = 0; y < py - 3; y++) {
                for (int z = 0; z < pz; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y + 1][z], board[x][y + 2][z], board[x][y + 3][z])) {
                        return true;
                    }
                }
            }
        }

        // Vérification des lignes diagonales
        for (int x = 0; x < px ; x++) {
            for (int y = 0; y < py - 3; y++) {
                for (int z = 0; z < pz - 3; z++) {
                    if (CheckFourInARow(board[x][y][z], board[x][y + 1][z + 1], board[x][y + 2][z + 2], board[x][y + 3][z + 3])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean CheckFourInARow(int a, int b, int c , int d){
        // vérifie si toute les valeur d'une ligne sont identiques
        return a == b && b == c && c == d && a != 0;

    }
}
