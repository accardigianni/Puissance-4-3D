package main.p4_3d.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        // Before each test, create a GameBoard instance with a 4x4x4 grid
        gameBoard = new GameBoard(4, 4, 4);
    }

    @Test
    public void testOutOfZone(){
        // Play out of gameboard
        assertFalse(gameBoard.isMoveValid(4,4,4));
        assertFalse(gameBoard.isMoveValid(0,2,4));
        assertFalse(gameBoard.isMoveValid(4,2,3));
        assertFalse(gameBoard.isMoveValid(2,4,1));
    }

    @Test
    public void testPlayMove() {
        // Test PlayMove function
        assertTrue(gameBoard.isMoveValid(0, 0, 0)); // Box (0, 0, 0) is valid at start
        gameBoard.playMove(0, 0, 0); // Player 1 play in the box (0,0,0)
        assertEquals(1, gameBoard.getCurrentPlayer()); // Check that currentplayer value is 1
        assertFalse(gameBoard.isMoveValid(0, 0, 0)); // Check if the box is empty are already play
        System.out.println("Le joueur change bien l'état de la case sur lequel il joue, il ne peut plus jouer sur la même case dorénanvant");

    }

    @Test
    public void testSwitchPlayer() {
        // Test SwitchPlayer funtion
        assertEquals(1, gameBoard.getCurrentPlayer()); // currentPlayer is "1"
        gameBoard.switchPlayer(); // switch value to "2"
        assertEquals(2, gameBoard.getCurrentPlayer()); // currentPlayer is "2"
        gameBoard.switchPlayer(); // switch value to "1"
        assertEquals(1, gameBoard.getCurrentPlayer()); // currentPlayer is "1"
        System.out.println("Switch player fonctionne");
    }

    @Test /////------------------------------------------------------------
    public void testWhenIsMoveValidIsFalse(){
        assertTrue(gameBoard.isMoveValid(0,0,0));
        gameBoard.playMove(0,0,0);
        assertEquals(1,gameBoard.getCurrentPlayer());
        gameBoard.switchPlayer();
        assertFalse(gameBoard.isMoveValid(0,0,0));
        gameBoard.playMove(0,0,0);
        gameBoard.printBoard();
//        gameBoard.SwitchPlayer();
//        gameBoard.PlayMove(2,0,0);
//        gameBoard.SwitchPlayer();
//        gameBoard.PlayMove(3,0,0);
//        gameBoard.SwitchPlayer();
//        gameBoard.PlayMove(4,0,0);
//       gameBoard.PrintBoard();
    }

    @Test
    public void testCheckInARowIsTrue(){
        // Test si checkInARow compare correctement les valeurs
        assertTrue(gameBoard.checkFourInARow(1,1,1,1));
        System.out.println("la ligne possède les mêmes valeurs et différentes de 0");
    }

