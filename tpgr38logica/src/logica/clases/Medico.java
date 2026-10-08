package logica.clases;

import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTConsulta;
import logica.datatypes.DTDatosUsuarioMedico;
import logica.manejadores.ManejadorEspecialidad;

public class Medico extends Usuario {

	private int numReg;
	private int mesUL;
	private float porcentajeAd;
	private String web;
	private Set<Especialidad> especialidades;
	private Set<Consulta> consultas;

	public Medico(String nickname, String nombre, String apellido, String mail, String contrasenia, int numReg, int mesUL,
			float porcentajeAd, String web, Set<Especialidad> especialidades) {

		super(nickname, nombre, apellido, mail, contrasenia);

		this.numReg = numReg;
		this.mesUL = mesUL;
		this.porcentajeAd = porcentajeAd;
		this.web = web;
		this.especialidades = especialidades;
		this.consultas = new HashSet<>();
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

	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setNumReg(int numReg) {
		this.numReg = numReg;
	}

	public void setMesUL(int mesUL) {
		this.mesUL = mesUL;
	}

	public void setPorcentajeAd(float porcentajeAd) {
		this.porcentajeAd = porcentajeAd;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public void quitarEspecialidadesActuales() {
		especialidades.clear();
	}

	public void agregarEspecialidad(Especialidad especialidad) {
		especialidades.add(especialidad);
	}

	public void actualizarEspecialidades(Set<String> nuevasEspecialidades) {
		quitarEspecialidadesActuales();

		ManejadorEspecialidad me = ManejadorEspecialidad.getinstance();

		for (String nombreEspecialidad : nuevasEspecialidades) {
			Especialidad especialidad = me.getEspecialidad(nombreEspecialidad);
			agregarEspecialidad(especialidad);
		}
	}

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void agregarConsulta(Consulta c) {
		consultas.add(c);
	}

	public DTDatosUsuarioMedico getDatosMedico() {
		Set<DTConsulta> dtConsultas = new HashSet<>();

		for (Consulta c : consultas) {
			dtConsultas.add(c.getDTConsulta());
		}

		return new DTDatosUsuarioMedico(getNickname(), getNombre(), getApellido(), getMail(), dtConsultas);
	}
}
