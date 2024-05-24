package com.example.androidchess.pieces;


import com.example.androidchess.app.ChessBoard;

/**
 * Class for the Pawn piece
 *
 * @author Ethan Lee
 *
 * @author David Lee
 */
public class Pawn extends Piece {

    /**
     * @param player
     * @param x
     * @param y
     */
    public Pawn(boolean player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.setPieceName("Pawn");
    }


    public static boolean isPawn(Piece chessBoard) {
        if(chessBoard.getPieceName() == "Pawn")
            return true;
        return false;
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
        firstMove = false;

        if(ChessBoard.isPromotion(chessBoard)) {

        }



        return chessBoard;
    }

    /**
     * Checks if the Pawn move is valid
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
        int distance = Math.abs(endingRowIndex - startingRowIndex);
        int colDistance = Math.abs(endingColIndex - startingColIndex);

        if((startingRowIndex == endingRowIndex) && (startingColIndex == endingColIndex))
        {
            return false;
        }

        if((endingColIndex < 0 && endingColIndex > 8) || (endingRowIndex < 0 && endingRowIndex > 8))
        {
            return false;
        }

        //if firstMove, can move up 2 squares
        if(firstMove) {
            if(startingColIndex == endingColIndex && (distance == 1 || distance == 2)) {
                if(distance == 2)
                    chessBoard[startingRowIndex][startingColIndex].vulnerable = true;
                return true;
            }
            //black player
            if(chessBoard[startingRowIndex][startingColIndex].getPlayer()==false) {
                if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile") && startingRowIndex < endingRowIndex && startingColIndex < endingColIndex)
                    return true;
                if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile") && startingRowIndex < endingRowIndex && startingColIndex > endingColIndex)
                    return true;
            }
            //white player
            if(chessBoard[startingRowIndex][startingColIndex].getPlayer()==true) {
                if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile") && startingRowIndex > endingRowIndex && startingColIndex < endingColIndex)
                    return true;
                if(distance ==1 && colDistance == 1 && chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")==false && startingRowIndex > endingRowIndex && startingColIndex > endingColIndex)
                    return true;
            }

        }
        // enpassant capture
        else if(canEnpassant(startingColIndex, startingRowIndex, endingColIndex, endingRowIndex, chessBoard)) {
            //black player
            if(!chessBoard[startingRowIndex][startingColIndex].getPlayer()) {
                if(isPawn(chessBoard[endingRowIndex-1][endingColIndex])) {
                    if(chessBoard[endingRowIndex-1][endingColIndex].getPlayer() == true) {
                        if(((endingRowIndex-1) % 2 != 0 && endingColIndex % 2 == 0) || ((endingRowIndex-1) % 2 == 0 && endingColIndex % 2 != 0)) {
                            chessBoard[endingRowIndex-1][endingColIndex] = new Tile(false, endingRowIndex-1, endingColIndex);
                        }
                        if(((endingRowIndex-1) % 2 != 0 && endingColIndex % 2 != 0) || ((endingRowIndex-1) % 2 == 0 && endingColIndex % 2 == 0)) {
                            chessBoard[endingRowIndex-1][endingColIndex] = new Tile(true, endingRowIndex-1, endingColIndex);
                        }
                        return true;
                    }
                }
            }
            //white player
            if(chessBoard[startingRowIndex][startingColIndex].getPlayer()) {
                if(isPawn(chessBoard[endingRowIndex+1][endingColIndex])) {
                    if(chessBoard[endingRowIndex+1][endingColIndex].getPlayer() == false) {
                        if(((endingRowIndex+1) % 2 != 0 && endingColIndex % 2 == 0) || ((endingRowIndex+1) % 2 == 0 && endingColIndex % 2 != 0)) {
                            chessBoard[endingRowIndex+1][endingColIndex] = new Tile(false, endingRowIndex+1, endingColIndex);
                        }
                        if(((endingRowIndex+1) % 2 != 0 && endingColIndex % 2 != 0) || ((endingRowIndex+1) % 2 == 0 && endingColIndex % 2 == 0)) {
                            chessBoard[endingRowIndex+1][endingColIndex] = new Tile(true, endingRowIndex+1, endingColIndex);
                        }

                        return true;
                    }
                }
            }
        }
        // normal move
        else {
            //black player
            if(chessBoard[startingRowIndex][startingColIndex].getPlayer()==false) {
                if(startingColIndex == endingColIndex && startingRowIndex < endingRowIndex && distance == 1 &&
                        chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")) {
                    if(endingColIndex == 7) {
                        setValidPro(true);
                    }
                    chessBoard[startingRowIndex][startingColIndex].vulnerable = false;
                    return true;

                }
                if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")) {
                    if(endingColIndex == 7) {
                        setValidPro(true);
                    }
                    chessBoard[startingRowIndex][startingColIndex].vulnerable = false;

                    if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile") && startingRowIndex < endingRowIndex && startingColIndex < endingColIndex)
                        return true;
                    if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile") && startingRowIndex < endingRowIndex && startingColIndex > endingColIndex)

                        return true;
                }
            }
            //white player
            if(chessBoard[startingRowIndex][startingColIndex].getPlayer()) {
                if(startingColIndex == endingColIndex && startingRowIndex > endingRowIndex && distance == 1 &&
                        chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")) {
                    if(endingColIndex == 0) {
                        setValidPro(true);
                    }
                    chessBoard[startingRowIndex][startingColIndex].vulnerable = false;
                    return true;

                }
                if(distance ==1 && colDistance == 1 && !chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")) {
                    if(endingColIndex == 0) {
                        setValidPro(true);
                    }
                    chessBoard[startingRowIndex][startingColIndex].vulnerable = false;

                    if(distance ==1 && colDistance == 1 && chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")==false && startingRowIndex > endingRowIndex && startingColIndex < endingColIndex)
                        return true;
                    if(distance ==1 && colDistance == 1 && chessBoard[endingRowIndex][endingColIndex].getPieceName().equals("Tile")==false && startingRowIndex > endingRowIndex && startingColIndex > endingColIndex)

                        return true;
                }
            }
        }
        return false;
        //if enpassant, can move diagonally if there is a piece to capture
        //if neither, then can only move forwards one square at a time, never backwards or side to side
    }

    /**
     * boolean for if the enemy pawn is vulnerable to an enpassant
     * (so if it made a 2 step move), and then if the user does an enpassant,
     * then it will check if the starting enemy and user pawns are adjacent,
     * and then check if the user's pawn is above the enemy's pawn
     * @param startingColIndex
     * @param startingRowIndex
     * @param endingColIndex
     * @param endingRowIndex
     * @param chessBoard
     * @return true if enpassant is allowed, false its not allowed
     */
    public boolean canEnpassant(int startingColIndex, int startingRowIndex, int endingColIndex, int endingRowIndex, Piece[][]chessBoard) {
        // if the pawn is not on the edge
        if(firstMove = true)
            return false;
        if(startingColIndex > 0 && startingColIndex < 7) {
            if(isPawn(chessBoard[startingRowIndex][startingColIndex-1])) {
                if(chessBoard[startingRowIndex][startingColIndex -1].vulnerable) {
                    return true;
                }
            }
            else if(isPawn(chessBoard[startingRowIndex][startingColIndex+1])) {
                if(chessBoard[startingRowIndex][startingColIndex +1].vulnerable) {
                    return true;
                }
            }
        }
        // if the pawn is all the way on the left side
        else if(startingColIndex == 0) {
            if(isPawn(chessBoard[startingRowIndex][startingColIndex+1])) {
                if(chessBoard[startingRowIndex][startingColIndex +1].vulnerable) {
                    return true;
                }
            }
        }
        // if the pawn is all the way on the right side
        else if(startingColIndex == 7) {
            if(isPawn(chessBoard[startingRowIndex][startingColIndex-1])) {
                if(chessBoard[startingRowIndex][startingColIndex -1].vulnerable) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Promotes the pawn to a queen rook or knight
     *
     * @param s piece that the pawn promotes to
     * @param chessBoard chessBoard at current time
     */
    public static void promote(String s, Piece[][] chessBoard) {
        int blackTeamRow = 0;
        int whiteTeamRow = 7;
        char c;
        String[] input = s.split(" ");
        if(input.length == 3)
            c = input[2].charAt(0);
        else
            c = 'q';

        //white promotion
        for(int i = 0; i < 8; i++) {
            if(chessBoard[blackTeamRow][i].getPlayer() && chessBoard[blackTeamRow][i].getPieceName().equals("Pawn")) {
                if (c == 'q' || c == 'Q') {
                    chessBoard[blackTeamRow][i] = new Queen(chessBoard[blackTeamRow][i].getPlayer(), blackTeamRow, i);
                }
                else if (c == 'r' || c == 'R') {
                    chessBoard[blackTeamRow][i] = new Rook(chessBoard[blackTeamRow][i].getPlayer(), blackTeamRow, i);
                }
                else if (c =='n' || c == 'N') {
                    chessBoard[blackTeamRow][i] = new Knight(chessBoard[blackTeamRow][i].getPlayer(), blackTeamRow, i);
                }
                else if (c == 'b'|| c == 'B') {
                    chessBoard[blackTeamRow][i] = new Bishop(chessBoard[blackTeamRow][i].getPlayer(), blackTeamRow, i);
                }
                else {
                    chessBoard[blackTeamRow][i] = new Queen(chessBoard[blackTeamRow][i].getPlayer(), blackTeamRow, i);
                }
                return;
            }
        }
        //black promotion
        for(int i = 0; i < 8; i++) {
            if(!chessBoard[whiteTeamRow][i].getPlayer() && chessBoard[whiteTeamRow][i].getPieceName().equals("Pawn")) {
                if (c == 'q' || c == 'Q') {
                    chessBoard[whiteTeamRow][i] = new Queen(chessBoard[whiteTeamRow][i].getPlayer(), whiteTeamRow, i);
                }
                else if (c == 'r' || c == 'R') {
                    chessBoard[whiteTeamRow][i] = new Rook(chessBoard[whiteTeamRow][i].getPlayer(), whiteTeamRow, i);
                }
                else if (c == 'n' || c == 'N') {
                    chessBoard[whiteTeamRow][i] = new Knight(chessBoard[whiteTeamRow][i].getPlayer(), whiteTeamRow, i);
                }
                else if (c == 'b' || c == 'B') {
                    chessBoard[whiteTeamRow][i] = new Bishop(chessBoard[whiteTeamRow][i].getPlayer(), whiteTeamRow, i);
                }
                else {
                    chessBoard[whiteTeamRow][i] = new Queen(chessBoard[whiteTeamRow][i].getPlayer(), whiteTeamRow, i);
                }
                return;
            }
        }
        return;
    }

    public String getPieceID()
    {
        String t="";
        if(this.player == true)
            t = "w";
        else
            t = "b";
        return t+"p";

    }

    /**
     * Prints the Pawn piece with color in the console
     */
    public void printPiece()
    {
        if(player == true)
            System.out.print("wp");
        else
            System.out.print("bp");
    }

}
