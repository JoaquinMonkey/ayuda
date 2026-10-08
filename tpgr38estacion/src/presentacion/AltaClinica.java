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

import excepciones.NombreClinicaRepetidoException;
import logica.interfaces.IControladorClinica;

@SuppressWarnings("serial")
public class AltaClinica extends JInternalFrame {
	private IControladorClinica icc;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDireccion;
	private JTextField textFieldWeb;
	private JTextField textFieldCostoTicket;
	private JTextField textFieldCostoOrden;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblDireccion;
	private JLabel lblWeb;
	private JLabel lblCostoTicket;
	private JLabel lblCostoOrden;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public AltaClinica(IControladorClinica icc) {
		this.icc = icc;
		setTitle("Alta de Clinica");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(10, 40, 500, 250);
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

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 0;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 1;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 2;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcion.gridx = 1;
		gbc_textFieldDescripcion.gridy = 1;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.EAST;
		gbc_lblDireccion.fill = GridBagConstraints.VERTICAL;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 2;
		getContentPane().add(lblDireccion, gbc_lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();
		gbc_textFieldDireccion.gridwidth = 2;
		gbc_textFieldDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDireccion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDireccion.gridx = 1;
		gbc_textFieldDireccion.gridy = 2;
		getContentPane().add(textFieldDireccion, gbc_textFieldDireccion);

		lblWeb = new JLabel("Sitio web (opcional):");
		lblWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblWeb = new GridBagConstraints();
		gbc_lblWeb.anchor = GridBagConstraints.EAST;
		gbc_lblWeb.fill = GridBagConstraints.VERTICAL;
		gbc_lblWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeb.gridx = 0;
		gbc_lblWeb.gridy = 3;
		getContentPane().add(lblWeb, gbc_lblWeb);

		textFieldWeb = new JTextField();
		textFieldWeb.setColumns(10);
		GridBagConstraints gbc_textFieldWeb = new GridBagConstraints();
		gbc_textFieldWeb.gridwidth = 2;
		gbc_textFieldWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldWeb.fill = GridBagConstraints.BOTH;
		gbc_textFieldWeb.gridx = 1;
		gbc_textFieldWeb.gridy = 3;
		getContentPane().add(textFieldWeb, gbc_textFieldWeb);

		lblCostoTicket = new JLabel("Costo ticket medicamento:");
		lblCostoTicket.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCostoTicket = new GridBagConstraints();
		gbc_lblCostoTicket.anchor = GridBagConstraints.EAST;
		gbc_lblCostoTicket.fill = GridBagConstraints.VERTICAL;
		gbc_lblCostoTicket.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoTicket.gridx = 0;
		gbc_lblCostoTicket.gridy = 4;
		getContentPane().add(lblCostoTicket, gbc_lblCostoTicket);

		textFieldCostoTicket = new JTextField();
		textFieldCostoTicket.setColumns(10);
		GridBagConstraints gbc_textFieldCostoTicket = new GridBagConstraints();
		gbc_textFieldCostoTicket.gridwidth = 2;
		gbc_textFieldCostoTicket.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCostoTicket.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoTicket.gridx = 1;
		gbc_textFieldCostoTicket.gridy = 4;
		getContentPane().add(textFieldCostoTicket, gbc_textFieldCostoTicket);

		lblCostoOrden = new JLabel("Costo orden consulta:");
		lblCostoOrden.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCostoOrden = new GridBagConstraints();
		gbc_lblCostoOrden.anchor = GridBagConstraints.EAST;
		gbc_lblCostoOrden.fill = GridBagConstraints.VERTICAL;
		gbc_lblCostoOrden.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoOrden.gridx = 0;
		gbc_lblCostoOrden.gridy = 5;
		getContentPane().add(lblCostoOrden, gbc_lblCostoOrden);

		textFieldCostoOrden = new JTextField();
		textFieldCostoOrden.setColumns(10);
		GridBagConstraints gbc_textFieldCostoOrden = new GridBagConstraints();
		gbc_textFieldCostoOrden.gridwidth = 2;
		gbc_textFieldCostoOrden.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCostoOrden.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoOrden.gridx = 1;
		gbc_textFieldCostoOrden.gridy = 5;
		getContentPane().add(textFieldCostoOrden, gbc_textFieldCostoOrden);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmdAltaClinicaActionPerformed(e);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
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
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	protected void cmdAltaClinicaActionPerformed(ActionEvent arg0) {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String direccion = this.textFieldDireccion.getText();
		String web = this.textFieldWeb.getText();

		if (checkFormulario()) {
			try {
				float costoTicket = Float.parseFloat(this.textFieldCostoTicket.getText());
				float costoOrden = Float.parseFloat(this.textFieldCostoOrden.getText());
				icc.altaClinica(nombre, descripcion, direccion, web, costoTicket, costoOrden);

				JOptionPane.showMessageDialog(this, "La clinica se ha creado con exito", "Alta de Clinica",
						JOptionPane.INFORMATION_MESSAGE);

				limpiarFormulario();
				setVisible(false);
			} catch (NombreClinicaRepetidoException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Clinica", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String direccion = this.textFieldDireccion.getText();
		String costoTicket = this.textFieldCostoTicket.getText();
		String costoOrden = this.textFieldCostoOrden.getText();

		if (nombre.isEmpty() || descripcion.isEmpty() || direccion.isEmpty() || costoTicket.isEmpty()
				|| costoOrden.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Solo el sitio web puede quedar vacio", "Alta de Clinica",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		float ticket = 0;
		float orden = 0;
		try {
			ticket = Float.parseFloat(costoTicket);
			orden = Float.parseFloat(costoOrden);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Los costos deben ser numeros", "Alta de Clinica",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (ticket < 0 || orden < 0) {
			JOptionPane.showMessageDialog(this, "Los costos no pueden ser negativos", "Alta de Clinica",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldDireccion.setText("");
		textFieldWeb.setText("");
		textFieldCostoTicket.setText("");
		textFieldCostoOrden.setText("");
	}

}
