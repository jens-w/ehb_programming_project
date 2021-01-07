package ehb.programmingproject.coursequiz.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection conn = null;

    public void close(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public Connection getConnection() throws SQLException{
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=CourseQuiz_Discord;integratedSecurity=true;");
        }
        return conn;
    }
}
