package logica.clases;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Dia_consulta {
	private String nombre;
	private String descripcion;
	private int dia_sem;
	private LocalTime hora_inicio;
	private Clinica clinica;
	private Map<String, Consulta> consultas;
	private Especialidad especialidad;

	public Dia_consulta(String nombre, String descripcion, int dia_sem, LocalTime hora_inicio, Clinica clinica,
			Especialidad especialidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dia_sem = dia_sem;
		this.hora_inicio = hora_inicio;
		this.clinica = clinica;
		this.especialidad = especialidad;
		this.consultas = new HashMap<>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDesc() {
		return this.descripcion;
	}

	public int getDiaSem() {
		return this.dia_sem;
	}

	public LocalTime getHoraInicio() {
		return this.hora_inicio;
	}

	public Clinica getClinica() {
		return this.clinica;
	}

	public String getNombreClinica() {
		return clinica.getNombre();
	}

	public Map<String, Consulta> getConsultas() {
		return consultas;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void addConsulta(Consulta c) {
		this.consultas.put(c.getNombre(), c);
	}

	public float getCosto() {
		return ((1 + (especialidad.getPorcentaje_ad() / 100)) * clinica.getCostoConsulta());
	}
}
