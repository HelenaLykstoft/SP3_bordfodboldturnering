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
       // String password = "Lampen04aug"; // Helenas Password
        //String password = "Mysql1238Code18"; // Jamies Password
        String password = "Solskin#12";

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
        ArrayList<Team> teamsDB = new ArrayList<>();
        createConnection();
        String getTeamName = "SELECT * FROM team";
        try {
            PreparedStatement query2 = connection.prepareStatement(getTeamName);
            var query2Result = query2.executeQuery();

            while (query2Result.next()) {
                System.out.println(query2Result.getString(2));
                //Team tmpteam = new Team("asda",);
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }

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
                int teamID = selectIDfromTeam(teams.get(i).getTeamName());

                writePlayerData(teams.get(i).getTeamPlayers(),teamID);
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

    public void writePlayerData(ArrayList<Player> players,int teamID)
    {
       // createConnection();
        String playerData;
        try {
            PreparedStatement query4;
            for (int i = 0; i < players.size(); i++) {
                playerData = "INSERT INTO player (playerName) VALUES ('" + players.get(i).getName()+ "')";
                query4 = connection.prepareStatement(playerData);
                var query1Result = query4.executeUpdate();
                int playerID = selectIDfromPlayer(players.get(i).getName());
                insertIntoTeamPlayer(playerID,teamID);
                System.out.println(query1Result);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       // closeConnection();
    }
    public void insertIntoTeamPlayer(int playerID, int teamID)
    {
        PreparedStatement query5;
        try {
            String teamPlayerData = "INSERT INTO teamPlayer (playerID, teamID) VALUES ('" + playerID + "','" + teamID + "')";
            query5 = connection.prepareStatement(teamPlayerData);
            var query5Result = query5.executeUpdate();
        }catch(SQLException a) {
            a.printStackTrace();

        }
    }
    public int selectIDfromTeam(String teamName)
    {
        int teamID = Integer.MIN_VALUE;
        String selectID = "SELECT ID FROM team WHERE name ='" + teamName + "'";
        try {
            PreparedStatement query2 = connection.prepareStatement(selectID);
            var query2Result = query2.executeQuery();
            if(query2Result.next()) {
                teamID = query2Result.getInt(1);
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
        return teamID;
    }
    public int selectIDfromPlayer(String playerName)
    {
        int playerID = Integer.MIN_VALUE;
        String selectID = "SELECT ID FROM player WHERE playerName ='" + playerName + "'";
        try {
            PreparedStatement query2 = connection.prepareStatement(selectID);
            var query2Result = query2.executeQuery();
            if(query2Result.next()) {
                playerID = query2Result.getInt(1);
            }
        } catch (SQLException b) {
            b.printStackTrace();
        }
        return playerID;
    }

}