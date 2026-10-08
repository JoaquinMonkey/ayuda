package logica.datatypes;

import java.time.LocalDate;

public class DTReserva {
	private LocalDate fecha;
	private int numero;
	private float costo;

	public DTReserva(LocalDate fecha, int numero, float costo) {
		this.fecha = fecha;
		this.numero = numero;
		this.costo = costo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public int getNumero() {
		return numero;
	}

	public float getCosto() {
		return costo;
	}

}
