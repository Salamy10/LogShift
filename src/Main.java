import java.awt.*;
import javax.swing.*;

public class Main extends JPanel{
	
	public Main () {
		setBackground(Color.BLACK);
        setFocusable(true);
	}
	
	public static void main(String[] args) {		
		JFrame window = new JFrame("LilGame");
		window.setSize(1920, 1080);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new Main());
        window.setVisible(true);	
	}

}
