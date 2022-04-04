package com.company;

public class Match {
    private int startTime;
    private int endTime;
    private Team team1;
    private Team team2;

    // constructor
    public Match(int startTime, int endTime, Team team1, Team team2){
        this.startTime = startTime;
        this.endTime = endTime;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getMatchTime(){ // give a string tells the start and end time of this match
        return  "The match takes place at this: " + startTime + "-" + endTime;
    }

    public Team getWinningTeam(TextUI textUI){ // register the winning team
        String str = textUI.getUserInput("What team won the match press 1 for team 1 and 2 for team 2.");
        if (!str.equals("1") && !str.equals("2")) {
            textUI.writeToUser("you can only write 1 or 2.");
            getWinningTeam(textUI);
        }
        if(str.equals("1")){
            return team1;
        }
            return team2;
    }
}
