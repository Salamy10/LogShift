package main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SimpleFileDatabase {

	private JFrame frame;
	private JTable table;

	String pfad = "LilDataBase";
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnDirectorieChooser;
	private Object[][] ladeOrdner(String pfad) {
		
	    File datei = new File(pfad);
	    File[] dateien = datei.listFiles();
	    
	    if (dateien == null) {
	        return new Object[0][3];  // leeres Array, kein Absturz
	    }
	    
	    Object[][] daten = new Object[dateien.length][3];
        

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
	    for (int i = 0; i < dateien.length; i++) {
	        daten[i][0] = dateien[i].getName();  // Name
	        daten[i][1] = dateien[i].length();  // Größe in Bytes 
	        
	        //datumsformat von millisec in dd.mm.yyyy ändern
		    long millisekunden = dateien[i].lastModified();
	        String datum = sdf.format(millisekunden); 
	        daten[i][2] = datum;  // Datum
	    }
	    
	    return daten;
	}
	
	
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
		frame.setBounds(400, 200, 1920/2, 1080/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		String[] spalten = {"Name", "Größe", "Datum"};
		Object[][] daten = ladeOrdner(pfad);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(daten, spalten);
		table = new JTable(model);
		table.setBounds(49, 41, 832, 106);
		table.setBackground(new Color(192, 192, 192));
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 52, 924, 438);
		frame.getContentPane().add(scrollPane);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		btnAdd = new JButton("Hinzufügen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Dateiname:");
				String dataType = null;
				if (name == null || name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Kein Name eingegeben.");
					return;
				} 
				if (!name.contains(".")) {

					dataType = JOptionPane.showInputDialog("DatenTyp (.txt, .docx, ...):");
					// Punkt entfernen
					if (dataType.startsWith(".")) {
					    dataType = dataType.substring(1);  
					}
					String fulfilled = name + "." + dataType;
					File toAdd = new File(pfad + "\\" + fulfilled);
					try {
					    toAdd.createNewFile();
					} catch (IOException ex) {
					    ex.printStackTrace();
					}					
					long millisekunden = toAdd.lastModified();
			        String datum = sdf.format(millisekunden);
					Object[] neueZeile = {toAdd.getName(), toAdd.length(), datum};
					model.addRow(neueZeile);
				}
				else {
					
					File toAdd = new File(pfad + "\\" + name);
					try {
					    toAdd.createNewFile();
					} catch (IOException ex) {
					    ex.printStackTrace();
					}
					long millisekunden = toAdd.lastModified();
			        String datum = sdf.format(millisekunden);
					Object[] neueZeile = {toAdd.getName(), toAdd.length(), datum};
					model.addRow(neueZeile);
				}
			}
		});
		btnAdd.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnDelete = new JButton("Löschen");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int zeile = table.getSelectedRow();
				
				if (zeile == -1) {
					JOptionPane.showMessageDialog(null, "Kein Element ausgewählt.");
				} else { 
					String dateiName = (String) table.getModel().getValueAt(zeile, 0);
					File zuLoeschen = new File(pfad + "\\" + dateiName);
					zuLoeschen.delete();
					model.removeRow(zeile);
					JOptionPane.showMessageDialog(null, "Element aus Zeile " + zeile + " gelöscht.");
				}
			}
		});
		btnDelete.setBounds(109, 11, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		btnDirectorieChooser = new JButton("Ordner suchen");
		btnDirectorieChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int ergebnis = chooser.showOpenDialog(null);
				if (ergebnis == JFileChooser.APPROVE_OPTION) {
					File gewaehlterOrdner = chooser.getSelectedFile();
					pfad = gewaehlterOrdner.getPath();  // pfad aktualisieren
					model.setRowCount(0);               // alle Zeilen leeren
					Object[][] neueDaten = ladeOrdner(pfad);
					for (Object[] zeile : neueDaten) {
					    model.addRow(zeile);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Kein Ordner ausgewählt");
					return;
				}
			}
		});
		btnDirectorieChooser.setBounds(831, 11, 103, 23);
		frame.getContentPane().add(btnDirectorieChooser);
	}
}
