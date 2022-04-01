package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TextUI textUI = new TextUI();
        ArrayList<String> players = new ArrayList<>();
        players.add("Jamie");
        players.add("Helena");
        Team team1 = new Team("Poggers",players,0,0);
        Team team2 = new Team("Kappa", players,0,0);
        Match match = new Match(10,11,team1,team2);
        System.out.println(match.getMatchTime());
        match.getWinningTeam(textUI);
    }
}
