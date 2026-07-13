package main;

public class Main {
	public static void main(String[] args) {
	    DataBaseManager db = new DataBaseManager();
	    String hash = db.hashPassword("admin");
	    User admin = new User("admin", hash, "LilDataBase", "admin");
	    db.addUser(admin);
	    System.out.println("Admin erstellt!");
	}

}
