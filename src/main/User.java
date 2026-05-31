package main;

public class User {
    private String userName;
    private String passwordHash;
    private String directory;
    private String role;  // "admin" oder "user"
    
    public User(String userName, String passwordHash, String directory, String role) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.directory = directory;
        this.role = role;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getPasswordHash() {
    	return passwordHash;
    }
    
    public String getDirectory() {
    	return directory;
    }
    
    public String getRole() {
    	return role;
    }
    
    public boolean isAdmin() {
    	return role.equals("admin");
    }
}
