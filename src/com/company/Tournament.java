package com.company;

import java.io.File;
import java.util.ArrayList;

public class Tournament {

private FileIO fileIO = new FileIO();
private com.company.TextUI textUI = new com.company.TextUI();
ArrayList<Team> teams;
ArrayList<Match> matches;
String time;
Result result = new Result();
private float matchTime;

    public Tournament(){
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        teams = fileIO.readTeamData();
        matches = fileIO.readMatchData(teams);
    }


    public void tournamentMenu() {
        textUI.writeToUser("Hello! Welcome! This is the startmenu.");
        int i= Integer.MAX_VALUE;
        String[] split;
        while (i != -1) {
            i = Integer.parseInt(textUI.getUserInput("Press 0 to open menu: "));
            switch (i) {
                case 0:
                    textUI.writeToUser("Here u will see the menu: " +
                            "\nPress 1 to register tournament time. " +
                            "\nPress 2 to register team. " +
                            "\nPress 3 to register the lineup for the first 4 games. " +
                            "\nPress 4 to register the semifinals. " +
                            "\nPress 5 to register the final match. " +
                            "\nPress 6 to register wins." +
                            "\nPress 7 to show team data."+
                            "\nPress 8 to show the positioning of the teams."+
                            "\nPress 9 to show the current matches registered in the tournament.");
                    break;
                case 1:
                    textUI.writeToUser("All arguments should be typed as decimal numbers for example 8 am = 8.00. 30 min = 0.50, 1 hour = 1.00");
                    float start = Float.parseFloat(textUI.getUserInput("When does the tournament start?"));
                    matchTime = Float.parseFloat(textUI.getUserInput("How long should a match last?"));
                    float pauseTime = Float.parseFloat(textUI.getUserInput("How long should the breaks in between matches be? "));
                    setTime(String.valueOf(start+((matchTime+pauseTime)*7)));

                    System.out.println(time);
                    break;
                case 2:
                    ArrayList<String> data = new ArrayList<>();
                    data = textUI.registerTeam();
                    Team newTeam = createTeam(data);
                    teams.add(newTeam);
                    System.out.println(teams);
                    fileIO.writeTeamData(teams);

                    break;
                case 3:
                    for (int j =1;j<5;j++) {

                        String team1 = textUI.getUserInput("This is match number "+ j+ ".\nInsert the teamname of the first team");
                        Match matchTemp = new Match();
                        matchTemp.setMatchNumber(j);
                        matchTemp.setTeam1(searchTeam(team1));
                        String team2 = textUI.getUserInput("Insert the teamname of the second team");
                        matchTemp.setTeam2(searchTeam(team2));
                        float tmpStartTime = Float.parseFloat(textUI.getUserInput("All arguments should be typed as decimal numbers for example 8 am = 8.00. 30 min = 0.50, 1 hour = 1.00\n" +
                                "Write the start time of this match"));
                        matchTemp.setTime(tmpStartTime,tmpStartTime+matchTime);
                        matches.add(matchTemp);
                        System.out.println(matches);
                    }
                    fileIO.writeMatchData(matches);
                    break;
                case 4:
                    for (int j =5;j<7;j++) {
                        split = textUI.getUserInput("What teams should play the semifinals against each other?" +
                                "\nMatch number " + j + " is played by: ").split(" ");
                        Match matchTemp = new Match();
                        matchTemp.setMatchNumber(j);
                        matchTemp.setTeam1(searchTeam(split[0]));
                        matchTemp.setTeam2(searchTeam(split[1]));
                        matches.add(matchTemp);
                        System.out.println(matches);
                    }
                    break;
                case 5:
                    split = textUI.getUserInput("What two teams should play  the finals against each other?").split(" ");
                    Match matchTemp = new Match();
                    matchTemp.setMatchNumber(7); // 7 because it is the final match
                    matchTemp.setTeam1(searchTeam(split[0]));
                    matchTemp.setTeam2(searchTeam(split[1]));
                    matches.add(matchTemp);
                    System.out.println(matches);
                    break;
                case 6:
                    int k = Integer.parseInt(textUI.getUserInput("1,2,3,4 ( first matches ) 5,6 ( semifinals ), 7 (final match)" +
                            "\nWhich match do you want to add result to? "));
                    result.addResultToTeam(matches.get(k-1),textUI);
                    System.out.println(matches.get(k-1).getTeam1().teamScore + " " + matches.get(k-1).getTeam2().teamScore);
                    split = textUI.getUserInput("What was the goal points of both teams? Write it as (team 1) - (team 2)").split(" - ");
                    result.addGoalPointsToTeam(matches.get(k-1),textUI,Integer.parseInt(split[0]),Integer.parseInt(split[1]));
                    break;
                case 7:
                    textUI.writeToUser("These are the current teams in the tournament: " + teams);
                    break;
                case 8:
                    textUI.writeToUser("These are the teams current positions in the tournament: ");
                    result.checkTeamsPositions(teams);
                    System.out.println(teams);
                    break;
                case 9:
                    textUI.writeToUser("These are the current matches registered:\n" + matches);
                    break;
                case 10:
                    writeDB();
                    break;
            }
        }
    }

    /*public Tournament(FileIO fileIO, TextUI textUI, ArrayList<Team> teams, Team team) {
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

    }*/

    private Team createTeam(ArrayList<String> teamPlayers){
        String teamName = teamPlayers.get(0);
        teamPlayers.remove(0);
        Team team = new Team(teamName,teamPlayers,0,0,0,teams.size());
        return team;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void createMatches(){
        textUI.getUserInput("Match one is played by: ");
        System.out.println("Match two is played by: ");
        System.out.println("Match three is played by: ");
        System.out.println("Match four is played by: ");
    }

    public Team searchTeam(String searchName){
        for (int i=0; i<teams.size();i++){
            if (teams.get(i).getTeamName().equals(searchName)){
                return teams.get(i);
            }
        }
        return searchTeam(textUI.getUserInput("The team was spelled wrong or doesn´t exist\nTry again!"));
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
    public void writeDB(){
        DatabaseIO databaseIO = new DatabaseIO();
        databaseIO.writeTeamData(teams);
    }
}
