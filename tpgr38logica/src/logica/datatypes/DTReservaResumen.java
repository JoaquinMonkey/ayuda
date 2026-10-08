package logica.datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DTReservaResumen {
	private String consulta;
	private LocalDate fecha;
	private int numero;
	private float costo;

	public DTReservaResumen(String consulta, LocalDate fecha, int numero, float costo) {
		this.consulta = consulta;
		this.fecha = fecha;
		this.numero = numero;
		this.costo = costo;
	}

	public String getConsulta() {
		return this.consulta;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public int getNumero() {
		return this.numero;
	}

	public float getCosto() {
		return costo;
	}

	@Override
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.getConsulta() + " - Nro " + this.getNumero() + " - " + this.getFecha().format(formato);
	}
}
