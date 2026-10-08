package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EspecialidadNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.clases.Fabrica;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Usuario;
import logica.datatypes.DTDatosUsuarioMedico;
import logica.datatypes.DTDatosUsuarioPaciente;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTUsuario;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

public class ControladorUsuarioTest {

	private static IControladorUsuario cu;
	private static ManejadorUsuario mu;
	private static ManejadorEspecialidad me;

	@BeforeAll
	public static void iniciar() {
		ManejadorEspecialidad.getinstance().resetForTests();
		ManejadorUsuario.getinstance().resetForTests();
		ManejadorConsultas.getinstance().resetForTests();
		ManejadorDiasConsulta.getinstance().resetForTests();
		ManejadorClinica.getinstance().resetForTests();

		Fabrica fabrica = Fabrica.getInstance();
		mu = ManejadorUsuario.getinstance();
		me = ManejadorEspecialidad.getinstance();
		cu = fabrica.getIControladorUsuario();

		me.crearEspecialidad("Cardiología", 20);
		me.crearEspecialidad("Pediatría", 10);
		me.crearEspecialidad("Medicina General", 0);

		mu.addUsuario(new Medico("ana.perez", "Ana", "Pérez", "ana.perez@tuclinica.com","123", 1001, 1, 15,
				"https://www.hospitalbritanico.org.uy/",
				new HashSet<>(Set.of(me.getEspecialidad("Cardiología"), me.getEspecialidad("Medicina General")))));

		mu.addUsuario(new Medico("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com", "123",1002, 7, 5, "",
				new HashSet<>(Set.of(me.getEspecialidad("Pediatría")))));

		mu.addUsuario(new Medico("carla.rodriguez", "Carla", "Rodríguez", "carla.rodriguez@hospital.com","123", 1003, 2, 10,
				"https://www.hospitalbritanico.org.uy/",
				new HashSet<>(Set.of(me.getEspecialidad("Cardiología"), me.getEspecialidad("Medicina General")))));

		mu.addUsuario(new Paciente("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com", "123", LocalDate.of(1990, 4, 12),
				LocalDate.of(2024, 3, 1), 45678901, "Sin antecedentes relevantes."));

		mu.addUsuario(new Paciente("mar.fernandez", "Mario", "Fernández", "mario.fernandez@hotmail.com", "123",
				LocalDate.of(1955, 11, 8), LocalDate.of(2022, 6, 15), 32145678,
				"Hipertensión arterial diagnosticada en 2018."));

		mu.addUsuario(new Paciente("s.lopez", "Sofía", "López", "sofia.lopez@gmail.com", "123",LocalDate.of(2014, 9, 20),
				LocalDate.of(2025, 2, 10), 58963214, "Alergia a penicilina."));
	}

	@Test
	void testListarUsuariosOK() {
		DTUsuario[] usuarios = cu.listarUsuarios();

		assertNotNull(usuarios);
		assertTrue(usuarios.length >= 6);
	}

	@Test
	void testListarPacientesOK() {
		Set<String> pacientes = cu.listarPacientes();

		List<String> enSistema = mu.getPacientes().stream().map(Paciente::getNickname).toList();
		assertEquals(pacientes.size(), enSistema.size());
		assertTrue(pacientes.containsAll(enSistema));
	}

	@Test
	void testSeleccionarPacienteOK() throws UsuarioNoExisteException {
		DTUsuario usuario = cu.seleccionarUsuario("lgomez");

		assertTrue(usuario instanceof DTPaciente);

		DTPaciente paciente = (DTPaciente) usuario;

		assertEquals("lgomez", paciente.getNickname());
		assertEquals("Laura", paciente.getNombre());
		assertEquals("Gómez", paciente.getApellido());
		assertEquals("laura.gomez@gmail.com", paciente.getMail());
		assertEquals(LocalDate.of(1990, 4, 12), paciente.getFechaNac());
		assertEquals(LocalDate.of(2024, 3, 1), paciente.getFechaIng());
		assertEquals(45678901, paciente.getCI());
		assertEquals("Sin antecedentes relevantes.", paciente.getAntecedentes());
	}

	@Test
	void testSeleccionarMedicoOK() throws UsuarioNoExisteException {
		DTUsuario usuario = cu.seleccionarUsuario("ana.perez");

		assertTrue(usuario instanceof DTMedico);

		DTMedico medico = (DTMedico) usuario;

		assertEquals("Ana", medico.getNombre());
		assertEquals("Pérez", medico.getApellido());
		assertEquals(1001, medico.getNumReg());
		assertEquals(1, medico.getMesUL());
		assertEquals(15f, medico.getPorcentajeAd(), 0.001f);
		assertEquals("https://www.hospitalbritanico.org.uy/", medico.getWeb());
		assertTrue(medico.getEspecialidades().contains("Cardiología"));
		assertTrue(medico.getEspecialidades().contains("Medicina General"));
	}

	@Test
	void testModificarMedicoEspecialidadNoExiste() {
		assertThrows(EspecialidadNoExisteException.class, () -> {
			cu.modificarMedico("carla.rodriguez", "Carla", "Rodríguez", 1003, "https://www.hospitalbritanico.org.uy/",
					Set.of("asdasdasds"), 10, 2);
		});
	}

