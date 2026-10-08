package logica.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import excepciones.FechaNoCoincideDiaException;
import excepciones.MedicoSinEspecialidadException;
import excepciones.NombreClinicaRepetidoException;
import excepciones.NombreConsultaRepetidoException;
import excepciones.PacienteYaReservadoException;
import excepciones.SinCupoException;
import logica.clases.Fabrica;
import logica.interfaces.IControladorBeneficioAnual;
import logica.interfaces.IControladorCargaDatos;
import logica.interfaces.IControladorClinica;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorEspecialidad;
import logica.interfaces.IControladorUsuario;

public class ControladorCargaDatos implements IControladorCargaDatos {
	private boolean ejecuto = false;

	@Override
	public void cargarDatos() {
		if (ejecuto) {
			return;
		}

		// carga de especialidades
		IControladorEspecialidad ICE = Fabrica.getInstance().getIControladorEspecialidad();
		ICE.altaEspecialidad("Cardiología", 20);
		ICE.altaEspecialidad("Pediatría", 10);
		ICE.altaEspecialidad("Medicina General", 0);

		// carga de usuarios
		IControladorUsuario ICU = Fabrica.getInstance().getIControladorUsuario();
		ICU.altaMedico("ana.perez", "Ana", "Pérez", "ana.perez@tuclinica.com", 1001, 1, 15,
				"https://www.hospitalbritanico.org.uy/", Set.of("Cardiología", "Medicina General"));
		ICU.altaMedico("bsilva", "Bruno", "Silva", "bruno.silva@hospital.com", 1002, 7, 5, "", Set.of("Pediatría"));
		ICU.altaMedico("carla.rodriguez", "Carla", "Rodríguez", "carla.rodriguez@hospital.com", 1003, 2, 10,
				"https://www.hospitalbritanico.org.uy/", Set.of("Cardiología", "Medicina General"));
		ICU.altaPaciente("lgomez", "Laura", "Gómez", "laura.gomez@gmail.com", LocalDate.of(1990, 4, 12), 45678901,
				LocalDate.of(2024, 3, 1), "Sin antecedentes relevantes.");
		ICU.altaPaciente("mar.fernandez", "Mario", "Fernández", "mario.fernandez@hotmail.com",
				LocalDate.of(1955, 11, 8), 32145678, LocalDate.of(2022, 6, 15),
				"Hipertensión arterial diagnosticada en 2018, actualmente controlada con tratamiento farmacológico. Antecedentes de colesterol elevado y episodios ocasionales de migraña. En 2022 fue intervenido quirúrgicamente por una hernia inguinal, sin complicaciones posteriores.");
		ICU.altaPaciente("s.lopez", "Sofía", "López", "sofia.lopez@gmail.com", LocalDate.of(2014, 9, 20), 58963214,
				LocalDate.of(2025, 2, 10), "Alergia a penicilina.");

		// carga de beneficios anuales
		IControladorBeneficioAnual ICBA = Fabrica.getInstance().getIControladorBeneficioAnual();
		ICBA.altaBeneficioAnual("lgomez", 2025, 3, 2);
		ICBA.altaBeneficioAnual("lgomez", 2026, 4, 3);
		ICBA.altaBeneficioAnual("mar.fernandez", 2026, 5, 4);
		ICBA.altaBeneficioAnual("s.lopez", 2026, 2, 2);

		// carga de clinicas
		IControladorClinica ICCL = Fabrica.getInstance().getIControladorClinica();
		try {
			ICCL.altaClinica("Hospital Británico", "Hospital general privado", "Av. Italia 2420",
					"https://www.hospitalbritanico.org.uy/", 350, 1200);
			ICCL.altaClinica("CASMU", "Institución de asistencia médica", "Av. 8 de Octubre 3310",
					"https://www.casmu.com.uy/", 200, 950);
		} catch (NombreClinicaRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// carga de dias de consulta (faltan costos?)
		IControladorConsulta ICCO = Fabrica.getInstance().getIControladorConsulta();
		ICCO.registrarDiaDeConsulta("Cardiología - Lunes", "Atención cardiológica semanal", 1, LocalTime.parse("09:00"),
				"Cardiología", "Hospital Británico");
		ICCO.registrarDiaDeConsulta("Pediatría - Martes", "Atención pediátrica semanal", 2, LocalTime.parse("14:00"),
				"Pediatría", "CASMU");
		ICCO.registrarDiaDeConsulta("Medicina General Central - Miércoles", "Consulta general semanal", 3,
				LocalTime.parse("10:30"), "Medicina General", "Hospital Británico");
		ICCO.registrarDiaDeConsulta("Cardiología - Jueves", "Atención cardiológica semanal", 4,
				LocalTime.parse("16:00"), "Cardiología", "CASMU");

		// carga de consultas
		try {
			ICCO.altaConsulta("Cardiología - Lunes", "ana.perez", "Cardiología 240826", LocalDate.of(2026, 8, 24), 101,
					3);
			ICCO.altaConsulta("Pediatría - Martes", "bsilva", "Pediatría 080926", LocalDate.of(2026, 9, 8), 202, 2);
			ICCO.altaConsulta("Medicina General Central - Miércoles", "carla.rodriguez", "Medicina General 090926",
					LocalDate.of(2026, 9, 9), 103, 4);
			ICCO.altaConsulta("Cardiología - Jueves", "carla.rodriguez", "Cardiología 130826",
					LocalDate.of(2026, 8, 13), 204, 2);
			ICCO.altaConsulta("Cardiología - Lunes", "ana.perez", "Cardiología 140926", LocalDate.of(2026, 9, 14), 101,
					3);
		} catch (NombreConsultaRepetidoException | MedicoSinEspecialidadException | FechaNoCoincideDiaException e) {
			e.printStackTrace();
		}

		try {
			ICCO.reservarConsultaPaciente(LocalDate.of(2026, 8, 1), "lgomez", "Cardiología 240826");
			ICCO.reservarConsultaPaciente(LocalDate.of(2026, 7, 27), "mar.fernandez", "Cardiología 240826");
			ICCO.reservarConsultaPaciente(LocalDate.of(2026, 8, 27), "s.lopez", "Pediatría 080926");
			ICCO.reservarConsultaPaciente(LocalDate.of(2026, 8, 28), "lgomez", "Pediatría 080926");
			ICCO.reservarConsultaPaciente(LocalDate.of(2026, 8, 29), "mar.fernandez", "Medicina General 090926");
		} catch (SinCupoException | PacienteYaReservadoException e) {
			e.printStackTrace();
		}
		ejecuto = true;
	}
}
