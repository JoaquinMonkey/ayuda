package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import logica.datatypes.DTConsulta;
import logica.interfaces.IControladorConsulta;

@SuppressWarnings("serial")
public class ConsultaConsulta extends JInternalFrame {
	private IControladorConsulta icc;
	private JComboBox<String> comboDia;
	private JComboBox<String> comboConsulta;
	private JTextField textNombre;
	private JTextField textMedico;
	private JTextField textFecha;
	private JTextField textSala;
	private JTextField textNumeros;
	private JTextField textCosto;
	private JList<String> listReservas;
	private DefaultListModel<String> modeloReservas;

	public ConsultaConsulta(IControladorConsulta icc, ConsultaReserva consultaReserva) {
		this.icc = icc;
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Consulta de Consulta");
		setBounds(100, 100, 500, 380);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 60 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblDia = new JLabel("Dia de consulta:");
		lblDia.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDia = new GridBagConstraints();
		gbc_lblDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDia.insets = new Insets(0, 0, 5, 5);
		gbc_lblDia.gridx = 0;
		gbc_lblDia.gridy = 0;
		getContentPane().add(lblDia, gbc_lblDia);

		comboDia = new JComboBox<>();
		GridBagConstraints gbc_comboDia = new GridBagConstraints();
		gbc_comboDia.gridwidth = 2;
		gbc_comboDia.fill = GridBagConstraints.BOTH;
		gbc_comboDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboDia.gridx = 1;
		gbc_comboDia.gridy = 0;
		getContentPane().add(comboDia, gbc_comboDia);

		comboDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarConsultasDelDia();
			}
		});

		JLabel lblConsulta = new JLabel("Consulta:");
		lblConsulta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblConsulta = new GridBagConstraints();
		gbc_lblConsulta.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblConsulta.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsulta.gridx = 0;
		gbc_lblConsulta.gridy = 1;
		getContentPane().add(lblConsulta, gbc_lblConsulta);

		comboConsulta = new JComboBox<>();
		GridBagConstraints gbc_comboConsulta = new GridBagConstraints();
		gbc_comboConsulta.gridwidth = 2;
		gbc_comboConsulta.fill = GridBagConstraints.BOTH;
		gbc_comboConsulta.insets = new Insets(0, 0, 5, 5);
		gbc_comboConsulta.gridx = 1;
		gbc_comboConsulta.gridy = 1;
		getContentPane().add(comboConsulta, gbc_comboConsulta);

		comboConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDetalle();
			}
		});

		textNombre = crearCampo("Nombre:", 2);
		textMedico = crearCampo("Medico:", 3);
		textFecha = crearCampo("Fecha:", 4);
		textSala = crearCampo("Numero de sala:", 5);
		textNumeros = crearCampo("Cantidad de numeros:", 6);
		textCosto = crearCampo("Costo:", 7);

		JLabel lblReservas = new JLabel("Reservas (nro):");
		lblReservas.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblReservas = new GridBagConstraints();
		gbc_lblReservas.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReservas.anchor = GridBagConstraints.NORTH;
		gbc_lblReservas.insets = new Insets(0, 0, 0, 5);
		gbc_lblReservas.gridx = 0;
		gbc_lblReservas.gridy = 8;
		getContentPane().add(lblReservas, gbc_lblReservas);

		modeloReservas = new DefaultListModel<>();
		listReservas = new JList<>(modeloReservas);
		JScrollPane scroll = new JScrollPane(listReservas);
		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.gridwidth = 2;
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.gridx = 1;
		gbc_scroll.gridy = 8;
		getContentPane().add(scroll, gbc_scroll);
		listReservas.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					String seleccionado = listReservas.getSelectedValue();

					if (seleccionado != null) {
						int numero = Integer.parseInt(seleccionado.replace("Reserva nro ", ""));
						consultaReserva.mostrarReserva(textNombre.getText(), numero);
					}
				}
			}
		});
	}

	private JTextField crearCampo(String etiqueta, int fila) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbl = new GridBagConstraints();
		gbc_lbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_lbl.gridx = 0;
		gbc_lbl.gridy = fila;
		getContentPane().add(lbl, gbc_lbl);

		JTextField campo = new JTextField();
		campo.setColumns(10);
		campo.setEditable(false);
		GridBagConstraints gbc_campo = new GridBagConstraints();
		gbc_campo.gridwidth = 2;
		gbc_campo.fill = GridBagConstraints.BOTH;
		gbc_campo.insets = new Insets(0, 0, 5, 5);
		gbc_campo.gridx = 1;
		gbc_campo.gridy = fila;
		getContentPane().add(campo, gbc_campo);
		return campo;
	}

	public void cargarDias() {
		comboDia.removeAllItems();
		for (String d : icc.listarDiasConsulta()) {
			comboDia.addItem(d);
		}
	}

	private void cargarConsultasDelDia() {
		comboConsulta.removeAllItems();
		String dia = (String) comboDia.getSelectedItem();
		if (dia == null) {
			return;
		}
		for (String c : icc.listarConsultas(dia)) {
			comboConsulta.addItem(c);
		}
	}

	private void mostrarDetalle() {
		String nombre = (String) comboConsulta.getSelectedItem();
		mostrarConsulta(nombre);
	}

	public void mostrarConsulta(String nombre) {
		limpiarDetalle();

		if (nombre == null) {
			return;
		}

		DTConsulta dt = icc.consultarConsulta(nombre);

		if (dt == null) {
			return;
		}

		textNombre.setText(dt.getNombre());
		textMedico.setText(dt.getMedico());
		textFecha.setText(dt.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		textSala.setText(String.valueOf(dt.getNroSala()));
		textNumeros.setText(String.valueOf(dt.getNrosDisp()));
		textCosto.setText(String.valueOf(dt.getCosto()));

		for (Integer num : dt.getReservas()) {
			modeloReservas.addElement("Reserva nro " + num);
		}

		setVisible(true);
		toFront();
	}

	private void limpiarDetalle() {
		textNombre.setText("");
		textMedico.setText("");
		textFecha.setText("");
		textSala.setText("");
		textNumeros.setText("");
		textCosto.setText("");
		modeloReservas.clear();
	}
}
