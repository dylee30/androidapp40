package com.example.androidchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidchess.app.ChessBoard;
import com.example.androidchess.savedata.SavedgameDatabase;

import java.util.ArrayList;

public class PlaybackScreen extends AppCompatActivity {
    boolean playerTurn = true;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playback);
        startingBoard();
        ArrayList<String> moves =(ArrayList<String>) getIntent().getStringArrayListExtra("moves");
        ChessBoard.buildBoard();
        final Button nextMove = (Button)findViewById(R.id.btn_nextmove);
        TextView turn = (TextView)findViewById(R.id.txt_turn);
        TextView currMove = (TextView)findViewById(R.id.txt_turn2);
        nextMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(index < moves.size())
                {
                    movePieceSelected(moves.get(index));
                    currMove.setText(moves.get(index));
                    updateScreen();
                    index++;
                }
                else if(index >= moves.size())
                {
                    Toast toast = Toast.makeText(PlaybackScreen.this, "Game is over", Toast.LENGTH_SHORT);
                    toast.show();
                    String x ="";

                    if(playerTurn == false)
                        x = "White";
                    else
                        x = "Black";
                    turn.setText(x + " wins!");
                }
            }
        });

    }


    public void movePieceSelected(String moveString)
    {
        TextView turn = (TextView)findViewById(R.id.txt_turn);
        if(moveString.equals("resign"))
        {
            return;
        }
        else if(moveString.equals("draw"))
        {
            turn.setText("Ended in draw");
        }
        else
        {
            ChessBoard.updateBoard(moveString, playerTurn);

            playerTurn = !playerTurn;
            updateScreen();
        }

    }

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
        TextView turn = (TextView)findViewById(R.id.txt_turn);
        String t ="";





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


    }
}