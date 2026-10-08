package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.DiaConsultaNoExisteException;
import excepciones.FechaNoCoincideDiaException;
import excepciones.MedicoSinEspecialidadException;
import excepciones.NombreConsultaRepetidoException;
import excepciones.PacienteYaReservadoException;
import excepciones.ReservaFueraDeFechaException;
import excepciones.SinCupoException;
import logica.clases.Clinica;
import logica.clases.Dia_consulta;
import logica.clases.Especialidad;
import logica.clases.Fabrica;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDia_consulta;
import logica.datatypes.DTReserva;
import logica.datatypes.DTReservaDet;
import logica.datatypes.DTReservaResumen;
import logica.interfaces.IControladorConsulta;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

class ControladorConsultasTest {
	@BeforeAll
	public static void iniciar() {
		ManejadorEspecialidad.getinstance().resetForTests();
		ManejadorUsuario.getinstance().resetForTests();
		ManejadorConsultas.getinstance().resetForTests();
		ManejadorDiasConsulta.getinstance().resetForTests();
		ManejadorClinica.getinstance().resetForTests();
	}

	@BeforeEach
	void limpiarEstado() {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		mC.resetForTests();
	}

	private static final int LUNES = 1;
	private static final LocalDate UN_LUNES = LocalDate.of(2024, 1, 1);
	private static final LocalDate UN_MARTES = LocalDate.of(2024, 1, 2);

	private IControladorConsulta getControlador() {
		return Fabrica.getInstance().getIControladorConsulta();
	}

	private void prepararEscenario(String nombreDia, String nickMedico, boolean medicoConEspecialidad) {
		Especialidad esp = new Especialidad("EspTest", 20f);
		Clinica cli = new Clinica("CliTest", "desc", "dir", "www", 100f, 500f);

		Dia_consulta dia = new Dia_consulta(nombreDia, "descripcion", LUNES, LocalTime.of(9, 0), cli, esp);
		ManejadorDiasConsulta.getinstance().addDiaConsulta(dia);

		Set<Especialidad> esps = new HashSet<>();
		if (medicoConEspecialidad) {
			esps.add(esp);
		} else {
			esps.add(new Especialidad("OtraEsp", 5f));
		}

		Medico m = new Medico(nickMedico, "Nom", "Ape", nickMedico + "@test.com", "123", 1, 1, 10f, "", esps);
		ManejadorUsuario.getinstance().addUsuario(m);
	}

	private Paciente crearPaciente(String nickname, LocalDate fechaNac) {
		Paciente p = new Paciente(nickname, "Nombre", "Apellido", nickname + "@test.com", "123",fechaNac,
				LocalDate.of(2020, 1, 1), 12345678, "Sin antecedentes");

		ManejadorUsuario.getinstance().addUsuario(p);

		return p;
	}

	@Test
	void testAltaConsultaOk() throws Exception {
		prepararEscenario("DiaOk", "medOk", true);
		IControladorConsulta icc = getControlador();

		icc.altaConsulta("DiaOk", "medOk", "ConsultaOk", UN_LUNES, 5, 10);

		assertTrue(icc.listarConsultas("DiaOk").contains("ConsultaOk"),
				"La consulta deberia figurar en el dia de consulta");
	}

	@Test
	void testCostoSegunDiaYMedico() throws Exception {
		prepararEscenario("DiaCosto", "medCosto", true);
		IControladorConsulta icc = getControlador();

		icc.altaConsulta("DiaCosto", "medCosto", "ConsultaCosto", UN_LUNES, 1, 3);
		DTConsulta dt = icc.consultarConsulta("ConsultaCosto");

		assertEquals("medCosto", dt.getMedico());
		assertEquals("ConsultaCosto", dt.getNombre());
		assertEquals(1, dt.getNroSala());
		assertEquals(3, dt.getNrosDisp());
		assertNotNull(dt.getReservas());
		assertTrue(dt.getReservas().isEmpty());
	}

	@Test
	void testNombreConsultaRepetido() throws Exception {
		prepararEscenario("DiaRep", "medRep", true);
		IControladorConsulta icc = getControlador();

		icc.altaConsulta("DiaRep", "medRep", "ConsultaRepetida", UN_LUNES, 1, 3);

		assertThrows(NombreConsultaRepetidoException.class,
				() -> icc.altaConsulta("DiaRep", "medRep", "ConsultaRepetida", UN_LUNES, 2, 4));
	}

