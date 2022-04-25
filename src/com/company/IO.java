package com.company;

import java.util.ArrayList;

public interface IO {

    ArrayList<Team> readTeamData();

    void writeTeamData(ArrayList<Team> teams);

    void writeMatchData(ArrayList<Match> matches);

    ArrayList<Match> readMatchData(ArrayList<Team> teams);
}
