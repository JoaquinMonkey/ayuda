package logica.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.BeneficioSinOrdenesException;
import excepciones.DiaConsultaNoExisteException;
import excepciones.FechaNoCoincideDiaException;
import excepciones.MedicoSinEspecialidadException;
import excepciones.NombreConsultaRepetidoException;
import excepciones.PacienteYaReservadoException;
import excepciones.ReservaFueraDeFechaException;
import excepciones.SinCupoException;
import logica.clases.BeneficioAnual;
import logica.clases.Clinica;
import logica.clases.Consulta;
import logica.clases.Dia_consulta;
import logica.clases.Especialidad;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Reserva;
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

public class ControladorConsultas implements IControladorConsulta {

	@Override
	public Set<String> consultarDiaConsulta(String nombre) {
		Set<String> ret = new HashSet<>();
		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		Dia_consulta dc = mD.getDiaConsulta(nombre);
		if (dc == null) {
			return ret;
		}
		Map<String, Consulta> cons = dc.getConsultas();
		if (cons != null) {
			for (Consulta c : cons.values()) {
				ret.add(c.getNombre());
			}
		}
		return ret;
	}

	@Override
	public Set<String> listarDiasConsulta() {
		Set<String> ret = new HashSet<>();
		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		Set<Dia_consulta> dC = mD.getDiasConsulta();
		for (Dia_consulta dia : dC) {
			ret.add(dia.getNombre());
		}
		return ret;
	}

