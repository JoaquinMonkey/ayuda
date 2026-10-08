package logica.clases;

import java.time.LocalDate;

import logica.datatypes.DTReservaResumen;

public class Reserva {
	private LocalDate fecha;
	private int numero;
	private float costo;
	private Paciente paciente;
	private Consulta consulta;
	private BeneficioAnual beneficio_usado;
	public Reserva(LocalDate fecha, int numero, float costo, Paciente p, Consulta c, BeneficioAnual ben) {
		this.fecha = fecha;
		this.numero = numero;
		this.costo = costo;
		this.paciente = p;
		this.consulta = c;
		this.beneficio_usado = ben;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public int getNumero() {
		return this.numero;
	}

	public float getCosto() {
		return this.costo;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public Consulta getConsulta() {
		return this.consulta;
	}

	public DTReservaResumen getDTResumen() {
		DTReservaResumen dtr = new DTReservaResumen(this.consulta.getNombre(), this.fecha, this.numero, this.costo);
		return dtr;
	}
	public BeneficioAnual getBeneficio() {
		return beneficio_usado;
	}
}
