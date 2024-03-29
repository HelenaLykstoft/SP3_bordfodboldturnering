package com.company;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> teamPlayers;
    private int teamScore;
    private int teamPosition;
    private int goalPoints;
    private int seed;

    public Team (String teamName, ArrayList<Player> teamPlayers, int teamScore, int teamPosition, int goalPoints, int seed){
        this.teamName = teamName;
        this.teamPlayers = teamPlayers;
        this.teamScore = teamScore;
        this.teamPosition = teamPosition;
        this.goalPoints = goalPoints;
        this.seed = seed;
    }

    @Override
    public String toString(){

        return("\nTeams:"+ this.teamName+" "+"Score: "+ this.teamScore+" "+"Position in tournament: "+this.teamPosition+" "+"Goal points: "+this.goalPoints +"\nPlayer on team "+ this.teamName +": "+ getTeamPlayersasString()+"\n");
    }
    public String getTeamName(){
        return this.teamName;
    }
    public ArrayList<Player> getTeamPlayers(){
        return this.teamPlayers;
    }

    public String getTeamPlayersasString(){
        String temp="";
        for(int i=0;i<teamPlayers.size();i++){
            temp += teamPlayers.get(i) + ", ";
        }
        return temp;
    }
    public void removePlayerFromTeam(int removeTeamPlayer){
        teamPlayers.remove(removeTeamPlayer);
    }

    public void addPlayerToTeam(Player player){
        teamPlayers.add(player);
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

    public int getGoalPoints(){
        return goalPoints;
    }

    public void addGoalPoints(int teamGoals, int opponentGoals){
        goalPoints += teamGoals-opponentGoals;
    }

    public void setTeamPosition(int position){
        this.teamPosition = position;
    }

    public int getSeed() {
        return seed;
    }
}
