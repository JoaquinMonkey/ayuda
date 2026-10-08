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

import excepciones.AnioBeneficioAnualRepetidoException;
import logica.interfaces.IControladorBeneficioAnual;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class AltaBeneficioAnual extends JInternalFrame {
	private IControladorBeneficioAnual icba;
	private IControladorUsuario icu;
	private JTextField textFieldCantMed;
	private JTextField textFieldAnio;
	private JTextField textFieldCantOrd;
	private JLabel lblIngreseCantMed;
	private JLabel lblIngreseAnio;
	private JLabel lblIngreseCantOrd;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblIngresePaciente;
	private JComboBox<String> comboBoxPacientes;

	public AltaBeneficioAnual(IControladorBeneficioAnual icba, IControladorUsuario icu) {
		this.icba = icba;
		this.icu = icu;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de Beneficio Anual");
		setBounds(10, 40, 450, 190);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
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
		gbc_lblIngreseAnio.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseAnio.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseAnio.gridx = 0;
		gbc_lblIngreseAnio.gridy = 1;
		getContentPane().add(lblIngreseAnio, gbc_lblIngreseAnio);

		textFieldAnio = new JTextField();
		GridBagConstraints gbc_textFieldAnio = new GridBagConstraints();
		gbc_textFieldAnio.gridwidth = 2;
		gbc_textFieldAnio.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnio.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAnio.gridx = 1;
		gbc_textFieldAnio.gridy = 1;
		getContentPane().add(textFieldAnio, gbc_textFieldAnio);
		textFieldAnio.setColumns(10);

		lblIngreseCantOrd = new JLabel("Consultas:");
		lblIngreseCantOrd.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseCantOrd = new GridBagConstraints();
		gbc_lblIngreseCantOrd.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseCantOrd.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseCantOrd.gridx = 0;
		gbc_lblIngreseCantOrd.gridy = 2;
		getContentPane().add(lblIngreseCantOrd, gbc_lblIngreseCantOrd);

		textFieldCantOrd = new JTextField();
		GridBagConstraints gbc_textFieldCantOrd = new GridBagConstraints();
		gbc_textFieldCantOrd.gridwidth = 2;
		gbc_textFieldCantOrd.fill = GridBagConstraints.BOTH;
		gbc_textFieldCantOrd.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCantOrd.gridx = 1;
		gbc_textFieldCantOrd.gridy = 2;
		getContentPane().add(textFieldCantOrd, gbc_textFieldCantOrd);
		textFieldCantOrd.setColumns(10);

		lblIngreseCantMed = new JLabel("Medicamentos:");
		lblIngreseCantMed.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseCantMed = new GridBagConstraints();
		gbc_lblIngreseCantMed.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseCantMed.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseCantMed.gridx = 0;
		gbc_lblIngreseCantMed.gridy = 3;
		getContentPane().add(lblIngreseCantMed, gbc_lblIngreseCantMed);

		textFieldCantMed = new JTextField();
		GridBagConstraints gbc_textFieldCantMed = new GridBagConstraints();
		gbc_textFieldCantMed.gridwidth = 2;
		gbc_textFieldCantMed.fill = GridBagConstraints.BOTH;
		gbc_textFieldCantMed.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCantMed.gridx = 1;
		gbc_textFieldCantMed.gridy = 3;
		getContentPane().add(textFieldCantMed, gbc_textFieldCantMed);
		textFieldCantMed.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdAltaBeneficioAnualActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 4;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	public void cargarPacientes() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model = new DefaultComboBoxModel<>(icu.listarPacientes().toArray(String[]::new));
		comboBoxPacientes.setModel(model);
	}

	protected void cmdAltaBeneficioAnualActionPerformed(ActionEvent arg0) {
		String sAnio = this.textFieldAnio.getText();
		String sCantOrd = this.textFieldCantOrd.getText();
		String sCantMed = this.textFieldCantMed.getText();
		String nickname = (String) this.comboBoxPacientes.getSelectedItem();

		if (checkFormulario()) {
			try {
				int anio = Integer.parseInt(sAnio);
				int cantMed = Integer.parseInt(sCantMed);
				int cantOrd = Integer.parseInt(sCantOrd);
				icba.altaBeneficioAnual(nickname, anio, cantMed, cantOrd);

				JOptionPane.showMessageDialog(this, "El beneficio anual se ha creado con éxito",
						"Alta de Beneficio Anual", JOptionPane.INFORMATION_MESSAGE);

				limpiarFormulario();
				setVisible(false);
			} catch (AnioBeneficioAnualRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Beneficio Anual",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private boolean checkFormulario() {
		String sAnio = this.textFieldAnio.getText();
		String sCantOrd = this.textFieldCantOrd.getText();
		String sCantMed = this.textFieldCantMed.getText();
		String nickname = (String) this.comboBoxPacientes.getSelectedItem();

		if (nickname.isEmpty() || sAnio.isEmpty() || sCantOrd.isEmpty() || sCantMed.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Beneficio Anual",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		int anio = 0;
		int cantOrd = 0;
		int cantMed = 0;
		try {
			anio = Integer.parseInt(sAnio);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El Año debe ser un numero", "Alta de Beneficio Anual",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			cantOrd = Integer.parseInt(sCantOrd);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de ordenes debe ser un numero", "Alta de Beneficio Anual",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			cantMed = Integer.parseInt(sCantMed);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de tickets de medicamentos debe ser un numero",
					"Alta de Beneficio Anual", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (anio < 0) {
			JOptionPane.showMessageDialog(this, "El Año debe ser un numero positivo", "Alta de Beneficio Anual",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cantOrd < 0) {
			JOptionPane.showMessageDialog(this, "La cantidad de ordenes debe ser un numero positivo",
					"Alta de Beneficio Anual", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (cantMed < 0) {
			JOptionPane.showMessageDialog(this, "La cantidad de tickets de medicamentos debe ser un numero positivo",
					"Alta de Beneficio Anual", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void limpiarFormulario() {
		comboBoxPacientes.setSelectedIndex(-1);
		textFieldAnio.setText("");
		textFieldCantOrd.setText("");
		textFieldCantMed.setText("");
	}

}