package ehb.programmingproject.coursequiz.DB;

import ehb.programmingproject.coursequiz.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBReader {
    private Statement stmt = null;

    public User GetUser(Long snowflake){
        User user = new User();

        try {
            DBConnection dbconn = new DBConnection();

            stmt = dbconn.getConnection().createStatement();
            String sql = String.format("SELECT * FROM Users WHERE Snowflake = '%d'", snowflake);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                user = new User(rs.getString("Userkey"), rs.getLong("Snowflake"));
            }

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

        return user;
    }
}
