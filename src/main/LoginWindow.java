package main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frame;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
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
		
		txtUserName = new JTextField();
		txtUserName.setBounds(210,60, 540, 50);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(210,150, 540, 50);
		frame.getContentPane().add(txtPassword);
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtUserName.getText();
				String password = new String(txtPassword.getPassword());
				DataBaseManager d = new DataBaseManager();
				User u = d.getUser(userName);
				if (u == null) {
				    JOptionPane.showMessageDialog(null, "User nicht gefunden!");
				    return;
				}
				String hashedInput = d.hashPassword(password);
				if (hashedInput.equals(u.getPasswordHash())) {
					SimpleFileDatabase mainWindow = new SimpleFileDatabase(u.getDirectory());
					mainWindow.show();
					frame.dispose();
				} else {
				    JOptionPane.showMessageDialog(null, "Falsches Passwort!");
				}
			}
		});
		btnLogin.setBounds(322, 302, 328, 79);
		frame.getContentPane().add(btnLogin);
		frame.setBounds(400, 200, 1920/2, 1080/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
