package logica.controladores;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import excepciones.EspecialidadNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.clases.Especialidad;
import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Reserva;
import logica.clases.Usuario;
import logica.datatypes.DTDatosUsuarioMedico;
import logica.datatypes.DTDatosUsuarioPaciente;
import logica.datatypes.DTMedico;
import logica.datatypes.DTPaciente;
import logica.datatypes.DTReservaResumen;
import logica.datatypes.DTUsuario;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorEspecialidad;
import logica.manejadores.ManejadorUsuario;

public class ControladorUsuario implements IControladorUsuario {

	public ControladorUsuario() {
	}

	@Override
	public DTUsuario[] listarUsuarios() {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Usuario[] usrs = mu.getUsuarios();
		if (usrs == null) {
			return new DTUsuario[0];
		}

		DTUsuario[] resultado = new DTUsuario[usrs.length];
		for (int i = 0; i < usrs.length; i++) {
			Usuario u = usrs[i];
			try {
				resultado[i] = seleccionarUsuario(u.getNickname());
			} catch (UsuarioNoExisteException e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	@Override
	public DTUsuario seleccionarUsuario(String nickname) throws UsuarioNoExisteException {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();

		Usuario u = mu.obtenerUsuario(nickname);

		if (u == null) {
			throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");
		}

		if (u instanceof Paciente) {

			Paciente p = (Paciente) u;

			return new DTPaciente(p.getNickname(), p.getNombre(), p.getApellido(), p.getMail(), p.getFechaNac(),
					p.getFechaIng(), p.getCI(), p.getAntecedentes());

		} else if (u instanceof Medico) {

			Medico m = (Medico) u;
			Set<String> especialidades = new HashSet<>();

			for (Especialidad e : m.getEspecialidades()) {
				especialidades.add(e.getNombre());
			}

			return new DTMedico(m.getNickname(), m.getNombre(), m.getApellido(), m.getMail(), m.getNumReg(),
					m.getMesUL(), m.getPorcentajeAd(), m.getWeb(), especialidades);
		}

		throw new UsuarioNoExisteException("Tipo de usuario desconocido");
	}

	@Override
	public Set<String> listarPacientes() {
		ManejadorUsuario mU = ManejadorUsuario.getinstance();
		Set<Paciente> usu = mU.getPacientes();
		Set<String> ret = new HashSet<>();
		for (Paciente p : usu) {
			ret.add(p.getNickname());
		}
		return ret;
	}

	@Override
	public void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNacimiento, int ci,
			String antecedentesMedicos, LocalDate fechaIngreso) throws UsuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Usuario u = mu.obtenerUsuario(nickname);

		if (u == null || !(u instanceof Paciente)) {
			throw new UsuarioNoExisteException("El paciente " + nickname + " no existe");
		}

		Paciente p = (Paciente) u;
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setFechaNac(fechaNacimiento);
		p.setCI(ci);
		p.setAntecedentes(antecedentesMedicos);
		p.setFechaIng(fechaIngreso);
	}

	@Override
	public void modificarMedico(String nickname, String nombre, String apellido, int nroRegistro, String sitioWeb,
			Set<String> especialidades, float porcentajeAdicional, int mesLicencia) throws UsuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Usuario u = mu.obtenerUsuario(nickname);

		if (u == null || !(u instanceof Medico)) {
			throw new UsuarioNoExisteException("El médico " + nickname + " no existe");
		}

		ManejadorEspecialidad me = ManejadorEspecialidad.getinstance();
		for (String nombreEspecialidad : especialidades) {
			if (me.getEspecialidad(nombreEspecialidad) == null) {
				throw new EspecialidadNoExisteException("La especialidad " + nombreEspecialidad + " no existe");
			}
		}

		Medico m = (Medico) u;
		m.setNombre(nombre);
		m.setApellido(apellido);
		m.setNumReg(nroRegistro);
		m.setWeb(sitioWeb);
		m.actualizarEspecialidades(especialidades);
		m.setPorcentajeAd(porcentajeAdicional);
		m.setMesUL(mesLicencia);
	}

