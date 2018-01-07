package redlaboratory.pzkoreanchatpatch;

import java.io.File;

import redlaboratory.pzkoreanchatpatch.Installer;

import org.junit.*;
import static org.junit.Assert.*;

public class RegTest {

	@Test
	public void myTest() {
		File pzFolder = Installer.getPZFolder();

		System.out.println("Found ProjectZomboid: " + pzFolder);
	}

}
