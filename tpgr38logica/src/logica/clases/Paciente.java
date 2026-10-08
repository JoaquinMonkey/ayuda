package logica.clases;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;


public class Paciente extends Usuario {

	private LocalDate fechaNac;
	private LocalDate fechaIng;
	private int ci;
	private String antecedentes;
	private Map<Integer, BeneficioAnual> beneficiosAnuales;
	private Map<String, Reserva> reservas;

	public Paciente(String nickname, String nombre, String apellido, String mail, String contrasenia, LocalDate fechaNac,
			LocalDate fechaIng, int ci, String antecedentes) {

		super(nickname, nombre, apellido, mail, contrasenia);

		this.fechaNac = fechaNac;
		this.fechaIng = fechaIng;
		this.ci = ci;
		this.antecedentes = antecedentes;
		this.beneficiosAnuales = new HashMap<>();
		this.reservas = new HashMap<>();
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public LocalDate getFechaIng() {
		return fechaIng;
	}

	public int getCI() {
		return ci;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setFechaIng(LocalDate fechaIng) {
		this.fechaIng = fechaIng;
	}

	public void setCI(int ci) {
		this.ci = ci;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public Map<Integer, BeneficioAnual> getBeneficiosAnuales() {
		return beneficiosAnuales;
	}

	public void addBeneficioAnual(BeneficioAnual ba) {
		this.beneficiosAnuales.put(ba.getAnio(), ba);
	}

	public Map<String, Reserva> getReservas() {
		return reservas;
	}

	public int getEdad() {
		return Period.between(fechaNac, LocalDate.now()).getYears();
	}

	public void agregarReserva(Reserva rsv) {
		reservas.put(rsv.getConsulta().getNombre(), rsv);
	}

	public BeneficioAnual getBeneficio(Integer anioBenef) {
		return beneficiosAnuales.get(anioBenef);
	}
}