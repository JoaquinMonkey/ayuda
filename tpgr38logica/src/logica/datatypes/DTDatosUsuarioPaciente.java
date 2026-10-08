package logica.datatypes;

import java.util.Set;

public class DTDatosUsuarioPaciente {
	private String nickname;
	private String nombre;
	private String apellido;
	private String mail;
	private Set<DTReservaResumen> reservasConsultas;

	public DTDatosUsuarioPaciente(String nickname, String nombre, String apellido, String mail,
			Set<DTReservaResumen> reservasConsultas) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.reservasConsultas = reservasConsultas;
	}

	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getMail() {
		return mail;
	}

	public Set<DTReservaResumen> getReservasConsultas() {
		return reservasConsultas;
	}
}
