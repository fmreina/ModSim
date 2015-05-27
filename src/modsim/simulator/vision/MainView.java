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
	private JPanel panelServer_1, panelTS_1, panelTEF_1, panelTEmF_1;
	private static JComboBox comboBoxTimeServer_1, comboBoxTEF_1,
			comboBoxTEmF_1;
	private static JTextField textFieldMaxTS_1, textFieldMedTS_1,
			textFieldMinTS_1, textFieldMaxTEF_1, textFieldMedTEF_1,
			textFieldMinTEF_1, textFieldMaxTEmF_1, textFieldMedTEmF_1,
			textFieldMinTEmF_1, textField_1;

	// Attributes Server 2
	private JPanel panelServer_2, panelTS_2, panelTEF_2, panelTEmF_2;
	private static JComboBox comboBoxTimeServer_2, comboBoxTEF_2,
			comboBoxTEmF_2;
	private static JTextField textFieldMaxTS_2, textFieldMedTS_2,
			textFieldMinTS_2, textFieldMaxTEF_2, textFieldMedTEF_2,
			textFieldMinTEF_2, textFieldMaxTEmF_2, textFieldMedTEmF_2,
			textFieldMinTEmF_2, textField_2;

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
		frmSimulador.setBounds(100, 100, 1071, 608);
		frmSimulador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		renderMenuBar();

		rederSimulatorPanel();

		renderConfigurationPanel();

		rederLastSimulationPanel();

		renderEntityPanel();

		renderServer1Panel();

		renderServer2Panel();

		renderLogPanel();
	}

	private void renderMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		frmSimulador.setJMenuBar(menuBar);

		JMenu File = new JMenu("File");
		menuBar.add(File);

		JMenu mnNewMenu = new JMenu("About");
		menuBar.add(mnNewMenu);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		frmSimulador.getContentPane().setLayout(null);
	}

	private void rederSimulatorPanel() {
		panelSimulator = new JPanel();
		panelSimulator.setBounds(10, 11, 640, 526);
		frmSimulador.getContentPane().add(panelSimulator);
		panelSimulator.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelSimulator.setLayout(null);

		panelConfiguration = new JPanel();
		panelConfiguration.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Configurações",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfiguration.setBounds(10, 169, 626, 346);
		panelSimulator.add(panelConfiguration);
		panelConfiguration.setLayout(null);
	}

	private void renderConfigurationPanel() {
		JPanel panelControl = new JPanel();
		panelControl.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Controle",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelControl.setBounds(10, 11, 620, 153);
		panelSimulator.add(panelControl);
		panelControl.setLayout(null);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(null);
		panelButtons.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelButtons.setBounds(465, 22, 145, 120);
		panelControl.add(panelButtons);

		buttonPausar = new JButton("Pausar Simulação");
		buttonPausar.setForeground(Color.RED);
		buttonPausar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPausar.setEnabled(false);
		buttonPausar.setBounds(10, 65, 125, 43);
		panelButtons.add(buttonPausar);
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

		buttonIniciar = new JButton("Iniciar Simulação");
		buttonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Simulation simulation = new Simulation(textSimName.getText(),
						id++);
				simulations.add(simulation);
				listLastSim.updateUI();

				Simulator.init(simulation);
				simulator = new Thread(new Simulator());
				textAreaLog.setText(null);
				simulator.start();
				buttonPausar.setEnabled(true);
				buttonIniciar.setEnabled(true);

			}
		});
		buttonIniciar.setBounds(10, 11, 125, 43);
		panelButtons.add(buttonIniciar);
		buttonIniciar.setForeground(new Color(0, 100, 0));
		buttonIniciar.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JPanel panelNameTime = new JPanel();
		panelNameTime.setBounds(10, 22, 445, 120);
		panelControl.add(panelNameTime);
		panelNameTime.setLayout(null);
		panelNameTime.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

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
		labelSimulationTime.setBounds(221, 24, 87, 14);
		panelNameTime.add(labelSimulationTime);

		textFieldSimulationTime = new JTextField();
		textFieldSimulationTime.setText("15");
		textFieldSimulationTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldSimulationTime.setColumns(10);
		textFieldSimulationTime.setBounds(318, 21, 70, 20);
		panelNameTime.add(textFieldSimulationTime);

		JLabel label_2 = new JLabel("minutos");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(398, 24, 37, 14);
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
		slider.setBounds(10, 70, 425, 45);
		panelNameTime.add(slider);

		checkboxFastFoward = new Checkbox("Fast Foward >>");
		checkboxFastFoward.setBounds(318, 47, 104, 22);
		panelNameTime.add(checkboxFastFoward);
	}

	private void renderLogPanel() {
		JPanel panelLog = new JPanel();
		panelLog.setBorder(new TitledBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLog.setBounds(660, 165, 395, 372);
		frmSimulador.getContentPane().add(panelLog);
		panelLog.setLayout(null);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 11, 375, 350);
		panelLog.add(scrollPane);

		textAreaLog = new JTextArea();
		textAreaLog.setEditable(false);
		scrollPane.setViewportView(textAreaLog);
		textAreaLog.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JButton btnAnalisarSelecao = new JButton("Analisar Sele\u00E7\u00E3o");
		btnAnalisarSelecao.setForeground(Color.BLUE);
		btnAnalisarSelecao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnalisarSelecao.setBounds(660, 131, 125, 23);
		frmSimulador.getContentPane().add(btnAnalisarSelecao);
		btnAnalisarSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listLastSim.getSelectedIndex();
				if (index == -1)
					JOptionPane.showMessageDialog(null, "Nenhuma Simulação foi Selecionada!", "Erro!", 1);
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

		JButton btnExcluirSelecao = new JButton("Excluir Sele\u00E7\u00E3o");
		btnExcluirSelecao.setForeground(Color.BLUE);
		btnExcluirSelecao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExcluirSelecao.setBounds(930, 131, 125, 23);
		frmSimulador.getContentPane().add(btnExcluirSelecao);
		btnExcluirSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listLastSim.getSelectedIndex();
				if (index == -1)
					JOptionPane.showMessageDialog(null, "Nenhuma Simulação foi Selecionada!", "Erro!", 1);
				else {
					int i = JOptionPane.showConfirmDialog(null, "Excluir as simulações selecionadas?");
					if (i == 0) {
						List names = MainView.listLastSim.getSelectedValuesList();

						for (int k = 0; k < names.size(); k++) {
							for (int j = 0; j < MainView.simulations.size(); j++) {
								if (MainView.simulations.get(j).toString().equals(names.get(k))) {
									MainView.simulations.remove(MainView.simulations.indexOf(simulations.get(j)));
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

		JButton btnGerarEstatisticas = new JButton("Gerar Estat\u00EDsticas");
		btnGerarEstatisticas.setForeground(Color.BLUE);
		btnGerarEstatisticas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGerarEstatisticas.setBounds(795, 131, 125, 23);
		frmSimulador.getContentPane().add(btnGerarEstatisticas);
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
	}

	private void rederLastSimulationPanel() {
		JPanel panelLastSim = new JPanel();
		panelLastSim.setBorder(new TitledBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLastSim.setBounds(660, 11, 395, 109);
		frmSimulador.getContentPane().add(panelLastSim);
		panelLastSim.setLayout(null);

		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 11, 373, 85);
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
		panelServer_1.setBounds(214, 22, 202, 313);
		panelConfiguration.add(panelServer_1);
		panelServer_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Servidor 1",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServer_1.setLayout(null);

		// Tempo de Serviço
		panelTS_1 = new JPanel();
		panelTS_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo de Serviço",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
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

		// Tempo entre falhas
		panelTEmF_1 = new JPanel();
		panelTEmF_1.setBounds(10, 229, 183, 73);
		panelServer_1.add(panelTEmF_1);
		panelTEmF_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Em Falhas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEmF_1.setLayout(null);

		JLabel lblFuncTEmF_1 = new JLabel("Função");
		lblFuncTEmF_1.setBounds(10, 22, 39, 14);
		panelTEmF_1.add(lblFuncTEmF_1);
		lblFuncTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEmF_1 = new JComboBox();
		comboBoxTEmF_1.setBounds(56, 19, 117, 20);
		panelTEmF_1.add(comboBoxTEmF_1);
		comboBoxTEmF_1.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTEmF_1.setSelectedItem("Exponencial");

		final JLabel lblValueTEmF_1 = new JLabel("Valor:");
		lblValueTEmF_1.setBounds(20, 47, 28, 14);
		panelTEmF_1.add(lblValueTEmF_1);
		lblValueTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEmF_1 = new JLabel("min.");
		lblMinutesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEmF_1.setBounds(155, 47, 20, 14);
		panelTEmF_1.add(lblMinutesTEmF_1);

		final JLabel lblValuesTEmF_1 = new JLabel("Valores:");
		lblValuesTEmF_1.setBounds(10, 47, 39, 14);
		panelTEmF_1.add(lblValuesTEmF_1);
		lblValuesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEmF_1 = new JTextField();
		textFieldMaxTEmF_1.setText("7");
		textFieldMaxTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEmF_1.setColumns(10);
		textFieldMaxTEmF_1.setBounds(120, 44, 28, 20);
		panelTEmF_1.add(textFieldMaxTEmF_1);

		textFieldMedTEmF_1 = new JTextField();
		textFieldMedTEmF_1.setText("5");
		textFieldMedTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEmF_1.setColumns(10);
		textFieldMedTEmF_1.setBounds(88, 44, 28, 20);
		panelTEmF_1.add(textFieldMedTEmF_1);
		textFieldMedTEmF_1.setVisible(false);

		textFieldMinTEmF_1 = new JTextField();
		textFieldMinTEmF_1.setText("3");
		textFieldMinTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEmF_1.setColumns(10);
		textFieldMinTEmF_1.setBounds(56, 44, 28, 20);
		panelTEmF_1.add(textFieldMinTEmF_1);

		// Tempo entre falhas
		panelTEF_1 = new JPanel();
		panelTEF_1.setBounds(10, 145, 183, 73);
		panelServer_1.add(panelTEF_1);
		panelTEF_1.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Entre Falhas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEF_1.setLayout(null);

		JLabel lblFuncTEF_1 = new JLabel("Função");
		lblFuncTEF_1.setBounds(10, 22, 39, 14);
		panelTEF_1.add(lblFuncTEF_1);
		lblFuncTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEF_1 = new JComboBox();
		comboBoxTEF_1.setBounds(56, 19, 117, 20);
		panelTEF_1.add(comboBoxTEF_1);
		comboBoxTEF_1.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTEF_1.setSelectedItem("Exponencial");

		final JLabel lblValueTEF_1 = new JLabel("Valor:");
		lblValueTEF_1.setBounds(20, 47, 28, 14);
		panelTEF_1.add(lblValueTEF_1);
		lblValueTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEF_1 = new JLabel("min.");
		lblMinutesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEF_1.setBounds(155, 47, 20, 14);
		panelTEF_1.add(lblMinutesTEF_1);

		final JLabel lblValuesTEF_1 = new JLabel("Valores:");
		lblValuesTEF_1.setBounds(10, 47, 39, 14);
		panelTEF_1.add(lblValuesTEF_1);
		lblValuesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEF_1 = new JTextField();
		textFieldMaxTEF_1.setText("7");
		textFieldMaxTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEF_1.setColumns(10);
		textFieldMaxTEF_1.setBounds(120, 44, 28, 20);
		panelTEF_1.add(textFieldMaxTEF_1);

		textFieldMedTEF_1 = new JTextField();
		textFieldMedTEF_1.setText("5");
		textFieldMedTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEF_1.setColumns(10);
		textFieldMedTEF_1.setBounds(88, 44, 28, 20);
		panelTEF_1.add(textFieldMedTEF_1);
		textFieldMedTEF_1.setVisible(false);

		textFieldMinTEF_1 = new JTextField();
		textFieldMinTEF_1.setText("3");
		textFieldMinTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEF_1.setColumns(10);
		textFieldMinTEF_1.setBounds(56, 44, 28, 20);
		panelTEF_1.add(textFieldMinTEF_1);

		JLabel lblProbabilidadeDeFalha = new JLabel("Probabilidade de falha:");
		lblProbabilidadeDeFalha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProbabilidadeDeFalha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProbabilidadeDeFalha.setBounds(10, 110, 126, 14);
		panelServer_1.add(lblProbabilidadeDeFalha);

		textField_1 = new JTextField();
		textField_1.setText("30");
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(145, 107, 23, 20);
		panelServer_1.add(textField_1);

		JLabel label_1 = new JLabel("%");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(171, 110, 11, 14);
		panelServer_1.add(label_1);

		lblValuesTEF_1.setVisible(false);
		textFieldMaxTEF_1.setVisible(false);
		textFieldMedTEF_1.setVisible(false);
		textFieldMinTEF_1.setVisible(true);

		comboBoxTEF_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEF_1.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEF_1.setVisible(false);
					textFieldMedTEF_1.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);

				} else if (comboBoxTEF_1.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEF_1.setVisible(false);
					textFieldMedTEF_1.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_1.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEF_1.setVisible(true);
					textFieldMedTEF_1.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_1.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEF_1.setVisible(false);
					textFieldMedTEF_1.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_1.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEF_1.setVisible(false);
					textFieldMedTEF_1.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);
				}
			}
		});

		lblValuesTEmF_1.setVisible(false);
		textFieldMaxTEmF_1.setVisible(false);
		textFieldMedTEmF_1.setVisible(false);
		textFieldMinTEmF_1.setVisible(true);

		comboBoxTEmF_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEmF_1.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEmF_1.setVisible(false);
					textFieldMedTEmF_1.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);

				} else if (comboBoxTEmF_1.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEmF_1.setVisible(false);
					textFieldMedTEmF_1.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_1.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEmF_1.setVisible(true);
					textFieldMedTEmF_1.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_1.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEmF_1.setVisible(false);
					textFieldMedTEmF_1.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_1.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEmF_1.setVisible(false);
					textFieldMedTEmF_1.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);
				}
			}
		});
	}

	private void renderServer2Panel() {

		panelServer_2 = new JPanel();
		panelServer_2.setBounds(415, 22, 202, 313);
		panelConfiguration.add(panelServer_2);
		panelServer_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Servidor 2",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServer_2.setLayout(null);

		// Tempo de Serviço
		panelTS_2 = new JPanel();
		panelTS_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo de Serviço",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTS_2.setBounds(10, 22, 183, 73);
		panelServer_2.add(panelTS_2);
		panelTS_2.setLayout(null);

		JLabel lblFuncTS_1 = new JLabel("Função");
		lblFuncTS_1.setBounds(10, 22, 39, 14);
		panelTS_2.add(lblFuncTS_1);
		lblFuncTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTimeServer_2 = new JComboBox();
		comboBoxTimeServer_2.setBounds(56, 19, 117, 20);
		panelTS_2.add(comboBoxTimeServer_2);
		comboBoxTimeServer_2.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTimeServer_2.setSelectedItem("Exponencial");

		final JLabel lblValueTS_1 = new JLabel("Valor:");
		lblValueTS_1.setBounds(20, 47, 28, 14);
		panelTS_2.add(lblValueTS_1);
		lblValueTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTS_1 = new JLabel("seg.");
		lblMinutesTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTS_1.setBounds(147, 47, 28, 14);
		panelTS_2.add(lblMinutesTS_1);

		final JLabel lblValuesTS_1 = new JLabel("Valores:");
		lblValuesTS_1.setBounds(10, 47, 39, 14);
		panelTS_2.add(lblValuesTS_1);
		lblValuesTS_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTS_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

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
		lblValuesTS_1.setVisible(false);
		textFieldMaxTS_2.setVisible(false);
		textFieldMedTS_2.setVisible(false);

		comboBoxTimeServer_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(false);
					lblValueTS_1.setVisible(true);
					lblValuesTS_1.setVisible(false);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTS_2.setVisible(true);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(true);
					lblValueTS_1.setVisible(false);
					lblValuesTS_1.setVisible(true);

				} else if (comboBoxTimeServer_2.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTS_2.setVisible(false);
					textFieldMedTS_2.setVisible(false);
					lblValueTS_1.setVisible(true);
					lblValuesTS_1.setVisible(false);
				}
			}
		});

		// Tempo entre falhas
		panelTEmF_2 = new JPanel();
		panelTEmF_2.setBounds(10, 229, 183, 73);
		panelServer_2.add(panelTEmF_2);
		panelTEmF_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Em Falhas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEmF_2.setLayout(null);

		JLabel lblFuncTEmF_1 = new JLabel("Função");
		lblFuncTEmF_1.setBounds(10, 22, 39, 14);
		panelTEmF_2.add(lblFuncTEmF_1);
		lblFuncTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEmF_2 = new JComboBox();
		comboBoxTEmF_2.setBounds(56, 19, 117, 20);
		panelTEmF_2.add(comboBoxTEmF_2);
		comboBoxTEmF_2.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTEmF_2.setSelectedItem("Exponencial");

		final JLabel lblValueTEmF_1 = new JLabel("Valor:");
		lblValueTEmF_1.setBounds(20, 47, 28, 14);
		panelTEmF_2.add(lblValueTEmF_1);
		lblValueTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEmF_1 = new JLabel("min.");
		lblMinutesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEmF_1.setBounds(155, 47, 20, 14);
		panelTEmF_2.add(lblMinutesTEmF_1);

		final JLabel lblValuesTEmF_1 = new JLabel("Valores:");
		lblValuesTEmF_1.setBounds(10, 47, 39, 14);
		panelTEmF_2.add(lblValuesTEmF_1);
		lblValuesTEmF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEmF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEmF_2 = new JTextField();
		textFieldMaxTEmF_2.setText("7");
		textFieldMaxTEmF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEmF_2.setColumns(10);
		textFieldMaxTEmF_2.setBounds(120, 44, 28, 20);
		panelTEmF_2.add(textFieldMaxTEmF_2);

		textFieldMedTEmF_2 = new JTextField();
		textFieldMedTEmF_2.setText("5");
		textFieldMedTEmF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEmF_2.setColumns(10);
		textFieldMedTEmF_2.setBounds(88, 44, 28, 20);
		panelTEmF_2.add(textFieldMedTEmF_2);
		textFieldMedTEmF_2.setVisible(false);

		textFieldMinTEmF_2 = new JTextField();
		textFieldMinTEmF_2.setText("3");
		textFieldMinTEmF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEmF_2.setColumns(10);
		textFieldMinTEmF_2.setBounds(56, 44, 28, 20);
		panelTEmF_2.add(textFieldMinTEmF_2);

		// Tempo entre falhas
		panelTEF_2 = new JPanel();
		panelTEF_2.setBounds(10, 145, 183, 73);
		panelServer_2.add(panelTEF_2);
		panelTEF_2.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Tempo Entre Falhas",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma",
						Font.PLAIN, 11), null));
		panelTEF_2.setLayout(null);

		JLabel lblFuncTEF_1 = new JLabel("Função");
		lblFuncTEF_1.setBounds(10, 22, 39, 14);
		panelTEF_2.add(lblFuncTEF_1);
		lblFuncTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxTEF_2 = new JComboBox();
		comboBoxTEF_2.setBounds(56, 19, 117, 20);
		panelTEF_2.add(comboBoxTEF_2);
		comboBoxTEF_2.setModel(new DefaultComboBoxModel(
				new String[] { "Constante", "Normal", "Triangular", "Uniforme",
						"Exponencial" }));
		comboBoxTEF_2.setSelectedItem("Exponencial");

		final JLabel lblValueTEF_1 = new JLabel("Valor:");
		lblValueTEF_1.setBounds(20, 47, 28, 14);
		panelTEF_2.add(lblValueTEF_1);
		lblValueTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValueTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblMinutesTEF_1 = new JLabel("min.");
		lblMinutesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinutesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMinutesTEF_1.setBounds(155, 47, 20, 14);
		panelTEF_2.add(lblMinutesTEF_1);

		final JLabel lblValuesTEF_1 = new JLabel("Valores:");
		lblValuesTEF_1.setBounds(10, 47, 39, 14);
		panelTEF_2.add(lblValuesTEF_1);
		lblValuesTEF_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValuesTEF_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textFieldMaxTEF_2 = new JTextField();
		textFieldMaxTEF_2.setText("7");
		textFieldMaxTEF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMaxTEF_2.setColumns(10);
		textFieldMaxTEF_2.setBounds(120, 44, 28, 20);
		panelTEF_2.add(textFieldMaxTEF_2);

		textFieldMedTEF_2 = new JTextField();
		textFieldMedTEF_2.setText("5");
		textFieldMedTEF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMedTEF_2.setColumns(10);
		textFieldMedTEF_2.setBounds(88, 44, 28, 20);
		panelTEF_2.add(textFieldMedTEF_2);
		textFieldMedTEF_2.setVisible(false);

		textFieldMinTEF_2 = new JTextField();
		textFieldMinTEF_2.setText("3");
		textFieldMinTEF_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldMinTEF_2.setColumns(10);
		textFieldMinTEF_2.setBounds(56, 44, 28, 20);
		panelTEF_2.add(textFieldMinTEF_2);

		JLabel lblProbabilidadeDeFalha = new JLabel("Probabilidade de falha:");
		lblProbabilidadeDeFalha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProbabilidadeDeFalha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProbabilidadeDeFalha.setBounds(10, 110, 126, 14);
		panelServer_2.add(lblProbabilidadeDeFalha);

		textField_2 = new JTextField();
		textField_2.setText("30");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_2.setColumns(10);
		textField_2.setBounds(145, 107, 23, 20);
		panelServer_2.add(textField_2);

		JLabel label_1 = new JLabel("%");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(171, 110, 11, 14);
		panelServer_2.add(label_1);

		lblValuesTEF_1.setVisible(false);
		textFieldMaxTEF_2.setVisible(false);
		textFieldMedTEF_2.setVisible(false);
		textFieldMinTEF_2.setVisible(true);

		comboBoxTEF_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEF_2.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEF_2.setVisible(false);
					textFieldMedTEF_2.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);

				} else if (comboBoxTEF_2.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEF_2.setVisible(false);
					textFieldMedTEF_2.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_2.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEF_2.setVisible(true);
					textFieldMedTEF_2.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_2.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEF_2.setVisible(false);
					textFieldMedTEF_2.setVisible(true);
					lblValueTEF_1.setVisible(false);
					lblValuesTEF_1.setVisible(true);

				} else if (comboBoxTEF_2.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEF_2.setVisible(false);
					textFieldMedTEF_2.setVisible(false);
					lblValueTEF_1.setVisible(true);
					lblValuesTEF_1.setVisible(false);
				}
			}
		});

		lblValuesTEmF_1.setVisible(false);
		textFieldMaxTEmF_2.setVisible(false);
		textFieldMedTEmF_2.setVisible(false);
		textFieldMinTEmF_2.setVisible(true);

		comboBoxTEmF_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxTEmF_2.getSelectedItem().toString()
						.equals("Constante")) {
					textFieldMaxTEmF_2.setVisible(false);
					textFieldMedTEmF_2.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);

				} else if (comboBoxTEmF_2.getSelectedItem().toString()
						.equals("Normal")) {
					textFieldMaxTEmF_2.setVisible(false);
					textFieldMedTEmF_2.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_2.getSelectedItem().toString()
						.equals("Triangular")) {
					textFieldMaxTEmF_2.setVisible(true);
					textFieldMedTEmF_2.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_2.getSelectedItem().toString()
						.equals("Uniforme")) {
					textFieldMaxTEmF_2.setVisible(false);
					textFieldMedTEmF_2.setVisible(true);
					lblValueTEmF_1.setVisible(false);
					lblValuesTEmF_1.setVisible(true);

				} else if (comboBoxTEmF_2.getSelectedItem().toString()
						.equals("Exponencial")) {
					textFieldMaxTEmF_2.setVisible(false);
					textFieldMedTEmF_2.setVisible(false);
					lblValueTEmF_1.setVisible(true);
					lblValuesTEmF_1.setVisible(false);
				}
			}
		});
	}

	private void renderEntityPanel() {

		panelEntities = new JPanel();
		panelEntities.setBounds(10, 82, 202, 167);
		panelConfiguration.add(panelEntities);
		panelEntities.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Entidades",
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
		panelProbabilityEntity.setBounds(10, 106, 183, 51);
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
		return panelTEF_1;
	}

	public JPanel getPanelTEmF_1() {
		return panelTEmF_1;
	}

	public static JComboBox getComboBoxTimeServer_1() {
		return comboBoxTimeServer_1;
	}

	public static JComboBox getComboBoxTEF_1() {
		return comboBoxTEF_1;
	}

	public static JComboBox getComboBoxTEmF_1() {
		return comboBoxTEmF_1;
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

	public static JTextField getTextFieldMaxTEF_1() {
		return textFieldMaxTEF_1;
	}

	public static JTextField getTextFieldMedTEF_1() {
		return textFieldMedTEF_1;
	}

	public static JTextField getTextFieldMinTEF_1() {
		return textFieldMinTEF_1;
	}

	public static JTextField getTextFieldMaxTEmF_1() {
		return textFieldMaxTEmF_1;
	}

	public static JTextField getTextFieldMedTEmF_1() {
		return textFieldMedTEmF_1;
	}

	public static JTextField getTextFieldMinTEmF_1() {
		return textFieldMinTEmF_1;
	}

	public static JTextField getTextField_1() {
		return textField_1;
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

	public static JComboBox getComboBoxTEF_2() {
		return comboBoxTEF_2;
	}

	public static JComboBox getComboBoxTEmF_2() {
		return comboBoxTEmF_2;
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

	public static JTextField getTextFieldMaxTEF_2() {
		return textFieldMaxTEF_2;
	}

	public static JTextField getTextFieldMedTEF_2() {
		return textFieldMedTEF_2;
	}

	public static JTextField getTextFieldMinTEF_2() {
		return textFieldMinTEF_2;
	}

	public static JTextField getTextFieldMaxTEmF_2() {
		return textFieldMaxTEmF_2;
	}

	public static JTextField getTextFieldMedTEmF_2() {
		return textFieldMedTEmF_2;
	}

	public static JTextField getTextFieldMinTEmF_2() {
		return textFieldMinTEmF_2;
	}

	public static JTextField getTextField_2() {
		return textField_2;
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
