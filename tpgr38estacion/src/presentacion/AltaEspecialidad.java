package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import excepciones.NombreEspecialidadRepetidoException;
import logica.interfaces.IControladorEspecialidad;

@SuppressWarnings("serial")
public class AltaEspecialidad extends JInternalFrame {
	private IControladorEspecialidad ice;
	private JTextField textFieldNombre;
	private JTextField textFieldPorcentaje;
	private JLabel lblIngreseNombre;
	private JLabel lblIngresePorcentaje;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public AltaEspecialidad(IControladorEspecialidad ice) {
		this.ice = ice;
		setTitle("Alta de Especialidad");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(10, 40, 425, 135);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		contentPane.setLayout(gridBagLayout);

		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
		gbc_lblIngreseNombre.anchor = GridBagConstraints.EAST;
		gbc_lblIngreseNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNombre.gridx = 0;
		gbc_lblIngreseNombre.gridy = 0;
		getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 0;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		lblIngresePorcentaje = new JLabel("Porcentaje:");
		lblIngresePorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngresePorcentaje = new GridBagConstraints();
		gbc_lblIngresePorcentaje.anchor = GridBagConstraints.EAST;
		gbc_lblIngresePorcentaje.fill = GridBagConstraints.VERTICAL;
		gbc_lblIngresePorcentaje.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresePorcentaje.gridx = 0;
		gbc_lblIngresePorcentaje.gridy = 1;
		getContentPane().add(lblIngresePorcentaje, gbc_lblIngresePorcentaje);

		textFieldPorcentaje = new JTextField();
		textFieldPorcentaje.setColumns(10);
		GridBagConstraints gbc_textFieldPorcentaje = new GridBagConstraints();
		gbc_textFieldPorcentaje.gridwidth = 2;
		gbc_textFieldPorcentaje.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPorcentaje.fill = GridBagConstraints.BOTH;
		gbc_textFieldPorcentaje.gridx = 1;
		gbc_textFieldPorcentaje.gridy = 1;
		getContentPane().add(textFieldPorcentaje, gbc_textFieldPorcentaje);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdAltaEspecialidadActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 2;
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
		gbc_btnCancelar.gridy = 2;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	protected void cmdAltaEspecialidadActionPerformed(ActionEvent arg0) {
		String nombre = this.textFieldNombre.getText();
		String porcentaje = this.textFieldPorcentaje.getText();

		if (checkFormulario()) {
			try {
				float porcentaje_ad = Float.parseFloat(porcentaje);
				ice.altaEspecialidad(nombre, porcentaje_ad);

				JOptionPane.showMessageDialog(this, "La especialidad se ha creado con éxito", "Alta de Especialidad",
						JOptionPane.INFORMATION_MESSAGE);

				limpiarFormulario();
				setVisible(false);
			} catch (NombreEspecialidadRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Especialidad", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
		String porcentaje = this.textFieldPorcentaje.getText();

		if (nombre.isEmpty() || porcentaje.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Especialidad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		float porcentaje_ad = 0;
		try {
			porcentaje_ad = Float.parseFloat(porcentaje);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El porcentaje debe ser un numero", "Alta de Especialidad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (porcentaje_ad < 0) {
			JOptionPane.showMessageDialog(this, "El porcentaje no puede ser negativo", "Alta de Especialidad",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldPorcentaje.setText("");
	}

}
