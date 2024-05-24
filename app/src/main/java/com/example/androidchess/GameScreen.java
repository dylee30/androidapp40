package com.example.androidchess;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidchess.pieces.*;
import com.example.androidchess.app.*;

public class GameScreen extends AppCompatActivity {

    boolean winner = false;
    boolean undoable = false;
    String start, end;
    boolean playerTurn = true; //white goes first
    ArrayList<String> gameMoves = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamescreen);

        startingBoard();
        Scanner scan = new Scanner(System.in);
        final Button undo = (Button)findViewById(R.id.btn_undo);
        final Button ai = (Button)findViewById(R.id.btn_ai);
        final Button resign = (Button)findViewById(R.id.btn_resign);
        final Button draw = (Button)findViewById(R.id.btn_draw);

        ChessBoard.buildBoard();

        String move = "";

        draw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Show an AlertDialog with a message indicating that the game has ended in a draw
                AlertDialog.Builder builder = new AlertDialog.Builder(GameScreen.this);
                builder.setMessage("The game has ended in a draw")
                        .setTitle("Draw")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                gameMoves.add("draw");
                                Bundle bundle = new Bundle();
                                Intent i2 = new Intent(GameScreen.this, SavePrompt.class);
                                i2.putExtra("complete", gameMoves);
                                i2.putExtras(bundle);
                                startActivity(i2);
                                gameMoves = new ArrayList<String>();
                            }
                        })
                        .show();
            }
        });

        resign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Show an AlertDialog with a message indicating that the game has ended in a draw
                AlertDialog.Builder builder = new AlertDialog.Builder(GameScreen.this);
                winner = true;
                if(playerTurn == true)
                {
                    builder.setMessage("Black wins the game!")
                            .setTitle("White has resigned!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    gameMoves.add("resign");
                                    Bundle bundle = new Bundle();
                                    Intent i2 = new Intent(GameScreen.this, SavePrompt.class);
                                    i2.putExtra("complete", gameMoves);
                                    i2.putExtras(bundle);
                                    startActivity(i2);
                                    gameMoves = new ArrayList<String>();
                                }
                            })
                            .show();
                }
                else
                {
                    builder.setMessage("White wins the game!")
                            .setTitle("Black has resigned!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    gameMoves.add("resign");
                                    Bundle bundle = new Bundle();
                                    Intent i2 = new Intent(GameScreen.this, SavePrompt.class);
                                    i2.putExtra("complete", gameMoves);
                                    i2.putExtras(bundle);
                                    startActivity(i2);
                                    gameMoves = new ArrayList<String>();
                                }
                            })
                            .show();
                }

            }
        });

        undo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(undoable == true)
                    undoMove();
                else if(undoable == false)
                    undoMaxWarning();
            }
        });

        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    public String generateRandomMove() {
        // Starting index x and y
        System.out.println("Start: " + start);
        char startingColInput = start.charAt(0);
        char startingRowInput = start.charAt(1);
        int startingColIndex = ChessBoard.letterToIndex(startingColInput);
        int startingRowIndex = ((startingRowInput - '0') - 8) * -1;
        System.out.println("Coords: " + startingColIndex + startingRowIndex);
        // Generate a list of possible moves
        Piece curr = ChessBoard.chessBoard[startingRowIndex][startingColIndex];
        System.out.println(curr.getPlayer() + " " + curr.getPieceID());
        List<String> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((curr.validMove(startingColIndex, startingRowIndex, j, i, ChessBoard.chessBoard))) {
                    if(curr.getPlayer() && i < startingRowIndex) {
                        String letter = indexToLetter(j);
                        int second = intSwitch(i + 1);
                        possibleMoves.add(letter + second);
                        System.out.println("added: " + letter + second);
                    }
                    else if(!curr.getPlayer() && i > startingRowIndex) {
                        String letter = indexToLetter(j);
                        int second = intSwitch(i + 1);
                        possibleMoves.add(letter + second);
                        System.out.println("added: " + letter + second);
                    }
                }
            }
        }

        // Select a random move
        Random random = new Random();
        int randomIndex = random.nextInt(possibleMoves.size());
        System.out.println("end: " + possibleMoves.get(randomIndex));
        return possibleMoves.get(randomIndex);

    }

    private int intSwitch(int i) {
        if(i == 1)
            return 8;
        if(i == 2)
            return 7;
        if(i == 3)
            return 6;
        if(i == 4)
            return 5;
        if(i == 5)
            return 4;
        if(i == 6)
            return 3;
        if(i == 7)
            return 2;
        if(i == 8)
            return 1;
        return 0;
    }

    public String indexToLetter(int index) {
        if(index == 1)
            return "a";
        if(index == 2)
            return "b";
        if(index == 3)
            return "c";
        if(index == 4)
            return "d";
        if(index == 5)
            return "e";
        if(index == 6)
            return "f";
        if(index == 7)
            return "g";
        if(index == 8)
            return "h";
        return null;
    }


    

    public void pieceSelected(View v)
    {
        int tempID = v.getId();
        ImageView square = (ImageView) findViewById(tempID);
        String tempSquare = v.getResources().getResourceName(tempID);
        tempSquare = tempSquare.substring(tempSquare.length()-2);
        if (start==null)
        {
            if(square.getDrawable()==null || ChessBoard.getType(tempSquare)!=playerTurn)
            {
                Toast toast = Toast.makeText(this, "Please Select your own piece", Toast.LENGTH_SHORT);
                toast.setMargin(15,15);
                toast.show();
            }
            else {
                start = tempSquare;
                square.setColorFilter(Color.GREEN, PorterDuff.Mode.OVERLAY);
            }
        } else if (start.equals(tempSquare)) {
            start = null;
            square.setColorFilter(Color.TRANSPARENT);
        } else {
            end = tempSquare;
            movePieceSelected();
        }

        //System.out.println(start + end);
    }

    public void movePieceSelected()
    {
        ImageView tempView;
        int imgID;
        String moveString = start + " " + end;
        ChessBoard.updateBoard(moveString, playerTurn);

        if(ChessBoard.legal == true)
        {
            playerTurn = !playerTurn;
            gameMoves.add(moveString);
            tempView = (ImageView) findViewById(getResources().getIdentifier(start, "id", getPackageName()));
            //tempView.setImageDrawable(null);
            tempView.clearColorFilter();
            start = null;
            end = null;
            undoable = true;
            updateScreen();

            if(ChessBoard.isCheck(ChessBoard.chessBoard,playerTurn) == true)
            {
                if(playerTurn == true)
                {
                    Toast toast = Toast.makeText(this, "White King in Check", Toast.LENGTH_SHORT);
                    toast.setMargin(15,15);
                    toast.show();
                }
                else if(playerTurn == false)
                {
                    Toast toast = Toast.makeText(this, "Black King in Check", Toast.LENGTH_SHORT);
                    toast.setMargin(15,15);
                    toast.show();
                }

            }
        }
        else if (ChessBoard.legal == false)
        {
            Toast toast = Toast.makeText(this, "Invalid Move. Try Again", Toast.LENGTH_SHORT);
            toast.setMargin(15,15);
            toast.show();
            tempView = (ImageView) findViewById(getResources().getIdentifier(start, "id", getPackageName()));
            tempView.clearColorFilter();
            start = null;
            end = null;
        }


    }

    //updates the screen visually when a move is made
    public void updateScreen()
    {
        ChessBoard.printBoard(ChessBoard.chessBoard);

        ImageView tempView;
        int imgID;
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j <8; j++)
                {
                    if(ChessBoard.chessBoard[i][j].getPieceName().equals("Tile")== false)
                    {
                        tempView = (ImageView) findViewById(getResources().getIdentifier(String.valueOf((char)(j+97))+(8-i), "id", getPackageName()));
                        imgID = getResources().getIdentifier(ChessBoard.chessBoard[i][j].getPieceID(), "drawable", getPackageName());
                        tempView.setImageResource(imgID);
                    }
                    else if(ChessBoard.chessBoard[i][j].getPieceName().equals("Tile")== true)
                    {
                        tempView = (ImageView) findViewById(getResources().getIdentifier(String.valueOf((char)(j+97))+(8-i), "id", getPackageName()));
                        tempView.setImageDrawable(null);
                    }
                }
            }

        String t ="";
        TextView turn = (TextView)findViewById(R.id.txt_turn);
        if(playerTurn == true)
            t = "White's";
        else
            t = "Black's";
        turn.setText(t + " turn");
        System.out.println(gameMoves);

    }


    public void undoMove()
    {
        if(gameMoves==null || gameMoves.size()<1)
        {
            Toast toast = Toast.makeText(this, "Nothing to Undo!", Toast.LENGTH_SHORT);
            toast.setMargin(15,15);
            toast.show();
        }
        else {
            gameMoves.remove(gameMoves.size() - 1);
            undoable = false;
            ChessBoard.undoBoard();
            playerTurn = !playerTurn;
            updateScreen();
        }
    }

    public void undoMaxWarning()
    {
        Toast toast = Toast.makeText(this, "Can't undo again!", Toast.LENGTH_SHORT);
        toast.setMargin(15,15);
        toast.show();
    }

    public void startingBoard() {
        ImageView tempView;
        int imgID;
        for (int i = 0; i < 8; i++) {
            tempView = (ImageView) findViewById(getResources().getIdentifier(String.valueOf((char) (i + 97)) + 7, "id", getPackageName()));
            imgID = getResources().getIdentifier("bp", "drawable", getPackageName());
            tempView.setImageResource(imgID);
            //System.out.println(String.valueOf((char)(i+97))+7);
        }

        tempView = (ImageView) findViewById(getResources().getIdentifier("a8", "id", getPackageName()));
        imgID = getResources().getIdentifier("br", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("b8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bn", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("c8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bb", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("d8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bq", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("e8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bk", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("f8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bb", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("g8", "id", getPackageName()));
        imgID = getResources().getIdentifier("bn", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        tempView = (ImageView) findViewById(getResources().getIdentifier("h8", "id", getPackageName()));
        imgID = getResources().getIdentifier("br", "drawable", getPackageName());
        tempView.setImageResource(imgID);

        for (int i = 0; i < 8; i++) {
            tempView = (ImageView) findViewById(getResources().getIdentifier(String.valueOf((char) (i + 97)) + 2, "id", getPackageName()));
            imgID = getResources().getIdentifier("wp", "drawable", getPackageName());
            tempView.setImageResource(imgID);
        }

            tempView = (ImageView) findViewById(getResources().getIdentifier("a1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wr", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("b1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wn", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("c1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wb", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("d1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wq", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("e1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wk", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("f1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wb", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("g1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wn", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            tempView = (ImageView) findViewById(getResources().getIdentifier("h1", "id", getPackageName()));
            imgID = getResources().getIdentifier("wr", "drawable", getPackageName());
            tempView.setImageResource(imgID);

            String t ="";
            TextView turn = (TextView)findViewById(R.id.txt_turn);
            if(playerTurn == true)
                t = "White's";
            else
                t = "Black's";
            turn.setText(t + " turn");
    }

}
