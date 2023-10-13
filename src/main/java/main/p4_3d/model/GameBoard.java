package main.p4_3d.model;

public class GameBoard {

    private int[][][] board;//  array in 3d
    private int px, py, pz;
    private int currentPlayer;// variable player

    public GameBoard(int px, int py, int pz){
        this.px = px; // vertical
        this.py = py; // horizontal
        this.pz = pz; // hauteur
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
        return checkHorizontalLines() || checkVerticaleLines() || checkDiagonalsLines();
//                || checkDiagonalsXZ() || checkDiagonalsYZ();
    }

//

    public boolean checkHorizontalLines() {
        // Vérifie les lignes horizontales
        for (int x = 0; x < px; x++) {
            for (int z = 0; z < pz; z++) {
                for (int y = 0; y < py; y++) {
                    if (checkFourInARow(board[x][y][z], board[x][y][z + 1], board[x][y][z + 2], board[x][y][z + 3])) {
                        return true; // Victoire sur une ligne horizontale
                    }
                }
            }
        }
        return false;
    }

//    public boolean checkVerticaleLines() {
//        // Vérifie les lignes verticales
//        for (int x = 0; x < px; x++) {
//            for (int y = 0; y < py - 3; y++) {
//                for (int z = 0; z < pz; z++) {
//                    if (checkFourInARow(board[x][y][z], board[x][y + 1][z], board[x][y + 2][z], board[x][y + 3][z])) {
//                        return true; // Victoire sur une colonne
//                    }
//                }
//            }
//        }
//        return false;
//    }

//    private boolean checkDiagonalsLines() {
//        // Vérifie les diagonales dans le plan XY (horizontale et verticale)
//        for (int x = 0; x < px - 3; x++) {
//            for (int y = 0; y < py - 3; y++) {
//                for (int z = 0; z < pz; z++) {
//                    if (checkFourInARow(board[x][y][z], board[x + 1][y + 1][z], board[x + 2][y + 2][z], board[x + 3][y + 3][z])) {
//                        return true; // Victoire sur une diagonale dans le plan XY
//                    }
//                }
//            }
//        }
//        return false;
//    }

// Ajoutez les méthodes checkDiagonalsXZ et checkDiagonalsYZ de manière similaire

    private boolean checkFourInARow(int... values) {
        int count = 0; // Compteur de valeurs identiques
        for (int value : values) {
            if (value != 0) { // Si la valeur n'est pas nulle
                if (count == 0) {
                    count = 1; // Démarre le compteur à 1
                } else if (value == values[count - 1]) {
                    count++; // Incrémente le compteur si la valeur est identique à la précédente
                    if (count == 4) {
                        return true; // Séquence de 4 valeurs identiques trouvée
                    }
                } else {
                    count = 1; // Réinitialise le compteur si la séquence est interrompue
                }
            } else {
                count = 0; // Réinitialise le compteur si la valeur est nulle
            }
        }
        return false; // Aucune séquence de 4 valeurs identiques trouvée
    }
}
