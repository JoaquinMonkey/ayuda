package logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

public class DTConsulta {
	private String nombre;
	private LocalDate fecha;
	private String medico;
	private int nroSala;
	private int nrosDisp;
	private float costo;
	private Set<Integer> reservas;

	public DTConsulta(String nombre, LocalDate fecha, String medico, int nroSala, int nrosDisp, float costo,
			Set<Integer> reservas) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.medico = medico;
		this.nroSala = nroSala;
		this.nrosDisp = nrosDisp;
		this.costo = costo;
		this.reservas = reservas;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getMedico() {
		return medico;
	}

	public int getNroSala() {
		return nroSala;
	}

	public int getNrosDisp() {
		return nrosDisp;
	}

	public float getCosto() {
		return costo;
	}

	public Set<Integer> getReservas() {
		return reservas;
	}

	@Override
	public String toString() {
		return nombre + " - " + fecha.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}