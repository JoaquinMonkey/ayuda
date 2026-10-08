package logica.datatypes;

import java.time.LocalTime;
import java.util.Set;

public class DTDia_consulta {

	private String nombre;
	private String descripcion;
	private int diaSemana;
	private LocalTime horaInicio;
	private float costo;
	private Set<String> consultas;

	public DTDia_consulta(String nombre, String descripcion, int diaSemana, LocalTime horaInicio, float costo,
			Set<String> consultas) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.costo = costo;
		this.consultas = consultas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public float getCosto() {
		return costo;
	}

	public Set<String> getConsultas() {
		return consultas;
	}
}