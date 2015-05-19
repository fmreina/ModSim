package modsim.simuator.vision;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1081, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		
		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		frame.getContentPane().setLayout(null);
		
		JPanel backGroundPanel = new JPanel();
		backGroundPanel.setBounds(10, 10, 1045, 527);
		frame.getContentPane().add(backGroundPanel);
		backGroundPanel.setLayout(null);
		
		JPanel simulatorPanel = new JPanel();
		simulatorPanel.setBounds(-4, 0, 689, 527);
		backGroundPanel.add(simulatorPanel);
		simulatorPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 669, 134);
		simulatorPanel.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JPanel ConfigurationPanel = new JPanel();
		ConfigurationPanel.setBounds(10, 145, 669, 372);
		simulatorPanel.add(ConfigurationPanel);
		ConfigurationPanel.setLayout(null);
		
		JScrollPane logScrollPane = new JScrollPane();
		logScrollPane.setBounds(695, 0, 350, 527);
		backGroundPanel.add(logScrollPane);
		
		JTextPane logTextPane = new JTextPane();
		logScrollPane.setViewportView(logTextPane);
	}
}
