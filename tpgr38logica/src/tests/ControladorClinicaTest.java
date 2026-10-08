package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.ClinicaNoExisteException;
import excepciones.NombreClinicaRepetidoException;
import logica.clases.Clinica;
import logica.clases.Fabrica;
import logica.datatypes.DTClinica;
import logica.interfaces.IControladorClinica;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

class ControladorClinicaTest {
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
		ManejadorClinica mC = ManejadorClinica.getinstance();
		mC.resetForTests();
	}

	private IControladorClinica getControlador() {
		return Fabrica.getInstance().getIControladorClinica();
	}

	@Test
	void testAltaClinicaOk() throws NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTestOk", "Una descripcion", "Av. Siempreviva 742", "www.testok.com", 150f, 320f);

		ManejadorClinica mc = ManejadorClinica.getinstance();
		Map<String, Clinica> clinicas = mc.getClinicas();
		Clinica c = clinicas.get("ClinicaTestOk");

		assertNotNull(c, "La clinica deberia haber quedado registrada");
		assertEquals("ClinicaTestOk", c.getNombre());
		assertEquals("Una descripcion", c.getDesc());
		assertEquals("Av. Siempreviva 742", c.getDire());
		assertEquals("www.testok.com", c.getWeb());
		assertEquals(150f, c.getCostoMed());
		assertEquals(320f, c.getCostoConsulta());
	}

	@Test
	void testAltaClinicaNombreRepetido() throws NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTestRepetida", "Primera", "Direccion 1", "www.uno.com", 100f, 200f);

		NombreClinicaRepetidoException ex = assertThrows(NombreClinicaRepetidoException.class,
				() -> icc.altaClinica("ClinicaTestRepetida", "Segunda", "Direccion 2", "www.dos.com", 300f, 400f));

		assertTrue(ex.getMessage().length() > 0, "La excepcion deberia traer un mensaje");
	}

	@Test
	void testAltaClinicaNoPisaLaExistente() throws NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTestNoPisa", "Original", "Direccion original", "www.original.com", 111f, 222f);

		ManejadorClinica mc = ManejadorClinica.getinstance();
		Clinica antes = mc.getClinicas().get("ClinicaTestNoPisa");

		assertThrows(NombreClinicaRepetidoException.class,
				() -> icc.altaClinica("ClinicaTestNoPisa", "Modificada", "Otra direccion", "www.otra.com", 999f, 999f));

		Clinica despues = mc.getClinicas().get("ClinicaTestNoPisa");

		assertSame(antes, despues, "El alta repetida no debe reemplazar la clinica existente");
	}

	@Test
	void testAltaClinicaSinSitioWeb() throws NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTestSinWeb", "Sin sitio web", "Direccion 3", "", 50f, 60f);

		ManejadorClinica mc = ManejadorClinica.getinstance();
		assertNotNull(mc.getClinicas().get("ClinicaTestSinWeb"), "El sitio web es opcional");
	}

	@Test
	void listarClinicaVacio() {
		IControladorClinica icc = getControlador();
		Set<String> clinicas = icc.listarClinicas();
		assertNotNull(clinicas, "Deberia retornar un set vacio, no null");
		assertTrue(clinicas.isEmpty(), "No deberian de haber clinicas");
	}

	@Test
	void listarClinicasOk() throws NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTest", "Una clinica", "La Blanqueada", "clinicatest.com", 50f, 60f);
		icc.altaClinica("otra Clinica", "distinta", "Camino Manganga 301", "", 30f, 40f);
		Set<String> clinicas = icc.listarClinicas();
		assertEquals(2, clinicas.size(), "deberian haber dos clinicas en el listado");
		assertTrue(clinicas.contains("ClinicaTest"), "deberia estar en el listado");
		assertTrue(clinicas.contains("otra Clinica"), "tambien deberia estar");
	}

	@Test
	void consultarClinicaOk() throws ClinicaNoExisteException, NombreClinicaRepetidoException {
		IControladorClinica icc = getControlador();
		icc.altaClinica("ClinicaTest", "Una clinica", "La Blanqueada", "clinicatest.com", 50f, 60f);
		icc.altaClinica("otra Clinica", "distinta", "Camino Manganga 301", "", 30f, 40f);
		DTClinica dt = icc.consultarClinica("ClinicaTest");
		assertEquals(dt.getWeb(), "clinicatest.com");
		assertEquals(dt.getNombre(), "ClinicaTest", "Los nombres coinciden");
		assertEquals(dt.getDesc(), "Una clinica", "Las descripciones coinciden tambien");
		assertEquals(dt.getDirec(), "La Blanqueada", "Las direcciones coinciden");
		assertEquals(dt.getCostoMed(), 50f, "Los costos del ticket de medicamento coinciden");
		assertEquals(dt.getCostoConsulta(), 60f, "Los costos de la orden de consulta coinciden");
	}

	@Test
	void consultarClinicaInexistente() throws ClinicaNoExisteException {
		IControladorClinica icc = getControlador();
		assertThrows(ClinicaNoExisteException.class, () -> icc.consultarClinica("Existen't clinica"));
	}
}
