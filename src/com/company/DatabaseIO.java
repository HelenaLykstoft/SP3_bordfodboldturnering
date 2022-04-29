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

    public void closeConnection() {
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
            //System.out.println(query1Result);
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
                //System.out.println(query2Result.getString(2));
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
    }

    @Override
    public ArrayList<Team> readTeamData() {
        ArrayList<Team> teamsDB = new ArrayList<>();
        createConnection();
        String getTeamName = "SELECT * FROM team";
        try {
            PreparedStatement query2 = connection.prepareStatement(getTeamName);
            var query2Result = query2.executeQuery();

            while (query2Result.next()) {
                //System.out.println(query2Result.getString(2));
                ArrayList<Player> tempPlayers = selectPlayerNames(selectIDfromTeam(query2Result.getString(2)));
                Team tmpTeam = new Team(query2Result.getString(2), tempPlayers, query2Result.getInt(3), query2Result.getInt(4), query2Result.getInt(5), query2Result.getInt(1));
                teamsDB.add(tmpTeam);
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }

        closeConnection();
        return teamsDB;

    }

    public ArrayList<Player> selectPlayerNames(int teamID) {
        ArrayList<Player> players = new ArrayList<>();
        Player tmpPlayer;
        String selectName = "SELECT playerID FROM teamplayer WHERE teamID = '" + teamID + "'";
        try {
            PreparedStatement query2 = connection.prepareStatement(selectName);
            var query2Result = query2.executeQuery();
            while (query2Result.next()) {
                selectName = "SELECT playerName FROM player WHERE ID = '" + query2Result.getInt(1) + "'";
                PreparedStatement query2ButTotallyNOTQuery2 = connection.prepareStatement(selectName);
                var query2ButTotallyNOTQuery2RESULTButNOTReallyResultFromQuery2 = query2ButTotallyNOTQuery2.executeQuery();
                if (query2ButTotallyNOTQuery2RESULTButNOTReallyResultFromQuery2.next()) {
                    tmpPlayer = new Player(query2ButTotallyNOTQuery2RESULTButNOTReallyResultFromQuery2.getString(1));
                    players.add(tmpPlayer);
                }
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
        return players;
    }

    @Override
    public void writeTeamData(ArrayList<Team> teams,ArrayList<Player> unregiPlayer) {
        createConnection();
        String teamData;
        try {
            PreparedStatement query1;

            for (int i = 0; i < teams.size(); i++) {
                teamData = "INSERT INTO team (name,score,teamPosition,goalPoints) VALUES ('" + teams.get(i).getTeamName() + "','" + teams.get(i).getTeamScore() + "','" + teams.get(i).getTeamPosition() + "','" + teams.get(i).getGoalPoints() + "')";
                query1 = connection.prepareStatement(teamData);
                var query1Result = query1.executeUpdate();
                int teamID = selectIDfromTeam(teams.get(i).getTeamName());

                writePlayerData(teams.get(i).getTeamPlayers(), teamID);
                //System.out.println(query1Result);

            }
            writeUnregiPlayerData(unregiPlayer);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    @Override
    public void writeMatchData(ArrayList<Match> matches) {
        createConnection();
        String matchData;
        try {
            PreparedStatement query1;
            for (int i = 0; i < matches.size(); i++) { //startTime, endTime, team1, team2, matchNumber
                matchData = "INSERT INTO match (startTime,endTime,team1,team2) VALUES ('" + matches.get(i).getStartTime() + "','" + matches.get(i).getEndTime() + "','" + matches.get(i).getTeam1() + "','" + matches.get(i).getTeam2() + "')";
                query1 = connection.prepareStatement(matchData);
                var query1Result = query1.executeUpdate();
                //System.out.println(query1Result);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

    }

    @Override
    public ArrayList<Match> readMatchData(ArrayList<Team> teams) {
        return null;
    }

    public void writePlayerData(ArrayList<Player> players, int teamID) {
        // createConnection();
        String playerData;
        try {
            PreparedStatement query4;
            for (int i = 0; i < players.size(); i++) {
                playerData = "INSERT INTO player (playerName,hasTeam) VALUES ('" + players.get(i).getName()+"','1')";
                query4 = connection.prepareStatement(playerData);
                var query1Result = query4.executeUpdate();
                int playerID = selectIDfromPlayer(players.get(i).getName());
                insertIntoTeamPlayer(playerID, teamID);
                //System.out.println(query1Result);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // closeConnection();
    }

    public void insertIntoTeamPlayer(int playerID, int teamID) {
        PreparedStatement query5;
        try {
            String teamPlayerData = "INSERT INTO teamPlayer (playerID, teamID) VALUES ('" + playerID + "','" + teamID + "')";
            query5 = connection.prepareStatement(teamPlayerData);
            var query5Result = query5.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();

        }
    }

    public int selectIDfromTeam(String teamName) {
        int teamID = Integer.MIN_VALUE;
        String selectID = "SELECT ID FROM team WHERE name ='" + teamName + "'";
        try {
            PreparedStatement query2 = connection.prepareStatement(selectID);
            var query2Result = query2.executeQuery();
            if (query2Result.next()) {
                teamID = query2Result.getInt(1);
            }

        } catch (SQLException b) {
            b.printStackTrace();
        }
        return teamID;
    }

    public int selectIDfromPlayer(String playerName) {
        int playerID = Integer.MIN_VALUE;
        String selectID = "SELECT ID FROM player WHERE playerName ='" + playerName + "'";
        try {
            PreparedStatement query2 = connection.prepareStatement(selectID);
            var query2Result = query2.executeQuery();
            if (query2Result.next()) {
                playerID = query2Result.getInt(1);
            }
        } catch (SQLException b) {
            b.printStackTrace();
        }
        return playerID;
    }

    public ArrayList<String> searchForTeam(String teamName) {
        createConnection();
        ArrayList<String> teamNamesLike = new ArrayList<>();
        String searchTeamName = "SELECT name FROM team WHERE name LIKE '%" + teamName + "%'";
        try {
            PreparedStatement query8 = connection.prepareStatement(searchTeamName);
            var query2Result = query8.executeQuery();
            while (query2Result.next()) {
                teamNamesLike.add(query2Result.getString(1));

            }
        } catch (SQLException q) {
            q.printStackTrace();
        }
        closeConnection();
        return teamNamesLike;
    }

    public ArrayList<String> searchForPlayer(String teamPlayers) {
        createConnection();
        ArrayList<String> teamPlayersLike = new ArrayList<>();
        String searchPlayerName = "SELECT playerName FROM player WHERE playerName LIKE '%" + teamPlayers + "%'";
        try {
            PreparedStatement query9 = connection.prepareStatement(searchPlayerName);
            var query2Result = query9.executeQuery();
            while (query2Result.next()) {
                teamPlayersLike.add(query2Result.getString(1));

            }
        } catch (SQLException q) {
            q.printStackTrace();
        }
        closeConnection();
        return teamPlayersLike;
    }


    public void truncateTablesInfo() {
        createConnection();
        String truncateTables = "SELECT * FROM team;";
        try {
            PreparedStatement query112 = connection.prepareStatement(truncateTables);
            query112.executeQuery();
            truncateTables = "TRUNCATE TABLE team;";
            PreparedStatement query113 = connection.prepareStatement(truncateTables);
            query113.executeUpdate();
            truncateTables = "SELECT * FROM player;";
            PreparedStatement query114 = connection.prepareStatement(truncateTables);
            query114.executeQuery();
            truncateTables = "TRUNCATE TABLE player;";
            PreparedStatement query115 = connection.prepareStatement(truncateTables);
            query115.executeUpdate();
            truncateTables = "SELECT * FROM teamplayer;";
            PreparedStatement query116 = connection.prepareStatement(truncateTables);
            query116.executeQuery();
            truncateTables = "TRUNCATE TABLE teamplayer;";
            PreparedStatement query117 = connection.prepareStatement(truncateTables);
            query117.executeUpdate();
        } catch (SQLException w) {
            w.printStackTrace();
        }
    }

    public void addPlayer(Player player) {
        createConnection();
        String addPlayer;
        try {
            PreparedStatement query4;
            addPlayer = "INSERT INTO player (playerName,hasTeam) VALUES ('" + player.getName() + "','0')";
            query4 = connection.prepareStatement(addPlayer);
            var query1Result = query4.executeUpdate();
            //System.out.println(query1Result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    public ArrayList<Player> selectUnregistered() {
        createConnection();
        String select;
        ArrayList<Player> unregPlay = new ArrayList<>();
        Player tmpplayer;
        try {
            PreparedStatement query7;
            select = "SELECT playerName FROM player WHERE hasTeam = '0'";
            query7 = connection.prepareStatement(select);
            var query7result = query7.executeQuery();
            //System.out.println(query7result);
            while (query7result.next()) {
                tmpplayer = new Player(query7result.getString(1));
                unregPlay.add(tmpplayer);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        closeConnection();
        return unregPlay;
    }
    public void writeUnregiPlayerData(ArrayList<Player> UnregiPlayers) {
        // createConnection();
        String playerData;
        try {
            PreparedStatement query4;
            for (int i = 0; i < UnregiPlayers.size(); i++) {
                playerData = "INSERT INTO player (playerName,hasTeam) VALUES ('" + UnregiPlayers.get(i).getName()+"','0')";
                query4 = connection.prepareStatement(playerData);
                var query1Result = query4.executeUpdate();
                //System.out.println(query1Result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // closeConnection();
    }

}