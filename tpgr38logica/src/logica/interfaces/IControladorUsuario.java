package logica.interfaces;

import java.time.LocalDate;
import java.util.Set;

import excepciones.EspecialidadNoExisteException;
import excepciones.UsuarioNoExisteException;
import logica.clases.Usuario;
import logica.datatypes.DTDatosUsuarioMedico;
import logica.datatypes.DTDatosUsuarioPaciente;
import logica.datatypes.DTUsuario;

public interface IControladorUsuario {
	public abstract DTUsuario[] listarUsuarios();

	public abstract DTUsuario seleccionarUsuario(String nickname) throws UsuarioNoExisteException;

	public abstract Set<String> listarPacientes();

	// retorna false si el nickname o mail ya existen, true si se puede registrar y
	// lo da de alta jeje
	public abstract boolean altaMedico(String nickname, String nombre, String apellido, String mail, String contrasenia, int num_reg,
			int mes_UL, float porcentaje_ad, String web, Set<String> especialidades);

	public abstract boolean altaPaciente(String nickname, String nombre, String apellido, String mail, String contrasenia,
			LocalDate fechaNac, int ci, LocalDate fechaIng, String antecedentes);

	public abstract DTDatosUsuarioPaciente consultarPaciente(String nickname) throws UsuarioNoExisteException;

	public abstract void modificarPaciente(String nickname, String nombre, String apellido, LocalDate fechaNacimiento,
			int ci, String antecedentesMedicos, LocalDate fechaIngreso) throws UsuarioNoExisteException;

	public abstract void modificarMedico(String nickname, String nombre, String apellido, int nroRegistro,
			String sitioWeb, Set<String> especialidades, float porcentajeAdicional, int mesLicencia)
			throws UsuarioNoExisteException, EspecialidadNoExisteException;

	public abstract DTDatosUsuarioMedico consultarMedico(String nickname) throws UsuarioNoExisteException;
	
	public abstract Usuario validarIniSesion(String nickname, String contrasenia);

	boolean altaMedico(String nickname, String nombre, String apellido, String mail, int num_reg, int mes_UL,
			float porcentaje_ad, String web, Set<String> especialidades);

	boolean altaPaciente(String nickname, String nombre, String apellido, String mail, LocalDate fechaNac, int ci,
			LocalDate fechaIng, String antecedentes);
}
