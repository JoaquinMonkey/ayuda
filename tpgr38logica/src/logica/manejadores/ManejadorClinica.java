package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Clinica;

public class ManejadorClinica {
	private Map<String, Clinica> clinicas;
	private static ManejadorClinica instancia = null;

	private ManejadorClinica() {
		clinicas = new HashMap<>();
	}

	public static ManejadorClinica getinstance() {
		if (instancia == null) {
			instancia = new ManejadorClinica();
		}
		return instancia;
	}

	public void crearClinica(String nombre, String descripcion, String direccion, String web,
			float costoTicketMedicamento, float costoOrdenConsulta) {
		Clinica c = new Clinica(nombre, descripcion, direccion, web, costoTicketMedicamento, costoOrdenConsulta);
		clinicas.put(nombre, c);
	}

	public Map<String, Clinica> getClinicas() {
		return clinicas;
	}

	public Clinica getClinica(String nombre) {
		return clinicas.get(nombre);
	}

	public void resetForTests() {
		this.clinicas.clear();
	}
}