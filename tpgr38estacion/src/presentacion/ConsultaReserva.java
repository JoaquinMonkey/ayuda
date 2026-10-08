package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import logica.datatypes.DTReservaDet;
import logica.datatypes.DTReservaResumen;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ConsultaReserva extends JInternalFrame {
	private IControladorUsuario ICU;
	private IControladorConsulta ICO;
	private JComboBox<String> comboPacientes;
	private JComboBox<DTReservaResumen> comboReservas;

	private JTextField textFecha;
	private JTextField textNumero;
	private JTextField textCosto;
	private JTextField textMedico;
	private JTextField textClinica;
	private JTextField textConsulta;
	private JPanel panel;
	private JPanel panelInfo;

	public void cargarCombos() {
		comboPacientes.removeAllItems();
		comboPacientes.addItem("Click para seleccionar");
		for (String p : ICU.listarPacientes()) {
			comboPacientes.addItem(p);
		}
	}

	/**
	 * Create the frame.
	 */
	public ConsultaReserva(IControladorUsuario ICU, IControladorConsulta ICO) {
		this.ICU = ICU;
		this.ICO = ICO;
		setTitle("Consulta de Reserva");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		JLabel lbPaciente = new JLabel("Pacientes:");

		comboPacientes = new JComboBox<>();

		panel = new JPanel();
		panel.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(20).addComponent(lbPaciente)
										.addGap(4).addComponent(comboPacientes, GroupLayout.PREFERRED_SIZE, 316,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(28)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbPaciente))
						.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)));
		panel.setLayout(null);

		JLabel lblReservas = new JLabel("Reservas:");
		lblReservas.setBounds(23, 12, 70, 15);
		panel.add(lblReservas);

		comboReservas = new JComboBox<>();
		comboReservas.setBounds(99, 7, 316, 24);
		panel.add(comboReservas);

		panelInfo = new JPanel();
		panelInfo.setBounds(23, 39, 392, 172);
		panel.add(panelInfo);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 18, 70, 15);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);

		textFecha = new JTextField();
		textFecha.setBounds(92, 16, 114, 19);
		textFecha.setColumns(10);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(20, 41, 60, 15);
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);

		textNumero = new JTextField();
		textNumero.setBounds(92, 39, 114, 19);
		textNumero.setColumns(10);

		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(23, 66, 57, 15);
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);

		textCosto = new JTextField();
		textCosto.setBounds(92, 64, 114, 19);
		textCosto.setColumns(10);

		JLabel lblMedico = new JLabel("Medico:");
		lblMedico.setBounds(25, 141, 55, 15);

		textMedico = new JTextField();
		textMedico.setBounds(92, 139, 288, 19);
		textMedico.setColumns(10);

		JLabel lblClinica = new JLabel("Clinica:");
		lblClinica.setBounds(29, 91, 51, 15);

		textClinica = new JTextField();
		textClinica.setBounds(92, 89, 288, 19);
		textClinica.setColumns(10);

		JLabel lblConsulta = new JLabel("Consulta:");
		lblConsulta.setBounds(12, 116, 68, 15);
		lblConsulta.setHorizontalAlignment(SwingConstants.RIGHT);

		textConsulta = new JTextField();
		textConsulta.setBounds(92, 114, 288, 19);
		textConsulta.setColumns(10);
		panelInfo.setLayout(null);
		panelInfo.add(lblClinica);
		panelInfo.add(textClinica);
		panelInfo.add(lblConsulta);
		panelInfo.add(textConsulta);
		panelInfo.add(lblFecha);
		panelInfo.add(lblNumero);
		panelInfo.add(lblMedico);
		panelInfo.add(lblCosto);
		panelInfo.add(textNumero);
		panelInfo.add(textFecha);
		panelInfo.add(textMedico);
		panelInfo.add(textCosto);
		getContentPane().setLayout(groupLayout);

		panel.setVisible(false);
		panelInfo.setVisible(false);
		textFecha.setEditable(false);
		textNumero.setEditable(false);
		textCosto.setEditable(false);
		textMedico.setEditable(false);
		textClinica.setEditable(false);
		textConsulta.setEditable(false);

		comboPacientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String paciente = (String) comboPacientes.getSelectedItem();

				if (paciente == null || paciente.equals("Click para seleccionar")) {
					panel.setVisible(false);
					panelInfo.setVisible(false);
					return;
				}

				comboReservas.removeAllItems();
				DateTimeFormatter.ofPattern("dd/MM/yyyy");
				for (DTReservaResumen r : ICO.listarReservasPaciente(paciente)) {
					comboReservas.addItem(r);
				}
				panelInfo.setVisible(false);
				panel.setVisible(true);
			}
		});

		comboReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DTReservaResumen seleccionada = (DTReservaResumen) comboReservas.getSelectedItem();
				if (seleccionada == null) {
					panelInfo.setVisible(false);
					return;
				}
				DTReservaDet dt = ICO.consultarReserva(seleccionada.getConsulta(), seleccionada.getNumero());
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				textFecha.setText(dt.getReserva().getFecha().format(formato));
				textNumero.setText(String.valueOf(dt.getReserva().getNumero()));
				textCosto.setText(String.valueOf(dt.getReserva().getCosto()));
				textMedico.setText(dt.getMedico());
				textClinica.setText(dt.getClinica());
				textConsulta.setText(dt.getReserva().getConsulta());
				panelInfo.setVisible(true);
			}
		});

	}

	public void mostrarReserva(String consulta, int numero) {

		DTReservaDet dt = ICO.consultarReserva(consulta, numero);

		if (dt == null) {
			return;
		}

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		textFecha.setText(dt.getReserva().getFecha().format(formato));
		textNumero.setText(String.valueOf(dt.getReserva().getNumero()));
		textCosto.setText(String.valueOf(dt.getReserva().getCosto()));
		textMedico.setText(dt.getMedico());
		textClinica.setText(dt.getClinica());
		textConsulta.setText(dt.getReserva().getConsulta());

		panel.setVisible(true);
		panelInfo.setVisible(true);

		setVisible(true);
		toFront();
	}
}
