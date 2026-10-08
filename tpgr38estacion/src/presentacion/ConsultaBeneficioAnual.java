package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import logica.datatypes.DTBeneficioAnual;
import logica.interfaces.IControladorBeneficioAnual;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ConsultaBeneficioAnual extends JInternalFrame {
	private IControladorBeneficioAnual icba;
	private IControladorUsuario icu;
	private JLabel lblIngresePaciente;
	private JComboBox<String> comboBoxPacientes;
	private JLabel lblIngreseAnio;
	private JComboBox<Integer> comboBoxBeneficioAnual;
	private JButton btnConsultar;
	private JPanel panelDataConsultaBeneficio;
	private JLabel lblAnio;
	private JLabel lblOrdenes;
	private JLabel lblTickets;
	private JTextField textFieldAnio;
	private JTextField textFieldOrdenes;
	private JTextField textFieldTickets;
	private JLabel lblBeneficioAnual;

	public ConsultaBeneficioAnual(IControladorBeneficioAnual icba, IControladorUsuario icu) {
		this.icba = icba;
		this.icu = icu;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Beneficio Anual");
		setBounds(10, 40, 450, 300);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0 };
		contentPane.setLayout(gridBagLayout);

		lblIngresePaciente = new JLabel("Paciente:");
		lblIngresePaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngresePaciente = new GridBagConstraints();
		gbc_lblIngresePaciente.anchor = GridBagConstraints.EAST;
		gbc_lblIngresePaciente.fill = GridBagConstraints.VERTICAL;
		gbc_lblIngresePaciente.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresePaciente.gridx = 0;
		gbc_lblIngresePaciente.gridy = 0;
		getContentPane().add(lblIngresePaciente, gbc_lblIngresePaciente);

		comboBoxPacientes = new JComboBox<>();
		comboBoxPacientes.setSelectedIndex(-1);
		comboBoxPacientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname = (String) comboBoxPacientes.getSelectedItem();
				if (nickname != null) { // <--- ESTA VALIDACIÓN EVITA CRASHES AL RECARGAR
					cargarBeneficiosAnuales(nickname);
				}
			}
		});
		GridBagConstraints gbc_comboBoxUsuarios = new GridBagConstraints();
		gbc_comboBoxUsuarios.gridwidth = 2;
		gbc_comboBoxUsuarios.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUsuarios.fill = GridBagConstraints.BOTH;
		gbc_comboBoxUsuarios.gridx = 1;
		gbc_comboBoxUsuarios.gridy = 0;
		getContentPane().add(comboBoxPacientes, gbc_comboBoxUsuarios);

		lblIngreseAnio = new JLabel("Año:");
		lblIngreseAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseAnio = new GridBagConstraints();
		gbc_lblIngreseAnio.anchor = GridBagConstraints.EAST;
		gbc_lblIngreseAnio.fill = GridBagConstraints.VERTICAL;
		gbc_lblIngreseAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseAnio.gridx = 0;
		gbc_lblIngreseAnio.gridy = 1;
		getContentPane().add(lblIngreseAnio, gbc_lblIngreseAnio);

		comboBoxBeneficioAnual = new JComboBox<>();
		comboBoxBeneficioAnual.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxBeneficioAnual = new GridBagConstraints();
		gbc_comboBoxBeneficioAnual.gridwidth = 2;
		gbc_comboBoxBeneficioAnual.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxBeneficioAnual.fill = GridBagConstraints.BOTH;
		gbc_comboBoxBeneficioAnual.gridx = 1;
		gbc_comboBoxBeneficioAnual.gridy = 1;
		getContentPane().add(comboBoxBeneficioAnual, gbc_comboBoxBeneficioAnual);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdConsultaBeneficioAnualActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 2;
		getContentPane().add(btnConsultar, gbc_btnAceptar);

		panelDataConsultaBeneficio = new JPanel();
		GridBagConstraints gbc_panelDataConsultaBeneficio = new GridBagConstraints();
		gbc_panelDataConsultaBeneficio.anchor = GridBagConstraints.EAST;
		gbc_panelDataConsultaBeneficio.gridwidth = 3;
		gbc_panelDataConsultaBeneficio.gridx = 0;
		gbc_panelDataConsultaBeneficio.gridy = 4;
		GridBagLayout gbl_panelDataConsultaBeneficio = new GridBagLayout();
		gbl_panelDataConsultaBeneficio.columnWidths = new int[] { 120, 150, 150 };
		gbl_panelDataConsultaBeneficio.rowHeights = new int[] { 30, 30, 30, 30, 0 };
		gbl_panelDataConsultaBeneficio.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gbl_panelDataConsultaBeneficio.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelDataConsultaBeneficio.setLayout(gbl_panelDataConsultaBeneficio);
		contentPane.add(panelDataConsultaBeneficio, gbc_panelDataConsultaBeneficio);

		lblBeneficioAnual = new JLabel("Beneficio Anual:");
		lblBeneficioAnual.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBeneficioAnual = new GridBagConstraints();
		gbc_lblBeneficioAnual.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBeneficioAnual.gridwidth = 3;
		gbc_lblBeneficioAnual.insets = new Insets(0, 0, 5, 0);
		gbc_lblBeneficioAnual.gridx = 0;
		gbc_lblBeneficioAnual.gridy = 0;
		panelDataConsultaBeneficio.add(lblBeneficioAnual, gbc_lblBeneficioAnual);

		lblAnio = new JLabel("Año:");
		lblAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAnio = new GridBagConstraints();
		gbc_lblAnio.anchor = GridBagConstraints.EAST;
		gbc_lblAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnio.gridx = 0;
		gbc_lblAnio.gridy = 1;
		panelDataConsultaBeneficio.add(lblAnio, gbc_lblAnio);

		textFieldAnio = new JTextField();
		textFieldAnio.setEditable(false);
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.gridwidth = 2;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAnio.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnio.gridx = 1;
		gbc_textFieldAnio.gridy = 1;
		panelDataConsultaBeneficio.add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);

		lblOrdenes = new JLabel("Consultas:");
		lblOrdenes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblOrdenes = new GridBagConstraints();
		gbc_lblOrdenes.anchor = GridBagConstraints.EAST;
		gbc_lblOrdenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrdenes.gridx = 0;
		gbc_lblOrdenes.gridy = 2;
		panelDataConsultaBeneficio.add(lblOrdenes, gbc_lblOrdenes);

		textFieldOrdenes = new JTextField();
		textFieldOrdenes.setEditable(false);
		GridBagConstraints gbc_textFieldOrdenes = new GridBagConstraints();
		gbc_textFieldOrdenes.gridwidth = 2;
		gbc_textFieldOrdenes.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldOrdenes.fill = GridBagConstraints.BOTH;
		gbc_textFieldOrdenes.gridx = 1;
		gbc_textFieldOrdenes.gridy = 2;
		panelDataConsultaBeneficio.add(textFieldOrdenes, gbc_textFieldOrdenes);
		textFieldOrdenes.setColumns(10);

		lblTickets = new JLabel("Medicamentos:");
		lblTickets.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTickets = new GridBagConstraints();
		gbc_lblTickets.anchor = GridBagConstraints.EAST;
		gbc_lblTickets.insets = new Insets(0, 0, 0, 5);
		gbc_lblTickets.gridx = 0;
		gbc_lblTickets.gridy = 3;
		panelDataConsultaBeneficio.add(lblTickets, gbc_lblTickets);

		textFieldTickets = new JTextField();
		textFieldTickets.setEditable(false);
		GridBagConstraints gbc_textFieldTickets = new GridBagConstraints();
		gbc_textFieldTickets.gridwidth = 2;
		gbc_textFieldTickets.fill = GridBagConstraints.BOTH;
		gbc_textFieldTickets.gridx = 1;
		gbc_textFieldTickets.gridy = 3;
		panelDataConsultaBeneficio.add(textFieldTickets, gbc_textFieldTickets);
		textFieldTickets.setColumns(10);

	}

	public void cargarPacientes() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model = new DefaultComboBoxModel<>(icu.listarPacientes().toArray(String[]::new));
		comboBoxPacientes.setModel(model);
	}

	private void cargarBeneficiosAnuales(String nickname) {
		DefaultComboBoxModel<Integer> model;
		model = new DefaultComboBoxModel<>(icba.listarBeneficiosAnuales(nickname).toArray(new Integer[0]));
		comboBoxBeneficioAnual.setModel(model);
	}

	protected void cmdConsultaBeneficioAnualActionPerformed(ActionEvent arg0) {
		String nickname = (String) this.comboBoxPacientes.getSelectedItem();
		Integer anio = (Integer) this.comboBoxBeneficioAnual.getSelectedItem();

		if (checkFormulario()) {
			DTBeneficioAnual dtba = icba.consultaBeneficioAnual(nickname, anio);
			textFieldAnio.setText(String.valueOf(dtba.getAnio()));
			textFieldOrdenes.setText(String.valueOf(dtba.getCantOrd()));
			textFieldTickets.setText(String.valueOf(dtba.getCantMed()));
		}
	}

	private boolean checkFormulario() {
		String nickname = (String) this.comboBoxPacientes.getSelectedItem();
		Integer anio = (Integer) this.comboBoxBeneficioAnual.getSelectedItem();

		if (nickname == null || anio == null) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Consulta de Beneficio Anual",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void limpiarFormulario() {
		comboBoxPacientes.setSelectedIndex(-1);
		comboBoxBeneficioAnual.setSelectedIndex(-1);
		textFieldAnio.setText("");
		textFieldOrdenes.setText("");
		textFieldTickets.setText("");
	}

}
