package presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import logica.clases.Fabrica;
import logica.interfaces.IControladorBeneficioAnual;
import logica.interfaces.IControladorCargaDatos;
import logica.interfaces.IControladorClinica;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorEspecialidad;
import logica.interfaces.IControladorUsuario;

public class Principal {

	private JFrame frmGestionDeUsuarios;
	private JDesktopPane desktopPane;
	private IControladorUsuario ICU;
	private IControladorEspecialidad ICE;
	private IControladorBeneficioAnual ICBA;
	private IControladorClinica ICC;
	private IControladorCargaDatos ICCD;
	private IControladorConsulta ICO;
	private AltaClinica altaCliInternalFrame;
	private ConsultaClinica consultaCliInternalFrame;
	private AltaUsuario creUsrInternalFrame;
	private ConsultarUsuario conUsrInternalFrame;
	private AltaEspecialidad altaEspInternalFrame;
	private AltaBeneficioAnual altaBenAnInternalFrame;
	private ConsultaBeneficioAnual conBenAnInternalFrame;
	private ModificarDatosDeUsuario modUsrInternalFrame;
	private AltaDiaConsulta altaDiaConsultaInternalFrame;
	private ReservaConsulta reservaConsInternalFrame;
	private ConsultaReserva consReservaInternalFrame;
	private AltaConsulta altaConsultaInternalFrame;
	private ConsultaConsulta consultaConsultaInternalFrame;
	private ConsultaDiaConsulta consultaDiaConsultaInternalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Principal window = new Principal();
					window.frmGestionDeUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();

		// Inicialización
		Fabrica fabrica = Fabrica.getInstance();
		ICU = fabrica.getIControladorUsuario();
		ICE = fabrica.getIControladorEspecialidad();
		ICC = fabrica.getIControladorClinica();
		ICO = fabrica.getIControladorConsulta();
		ICBA = fabrica.getIControladorBeneficioAnual();
		ICCD = fabrica.getIControladorCargaDatos();
		ICO = fabrica.getIControladorConsulta();

		// Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
		// De esta forma, no es necesario crear y destruir objetos lo que enlentece la
		// ejecución.
		// Cada InternalFrame usa un layout diferente, simplemente para mostrar
		// distintas opciones.

		desktopPane = new JDesktopPane();
		frmGestionDeUsuarios.setContentPane(desktopPane);
		desktopPane.setBackground(new Color(238, 238, 238));

		creUsrInternalFrame = new AltaUsuario(ICE, ICU);
		creUsrInternalFrame.setVisible(false);

		altaCliInternalFrame = new AltaClinica(ICC);
		altaCliInternalFrame.setVisible(false);

		consultaCliInternalFrame = new ConsultaClinica(ICC);
		consultaCliInternalFrame.setVisible(false);

		altaEspInternalFrame = new AltaEspecialidad(ICE);
		altaEspInternalFrame.setVisible(false);

		altaBenAnInternalFrame = new AltaBeneficioAnual(ICBA, ICU);
		altaBenAnInternalFrame.setVisible(false);

		altaConsultaInternalFrame = new AltaConsulta(ICO);
		altaConsultaInternalFrame.setVisible(false);

		conBenAnInternalFrame = new ConsultaBeneficioAnual(ICBA, ICU);
		conBenAnInternalFrame.setVisible(false);

		modUsrInternalFrame = new ModificarDatosDeUsuario(ICU, ICE);
		modUsrInternalFrame.setVisible(false);

		altaDiaConsultaInternalFrame = new AltaDiaConsulta(ICO, ICE, ICC);
		altaDiaConsultaInternalFrame.setVisible(false);

		reservaConsInternalFrame = new ReservaConsulta(ICU, ICO);
		reservaConsInternalFrame.setVisible(false);

		consReservaInternalFrame = new ConsultaReserva(ICU, ICO);
		consReservaInternalFrame.setVisible(false);

