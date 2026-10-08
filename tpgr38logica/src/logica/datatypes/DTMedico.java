package logica.datatypes;

import java.util.Set;

public class DTMedico extends DTUsuario {

	private int numReg;
	private int mesUL;
	private float porcentajeAd;
	private String web;
	private Set<String> especialidades;

	public DTMedico(String nickname, String nombre, String apellido, String mail, int numReg, int mesUL,
			float porcentajeAd, String web, Set<String> especialidades) {

		super(nickname, nombre, apellido, mail);

		this.numReg = numReg;
		this.mesUL = mesUL;
		this.porcentajeAd = porcentajeAd;
		this.web = web;
		this.especialidades = especialidades;
	}

	public int getNumReg() {
		return numReg;
	}

	public int getMesUL() {
		return mesUL;
	}

	public float getPorcentajeAd() {
		return porcentajeAd;
	}

	public String getWeb() {
		return web;
	}

	public Set<String> getEspecialidades() {
		return especialidades;
	}
}