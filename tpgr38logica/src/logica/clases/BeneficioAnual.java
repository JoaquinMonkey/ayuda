package logica.clases;

import logica.datatypes.DTBeneficioAnual;

public class BeneficioAnual {
	private int anio;
	private int cantMed;
	private int cantOrd;

	public BeneficioAnual(int anio, int cantMed, int cantOrd) {
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

	public DTBeneficioAnual getDTBeneficioAnual() {
		return new DTBeneficioAnual(this.anio, this.cantMed, this.cantOrd);
	}

	public void descontarUso() {
		cantOrd--;
	}
}