		consultaConsultaInternalFrame = new ConsultaConsulta(ICO, consReservaInternalFrame);
		consultaConsultaInternalFrame.setVisible(false);

		consultaDiaConsultaInternalFrame = new ConsultaDiaConsulta(ICO, consultaConsultaInternalFrame);
		consultaDiaConsultaInternalFrame.setVisible(false);

		conUsrInternalFrame = new ConsultarUsuario(ICU, consultaConsultaInternalFrame, consReservaInternalFrame);
		conUsrInternalFrame.setVisible(false);

		desktopPane.add(conUsrInternalFrame);
		desktopPane.add(creUsrInternalFrame);
		desktopPane.add(altaCliInternalFrame);
		desktopPane.add(consultaCliInternalFrame);
		desktopPane.add(altaEspInternalFrame);
		desktopPane.add(altaBenAnInternalFrame);
		desktopPane.add(conBenAnInternalFrame);
		desktopPane.add(modUsrInternalFrame);
		desktopPane.add(altaDiaConsultaInternalFrame);
		desktopPane.add(reservaConsInternalFrame);
		desktopPane.add(consReservaInternalFrame);
		desktopPane.add(altaConsultaInternalFrame);
		desktopPane.add(consultaConsultaInternalFrame);
		desktopPane.add(consultaDiaConsultaInternalFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Se crea el Frame con las dimensiones indicadas.
		frmGestionDeUsuarios = new JFrame();
		frmGestionDeUsuarios.setTitle("Gestion de Usuarios 1.0");
		frmGestionDeUsuarios.setBounds(100, 100, 1280, 720);
		frmGestionDeUsuarios.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
		// Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un
		// evento asociado que permite realizar una acción una vez se seleccionan.
		JMenuBar menuBar = new JMenuBar();
		frmGestionDeUsuarios.setJMenuBar(menuBar);

		JMenu menuSistema = new JMenu("Sistema");
		menuBar.add(menuSistema);

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Salgo de la aplicación
				frmGestionDeUsuarios.setVisible(false);
				frmGestionDeUsuarios.dispose();
			}
		});

		JMenuItem mntmCargarDatos = new JMenuItem("Cargar datos");
		mntmCargarDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ICCD.cargarDatos();
			}
		});
		menuSistema.add(mntmCargarDatos);
		menuSistema.add(menuSalir);

		JMenu menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);

		JMenuItem menuItemRegistrar = new JMenuItem("Alta de Usuario");
		menuItemRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creUsrInternalFrame.cargarEspecialidades();
				creUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemRegistrar);

		JMenuItem menuItemVerInfo = new JMenuItem("Consultar Usuario");
		menuItemVerInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver información de un usuario
				conUsrInternalFrame.cargarUsuarios();
				conUsrInternalFrame.setVisible(true);
			}
		});

		menuUsuarios.add(menuItemVerInfo);

		JMenuItem menuItemModificarU = new JMenuItem("Modificar Datos De Usuario");
		menuItemModificarU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modUsrInternalFrame.cargarUsuarios();
				// Muestro el InternalFrame para ver información de un usuario
				modUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemModificarU);

		JMenu menuEspecialidades = new JMenu("Especialidades");
		menuBar.add(menuEspecialidades);

		JMenuItem menuItemAltaDeEspecialidad = new JMenuItem("Alta de Especialidad");
		menuItemAltaDeEspecialidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaEspInternalFrame.setVisible(true);
			}
		});
		menuEspecialidades.add(menuItemAltaDeEspecialidad);

		JMenu menuConsultas = new JMenu("Consultas");
		menuBar.add(menuConsultas);

		JMenuItem menuItemAltaDiaConsulta = new JMenuItem("Alta de Día de Consulta");
		menuItemAltaDiaConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaDiaConsultaInternalFrame.cargarDatos();
				altaDiaConsultaInternalFrame.setVisible(true);
			}
		});
		menuConsultas.add(menuItemAltaDiaConsulta);

		JMenuItem menuItemConsultaDiaConsulta = new JMenuItem("Consulta de Día de Consulta");
		menuItemConsultaDiaConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consultaDiaConsultaInternalFrame.cargarDias();
				consultaDiaConsultaInternalFrame.setVisible(true);
			}
		});
		menuConsultas.add(menuItemConsultaDiaConsulta);

		JMenu menuBeneficiosAnuales = new JMenu("Beneficios Anuales");
		menuBar.add(menuBeneficiosAnuales);

		JMenuItem menuItemAltaDeBeneficio = new JMenuItem("Alta de Beneficio Anual");
		menuItemAltaDeBeneficio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaBenAnInternalFrame.cargarPacientes();
				altaBenAnInternalFrame.setVisible(true);
			}
		});
		menuBeneficiosAnuales.add(menuItemAltaDeBeneficio);

		JMenuItem menuItemConsultaDeBeneficio = new JMenuItem("Consulta de Beneficio Anual");
		menuItemConsultaDeBeneficio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conBenAnInternalFrame.limpiarFormulario();
				conBenAnInternalFrame.cargarPacientes();
				conBenAnInternalFrame.setVisible(true);
			}
		});
		menuBeneficiosAnuales.add(menuItemConsultaDeBeneficio);
		JMenu menuClinicas = new JMenu("Clinicas");
		menuBar.add(menuClinicas);

		JMenuItem menuItemAltaDeClinica = new JMenuItem("Alta de Clinica");
		menuItemAltaDeClinica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaCliInternalFrame.setVisible(true);
			}
		});
		menuClinicas.add(menuItemAltaDeClinica);

		JMenuItem menuItemConsultaDeClinica = new JMenuItem("Consulta de Clinica");
		menuItemConsultaDeClinica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consultaCliInternalFrame.cargarCombo();
				consultaCliInternalFrame.setVisible(true);
				consultaCliInternalFrame.toFront();
			}
		});
		menuClinicas.add(menuItemConsultaDeClinica);

		JMenuItem menuItemReservaDeConsulta = new JMenuItem("Reserva de Consulta");
		menuConsultas.add(menuItemReservaDeConsulta);
		menuItemReservaDeConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reservaConsInternalFrame.cargarCombos();
				reservaConsInternalFrame.setVisible(true);
			}
		});

		JMenuItem menuItemConsultaDeConsulta = new JMenuItem("Consulta de Consulta");
		menuItemConsultaDeConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consultaConsultaInternalFrame.cargarDias();
				consultaConsultaInternalFrame.setVisible(true);
			}
		});
		menuConsultas.add(menuItemConsultaDeConsulta);

		JMenuItem menuItemAltaConsulta = new JMenuItem("Alta de Consulta");
		menuItemAltaConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaConsultaInternalFrame.cargarCombos();
				altaConsultaInternalFrame.setVisible(true);
			}
		});
		menuConsultas.add(menuItemAltaConsulta);

		JMenuItem menuItemConsultaDeReserva = new JMenuItem("Consulta de Reserva");
		menuConsultas.add(menuItemConsultaDeReserva);
		menuItemConsultaDeReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consReservaInternalFrame.cargarCombos();
				consReservaInternalFrame.setVisible(true);
			}
		});

		// HARDCODEO PARA TEST GUI
//		ManejadorUsuario mu = ManejadorUsuario.getinstance();
//		mu.addUsuario(new Paciente("pedro", "asd", "asd2", "asd3", LocalDate.now(), LocalDate.now(), 12345678, new HashSet<String>()));
//		mu.addUsuario(new Paciente("juan", "asd", "asd2", "asd3", LocalDate.now(), LocalDate.now(), 12345678, new HashSet<String>()));
//		mu.addUsuario(new Medico("asdasd1", "asd", "asd2", "asd3", 1, 2, 3, "eva"));
	}
}
