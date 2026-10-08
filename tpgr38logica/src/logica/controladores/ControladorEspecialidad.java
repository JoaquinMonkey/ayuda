package logica.controladores;

import java.util.HashSet;
import java.util.Set;

import excepciones.EspecialidadNoExisteException;
import excepciones.NombreEspecialidadRepetidoException;
import logica.clases.Especialidad;
import logica.interfaces.IControladorEspecialidad;
import logica.manejadores.ManejadorEspecialidad;

public class ControladorEspecialidad implements IControladorEspecialidad {
	@Override
	public void altaEspecialidad(String nombre, float porcentaje_ad) throws NombreEspecialidadRepetidoException {
		if (!esValidoNombreEspecialidad(nombre)) {
			throw new NombreEspecialidadRepetidoException("El nombre de la especialidad ya esta en uso");
		}
		ManejadorEspecialidad.getinstance().crearEspecialidad(nombre, porcentaje_ad);
	}

	@Override
	public boolean existenEspecialidades(Set<String> especialidades) throws EspecialidadNoExisteException {
		if (especialidades.isEmpty()) {
			throw new EspecialidadNoExisteException("Debe haber al menos una especialidad.");
		}
		for (String nombre : especialidades) {
			if (esValidoNombreEspecialidad(nombre)) {
				throw new EspecialidadNoExisteException("Hay alguna especialidad invalida.");
			}
		}
		return true;
	}

	@Override
	public Set<String> listarEspecialidades() {
		return new HashSet<>(ManejadorEspecialidad.getinstance().getEspecialidades().keySet());
	}

	private boolean esValidoNombreEspecialidad(String nombre) {
		Especialidad e = ManejadorEspecialidad.getinstance().getEspecialidad(nombre);
		return (e == null);
	}
}
