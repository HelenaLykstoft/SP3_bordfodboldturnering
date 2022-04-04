package com.company;

import java.util.ArrayList;

public class Tournament {

private FileIO fileIO = new FileIO();
private com.company.TextUI textUI = new com.company.TextUI();
ArrayList<Team> teams = new ArrayList<>();
private Match match;


    public void tournamentMenu(){
        textUI.writeToUser("Hello! Welcome! This is the startmenu.");
        int i=0;
        textUI.getUserInput("Here u will see the menu: " +
                "\nPress 1 to register tournament time. " +
                "\nPress 2 to register team. " +
                "\nPress 3 to register the lineup for the first 4 games. " +
                "\nPress 4 to register the semifinals. " +
                "\nPress 5 to register the final match. " +
                "\nPress 6 to register wins.");
        switch (i){
            case 1:
                registerTime();
                break;
            case 2:
                ArrayList<String>  data= new ArrayList<>();
                data = textUI.registerTeam("Create your team here. Type your teamname and all names of the members: ");
                Team newTeam = createTeam(data);
                teams.add(newTeam);
                break;
            case 3:
                textUI.getUserInput("Which teams should play against eachother? Type (teamname),(teamname). (teamname),(teamname).");
                break;
            case 4:
                textUI.getUserInput("Which teams should play semifinals against eachother? Type (teamname),(teamname). ");
                break;
            case 5:
                textUI.getUserInput("Which two teams should play in the finals? Seperat them with a comma. ");
                break;
            case 6:
                textUI.getUserInput("Which match do you want to add result to? ");
                // 1,2,3,4 ( første kampe ) 5,6 ( semifinale ), 7 (finale)
                textUI.getUserInput("Who won? xx or xx?");
        }
    }
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

    private Team createTeam(ArrayList<String> teamPlayers){
        String teamName = teamPlayers.get(0);
        teamPlayers.remove(0);
        Team team = new Team(teamName,teamPlayers,0,0);
        return team;
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
