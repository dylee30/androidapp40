package com.example.androidchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidchess.savedata.Savedgame;
import com.example.androidchess.savedata.SavedgameDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class SavePrompt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveprompt);
        final Button save = (Button)findViewById(R.id.btn_save);
        final Button exit = (Button)findViewById(R.id.btn_exit);
        final EditText textBox = (EditText) findViewById(R.id.title_box);

        ArrayList<String> gameMoves = (ArrayList<String>) getIntent().getSerializableExtra("complete");


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println(gameMoves);
                if(textBox.getText().toString() == null || textBox.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(SavePrompt.this, "Please enter a title!!", Toast.LENGTH_SHORT);
                    toast.setMargin(15,15);
                    toast.show();
                }
                String gameTitle = textBox.getText().toString();
                Savedgame temp = new Savedgame(gameTitle, gameMoves);
                    SavedgameDatabase.writeGameList(temp);
                    textBox.setText("");
                    Toast toast = Toast.makeText(SavePrompt.this, "Game saved", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent i = new Intent(SavePrompt.this, MainActivity.class);
                    startActivity(i);




            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle bundle = new Bundle();
                Intent i = new Intent(SavePrompt.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });



    }
}