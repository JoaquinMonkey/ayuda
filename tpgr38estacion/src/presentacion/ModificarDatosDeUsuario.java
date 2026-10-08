package presentacion;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

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

import excepciones.EspecialidadNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTUsuario;
import logica.interfaces.IControladorEspecialidad;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ModificarDatosDeUsuario extends JInternalFrame {
	private IControladorUsuario controlUsr;
	private IControladorEspecialidad ice;
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
	private JTextField textFieldEspecialidades;
	private DTUsuario usuarioSeleccionado;

	public ModificarDatosDeUsuario(IControladorUsuario icu, IControladorEspecialidad ice) {
		controlUsr = icu;
		this.ice = ice;

		setTitle("Modificar Datos De Usuario");
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(30, 30, 500, 430);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 125, 150, 150, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 30, 30, 30, 30, 140, 0, 30, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
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

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.EAST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 1;
		contentPane.add(lblNickname, gbc_lblNickname);

		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		textFieldNickname.setColumns(10);
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 1;
		contentPane.add(textFieldNickname, gbc_textFieldNickname);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		contentPane.add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(true);
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 2;
		contentPane.add(textFieldNombre, gbc_textFieldNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.EAST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 3;
		contentPane.add(lblApellido, gbc_lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(true);
		textFieldApellido.setColumns(10);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 3;
		contentPane.add(textFieldApellido, gbc_textFieldApellido);

		JLabel lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 0;
		gbc_lblMail.gridy = 4;
		contentPane.add(lblMail, gbc_lblMail);

		textFieldMail = new JTextField();
		textFieldMail.setEditable(false);
		textFieldMail.setColumns(10);
		GridBagConstraints gbc_textFieldMail = new GridBagConstraints();
		gbc_textFieldMail.gridwidth = 2;
		gbc_textFieldMail.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMail.fill = GridBagConstraints.BOTH;
		gbc_textFieldMail.gridx = 1;
		gbc_textFieldMail.gridy = 4;
		contentPane.add(textFieldMail, gbc_textFieldMail);

		panelContenedor = new JPanel();

		GridBagConstraints gbc_panelContenedor = new GridBagConstraints();
		gbc_panelContenedor.fill = GridBagConstraints.BOTH;
		gbc_panelContenedor.gridwidth = 3;
		gbc_panelContenedor.insets = new Insets(0, 0, 5, 0);
		gbc_panelContenedor.gridx = 0;
		gbc_panelContenedor.gridy = 5;

		contentPane.add(panelContenedor, gbc_panelContenedor);

		panelContenedor.setLayout(new CardLayout());

		JPanel panelVacio = new JPanel();
		panelContenedor.add(panelVacio, "vacio");

		// paciente
		panelPaciente = new JPanel();
		panelContenedor.add(panelPaciente, "paciente");

		GridBagLayout gbl_panelPaciente = new GridBagLayout();
		gbl_panelPaciente.columnWidths = new int[] { 125, 150, 150, 0 };
		gbl_panelPaciente.rowHeights = new int[] { 30, 30, 30, 30, 0 };
		gbl_panelPaciente.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };

		panelPaciente.setLayout(gbl_panelPaciente);

		JLabel lblFechaNac = new JLabel("Fecha nacimiento:");
		GridBagConstraints gbc_lblFechaNac = new GridBagConstraints();
		gbc_lblFechaNac.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNac.gridx = 0;
		gbc_lblFechaNac.gridy = 0;
		panelPaciente.add(lblFechaNac, gbc_lblFechaNac);

		textFieldFechaNac = new JTextField();
		GridBagConstraints gbc_textFieldFechaNac = new GridBagConstraints();
		gbc_textFieldFechaNac.gridwidth = 2;
		gbc_textFieldFechaNac.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaNac.fill = GridBagConstraints.BOTH;
		gbc_textFieldFechaNac.gridx = 1;
		gbc_textFieldFechaNac.gridy = 0;
		panelPaciente.add(textFieldFechaNac, gbc_textFieldFechaNac);

		JLabel lblFechaIng = new JLabel("Fecha De ingreso:");
		GridBagConstraints gbc_lblFechaIng = new GridBagConstraints();
		gbc_lblFechaIng.anchor = GridBagConstraints.EAST;
		gbc_lblFechaIng.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaIng.gridx = 0;
		gbc_lblFechaIng.gridy = 1;
		panelPaciente.add(lblFechaIng, gbc_lblFechaIng);

		textFieldFechaIng = new JTextField();
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
		GridBagConstraints gbc_textFieldAntecedentes = new GridBagConstraints();
		gbc_textFieldAntecedentes.gridwidth = 2;
		gbc_textFieldAntecedentes.fill = GridBagConstraints.BOTH;
		gbc_textFieldAntecedentes.gridx = 1;
		gbc_textFieldAntecedentes.gridy = 3;
		panelPaciente.add(textFieldAntecedentes, gbc_textFieldAntecedentes);

		panelMedico = new JPanel();
		panelContenedor.add(panelMedico, "medico");
		GridBagLayout gbl_panelMedico = new GridBagLayout();
		gbl_panelMedico.columnWidths = new int[] { 125, 150, 150, 0 };
		gbl_panelMedico.rowHeights = new int[] { 30, 30, 30, 30, 30, 0 };
		gbl_panelMedico.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
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
		textFieldNumReg.setColumns(10);
		GridBagConstraints gbc_textFieldNumReg = new GridBagConstraints();
		gbc_textFieldNumReg.gridwidth = 2;
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
		textFieldMesLic.setColumns(10);
		GridBagConstraints gbc_textFieldMesLic = new GridBagConstraints();
		gbc_textFieldMesLic.gridwidth = 2;
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
		textFieldPorcentaje.setColumns(10);
		GridBagConstraints gbc_textFieldPorcentaje = new GridBagConstraints();
		gbc_textFieldPorcentaje.gridwidth = 2;
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
		textFieldWeb.setColumns(10);
		GridBagConstraints gbc_textFieldWeb = new GridBagConstraints();
		gbc_textFieldWeb.gridwidth = 2;
		gbc_textFieldWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldWeb.fill = GridBagConstraints.BOTH;
		gbc_textFieldWeb.gridx = 1;
		gbc_textFieldWeb.gridy = 3;
		panelMedico.add(textFieldWeb, gbc_textFieldWeb);

		JLabel lblEspecialidades = new JLabel("Especialidades:");
		lblEspecialidades.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEspecialidades = new GridBagConstraints();
		gbc_lblEspecialidades.anchor = GridBagConstraints.EAST;
		gbc_lblEspecialidades.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspecialidades.gridx = 0;
		gbc_lblEspecialidades.gridy = 4;
		panelMedico.add(lblEspecialidades, gbc_lblEspecialidades);

		textFieldEspecialidades = new JTextField();
		textFieldEspecialidades.setColumns(10);
		GridBagConstraints gbc_textFieldEspecialidades = new GridBagConstraints();
		gbc_textFieldEspecialidades.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEspecialidades.gridwidth = 2;
		gbc_textFieldEspecialidades.fill = GridBagConstraints.BOTH;
		gbc_textFieldEspecialidades.gridx = 1;
		gbc_textFieldEspecialidades.gridy = 4;
		panelMedico.add(textFieldEspecialidades, gbc_textFieldEspecialidades);

		JPanel panelBotones = new JPanel();
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.EAST;
		gbc_panelBotones.gridwidth = 3;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 6;
		contentPane.add(panelBotones, gbc_panelBotones);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarUsuario();
			}
		});
		panelBotones.add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panelBotones.add(btnCancelar);

		CardLayout cl = (CardLayout) panelContenedor.getLayout();
		cl.show(panelContenedor, "vacio");
	}

	public void cargarUsuarios() {
		try {
			DTUsuario[] usuarios = controlUsr.listarUsuarios();
			DefaultComboBoxModel<DTUsuario> model = new DefaultComboBoxModel<>(usuarios);
			comboBoxUsuarios.setModel(model);
		} catch (Exception e) {
			comboBoxUsuarios.setModel(new DefaultComboBoxModel<>());
		}

	}

	private void seleccionarUsuario(DTUsuario seleccionado) {
		try {
			DTUsuario usuario = controlUsr.seleccionarUsuario(seleccionado.getNickname());
			mostrarUsuario(usuario);
		} catch (UsuarioNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocurrió un error seleccionando el usuario", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void mostrarUsuario(DTUsuario usuario) {
		usuarioSeleccionado = usuario;

		textFieldNickname.setText(usuario.getNickname());
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldMail.setText(usuario.getMail());

		CardLayout cl = (CardLayout) panelContenedor.getLayout();

		if (usuario instanceof DTPaciente) {

			DTPaciente paciente = (DTPaciente) usuario;

			textFieldFechaNac.setText(paciente.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			textFieldFechaIng.setText(paciente.getFechaIng().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			textFieldCI.setText(String.valueOf(paciente.getCI()));
			textFieldAntecedentes.setText(paciente.getAntecedentes());

			cl.show(panelContenedor, "paciente");

		} else if (usuario instanceof DTMedico) {

			DTMedico medico = (DTMedico) usuario;

			textFieldNumReg.setText(String.valueOf(medico.getNumReg()));
			textFieldMesLic.setText(String.valueOf(medico.getMesUL()));
			textFieldPorcentaje.setText(String.valueOf(medico.getPorcentajeAd()));
			textFieldWeb.setText(medico.getWeb());
			textFieldEspecialidades.setText(String.join(", ", medico.getEspecialidades()));

			cl.show(panelContenedor, "medico");
		}
	}

	private void modificarUsuario() {
		if (usuarioSeleccionado == null) {
			return;
		}

		try {
			String nickname = textFieldNickname.getText();
			String nombre = textFieldNombre.getText();
			String apellido = textFieldApellido.getText();

			if (usuarioSeleccionado instanceof DTPaciente) {
				LocalDate fechaNac = LocalDate.parse(textFieldFechaNac.getText().trim(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalDate fechaIng = LocalDate.parse(textFieldFechaIng.getText().trim(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				int ci = Integer.parseInt(textFieldCI.getText());
				String antecedentes = textFieldAntecedentes.getText();

				controlUsr.modificarPaciente(nickname, nombre, apellido, fechaNac, ci, antecedentes, fechaIng);
			} else if (usuarioSeleccionado instanceof DTMedico) {
				int numReg = Integer.parseInt(textFieldNumReg.getText());
				int mesLic = Integer.parseInt(textFieldMesLic.getText());
				float porcentaje = Float.parseFloat(textFieldPorcentaje.getText());
				if (porcentaje < 0) {
					JOptionPane.showMessageDialog(this, "El porcentaje no puede ser negativo",
							"Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String web = textFieldWeb.getText();
				Set<String> especialidades = convertirASet(textFieldEspecialidades.getText());
				ice.existenEspecialidades(especialidades);
				controlUsr.modificarMedico(nickname, nombre, apellido, numReg, web, especialidades, porcentaje, mesLic);
			}
			JOptionPane.showMessageDialog(this, "Usuario modificado correctamente", "Modificar Datos de Usuario",
					JOptionPane.INFORMATION_MESSAGE);
			cargarUsuarios();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Los campos CI, número de registro, mes de licencia y porcentaje deben ser valores numéricos",
					"Datos inválidos", JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "Las fechas deben tener formato dd/mm/aaaa", "Fechas inválidas",
					JOptionPane.ERROR_MESSAGE);
		} catch (EspecialidadNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Especialidad no encontrada",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al modificar usuario: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private Set<String> convertirASet(String txt) {
		Set<String> resultado = new HashSet<>();

		if (txt == null || txt.trim().isEmpty()) {
			return resultado;
		}

		String[] elementos = txt.split(",");
		for (String elemento : elementos) {
			resultado.add(elemento.trim());
		}

		return resultado;
	}

}
