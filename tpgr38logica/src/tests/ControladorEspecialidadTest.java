package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EspecialidadNoExisteException;
import excepciones.NombreEspecialidadRepetidoException;
import logica.clases.Especialidad;
import logica.clases.Fabrica;
import logica.interfaces.IControladorEspecialidad;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

class ControladorEspecialidadTest {

	private static IControladorEspecialidad ce;
	private static ManejadorEspecialidad me;

	@BeforeAll
	public static void iniciar() {
		ManejadorEspecialidad.getinstance().resetForTests();
		ManejadorUsuario.getinstance().resetForTests();
		ManejadorConsultas.getinstance().resetForTests();
		ManejadorDiasConsulta.getinstance().resetForTests();
		ManejadorClinica.getinstance().resetForTests();
		Fabrica fabrica = Fabrica.getInstance();
		me = ManejadorEspecialidad.getinstance();
		ce = fabrica.getIControladorEspecialidad();
	}

	@Test
	void testAltaEspecialidadOK() throws NombreEspecialidadRepetidoException {
		String ES01n = "Esp";
		float ES01p = 20;
		String ES02n = "Esp2";
		float ES02p = 10;
		String ES03n = "Esp3";
		float ES03p = 0;

		ce.altaEspecialidad(ES01n, ES01p);
		ce.altaEspecialidad(ES02n, ES02p);
		ce.altaEspecialidad(ES03n, ES03p);

		Map<String, Especialidad> especialidades = me.getEspecialidades();
		assertTrue(especialidades.containsKey(ES01n));
		assertTrue(especialidades.containsKey(ES02n));
		assertTrue(especialidades.containsKey(ES02n));

		Especialidad e1 = especialidades.get(ES01n);
		Especialidad e2 = especialidades.get(ES02n);
		Especialidad e3 = especialidades.get(ES03n);

		assertEquals(e1.getNombre(), ES01n);
		assertEquals(e1.getPorcentaje_ad(), ES01p);
		assertEquals(e2.getNombre(), ES02n);
		assertEquals(e2.getPorcentaje_ad(), ES02p);
		assertEquals(e3.getNombre(), ES03n);
		assertEquals(e3.getPorcentaje_ad(), ES03p);
	}

	@Test
	void testAltaEspecialidadNombreRepetido() throws NombreEspecialidadRepetidoException {
		try {
			ce.altaEspecialidad("esp4", 43.3f);
		} catch (NombreEspecialidadRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		assertThrows(NombreEspecialidadRepetidoException.class, () -> {
			ce.altaEspecialidad("esp4", 24.7f);
		});
	}

	@Test
	void testExistenEspecialidadesOK() throws EspecialidadNoExisteException {
		ce.altaEspecialidad("el pepe", 12);
		ce.altaEspecialidad("ete sech", 15);

		assertTrue(ce.existenEspecialidades(Set.of("el pepe", "ete sech")));
		assertTrue(ce.existenEspecialidades(Set.of("el pepe")));
		assertTrue(ce.existenEspecialidades(Set.of("ete sech")));
	}

	@Test
	void testExistenEspecialidadesNoExiste() throws EspecialidadNoExisteException {
		ce.altaEspecialidad("don ramon", 12);

		assertThrows(EspecialidadNoExisteException.class, () -> {
			ce.existenEspecialidades(Set.of("don ramon", "el pepe"));
		});
		assertThrows(EspecialidadNoExisteException.class, () -> {
			ce.existenEspecialidades(Set.of("el pepe", "don ramon"));
		});
		assertThrows(EspecialidadNoExisteException.class, () -> {
			ce.existenEspecialidades(Set.of("el pepe"));
		});
		assertThrows(EspecialidadNoExisteException.class, () -> {
			ce.existenEspecialidades(Set.of());
		});
	}

	@Test
	void testListarEspecialidadesOK() {
		ce.altaEspecialidad("hola", 12);
		ce.altaEspecialidad("chau", 15);
		Set<String> lista = ce.listarEspecialidades();
		assertTrue(lista.containsAll(Set.of("hola", "chau")));
	}
}
