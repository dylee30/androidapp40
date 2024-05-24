package com.example.androidchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import com.example.androidchess.savedata.SavedgameDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = (Button)findViewById(R.id.btn_start);
        SavedgameDatabase.context = getApplicationContext();
        SavedgameDatabase.readGameList();

            start.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(MainActivity.this, GameScreen.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            });

        final Button saved = (Button)findViewById(R.id.btn_saved);
        saved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println(SavedgameDatabase.gameList);
                Bundle bundle = new Bundle();
                Intent i = new Intent(MainActivity.this, SavedList.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        final Button quit = (Button)findViewById(R.id.btn_quit);
        quit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}