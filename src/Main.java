import java.awt.*;
import javax.swing.*;

public class Main extends JPanel{
	
	public Main () {
		setBackground(Color.BLACK);
        setFocusable(true);
	}
	
	public static void main(String[] args) {		
		JFrame fenster = new JFrame("LilGame");
        fenster.setSize(1920, 1080);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.add(new Main());
        fenster.setVisible(true);	
	}

}
