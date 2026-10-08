package logica.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import excepciones.DiaConsultaNoExisteException;
import excepciones.FechaNoCoincideDiaException;
import excepciones.MedicoSinEspecialidadException;
import excepciones.NombreConsultaRepetidoException;
import excepciones.PacienteYaReservadoException;
import excepciones.ReservaFueraDeFechaException;
import excepciones.SinCupoException;
import excepciones.BeneficioSinOrdenesException;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDia_consulta;
import logica.datatypes.DTReserva;
import logica.datatypes.DTReservaDet;
import logica.datatypes.DTReservaResumen;

public interface IControladorConsulta {
	public abstract Set<String> consultarDiaConsulta(String nombre);

	public abstract Set<String> listarDiasConsulta();

	public abstract Set<String> listarConsultas(String dia);

	public abstract Set<DTReservaResumen> listarReservasPaciente(String paciente);

	public abstract DTReservaDet consultarReserva(String nombre_cons, int num);

	public abstract DTReserva reservarConsultaPaciente(String paciente, String consulta)
			throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException;

	public abstract boolean esValidoNombreDiaDeConsulta(String nombre);

	public abstract void registrarDiaDeConsulta(String nombre, String descripcion, int diaSemana, LocalTime horaInicio,
			String especialidad, String clinica);

	public abstract void altaConsulta(String nombreDia, String nickMedico, String nombre, LocalDate fecha, int nroSala,
			int cantNumeros)
			throws NombreConsultaRepetidoException, MedicoSinEspecialidadException, FechaNoCoincideDiaException;

	public abstract Set<String> listarMedicos();

	public abstract DTConsulta consultarConsulta(String nombreConsulta);

	public abstract DTDia_consulta seleccionarDiaDeConsulta(String nombre) throws DiaConsultaNoExisteException;

	public abstract DTReserva reservarConsultaPaciente(LocalDate fecha, String paciente, String consulta)
			throws SinCupoException, PacienteYaReservadoException;

	DTReserva reservarConsultaPaciente(String paciente, String consulta, Integer anioBenef)
			throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException, BeneficioSinOrdenesException;

}
