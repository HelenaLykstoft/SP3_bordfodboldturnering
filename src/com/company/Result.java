package com.company;

public class Result {

    public void addResultToTeam(Match match,TextUI textUI){
        Team winningTeam = match.getWinningTeam(textUI); // register the winning team in the match
        winningTeam.addToTeamScore(); // adds 2 points to the winning team
    }
}
