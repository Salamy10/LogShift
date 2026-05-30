package main;

import model.Formulas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

public class LilCalc {

	private JFrame frame;
	private JTextField txtA;
	private JTextField txtB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LilCalc window = new LilCalc();
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
	public LilCalc() {
		initialize();
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(400, 200, 1920/2, 1080/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtA = new JTextField();
		txtA.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtA.setBounds(268, 126, 143, 70);
		frame.getContentPane().add(txtA);
		txtA.setColumns(10);
		
		txtB = new JTextField();
		txtB.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtB.setBounds(509, 126, 152, 70);
		frame.getContentPane().add(txtB);
		txtB.setColumns(10);
		
		JLabel lblSign = new JLabel("sign");
		lblSign.setBackground(new Color(128, 128, 128));
		lblSign.setToolTipText("Sign");
		lblSign.setBounds(453, 154, 29, 14);
		frame.getContentPane().add(lblSign);
		
		JLabel lblAns = new JLabel("Answer");
		lblAns.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAns.setBounds(737, 126, 125, 70);
		frame.getContentPane().add(lblAns);
		

		Formulas f = new Formulas();
		
		JButton btnADD = new JButton("ADD");
		btnADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setA(Integer.parseInt(txtA.getText()));
				f.setB(Integer.parseInt(txtB.getText()));
				lblAns.setText(Integer.toString(f.add()));
			}
			
		});
	
		
		btnADD.setBounds(268, 260, 232, 70);
		frame.getContentPane().add(btnADD);
		
		JButton btnSubtract = new JButton("SUBTRACT");
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setA(Integer.parseInt(txtA.getText()));
				f.setB(Integer.parseInt(txtB.getText()));
				lblAns.setText(Integer.toString(f.subtract()));
			}
		});
		btnSubtract.setBounds(510, 260, 232, 70);
		frame.getContentPane().add(btnSubtract);
		
		JLabel lblEquals = new JLabel("=");
		lblEquals.setBounds(696, 154, 46, 14);
		frame.getContentPane().add(lblEquals);
		
		JButton btnDivide = new JButton("DIVIDE");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setA(Integer.parseInt(txtA.getText()));
				f.setB(Integer.parseInt(txtB.getText()));
				lblAns.setText(Integer.toString(f.divide()));
			}
		});
		btnDivide.setBounds(268, 353, 232, 70);
		frame.getContentPane().add(btnDivide);
		
		JButton btnMultiply = new JButton("MULTIPLY");
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setA(Integer.parseInt(txtA.getText()));
				f.setB(Integer.parseInt(txtB.getText()));
				lblAns.setText(Integer.toString(f.multiply()));
			}
		});
		btnMultiply.setBounds(510, 353, 232, 70);
		frame.getContentPane().add(btnMultiply);
		
		
	}
}
