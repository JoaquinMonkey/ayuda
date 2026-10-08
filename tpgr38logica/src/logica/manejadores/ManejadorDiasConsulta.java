package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.clases.Dia_consulta;

public class ManejadorDiasConsulta {
	private Map<String, Dia_consulta> diasConsulta;
	private static ManejadorDiasConsulta instancia = null;

	private ManejadorDiasConsulta() {
		diasConsulta = new HashMap<>();
	}

	public static ManejadorDiasConsulta getinstance() {
		if (instancia == null) {
			instancia = new ManejadorDiasConsulta();
		}
		return instancia;
	}

	public void addDiaConsulta(Dia_consulta dc) {
		diasConsulta.put(dc.getNombre(), dc);
	}

	public boolean existeDiaConsulta(String nombre) {
		return diasConsulta.get(nombre) != null;
	}

	public Dia_consulta getDiaConsulta(String nombre) {
		return diasConsulta.get(nombre);
	}

	public Set<Dia_consulta> getDiasConsulta() {
		Collection<Dia_consulta> dias = diasConsulta.values();
		return new HashSet<>(dias);
	}

	public void resetForTests() {
		this.diasConsulta.clear();
	}
}
