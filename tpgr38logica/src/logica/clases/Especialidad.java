package logica.clases;

public class Especialidad {
	private String nombre;
	private float porcentaje_ad;

	public Especialidad(String nombre, float porcentaje_ad) {
		this.nombre = nombre;
		this.porcentaje_ad = porcentaje_ad;
	}

	public String getNombre() {
		return nombre;
	}

	public float getPorcentaje_ad() {
		return porcentaje_ad;
	}
}
