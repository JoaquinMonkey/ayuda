package logica.datatypes;

public class DTReservaDet {
	private DTReservaResumen reserva;
	private String medico;
	private String clinica;

	public DTReservaDet(DTReservaResumen reserva, String medico, String clinica) {
		this.reserva = reserva;
		this.medico = medico;
		this.clinica = clinica;

	}

	public DTReservaResumen getReserva() {
		return this.reserva;
	}

	public String getMedico() {
		return this.medico;
	}

	public String getClinica() {
		return this.clinica;
	}

}
