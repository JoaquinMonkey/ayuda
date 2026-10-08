package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Consulta;

public class ManejadorConsultas {
	private Map<String, Consulta> consultas;
	private static ManejadorConsultas instancia = null;

	private ManejadorConsultas() {
		consultas = new HashMap<>();
	}

	public static ManejadorConsultas getinstance() {
		if (instancia == null) {
			instancia = new ManejadorConsultas();
		}
		return instancia;
	}

	public Consulta getConsulta(String consulta) {
		return consultas.get(consulta);
	}

	public boolean existeConsulta(String nombre) {
		return consultas.containsKey(nombre);
	}

	public void addConsulta(Consulta c) {
		consultas.put(c.getNombre(), c);
	}

	public void resetForTests() {
		this.consultas.clear();
	}
}
