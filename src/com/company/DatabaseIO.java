package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseIO {
    Connection connection = null;

    public void createConnection() {

        String JdbcUrl = "jdbc:mysql://127.0.0.1:3306/sp3+?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        //String password = "Lampen04aug"; // helena password
        String password = "Mysql1238Code18";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);

            insertInfoToDB();
            readInfoFromDB();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void insertInfoToDB() {
        String insertTeamName = "INSERT INTO team (name,score,teamPoints,goalPoints) VALUES ('SUPER','1','2','3')";
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
}