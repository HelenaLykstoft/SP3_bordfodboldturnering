package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    ArrayList<String> readTeamData() {
        File file = new File("src/teamData.txt");
        ArrayList<String> teamData = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            int i = 0;

            String firstline = scan.nextLine();

            while (scan.hasNextLine()) {
                teamData.add(scan.nextLine()); //team name + player + score + position.
                i++; //
            }
        } catch (FileNotFoundException e) {
            teamData = null;
        }
        return teamData;

    }
    public ArrayList<String> writeTeamData() {
        return readTeamData();

    }

    public void saveData(Team team) {
        String teamData = "teamName, playerNames, score, position\n";
        for (String player : team.teamPlayers) {
            teamData = "" + team.getTeamName() + ", " + team.getTeamPlayers() + ", " + team.getTeamPosition() + "\n";
        }
        try {
            FileWriter output = new FileWriter("src/teamData.txt");
            output.write(teamData);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

