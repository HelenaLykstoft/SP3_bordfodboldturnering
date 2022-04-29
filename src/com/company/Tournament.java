package com.company;

import java.util.ArrayList;

public class Tournament {

private FileIO fileIO = new FileIO();
private com.company.TextUI textUI = new com.company.TextUI();
private ArrayList<Team> teams;
private ArrayList<Match> matches;
private String time;
private Result result = new Result();
private float matchTime;
private DatabaseIO databaseIO = new DatabaseIO();
private ArrayList<Player> unregisteredPlayers;

    public Tournament(){
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        teams = databaseIO.readTeamData();
        matches = fileIO.readMatchData(teams);
        unregisteredPlayers = databaseIO.selectUnregistered();
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
                            "\nPress 3 to register player. " +
                            "\nPress 4 to register the lineup for the first 4 games. " +
                            "\nPress 5 to register the semifinals. " +
                            "\nPress 6 to register the final match. " +
                            "\nPress 7 to register wins." +
                            "\nPress 8 to show team data."+
                            "\nPress 9 to show the positioning of the teams."+
                            "\nPress 10 to show the current matches registered in the tournament." +
                            "\nPress 11 to update the database." +
                            "\nPress 12 to read teamdata from the database."+
                            "\nPress 13 to search for a team."+
                            "\nPress 14 to search for a player."+
                            "\nPress 15 to wipe the database. WATCH OUT THO!!!!" +
                            "\nPress 16 to add unregistered players to a team." +
                            "\nPress 17 to remove players.");
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
                    String tempTeamName = data.get(0);
                    data.remove(0);
                    ArrayList<Player> tempPlayers = stringToPlayer(data);
                    Team newTeam = createTeam(tempTeamName,tempPlayers);
                    teams.add(newTeam);
                    System.out.println(teams);
                    fileIO.writeTeamData(teams,unregisteredPlayers);