// ------- test if Game is win -------

    // -------  classic vertical
    @Test
    public void testVerticalLineLevel0(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(0,1,0);
        gameBoard.playMove(0,2,0);
        gameBoard.playMove(0,3,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 0");
    }

    @Test
    public void testVerticalLineLevel1(){
        gameBoard.playMove(1,3,2);
        gameBoard.playMove(1,2,2);
        gameBoard.playMove(1,1,2);
        gameBoard.playMove(1,0,2);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 1");
    }

    @Test
    public void testVerticalLineLevel2(){
        gameBoard.playMove(2,0,1);
        gameBoard.playMove(2,1,1);
        gameBoard.playMove(2,2,1);
        gameBoard.playMove(2,3,1);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 2");
    }

    @Test
    public void testVerticalLineLevel3(){
        gameBoard.playMove(3,3,3);
        gameBoard.playMove(3,2,3);
        gameBoard.playMove(3,1,3);
        gameBoard.playMove(3,0,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 3");
    }

    // -------- classic horizontal lines
    @Test
    public void testHorizontalLineLevel0(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(0,0,1);
        gameBoard.playMove(0,0,2);
        gameBoard.playMove(0,0,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 0 avec x=0 y=0");
    }

    @Test
    public void testHorizontalLineLevel0WithDifferentCoordinates() {
        gameBoard.playMove(0, 1, 0);
        gameBoard.playMove(0, 1, 1);
        gameBoard.playMove(0, 1, 2);
        gameBoard.playMove(0, 1, 3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Le jeu détecte bien une ligne horizontale au niveau 0 avec x=0 et y=1");
    }

    @Test
    public void testHorizontalLineLevel1(){
        gameBoard.playMove(1,2,3);
        gameBoard.playMove(1,2,2);
        gameBoard.playMove(1,2,1);
        gameBoard.playMove(1,2,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 1 avec x=1 et y=2");
    }

    @Test
    public void testHorizontalLineLevel2(){
        gameBoard.playMove(2,3,0);
        gameBoard.playMove(2,3,1);
        gameBoard.playMove(2,3,2);
        gameBoard.playMove(2,3,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 2 avec x=2 y=3");
    }

    @Test
    public void testHorizontalLineLevel3(){
        gameBoard.playMove(3,1,3);
        gameBoard.playMove(3,1,2);
        gameBoard.playMove(3,1,1);
        gameBoard.playMove(3,1,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 3");
    }

    // ------- classic diagonal lines
    @Test
    public void testDiagonaleLevel0(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(0,1,1);
        gameBoard.playMove(0,2,2);
        gameBoard.playMove(0,3,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les diagonales au niveau 0");
    }

    @Test
    public void testDiagonaleInverseLevel0(){
        gameBoard.playMove(0,3,0);
        gameBoard.playMove(0,2,1);
        gameBoard.playMove(0,1,2);
        gameBoard.playMove(0,0,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 0");
    }

    @Test
    public void testDiagonaleInverseLevel1(){
        gameBoard.playMove(1,3,3);
        gameBoard.playMove(1,2,2);
        gameBoard.playMove(1,1,1);
        gameBoard.playMove(1,0,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 1");
    }

    @Test
    public void testDiagonaleInverseLevel2(){
        gameBoard.playMove(2,3,3);
        gameBoard.playMove(2,2,2);
        gameBoard.playMove(2,1,1);
        gameBoard.playMove(2,0,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 2");
    }

    @Test
    public void testDiagonaleLevel3(){
        gameBoard.playMove(3,0,0);
        gameBoard.playMove(3,1,1);
        gameBoard.playMove(3,2,2);
        gameBoard.playMove(3,3,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les diagonales au niveau 3");
    }

    // ------- 3D lines -------

    // ------- column
    @Test
    public void testColonne3D(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(1,0,0);
        gameBoard.playMove(2,0,0);
        gameBoard.playMove(3,0,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("Nous prenons en charge les colonnes 3D");
    }

    // ------- Horizontale 3D
    @Test
    public void testHorizontalLine3D() {
        gameBoard.playMove(3, 3, 0);
        gameBoard.playMove(2, 3, 1);
        gameBoard.playMove(1, 3, 2);
        gameBoard.playMove(0, 3, 3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=3 z=3 ");
    }

    @Test
    public void testHorizontaleDecreaseLine(){
        gameBoard.playMove(0,2,3);
        gameBoard.playMove(1,2,2);
        gameBoard.playMove(2,2,1);
        gameBoard.playMove(3,2,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=2 z=3");
    }

    @Test
    public void testHorizontaleIncreaseLine(){
        gameBoard.playMove(3,2,3);
        gameBoard.playMove(2,2,2);
        gameBoard.playMove(1,2,1);
        gameBoard.playMove(0,2,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=2 z=3");
    }

    @Test
    public void testHorinzontaleLineIn3D2(){
        gameBoard.playMove(0,1,0);
        gameBoard.playMove(1,1,1);
        gameBoard.playMove(2,1,2);
        gameBoard.playMove(3,1,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 en diagonale montant 3D sur une ligne horizontale qui commence à l'index y=1 z=0");
    }

    @Test
    public void testHorizontalLineInInverse3D(){
        gameBoard.playMove(3,3,3);
        gameBoard.playMove(2,3,2);
        gameBoard.playMove(1,3,1);
        gameBoard.playMove(0,3,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 en diagonale montant 3D sur une ligne horizontal qui commence à l'index y=3 z=0");
    }

    // ------- Verticale 3D
    @Test
    public void testVerticalLineIn3D(){
        gameBoard.playMove(0,0,3);
        gameBoard.playMove(1,1,3);
        gameBoard.playMove(2,2,3);
        gameBoard.playMove(3,3,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 en vertical montant 3D  qui commence à l'index y=0 z=3");
    }

    @Test
    public void testVerticalLineInInverse3D(){
        gameBoard.playMove(3,0,0);
        gameBoard.playMove(2,1,0);
        gameBoard.playMove(1,2,0);
        gameBoard.playMove(0,3,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 en verticale descendant 3D qui commence a l'index y=0 z=0");
    }

    //-------Diagonale 3D
    @Test
    public void testRisingDiagonale3D(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(1,1,1);
        gameBoard.playMove(2,2,2);
        gameBoard.playMove(3,3,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D montante qui commence à l'index x=0 y=0 z=0");
    }
    @Test
    public void testDescendingDiagonale3D(){
        gameBoard.playMove(0,3,3);
        gameBoard.playMove(1,2,2);
        gameBoard.playMove(2,1,1);
        gameBoard.playMove(3,0,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D descendante qui commence à l'index x=0 y=3 z=3");
    }

    @Test
    public void testInverseRisingDiagonale3D(){
        gameBoard.playMove(0,3,0);
        gameBoard.playMove(1,2,1);
        gameBoard.playMove(2,1,2);
        gameBoard.playMove(3,0,3);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D montante qui commence à l'index x=0 y=3 z=0");
    }

    @Test
    public void testInverseDescendingDiagonale3D(){
        gameBoard.playMove(0,0,3);
        gameBoard.playMove(1,1,2);
        gameBoard.playMove(2,2,1);
        gameBoard.playMove(3,3,0);
        assertTrue(gameBoard.gameIsWin());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D descendante qui commence à l'index x=0 y=0 z=3");
    }

    // ------- check if the game continue

    @Test
    public void testNoWinOnALine(){
        gameBoard.playMove(0,0,0);
        gameBoard.playMove(0,0,3);
        gameBoard.playMove(0,1,1);
        gameBoard.playMove(0,1,2);
        assertFalse(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien 4 fois la même valeur mais pas sur le meme axe ");
    }

    @Test
    public void testBadHorizontalLine() {
        gameBoard.playMove(3, 3, 0);
        gameBoard.playMove(3, 3, 1);
        gameBoard.playMove(1, 3, 2);
        gameBoard.playMove(0, 3, 3);
        assertFalse(gameBoard.gameIsWin());
        System.out.println("le jeu détecte bien que la ligne horizontale 3D n'est pas correct");
    }
}
