package com.example.androidchess.savedata;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class SavedgameDatabase implements Serializable {

    public static final String storeDir = "dat";
    public static final String storeFile = "gameList.dat";
    public static Context context;
    public static ArrayList<Savedgame> gameList = new ArrayList<>();

    public ArrayList<Savedgame> getGameList()
    {
        return gameList;
    }

    public void addGame(Savedgame g)
    {
        gameList.add(g);
    }


    public static void readGameList(){
        File gameFile = new File(context.getFilesDir(), storeFile);
        ObjectInputStream ois;
        if (gameFile.exists()) {
            try {
                ois = new ObjectInputStream(context.openFileInput(storeFile));
                gameList = (ArrayList<Savedgame>) ois.readObject();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            gameList = new ArrayList<Savedgame>();
        }


    }


    public static void writeGameList(Savedgame g){
        gameList.add(g);
        try {
            ObjectOutput oos = new ObjectOutputStream(context.openFileOutput(storeFile, Context.MODE_PRIVATE));
            oos.writeObject(gameList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
