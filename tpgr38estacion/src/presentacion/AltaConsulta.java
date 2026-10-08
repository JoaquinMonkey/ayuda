package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

import excepciones.FechaNoCoincideDiaException;
import excepciones.MedicoSinEspecialidadException;
import excepciones.NombreConsultaRepetidoException;
import logica.interfaces.IControladorConsulta;

@SuppressWarnings("serial")
public class AltaConsulta extends JInternalFrame {
	private IControladorConsulta icc;
	private JComboBox<String> comboDia;
	private JComboBox<String> comboMedico;
	private JTextField textFieldNombre;
	private JTextField textFieldFecha;
	private JTextField textFieldSala;
	private JTextField textFieldNumeros;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public AltaConsulta(IControladorConsulta icc) {
		this.icc = icc;
		setTitle("Alta de Consulta");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(10, 40, 500, 280);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gridBagLayout);

		JLabel lblDia = new JLabel("Dia de consulta:");
		lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDia = new GridBagConstraints();
		gbc_lblDia.anchor = GridBagConstraints.EAST;
		gbc_lblDia.fill = GridBagConstraints.VERTICAL;
		gbc_lblDia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDia.gridx = 0;
		gbc_lblDia.gridy = 0;
		contentPane.add(lblDia, gbc_lblDia);

		comboDia = new JComboBox<>();
		GridBagConstraints gbc_comboDia = new GridBagConstraints();
		gbc_comboDia.gridwidth = 2;
		gbc_comboDia.insets = new Insets(0, 0, 5, 0);
		gbc_comboDia.fill = GridBagConstraints.BOTH;
		gbc_comboDia.gridx = 1;
		gbc_comboDia.gridy = 0;
		contentPane.add(comboDia, gbc_comboDia);

		JLabel lblMedico = new JLabel("Medico:");
		lblMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.anchor = GridBagConstraints.EAST;
		gbc_lblMedico.fill = GridBagConstraints.VERTICAL;
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.gridx = 0;
		gbc_lblMedico.gridy = 1;
		contentPane.add(lblMedico, gbc_lblMedico);

		comboMedico = new JComboBox<>();
		GridBagConstraints gbc_comboMedico = new GridBagConstraints();
		gbc_comboMedico.gridwidth = 2;
		gbc_comboMedico.insets = new Insets(0, 0, 5, 0);
		gbc_comboMedico.fill = GridBagConstraints.BOTH;
		gbc_comboMedico.gridx = 1;
		gbc_comboMedico.gridy = 1;
		contentPane.add(comboMedico, gbc_comboMedico);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		contentPane.add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 2;
		contentPane.add(textFieldNombre, gbc_textFieldNombre);

		JLabel lblFecha = new JLabel("Fecha (dd/mm/aaaa):");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.fill = GridBagConstraints.VERTICAL;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 3;
		contentPane.add(lblFecha, gbc_lblFecha);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.gridwidth = 2;
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFecha.fill = GridBagConstraints.BOTH;
		gbc_textFieldFecha.gridx = 1;
		gbc_textFieldFecha.gridy = 3;
		contentPane.add(textFieldFecha, gbc_textFieldFecha);

		JLabel lblSala = new JLabel("Numero de sala:");
		lblSala.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblSala = new GridBagConstraints();
		gbc_lblSala.anchor = GridBagConstraints.EAST;
		gbc_lblSala.fill = GridBagConstraints.VERTICAL;
		gbc_lblSala.insets = new Insets(0, 0, 5, 5);
		gbc_lblSala.gridx = 0;
		gbc_lblSala.gridy = 4;
		contentPane.add(lblSala, gbc_lblSala);

		textFieldSala = new JTextField();
		textFieldSala.setColumns(10);
		GridBagConstraints gbc_textFieldSala = new GridBagConstraints();
		gbc_textFieldSala.gridwidth = 2;
		gbc_textFieldSala.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSala.fill = GridBagConstraints.BOTH;
		gbc_textFieldSala.gridx = 1;
		gbc_textFieldSala.gridy = 4;
		contentPane.add(textFieldSala, gbc_textFieldSala);

		JLabel lblNumeros = new JLabel("Cantidad de numeros:");
		lblNumeros.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNumeros = new GridBagConstraints();
		gbc_lblNumeros.anchor = GridBagConstraints.EAST;
		gbc_lblNumeros.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumeros.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumeros.gridx = 0;
		gbc_lblNumeros.gridy = 5;
		contentPane.add(lblNumeros, gbc_lblNumeros);

		textFieldNumeros = new JTextField();
		textFieldNumeros.setColumns(10);
		GridBagConstraints gbc_textFieldNumeros = new GridBagConstraints();
		gbc_textFieldNumeros.gridwidth = 2;
		gbc_textFieldNumeros.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNumeros.fill = GridBagConstraints.BOTH;
		gbc_textFieldNumeros.gridx = 1;
		gbc_textFieldNumeros.gridy = 5;
		contentPane.add(textFieldNumeros, gbc_textFieldNumeros);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdAltaConsultaActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 6;
		contentPane.add(btnAceptar, gbc_btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 6;
		contentPane.add(btnCancelar, gbc_btnCancelar);
	}

	public void cargarCombos() {
		comboDia.removeAllItems();
		for (String d : icc.listarDiasConsulta()) {
			comboDia.addItem(d);
		}
		comboMedico.removeAllItems();
		for (String m : icc.listarMedicos()) {
			comboMedico.addItem(m);
		}
	}

	protected void cmdAltaConsultaActionPerformed(ActionEvent arg0) {
		String dia = (String) comboDia.getSelectedItem();
		String medico = (String) comboMedico.getSelectedItem();
		String nombre = this.textFieldNombre.getText();

		if (dia == null || medico == null) {
			JOptionPane.showMessageDialog(this, "Debe existir al menos un dia de consulta y un medico.",
					"Alta de Consulta", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (nombre.isEmpty() || textFieldFecha.getText().isEmpty() || textFieldSala.getText().isEmpty()
				|| textFieldNumeros.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Alta de Consulta",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		LocalDate fecha;
		try {
			fecha = LocalDate.parse(this.textFieldFecha.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/mm/aaaa.", "Alta de Consulta",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		int nroSala;
		int cantNumeros;
		try {
			nroSala = Integer.parseInt(this.textFieldSala.getText().trim());
			cantNumeros = Integer.parseInt(this.textFieldNumeros.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El numero de sala y la cantidad de numeros deben ser enteros.",
					"Alta de Consulta", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (nroSala < 0 || cantNumeros < 0) {
			JOptionPane.showMessageDialog(this, "El numero de sala y la cantidad de numeros no pueden ser negativos.",
					"Alta de Consulta", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			icc.altaConsulta(dia, medico, nombre, fecha, nroSala, cantNumeros);
			JOptionPane.showMessageDialog(this, "La consulta se ha creado con exito.", "Alta de Consulta",
					JOptionPane.INFORMATION_MESSAGE);
			limpiarFormulario();
			setVisible(false);
		} catch (NombreConsultaRepetidoException | MedicoSinEspecialidadException | FechaNoCoincideDiaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Consulta", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldFecha.setText("");
		textFieldSala.setText("");
		textFieldNumeros.setText("");
	}
}
