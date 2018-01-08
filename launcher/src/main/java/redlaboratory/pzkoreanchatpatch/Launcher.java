package redlaboratory.pzkoreanchatpatch;

import java.io.*;
import java.lang.Runtime;
import java.lang.Process;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.github.sarxos.winreg.HKey;
import com.github.sarxos.winreg.WindowsRegistry;
import com.nosoop.json.VDF;

public class Launcher {

	private static final String[] patchFiles = {
			"redlaboratory/koreancore/KoreanCore.class",
			"redlaboratory/koreancore/Result.class",
			"zombie/core/Core.class",
			"zombie/core/Core$1.class"
	};

	private static final String[] jvmArgs = {
			"-Xms768m", "-Xmx768m",
			"-XX:-CreateMinidumpOnCrash", "-XX:-OmitStackTraceInFastThrow",
			"-Djava.library.path=./"
	};

	private static boolean flagSteam = false;
	private static boolean flagLogging = false;

	private static PrintStream defaultOut;
	private static PrintStream defaultErr;

	public static void main(String[] args) {
		for (String arg : args) {
		    if (arg.startsWith("--")) {
		    	switch (arg.substring(2)) {
		    		case "steam":
		    			flagSteam = true;
		    			break;
					case "logging":
						flagLogging = true;
						break;
				}
			}
		}

		try {
			if (flagLogging) {
				File logFile = new File("log.txt");
				FileOutputStream logStream = new FileOutputStream(logFile);
				PrintStream printStream = new PrintStream(logStream);

				defaultOut = System.out;
				defaultErr = System.err;

				System.setOut(printStream);
				System.setErr(printStream);
			}

			File pzFolder = getPZFolder();
			if (pzFolder == null) {
				System.out.println("Cannot find pzFolder");
				return;
			}
			System.out.println("PZFolder: " + pzFolder);

			String versionNumber = getVersionNumber(pzFolder);
			System.out.println("Installed PZ VersionNumber: " + versionNumber);

			File tmpClasspathDir = getTmpClasspathDir();
			for (String i : tmpClasspathDir.list()) {
				System.out.println("Create tmp file: " + i);
			}

			if (flagLogging) {
				System.setOut(defaultOut);
				System.setErr(defaultErr);
			}

			execute(pzFolder, tmpClasspathDir);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void execute(File pzFolder, File tmpClasspathDir) throws Exception {
		StringBuilder command = new StringBuilder();
		command.append("java");
		if (flagSteam) {
			command.append(" -Dzomboid.steam=1 -Dzomboid.znetlog=1");
		}
		for (String jvmArg : jvmArgs) {
			command.append(' ');
		    command.append(jvmArg);
		}
		command.append(" -cp ");
		command.append(tmpClasspathDir.getAbsolutePath());
		command.append(";jinput.jar;lwjgl.jar;lwjgl_util.jar;sqlite-jdbc-3.8.10.1.jar;uncommons-maths-1.2.3.jar;trove-3.0.3.jar;./");
		command.append(" zombie.gameStates.MainScreenState");
		System.out.println("Command: " + command);

		Process pro = Runtime.getRuntime().exec(command.toString(), null, pzFolder);

		InputStreamReader inputReader = new InputStreamReader(pro.getInputStream());
		BufferedReader bufInputReader = new BufferedReader(inputReader);

		InputStreamReader errorReader = new InputStreamReader(pro.getErrorStream());
		BufferedReader bufErrorReader = new BufferedReader(errorReader);

		String buf = null;
		while ((buf = bufInputReader.readLine()) != null) {
			System.out.println(buf);
		}
		while ((buf = bufErrorReader.readLine()) != null) {
			System.err.println(buf);
		}

		pro.waitFor();
	}

	public static File getTmpClasspathDir() {
	    File tmpDir = null;

	    try {
			tmpDir = Files.createTempDirectory(null).toFile();

	    	for (String patchFile : patchFiles) {
				InputStream resStream = Launcher.class.getResourceAsStream("/" + patchFile);
				if (resStream == null) {
					System.out.println("Failed to get resource: /" + patchFile);
					continue;
				}

				File tmpFile = new File(tmpDir, patchFile);
				tmpFile.getParentFile().mkdirs();
				tmpFile.createNewFile();

				FileOutputStream tmpFileOutput = new FileOutputStream(tmpFile);
				byte[] buf = new byte[1024];
				int read = 0;
				while ((read = resStream.read(buf)) > 0) {
					tmpFileOutput.write(buf, 0, read);
				}

				tmpFileOutput.close();
				resStream.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return tmpDir;
	}

	public static File getPZFolder() {
		List<File> libraries = getSteamLibraryFolders();

		for (File libFolder : libraries) {
			File pzFolder = new File(libFolder, "SteamApps\\Common\\ProjectZomboid");

			if (pzFolder.exists() && pzFolder.isDirectory()) {
				return pzFolder;
			}
		}

		return null;
	}

	public static List<File> getSteamLibraryFolders() {
		try {
			WindowsRegistry reg = WindowsRegistry.getInstance();
			String tree = "SOFTWARE\\VALVE\\STEAM";

			String value = reg.readString(HKey.HKCU, tree, "SteamPath");

			File steamDir = new File(value, "config/config.vdf");
			BufferedReader reader = new BufferedReader(new FileReader(steamDir));

			String stringRaw = "";
			for (String i = reader.readLine(); i != null; i = reader.readLine()) {
				stringRaw += i;
			}

			JSONObject jsonData = VDF.toJSONObject(stringRaw, false);
			JSONObject steam = jsonData
				.getJSONObject("InstallConfigStore")
				.getJSONObject("Software")
				.getJSONObject("Valve")
				.getJSONObject("Steam");

			ArrayList<File> list = new ArrayList<File>();

			for (int i = 1; ; i++) {
				try {
					String installFolderPath = steam.getString("BaseInstallFolder_" + i);
					File installFolder = new File(installFolderPath);
					
					list.add(installFolder);
				} catch (Exception e) {
					break;
				}
			}

			return list;
		} catch (Exception e) {
			System.err.println(e);

			return null;
		}
	}

	public static String getVersionNumber(File pzFolder) {
		try {
			PZClassLoader loader = new PZClassLoader(pzFolder);

			Class<?> result = loader.loadClass("zombie.core.Core");
			if (result == null) return null;

			Field field = result.getField("versionNumber");

			String versionNumber = (String) field.get(result.newInstance());

			return versionNumber;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		return null;
	}

}
