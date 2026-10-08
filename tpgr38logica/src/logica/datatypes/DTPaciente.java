package logica.datatypes;

import java.time.LocalDate;

public class DTPaciente extends DTUsuario {

	private LocalDate fechaNac;
	private LocalDate fechaIng;
	private int ci;
	private String antecedentes;

	public DTPaciente(String nickname, String nombre, String apellido, String mail, LocalDate fechaNac,
			LocalDate fechaIng, int ci, String antecedentes) {

		super(nickname, nombre, apellido, mail);

		this.fechaNac = fechaNac;
		this.fechaIng = fechaIng;
		this.ci = ci;
		this.antecedentes = antecedentes;
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
}