package redlaboratory.pzkoreanchatpatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.jar.Manifest;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Installer {
	
	private static final String appPath = "steamapps\\common\\ProjectZomboid";
	private static final String[] filesToInstall = {
			"redlaboratory/koreancore/KoreanCore.class",
			"redlaboratory/koreancore/Result.class",
			"zombie/core/Core.class",
			"zombie/core/Core$1.class"};
	private static final String[] filesToBackup = {
			"zombie/core/Core.class",
			"zombie/core/Core$1.class"
	};
	
	private InstallerGUI gui;
	
	public Installer() {
		initialize();
	}
	
	private void initialize() {
		gui = new InstallerGUI();
		
		String steamLibPath = getSteamFolder().getAbsolutePath();
//		String steamLibPath = "K:\\SteamLibrary\\";
		
		if (steamLibPath != null) {
			gui.pzFolder.setText(steamLibPath.concat("\\") + appPath);
		}
		
		gui.checkInstalledPZVersion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				File pzFolder = new File(gui.pzFolder.getText());
				
				String versionNumber = getVersionNumber(pzFolder);
				if (versionNumber != null) {
					JOptionPane.showMessageDialog(gui, "" + versionNumber);
					
					System.out.println("Check version from pzFolder: " + versionNumber);
				} else {
					JOptionPane.showMessageDialog(gui, "������ Ȯ���� �� �����ϴ�. ����� �� ������ �����Ͽ����� Ȯ���Ͽ� �ֽʽÿ�.\n�ڼ��� ������ log.txt ������ �����Ͽ� �ֽʽÿ�.");
				}
			}
		});
		
		gui.fileChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.showOpenDialog(gui);
				
				File selectedFile = chooser.getSelectedFile();
				if (selectedFile != null) gui.pzFolder.setText(selectedFile.getAbsolutePath());
			}
		});
		
		gui.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.dispose();
				
				JOptionPane.showMessageDialog(gui, "��ġ�� ����մϴ�.");
			}
		});
		
		gui.install.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				File pzFolder = new File(gui.pzFolder.getText());
				
				String versionNumber = getVersionNumber(pzFolder);
				if (versionNumber != null && versionNumber.equals(getValueFromManifest("Version"))) {
					JOptionPane.showMessageDialog(gui, "������ ��ġ�մϴ�. ��ġ�� �����մϴ�.");
					boolean backuped = backup(pzFolder);
					boolean installed = install(pzFolder);
					
					if (backuped) {
						JOptionPane.showMessageDialog(gui, "����Ǿ����ϴ�.");
					}
					
					if (installed) {
						JOptionPane.showMessageDialog(gui, "��ġ�Ǿ����ϴ�.");
					} else {
						JOptionPane.showMessageDialog(gui, "��ġ�� �����Ͽ����ϴ�.");
					}
				} else {
					JOptionPane.showMessageDialog(gui, "������ ���� �ʰų� ������ ���� ��ġ�� �� �����ϴ�.\n" + "pzVersion: " + versionNumber + "\ninstallerVersion: " + getValueFromManifest("Version"));
				}
			}
		});
		
		gui.restore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				File pzFolder = new File(gui.pzFolder.getText());
				
				if (isBackuped(pzFolder)) {
					restore(pzFolder);
					JOptionPane.showMessageDialog(gui, "�����Ǿ����ϴ�.");
				} else {
					JOptionPane.showMessageDialog(gui, "����Ǿ� ���� �ʰų� ������ ���� ������ �����Ͽ����ϴ�. �ùٸ� ������ ��ġ�� �ϸ� �ڵ����� ����˴ϴ�.");
				}
			}
		});
	}
	
	public void start() {
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
	
	private boolean install(File pzFolder) {
		for (String fileName : filesToInstall) {
			try {
				File file = new File(pzFolder, fileName);
				file.getParentFile().mkdirs();
				file.delete();
				
				InputStream from = Installer.class.getResourceAsStream("/" + fileName);
				FileOutputStream to = new FileOutputStream(file);
				
				byte[] buffer = new byte[1024];
				while (from.available() > 0) {
					int read = from.read(buffer, 0, buffer.length);
					to.write(buffer, 0, read);
				}
				
				from.close();
				to.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	private void restore(File pzFolder) {
		if (!isBackuped(pzFolder)) return;
		
		File backupFolder = getBackupFolder(pzFolder);
		
		for (String fileName : filesToBackup) {
			try {
				File from = new File(backupFolder, fileName);
				File to = new File(pzFolder, fileName);
				
				to.delete();
				
				Files.copy(from.toPath(), to.toPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean backup(File pzFolder) {
		File backupFolder = getBackupFolder(pzFolder);
		
		for (String fileName : filesToBackup) {
			try {
				File from = new File(pzFolder, fileName);
				File to = new File(backupFolder, fileName);
				
				if (to.exists()) return false;
				
				to.getParentFile().mkdirs();
				to.delete();
				
				Files.copy(from.toPath(), to.toPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private boolean isBackuped(File pzFolder) {
		File backupFolder = getBackupFolder(pzFolder);
		
		return backupFolder.exists() && backupFolder.isDirectory();
	}
	
	private File getSteamFolder() {
		try {
			return new File(WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Valve\\Steam", "SteamPath", 0));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private File getBackupFolder(File pzFolder) {
		return new File(pzFolder, "redlaboratory/backup/" + getValueFromManifest("Version"));
	}
	
	private String getValueFromManifest(String key) {
		try {
			Manifest manifest = new Manifest(Installer.class.getResourceAsStream("/META-INF/MANIFEST.MF"));
			
			return manifest.getMainAttributes().getValue(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getVersionNumber(File pzFolder) {
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
