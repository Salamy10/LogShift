package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
    private Connection connection;
    
    public DataBaseManager() {
        connect();
        try {
            Class.forName("org.sqlite.JDBC");  // ← Treiber registrieren
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        createTable();
    }
    
    private void connect() {
         try {
        	 connection = DriverManager.getConnection("jdbc:sqlite:users.db");
         } catch (SQLException ex) {
        	    ex.printStackTrace();
        	}
    }
    
    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
        		+ "        	    userName TEXT,"
        		+ "        	    passwordHash TEXT,"
        		+ "        	    directory TEXT,"
        		+ "        	    role TEXT"
        		+ "        	)";
        
        try {
        	connection.createStatement().execute(sql);
        } catch (SQLException ex) {
    	    ex.printStackTrace();
    	}
    }
    
    public void addUser(User user) {
        String sql = "INSERT INTO users (userName, passwordHash, directory, role) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getDirectory());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public User getUser(String userName) {
        String sql = "SELECT * FROM users WHERE userName = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getString("userName"),
                    rs.getString("passwordHash"),
                    rs.getString("directory"),
                    rs.getString("role")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;  // User nicht gefunden
    }
    
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
