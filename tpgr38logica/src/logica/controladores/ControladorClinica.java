package logica.controladores;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.ClinicaNoExisteException;
import excepciones.NombreClinicaRepetidoException;
import logica.clases.Clinica;
import logica.datatypes.DTClinica;
import logica.interfaces.IControladorClinica;
import logica.manejadores.ManejadorClinica;

public class ControladorClinica implements IControladorClinica {

	@Override
	public void altaClinica(String nombre, String descripcion, String direccion, String web,
			float costoTicketMedicamento, float costoOrdenConsulta) throws NombreClinicaRepetidoException {
		if (!esValidoNombreClinica(nombre)) {
			throw new NombreClinicaRepetidoException("El nombre de la clínica ya existe");
		}
		ManejadorClinica mc = ManejadorClinica.getinstance();
		mc.crearClinica(nombre, descripcion, direccion, web, costoTicketMedicamento, costoOrdenConsulta);
	}

	@Override
	public boolean esValidoNombreClinica(String nombre) {
		boolean esValido = false;

		ManejadorClinica mc = ManejadorClinica.getinstance();
		Map<String, Clinica> sc = mc.getClinicas();
		Clinica c = sc.get(nombre);
		if (c == null) {
			esValido = true;
		}

		return esValido;
	}

	@Override
	public Set<String> listarClinicas() {
		ManejadorClinica mC = ManejadorClinica.getinstance();
		Set<String> ret = new HashSet<>();
		Map<String, Clinica> cs = mC.getClinicas();
		for (Clinica c : cs.values()) {
			ret.add(c.getNombre());
		}
		return ret;

	}

	@Override
	public DTClinica consultarClinica(String nombre) throws ClinicaNoExisteException {
		ManejadorClinica mC = ManejadorClinica.getinstance();
		Clinica c = mC.getClinica(nombre);
		if (c == null) {
			throw new ClinicaNoExisteException("No existe una clinica registrada con ese nombre");
		}
		DTClinica dt = c.getDTClinica();
		return dt;
	}
}