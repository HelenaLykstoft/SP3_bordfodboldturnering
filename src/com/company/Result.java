package com.company;

import java.util.ArrayList;

public class Result {

    public void addResultToTeam(Match match,TextUI textUI){
        Team winningTeam = match.getWinningTeam(textUI); // register the winning team in the match
        winningTeam.addToTeamScore(); // adds 2 points to the winning team
    }
    public void addGoalPointsToTeam(Match match, TextUI textui, int team1Goals, int team2Goals){
        match.getTeam1().addGoalPoints(team1Goals,team2Goals);
        match.getTeam2().addGoalPoints(team2Goals,team1Goals);
    }
    public void checkTeamsPositions(ArrayList<Team> teams){
        for (int i = 0; i<teams.size()-1; i++){
            for(int j = i+1; j<teams.size(); j++){
                if (teams.get(i).getTeamScore()<teams.get(j).getTeamScore()){
                    Team tempteam = teams.get(i);
                    teams.set(i,teams.get(j));
                    teams.set(j,tempteam);
                }else if (teams.get(i).getTeamScore()==teams.get(j).getTeamScore() && teams.get(i).getGoalPoints() <= teams.get(j).getGoalPoints()){
                    Team tempteam = teams.get(i);
                    teams.set(i,teams.get(j));
                    teams.set(j,tempteam);
                }
            }
        }
        for (int i = 0; i < teams.size(); i++){
            teams.get(i).setTeamPosition(i+1);
        }
    }
}
