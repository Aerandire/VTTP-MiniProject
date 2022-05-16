package vttp.project.keefe.repositories;

public interface Queries {
    
    public static final String SQL_SELECT_EMAIL = 
        "SELECT * FROM user WHERE email = ?";
    
    public static final String SQL_INSERT_USER = 
        "INSERT INTO user (email,name,password) values (?,?,?)";
}
