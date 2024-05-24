package com.example.androidchess.pieces;

/**
 * Class for the Tile piece
 *
 * @author Ethan Lee
 *
 * @author David Lee
 */
public class Tile extends Piece {
    public Tile(boolean player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.setPieceName("Tile");
    }


    /**
     * Prints the empty tile with color in the console
     */
    public void printPiece()
    {
        if(player == true)
            System.out.print("  ");
        else
            System.out.print("##");

    }


    public boolean validMove(int startingColIndex, int startingRowIndex, int endingColIndex, int endingRowIndex, Piece[][]chessBoard)
    {
        return false;
    }
}
