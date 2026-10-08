package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import excepciones.PacienteYaReservadoException;
import excepciones.ReservaFueraDeFechaException;
import excepciones.SinCupoException;
import logica.datatypes.DTReserva;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ReservaConsulta extends JInternalFrame {

	private IControladorUsuario ICU;
	private IControladorConsulta ICC;
	private JComboBox<String> comboDias;
	private JComboBox<String> comboConsultas;
	private JComboBox<String> comboPacientes;

	/**
	 * Create the frame.
	 */
	public ReservaConsulta(IControladorUsuario ICU, IControladorConsulta ICC) {
		this.ICU = ICU;
		this.ICC = ICC;
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 300);
		setTitle("Reserva de Consulta");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		JLabel lblDias = new JLabel("Dias de Consulta:");
		lblDias.setHorizontalAlignment(SwingConstants.RIGHT);

		comboDias = new JComboBox<>();

		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setVisible(false);

		JButton btnReservar = new JButton("Reservar");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboDias.setSelectedIndex(-1);
				comboPacientes.setSelectedIndex(-1);
				panelSeleccion.setVisible(false);
				setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblDias).addGap(3)
										.addComponent(comboDias, 0, 290, Short.MAX_VALUE).addContainerGap())
								.addGroup(Alignment.TRAILING,
										groupLayout.createSequentialGroup().addComponent(btnReservar).addGap(18)
												.addComponent(btnCancelar).addGap(11))))
				.addComponent(panelSeleccion, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblDias).addComponent(
						comboDias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(40).addComponent(panelSeleccion, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnReservar).addComponent(btnCancelar))
				.addContainerGap()));

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(69, 56, 67, 15);
		lblPaciente.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblConsulta = new JLabel("Consulta:");
		lblConsulta.setBounds(57, 26, 79, 15);
		lblConsulta.setHorizontalAlignment(SwingConstants.RIGHT);

		comboConsultas = new JComboBox<>();
		comboConsultas.setBounds(140, 21, 290, 24);

		comboPacientes = new JComboBox<>();
		comboPacientes.setBounds(140, 51, 290, 24);
		panelSeleccion.setLayout(null);
		panelSeleccion.add(lblPaciente);
		panelSeleccion.add(lblConsulta);
		panelSeleccion.add(comboConsultas);
		panelSeleccion.add(comboPacientes);
		getContentPane().setLayout(groupLayout);

		comboDias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dia = (String) comboDias.getSelectedItem();
				if (dia == null || dia.equals("Click para seleccionar")) {
					panelSeleccion.setVisible(false);
					return;
				}
				comboConsultas.removeAllItems();
				comboConsultas.addItem("Click para seleccionar");
				for (String c : ICC.consultarDiaConsulta(dia)) {
					comboConsultas.addItem(c);
				}
				panelSeleccion.setVisible(true);
			}

		});

		btnReservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String consulta = (String) comboConsultas.getSelectedItem();
				String paciente = (String) comboPacientes.getSelectedItem();
				if (consulta == null || paciente == null || consulta.equals("Click para seleccionar")
						|| paciente.equals("Click para seleccionar")) {
					JOptionPane.showMessageDialog(ReservaConsulta.this, "Debe seleccionar una consulta y un paciente",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				DTReserva dt = null;

				try {
					dt = ICC.reservarConsultaPaciente(paciente, consulta);
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String fechatxt = dt.getFecha().format(formato);
					JOptionPane.showMessageDialog(ReservaConsulta.this,
							"Reserva exitosa. \n" + "Numero asignado: " + dt.getNumero() + "\n" + "Fecha: " + fechatxt
									+ "\n" + "Costo: $" + dt.getCosto(),
							"Alta De Reserva", JOptionPane.INFORMATION_MESSAGE);
					comboDias.setSelectedIndex(-1);
					panelSeleccion.setVisible(false);
					setVisible(false);
				} catch (SinCupoException ex) {
					JOptionPane.showMessageDialog(ReservaConsulta.this, ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);

				} catch (PacienteYaReservadoException exx) {
					JOptionPane.showMessageDialog(ReservaConsulta.this, exx.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (ReservaFueraDeFechaException e1) {
					JOptionPane.showMessageDialog(ReservaConsulta.this, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void cargarCombos() {
		comboDias.removeAllItems();
		comboPacientes.removeAllItems();
		comboDias.addItem("Click para seleccionar");
		for (String dia : ICC.listarDiasConsulta()) {
			comboDias.addItem(dia);
		}
		comboPacientes.addItem("Click para seleccionar");
		for (String p : ICU.listarPacientes()) {
			comboPacientes.addItem(p);
		}
	}

}
