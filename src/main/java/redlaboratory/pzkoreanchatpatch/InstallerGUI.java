package redlaboratory.pzkoreanchatpatch;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InstallerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3460928204398305430L;
	
	public JTextField pzFolder;
	
	public JLabel pzFolderLabel;
	public JLabel contact;
	
	public JButton checkInstalledPZVersion;
	
	public JButton cancel;
	public JButton install;
	public JButton restore;
	
	public JButton fileChoose;
	
	public InstallerGUI() {
		initialize();
	}
	
	private void initialize() {
		setTitle("ProjectZomboid Korean Chat Patch");
		setSize(600, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		
		pzFolderLabel = new JLabel("ProjectZomboid 폴더");
		contact = new JLabel("rlj1202@gmai.com");
		add(pzFolderLabel, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0);
		add(contact, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0);
		
		pzFolder = new JTextField();
		fileChoose = new JButton("게임 폴더 선택");
		checkInstalledPZVersion = new JButton("버젼 확인");
		add(pzFolder, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0);
		add(fileChoose, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0);
		add(checkInstalledPZVersion, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0);
		
		cancel = new JButton("취소");
		install = new JButton("설치");
		restore = new JButton("복원");
		add(cancel, 0, 2, 1, 1, 1, 1, 0, 0, 0, 0);
		add(install, 1, 2, 1, 1, 1, 1, 0, 0, 0, 0);
		add(restore, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0);
	}
	
	public void add(Component comp, int x, int y, int width, int height, float weightx, float weighty, int top, int left, int bottom, int right) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = weightx;
		gbc.weighty = weightx;
		gbc.insets = new Insets(top, left, bottom, left);
		
		this.add(comp, gbc);
	}
	
}
