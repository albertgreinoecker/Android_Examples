package at.ac.htlinn.androidexamples.ttt;

import java.util.Arrays;

/**
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * @author albert
 */
public class TTTGame {

    final int DIM = 9;

    enum C {E, X, O};
    C[] field = new C[DIM];
    C actPlayer; //who is on turn

    public TTTGame() {
        init();
    }

    /**
     * Fill the whole game board with empty cells
     */
    public void init() {
        Arrays.fill(field, C.E);
        actPlayer = xStarts() ? C.X : C.O;
    }

    /**
     * is X the actual player
     * @return
     */
    public boolean xAct() {
        return actPlayer == C.X;
    }

    public void changePlayer() {
        actPlayer = xAct() ? C.O : C.X;
    }

    /**
     * The actual player makes a move
     * @param pos position where actual player wants to place his move
     * @return true if position was valid
     */
    public boolean play(int pos) {
        if (field[pos] != C.E) {
            return false;
        }

        field[pos] = actPlayer;
        return true;
    }

    /**
     * @return true if playfield is already full
     */
    public boolean full() {
        for (C c : field) {
            if (c == C.E) return false;
        }
        return true;
    }

    private String asString(int p1, int p2, int p3) {
        return "" + field[p1] + field[p2] + field[p3];
    }

    /**
     * @return true if actual player has won
     */
    public boolean won() {
        String f = asString(0, 1, 2) + "|" + asString(3, 4, 5) + "|" + asString(6, 7, 8) + "|"; //horz
        f += asString(0, 3, 6) + "|" + asString(1, 4, 7) + "|" + asString(2, 5, 8) + "|"; // vert
        f += asString(0, 4, 8) + "|" + asString(2, 4, 6); //diag
        return f.contains("XXX") || f.contains("OOO");
    }

    /**
     * @return use this to decide who has to start (true = X
     */
    private boolean xStarts() {
        return Math.random() > 0.5;
    }
}