	@Test
	void testMedicoSinEspecialidad() {
		prepararEscenario("DiaSinEsp", "medSinEsp", false);
		IControladorConsulta icc = getControlador();

		assertThrows(MedicoSinEspecialidadException.class,
				() -> icc.altaConsulta("DiaSinEsp", "medSinEsp", "ConsultaSinEsp", UN_LUNES, 1, 3));
	}

	@Test
	void testFechaNoCoincideDia() {
		prepararEscenario("DiaFecha", "medFecha", true);
		IControladorConsulta icc = getControlador();
		assertThrows(FechaNoCoincideDiaException.class,
				() -> icc.altaConsulta("DiaFecha", "medFecha", "ConsultaFecha", UN_MARTES, 1, 3));
	}

	@Test
	void testConsultarConsultaInexistente() {
		IControladorConsulta icc = getControlador();
		assertEquals(null, icc.consultarConsulta("NoExisteNiDeCasualidad"));
	}

	@Test
	void testEsValidoNombreDiaDeConsulta() {
		IControladorConsulta icc = getControlador();

		assertTrue(icc.esValidoNombreDiaDeConsulta("DiaNombreNuevo"));

		Especialidad esp = new Especialidad("EspNombreDia", 10f);
		Clinica cli = new Clinica("CliNombreDia", "desc", "dir", "web", 100f, 500f);

		Dia_consulta dia = new Dia_consulta("DiaNombreNuevo", "descripcion", LUNES, LocalTime.of(8, 0), cli, esp);

		ManejadorDiasConsulta.getinstance().addDiaConsulta(dia);

		assertFalse(icc.esValidoNombreDiaDeConsulta("DiaNombreNuevo"));
	}

	@Test
	void testRegistrarDiaDeConsulta() {
		IControladorConsulta icc = getControlador();

		ManejadorEspecialidad.getinstance().crearEspecialidad("EspRegistrarDia", 15f);

		ManejadorClinica.getinstance().crearClinica("CliRegistrarDia", "descripcion clinica", "direccion", "web", 100f,
				400f);

		icc.registrarDiaDeConsulta("DiaRegistrado", "descripcion dia", LUNES, LocalTime.of(10, 30), "EspRegistrarDia",
				"CliRegistrarDia");

		Dia_consulta dia = ManejadorDiasConsulta.getinstance().getDiaConsulta("DiaRegistrado");

		assertNotNull(dia);
		assertEquals("DiaRegistrado", dia.getNombre());
		assertEquals("descripcion dia", dia.getDesc());
		assertEquals(LUNES, dia.getDiaSem());
		assertEquals(LocalTime.of(10, 30), dia.getHoraInicio());
		assertEquals("EspRegistrarDia", dia.getEspecialidad().getNombre());
		assertEquals("CliRegistrarDia", dia.getClinica().getNombre());
	}

	private Paciente crearPaciente(String nickname, int edad) {
		Paciente p = new Paciente(nickname, "Nom", "Ape", nickname + "@test.com","123", LocalDate.now().minusYears(edad),
				LocalDate.now(), 12345678, "ant");
		ManejadorUsuario.getinstance().addUsuario(p);
		return p;
	}

