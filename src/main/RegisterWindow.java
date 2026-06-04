package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegisterWindow {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnRegister_1;
	private JButton btnToLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.GRAY);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(210,60, 540, 50);
		frame.getContentPane().add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(210,150, 540, 50);
		frame.getContentPane().add(txtPassword);
		
		JComboBox<String> cbRole = new JComboBox<>();
		cbRole.addItem("user");
		cbRole.addItem("admin");
		cbRole.setBounds(322, 232, 328, 26);
		frame.getContentPane().add(cbRole);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = (String) cbRole.getSelectedItem();
				String userName = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				
				// 1. Eingaben prüfen
				if (userName.isEmpty() || password.isEmpty()) { 
					JOptionPane.showMessageDialog(null, "A Field Is Empty!");
					return;
				}

				// 2. Passwort hashen
				DataBaseManager db = new DataBaseManager();
				String hash = db.hashPassword(password);

				// 3. Unterordner erstellen
				File userOrdner = new File("LilDataBase", userName);
				userOrdner.mkdir();

				// 4. User in Datenbank speichern
				User newUser = new User(userName, hash, userOrdner.getPath(), role);
				db.addUser(newUser);

				// 5. Erfolgsmeldung und Fenster schließen
				JOptionPane.showMessageDialog(null, "User created :)");
				frame.dispose();
				LoginWindow mainWindow = new LoginWindow();
				mainWindow.show();
			}
		});
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setBounds(322, 302, 328, 79);
		frame.getContentPane().add(btnRegister);
		
		btnToLogin = new JButton("Back to Login");
		btnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				LoginWindow mainWindow = new LoginWindow();
				mainWindow.show();							
				frame.dispose();
			}
		});
		btnToLogin.setBackground(Color.LIGHT_GRAY);
		btnToLogin.setBounds(322, 393, 328, 43);
		frame.getContentPane().add(btnToLogin);
		
		
		
		frame.setBounds(400, 200, 1920/2, 1080/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void show() {
	    frame.setVisible(true);
	}
	public void hide() {
		frame.setVisible(false);
	}
}
