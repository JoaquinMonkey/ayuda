package logica.datatypes;

public class DTClinica {
	private String nombre;
	private String descripcion;
	private String direccion;
	private String web;
	private float costoTicketMedicamento;
	private float costoOrdenConsulta;

	public DTClinica(String nombre, String descripcion, String direccion, String web, float costomed,
			float costoconsulta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.web = web;
		this.costoTicketMedicamento = costomed;
		this.costoOrdenConsulta = costoconsulta;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDesc() {
		return descripcion;
	}

	public String getDirec() {
		return direccion;
	}

	public String getWeb() {
		return web;
	}

	public float getCostoMed() {
		return costoTicketMedicamento;
	}

	public float getCostoConsulta() {
		return costoOrdenConsulta;
	}

}