	@Test
	void testReservaOk() throws Exception {
		prepararEscenario("DiaOk", "medOk", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaOk", "medOk", "ConsultaOk", LocalDate.of(2200, 9, 1), 5, 10);
		crearPaciente("pacReservaOk", 40);
		DTReserva r = icc.reservarConsultaPaciente("pacReservaOk", "ConsultaOk");
		assertNotNull(r, "No deberia ser null al ser creada");
		assertEquals(1, r.getNumero(), "Es el unico reservado, tiene que tener el numero 1");
		assertEquals(LocalDate.now(), r.getFecha(), "La fecha debe corresponder al dia que se hiozo la reserva");
	}

	@Test
	void testReservaConDescuentoMayor60() throws Exception {
		prepararEscenario("DiaBonif", "medBonif", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaBonif", "medBonif", "ConsultaBonif", LocalDate.of(2200, 9, 1), 5, 10);
		crearPaciente("pacMayor", 65);

		DTReserva r = icc.reservarConsultaPaciente("pacMayor", "ConsultaBonif");
		assertNotNull(r, "No deberia ser null al ser creada");
		assertEquals(528f, r.getCosto(), 0.01f, "Debe aplicarse el 20% de descuento porque el paciente es mayor de 60");
	}

	@Test
	void testReservaSinBonificacionMenor60() throws Exception {
		prepararEscenario("DiaSinBonif", "medSinBonif", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaSinBonif", "medSinBonif", "ConsultaSinBonif", LocalDate.of(2200, 9, 1), 5, 10);
		crearPaciente("pacMenor", 30);

		DTReserva r = icc.reservarConsultaPaciente("pacMenor", "ConsultaSinBonif");

		assertEquals(660f, r.getCosto(), 0.01f, "No debe aplicarse descuento si el paciente no es mayor de 60");
	}

	@Test
	void testSinCupo() throws Exception {
		prepararEscenario("DiaCupo", "medCupo", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaCupo", "medCupo", "ConsultaUnCupo", LocalDate.of(2200, 9, 1), 1, 1);
		crearPaciente("pacCupo1", 30);
		crearPaciente("pacCupo2", 30);

		icc.reservarConsultaPaciente("pacCupo1", "ConsultaUnCupo");

		assertThrows(SinCupoException.class, () -> icc.reservarConsultaPaciente("pacCupo2", "ConsultaUnCupo"));
	}

	@Test
	void testPacienteYaReservado() throws Exception {
		prepararEscenario("DiaReservado", "medReservado", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaReservado", "medReservado", "ConsultaReservado", LocalDate.of(2200, 9, 1), 1, 10);
		crearPaciente("pacReservado", 30);

		icc.reservarConsultaPaciente("pacReservado", "ConsultaReservado");

		assertThrows(PacienteYaReservadoException.class,
				() -> icc.reservarConsultaPaciente("pacReservado", "ConsultaReservado"));
	}

	@Test
	void testReservaFueraDeFecha() throws Exception {
		prepararEscenario("DiaVieja", "medVieja", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaVieja", "medVieja", "ConsultaVieja", UN_LUNES, 1, 10);
		crearPaciente("pacVieja", 30);

		assertThrows(ReservaFueraDeFechaException.class,
				() -> icc.reservarConsultaPaciente("pacVieja", "ConsultaVieja"),
				"Al ser la fecha de reserva posterior a la de la consulta, deberia saltar la excepcion");
	}

	@Test
	void testListarReservasPacienteVacio() {
		IControladorConsulta icc = getControlador();
		crearPaciente("pacSinReservas", 30);

		Set<DTReservaResumen> reservas = icc.listarReservasPaciente("pacSinReservas");

		assertNotNull(reservas);
		assertTrue(reservas.isEmpty());
	}

	@Test
	void testListarReservasPacienteOk() throws Exception {
		prepararEscenario("DiaListado", "medListado", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaListado", "medListado", "ConsultaListado", LocalDate.of(2200, 9, 1), 1, 10);
		crearPaciente("pacListado", 30);

		icc.reservarConsultaPaciente("pacListado", "ConsultaListado");
		boolean encontrada = false;
		Set<DTReservaResumen> reservas = icc.listarReservasPaciente("pacListado");
		for (DTReservaResumen r : reservas) {
			if ((r.getConsulta() == "ConsultaListado") && r.getNumero() == 1) {
				encontrada = true;
			}
		}
		assertEquals(1, reservas.size());
		assertTrue(encontrada);
	}

	@Test
	void testConsultarReservaOk() throws Exception {
		prepararEscenario("DiaDetalle", "medDetalle", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaDetalle", "medDetalle", "ConsultaDetalle", LocalDate.of(2200, 9, 1), 1, 10);
		crearPaciente("pacDetalle", 30);
		icc.reservarConsultaPaciente("pacDetalle", "ConsultaDetalle");
		DTReservaDet dt = icc.consultarReserva("ConsultaDetalle", 1);
		assertEquals("ConsultaDetalle", dt.getReserva().getConsulta());
		assertEquals(1, dt.getReserva().getNumero());
		assertEquals("medDetalle", dt.getMedico());
		assertEquals("CliTest", dt.getClinica());
	}

	@Test
	void seleccionarDiaConsultaOk() throws Exception {
		prepararEscenario("DiaSelOk", "medSelOk", true);
		IControladorConsulta icc = getControlador();
		icc.altaConsulta("DiaSelOk", "medSelOk", "ConsultaSelOk", LocalDate.of(2200, 9, 1), 1, 10);
		DTDia_consulta dt = icc.seleccionarDiaDeConsulta("DiaSelOk");
		assertNotNull(dt);
		assertEquals("DiaSelOk", dt.getNombre());
		assertEquals(LUNES, dt.getDiaSemana());
		assertTrue(dt.getConsultas().contains("ConsultaSelOk"));
	}

	@Test

	void testSeleccionarDiaDeConsultaInexistente() {
		IControladorConsulta icc = getControlador();
		assertThrows(DiaConsultaNoExisteException.class, () -> icc.seleccionarDiaDeConsulta("NoExiste"));
	}

	@Test
	void testListarMedicos() {
		prepararEscenario("DiaListarMed", "medListado", true);

		crearPaciente("pacNoDebeAparecer", LocalDate.of(2000, 1, 1));

		IControladorConsulta icc = getControlador();

		Set<String> medicos = icc.listarMedicos();

		assertTrue(medicos.contains("medListado"));
		assertFalse(medicos.contains("pacNoDebeAparecer"));
	}

	@Test
	void testListarDiasConsulta() {
		Especialidad esp = new Especialidad("EspListarDia", 10f);
		Clinica cli = new Clinica("CliListarDia", "desc", "dir", "web", 100f, 500f);

		Dia_consulta dia = new Dia_consulta("DiaParaListar", "descripcion", LUNES, LocalTime.of(9, 0), cli, esp);

		ManejadorDiasConsulta.getinstance().addDiaConsulta(dia);

		IControladorConsulta icc = getControlador();

		Set<String> dias = icc.listarDiasConsulta();

		assertTrue(dias.contains("DiaParaListar"));
	}

	@Test
	void testListarConsultasDiaInexistente() {
		IControladorConsulta icc = getControlador();

		Set<String> consultas = icc.listarConsultas("OtroDiaQueNoExiste");

		assertNotNull(consultas);
		assertTrue(consultas.isEmpty());
	}

	@Test
	void testConsultarDiaConsultaInexistente() {
		IControladorConsulta icc = getControlador();

		Set<String> consultas = icc.consultarDiaConsulta("DiaQueNoExiste");

		assertNotNull(consultas);
		assertTrue(consultas.isEmpty());
	}

	@Test
	void testSeleccionarDiaDeConsulta() throws Exception {
		prepararEscenario("DiaSeleccionar", "medSeleccionar", true);

		IControladorConsulta icc = getControlador();

		icc.altaConsulta("DiaSeleccionar", "medSeleccionar", "ConsultaSeleccionar", UN_LUNES, 3, 10);

		DTDia_consulta dt = icc.seleccionarDiaDeConsulta("DiaSeleccionar");

		assertNotNull(dt);
		assertEquals("DiaSeleccionar", dt.getNombre());
		assertEquals("descripcion", dt.getDescripcion());
		assertEquals(LUNES, dt.getDiaSemana());
		assertEquals(LocalTime.of(9, 0), dt.getHoraInicio());
		assertEquals(600f, dt.getCosto(), 0.01f);
		assertTrue(dt.getConsultas().contains("ConsultaSeleccionar"));
	}

	@Test
	void testReservarConsultaPacienteOk() throws Exception {
		prepararEscenario("DiaReservaOk", "medReservaOk", true);

		IControladorConsulta icc = getControlador();
		LocalDate fechaConsulta = LocalDate.of(2200, 9, 1);

		icc.altaConsulta("DiaReservaOk", "medReservaOk", "ConsultaReservaOk", fechaConsulta, 1, 5);

		crearPaciente("pacReservaOk", LocalDate.of(2000, 1, 1));

		DTReserva reserva = icc.reservarConsultaPaciente("pacReservaOk", "ConsultaReservaOk");

		assertNotNull(reserva);
		assertEquals(1, reserva.getNumero());
		assertEquals(LocalDate.now(), reserva.getFecha());
		assertEquals(660f, reserva.getCosto(), 0.01f);
	}

}