                    break;
                case 3:
                    Player tmpplayer = textUI.registerPlayer();
                    unregisteredPlayers.add(tmpplayer);
                    showUnregisteredPlayers();
                    databaseIO.addPlayer(tmpplayer);
                    break;
                case 4:
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
                case 5:
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
                case 6:
                    split = textUI.getUserInput("What two teams should play  the finals against each other?").split(" ");
                    Match matchTemp = new Match();
                    matchTemp.setMatchNumber(7); // 7 because it is the final match
                    matchTemp.setTeam1(searchTeam(split[0]));
                    matchTemp.setTeam2(searchTeam(split[1]));
                    matches.add(matchTemp);
                    System.out.println(matches);
                    break;
                case 7:
                    int k = Integer.parseInt(textUI.getUserInput("1,2,3,4 ( first matches ) 5,6 ( semifinals ), 7 (final match)" +
                            "\nWhich match do you want to add result to? "));
                    result.addResultToTeam(matches.get(k-1),textUI);
                    System.out.println(matches.get(k-1).getTeam1().getTeamScore() + " " + matches.get(k-1).getTeam2().getTeamScore());
                    split = textUI.getUserInput("What was the goal points of both teams? Write it as (team 1) - (team 2)").split(" - ");
                    result.addGoalPointsToTeam(matches.get(k-1),textUI,Integer.parseInt(split[0]),Integer.parseInt(split[1]));
                    break;
                case 8:
                    textUI.writeToUser("These are the current teams in the tournament: " + teams);
                    break;
                case 9:
                    textUI.writeToUser("These are the teams current positions in the tournament: ");
                    result.checkTeamsPositions(teams);
                    System.out.println(teams);
                    break;
                case 10:
                    textUI.writeToUser("These are the current matches registered:\n" + matches);
                    break;
                case 11:
                    textUI.writeToUser("The database has been updated.\n");
                    writeDB();
                    break;
                case 12:
                    textUI.writeToUser("This is the current teams in the database:\n");
                    readDB();
                    break;
                case 13:
                    String searchTeam = textUI.getUserInput("Search for a team here:\n");
                    //databaseIO.searchForTeam(searchTeam);
                    textUI.writeToUser("This is the teams you searched for:\n"+databaseIO.searchForTeam(searchTeam));
                    break;
                case 14:
                    String searchPlayer = textUI.getUserInput("Search for a player here:\n");
                    textUI.writeToUser("This is the players you searched for:\n"+databaseIO.searchForPlayer(searchPlayer));
                    break;
                case 15:
                    String answer = textUI.getUserInput("DO YOU WANT TO WIPE YOUR DATABASE? Y / YES, N / NO");
                    if (answer.equalsIgnoreCase("y")){
                        databaseIO.truncateTablesInfo();
                        textUI.writeToUser("Your tables has now been wiped.");
                } else if (answer.equalsIgnoreCase("n")){
                        textUI.writeToUser("U chose not to wipe. Good choice.");
                    }
                    break;
                case 16:
                    textUI.writeToUser("Here are a list of unregistered players:" + unregisteredPlayers);
                    int playerNumber =Integer.parseInt(textUI.getUserInput("Who do u want to add to a team? 0 is the first player and so on."));
                    textUI.writeToUser("Here is a list of available teams:");
                    textUI.availableTeams(teams);
                    String teamName = textUI.getUserInput("Which team do u want to add the player to?");
                    addAPlayerToTeam(unregisteredPlayers.get(playerNumber),teamName,playerNumber);
                    textUI.writeToUser("Your member has now been registered to a team.");
                    break;
                case 17:
                    textUI.writeToUser("Here are a list of all teams.");
                    getTeams();
                    String removeFromWhichTeam =textUI.getUserInput("Which team do u want to remove from? Write the name of the team.");
                    int teamNumber = searchRemovableTeam(removeFromWhichTeam);
                    int removePlayerNumber = Integer.parseInt(textUI.getUserInput("Which player do u wish to remove? 0 is the first player and so on." + teams.get(teamNumber).getTeamPlayers()));
                    unregisteredPlayers.add(teams.get(teamNumber).getTeamPlayers().get(removePlayerNumber));
                    teams.get(teamNumber).removePlayerFromTeam(removePlayerNumber);
                    textUI.writeToUser("The member is now unregistered.");
                    break;
            }
        }
        writeDB();
        textUI.writeToUser("The data has been saved. Have a nice day :D uwu");
    }

    private Team createTeam(String teamName,ArrayList<Player> teamPlayers){
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

    public int searchRemovableTeam(String searchName){
        for (int i=0; i<teams.size();i++){
            if (teams.get(i).getTeamName().equals(searchName)){
                return i;
            }
        }
        return searchRemovableTeam(textUI.getUserInput("The team was spelled wrong or doesn´t exist\nTry again!"));
    }

    public void showUnregisteredPlayers(){
        textUI.writeToUser("Here are all the unregistered players: \n" + unregisteredPlayers);
        // Tildel spiller til et hold
    }

    public void writeDB(){
        databaseIO.truncateTablesInfo();
        databaseIO.writeTeamData(teams,unregisteredPlayers);
    }
    public void readDB(){
        teams = databaseIO.readTeamData();
    }

    public ArrayList<Player> stringToPlayer(ArrayList<String> stringArrayList){
        ArrayList<Player> players = new ArrayList<>();
        for(int i = 0; i < stringArrayList.size(); i++)
        {
            Player tempPlayer = new Player(stringArrayList.get(i));
            players.add(tempPlayer);
        }
        return players;
    }

    public void addAPlayerToTeam(Player player,String teamName,int playerNumber){
        int correctTeamNumber = Integer.MAX_VALUE;
        for (int i = 0;i<teams.size();i++){
            if(teams.get(i).getTeamName().equals(teamName)){
                correctTeamNumber=i;
            }
        }
        teams.get(correctTeamNumber).addPlayerToTeam(player);
        unregisteredPlayers.remove(playerNumber);
    }

    public void getTeams(){
        for (int i = 0;i< teams.size();i++){
            textUI.writeToUser(teams.get(i).getTeamName());
        }
    }

}
