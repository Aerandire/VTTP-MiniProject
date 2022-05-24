package vttp.project.keefe.repositories;

public interface Queries {
    
    public static final String SQL_SELECT_EMAIL = 
        "SELECT * FROM user WHERE email = ?";
    
    public static final String SQL_INSERT_USER = 
        "INSERT INTO user (email,name,password) values (?,?,?)";

    public static final String SQL_DELETE_USER = 
        "DELETE FROM user WHERE email = ?";

    public static final String SQL_INSERT_ACCOUNT =
        "INSERT INTO accounts (email,site,password,user_id) values (?,?,?,?)";

    public static final String SQL_DELETE_ACCT = 
        "DELETE FROM accounts WHERE site = ? and user_id=?";

    public static final String SQL_SELECT_ACCTS = 
        "SELECT * FROM accounts WHERE user_id=?";

}
