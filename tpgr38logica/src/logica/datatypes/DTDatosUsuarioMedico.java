package logica.datatypes;

import java.util.Set;

public class DTDatosUsuarioMedico {
	private String nickname;
	private String nombre;
	private String apellido;
	private String mail;
	private Set<DTConsulta> consultas;

	public DTDatosUsuarioMedico(String nickname, String nombre, String apellido, String mail,
			Set<DTConsulta> consultas) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.consultas = consultas;
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

	public Set<DTConsulta> getConsultas() {
		return consultas;
	}
}
