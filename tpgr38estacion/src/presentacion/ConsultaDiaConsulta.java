package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import excepciones.DiaConsultaNoExisteException;
import logica.datatypes.DTDia_consulta;
import logica.interfaces.IControladorConsulta;

@SuppressWarnings("serial")
public class ConsultaDiaConsulta extends JInternalFrame {

	private IControladorConsulta icc;
	private JComboBox<String> comboBoxDias;

	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDiaSemana;
	private JTextField textFieldHoraInicio;
	private JTextField textFieldCosto;

	private JList<String> listConsultas;

	public ConsultaDiaConsulta(IControladorConsulta icc, ConsultaConsulta consultaConsulta) {

		this.icc = icc;
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);

		setTitle("Consulta de Día de Consulta");
		setBounds(100, 100, 500, 430);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130, 250, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 120, 30 };

		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

		getContentPane().setLayout(gridBagLayout);

		JLabel lblDiaConsulta = new JLabel("Día de consulta:");

		GridBagConstraints gbc_lblDiaConsulta = new GridBagConstraints();
		gbc_lblDiaConsulta.anchor = GridBagConstraints.EAST;
		gbc_lblDiaConsulta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiaConsulta.gridx = 0;
		gbc_lblDiaConsulta.gridy = 0;

		getContentPane().add(lblDiaConsulta, gbc_lblDiaConsulta);

		comboBoxDias = new JComboBox<>();

		comboBoxDias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarDia();
				} catch (DiaConsultaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		GridBagConstraints gbc_comboBoxDias = new GridBagConstraints();
		gbc_comboBoxDias.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDias.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDias.gridx = 1;
		gbc_comboBoxDias.gridy = 0;

		getContentPane().add(comboBoxDias, gbc_comboBoxDias);

		JLabel lblNombre = new JLabel("Nombre:");

		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;

		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);

		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;

		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción:");

		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 2;

		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);

		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcion.gridx = 1;
		gbc_textFieldDescripcion.gridy = 2;

		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		JLabel lblDiaSemana = new JLabel("Día de semana:");

		GridBagConstraints gbc_lblDiaSemana = new GridBagConstraints();
		gbc_lblDiaSemana.anchor = GridBagConstraints.EAST;
		gbc_lblDiaSemana.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiaSemana.gridx = 0;
		gbc_lblDiaSemana.gridy = 3;

		getContentPane().add(lblDiaSemana, gbc_lblDiaSemana);

		textFieldDiaSemana = new JTextField();
		textFieldDiaSemana.setEditable(false);

		GridBagConstraints gbc_textFieldDiaSemana = new GridBagConstraints();
		gbc_textFieldDiaSemana.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDiaSemana.fill = GridBagConstraints.BOTH;
		gbc_textFieldDiaSemana.gridx = 1;
		gbc_textFieldDiaSemana.gridy = 3;

		getContentPane().add(textFieldDiaSemana, gbc_textFieldDiaSemana);
		textFieldDiaSemana.setColumns(10);

		JLabel lblHoraInicio = new JLabel("Hora de inicio:");

		GridBagConstraints gbc_lblHoraInicio = new GridBagConstraints();
		gbc_lblHoraInicio.anchor = GridBagConstraints.EAST;
		gbc_lblHoraInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraInicio.gridx = 0;
		gbc_lblHoraInicio.gridy = 4;

		getContentPane().add(lblHoraInicio, gbc_lblHoraInicio);

		textFieldHoraInicio = new JTextField();
		textFieldHoraInicio.setEditable(false);

		GridBagConstraints gbc_textFieldHoraInicio = new GridBagConstraints();
		gbc_textFieldHoraInicio.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHoraInicio.fill = GridBagConstraints.BOTH;
		gbc_textFieldHoraInicio.gridx = 1;
		gbc_textFieldHoraInicio.gridy = 4;

		getContentPane().add(textFieldHoraInicio, gbc_textFieldHoraInicio);
		textFieldHoraInicio.setColumns(10);

		JLabel lblCosto = new JLabel("Costo:");

		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.anchor = GridBagConstraints.EAST;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 5;

		getContentPane().add(lblCosto, gbc_lblCosto);

		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);

		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
		gbc_textFieldCosto.gridx = 1;
		gbc_textFieldCosto.gridy = 5;

		getContentPane().add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);

		JLabel lblConsultas = new JLabel("Consultas:");

		GridBagConstraints gbc_lblConsultas = new GridBagConstraints();
		gbc_lblConsultas.anchor = GridBagConstraints.EAST;
		gbc_lblConsultas.insets = new Insets(0, 0, 5, 5);
		gbc_lblConsultas.gridx = 0;
		gbc_lblConsultas.gridy = 6;

		getContentPane().add(lblConsultas, gbc_lblConsultas);

		JScrollPane scrollPane = new JScrollPane();

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		gbc_scrollPane.gridheight = 2;

		getContentPane().add(scrollPane, gbc_scrollPane);

		listConsultas = new JList<>();
		scrollPane.setViewportView(listConsultas);
		listConsultas.addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {

				String nombreConsulta = listConsultas.getSelectedValue();

				if (nombreConsulta != null) {
					consultaConsulta.mostrarConsulta(nombreConsulta);
				}
			}
		});
	}

	public void cargarDias() {

		comboBoxDias.removeAllItems();

		for (String nombre : icc.listarDiasConsulta()) {
			comboBoxDias.addItem(nombre);
		}

		comboBoxDias.setSelectedIndex(-1);

		limpiarDatos();
	}

	private String obtenerNombreDia(int dia) {

		switch (dia) {
		case 1:
			return "Lunes";
		case 2:
			return "Martes";
		case 3:
			return "Miércoles";
		case 4:
			return "Jueves";
		case 5:
			return "Viernes";
		case 6:
			return "Sábado";
		case 7:
			return "Domingo";
		default:
			return "";
		}
	}

	private void seleccionarDia() throws DiaConsultaNoExisteException {

		String nombre = (String) comboBoxDias.getSelectedItem();

		if (nombre == null) {
			return;
		}

		DTDia_consulta dt = icc.seleccionarDiaDeConsulta(nombre);

		textFieldNombre.setText(dt.getNombre());
		textFieldDescripcion.setText(dt.getDescripcion());
		textFieldDiaSemana.setText(obtenerNombreDia(dt.getDiaSemana()));
		textFieldHoraInicio.setText(dt.getHoraInicio().toString());
		textFieldCosto.setText(Float.toString(dt.getCosto()));

		DefaultListModel<String> modelo = new DefaultListModel<>();

		for (String consulta : dt.getConsultas()) {
			modelo.addElement(consulta);
		}

		listConsultas.setModel(modelo);
	}

	private void limpiarDatos() {

		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldDiaSemana.setText("");
		textFieldHoraInicio.setText("");
		textFieldCosto.setText("");

		listConsultas.setModel(new DefaultListModel<>());
	}
}
