package logica.datatypes;

/**
 * Datatype para transportar la información de un usuario entre capa lógica y de
 * presentación.
 */
public class DTUsuario {

	private String nickname;
	private String nombre;
	private String apellido;
	private String mail;

	public DTUsuario(String nickname, String nombre, String apellido, String mail) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
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

	@Override
	public String toString() {
		return getNickname() + " (" + getNombre() + " " + getApellido() + ")";
	}
}