	@Override
	public DTReserva reservarConsultaPaciente(String paciente, String consulta)
			throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		ManejadorUsuario mU = ManejadorUsuario.getinstance();
		DTReserva dtr = null;
		Consulta c = mC.getConsulta(consulta);
		Paciente p = mU.getPaciente(paciente);
		if (c != null && p != null) {
			Reserva r = c.reservarPaciente(p);
			int numero = r.getNumero();
			LocalDate fecha = r.getFecha();
			float costo = r.getCosto();
			dtr = new DTReserva(fecha, numero, costo);
		}
		return dtr;
	}

	@Override
	public Set<String> listarConsultas(String dia) {
		Set<String> ret = new HashSet<>();
		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		Dia_consulta dc = mD.getDiaConsulta(dia);
		if (dc == null) {
			return ret;
		}
		for (Consulta c : dc.getConsultas().values()) {
			ret.add(c.getNombre());
		}
		return ret;
	}

	@Override
	public Set<DTReservaResumen> listarReservasPaciente(String paciente) {
		ManejadorUsuario mU = ManejadorUsuario.getinstance();
		Paciente p = mU.getPaciente(paciente);
		Set<DTReservaResumen> ret = new HashSet<>();
		if (p != null) {
			Map<String, Reserva> res = p.getReservas();
			for (Reserva r : res.values()) {
				ret.add(r.getDTResumen());
			}
		}
		return ret;
	}

	@Override
	public DTReservaDet consultarReserva(String nombre_cons, int num) {
		DTReservaDet ret = null;
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		Consulta c = mC.getConsulta(nombre_cons);
		if (c != null) {
			String med = c.getNickMedico();
			String cli = c.getNombreClinica();
			c.getNombre();
			Map<String, Reserva> res = c.getReservas();
			DTReservaResumen dtr = null;
			for (Reserva r : res.values()) {
				if (r.getNumero() == num) {
					dtr = r.getDTResumen();
					break;
				}
			}
			ret = new DTReservaDet(dtr, med, cli);
		}
		return ret;
	}

	@Override
	public boolean esValidoNombreDiaDeConsulta(String nombre) {
		ManejadorDiasConsulta mdc = ManejadorDiasConsulta.getinstance();
		return !mdc.existeDiaConsulta(nombre);
	}

	@Override
	public void registrarDiaDeConsulta(String nombre, String descripcion, int diaSemana, LocalTime horaInicio,
			String especialidad, String clinica) {

		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		ManejadorEspecialidad mE = ManejadorEspecialidad.getinstance();
		ManejadorClinica mC = ManejadorClinica.getinstance();

		Especialidad e = mE.getEspecialidad(especialidad);
		Clinica c = mC.getClinica(clinica);

		Dia_consulta dc = new Dia_consulta(nombre, descripcion, diaSemana, horaInicio, c, e);
		mD.addDiaConsulta(dc);
	}

	@Override
	public Set<String> listarMedicos() {
		Set<String> ret = new HashSet<>();
		ManejadorUsuario mU = ManejadorUsuario.getinstance();
		for (Medico m : mU.getMedicos()) {
			ret.add(m.getNickname());
		}
		return ret;
	}

	@Override
	public void altaConsulta(String nombreDia, String nickMedico, String nombre, LocalDate fecha, int nroSala,
			int cantNumeros)
			throws NombreConsultaRepetidoException, MedicoSinEspecialidadException, FechaNoCoincideDiaException {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		ManejadorUsuario mU = ManejadorUsuario.getinstance();

		Dia_consulta dia = mD.getDiaConsulta(nombreDia);
		Medico medico = mU.getMedico(nickMedico);

		if (mC.existeConsulta(nombre)) {
			throw new NombreConsultaRepetidoException("Ya existe una consulta con el nombre '" + nombre + "'.");
		}

		Especialidad espDia = dia.getEspecialidad();
		boolean tiene = false;
		for (Especialidad e : medico.getEspecialidades()) {
			if (e.getNombre().equals(espDia.getNombre())) {
				tiene = true;
				break;
			}
		}
		if (!tiene) {
			throw new MedicoSinEspecialidadException(
					"El medico no tiene la especialidad '" + espDia.getNombre() + "' del dia de consulta.");
		}

		if (fecha.getDayOfWeek().getValue() != dia.getDiaSem()) {
			throw new FechaNoCoincideDiaException(
					"La fecha " + fecha + " no cae en el dia de la semana del dia de consulta.");
		}

		Consulta c = new Consulta(nombre, fecha, nroSala, cantNumeros, medico, dia);
		mC.addConsulta(c);
		dia.addConsulta(c);
		medico.agregarConsulta(c);
	}

	@Override
	public DTConsulta consultarConsulta(String nombreConsulta) {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		Consulta c = mC.getConsulta(nombreConsulta);
		if (c == null) {
			return null;
		}
		Set<Integer> nums = new HashSet<>();
		for (Reserva r : c.getReservas().values()) {
			nums.add(r.getNumero());
		}
		return new DTConsulta(c.getNombre(), c.getFecha(), c.getNickMedico(), c.getNroSala(), c.getNrosDisp(),
				c.getCosto(), nums);
	}

	@Override
	public DTDia_consulta seleccionarDiaDeConsulta(String nombre) throws DiaConsultaNoExisteException {
		ManejadorDiasConsulta mD = ManejadorDiasConsulta.getinstance();
		Dia_consulta dc = mD.getDiaConsulta(nombre);
		if (dc == null) {
			throw new DiaConsultaNoExisteException();
		}
		Set<String> consultas = consultarDiaConsulta(nombre);
		return new DTDia_consulta(dc.getNombre(), dc.getDesc(), dc.getDiaSem(), dc.getHoraInicio(), dc.getCosto(),
				consultas);
	}

	// sobrecargada para la carga de datos
	@Override
	public DTReserva reservarConsultaPaciente(LocalDate fecha, String paciente, String consulta)
			throws SinCupoException, PacienteYaReservadoException {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		ManejadorUsuario mU = ManejadorUsuario.getinstance();

		Consulta c = mC.getConsulta(consulta);
		Paciente p = mU.getPaciente(paciente);

		Reserva r = c.reservarPaciente(p, fecha);
		return new DTReserva(r.getFecha(), r.getNumero(), r.getCosto());
	}
	//sobrecargado para la web (poder usar beneficios)
	@Override
	public DTReserva reservarConsultaPaciente(String paciente, String consulta, Integer anioBenef)
			throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException, BeneficioSinOrdenesException {
		ManejadorConsultas mC = ManejadorConsultas.getinstance();
		ManejadorUsuario mU = ManejadorUsuario.getinstance();
		DTReserva dtr = null;
		Consulta c = mC.getConsulta(consulta);
		Paciente p = mU.getPaciente(paciente);
		if (c != null && p != null) {
			BeneficioAnual benef = null;
			if (anioBenef != null)
				benef = p.getBeneficio(anioBenef);
			Reserva r = c.reservarPaciente(p, benef);
			int numero = r.getNumero();
			LocalDate fecha = r.getFecha();
			float costo = r.getCosto();
			dtr = new DTReserva(fecha, numero, costo);
		}
		return dtr;
	}

}
