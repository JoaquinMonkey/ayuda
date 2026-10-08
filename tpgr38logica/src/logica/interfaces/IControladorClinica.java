package logica.interfaces;

import java.util.Set;

import excepciones.ClinicaNoExisteException;
import excepciones.NombreClinicaRepetidoException;
import logica.datatypes.DTClinica;

public interface IControladorClinica {
	public abstract void altaClinica(String nombre, String descripcion, String direccion, String web,
			float costoTicketMedicamento, float costoOrdenConsulta) throws NombreClinicaRepetidoException;

	public abstract boolean esValidoNombreClinica(String nombre);

	public abstract Set<String> listarClinicas();

	public abstract DTClinica consultarClinica(String nombre) throws ClinicaNoExisteException;
}
