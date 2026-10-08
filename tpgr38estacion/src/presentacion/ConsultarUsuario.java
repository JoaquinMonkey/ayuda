package presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import excepciones.UsuarioNoExisteException;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDatosUsuarioMedico;
import logica.datatypes.DTDatosUsuarioPaciente;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTReservaResumen;
import logica.datatypes.DTUsuario;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {
	private IControladorUsuario controlUsr;
	private JPanel contentPane;
	private JComboBox<DTUsuario> comboBoxUsuarios;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldMail;
	private JPanel panelContenedor;
	private JPanel panelPaciente;
	private JPanel panelMedico;
	private JTextField textFieldFechaNac;
	private JTextField textFieldFechaIng;
	private JTextField textFieldCI;
	private JTextField textFieldAntecedentes;
	private JTextField textFieldNumReg;
	private JTextField textFieldMesLic;
	private JTextField textFieldPorcentaje;
	private JTextField textFieldWeb;
	private JPanel panelContenedor2;
	private JPanel panelVacio2;
	private JPanel panelReservas;
	private JPanel panelConsultas;
	private JList<DTReservaResumen> listReservas;
	private JList<DTConsulta> listConsultas;
	private JLabel lblEspecialidades;
	private JTextField textFieldEspecialidades;
	private JLabel lblTipo;
	private JTextField textFieldTipo;

	public ConsultarUsuario(IControladorUsuario icu, ConsultaConsulta consultaConsulta,
			ConsultaReserva consultaReserva) {
		controlUsr = icu;
		setTitle("Consultar Usuario");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(30, 30, 500, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 120, 150, 150 };
		gbl_contentPane.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 140 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 0;
		contentPane.add(lblUsuario, gbc_lblUsuario);

		comboBoxUsuarios = new JComboBox<>();
		GridBagConstraints gbc_comboBoxUsuarios = new GridBagConstraints();
		gbc_comboBoxUsuarios.gridwidth = 2;
		gbc_comboBoxUsuarios.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxUsuarios.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxUsuarios.gridx = 1;
		gbc_comboBoxUsuarios.gridy = 0;
		contentPane.add(comboBoxUsuarios, gbc_comboBoxUsuarios);
		comboBoxUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DTUsuario seleccionado = (DTUsuario) comboBoxUsuarios.getSelectedItem();
				if (seleccionado != null) {
					seleccionarUsuario(seleccionado);
				}
			}
		});

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 1;
		contentPane.add(lblTipo, gbc_lblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbc_textFieldTipo = new GridBagConstraints();
		gbc_textFieldTipo.gridwidth = 2;
		gbc_textFieldTipo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTipo.fill = GridBagConstraints.BOTH;
		gbc_textFieldTipo.gridx = 1;
		gbc_textFieldTipo.gridy = 1;
		contentPane.add(textFieldTipo, gbc_textFieldTipo);
		textFieldTipo.setColumns(10);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 2;
		contentPane.add(lblNickname, gbc_lblNickname);

		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		textFieldNickname.setColumns(10);
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 2;
		contentPane.add(textFieldNickname, gbc_textFieldNickname);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		contentPane.add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		contentPane.add(textFieldNombre, gbc_textFieldNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 4;
		contentPane.add(lblApellido, gbc_lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setColumns(10);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 4;
		contentPane.add(textFieldApellido, gbc_textFieldApellido);

		JLabel lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 0;
		gbc_lblMail.gridy = 5;
		contentPane.add(lblMail, gbc_lblMail);

		textFieldMail = new JTextField();
		textFieldMail.setEditable(false);
		textFieldMail.setColumns(10);
		GridBagConstraints gbc_textFieldMail = new GridBagConstraints();
		gbc_textFieldMail.gridwidth = 2;
		gbc_textFieldMail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMail.fill = GridBagConstraints.BOTH;
		gbc_textFieldMail.gridx = 1;
		gbc_textFieldMail.gridy = 5;
		contentPane.add(textFieldMail, gbc_textFieldMail);

		panelContenedor = new JPanel();
		GridBagConstraints gbc_panelContenedor = new GridBagConstraints();
		gbc_panelContenedor.fill = GridBagConstraints.BOTH;
		gbc_panelContenedor.insets = new Insets(0, 0, 5, 0);
		gbc_panelContenedor.gridwidth = 3;
		gbc_panelContenedor.gridx = 0;
		gbc_panelContenedor.gridy = 6;
		contentPane.add(panelContenedor, gbc_panelContenedor);
		panelContenedor.setLayout(new CardLayout(0, 0));

		JPanel panelVacio = new JPanel();
		panelContenedor.add(panelVacio, "vacio");

		panelPaciente = new JPanel();
		panelContenedor.add(panelPaciente, "paciente");
		GridBagLayout gbl_panelPaciente = new GridBagLayout();
		gbl_panelPaciente.columnWidths = new int[] { 120, 150, 150 };
		gbl_panelPaciente.rowHeights = new int[] { 30, 30, 30, 30, 0 };
		gbl_panelPaciente.columnWeights = new double[] { 0.0, 1.0, 1.0 };
		gbl_panelPaciente.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelPaciente.setLayout(gbl_panelPaciente);

		JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		lblFechaNac.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaNac = new GridBagConstraints();
		gbc_lblFechaNac.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNac.gridx = 0;
		gbc_lblFechaNac.gridy = 0;
		panelPaciente.add(lblFechaNac, gbc_lblFechaNac);

		textFieldFechaNac = new JTextField();
		textFieldFechaNac.setEditable(false);
		textFieldFechaNac.setColumns(10);
		GridBagConstraints gbc_textFieldFechaNac = new GridBagConstraints();
		gbc_textFieldFechaNac.gridwidth = 2;
		gbc_textFieldFechaNac.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaNac.fill = GridBagConstraints.BOTH;
		gbc_textFieldFechaNac.gridx = 1;
		gbc_textFieldFechaNac.gridy = 0;
		panelPaciente.add(textFieldFechaNac, gbc_textFieldFechaNac);

		JLabel lblFechaIng = new JLabel("Fecha ingreso:");
		lblFechaIng.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaIng = new GridBagConstraints();
		gbc_lblFechaIng.anchor = GridBagConstraints.EAST;
		gbc_lblFechaIng.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaIng.gridx = 0;
		gbc_lblFechaIng.gridy = 1;
		panelPaciente.add(lblFechaIng, gbc_lblFechaIng);

		textFieldFechaIng = new JTextField();
		textFieldFechaIng.setEditable(false);
		textFieldFechaIng.setColumns(10);
		GridBagConstraints gbc_textFieldFechaIng = new GridBagConstraints();
		gbc_textFieldFechaIng.gridwidth = 2;
		gbc_textFieldFechaIng.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaIng.fill = GridBagConstraints.BOTH;
		gbc_textFieldFechaIng.gridx = 1;
		gbc_textFieldFechaIng.gridy = 1;
		panelPaciente.add(textFieldFechaIng, gbc_textFieldFechaIng);

		JLabel lblCI = new JLabel("CI:");
		lblCI.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCI = new GridBagConstraints();
		gbc_lblCI.anchor = GridBagConstraints.EAST;
		gbc_lblCI.insets = new Insets(0, 0, 5, 5);
		gbc_lblCI.gridx = 0;
		gbc_lblCI.gridy = 2;
		panelPaciente.add(lblCI, gbc_lblCI);

		textFieldCI = new JTextField();
		textFieldCI.setEditable(false);
		textFieldCI.setColumns(10);
		GridBagConstraints gbc_textFieldCI = new GridBagConstraints();
		gbc_textFieldCI.gridwidth = 2;
		gbc_textFieldCI.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCI.fill = GridBagConstraints.BOTH;
		gbc_textFieldCI.gridx = 1;
		gbc_textFieldCI.gridy = 2;
		panelPaciente.add(textFieldCI, gbc_textFieldCI);

		JLabel lblAntecedentes = new JLabel("Antecedentes:");
		lblAntecedentes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAntecedentes = new GridBagConstraints();
		gbc_lblAntecedentes.anchor = GridBagConstraints.EAST;
		gbc_lblAntecedentes.insets = new Insets(0, 0, 0, 5);
		gbc_lblAntecedentes.gridx = 0;
		gbc_lblAntecedentes.gridy = 3;
		panelPaciente.add(lblAntecedentes, gbc_lblAntecedentes);

		textFieldAntecedentes = new JTextField();
		textFieldAntecedentes.setEditable(false);
		textFieldAntecedentes.setColumns(10);
		GridBagConstraints gbc_textFieldAntecedentes = new GridBagConstraints();
		gbc_textFieldAntecedentes.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAntecedentes.gridwidth = 2;
		gbc_textFieldAntecedentes.fill = GridBagConstraints.BOTH;
		gbc_textFieldAntecedentes.gridx = 1;
		gbc_textFieldAntecedentes.gridy = 3;
		panelPaciente.add(textFieldAntecedentes, gbc_textFieldAntecedentes);

		panelMedico = new JPanel();
		panelContenedor.add(panelMedico, "medico");
		GridBagLayout gbl_panelMedico = new GridBagLayout();
		gbl_panelMedico.columnWidths = new int[] { 120, 150, 150 };
		gbl_panelMedico.rowHeights = new int[] { 30, 30, 30, 30, 30, 0 };
		gbl_panelMedico.columnWeights = new double[] { 0.0, 1.0, 1.0 };
		gbl_panelMedico.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelMedico.setLayout(gbl_panelMedico);

		JLabel lblNumReg = new JLabel("Número Registro:");
		lblNumReg.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumReg = new GridBagConstraints();
		gbc_lblNumReg.anchor = GridBagConstraints.EAST;
		gbc_lblNumReg.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumReg.gridx = 0;
		gbc_lblNumReg.gridy = 0;
		panelMedico.add(lblNumReg, gbc_lblNumReg);

		textFieldNumReg = new JTextField();
		textFieldNumReg.setEditable(false);
		textFieldNumReg.setColumns(10);
		GridBagConstraints gbc_textFieldNumReg = new GridBagConstraints();
		gbc_textFieldNumReg.gridwidth = 3;
		gbc_textFieldNumReg.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumReg.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumReg.gridx = 1;
		gbc_textFieldNumReg.gridy = 0;
		panelMedico.add(textFieldNumReg, gbc_textFieldNumReg);

		JLabel lblMesLic = new JLabel("Mes Licencia:");
		lblMesLic.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMesLic = new GridBagConstraints();
		gbc_lblMesLic.anchor = GridBagConstraints.EAST;
		gbc_lblMesLic.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesLic.gridx = 0;
		gbc_lblMesLic.gridy = 1;
		panelMedico.add(lblMesLic, gbc_lblMesLic);

		textFieldMesLic = new JTextField();
		textFieldMesLic.setEditable(false);
		textFieldMesLic.setColumns(10);
		GridBagConstraints gbc_textFieldMesLic = new GridBagConstraints();
		gbc_textFieldMesLic.gridwidth = 3;
		gbc_textFieldMesLic.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMesLic.fill = GridBagConstraints.BOTH;
		gbc_textFieldMesLic.gridx = 1;
		gbc_textFieldMesLic.gridy = 1;
		panelMedico.add(textFieldMesLic, gbc_textFieldMesLic);

		JLabel lblPorcentaje = new JLabel("% adicional:");
		lblPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPorcentaje = new GridBagConstraints();
		gbc_lblPorcentaje.anchor = GridBagConstraints.EAST;
		gbc_lblPorcentaje.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorcentaje.gridx = 0;
		gbc_lblPorcentaje.gridy = 2;
		panelMedico.add(lblPorcentaje, gbc_lblPorcentaje);

		textFieldPorcentaje = new JTextField();
		textFieldPorcentaje.setEditable(false);
		textFieldPorcentaje.setColumns(10);
		GridBagConstraints gbc_textFieldPorcentaje = new GridBagConstraints();
		gbc_textFieldPorcentaje.gridwidth = 3;
		gbc_textFieldPorcentaje.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPorcentaje.fill = GridBagConstraints.BOTH;
		gbc_textFieldPorcentaje.gridx = 1;
		gbc_textFieldPorcentaje.gridy = 2;
		panelMedico.add(textFieldPorcentaje, gbc_textFieldPorcentaje);

		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblWeb = new GridBagConstraints();
		gbc_lblWeb.anchor = GridBagConstraints.EAST;
		gbc_lblWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeb.gridx = 0;
		gbc_lblWeb.gridy = 3;
		panelMedico.add(lblWeb, gbc_lblWeb);

		textFieldWeb = new JTextField();
		textFieldWeb.setEditable(false);
		textFieldWeb.setColumns(10);
		GridBagConstraints gbc_textFieldWeb = new GridBagConstraints();
		gbc_textFieldWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldWeb.gridwidth = 3;
		gbc_textFieldWeb.fill = GridBagConstraints.BOTH;
		gbc_textFieldWeb.gridx = 1;
		gbc_textFieldWeb.gridy = 3;
		panelMedico.add(textFieldWeb, gbc_textFieldWeb);

		lblEspecialidades = new JLabel("Especialidades:");
		lblEspecialidades.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEspecialidades = new GridBagConstraints();
		gbc_lblEspecialidades.anchor = GridBagConstraints.EAST;
		gbc_lblEspecialidades.insets = new Insets(0, 0, 0, 5);
		gbc_lblEspecialidades.gridx = 0;
		gbc_lblEspecialidades.gridy = 4;
		panelMedico.add(lblEspecialidades, gbc_lblEspecialidades);

		textFieldEspecialidades = new JTextField();
		textFieldEspecialidades.setEditable(false);
		GridBagConstraints gbc_textFieldEspecialidades = new GridBagConstraints();
		gbc_textFieldEspecialidades.gridwidth = 3;
		gbc_textFieldEspecialidades.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEspecialidades.fill = GridBagConstraints.BOTH;
		gbc_textFieldEspecialidades.gridx = 1;
		gbc_textFieldEspecialidades.gridy = 4;
		panelMedico.add(textFieldEspecialidades, gbc_textFieldEspecialidades);
		textFieldEspecialidades.setColumns(10);

		panelContenedor2 = new JPanel();
		GridBagConstraints gbc_panelContenedor2 = new GridBagConstraints();
		gbc_panelContenedor2.fill = GridBagConstraints.BOTH;
		gbc_panelContenedor2.gridwidth = 3;
		gbc_panelContenedor2.gridx = 0;
		gbc_panelContenedor2.gridy = 7;
		contentPane.add(panelContenedor2, gbc_panelContenedor2);
		panelContenedor2.setLayout(new CardLayout(0, 0));

		panelVacio2 = new JPanel();
		panelContenedor2.add(panelVacio2, "vacio");
		panelReservas = new JPanel();
		panelReservas.setLayout(new BorderLayout());
		panelReservas.add(new JLabel("Reservas de consultas:"), BorderLayout.NORTH);
		listReservas = new JList<>();
		panelReservas.add(new JScrollPane(listReservas), BorderLayout.CENTER);
		listReservas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					DTReservaResumen seleccionada = listReservas.getSelectedValue();
					if (seleccionada != null) {
						consultaReserva.mostrarReserva(seleccionada.getConsulta(), seleccionada.getNumero());
					}
				}
			}
		});
		panelContenedor2.add(panelReservas, "reservas");

		panelConsultas = new JPanel();
		panelConsultas.setLayout(new BorderLayout());
		panelConsultas.add(new JLabel("Consultas asociadas:"), BorderLayout.NORTH);
		listConsultas = new JList<>();
		panelConsultas.add(new JScrollPane(listConsultas), BorderLayout.CENTER);
		listConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					DTConsulta seleccionada = listConsultas.getSelectedValue();
					if (seleccionada != null) {
						consultaConsulta.mostrarConsulta(seleccionada.getNombre());
					}
				}
			}
		});
		panelContenedor2.add(panelConsultas, "consultas");

		CardLayout cl = (CardLayout) panelContenedor.getLayout();
		cl.show(panelContenedor, "vacio");

		CardLayout cl2 = (CardLayout) panelContenedor2.getLayout();
		cl2.show(panelContenedor2, "vacio");
	}

	public void cargarUsuarios() {
		DefaultComboBoxModel<DTUsuario> model = new DefaultComboBoxModel<>(controlUsr.listarUsuarios());
		comboBoxUsuarios.setModel(model);
	}

	private void seleccionarUsuario(DTUsuario seleccionado) {
		mostrarUsuario(seleccionado);
	}

	private void mostrarUsuario(DTUsuario usuario) {
		textFieldNickname.setText(usuario.getNickname());
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldMail.setText(usuario.getMail());

		CardLayout cl = (CardLayout) panelContenedor.getLayout();
		CardLayout cl2 = (CardLayout) panelContenedor2.getLayout();

		if (usuario instanceof DTPaciente) {
			DTPaciente paciente = (DTPaciente) usuario;
			textFieldTipo.setText("Paciente");
			textFieldFechaNac.setText(paciente.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			textFieldFechaIng.setText(paciente.getFechaIng().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			textFieldCI.setText(String.valueOf(paciente.getCI()));
			textFieldAntecedentes.setText(paciente.getAntecedentes().toString());
			cl.show(panelContenedor, "paciente");

			cargarReservas(usuario.getNickname());
			cl2.show(panelContenedor2, "reservas");

		} else if (usuario instanceof DTMedico) {
			DTMedico medico = (DTMedico) usuario;
			textFieldTipo.setText("Medico");
			textFieldNumReg.setText(String.valueOf(medico.getNumReg()));
			textFieldMesLic.setText(String.valueOf(medico.getMesUL()));
			textFieldPorcentaje.setText(String.valueOf(medico.getPorcentajeAd()));
			textFieldWeb.setText(medico.getWeb());
			textFieldEspecialidades.setText(String.join(", ", medico.getEspecialidades()));
			cl.show(panelContenedor, "medico");

			cargarConsultas(usuario.getNickname());
			cl2.show(panelContenedor2, "consultas");
		}
	}

	private void cargarReservas(String nickname) {
		DefaultListModel<DTReservaResumen> model = new DefaultListModel<>();
		try {
			DTDatosUsuarioPaciente datos = controlUsr.consultarPaciente(nickname);
			for (DTReservaResumen r : datos.getReservasConsultas()) {
				model.addElement(r);
			}
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		}
		listReservas.setModel(model);
	}

	private void cargarConsultas(String nickname) {
		DefaultListModel<DTConsulta> model = new DefaultListModel<>();
		try {
			DTDatosUsuarioMedico datos = controlUsr.consultarMedico(nickname);
			for (DTConsulta c : datos.getConsultas()) {
				model.addElement(c);
			}
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();
		}
		listConsultas.setModel(model);
	}
}