package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    Team team1;
    Team team2;
    TextUI textUI = new TextUI();
    ArrayList<String> players = new ArrayList<>();
    Match match;

    @BeforeEach
    void setUp() {
        players.add("Jamie");
        players.add("Helena");
        team1 = new Team("Poggers",players,0,0);
        team2 = new Team("Kappa", players,0,0);
        //match = new Match(10,11,team1,team2);

    }

    @Test
    void getMatchTime() {
        assertEquals("The match takes place at this: 10-11",  match.getMatchTime());
    }

    @Test
    void getWinningTeam() {
        //assertEquals("1",match.getWinningTeam(textUI));
    }
}