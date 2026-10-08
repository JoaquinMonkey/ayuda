package logica.datatypes;

public class DTBeneficioAnual {
	private int anio;
	private int cantMed;
	private int cantOrd;

	public DTBeneficioAnual(int anio, int cantMed, int cantOrd) {
		this.anio = anio;
		this.cantMed = cantMed;
		this.cantOrd = cantOrd;
	}

	public int getAnio() {
		return anio;
	}

	public int getCantMed() {
		return cantMed;
	}

	public int getCantOrd() {
		return cantOrd;
	}

}
