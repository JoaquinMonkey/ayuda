package logica.clases;

/**
 * Representa a un usuario en el sistema con nombre, apellido y cédula de
 * identidad.
 *
 * @author TProg2017
 *
 */
public abstract class Usuario {

	private String nickname;
	private String nombre;
	private String apellido;
	private String mail;
	private String contrasenia;

	public Usuario(String nickname, String nombre, String apellido, String mail, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.contrasenia = contrasenia;
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
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}	