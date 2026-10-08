package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Especialidad;

public class ManejadorEspecialidad {
	private Map<String, Especialidad> especialidades;
	private static ManejadorEspecialidad instancia = null;

	private ManejadorEspecialidad() {
		especialidades = new HashMap<>();
	}

	public static ManejadorEspecialidad getinstance() {
		if (instancia == null) {
			instancia = new ManejadorEspecialidad();
		}
		return instancia;
	}

	public void crearEspecialidad(String nombre, float porcentaje_ad) {
		Especialidad e = new Especialidad(nombre, porcentaje_ad);
		especialidades.put(nombre, e);
	}

	public Map<String, Especialidad> getEspecialidades() {
		return especialidades;
	}

	public Especialidad getEspecialidad(String nombre) {
		return especialidades.get(nombre);
	}

	public void resetForTests() {
		this.especialidades.clear();
	}
}
