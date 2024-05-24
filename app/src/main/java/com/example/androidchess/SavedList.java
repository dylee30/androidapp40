package com.example.androidchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidchess.savedata.Savedgame;
import com.example.androidchess.savedata.SavedgameDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SavedList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedlist);

        final Button sortTitle = (Button)findViewById(R.id.btn_sorttitle);
        final Button sortDate = (Button)findViewById(R.id.btn_sortdate);
        ListView list = findViewById(R.id.gameslist);
        String[] gameListArr = new String[SavedgameDatabase.gameList.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.blank, gameListArr);
        String[] titleArr = new String[SavedgameDatabase.gameList.size()];

        if(SavedgameDatabase.gameList != null)
        {
            for(int i = 0; i < SavedgameDatabase.gameList.size(); i++)
            {
                gameListArr[i] = SavedgameDatabase.gameList.get(i).printInfo();
            }


            list.setAdapter(adapter);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                Savedgame replay = SavedgameDatabase.gameList.get(pos);
                ArrayList<String> moves = new ArrayList<>();
                moves = replay.getMoves();
                System.out.println(moves);
                Bundle bundle = new Bundle();
                Intent i = new Intent(SavedList.this, PlaybackScreen.class);
                i.putExtra("moves", moves);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        sortTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Arrays.sort(gameListArr);


                list.setAdapter(adapter);

            }
        });

        sortDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String temp = "";
                for(int i = 1; i<SavedgameDatabase.gameList.size(); i++)
                {
                    if(SavedgameDatabase.gameList.get(i).getDate().compareToIgnoreCase(SavedgameDatabase.gameList.get(i-1).getDate())<0)
                    {
                        temp = SavedgameDatabase.gameList.get(i).getDate();
                        gameListArr[i] = gameListArr[i-1];
                        gameListArr[i-1] = temp;

                    }
                }

                list.setAdapter(adapter);

            }
        });


    }
}