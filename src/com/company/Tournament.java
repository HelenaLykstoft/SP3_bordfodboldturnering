package com.company;

import java.util.ArrayList;

public class Tournament {

private FileIO fileIO = new FileIO();
private com.company.TextUI textUI = new com.company.TextUI();
ArrayList<Team> teams = new ArrayList<>();
private Match match;


    public Tournament(FileIO fileIO, TextUI textUI, ArrayList<Team> teams, Team team) {
        this.fileIO = fileIO;
        this.textUI = textUI;
        this.teams = teams;

        ArrayList<String> teamPlayers = new ArrayList<>();
        teamPlayers =  fileIO.readTeamData(); // every index contains the following: teamName, playerNames, score, position


        if(teamPlayers == null)
        {
            System.out.println("error -- please insert team data");
            //an error message that displays in case of faulty data input.

            //fetching data from the TEAM class, aka. Asking user for data input.
            teamPlayers = team.getTeamPlayers(); //User will input the names of the individual players.
        }
        this.createTeam(teamPlayers);

    }

    private void createTeam(ArrayList<String> teamPlayers){
        textUI.registerTeam("####-//-MESSAGETEST-//-####");
    }




    public void startMatch()
    {


    }
    public void endMatch(){

    }
    public void currentMatch(){

    }
    public void getMatchHistory(){

    }
}
