package com.example.androidchess.app;

import com.example.androidchess.pieces.Piece;
import com.example.androidchess.pieces.Bishop;
import com.example.androidchess.pieces.King;
import com.example.androidchess.pieces.Knight;
import com.example.androidchess.pieces.Pawn;
import com.example.androidchess.pieces.Queen;
import com.example.androidchess.pieces.Rook;
import com.example.androidchess.pieces.Tile;

import java.util.Locale;

public class ChessBoard {

    /**
     * Creating a 2D array with 9x9 dimensions that will represent the chess board
     */
    public static Piece[][] chessBoard = new Piece[8][8];
    public static Piece[][] previousBoard = new Piece[8][8];
    public static boolean legal;


    /**
     * Method that checks if the provided index on the chessBoard is valid.
     * Valid meaning if the index is in bounds (within the indexes on the array)
     * @param  r  the row index entered by user on the chess board (0-7)
     * @param  c the column index entered by user on the chess board (0-7)
     * @return returns true if the index is valid and in bounds. returns false otherwise
     */
    public static boolean isValidSpace(int r, int c)
    {
        if((r > 7 || r < 0) || (c > 7 || c < 0)) //if row or column is outside of array bounds
            return false; //not valid

        return true;
    }

    /**
     * Building the board that will be printed to the user, with black pieces on the top
     * and white pieces on the bottom. Setting up the board at start of the game.
     */
    public static void buildBoard()
    {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                chessBoard[x][y] = null;
            }
        }

        // Black Pieces
        chessBoard[1][0] = new Pawn(false, 1, 0);
        chessBoard[1][1] = new Pawn(false, 1, 1);
        chessBoard[1][2] = new Pawn(false, 1, 2);
        chessBoard[1][3] = new Pawn(false, 1, 3);
        chessBoard[1][4] = new Pawn(false, 1, 4);
        chessBoard[1][5] = new Pawn(false, 1, 5);
        chessBoard[1][6] = new Pawn(false, 1, 6);
        chessBoard[1][7] = new Pawn(false, 1, 7);

        chessBoard[0][0] = new Rook(false, 0, 0);
        chessBoard[0][1] = new Knight(false, 0, 1);
        chessBoard[0][2] = new Bishop(false, 0, 2);
        chessBoard[0][3] = new Queen(false, 0, 3);
        chessBoard[0][4] = new King(false, 0, 4);
        chessBoard[0][5] = new Bishop(false, 0, 5);
        chessBoard[0][6] = new Knight(false, 0, 6);
        chessBoard[0][7] = new Rook(false, 0, 7);

        // White Pieces
        chessBoard[6][0] = new Pawn(true, 6, 0);
        chessBoard[6][1] = new Pawn(true, 6, 1);
        chessBoard[6][2] = new Pawn(true, 6, 2);
        chessBoard[6][3] = new Pawn(true, 6, 3);
        chessBoard[6][4] = new Pawn(true, 6, 4);
        chessBoard[6][5] = new Pawn(true, 6, 5);
        chessBoard[6][6] = new Pawn(true, 6, 6);
        chessBoard[6][7] = new Pawn(true, 6, 7);

        chessBoard[7][0] = new Rook(true, 7, 0);
        chessBoard[7][1] = new Knight(true, 7, 1);
        chessBoard[7][2] = new Bishop(true, 7, 2);
        chessBoard[7][3] = new Queen(true, 7, 3);
        chessBoard[7][4] = new King(true, 7, 4);
        chessBoard[7][5] = new Bishop(true, 7, 5);
        chessBoard[7][6] = new Knight(true, 7, 6);
        chessBoard[7][7] = new Rook(true, 7, 7);

        //Empty Tiles
        chessBoard[2][0] = new Tile(true, 2, 0);
        chessBoard[2][1] = new Tile(false, 2, 1);
        chessBoard[2][2] = new Tile(true, 2, 2);
        chessBoard[2][3] = new Tile(false, 2, 3);
        chessBoard[2][4] = new Tile(true, 2, 4);
        chessBoard[2][5] = new Tile(false, 2, 5);
        chessBoard[2][6] = new Tile(true, 2, 6);
        chessBoard[2][7] = new Tile(false, 2, 7);

        chessBoard[3][0] = new Tile(false, 3, 0);
        chessBoard[3][1] = new Tile(true, 3, 1);
        chessBoard[3][2] = new Tile(false, 3, 2);
        chessBoard[3][3] = new Tile(true, 3, 3);
        chessBoard[3][4] = new Tile(false, 3, 4);
        chessBoard[3][5] = new Tile(true, 3, 5);
        chessBoard[3][6] = new Tile(false, 3, 6);
        chessBoard[3][7] = new Tile(true, 3, 7);

        chessBoard[4][0] = new Tile(true, 4, 0);
        chessBoard[4][1] = new Tile(false, 4, 1);
        chessBoard[4][2] = new Tile(true, 4, 2);
        chessBoard[4][3] = new Tile(false, 4, 3);
        chessBoard[4][4] = new Tile(true, 4, 4);
        chessBoard[4][5] = new Tile(false, 4, 5);
        chessBoard[4][6] = new Tile(true, 4, 6);
        chessBoard[4][7] = new Tile(false, 4, 7);

        chessBoard[5][0] = new Tile(false, 5, 0);
        chessBoard[5][1] = new Tile(true, 5, 1);
        chessBoard[5][2] = new Tile(false, 5, 2);
        chessBoard[5][3] = new Tile(true, 5, 3);
        chessBoard[5][4] = new Tile(false, 5, 4);
        chessBoard[5][5] = new Tile(true, 5, 5);
        chessBoard[5][6] = new Tile(false, 5, 6);
        chessBoard[5][7] = new Tile(true, 5, 7);

    }


    /**
     * Printing the board for the user to see. Specifies piece color and type.
     */
    public static void printBoard(Piece[][]chessBoard)
    {
        System.out.println();
        int rowIndex = 8;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j<8; j++)
            {
                chessBoard[i][j].printPiece();
                System.out.print(" ");
            }
            System.out.print(rowIndex);
            rowIndex--;
            System.out.println();
        }

        for(char i = 'a'; i< 105; i++)
        {
            System.out.print(" " + i);
            System.out.print(" ");
        }

        System.out.println();
        System.out.println();

    }

    /**
     * Updates the board
     *
     * @param input The input that the user puts into the main
     * @param playerTurn is white if true and is black if false
     * @return the updated chessBoard
     */
    public static  Piece[][] updateBoard(String input, boolean playerTurn)
    {
        //System.arraycopy(chessBoard, 0,previousBoard, 0, chessBoard.length);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                previousBoard[i][j] = chessBoard[i][j];
            }
        }
        String[]moveInput = input.split(" ");
        char startingColInput = moveInput[0].charAt(0);
        char startingRowInput = moveInput[0].charAt(1);
        char endingColInput = moveInput[1].charAt(0);
        char endingRowInput = moveInput[1].charAt(1);

        int startingColIndex = letterToIndex(startingColInput) - 1;
        int startingRowIndex = ((startingRowInput - '0') - 8) * -1;
        int endingColIndex = letterToIndex(endingColInput) - 1;
        int endingRowIndex = ((endingRowInput - '0') - 8) * -1;

        if((endingColIndex < 0 && endingColIndex > 8) || (endingRowIndex < 0 && endingRowIndex > 8))
            legal = false;

        if(isCheck(chessBoard, playerTurn) == true)
        {
            if(isCheck(chessBoard[startingRowIndex][startingColIndex].movePiece(input, chessBoard), playerTurn) == true)
            {
                legal = false;
                return chessBoard;
            }
        }

        if(chessBoard[startingRowIndex][startingColIndex].getPlayer()==(playerTurn) &&
                chessBoard[startingRowIndex][startingColIndex].validMove(startingColIndex, startingRowIndex, endingColIndex, endingRowIndex, chessBoard)) {
            legal = true;
            return chessBoard[startingRowIndex][startingColIndex].movePiece(input, chessBoard);
        }
        else {
            legal = false;
            return chessBoard;
        }
    }

    /**
     * Returns the chessBoard at that time
     *
     * @return the chessBoard
     */
    public static Piece[][]getBoard()
    {
        return chessBoard;
    }


    public static boolean isPromotion(Piece[][] chessBoard) {
        int blackTeamRow = 0;
        int whiteTeamRow = 7;
        //white promotion
        for(int i = 0; i < 8; i++) {
            if(chessBoard[blackTeamRow][i].getPlayer() && chessBoard[blackTeamRow][i].getPieceName().equals("Pawn")) {
                return true;
            }
        }
        //black promotion
        for(int i = 0; i < 8; i++) {
            if(!chessBoard[whiteTeamRow][i].getPlayer() && chessBoard[whiteTeamRow][i].getPieceName().equals("Pawn")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the king is in check or not
     *
     * @param chessBoard
     * @param playerTurn
     * @return true if king is in check, false if king is not in check
     */
    public static boolean isCheck(Piece[][]chessBoard, boolean playerTurn)
    {
        int kingRow = 0;
        int kingCol = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(chessBoard[i][j].getPlayer()==playerTurn && !chessBoard[i][j].getPieceName().equals("Tile"))
                {
                    if(chessBoard[i][j].getPieceName().equals("King"))
                    {
                        kingRow = i;
                        kingCol = j;
                    }
                }
            }
        }

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(chessBoard[i][j].getPlayer()!=playerTurn && !chessBoard[i][j].getPieceName().equals("Tile"))
                {
                    if(chessBoard[i][j].validMove(j, i, kingCol, kingRow, chessBoard)==true)
                    {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * Returns whether player is in checkmate
     *
     * @param chessBoard
     * @param playerTurn
     * @return
     */
    public static boolean isCheckMate(Piece[][]chessBoard, boolean playerTurn)
    {
        int kingRow = 0;
        int kingCol = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(chessBoard[i][j].getPlayer()==playerTurn && !chessBoard[i][j].getPieceName().equals("Tile"))
                {
                    if(chessBoard[i][j].getPieceName().equals("King"))
                    {
                        kingRow = i;
                        kingCol = j;
                    }
                }
            }
        }
        if(isCheck(chessBoard,playerTurn) == true)
        {
            Piece[][] tempBoard = new Piece[8][8];
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol+2, kingRow, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow][kingCol+2] = new King(playerTurn, kingRow, kingCol+2);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol-2, kingRow, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow][kingCol-2] = new King(playerTurn, kingRow, kingCol-2);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol, kingRow+1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow+1][kingCol] = new King(playerTurn, kingRow+1, kingCol);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol, kingRow-1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow-1][kingCol] = new King(playerTurn, kingRow-1, kingCol);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol+1, kingRow, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow][kingCol+1] = new King(playerTurn, kingRow, kingCol+1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol-1, kingRow, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow][kingCol-1] = new King(playerTurn, kingRow, kingCol-1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol-1, kingRow-1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow-1][kingCol-1] = new King(playerTurn, kingRow-1, kingCol-1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol+1, kingRow+1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow+1][kingCol+1] = new King(playerTurn, kingRow+1, kingCol+1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol-1, kingRow+1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow+1][kingCol-1] = new King(playerTurn, kingRow+1, kingCol-1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            copyBoard(tempBoard);
            if(tempBoard[kingRow][kingCol].validMove(kingCol, kingRow, kingCol+1, kingRow-1, chessBoard) == true)
            {
                tempBoard[kingRow][kingCol] = new Tile(true, kingRow, kingCol);
                tempBoard[kingRow-1][kingCol+1] = new King(playerTurn, kingRow-1, kingCol+1);
                if(isCheck(tempBoard,playerTurn)!=true)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * makes a temporary copy of the chess board
     *
     * @param tempBoard takes in a board that is becomes copy of chessBoard
     */
    public static void copyBoard(Piece[][]tempBoard)
    {
        for(int i = 0; i<8;i++)
        {
            for(int j = 0; j<8;j++)
            {
                tempBoard[i][j] = chessBoard[i][j];
            }
        }
    }

    public static void changeBoard(Piece[][]board)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j <8; j++)
            {
                chessBoard[i][j] = board[i][j];
            }
        }
    }

    public static void undoBoard()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = previousBoard[i][j];
            }
        }

    }

    public static boolean getType(String coord)
    {
        String[]moveInput = coord.split(" ");
        char x = moveInput[0].charAt(0);
        char y = moveInput[0].charAt(1);

        int xInt = letterToIndex(x) - 1;
        int yInt = ((y - '0') - 8) * -1;

        return chessBoard[yInt][xInt].getPlayer();

    }




    /**
     * Takes a character from a-g and returns the integer equivalent
     *
     * @param index The character index that is inputed by the user
     * @return returns the integer equivalent of the character
     */
    public static int letterToIndex(char index)
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
        return 0;
    }

    public static int letterToIndex(String index)
    {
        if(index == "a")
            return 1;
        if(index == "b")
            return 2;
        if(index == "c")
            return 3;
        if(index == "d")
            return 4;
        if(index == "e")
            return 5;
        if(index == "f")
            return 6;
        if(index == "g")
            return 7;
        if(index == "h")
            return 8;
        return 0;
    }
}
