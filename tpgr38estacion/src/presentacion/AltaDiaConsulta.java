package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

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

import logica.interfaces.IControladorClinica;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorEspecialidad;

@SuppressWarnings("serial")
public class AltaDiaConsulta extends JInternalFrame {

	private IControladorConsulta icc;
	private IControladorEspecialidad ice;
	private IControladorClinica icl;

	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldHoraInicio;

	private JLabel lblIngreseNombre;
	private JLabel lblIngreseDescripcion;
	private JLabel lblIngreseDiaSemana;
	private JLabel lblIngreseHoraInicio;
	private JLabel lblIngreseEspecialidad;
	private JLabel lblIngreseClinica;

	private JComboBox<String> comboBoxDiaSemana;
	private JComboBox<String> comboBoxEspecialidades;
	private JComboBox<String> comboBoxClinicas;

	private JButton btnAceptar;
	private JButton btnCancelar;

	public AltaDiaConsulta(IControladorConsulta icc, IControladorEspecialidad ice, IControladorClinica icl) {
		this.icc = icc;
		this.ice = ice;
		this.icl = icl;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Día de Consulta");
		setBounds(10, 40, 450, 270);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gridBagLayout);

		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
		gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNombre.gridx = 0;
		gbc_lblIngreseNombre.gridy = 0;
		getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 0;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		lblIngreseDescripcion = new JLabel("Descripción:");
		lblIngreseDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseDescripcion = new GridBagConstraints();
		gbc_lblIngreseDescripcion.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseDescripcion.gridx = 0;
		gbc_lblIngreseDescripcion.gridy = 1;
		getContentPane().add(lblIngreseDescripcion, gbc_lblIngreseDescripcion);

		textFieldDescripcion = new JTextField();
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 2;
		gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.gridx = 1;
		gbc_textFieldDescripcion.gridy = 1;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblIngreseDiaSemana = new JLabel("Día de semana:");
		lblIngreseDiaSemana.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseDiaSemana = new GridBagConstraints();
		gbc_lblIngreseDiaSemana.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseDiaSemana.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseDiaSemana.gridx = 0;
		gbc_lblIngreseDiaSemana.gridy = 2;
		getContentPane().add(lblIngreseDiaSemana, gbc_lblIngreseDiaSemana);

