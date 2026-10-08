package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.AnioBeneficioAnualRepetidoException;
import logica.clases.BeneficioAnual;
import logica.clases.Fabrica;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.datatypes.DTBeneficioAnual;
import logica.interfaces.IControladorBeneficioAnual;
import logica.manejadores.ManejadorClinica;
import logica.manejadores.ManejadorConsultas;
import logica.manejadores.ManejadorDiasConsulta;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

class ControladorBeneficioAnualTest {

	private static IControladorBeneficioAnual cba;
	private static ManejadorUsuario mu;
	private static String nickU4;
	private static String nickU5;
	private static String nickU6;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		ManejadorEspecialidad.getinstance().resetForTests();
		ManejadorUsuario.getinstance().resetForTests();
		ManejadorConsultas.getinstance().resetForTests();
		ManejadorDiasConsulta.getinstance().resetForTests();
		ManejadorClinica.getinstance().resetForTests();
		mu = ManejadorUsuario.getinstance();
		cba = fabrica.getIControladorBeneficioAnual();

		nickU4 = "lgomez";
		nickU5 = "mar.fernandez";
		nickU6 = "s.lopez";

		mu.addUsuario(new Medico("ana.perez", "Ana", "Pérez", "ana.perez@tuclinica.com","123", 1001, 1, 15,
				"https://www.hospitalbritanico.org.uy/", Set.of()));
		mu.addUsuario(new Medico("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com","123", 1002, 7, 5, "", Set.of()));
		mu.addUsuario(new Medico("carla.rodriguez", "Carla", "Rodríguez", "carla.rodriguez@hospital.com","123", 1003, 2, 10,
				"https://www.hospitalbritanico.org.uy/", Set.of()));
		mu.addUsuario(new Paciente("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com", "123", LocalDate.of(1990, 4, 12),
				LocalDate.of(2024, 3, 1), 45678901, "Sin antecedentes relevantes."));
		mu.addUsuario(new Paciente("mar.fernandez", "Mario", "Fernández", "mario.fernandez@hotmail.com", "123",
				LocalDate.of(1955, 11, 8), LocalDate.of(2022, 6, 15), 32145678,
				"Hipertensión arterial diagnosticada en 2018, actualmente controlada con tratamiento farmacológico. Antecedentes de colesterol elevado y episodios ocasionales de migraña. En 2022 fue intervenido quirúrgicamente por una hernia inguinal, sin complicaciones posteriores."));
		mu.addUsuario(new Paciente("s.lopez", "Sofía", "López", "sofia.lopez@gmail.com", "123", LocalDate.of(2014, 9, 20),
				LocalDate.of(2025, 2, 10), 58963214, "Alergia a penicilina."));

		mu.addUsuario(new Paciente("juanceto01", "juanceto", "01", "juanceto01@gmail.com", "123",LocalDate.now(),
				LocalDate.now(), 12345678, "Auris de redacted."));
		mu.addUsuario(new Paciente("alonso", "alonso", "mazza", "alonsomazza@gmail.com", "123", LocalDate.now(),
				LocalDate.now(), 77777777, "Yo no voy a decirlo."));
		mu.addUsuario(new Paciente("ete sech", "ete", "sech", "ete.sech@gmail.com", "123", LocalDate.now(), LocalDate.now(),
				23456789, "Alergia a el pepe."));
	}

	@Test
	void testAltaBeneficioAnualOK() {
		cba.altaBeneficioAnual(nickU4, 2025, 3, 2);
		cba.altaBeneficioAnual(nickU4, 2026, 4, 3);
		cba.altaBeneficioAnual(nickU5, 2026, 5, 4);
		cba.altaBeneficioAnual(nickU6, 2026, 2, 2);

		Map<Integer, BeneficioAnual> B04 = mu.getPaciente(nickU4).getBeneficiosAnuales();
		Map<Integer, BeneficioAnual> B05 = mu.getPaciente(nickU5).getBeneficiosAnuales();
		Map<Integer, BeneficioAnual> B06 = mu.getPaciente(nickU6).getBeneficiosAnuales();
		assertTrue(B04.containsKey(2025));
		assertTrue(B04.containsKey(2026));
		assertTrue(B05.containsKey(2026));
		assertTrue(B06.containsKey(2026));

		BeneficioAnual BA01 = B04.get(2025);
		BeneficioAnual BA02 = B04.get(2026);
		BeneficioAnual BA03 = B05.get(2026);
		BeneficioAnual BA04 = B06.get(2026);

		assertEquals(BA01.getAnio(), 2025);
		assertEquals(BA01.getCantMed(), 3);
		assertEquals(BA01.getCantOrd(), 2);
		assertEquals(BA02.getAnio(), 2026);
		assertEquals(BA02.getCantMed(), 4);
		assertEquals(BA02.getCantOrd(), 3);
		assertEquals(BA03.getAnio(), 2026);
		assertEquals(BA03.getCantMed(), 5);
		assertEquals(BA03.getCantOrd(), 4);
		assertEquals(BA04.getAnio(), 2026);
		assertEquals(BA04.getCantMed(), 2);
		assertEquals(BA04.getCantOrd(), 2);
	}

	@Test
	void testAltaBeneficioAnualAnioRepetido() {
		try {
			cba.altaBeneficioAnual("juanceto01", 2048, 15, 24);
		} catch (AnioBeneficioAnualRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		assertThrows(AnioBeneficioAnualRepetidoException.class, () -> {
			cba.altaBeneficioAnual("juanceto01", 2048, 123, 23);
		});
	}

	@Test
	void testListarBeneficiosAnualesOK() {
		cba.altaBeneficioAnual("alonso", 2011, 123, 23);
		cba.altaBeneficioAnual("alonso", 2013, 12, 42);
		cba.altaBeneficioAnual("alonso", 2017, 1, 3);

		Set<Integer> lista = cba.listarBeneficiosAnuales("alonso");
		assertEquals(lista.size(), 3);
		assertTrue(lista.containsAll(Set.of(2011, 2013, 2017)));
	}

	@Test
	void testConsultaBeneficioAnualOK() {
		cba.altaBeneficioAnual("ete sech", 2014, 123, 23);
		cba.altaBeneficioAnual("ete sech", 2016, 14, 73);
		cba.altaBeneficioAnual("ete sech", 2017, 1, 3);

		DTBeneficioAnual dtba = cba.consultaBeneficioAnual("ete sech", 2016);
		assertEquals(dtba.getAnio(), 2016);
		assertEquals(dtba.getCantMed(), 14);
		assertEquals(dtba.getCantOrd(), 73);
	}

}
