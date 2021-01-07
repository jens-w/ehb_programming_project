package ehb.programmingproject.coursequiz.DB;

import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {

    private Statement stmt = null;

    public void InsertUser(String userkey, Long snowflake){
        try {
            DBConnection dbconn = new DBConnection();

            stmt = dbconn.getConnection().createStatement();

            String sql = String.format(
                    "IF NOT EXISTS (SELECT * FROM Users WHERE Userkey='%s' OR Snowflake=%d) " +
                    "BEGIN " +
                    "INSERT INTO Users (Userkey, Snowflake)"+
                    " VALUES ('%s', '%d') " +
                    "END", userkey, snowflake, userkey, snowflake);

            stmt.executeUpdate(sql);
            dbconn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(stmt !=null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
