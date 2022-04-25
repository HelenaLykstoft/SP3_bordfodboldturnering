package com.company;

public class Match {
    private float startTime;
    private float endTime;
    private Team team1;
    private Team team2;
    private int matchNumber;

    public String getMatchTime(){
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

    public void setMatchNumber(int i){
        this.matchNumber = i;
    }

    public int getMatchNumber(){
        return this.matchNumber;
    }


    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public Team getTeam1(){
        return this.team1;
    }
    public Team getTeam2(){
        return this.team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public void setTime(float startTime, float endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public float getStartTime() {
        return startTime;
    }

    @Override
    public String toString(){
        return "team1: " + team1+ " team2: "  + team2 + " match start time: " + startTime + " match end time: " + endTime;
    }

}
