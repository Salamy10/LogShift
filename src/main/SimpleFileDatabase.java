package main;

import java.awt.EventQueue;

import javax.swing.*;

public class SimpleFileDatabase {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleFileDatabase window = new SimpleFileDatabase();
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
	public SimpleFileDatabase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	String[] spalten = {"Name", "Größe", "Datum"};
	Object[][] daten = {
			{"test.txt", "12 KB", "heute"},
			{"todo.txt", "3 KB", "gestern"}
	};
	
	JTable tabelle = new JTable(daten, spalten);

}