	@Override
	public boolean altaMedico(String nickname, String nombre, String apellido, String mail, String contrasenia, int num_reg, int mes_UL,
			float porcentaje_ad, String web, Set<String> especialidades) {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		boolean existe = mu.existeUsuario(nickname, mail);

		if (existe) {
			return false;
		}

		Set<Especialidad> esp = new HashSet<>();
		ManejadorEspecialidad me = ManejadorEspecialidad.getinstance();
		for (String e : especialidades) {
			esp.add(me.getEspecialidad(e));
		}

		Medico med = new Medico(nickname, nombre, apellido, mail, contrasenia, num_reg, mes_UL, porcentaje_ad, web, esp);

		mu.addUsuario(med);

		return true;
	}

	@Override
	public boolean altaPaciente(String nickname, String nombre, String apellido, String mail, String contrasenia, LocalDate fechaNac,
			int ci, LocalDate fechaIng, String antecedentes) {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		boolean existe = mu.existeUsuario(nickname, mail);

		if (existe) {
			return false;
		}

		Paciente pac = new Paciente(nickname, nombre, apellido, mail,contrasenia, fechaNac, fechaIng, ci, antecedentes);

		mu.addUsuario(pac);
		return true;
	}

	@Override
	public DTDatosUsuarioPaciente consultarPaciente(String nickname) throws UsuarioNoExisteException {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();

		Usuario u = mu.obtenerUsuario(nickname);

		if (u == null || !(u instanceof Paciente)) {
			throw new UsuarioNoExisteException("No existe un paciente con nickname " + nickname);
		}

		Paciente p = (Paciente) u;

		Set<DTReservaResumen> dtReservas = new HashSet<>();
		for (Reserva r : p.getReservas().values()) {
			dtReservas.add(r.getDTResumen());
		}

		return new DTDatosUsuarioPaciente(p.getNickname(), p.getNombre(), p.getApellido(), p.getMail(), dtReservas);
	}

	@Override
	public DTDatosUsuarioMedico consultarMedico(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();

		Usuario u = mu.obtenerUsuario(nickname);

		if (u == null || !(u instanceof Medico)) {
			throw new UsuarioNoExisteException("No existe un médico con nickname " + nickname);
		}

		Medico m = (Medico) u;

		return m.getDatosMedico();
	}
	
	@Override
	public Usuario validarIniSesion(String nickname, String contrasenia) {
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Usuario u = mu.obtenerUsuario(nickname);
		if (u==null || !u.getContrasenia().equals(contrasenia)) {
				return null;
		}
		return u;
	}
	@Override
	public boolean altaMedico(String nickname, String nombre, String apellido, String mail, int num_reg, int mes_UL,
			float porcentaje_ad, String web, Set<String> especialidades) {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		boolean existe = mu.existeUsuario(nickname, mail);

		if (existe) {
			return false;
		}

		Set<Especialidad> esp = new HashSet<>();
		ManejadorEspecialidad me = ManejadorEspecialidad.getinstance();
		for (String e : especialidades) {
			esp.add(me.getEspecialidad(e));
		}

		Medico med = new Medico(nickname, nombre, apellido, mail, "12345678", num_reg, mes_UL, porcentaje_ad, web, esp);

		mu.addUsuario(med);

		return true;
	}
	@Override
	public boolean altaPaciente(String nickname, String nombre, String apellido, String mail, LocalDate fechaNac,
			int ci, LocalDate fechaIng, String antecedentes) {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		boolean existe = mu.existeUsuario(nickname, mail);

		if (existe) {
			return false;
		}

		Paciente pac = new Paciente(nickname, nombre, apellido, mail, "12345678", fechaNac, fechaIng, ci, antecedentes);

		mu.addUsuario(pac);
		return true;
	}
}
