package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseIO implements IO {
    Connection connection = null;

    public void createConnection() {

        String JdbcUrl = "jdbc:mysql://127.0.0.1:3306/sp3+?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "Lampen04aug"; // Helenas Password
        //String password = "Mysql1238Code18"; // Jamies Password
        //String password = "Solskin#12";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);




        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void closeConnection()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertInfoToDB() {
        String insertTeamName = "INSERT INTO team (name,score,teamPosition,goalPoints) VALUES ('SUPER','1','2','3')";
        try {
            PreparedStatement query1 = connection.prepareStatement(insertTeamName);
            var query1Result = query1.executeUpdate();
            System.out.println(query1Result);
        } catch (SQLException a) {
            a.printStackTrace();
        }
    }

    public void readInfoFromDB() {
        String getTeamName = "SELECT * FROM team";
        try {
            PreparedStatement query2 = connection.prepareStatement(getTeamName);
            var query2Result = query2.executeQuery();

            while (query2Result.next()) {
                System.out.println(query2Result.getString(2));
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
    }

    @Override
    public ArrayList<Team> readTeamData()
    {
        createConnection();
        String getTeamName = "SELECT * FROM team";
        try {
            PreparedStatement query2 = connection.prepareStatement(getTeamName);
            var query2Result = query2.executeQuery();

            while (query2Result.next()) {
                System.out.println(query2Result.getString(2));
               // Team tmpteam = new Team(query2Result.getString(2));
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
        ArrayList<Team> teamsDB = new ArrayList<>();
        closeConnection();
        return teamsDB;

    }

    @Override
    public void writeTeamData(ArrayList<Team> teams)
    {
        createConnection();
        String teamData;
        try {
            PreparedStatement query1;
            for (int i = 0; i < teams.size(); i++) {
                teamData = "INSERT INTO team (name,score,teamPosition,goalPoints) VALUES ('" + teams.get(i).getTeamName() + "','" + teams.get(i).getTeamScore() + "','" + teams.get(i).getTeamPosition() + "','" + teams.get(i).getGoalPoints()+"')";
                query1 = connection.prepareStatement(teamData);
                var query1Result = query1.executeUpdate();
                System.out.println(query1Result);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    @Override
    public void writeMatchData(ArrayList<Match> matches)
    {
        createConnection();
        String matchData;
        try {
            PreparedStatement query1;
            for (int i = 0; i < matches.size(); i++) { //startTime, endTime, team1, team2, matchNumber
                matchData = "INSERT INTO match (startTime,endTime,team1,team2) VALUES ('" + matches.get(i).getStartTime() + "','" + matches.get(i).getEndTime() + "','" + matches.get(i).getTeam1() + "','" + matches.get(i).getTeam2()+"')";
                query1 = connection.prepareStatement(matchData);
                var query1Result = query1.executeUpdate();
                System.out.println(query1Result);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    @Override
    public ArrayList<Match> readMatchData(ArrayList<Team> teams)
    {
        return null;
    }
}