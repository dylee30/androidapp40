package com.example.androidchess.pieces;

/**
 * Class for the Knight piece
 *
 * @author Ethan Lee
 *
 * @author David Lee
 */
public class Knight extends Piece {

    public Knight(boolean player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.setPieceName("Knight");
    }


    /**
     * Moves the piece from the starting destination to final destination if the move is valid
     * Updates the board accordingly and returns the updated board
     *
     * @param input The string input entered by the user in the main
     * @param chessBoard the chessBoard at the current state of the game
     * @return returns the updated chessBoard with the new move
     */
    public Piece[][] movePiece(String input, Piece[][] chessBoard)
    {
        String[]moveInput = input.split(" ");
        char startingColInput = moveInput[0].charAt(0);
        char startingRowInput = moveInput[0].charAt(1);
        char endingColInput = moveInput[1].charAt(0);
        char endingRowInput = moveInput[1].charAt(1);

        int startingColIndex = letterToIndex(startingColInput) - 1;
        int startingRowIndex = ((startingRowInput - '0') - 8) * -1;
        int endingColIndex = letterToIndex(endingColInput) - 1;
        int endingRowIndex = ((endingRowInput - '0') - 8) * -1;

        this.x = endingRowIndex;
        this.y = endingColIndex;
        if((startingRowIndex % 2 != 0 && startingColIndex % 2 == 0) || (startingRowIndex % 2 == 0 && startingColIndex % 2 != 0))
            chessBoard[startingRowIndex][startingColIndex] = new Tile(false, startingRowIndex, startingColIndex);
        if((startingRowIndex % 2 != 0 && startingColIndex % 2 != 0) || (startingRowIndex % 2 == 0 && startingColIndex % 2 == 0))
            chessBoard[startingRowIndex][startingColIndex] = new Tile(true, startingRowIndex, startingColIndex);


        chessBoard[endingRowIndex][endingColIndex] = this;

        return chessBoard;
    }

    /**
     * Checks if the Knight move is valid
     *
     * @param startingColIndex Column index of the starting coordinate
     * @param startingRowIndex Row index of the starting coordinate
     * @param endingColIndex Column index of the destination coordinate
     * @param endingRowIndex Row index of the destination coordinate
     * @param chessBoard the chessBoard at the current state of the game
     * @return returns true if the move is valid and false if it is invalid
     */
    public boolean validMove(int startingColIndex, int startingRowIndex, int endingColIndex, int endingRowIndex, Piece[][]chessBoard)
    {
        if((endingColIndex < 0 && endingColIndex > 8) || (endingRowIndex < 0 && endingRowIndex > 8))
            return false;
        if((startingRowIndex == endingRowIndex) && (startingColIndex == endingColIndex))
            return false;

        int rowDistance = Math.abs(endingRowIndex - startingRowIndex);
        int colDistance = Math.abs(endingColIndex - startingColIndex);

        if(rowDistance == 2 && colDistance == 1 && chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile"))
            return true;

        if(rowDistance == 1 && colDistance == 2 && chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile"))
            return true;

        if(rowDistance == 2 && colDistance == 1 && (chessBoard[startingRowIndex][startingColIndex].getPlayer() != chessBoard[endingRowIndex][endingColIndex].getPlayer()))
            return true;

        if(rowDistance == 1 && colDistance == 2 && (chessBoard[startingRowIndex][startingColIndex].getPlayer() != chessBoard[endingRowIndex][endingColIndex].getPlayer()))
            return true;


        return false;
    }

    public String getPieceID()
    {
        String t="";
        if(this.player == true)
            t = "w";
        else
            t = "b";
        return t+"n";

    }

    /**
     * Prints the Knight piece with color in the console
     */
    public void printPiece()
    {
        if(player == true)
            System.out.print("wN");
        else
            System.out.print("bN");
    }


}