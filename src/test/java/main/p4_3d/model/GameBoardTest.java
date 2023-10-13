package main.p4_3d.model;

import main.p4_3d.model.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        // Avant chaque test, crée une instance de GameBoard avec une grille 4x4x4
        gameBoard = new GameBoard(4, 4, 4);
    }

    @Test
    public void testPlayMove() {
        // Teste si PlayMove fonctionne correctement
        assertTrue(gameBoard.IsMoveValid(0, 0, 0)); // La case (0, 0, 0) est valide au début
        gameBoard.PlayMove(0, 0, 0); // Le joueur 1 joue dans la case (0, 0, 0)
        assertEquals(1, gameBoard.getCurrentPlayer()); // Vérifie que le joueur actuel est maintenant le joueur 2
        assertFalse(gameBoard.IsMoveValid(0, 0, 0)); // La case (0, 0, 0) n'est plus valide après avoir été jouée
        System.out.println("Le joueur change bien l'état de la case sur lequel il joue, il ne peut plus jouer dessus dorénanvant");
    }

    @Test
    public void testSwitchPlayer() {
        // Teste si SwitchPlayer fonctionne correctement
        assertEquals(1, gameBoard.getCurrentPlayer()); // Le joueur actuel est le joueur 1 au début
        gameBoard.SwitchPlayer(); // Passe au joueur 2
        assertEquals(2, gameBoard.getCurrentPlayer()); // Le joueur actuel est maintenant le joueur 2
        gameBoard.SwitchPlayer(); // Passe de nouveau au joueur 1
        assertEquals(1, gameBoard.getCurrentPlayer()); // Le joueur actuel est de retour au joueur 1
        System.out.println("Le joueur change après chaque coup joué");
    }

    @Test
    public void testIsMoveInvalidWhenAlreadyPlayed() {
        // Teste si IsMoveValid détecte correctement une case déjà jouée
        assertTrue(gameBoard.IsMoveValid(0, 0, 0)); // La case (0, 0, 0) est valide au début
        gameBoard.PlayMove(0, 0, 0); // Le joueur 1 joue dans la case (0, 0, 0)
        assertFalse(gameBoard.IsMoveValid(0, 0, 0)); // La case (0, 0, 0) n'est plus valide après avoir été jouée
        System.out.println("Un joueur ne peux pas jouer sur une case déjà jouée");
    }
    @Test
    public void testHorizontalWinXIs0() {
        gameBoard.PlayMove(0, 0, 0);
        gameBoard.PlayMove(0, 1, 0);
        gameBoard.PlayMove(0, 2, 0);
        gameBoard.PlayMove(0, 3, 0);
        assertTrue(gameBoard.checkHorizontalLines());
        System.out.println("ligne horizontale X=0 au Z=0 est OK");
    }

    @Test
    public void testHorizontalWinXIs1() {
        gameBoard.PlayMove(1, 0, 0);
        gameBoard.PlayMove(1, 1, 0);
        gameBoard.PlayMove(1, 2, 0);
        gameBoard.PlayMove(1, 3, 0);
        assertTrue(gameBoard.checkHorizontalLines());
        System.out.println("ligne horizontale X=1 au Z=0 est OK");
    }

    @Test
    public void testHorizontalWinXIs2() {
        gameBoard.PlayMove(2, 0, 0);
        gameBoard.PlayMove(2, 1, 0);
        gameBoard.PlayMove(2, 2, 0);
        gameBoard.PlayMove(2, 3, 0);
        assertTrue(gameBoard.checkHorizontalLines());
        System.out.println("ligne horizontale X=2 au Z=0 est OK");
    }

    @Test
    public void testHorizontalWinXIs3() {
        gameBoard.PlayMove(3, 0, 0);
        gameBoard.PlayMove(3, 1, 0);
        gameBoard.PlayMove(3, 2, 0);
        gameBoard.PlayMove(3, 3, 0);
        assertTrue(gameBoard.checkHorizontalLines());
        System.out.println("ligne horizontale X=3 au Z=0 est OK");
    }

}

    // test les conditions horizontales au niveau 0
//    @Test
//    public void testHorizontalWinXIs0() {
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(0, 1, 0);
//        gameBoard.PlayMove(0, 2, 0);
//        gameBoard.PlayMove(0, 3, 0);
//        assertTrue(gameBoard.checkHorizontalLines());
//        System.out.println("ligne horizontale X=0 au Z=0 est OK");
//    }
//
//    @Test
//    public void testHorizontalWinXIs1() {
//        gameBoard.PlayMove(1, 0, 0);
//        gameBoard.PlayMove(1, 1, 0);
//        gameBoard.PlayMove(1, 2, 0);
//        gameBoard.PlayMove(1, 3, 0);
//        assertTrue(gameBoard.checkHorizontalLines());
//        System.out.println("ligne horizontale X=1 au Z=0 est OK");
//    }
//
//   @Test
//   public void testHorizontalFail0(){
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(1, 0, 0);
//        gameBoard.PlayMove(2, 0, 0);
//        gameBoard.PlayMove(3, 0, 0);
//        assertFalse(gameBoard.checkHorizontalLines());
//        System.out.println("il y a bien une suite de 4 mais elle n'est pas horizontale");
//    }
//
//    @Test
//    public  void testHorizontalWin1(){
//        gameBoard.PlayMove(0, 0, 1);
//        gameBoard.PlayMove(0, 1, 1);
//        gameBoard.PlayMove(0, 2, 1);
//        gameBoard.PlayMove(0, 3, 1);
//        assertTrue(gameBoard.checkHorizontalLines());
//        System.out.println("ligne horizontale au niveau 1 est OK");
//    }
//
//    @Test
//    public void testVerticalWin() {
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(1, 0, 0);
//        gameBoard.PlayMove(2, 0, 0);
//        gameBoard.PlayMove(3, 0, 0);
//        assertTrue(gameBoard.GameIsWin());
//    }
//
//    @Test
//    public void testVerticalWinFail() {
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(1, 0, 0);
//        gameBoard.PlayMove(2, 0, 0);
//        gameBoard.PlayMove(3, 0, 0);
//        assertFalse(gameBoard.checkVerticaleLines());
//    }
//
//    @Test
//    public void testDiagonalWin() {
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(1, 1, 0);
//        gameBoard.PlayMove(2, 2, 0);
//        gameBoard.PlayMove(3, 3, 0);
//        assertTrue(gameBoard.GameIsWin());
//    }
//
//    @Test
//    public void testColumnWin(){
//        gameBoard.PlayMove(0, 0, 0);
//        gameBoard.PlayMove(0, 0, 1);
//        gameBoard.PlayMove(0, 0, 2);
//        gameBoard.PlayMove(0, 0, 3);
//        assertTrue(gameBoard.GameIsWin());
//    }