		comboBoxDiaSemana = new JComboBox<>();
		comboBoxDiaSemana.setModel(new DefaultComboBoxModel<>(
				new String[] { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" }));
		comboBoxDiaSemana.setSelectedIndex(-1);

		GridBagConstraints gbc_comboBoxDiaSemana = new GridBagConstraints();
		gbc_comboBoxDiaSemana.gridwidth = 2;
		gbc_comboBoxDiaSemana.fill = GridBagConstraints.BOTH;
		gbc_comboBoxDiaSemana.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDiaSemana.gridx = 1;
		gbc_comboBoxDiaSemana.gridy = 2;
		getContentPane().add(comboBoxDiaSemana, gbc_comboBoxDiaSemana);

		lblIngreseHoraInicio = new JLabel("Hora de inicio:");
		lblIngreseHoraInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseHoraInicio = new GridBagConstraints();
		gbc_lblIngreseHoraInicio.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseHoraInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseHoraInicio.gridx = 0;
		gbc_lblIngreseHoraInicio.gridy = 3;
		getContentPane().add(lblIngreseHoraInicio, gbc_lblIngreseHoraInicio);

		textFieldHoraInicio = new JTextField();
		GridBagConstraints gbc_textFieldHoraInicio = new GridBagConstraints();
		gbc_textFieldHoraInicio.gridwidth = 2;
		gbc_textFieldHoraInicio.fill = GridBagConstraints.BOTH;
		gbc_textFieldHoraInicio.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHoraInicio.gridx = 1;
		gbc_textFieldHoraInicio.gridy = 3;
		getContentPane().add(textFieldHoraInicio, gbc_textFieldHoraInicio);
		textFieldHoraInicio.setColumns(10);

		lblIngreseEspecialidad = new JLabel("Especialidad:");
		lblIngreseEspecialidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseEspecialidad = new GridBagConstraints();
		gbc_lblIngreseEspecialidad.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseEspecialidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseEspecialidad.gridx = 0;
		gbc_lblIngreseEspecialidad.gridy = 4;
		getContentPane().add(lblIngreseEspecialidad, gbc_lblIngreseEspecialidad);

		comboBoxEspecialidades = new JComboBox<>();
		comboBoxEspecialidades.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxEspecialidades = new GridBagConstraints();
		gbc_comboBoxEspecialidades.gridwidth = 2;
		gbc_comboBoxEspecialidades.fill = GridBagConstraints.BOTH;
		gbc_comboBoxEspecialidades.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEspecialidades.gridx = 1;
		gbc_comboBoxEspecialidades.gridy = 4;
		getContentPane().add(comboBoxEspecialidades, gbc_comboBoxEspecialidades);

		lblIngreseClinica = new JLabel("Clínica:");
		lblIngreseClinica.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseClinica = new GridBagConstraints();
		gbc_lblIngreseClinica.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseClinica.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseClinica.gridx = 0;
		gbc_lblIngreseClinica.gridy = 5;
		getContentPane().add(lblIngreseClinica, gbc_lblIngreseClinica);

		comboBoxClinicas = new JComboBox<>();
		comboBoxClinicas.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxClinicas = new GridBagConstraints();
		gbc_comboBoxClinicas.gridwidth = 2;
		gbc_comboBoxClinicas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxClinicas.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxClinicas.gridx = 1;
		gbc_comboBoxClinicas.gridy = 5;
		getContentPane().add(comboBoxClinicas, gbc_comboBoxClinicas);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdAltaDiaConsultaActionPerformed(e);
			}
		});

		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 6;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	public void cargarDatos() {
		DefaultComboBoxModel<String> modelEspecialidades = new DefaultComboBoxModel<>();
		modelEspecialidades = new DefaultComboBoxModel<>(ice.listarEspecialidades().toArray(String[]::new));
		comboBoxEspecialidades.setModel(modelEspecialidades);
		comboBoxEspecialidades.setSelectedIndex(-1);

		DefaultComboBoxModel<String> modelClinicas = new DefaultComboBoxModel<>();
		modelClinicas = new DefaultComboBoxModel<>(icl.listarClinicas().toArray(String[]::new));
		comboBoxClinicas.setModel(modelClinicas);
		comboBoxClinicas.setSelectedIndex(-1);
	}

	protected void cmdAltaDiaConsultaActionPerformed(ActionEvent arg0) {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String hora = this.textFieldHoraInicio.getText();
		String especialidad = (String) this.comboBoxEspecialidades.getSelectedItem();
		String clinica = (String) this.comboBoxClinicas.getSelectedItem();

		if (checkFormulario()) {
			if (!icc.esValidoNombreDiaDeConsulta(nombre)) {
				JOptionPane.showMessageDialog(this, "Ya existe un día de consulta con ese nombre",
						"Alta de Día de Consulta", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int diaSemana = comboBoxDiaSemana.getSelectedIndex() + 1;
			LocalTime horaInicio = LocalTime.parse(hora);

			icc.registrarDiaDeConsulta(nombre, descripcion, diaSemana, horaInicio, especialidad, clinica);
			JOptionPane.showMessageDialog(this, "El día de consulta se ha creado con éxito", "Alta de Día de Consulta",
					JOptionPane.INFORMATION_MESSAGE);

			limpiarFormulario();
			setVisible(false);
		}
	}

	private boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String hora = this.textFieldHoraInicio.getText();
		String especialidad = (String) this.comboBoxEspecialidades.getSelectedItem();
		String clinica = (String) this.comboBoxClinicas.getSelectedItem();

		if (nombre.isEmpty() || descripcion.isEmpty() || hora.isEmpty() || especialidad == null || clinica == null
				|| comboBoxDiaSemana.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta de Día de Consulta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			LocalTime.parse(hora);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "La hora debe tener formato HH:MM", "Alta de Día de Consulta",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldHoraInicio.setText("");
		comboBoxDiaSemana.setSelectedIndex(-1);
		comboBoxEspecialidades.setSelectedIndex(-1);
		comboBoxClinicas.setSelectedIndex(-1);
	}
}