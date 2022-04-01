package com.company;

import java.util.ArrayList;

public class Tournament {

private fileIO fileio = new fileio();
ArrayList<String> team1 = new ArrayList<>("Kenneth", "Kenny", "Penny");
Team team = new Team("Triple Fire Cyclops", team1,  0, 0);


    public void startMatch(){

    }
    public void endMatch(){
    fileIO.saveData(team);
    }
    public void currentMatch(){

    }
    public void getMatchHistory(){

    }
}
