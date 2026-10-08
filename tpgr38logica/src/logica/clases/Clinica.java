package logica.clases;

import logica.datatypes.DTClinica;

public class Clinica {
	private String nombre;
	private String descripcion;
	private String direccion;
	private String web;
	private float costoTicketMedicamento;
	private float costoOrdenConsulta;

	public Clinica(String nombre, String descripcion, String direccion, String web, float costoTicketMedicamento,
			float costoOrdenConsulta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.web = web;
		this.costoTicketMedicamento = costoTicketMedicamento;
		this.costoOrdenConsulta = costoOrdenConsulta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDesc() {
		return this.descripcion;
	}

	public String getDire() {
		return this.direccion;
	}

	public String getWeb() {
		return this.web;
	}

	public float getCostoMed() {
		return this.costoTicketMedicamento;
	}

	public float getCostoConsulta() {
		return this.costoOrdenConsulta;
	}

	public DTClinica getDTClinica() {
		return new DTClinica(nombre, descripcion, direccion, web, costoTicketMedicamento, costoOrdenConsulta);
	}

}
