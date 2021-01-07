package ehb.programmingproject.coursequiz.Models;

public class User {
    private String userkey;
    private Long snowflake;

    public User(){}

    public User(Long snowflake){
        this.snowflake = snowflake;
    }

    public User(String userkey, Long snowflake){
        this.userkey = userkey;
        this.snowflake = snowflake;
    }

    public String getUserkey() {
        return userkey;
    }

    public Long getSnowflake() {
        return snowflake;
    }

    //-------------------

    public boolean isRegistered() {
        return this.getUserkey() != null;
    }

    //USERKEY TESTDATA
    //rghRtk4lZmJLTsFJFBQ8
    //ogIM23NkPlfdxeeGZeX0
}
