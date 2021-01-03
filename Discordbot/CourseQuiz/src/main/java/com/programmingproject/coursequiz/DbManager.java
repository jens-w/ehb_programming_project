package com.programmingproject.coursequiz;

import java.sql.*;
import java.sql.DriverManager;

public class DbManager {
    private static String dbURL ="jdbc:sqlserver://localhost;databaseName=coursequiz_discord;integratedSecurity=true;";
    private static Connection conn = null;

    public static void InsertUser(String userkey, String snowflake){
        try {
            conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO Users (Userkey, Snowflake) VALUES ('%s', '%s')", userkey, snowflake);
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String SelectUserkeyWithSnowflake(String snowflake){

        String userkey = null;

        try {
            conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            String sql = String.format("select Userkey from Users where Snowflake = '%s'", snowflake);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userkey = rs.getString("Userkey");
            }

        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        return userkey;
    }
}
