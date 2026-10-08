package logica.clases;

import logica.controladores.ControladorBeneficioAnual;
import logica.controladores.ControladorCargaDatos;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorConsultas;
import logica.controladores.ControladorEspecialidad;
import logica.controladores.ControladorUsuario;
import logica.interfaces.IControladorBeneficioAnual;
import logica.interfaces.IControladorCargaDatos;
import logica.interfaces.IControladorClinica;
import logica.interfaces.IControladorConsulta;
import logica.interfaces.IControladorEspecialidad;
import logica.interfaces.IControladorUsuario;

/**
 * Fábrica para la construcción de un controlador de usuarios (uno distinto para
 * cada invocación). Se implementa en base al patrón Singleton.
 *
 * @author TProg2017
 *
 */
public class Fabrica {

	private static Fabrica instancia;

	private Fabrica() {
	}

	public static Fabrica getInstance() {
		if (instancia == null) {
			instancia = new Fabrica();
		}
		return instancia;
	}

	public IControladorUsuario getIControladorUsuario() {
		return new ControladorUsuario();
	}

	public IControladorEspecialidad getIControladorEspecialidad() {
		return new ControladorEspecialidad();
	}

	public IControladorBeneficioAnual getIControladorBeneficioAnual() {
		return new ControladorBeneficioAnual();
	}

	public IControladorClinica getIControladorClinica() {
		return new ControladorClinica();
	}

	public IControladorConsulta getIControladorConsulta() {
		return new ControladorConsultas();
	}

	public IControladorCargaDatos getIControladorCargaDatos() {
		return new ControladorCargaDatos();
	}
}
