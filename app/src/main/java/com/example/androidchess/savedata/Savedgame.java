package com.example.androidchess.savedata;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Savedgame implements Serializable {

    private String gameTitle;
    private Calendar time;
    private ArrayList<String> moves;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Savedgame(String gameTitle, ArrayList<String> gameMoves)
    {
        this.time = Calendar.getInstance();
        this.gameTitle = gameTitle;
        this.moves = gameMoves;
    }

    public String getTitle()
    {
        return gameTitle;
    }

    public String getDate()
    {
        return DATE_FORMAT.format(time.getTime());
    }

    public ArrayList<String> getMoves()
    {
        return moves;
    }

    public String printInfo()
    {
        String output = gameTitle + " , " + DATE_FORMAT.format(time.getTime());
        return output;
    }

}
