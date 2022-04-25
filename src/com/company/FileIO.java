package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IO{
    public ArrayList<Team> readTeamData() {
        File file = new File("src/com/company/teamData.txt");
        ArrayList<String> teamData = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            int i = 0;

            String header = scan.nextLine();

            while (scan.hasNextLine()) {
                teamData.add(scan.nextLine()); //team name+ teamSize + player + score + position.
                i++; //
            }
        } catch (FileNotFoundException e) {
            teamData = null;
        }
        ArrayList<Team> currentTeams = new ArrayList<>();
        for (int i = 0; i < teamData.size(); i++)
        {
            String[] currentTeamData = teamData.get(i).split(", ");
            int currentTeamSize = Integer.parseInt(currentTeamData[1]);

            ArrayList<String> currentPlayers = new ArrayList<>();


            for(int j = 2; j < currentTeamSize+2; j++)
            {
                currentPlayers.add(currentTeamData[j]);
            }
            Team tmpTeam = new Team(currentTeamData[0],currentPlayers,Integer.parseInt(currentTeamData[currentTeamSize+2]),Integer.parseInt(currentTeamData[currentTeamSize+3]),Integer.parseInt(currentTeamData[currentTeamSize+4]),Integer.parseInt(currentTeamData[currentTeamSize+5]));
            currentTeams.add(tmpTeam);
        }
        return currentTeams;

    }

    public void writeTeamData(ArrayList<Team> teams) {
        String teamData = "teamName, teamSize, playerNames, score, position, goalPoints, seed\n";
        for (int i=0;i<teams.size();i++) {
            teamData+= "" + teams.get(i).getTeamName() + ", " + teams.get(i).teamPlayers.size() + ", " + teams.get(i).getTeamPlayersasString() + teams.get(i).getTeamScore() +", "+ teams.get(i).getTeamPosition() +", "+ teams.get(i).getGoalPoints() + ", " + teams.get(i).getSeed()+"\n";
        }
        try {
            FileWriter output = new FileWriter("src/com/company/teamData.txt");
            output.write(teamData);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Match> readMatchData(ArrayList<Team> teams) {
    File file = new File("src/com/company/matchdata.txt");
    ArrayList<Match> matchResult = new ArrayList<>();
    try {
        Scanner scan = new Scanner(file);
        int i = 0;
        String header = scan.nextLine();

        while (scan.hasNextLine())
        {
            String[] matchData = scan.nextLine().split(", ");
            Match match = new Match();
            match.setTime(Float.parseFloat(matchData[0]),Float.parseFloat(matchData[1]));
            match.setTeam1(teams.get(Integer.parseInt(matchData[2])));
            match.setTeam2(teams.get(Integer.parseInt(matchData[3])));
            match.setMatchNumber(Integer.parseInt(matchData[4]));
            matchResult.add(match);
        }
    } catch (FileNotFoundException e) {
        matchResult = null;
    }
    return matchResult;
}
    public void writeMatchData(ArrayList<Match> matches){
        String matchData = "startTime, endTime, team1, team2, matchNumber\n";
        for (int i=0;i<matches.size();i++) {
            matchData+= "" + matches.get(i).getStartTime()+ ", " + matches.get(i).getEndTime() + ", " + matches.get(i).getTeam1().getSeed() + ", " + matches.get(i).getTeam2().getSeed() +", "+ matches.get(i).getMatchNumber() +"\n";
        }
        try {
            FileWriter output = new FileWriter("src/com/company/matchData.txt");
            output.write(matchData);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


