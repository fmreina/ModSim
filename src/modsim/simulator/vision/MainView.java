package modsim.simulator.vision;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modsim.simulator.control.Simulator;
import modsim.simulator.model.Simulation;

public class MainView {

	private static int id;
	public static ArrayList<Simulation> simulations = new ArrayList<Simulation>();
	private Thread simulator;

	private JFrame frmSimulador;

	private JPanel panelSimulator;

	private static JButton buttonPausar, buttonIniciar;
	private static JSlider slider;
	private static Checkbox checkboxFastFoward;

	private JPanel panelConfiguration;
	private static JTextField textSimName, textFieldSimulationTime;
	private static JTextArea textAreaLog;
	private static JList listLastSim;

	// Attributes Entity
	private JPanel panelEntities, panelTEC;
	private static JTextField textFieldPercEntType_1, textFieldPercEntType_2,
			textFieldMaxTEC, textFieldMedTEC, textFieldMinTEC;
	private static JComboBox comboBoxTimeEntity;

	// Attributes Server 1
	private JPanel panelServer_1, panelTS_1, panelTEF, panelTF;
	private static JComboBox comboBoxTimeServer_1;
	private static JTextField textFieldMaxTS_1, textFieldMedTS_1,
			textFieldMinTS_1;
	// Attributes Server 2
	private JPanel panelServer_2, panelTS_2, panelTEF_2, panelTEmF_2;
	private static JComboBox comboBoxTimeServer_2;
	private static JTextField textFieldMaxTS_2, textFieldMedTS_2,
			textFieldMinTS_2;

	// Attributes Tempo falha
	private static JTextField textFieldMaxTEF, textFieldMedTEF,
			textFieldMinTEF, textFieldMaxTF, textFieldMedTF, textFieldMinTF;
	private static JComboBox comboBoxTEF, comboBoxTF;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmSimulador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public MainView() {
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frmSimulador = new JFrame();
		frmSimulador.setResizable(false);
		frmSimulador.setTitle("ModSim - Simulador Entidade / Servidor ");
		frmSimulador.setBounds(100, 100, 874, 670);
		frmSimulador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		renderMenuBar();

		rederSimulatorPanel();

		renderConfigurationPanel();

		rederLastSimulationPanel();

		renderEntityPanel();

		renderServer1Panel();

		renderServer2Panel();

		renderTempoEntreFalhas();

		renderLogPanel();
	}

	private void renderMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		frmSimulador.setJMenuBar(menuBar);

//		JMenu File = new JMenu("File");
//		menuBar.add(File);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem about = new JMenuItem("Créditos");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about = "Universidade Federal de Santa Catarina - UFSC\n"
						+ "Simulador de propósito geral: Entidade/Servidor\n"
						+ "Projeto desenvolvido para o curso INE5425 - Modelagem e Simulação\n"
						+ "Professor Ministrante: Paulo Jose de Freitas Filho\n"
						+ "Alunos Desenvolvedores: Alisson Granemann Abreu e Fábio Miranda Reina\n";
				JOptionPane.showMessageDialog(null, about, "Simulador", 1);
			}
		});
		mnAbout.add(about);
		
		

