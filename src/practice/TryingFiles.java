package practice;

import java.io.File;

public class TryingFiles {
	public static void main(String[] args) {
		
		File ordner = new File("C:\\Git\\LogShift\\LilDataBase");
		File[] dateien = ordner.listFiles();
		

		for (File datei : dateien) {
			System.out.print("→ " + datei.getName() + "\n");
			
			
			if(datei.isDirectory()) {
				
				File unterordner = new File("C:\\Git\\LogShift\\LilDataBase\\" + datei.getName());
				File[] unterdateien = unterordner.listFiles();
				
				for (File unterdatei : unterdateien) {
					System.out.print(" ↪ " + unterdatei.getName() + "\n");
				}
			}
		}
		
		
	}
		

}
