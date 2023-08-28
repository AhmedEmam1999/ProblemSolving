package com.Package;

import java.sql.*;

public class DBConnection {
    private static final String url="jdbc:postgresql://localhost:5432/DataBase";
    private static final String user="postgres";
    private static final String password="8520";

    private static String ShowAll(String query)
    {
        query="Select * from Employee";
        Statement statement;
        return query;
    }
    public static void main(String[] args) throws SQLException {
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(url,user,password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (connection != null) {
            String s="select * from \"schema\".\"Employee\";";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(s);
            resultSet.next();
            System.out.println(resultSet.getString(2));
            resultSet.next();
            System.out.println("Connected.");
        }
        else
            System.out.println("Not Connected.");
    }
}