//		JMenuBar menuBar_1 = new JMenuBar();
//		menuBar.add(menuBar_1);
		frmSimulador.getContentPane().setLayout(null);
	}

	private void rederSimulatorPanel() {
		panelSimulator = new JPanel();
		panelSimulator.setBounds(10, 11, 447, 595);
		frmSimulador.getContentPane().add(panelSimulator);
		panelSimulator.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelSimulator.setLayout(null);

		panelConfiguration = new JPanel();
		panelConfiguration.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Configurações",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfiguration.setBounds(10, 244, 427, 340);
		panelSimulator.add(panelConfiguration);
		panelConfiguration.setLayout(null);
	}

	private void renderConfigurationPanel() {

		JPanel panelNameTime = new JPanel();
		panelNameTime.setBounds(10, 11, 427, 120);
		panelSimulator.add(panelNameTime);
		panelNameTime.setLayout(null);
		panelNameTime.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Controle",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel label = new JLabel("Nome:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 24, 31, 14);
		panelNameTime.add(label);

		textSimName = new JTextField();
		textSimName.setText("Amostra 1");
		textSimName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textSimName.setColumns(10);
		textSimName.setBounds(51, 21, 124, 20);
		panelNameTime.add(textSimName);

		JLabel labelSimulationTime = new JLabel("Tempo Simulado:");
		labelSimulationTime.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSimulationTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSimulationTime.setBounds(203, 21, 87, 14);
		panelNameTime.add(labelSimulationTime);

		textFieldSimulationTime = new JTextField();
		textFieldSimulationTime.setText("15");
		textFieldSimulationTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSimulationTime.setColumns(10);
		textFieldSimulationTime.setBounds(300, 18, 70, 20);
		panelNameTime.add(textFieldSimulationTime);

		JLabel label_2 = new JLabel("minutos");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(380, 21, 37, 14);
		panelNameTime.add(label_2);

		JLabel label_3 = new JLabel("Velocidade de execução por passo (ms):");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 52, 193, 14);
		panelNameTime.add(label_3);

		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinimum(20);
		slider.setMaximum(1020);
		slider.setMajorTickSpacing(100);
		slider.setValue(520);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		slider.setBounds(10, 70, 407, 45);
		panelNameTime.add(slider);

		checkboxFastFoward = new Checkbox("Fast Foward >>");
		checkboxFastFoward.setBounds(300, 44, 104, 22);
		panelNameTime.add(checkboxFastFoward);

		buttonIniciar = new JButton("Iniciar Simulação");
		buttonIniciar.setBounds(31, 142, 121, 91);
		panelSimulator.add(buttonIniciar);
		buttonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Simulation simulation = new Simulation(textSimName.getText(),
						id++, Integer.parseInt(MainView.getTextFieldSimulationTime()
								.getText()) * 60);
				simulations.add(simulation);
				listLastSim.updateUI();

				Simulator.init(simulation);
				simulator = new Thread(new Simulator());
				textAreaLog.setText(null);
				simulator.start();
				buttonPausar.setEnabled(true);
				buttonIniciar.setEnabled(false);

			}
		});
		buttonIniciar.setForeground(new Color(0, 100, 0));
		buttonIniciar.setFont(new Font("Tahoma", Font.PLAIN, 11));

		buttonPausar = new JButton("Pausar Simulação");
		buttonPausar.setBounds(162, 142, 123, 91);
		panelSimulator.add(buttonPausar);
		buttonPausar.setForeground(Color.RED);
		buttonPausar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPausar.setEnabled(false);

		JButton btnAnalisarSelecao = new JButton("Analisar Sele\u00E7\u00E3o");
		btnAnalisarSelecao.setBounds(295, 142, 122, 23);
		panelSimulator.add(btnAnalisarSelecao);
		btnAnalisarSelecao.setForeground(Color.BLUE);
		btnAnalisarSelecao.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnGerarEstatisticas = new JButton("Gerar Estat\u00EDsticas");
		btnGerarEstatisticas.setBounds(295, 176, 122, 23);
		panelSimulator.add(btnGerarEstatisticas);
		btnGerarEstatisticas.setForeground(Color.BLUE);
		btnGerarEstatisticas.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnExcluirSelecao = new JButton("Excluir Sele\u00E7\u00E3o");
		btnExcluirSelecao.setBounds(295, 210, 122, 23);
		panelSimulator.add(btnExcluirSelecao);
		btnExcluirSelecao.setForeground(Color.BLUE);
		btnExcluirSelecao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExcluirSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listLastSim.getSelectedIndex();
				if (index == -1)
					JOptionPane.showMessageDialog(null,
							"Nenhuma Simulação foi Selecionada!", "Erro!", 1);
				else {
					int i = JOptionPane.showConfirmDialog(null,
							"Excluir as simulações selecionadas?");
					if (i == 0) {
						List names = MainView.listLastSim
								.getSelectedValuesList();

						for (int k = 0; k < names.size(); k++) {
							for (int j = 0; j < MainView.simulations.size(); j++) {
								if (MainView.simulations.get(j).toString()
										.equals(names.get(k))) {
									MainView.simulations.remove(MainView.simulations
											.indexOf(simulations.get(j)));
									break;
								}
							}
						}
						MainView.listLastSim.clearSelection();
						MainView.listLastSim.updateUI();
						textAreaLog.setText("");
					}
				}
			}
		});
		btnGerarEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listLastSim.getSelectedIndex();
				if (index == -1)
					JOptionPane
							.showMessageDialog(
									null,
									"Nenhuma Simula\u00E7\u00E3o est\u00E1 Selecionada!",
									"Erro!", 1);
				else {
					Simulation simulation = (Simulation) simulations.get(index);
					textAreaLog.setText(simulation.getStats().toString());
				}
			}
		});
		btnAnalisarSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listLastSim.getSelectedIndex();
				if (index == -1)
					JOptionPane.showMessageDialog(null,
							"Nenhuma Simulação foi Selecionada!", "Erro!", 1);
				else {
					Simulation simulation = (Simulation) simulations.get(index);

					String str = "";
					for (String string : simulation.getLog()) {
						str += string + "\n";
					}
					textAreaLog.setText(str);
				}
			}
		});
		buttonPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulator.paused = !Simulator.paused;
				if (Simulator.running && Simulator.paused) {
					buttonPausar.setText("Continuar");
				} else {
					buttonPausar.setText("Pausar Simulação");
				}
			}
		});
	}

	private void renderLogPanel() {
		JPanel panelLog = new JPanel();
		panelLog.setBorder(new TitledBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLog.setBounds(467, 144, 395, 462);
		frmSimulador.getContentPane().add(panelLog);
		panelLog.setLayout(null);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 11, 375, 440);
		panelLog.add(scrollPane);

		textAreaLog = new JTextArea();
		scrollPane.setViewportView(textAreaLog);
		textAreaLog.setEditable(false);
		textAreaLog.setFont(new Font("Tahoma", Font.PLAIN, 12));
	}

	private void rederLastSimulationPanel() {
		JPanel panelLastSim = new JPanel();
		panelLastSim.setBorder(new TitledBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLastSim.setBounds(467, 11, 395, 122);
		frmSimulador.getContentPane().add(panelLastSim);
		panelLastSim.setLayout(null);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 11, 373, 100);
		panelLastSim.add(scrollPane);

		listLastSim = new JList();
		scrollPane.setViewportView(listLastSim);
		listLastSim.setFont(new Font("Tahoma", Font.PLAIN, 10));
		listLastSim.setModel(new javax.swing.AbstractListModel() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return simulations.size();
			}

			public Object getElementAt(int i) {
				return simulations.get(i).toString();
			}
		});
	}

	private void renderServer1Panel() {

		panelServer_1 = new JPanel();
		panelServer_1.setBounds(214, 24, 202, 184);
		panelConfiguration.add(panelServer_1);
		panelServer_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Servidor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServer_1.setLayout(null);

		// Tempo de Serviço
		panelTS_1 = new JPanel();
		panelTS_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null),
				"Tempo de Serviço Servidor 1", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), null));
		panelTS_1.setBounds(10, 22, 183, 73);
		panelServer_1.add(panelTS_1);
		panelTS_1.setLayout(null);

		JLabel lblFuncTS_1 = new JLabel("Função");
		lblFuncTS_1.setBounds(10, 22, 39, 14);
		panelTS_1.add(lblFuncTS_1);
		lblFuncTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTimeServer_1 = new JComboBox();
		comboBoxTimeServer_1.setBounds(56, 19, 117, 20);
		panelTS_1.add(comboBoxTimeServer_1);
		comboBoxTimeServer_1.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTimeServer_1.setSelectedItem("Exponencial");

		final JLabel lblValueTS_1 = new JLabel("Valor:");
		lblValueTS_1.setBounds(20, 47, 28, 14);
		panelTS_1.add(lblValueTS_1);
		lblValueTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTS_1 = new JLabel("seg.");
		lblMinutesTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTS_1.setBounds(147, 47, 28, 14);
		panelTS_1.add(lblMinutesTS_1);

		final JLabel lblValuesTS_1 = new JLabel("Valores:");
		lblValuesTS_1.setBounds(10, 47, 39, 14);
		panelTS_1.add(lblValuesTS_1);
		lblValuesTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTS_1 = new JTextField();
		textFieldMaxTS_1.setText("7");
		textFieldMaxTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTS_1.setColumns(10);
		textFieldMaxTS_1.setBounds(120, 44, 28, 20);
		panelTS_1.add(textFieldMaxTS_1);

		textFieldMedTS_1 = new JTextField();
		textFieldMedTS_1.setText("5");
		textFieldMedTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTS_1.setColumns(10);
		textFieldMedTS_1.setBounds(88, 44, 28, 20);
		panelTS_1.add(textFieldMedTS_1);
		textFieldMedTS_1.setVisible(false);

		textFieldMinTS_1 = new JTextField();
		textFieldMinTS_1.setText("3");
		textFieldMinTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTS_1.setColumns(10);
		textFieldMinTS_1.setBounds(56, 44, 28, 20);
		panelTS_1.add(textFieldMinTS_1);

		// Tempo de Serviço
		panelTS_2 = new JPanel();
		panelTS_2.setBounds(10, 102, 183, 73);
		panelServer_1.add(panelTS_2);
		panelTS_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null),
				"Tempo de Serviço Servidor 2", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), null));
		panelTS_2.setLayout(null);

		JLabel lblFuncTS_1_1 = new JLabel("Função");
		lblFuncTS_1_1.setBounds(10, 22, 39, 14);
		panelTS_2.add(lblFuncTS_1_1);
		lblFuncTS_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTS_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTimeServer_2 = new JComboBox();
		comboBoxTimeServer_2.setBounds(56, 19, 117, 20);
		panelTS_2.add(comboBoxTimeServer_2);
		comboBoxTimeServer_2.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTimeServer_2.setSelectedItem("Exponencial");

		final JLabel lblValueTS_1_1 = new JLabel("Valor:");
		lblValueTS_1_1.setBounds(20, 47, 28, 14);
		panelTS_2.add(lblValueTS_1_1);
		lblValueTS_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTS_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTS_1_1 = new JLabel("seg.");
		lblMinutesTS_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTS_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTS_1_1.setBounds(147, 47, 28, 14);
		panelTS_2.add(lblMinutesTS_1_1);

		final JLabel lblValuesTS_1_1 = new JLabel("Valores:");
		lblValuesTS_1_1.setBounds(10, 47, 39, 14);
		panelTS_2.add(lblValuesTS_1_1);
		lblValuesTS_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTS_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTS_2 = new JTextField();
		textFieldMaxTS_2.setText("7");
		textFieldMaxTS_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTS_2.setColumns(10);
		textFieldMaxTS_2.setBounds(120, 44, 28, 20);
		panelTS_2.add(textFieldMaxTS_2);

		textFieldMedTS_2 = new JTextField();
		textFieldMedTS_2.setText("5");
		textFieldMedTS_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTS_2.setColumns(10);
		textFieldMedTS_2.setBounds(88, 44, 28, 20);
		panelTS_2.add(textFieldMedTS_2);
		textFieldMedTS_2.setVisible(false);

		textFieldMinTS_2 = new JTextField();
		textFieldMinTS_2.setText("3");
		textFieldMinTS_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTS_2.setColumns(10);
		textFieldMinTS_2.setBounds(56, 44, 28, 20);
		panelTS_2.add(textFieldMinTS_2);

		textFieldMinTS_2.setVisible(true);
		lblValuesTS_1_1.setVisible(false);
		textFieldMaxTS_2.setVisible(false);
		textFieldMedTS_2.setVisible(false);

		comboBoxTimeServer_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(false);
					lblValueTS_1_1.setVisible(true);
					lblValuesTS_1_1.setVisible(false);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1_1.setVisible(false);
					lblValuesTS_1_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTS_2.setVisible(true);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1_1.setVisible(false);
					lblValuesTS_1_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1_1.setVisible(false);
					lblValuesTS_1_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(false);
					lblValueTS_1_1.setVisible(true);
					lblValuesTS_1_1.setVisible(false);
				}
			}
		});

		textFieldMinTS_1.setVisible(true);
		lblValuesTS_1.setVisible(false);
		textFieldMaxTS_1.setVisible(false);
		textFieldMedTS_1.setVisible(false);

		comboBoxTimeServer_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTimeServer_1.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTS_1.setVisible(false);
					textFieldMedTS_1.setVisible(false);
					lblValueTS_1.setVisible(true);
					lblValuesTS_1.setVisible(false);

				} else if (comboBoxTimeServer_1.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTS_1.setVisible(false);
					textFieldMedTS_1.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_1.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTS_1.setVisible(true);
					textFieldMedTS_1.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_1.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTS_1.setVisible(false);
					textFieldMedTS_1.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_1.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTS_1.setVisible(false);
					textFieldMedTS_1.setVisible(false);
					lblValueTS_1.setVisible(true);
					lblValuesTS_1.setVisible(false);
				}
			}
		});

	}

	private void renderServer2Panel() {

		panelServer_2 = new JPanel();
		panelServer_2.setBounds(10, 219, 406, 110);
		panelConfiguration.add(panelServer_2);
		panelServer_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Falha de Servidor",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServer_2.setLayout(null);

		// Tempo entre falhas
		panelTEF = new JPanel();
		panelTEF.setBounds(10, 26, 183, 73);
		panelServer_2.add(panelTEF);
		panelTEF.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Entre Falhas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEF.setLayout(null);

		JLabel lblFuncTEF_1 = new JLabel("Função");
		lblFuncTEF_1.setBounds(10, 22, 39, 14);
		panelTEF.add(lblFuncTEF_1);
		lblFuncTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEF = new JComboBox();
		comboBoxTEF.setBounds(56, 19, 117, 20);
		panelTEF.add(comboBoxTEF);
		comboBoxTEF.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTEF.setSelectedItem("Exponencial");

		final JLabel lblValueTEF_1 = new JLabel("Valor:");
		lblValueTEF_1.setBounds(20, 47, 28, 14);
		panelTEF.add(lblValueTEF_1);
		lblValueTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEF_1 = new JLabel("min.");
		lblMinutesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEF_1.setBounds(155, 47, 20, 14);
		panelTEF.add(lblMinutesTEF_1);

		final JLabel lblValuesTEF_1 = new JLabel("Valores:");
		lblValuesTEF_1.setBounds(10, 47, 39, 14);
		panelTEF.add(lblValuesTEF_1);
		lblValuesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEF = new JTextField();
		textFieldMaxTEF.setText("7");
		textFieldMaxTEF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEF.setColumns(10);
		textFieldMaxTEF.setBounds(120, 44, 28, 20);
		panelTEF.add(textFieldMaxTEF);

		textFieldMedTEF = new JTextField();
		textFieldMedTEF.setText("5");
		textFieldMedTEF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEF.setColumns(10);
		textFieldMedTEF.setBounds(88, 44, 28, 20);
		panelTEF.add(textFieldMedTEF);
		textFieldMedTEF.setVisible(false);

		textFieldMinTEF = new JTextField();
		textFieldMinTEF.setText("3");
		textFieldMinTEF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEF.setColumns(10);
		textFieldMinTEF.setBounds(56, 44, 28, 20);
		panelTEF.add(textFieldMinTEF);
		// Tempo de falhas
		panelTF = new JPanel();
		panelTF.setBounds(213, 26, 183, 73);
		panelServer_2.add(panelTF);
		panelTF.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo de Falha",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTF.setLayout(null);

		JLabel lblFuncTEmF_1 = new JLabel("Função");
		lblFuncTEmF_1.setBounds(10, 22, 39, 14);
		panelTF.add(lblFuncTEmF_1);
		lblFuncTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTF = new JComboBox();
		comboBoxTF.setBounds(56, 19, 117, 20);
		panelTF.add(comboBoxTF);
		comboBoxTF.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTF.setSelectedItem("Exponencial");

		final JLabel lblValueTEmF_1 = new JLabel("Valor:");
		lblValueTEmF_1.setBounds(20, 47, 28, 14);
		panelTF.add(lblValueTEmF_1);
		lblValueTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEmF_1 = new JLabel("min.");
		lblMinutesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEmF_1.setBounds(155, 47, 20, 14);
		panelTF.add(lblMinutesTEmF_1);

		final JLabel lblValuesTEmF_1 = new JLabel("Valores:");
		lblValuesTEmF_1.setBounds(10, 47, 39, 14);
		panelTF.add(lblValuesTEmF_1);
		lblValuesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTF = new JTextField();
		textFieldMaxTF.setText("7");
		textFieldMaxTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTF.setColumns(10);
		textFieldMaxTF.setBounds(120, 44, 28, 20);
		panelTF.add(textFieldMaxTF);

		textFieldMedTF = new JTextField();
		textFieldMedTF.setText("5");
		textFieldMedTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTF.setColumns(10);
		textFieldMedTF.setBounds(88, 44, 28, 20);
		panelTF.add(textFieldMedTF);
		textFieldMedTF.setVisible(false);

		textFieldMinTF = new JTextField();
		textFieldMinTF.setText("3");
		textFieldMinTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTF.setColumns(10);
		textFieldMinTF.setBounds(56, 44, 28, 20);
		panelTF.add(textFieldMinTF);

		lblValuesTEmF_1.setVisible(false);
		textFieldMaxTF.setVisible(false);
		textFieldMedTF.setVisible(false);
		textFieldMinTF.setVisible(true);

		comboBoxTF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTF.getSelectedItem().toString().equals("Constante")) {
					textFieldMaxTF.setVisible(false);
					textFieldMedTF.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);

				} else if (comboBoxTF.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTF.setVisible(false);
					textFieldMedTF.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTF.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTF.setVisible(true);
					textFieldMedTF.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTF.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTF.setVisible(false);
					textFieldMedTF.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTF.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTF.setVisible(false);
					textFieldMedTF.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);
				}
			}
		});

		lblValuesTEF_1.setVisible(false);
		textFieldMaxTEF.setVisible(false);
		textFieldMedTEF.setVisible(false);
		textFieldMinTEF.setVisible(true);

		comboBoxTEF.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEF.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEF.setVisible(false);
					textFieldMedTEF.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);

				} else if (comboBoxTEF.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEF.setVisible(false);
					textFieldMedTEF.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEF.setVisible(true);
					textFieldMedTEF.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEF.setVisible(false);
					textFieldMedTEF.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEF.setVisible(false);
					textFieldMedTEF.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);
				}
			}
		});
	}

	private void renderTempoEntreFalhas() {
	}

	private void renderEntityPanel() {

		panelEntities = new JPanel();
		panelEntities.setBounds(10, 24, 202, 184);
		panelConfiguration.add(panelEntities);
		panelEntities.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Entidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntities.setLayout(null);

		panelTEC = new JPanel();
		panelTEC.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Entre Chegadas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEC.setBounds(10, 22, 183, 73);
		panelEntities.add(panelTEC);
		panelTEC.setLayout(null);

		JLabel lblFuncTEC = new JLabel("Função");
		lblFuncTEC.setBounds(10, 22, 39, 14);
		panelTEC.add(lblFuncTEC);
		lblFuncTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTimeEntity = new JComboBox();
		comboBoxTimeEntity.setBounds(56, 19, 117, 20);
		panelTEC.add(comboBoxTimeEntity);
		comboBoxTimeEntity.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTimeEntity.setSelectedItem("Exponencial");

		final JLabel lblValueTEC = new JLabel("Valor:");
		lblValueTEC.setBounds(20, 47, 28, 14);
		panelTEC.add(lblValueTEC);
		lblValueTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEC = new JLabel("seg.");
		lblMinutesTEC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEC.setBounds(147, 47, 28, 14);
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
		panelProbabilityEntity.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Probabilidade da Entidade",
				TitledBorder.LEADING,

				TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), null));
		panelProbabilityEntity.setBounds(10, 122, 183, 51);
		panelEntities.add(panelProbabilityEntity);
		panelProbabilityEntity.setLayout(null);

		JLabel labelType_1 = new JLabel("Tipo 1:");
		labelType_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelType_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelType_1.setBounds(10, 24, 33, 14);
		panelProbabilityEntity.add(labelType_1);

		textFieldPercEntType_1 = new JTextField();
		textFieldPercEntType_1.setText("50");
		textFieldPercEntType_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldPercEntType_1.setColumns(10);
		textFieldPercEntType_1.setBounds(52, 21, 23, 20);
		panelProbabilityEntity.add(textFieldPercEntType_1);
		textFieldPercEntType_1.getDocument().addDocumentListener(
				new DocumentListener() {

					public void removeUpdate(DocumentEvent e) {
						int p;
						if (textFieldPercEntType_1.getText().equals("")) {
							p = 100;
						} else if (Integer.parseInt(textFieldPercEntType_1
								.getText()) < 100) {
							p = 100 - Integer.parseInt(textFieldPercEntType_1
									.getText());
							textFieldPercEntType_2.setText("" + p);
						}
					}

					public void insertUpdate(DocumentEvent e) {
						int p;
						if (textFieldPercEntType_1.getText().equals("")) {
							p = 100;
						} else if (Integer.parseInt(textFieldPercEntType_1
								.getText()) < 100) {
							p = 100 - Integer.parseInt(textFieldPercEntType_1
									.getText());
							textFieldPercEntType_2.setText("" + p);
						}
					}

					public void changedUpdate(DocumentEvent e) {
						int p;
						if (textFieldPercEntType_1.getText().equals("")) {
							p = 100;
						} else if (Integer.parseInt(textFieldPercEntType_1
								.getText()) < 100) {
							p = 100 - Integer.parseInt(textFieldPercEntType_1
									.getText());
							textFieldPercEntType_2.setText("" + p);
						}
					}
				});

		JLabel labelPencent_1 = new JLabel("%");
		labelPencent_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPencent_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPencent_1.setBounds(78, 24, 11, 14);
		panelProbabilityEntity.add(labelPencent_1);

		JLabel labelType_2 = new JLabel("Tipo 2:");
		labelType_2.setHorizontalAlignment(SwingConstants.RIGHT);
		labelType_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelType_2.setBounds(99, 24, 33, 14);
		panelProbabilityEntity.add(labelType_2);

		textFieldPercEntType_2 = new JTextField();
		textFieldPercEntType_2.setForeground(Color.BLACK);
		textFieldPercEntType_2.setBackground(Color.WHITE);
		textFieldPercEntType_2.setEditable(false);
		textFieldPercEntType_2.setText("50");
		textFieldPercEntType_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldPercEntType_2.setColumns(10);
		textFieldPercEntType_2.setBounds(141, 21, 23, 20);
		panelProbabilityEntity.add(textFieldPercEntType_2);

		JLabel labelPercent_2 = new JLabel("%");
		labelPercent_2.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPercent_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelPercent_2.setBounds(167, 24, 11, 14);
		panelProbabilityEntity.add(labelPercent_2);
		textFieldMinTEC.setVisible(true);

		lblValuesTEC.setVisible(false);
		textFieldMaxTEC.setVisible(false);
		textFieldMedTEC.setVisible(false);

		comboBoxTimeEntity.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTimeEntity.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(false);
					lblValueTEC.setVisible(true);
					lblValuesTEC.setVisible(false);

				} else if (comboBoxTimeEntity.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTimeEntity.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEC.setVisible(true);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTimeEntity.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(true);
					lblValueTEC.setVisible(false);
					lblValuesTEC.setVisible(true);

				} else if (comboBoxTimeEntity.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEC.setVisible(false);
					textFieldMedTEC.setVisible(false);
					lblValueTEC.setVisible(true);
					lblValuesTEC.setVisible(false);
				}
			}
		});
	}

	public static JButton getButtonPausar() {
		return buttonPausar;
	}

	public static JButton getButtonIniciar() {
		return buttonIniciar;
	}

	public JFrame getFrmSimulador() {
		return frmSimulador;
	}

	public JPanel getPanelSimulator() {
		return panelSimulator;
	}

	public JPanel getPanelConfiguration() {
		return panelConfiguration;
	}

	public static JTextField getTextSimName() {
		return textSimName;
	}

	public static JTextField getTextFieldSimulationTime() {
		return textFieldSimulationTime;
	}

	public JPanel getPanelEntities() {
		return panelEntities;
	}

	public JPanel getPanelTEC() {
		return panelTEC;
	}

	public static JTextField getTextFieldPercEntType_1() {
		return textFieldPercEntType_1;
	}

	public static JTextField getTextFieldPercEntType_2() {
		return textFieldPercEntType_2;
	}

	public static JComboBox getComboBoxTimeEntity() {
		return comboBoxTimeEntity;
	}

	public static JTextField getTextFieldMaxTEC() {
		return textFieldMaxTEC;
	}

	public static JTextField getTextFieldMedTEC() {
		return textFieldMedTEC;
	}

	public static JTextField getTextFieldMinTEC() {
		return textFieldMinTEC;
	}

	public JPanel getPanelServer_1() {
		return panelServer_1;
	}

	public JPanel getPanelTS_1() {
		return panelTS_1;
	}

	public JPanel getPanelTEF_1() {
		return panelTEF;
	}

	public JPanel getPanelTEmF_1() {
		return panelTF;
	}

	public static JComboBox getComboBoxTimeServer_1() {
		return comboBoxTimeServer_1;
	}

	public static JComboBox getComboBoxTEF() {
		return comboBoxTEF;
	}

	public static JComboBox getComboBoxTF() {
		return comboBoxTF;
	}

	public static JTextField getTextFieldMaxTS_1() {
		return textFieldMaxTS_1;
	}

	public static JTextField getTextFieldMedTS_1() {
		return textFieldMedTS_1;
	}

	public static JTextField getTextFieldMinTS_1() {
		return textFieldMinTS_1;
	}

	public static JTextField getTextFieldMaxTEF() {
		return textFieldMaxTEF;
	}

	public static JTextField getTextFieldMedTEF() {
		return textFieldMedTEF;
	}

	public static JTextField getTextFieldMinTEF() {
		return textFieldMinTEF;
	}

	public static JTextField getTextFieldMaxTF() {
		return textFieldMaxTF;
	}

	public static JTextField getTextFieldMedTF() {
		return textFieldMedTF;
	}

	public static JTextField getTextFieldMinTF() {
		return textFieldMinTF;
	}

	public JPanel getPanelServer_2() {
		return panelServer_2;
	}

	public JPanel getPanelTS_2() {
		return panelTS_2;
	}

	public JPanel getPanelTEF_2() {
		return panelTEF_2;
	}

	public JPanel getPanelTEmF_2() {
		return panelTEmF_2;
	}

	public static JComboBox getComboBoxTimeServer_2() {
		return comboBoxTimeServer_2;
	}

	public static JTextField getTextFieldMaxTS_2() {
		return textFieldMaxTS_2;
	}

	public static JTextField getTextFieldMedTS_2() {
		return textFieldMedTS_2;
	}

	public static JTextField getTextFieldMinTS_2() {
		return textFieldMinTS_2;
	}

	public static JTextArea getTextPaneLog() {
		return textAreaLog;
	}

	public static JList getTextPaneLastSim() {
		return listLastSim;
	}

	public static void print(String msg) {
		textAreaLog.append(msg + "\n");
	}

	public static JSlider getSlider() {
		return slider;
	}

	public static Checkbox getCheckboxFastFoward() {
		return checkboxFastFoward;
	}

}
