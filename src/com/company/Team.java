package com.company;

import java.util.ArrayList;

public class Team {
    private String teamName;
    ArrayList<String> teamPlayers;
    int teamScore;
    int teamPosition;

    public Team (String teamName, ArrayList<String> teamPlayers, int teamScore, int teamPosition){
        this.teamName = teamName;
        this.teamPlayers = teamPlayers;
        this.teamScore = teamScore;
        this.teamPosition = teamPosition;
    }

    @Override
    public String toString(){

        return this.teamName;
    }
    public String getTeamName(){
        return this.teamName;
    }
    public ArrayList<String> getTeamPlayers(){
        return this.teamPlayers;
    }

    public String getTeamPlayersasString(){
        String temp="";
        for(int i=0;i<teamPlayers.size();i++){
            temp += teamPlayers.get(i) + ", ";
        }
        return temp;
    }

    public int getTeamScore(){
        return this.teamScore;
    }
    public int getTeamPosition(){
        return this.teamPosition;
    }

    public void addToTeamScore() {
       this.teamScore += 2;
    }
}
