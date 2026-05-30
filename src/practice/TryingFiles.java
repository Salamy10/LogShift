package practice;

import java.awt.Desktop;
import java.io.File;

public class TryingFiles {
	public static void main(String[] args) {
		
		File ordner = new File("LilDataBase");
		File[] dateien = ordner.listFiles();
		

		for (File datei : dateien) {
			System.out.print("→ " + datei.getName() + "\n");
			
			
			if(datei.isDirectory()) {
				
				File unterordner = new File("LilDataBase\\" + datei.getName());
				File[] unterdateien = unterordner.listFiles();
				
				for (File unterdatei : unterdateien) {
					System.out.print(" ↪ " + unterdatei.getName() + "\n");
				}
			}
		}
		
		
		
		System.out.println();
		if(Desktop.isDesktopSupported()) {
			System.out.println(true);
		}else {System.out.println(false);}
		
		
	}
	
	
		

}
