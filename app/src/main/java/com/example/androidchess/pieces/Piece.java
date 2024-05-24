package com.example.androidchess.pieces;

public abstract class Piece {
    /**
     * x coordinate
     */
    int x;
    /**
     * y coordinate
     */
    int y;
    /**
     * true is white player
     * false is black player
     */
    boolean player;
    /**
     * name of the piece
     */
    String pieceName;
    /**
     * checks to see if the pawn made a 2 step move
     */
    boolean vulnerable;
    /**
     * true = is first move, false = not first move
     * applies to the 2 step rule for the pawn
     */
    boolean firstMove = true;
    /**
     * true is pawn is promoting, false is pawn cannot promote
     * only true if pawn reaches end of board for their respective team
     */
    private boolean validPro = false;
    /**
     * returns the x coordinate of the piece
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * returns the y coordinate of the piece
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * sets new x coordinate for the piece
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * sets new y coordinate for the piece
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * gets player status
     * @return player
     * white is true and black is false
     */
    public boolean getPlayer() {
        return player;
    }

    /**
     * returns the name of the piece
     * @return pieceName
     */
    public void setPieceName(String name) {
        this.pieceName = name;
    }
    /**
     * returns the name of the piece
     * @return pieceName
     */
    public String getPieceName() {
        return pieceName;
    }

    public void setPieceID(String name) {
        this.pieceName = name;
    }
    public String getPieceID() {
        return pieceName;
    }

    /**
     * Prints the piece in the console
     */
    public void printPiece()
    {
        System.out.println(" ");
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
        return chessBoard;
    }

    public abstract boolean validMove(int startingColIndex, int startingRowIndex, int endingColIndex, int endingRowIndex, Piece[][] chessBoard);

    /**
     * Takes a character from a-g and returns the integer equivalent
     *
     * @param index The character index that is inputed by the user
     * @return returns the integer equivalent of the character
     */


    public int letterToIndex(char index)
    {
        if(index == 'a')
            return 1;
        if(index == 'b')
            return 2;
        if(index == 'c')
            return 3;
        if(index == 'd')
            return 4;
        if(index == 'e')
            return 5;
        if(index == 'f')
            return 6;
        if(index == 'g')
            return 7;
        if(index == 'h')
            return 8;
        return -1;
    }
    /**
     *
     * @return returns if promotion is valid
     */
    public boolean getValidPro() {
        return validPro;
    }
    /**
     *
     * @param validPro
     */
    public void setValidPro(boolean validPro) {
        this.validPro = validPro;
    }
}