	@Test
	void testModificarMedicoOK() throws UsuarioNoExisteException, EspecialidadNoExisteException {

		cu.modificarMedico("bsilva", "BrunoModificado", "SilvaModificado", 2002, "www.modificado.com",
				Set.of("Cardiología", "Medicina General"), 25, 8);

		Medico m = (Medico) mu.obtenerUsuario("bsilva");

		assertEquals("BrunoModificado", m.getNombre());
		assertEquals("SilvaModificado", m.getApellido());
		assertEquals(2002, m.getNumReg());
		assertEquals("www.modificado.com", m.getWeb());
		assertEquals(25, m.getPorcentajeAd());
		assertEquals(8, m.getMesUL());

		assertEquals(2, m.getEspecialidades().size());

		assertTrue(m.getEspecialidades().stream().anyMatch(e -> e.getNombre().equals("Cardiología")));

		assertTrue(m.getEspecialidades().stream().anyMatch(e -> e.getNombre().equals("Medicina General")));
	}

	@Test
	void testModificarPacienteOK() throws UsuarioNoExisteException {

		cu.modificarPaciente("lgomez", "LauraModificada", "GómezModificada", LocalDate.of(1991, 5, 10), 87654321,
				"Antecedentes modificados", LocalDate.of(2025, 4, 1));

		Paciente p = (Paciente) mu.obtenerUsuario("lgomez");

		assertEquals("LauraModificada", p.getNombre());
		assertEquals("GómezModificada", p.getApellido());
		assertEquals(LocalDate.of(1991, 5, 10), p.getFechaNac());
		assertEquals(87654321, p.getCI());
		assertEquals("Antecedentes modificados", p.getAntecedentes());
		assertEquals(LocalDate.of(2025, 4, 1), p.getFechaIng());
	}

	@Test
	void testModificarPacienteNoExiste() {

		assertThrows(UsuarioNoExisteException.class, () -> {
			cu.modificarPaciente("pacienteeeeee", "Nombre", "Apellido", LocalDate.of(2000, 1, 1), 11111111,
					"no tiene antecedentes", LocalDate.of(2000, 1, 1));
		});
	}

	@Test
	void testModificarMedicoNoExiste() {

		assertThrows(UsuarioNoExisteException.class, () -> {
			cu.modificarMedico("medicoooo", "Nombre", "Apellido", 1111, "", Set.of("Cardiología"), 10, 1);
		});
	}

	@Test
	void testModificarPacienteEsMedico() {

		assertThrows(UsuarioNoExisteException.class, () -> {
			cu.modificarPaciente("ana.perez", "Nombre", "Apellido", LocalDate.of(2000, 1, 1), 12345678,
					"Sin antecedentes", LocalDate.of(2025, 1, 1));
		});
	}

	@Test
	void testModificarMedicoEsPaciente() {

		assertThrows(UsuarioNoExisteException.class, () -> {
			cu.modificarMedico("s.lopez", "Nombre", "Apellido", 1234, "", Set.of("Cardiología"), 10, 1);
		});
	}

	@Test
	void testAltaMedicoRepetido() {

		boolean resultado = cu.altaMedico("ana.perez", "Ana", "Pérez", "otro@gmail.com", 1001, 1, 15, "",
				Set.of("Cardiología"));
		assertFalse(resultado);
	}

	@Test
	void testAltaMedicoOK() {

		boolean resultado = cu.altaMedico("pedrog", "Pedro", "gomez", "gomezpedro@gmail.com", 5000, 6, 10,
				"www.gomez.com", Set.of("Cardiología"));
		assertTrue(resultado);

		Usuario u = mu.obtenerUsuario("pedrog");

		assertNotNull(u);
		assertTrue(u instanceof Medico);

		Medico m = (Medico) u;

		assertEquals(5000, m.getNumReg());
		assertTrue(m.getEspecialidades().stream().anyMatch(e -> e.getNombre().equals("Cardiología")));
	}

	@Test
	void testAltaPacienteRepetido() {

		boolean resultado = cu.altaPaciente("lgomez", "Laura", "Gómez", "otro@mail.com", LocalDate.of(1990, 4, 12),
				45678901, LocalDate.of(2024, 3, 1), "No tiene antecedentes");
		assertFalse(resultado);
	}

	@Test
	void testAltaPacienteOK() {

		boolean resultado = cu.altaPaciente("gomezcarlos", "carlos", "gomez", "carlosssg@gmail.com",
				LocalDate.of(1995, 3, 10), 44444444, LocalDate.of(2026, 1, 1), "No tiene antecedentes");
		assertTrue(resultado);

		Usuario u = mu.obtenerUsuario("gomezcarlos");

		assertNotNull(u);
		assertTrue(u instanceof Paciente);
	}

	@Test
	void testConsultarPacienteOK() {
		DTDatosUsuarioPaciente dtp = null;
		try {
			dtp = cu.consultarPaciente("lgomez");
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(dtp);
		assertEquals("lgomez", dtp.getNickname());
		assertEquals("Laura", dtp.getNombre());
		assertEquals("Gómez", dtp.getApellido());
		assertEquals("laura.gomez@gmail.com", dtp.getMail());
		assertNotNull(dtp.getReservasConsultas());
		assertTrue(dtp.getReservasConsultas().isEmpty());
	}

	@Test
	void testConsultarMedicoOK() {

		DTDatosUsuarioMedico dtm = null;
		try {
			dtm = cu.consultarMedico("carla.rodriguez");
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(dtm);
		assertEquals("carla.rodriguez", dtm.getNickname());
		assertEquals("Carla", dtm.getNombre());
		assertEquals("Rodríguez", dtm.getApellido());
		assertEquals("carla.rodriguez@hospital.com", dtm.getMail());
		assertNotNull(dtm.getConsultas());
		assertTrue(dtm.getConsultas().isEmpty());
	}
}
