package logica.interfaces;

import java.util.Set;

import excepciones.EspecialidadNoExisteException;
import excepciones.NombreEspecialidadRepetidoException;

public interface IControladorEspecialidad {
	public abstract void altaEspecialidad(String nombre, float porcentaje_ad)
			throws NombreEspecialidadRepetidoException;

	public abstract boolean existenEspecialidades(Set<String> especialidades) throws EspecialidadNoExisteException;

	public abstract Set<String> listarEspecialidades();
}
