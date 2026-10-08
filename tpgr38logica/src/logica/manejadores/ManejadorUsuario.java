package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.clases.Medico;
import logica.clases.Paciente;
import logica.clases.Usuario;

public class ManejadorUsuario {
	private Map<String, Usuario> usuariosPorMail;
	private Map<String, Usuario> usuariosPorNick;
	private static ManejadorUsuario instancia = null;

	private ManejadorUsuario() {
		usuariosPorMail = new HashMap<>();
		usuariosPorNick = new HashMap<>();
	}

	public static ManejadorUsuario getinstance() {
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}

	public void addUsuario(Usuario usu) {
		usuariosPorNick.put(usu.getNickname(), usu);
		usuariosPorMail.put(usu.getMail(), usu);
	}

	public Usuario obtenerUsuario(String nickname) {
		return usuariosPorNick.get(nickname);
	}

	public Paciente getPaciente(String paciente) {
		Usuario u = usuariosPorNick.get(paciente);
		if (u instanceof Paciente p) {
			return p;
		}
		return null;
	}

	public Usuario[] getUsuarios() {
		if (usuariosPorNick.isEmpty()) {
			return null;
		} else {
			Collection<Usuario> usrs = usuariosPorNick.values();
			Object[] o = usrs.toArray();
			Usuario[] res = new Usuario[o.length];
			for (int i = 0; i < o.length; i++) {
				res[i] = (Usuario) o[i];
			}

			return res;
		}

	}

	public Set<Paciente> getPacientes() {
		Collection<Usuario> usu = usuariosPorNick.values();
		Set<Paciente> pacientes = new HashSet<>();
		for (Usuario u : usu) {
			if (u instanceof Paciente p) {
				pacientes.add(p);
			}
		}

		return pacientes;

	}

	public boolean existeUsuario(String nickname, String mail) {
		Usuario u = usuariosPorNick.get(nickname);
		Usuario m = usuariosPorMail.get(mail);
		return u != null || m != null;
	}

	public Set<Medico> getMedicos() {
		Set<Medico> medicos = new HashSet<>();
		for (Usuario u : usuariosPorNick.values()) {
			if (u instanceof Medico m) {
				medicos.add(m);
			}
		}
		return medicos;
	}

	public Medico getMedico(String nickname) {
		Usuario u = usuariosPorNick.get(nickname);
		if (u instanceof Medico m) {
			return m;
		}
		return null;
	}

	public void resetForTests() {
		this.usuariosPorMail.clear();
		this.usuariosPorNick.clear();
	}
}
