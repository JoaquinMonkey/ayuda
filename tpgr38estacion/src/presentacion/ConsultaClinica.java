package presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import excepciones.ClinicaNoExisteException;
import logica.datatypes.DTClinica;
import logica.interfaces.IControladorClinica;

@SuppressWarnings("serial")
public class ConsultaClinica extends JInternalFrame {

	private JTextField textNombre;
	private JTextField textDesc;
	private JTextField textDirecc;
	private JTextField textWeb;
	private JTextField textMed;
	private JTextField textCons;
	private JComboBox<String> comboClinica;
	private IControladorClinica ctrlClinica;

	public void cargarCombo() {
		comboClinica.removeAllItems();
		for (String clinica : ctrlClinica.listarClinicas()) {
			comboClinica.addItem(clinica);
		}
	}

	public ConsultaClinica(IControladorClinica ICC) {
		this.ctrlClinica = ICC;
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Consulta de Clinica");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 150, 150 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		contentPane.setLayout(gridBagLayout);

		JLabel lblClinica = new JLabel("Clinica:");
		lblClinica.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblClinica = new GridBagConstraints();
		gbc_lblClinica.anchor = GridBagConstraints.EAST;
		gbc_lblClinica.fill = GridBagConstraints.VERTICAL;
		gbc_lblClinica.insets = new Insets(0, 0, 5, 5);
		gbc_lblClinica.gridx = 0;
		gbc_lblClinica.gridy = 0;
		contentPane.add(lblClinica, gbc_lblClinica);

		comboClinica = new JComboBox<>();
		GridBagConstraints gbc_comboClinica = new GridBagConstraints();
		gbc_comboClinica.gridwidth = 2;
		gbc_comboClinica.insets = new Insets(0, 0, 5, 0);
		gbc_comboClinica.fill = GridBagConstraints.BOTH;
		gbc_comboClinica.gridx = 1;
		gbc_comboClinica.gridy = 0;
		contentPane.add(comboClinica, gbc_comboClinica);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		contentPane.add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 2;
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.fill = GridBagConstraints.BOTH;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 1;
		contentPane.add(textNombre, gbc_textNombre);

		JLabel lblDesc = new JLabel("Descripcion:");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDesc = new GridBagConstraints();
		gbc_lblDesc.anchor = GridBagConstraints.EAST;
		gbc_lblDesc.fill = GridBagConstraints.VERTICAL;
		gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesc.gridx = 0;
		gbc_lblDesc.gridy = 2;
		contentPane.add(lblDesc, gbc_lblDesc);

		textDesc = new JTextField();
		textDesc.setEditable(false);
		textDesc.setColumns(10);
		GridBagConstraints gbc_textDesc = new GridBagConstraints();
		gbc_textDesc.gridwidth = 2;
		gbc_textDesc.insets = new Insets(0, 0, 5, 0);
		gbc_textDesc.fill = GridBagConstraints.BOTH;
		gbc_textDesc.gridx = 1;
		gbc_textDesc.gridy = 2;
		contentPane.add(textDesc, gbc_textDesc);

		JLabel lblDirecc = new JLabel("Direccion:");
		lblDirecc.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDirecc = new GridBagConstraints();
		gbc_lblDirecc.anchor = GridBagConstraints.EAST;
		gbc_lblDirecc.fill = GridBagConstraints.VERTICAL;
		gbc_lblDirecc.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirecc.gridx = 0;
		gbc_lblDirecc.gridy = 3;
		contentPane.add(lblDirecc, gbc_lblDirecc);

		textDirecc = new JTextField();
		textDirecc.setEditable(false);
		textDirecc.setColumns(10);
		GridBagConstraints gbc_textDirecc = new GridBagConstraints();
		gbc_textDirecc.gridwidth = 2;
		gbc_textDirecc.insets = new Insets(0, 0, 5, 0);
		gbc_textDirecc.fill = GridBagConstraints.BOTH;
		gbc_textDirecc.gridx = 1;
		gbc_textDirecc.gridy = 3;
		contentPane.add(textDirecc, gbc_textDirecc);

		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblWeb = new GridBagConstraints();
		gbc_lblWeb.anchor = GridBagConstraints.EAST;
		gbc_lblWeb.fill = GridBagConstraints.VERTICAL;
		gbc_lblWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeb.gridx = 0;
		gbc_lblWeb.gridy = 4;
		contentPane.add(lblWeb, gbc_lblWeb);

		textWeb = new JTextField();
		textWeb.setEditable(false);
		textWeb.setColumns(10);
		GridBagConstraints gbc_textWeb = new GridBagConstraints();
		gbc_textWeb.gridwidth = 2;
		gbc_textWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textWeb.fill = GridBagConstraints.BOTH;
		gbc_textWeb.gridx = 1;
		gbc_textWeb.gridy = 4;
		contentPane.add(textWeb, gbc_textWeb);

		JLabel lblTicketMed = new JLabel("Costo Medicamento:");
		lblTicketMed.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTicketMed = new GridBagConstraints();
		gbc_lblTicketMed.anchor = GridBagConstraints.EAST;
		gbc_lblTicketMed.fill = GridBagConstraints.VERTICAL;
		gbc_lblTicketMed.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketMed.gridx = 0;
		gbc_lblTicketMed.gridy = 5;
		contentPane.add(lblTicketMed, gbc_lblTicketMed);

		textMed = new JTextField();
		textMed.setEditable(false);
		textMed.setColumns(10);
		GridBagConstraints gbc_textMed = new GridBagConstraints();
		gbc_textMed.gridwidth = 2;
		gbc_textMed.insets = new Insets(0, 0, 5, 0);
		gbc_textMed.fill = GridBagConstraints.BOTH;
		gbc_textMed.gridx = 1;
		gbc_textMed.gridy = 5;
		contentPane.add(textMed, gbc_textMed);

		JLabel lblCostoConsulta = new JLabel("Costo Consulta:");
		lblCostoConsulta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCostoConsulta = new GridBagConstraints();
		gbc_lblCostoConsulta.anchor = GridBagConstraints.EAST;
		gbc_lblCostoConsulta.fill = GridBagConstraints.VERTICAL;
		gbc_lblCostoConsulta.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoConsulta.gridx = 0;
		gbc_lblCostoConsulta.gridy = 6;
		contentPane.add(lblCostoConsulta, gbc_lblCostoConsulta);

		textCons = new JTextField();
		textCons.setEditable(false);
		textCons.setColumns(10);
		GridBagConstraints gbc_textCons = new GridBagConstraints();
		gbc_textCons.gridwidth = 2;
		gbc_textCons.insets = new Insets(0, 0, 5, 0);
		gbc_textCons.fill = GridBagConstraints.BOTH;
		gbc_textCons.gridx = 1;
		gbc_textCons.gridy = 6;
		contentPane.add(textCons, gbc_textCons);

		comboClinica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreSelec = (String) comboClinica.getSelectedItem();
				if (nombreSelec == null) {
					return;
				}
				DTClinica dt = null;
				try {
					dt = ctrlClinica.consultarClinica(nombreSelec);
				} catch (ClinicaNoExisteException e1) {
					JOptionPane.showMessageDialog(ConsultaClinica.this,
							"No existe una clinica registrada con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (dt == null) {
					return;
				}
				textNombre.setText(dt.getNombre());
				textDesc.setText(dt.getDesc());
				textDirecc.setText(dt.getDirec());
				textWeb.setText(dt.getWeb());
				textMed.setText(String.valueOf(dt.getCostoMed()));
				textCons.setText(String.valueOf(dt.getCostoConsulta()));
			}
		});
	}

}
