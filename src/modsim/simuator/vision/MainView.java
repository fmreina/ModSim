package modsim.simuator.vision;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
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
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Checkbox;

public class MainView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public static JComboBox comboBoxTEC;
	public static JComboBox comboBoxC2;
	public static JTextField textNumEntities;
	public static JTextField textFieldCanaisC2;
	public static JTextField textFieldMaxTEC;
	public static JTextField textFieldMedTEC;
	public static JTextField textFieldMinTEC;

	private JPanel panelEntities;
	private JPanel panelTEC;
	private JLabel labelNumEntities;
	private JTextField textField_2;
	private JTextField textField_5;

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
		
		JPanel panelSimulator = new JPanel();
		panelSimulator.setBounds(10, 11, 610, 526);
		frame.getContentPane().add(panelSimulator);
		panelSimulator.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null)/*BevelBorder(BevelBorder.LOWERED, null, null, null, null)*/, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSimulator.setLayout(null);
		
		JPanel panelConfiguration = new JPanel();
		panelConfiguration.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Configurações",	TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfiguration.setBounds(10, 169, 593, 346);
		panelSimulator.add(panelConfiguration);
		panelConfiguration.setLayout(null);
		
		panelEntities = new JPanel();
		panelEntities.setBounds(10, 20, 202, 179);
		panelConfiguration.add(panelEntities);
		panelEntities.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Entidades", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelEntities.setLayout(null);

		labelNumEntities = new JLabel("Número de Entidades:");
		labelNumEntities.setBounds(10, 20, 107, 14);
		panelEntities.add(labelNumEntities);
		labelNumEntities.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNumEntities.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textNumEntities = new JTextField();
		textNumEntities.setBounds(127, 17, 66, 20);
		panelEntities.add(textNumEntities);
		textNumEntities.setText("15");
		textNumEntities.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textNumEntities.setColumns(10);

		panelTEC = new JPanel();
		panelTEC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Tempo Entre Chegadas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), null));
		panelTEC.setBounds(10, 45, 183, 73);
		panelEntities.add(panelTEC);
		panelTEC.setLayout(null);

		JLabel lblFuncTEC = new JLabel("Função");
		lblFuncTEC.setBounds(10, 22, 39, 14);
		panelTEC.add(lblFuncTEC);
		lblFuncTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEC = new JComboBox();
		comboBoxTEC.setBounds(56, 19, 117, 20);
		panelTEC.add(comboBoxTEC);
		comboBoxTEC.setModel(new DefaultComboBoxModel(new String[] { "Constante", "Normal", "Triangular", "Uniforme", "Exponencial" }));

		final JLabel lblValueTEC = new JLabel("Valor:");
		lblValueTEC.setBounds(20, 47, 28, 14);
		panelTEC.add(lblValueTEC);
		lblValueTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEC = new JLabel("min.");
		lblMinutesTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEC.setBounds(155, 47, 20, 14);
		panelTEC.add(lblMinutesTEC);

		final JLabel lblValuesTEC = new JLabel("Valores:");
		lblValuesTEC.setBounds(10, 47, 39, 14);
		panelTEC.add(lblValuesTEC);
		lblValuesTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEC = new JTextField();
		textFieldMaxTEC.setText("7");
		textFieldMaxTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEC.setColumns(10);
		textFieldMaxTEC.setBounds(120, 44, 28, 20);
		panelTEC.add(textFieldMaxTEC);

		textFieldMedTEC = new JTextField();
		textFieldMedTEC.setText("5");
		textFieldMedTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEC.setColumns(10);
		textFieldMedTEC.setBounds(88, 44, 28, 20);
		panelTEC.add(textFieldMedTEC);
		textFieldMedTEC.setVisible(false);

		textFieldMinTEC = new JTextField();
		textFieldMinTEC.setText("3");
		textFieldMinTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEC.setColumns(10);
		textFieldMinTEC.setBounds(56, 44, 28, 20);
		panelTEC.add(textFieldMinTEC);
		
		JPanel panelProbabilityEntity = new JPanel();
		panelProbabilityEntity.setForeground(new Color(0, 100, 0));
		panelProbabilityEntity.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Probabilidade da Entidade", TitledBorder.LEADING,
								TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), null));
		panelProbabilityEntity.setBounds(10, 117, 183, 51);
		panelEntities.add(panelProbabilityEntity);
		panelProbabilityEntity.setLayout(null);
		
		JLabel label_6 = new JLabel("Tipo 1:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(10, 24, 33, 14);
		panelProbabilityEntity.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setText("50");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_2.setColumns(10);
		textField_2.setBounds(52, 21, 23, 20);
		panelProbabilityEntity.add(textField_2);
		
		JLabel label_9 = new JLabel("%");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(78, 24, 11, 14);
		panelProbabilityEntity.add(label_9);
		
		JLabel label_10 = new JLabel("Tipo 2:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(99, 24, 33, 14);
		panelProbabilityEntity.add(label_10);
		
		textField_5 = new JTextField();
		textField_5.setText("30");
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_5.setColumns(10);
		textField_5.setBounds(141, 21, 23, 20);
		panelProbabilityEntity.add(textField_5);
		
		JLabel label_11 = new JLabel("%");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(167, 24, 11, 14);
		panelProbabilityEntity.add(label_11);
		textFieldMinTEC.setVisible(true);

		lblValuesTEC.setVisible(false);
		textFieldMaxTEC.setVisible(false);
		textFieldMedTEC.setVisible(false);

		comboBoxTEC.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEC.getSelectedItem().toString().equals("Constante")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(false);
					lblValueTEC.setVisible(true);
					lblValuesTEC.setVisible(false);

				} else if (comboBoxTEC.getSelectedItem().toString().equals("Normal")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTEC.getSelectedItem().toString().equals("Triangular")) {
					textFieldMaxTEC.setVisible(true);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTEC.getSelectedItem().toString().equals("Uniforme")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTEC.getSelectedItem().toString().equals("Exponencial")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(false);
					lblValueTEC.setVisible(true);
					lblValuesTEC.setVisible(false);
				}
			}
		});
		
		
		
		JPanel panelControl = new JPanel();
		panelControl.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Controle",	TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelControl.setBounds(10, 11, 590, 153);
		panelSimulator.add(panelControl);
		panelControl.setLayout(null);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "",	TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelButtons.setBounds(435, 22, 145, 120);
		panelControl.add(panelButtons);
		
		JButton buttonPausar = new JButton("Pausar Simula\u00E7\u00E3o!");
		buttonPausar.setForeground(Color.RED);
		buttonPausar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPausar.setEnabled(false);
		buttonPausar.setBounds(10, 65, 125, 43);
		panelButtons.add(buttonPausar);
		
		JButton buttonIniciar = new JButton("Iniciar Simul\u00E7\u00E3o");
		buttonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonIniciar.setBounds(10, 11, 125, 43);
		panelButtons.add(buttonIniciar);
		buttonIniciar.setForeground(new Color(0, 100, 0));
		buttonIniciar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JPanel panelNameTime = new JPanel();
		panelNameTime.setBounds(10, 22, 415, 120);
		panelControl.add(panelNameTime);
		panelNameTime.setLayout(null);
		panelNameTime.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "",	TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("Nome:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 24, 31, 14);
		panelNameTime.add(label);
		
		textField = new JTextField();
		textField.setText("Amostra 1");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setColumns(10);
		textField.setBounds(51, 21, 124, 20);
		panelNameTime.add(textField);
		
		JLabel label_1 = new JLabel("Tempo Simulado:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(185, 24, 87, 14);
		panelNameTime.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText("15");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(282, 21, 70, 20);
		panelNameTime.add(textField_1);
		
		JLabel label_2 = new JLabel("minutos");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(362, 24, 37, 14);
		panelNameTime.add(label_2);
		
		JLabel label_3 = new JLabel("Velocidade de execu\u00E7\u00E3o por passo (ms):");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 52, 193, 14);
		panelNameTime.add(label_3);
		
		JSlider slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(20);
		slider.setMaximum(1020);
		slider.setMajorTickSpacing(100);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		slider.setBounds(10, 70, 389, 45);
		panelNameTime.add(slider);
		
		Checkbox checkboxFastFoward = new Checkbox("Fast Foward >>");
		checkboxFastFoward.setBounds(282, 47, 104, 22);
		panelNameTime.add(checkboxFastFoward);
		
		JPanel panelLastSim = new JPanel();
		panelLastSim.setLayout(null);
		panelLastSim.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "", TitledBorder.LEADING,
								TitledBorder.TOP, null, null));
		panelLastSim.setBounds(626, 11, 429, 109);
		frame.getContentPane().add(panelLastSim);
		
		JScrollPane lastSimScrollPane = new JScrollPane((Component) null);
		lastSimScrollPane.setBounds(10, 11, 409, 87);
		panelLastSim.add(lastSimScrollPane);
		
		JPanel panelLog = new JPanel();
		panelLog.setLayout(null);
		panelLog.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLog.setBounds(626, 131, 429, 406);
		frame.getContentPane().add(panelLog);
		
		JScrollPane logScrollPane = new JScrollPane((Component) null);
		logScrollPane.setBounds(10, 11, 409, 384);
		panelLog.add(logScrollPane);
	}
}
