package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    ArrayList<Team> readTeamData() {
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
            Team tmpTeam = new Team(currentTeamData[0],currentPlayers,Integer.parseInt(currentTeamData[currentTeamSize+2]),Integer.parseInt(currentTeamData[currentTeamSize+3]));
            currentTeams.add(tmpTeam);
        }
        return currentTeams;

    }

    public void writeTeamData(ArrayList<Team> teams) {
        String teamData = "teamName, teamSize, playerNames, score, position\n";
        for (int i=0;i<teams.size();i++) {
            teamData+= "" + teams.get(i).getTeamName() + ", " + teams.get(i).teamPlayers.size() + ", " + teams.get(i).getTeamPlayersasString() + teams.get(i).getTeamScore() +", "+ teams.get(i).getTeamPosition() + "\n";
        }
        try {
            FileWriter output = new FileWriter("src/com/company/teamData.txt");
            output.write(teamData);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Match> loadData() {
    File file = new File("src/matchdata.txt");
    ArrayList<Match> matchResult = new ArrayList<>();
    try {
        Scanner scan = new Scanner(file);
        int i = 0;
        String header = scan.nextLine();

        while (scan.hasNextLine())
        {
            String[] matchData = scan.nextLine().split("tmp ");
            Match match = new Match();
            //match.setTeam1(matchData[0]);
            //match.setTeam2(matchData[0]);
        }
    } catch (FileNotFoundException e) {
        matchResult = null;
    }
    return matchResult;
}
}


