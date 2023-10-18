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
        System.out.println("Switch player fonctionne");
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
    public void testCheckInARowIsTrue(){
        // Test si checkInARow compare correctement les valeurs
        assertTrue(gameBoard.CheckFourInARow(1,1,1,1));
        System.out.println("la ligne possède les mêmes valeurs et différentes de 0");
    }

// ------- test qui vérifie que le jeu est gagné -------

    // ------- ligne horizontal et vertical
    @Test
    public void testVerticalLineLevel0(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(0,1,0);
        gameBoard.PlayMove(0,2,0);
        gameBoard.PlayMove(0,3,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 0");
    }

    @Test
    public void testVerticalLineLevel1(){
        gameBoard.PlayMove(1,3,2);
        gameBoard.PlayMove(1,2,2);
        gameBoard.PlayMove(1,1,2);
        gameBoard.PlayMove(1,0,2);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 1");
    }

    @Test
    public void testVerticalLineLevel2(){
        gameBoard.PlayMove(2,0,1);
        gameBoard.PlayMove(2,1,1);
        gameBoard.PlayMove(2,2,1);
        gameBoard.PlayMove(2,3,1);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 2");
    }

    @Test
    public void testVerticalLineLevel3(){
        gameBoard.PlayMove(3,3,3);
        gameBoard.PlayMove(3,2,3);
        gameBoard.PlayMove(3,1,3);
        gameBoard.PlayMove(3,0,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne vertical au niveau 3");
    }

    // ------------------------------------------------------------
    @Test
    public void testHorizontalLineLevel0(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(0,0,1);
        gameBoard.PlayMove(0,0,2);
        gameBoard.PlayMove(0,0,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 0 avec x=0 y=0");
    }

    @Test
    public void testHorizontalLineLevel0WithDifferentCoordinates() {
        gameBoard.PlayMove(0, 1, 0);
        gameBoard.PlayMove(0, 1, 1);
        gameBoard.PlayMove(0, 1, 2);
        gameBoard.PlayMove(0, 1, 3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Le jeu détecte bien une ligne horizontale au niveau 0 avec x=0 et y=1");
    }

    @Test
    public void testHorizontalLineLevel0WithY2() {
        gameBoard.PlayMove(0, 2, 0);
        gameBoard.PlayMove(0, 2, 1);
        gameBoard.PlayMove(0, 2, 2);
        gameBoard.PlayMove(0, 2, 3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Le jeu détecte bien une ligne horizontale au niveau 0 avec x=0 et y=2");
    }

    @Test
    public void testHorizontalLineLevel0WithY3() {
        gameBoard.PlayMove(0, 3, 0);
        gameBoard.PlayMove(0, 3, 1);
        gameBoard.PlayMove(0, 3, 2);
        gameBoard.PlayMove(0, 3, 3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Le jeu détecte bien une ligne horizontale au niveau 0 avec x=0 et y=3");
    }

    @Test
    public void testHorizontalLineLevel1WithX1Y1() {
        gameBoard.PlayMove(1, 1, 3);
        gameBoard.PlayMove(1, 1, 2);
        gameBoard.PlayMove(1, 1, 1);
        gameBoard.PlayMove(1, 1, 0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Le jeu détecte bien une ligne horizontale au niveau 1 avec x=1 et y=1");
    }

    @Test
    public void testHorizontalLineLevel1(){
        gameBoard.PlayMove(1,2,3);
        gameBoard.PlayMove(1,2,2);
        gameBoard.PlayMove(1,2,1);
        gameBoard.PlayMove(1,2,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 1 avec x=1 et y=2");
    }

    @Test
    public void testHorizontalLineLevel2(){
        gameBoard.PlayMove(2,3,0);
        gameBoard.PlayMove(2,3,1);
        gameBoard.PlayMove(2,3,2);
        gameBoard.PlayMove(2,3,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 2 avec x=2 y=3");
    }

    @Test
    public void testHorizontalLineLevel3(){
        gameBoard.PlayMove(3,1,3);
        gameBoard.PlayMove(3,1,2);
        gameBoard.PlayMove(3,1,1);
        gameBoard.PlayMove(3,1,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien une ligne horizontal au niveau 3");
    }

    // -------- test lignes diagonales --------
    @Test
    public void testDiagonaleLevel0(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(0,1,1);
        gameBoard.PlayMove(0,2,2);
        gameBoard.PlayMove(0,3,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les diagonales au niveau 0");
    }

    @Test
    public void testDiagonaleInverseLevel0(){
        gameBoard.PlayMove(0,3,0);
        gameBoard.PlayMove(0,2,1);
        gameBoard.PlayMove(0,1,2);
        gameBoard.PlayMove(0,0,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 0");
    }

    @Test
    public void testDiagonaleInverseLevel1(){
        gameBoard.PlayMove(1,3,3);
        gameBoard.PlayMove(1,2,2);
        gameBoard.PlayMove(1,1,1);
        gameBoard.PlayMove(1,0,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 1");
    }

    @Test
    public void testDiagonaleInverseLevel2(){
        gameBoard.PlayMove(2,3,3);
        gameBoard.PlayMove(2,2,2);
        gameBoard.PlayMove(2,1,1);
        gameBoard.PlayMove(2,0,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les diagonales inverser au niveau 2");
    }

    @Test
    public void testDiagonaleLevel3(){
        gameBoard.PlayMove(3,0,0);
        gameBoard.PlayMove(3,1,1);
        gameBoard.PlayMove(3,2,2);
        gameBoard.PlayMove(3,3,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les diagonales au niveau 3");
    }

    // ------- test les lignes 3D -----------
    @Test
    public void testColonne3D(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(1,0,0);
        gameBoard.PlayMove(2,0,0);
        gameBoard.PlayMove(3,0,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("Nous prenons en charge les colonnes 3D");
    }

    // ---------------- Horizontale 3D-----------
    @Test
    public void testHorizontalLine3D() {
        gameBoard.PlayMove(3, 3, 0);
        gameBoard.PlayMove(2, 3, 1);
        gameBoard.PlayMove(1, 3, 2);
        gameBoard.PlayMove(0, 3, 3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=3 z=3 ");
    }

    @Test
    public void testHorizontaleDecreaseLine(){
        gameBoard.PlayMove(0,2,3);
        gameBoard.PlayMove(1,2,2);
        gameBoard.PlayMove(2,2,1);
        gameBoard.PlayMove(3,2,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=2 z=3");
    }

    @Test
    public void testHorizontaleIncreaseLine(){
        gameBoard.PlayMove(3,2,3);
        gameBoard.PlayMove(2,2,2);
        gameBoard.PlayMove(1,2,1);
        gameBoard.PlayMove(0,2,0);
        System.out.println("le jeu détecte un puissance 4 horizontale descendante 3D qui commence a l'index y=2 z=3");
    }

    @Test
    public void testHorinzontaleLineIn3D2(){
        gameBoard.PlayMove(0,1,0);
        gameBoard.PlayMove(1,1,1);
        gameBoard.PlayMove(2,1,2);
        gameBoard.PlayMove(3,1,3);
        System.out.println("le jeu détecte un puissance 4 en diagonale montant 3D sur une ligne horizontale qui commence à l'index y=1 z=0");
    }

    @Test
    public void testHorizontalLineInInverse3D(){
        gameBoard.PlayMove(3,3,3);
        gameBoard.PlayMove(2,3,2);
        gameBoard.PlayMove(1,3,1);
        gameBoard.PlayMove(0,3,0);
        System.out.println("le jeu détecte un puissance 4 en diagonale montant 3D sur une ligne horizontal qui commence à l'index y=3 z=0");
    }

    // ------------------ Verticale 3D-----------------
    @Test
    public void testVerticalLineIn3D(){
        gameBoard.PlayMove(0,0,3);
        gameBoard.PlayMove(1,1,3);
        gameBoard.PlayMove(2,2,3);
        gameBoard.PlayMove(3,3,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 en vertical montant 3D  qui commence à l'index y=0 z=3");
    }

    @Test
    public void testVerticalLineInInverse3D(){
        gameBoard.PlayMove(3,0,0);
        gameBoard.PlayMove(2,1,0);
        gameBoard.PlayMove(1,2,0);
        gameBoard.PlayMove(0,3,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 en verticale descendant 3D qui commence a l'index y=0 z=0");
    }

    //------------Diagonale 3D -------------
    @Test
    public void testRisingDiagonale3D(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(1,1,1);
        gameBoard.PlayMove(2,2,2);
        gameBoard.PlayMove(3,3,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D montante qui commence à l'index x=0 y=0 z=0");
    }
    @Test
    public void testDescendingDiagonale3D(){
        gameBoard.PlayMove(0,3,3);
        gameBoard.PlayMove(1,2,2);
        gameBoard.PlayMove(2,1,1);
        gameBoard.PlayMove(3,0,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D descendante qui commence à l'index x=0 y=3 z=3");
    }

    @Test
    public void testInverseRisingDiagonale3D(){
        gameBoard.PlayMove(0,3,0);
        gameBoard.PlayMove(1,2,1);
        gameBoard.PlayMove(2,1,2);
        gameBoard.PlayMove(3,0,3);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D montante qui commence à l'index x=0 y=3 z=0");
    }

    @Test
    public void testInverseDescendingDiagonale3D(){
        gameBoard.PlayMove(0,0,3);
        gameBoard.PlayMove(1,1,2);
        gameBoard.PlayMove(2,2,1);
        gameBoard.PlayMove(3,3,0);
        assertTrue(gameBoard.CheckLines());
        System.out.println("le jeu détecte un puissance 4 diagonale 3D descendante qui commence à l'index x=0 y=0 z=3");
    }

    // -------- test qui vérifie que le jeu continue -----------

    @Test
    public void testNoWinOnALine(){
        gameBoard.PlayMove(0,0,0);
        gameBoard.PlayMove(0,0,3);
        gameBoard.PlayMove(0,1,1);
        gameBoard.PlayMove(0,1,2);
        assertFalse(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien 4 fois la même valeur mais pas sur le meme axe ");
    }

    @Test
    public void testBadHorizontalLine() {
        gameBoard.PlayMove(3, 3, 0);
        gameBoard.PlayMove(3, 3, 1);
        gameBoard.PlayMove(1, 3, 2);
        gameBoard.PlayMove(0, 3, 3);
        assertFalse(gameBoard.CheckLines());
        System.out.println("le jeu détecte bien que la ligne horizontale 3D n'est pas correct");
    }
}
