package com.company;

import java.util.ArrayList;

public interface IO {
    void saveData(Team team);
    ArrayList<String> readTeamData();
    void writeTeamData();
}
