package redlaboratory.pzkoreanchatpatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) {
		try {
			PrintStream stream = new PrintStream(new FileOutputStream(new File("log.txt")));
			System.setOut(stream);
			System.setErr(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Installer installer = new Installer();
		installer.start();
	}

}
