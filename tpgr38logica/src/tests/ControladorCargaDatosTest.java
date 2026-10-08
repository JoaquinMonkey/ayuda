package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.clases.Fabrica;
import logica.interfaces.IControladorCargaDatos;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

public class ControladorCargaDatosTest {
	private static IControladorCargaDatos ccd;
	private static Fabrica f;

	@BeforeAll
	public static void iniciar() {
		ManejadorEspecialidad.getinstance().resetForTests();
		ManejadorUsuario.getinstance().resetForTests();
		ManejadorConsultas.getinstance().resetForTests();
		ManejadorDiasConsulta.getinstance().resetForTests();
		ManejadorClinica.getinstance().resetForTests();
		f = Fabrica.getInstance();
		ccd = f.getIControladorCargaDatos();
	}

	@Test
	void testCargaDatosOK() {
		ccd.cargarDatos();
		assertTrue(f.getIControladorUsuario().listarUsuarios().length == 6);
		assertTrue(f.getIControladorUsuario().listarPacientes().size() == 3);
		assertTrue(f.getIControladorEspecialidad().listarEspecialidades().size() == 3);
		assertTrue(f.getIControladorClinica().listarClinicas().size() == 2);
		assertTrue(f.getIControladorConsulta().listarDiasConsulta().size() == 4);
		Set<String> consultas = new HashSet<>();
		for (String dia : f.getIControladorConsulta().listarDiasConsulta()) {
			consultas.addAll(f.getIControladorConsulta().listarConsultas(dia));
		}
		assertTrue(consultas.size() == 5);
	}
}
