package logica.controladores;

import java.util.Map;
import java.util.Set;

import excepciones.AnioBeneficioAnualRepetidoException;
import logica.clases.BeneficioAnual;
import logica.clases.Paciente;
import logica.datatypes.DTBeneficioAnual;
import logica.interfaces.IControladorBeneficioAnual;
import logica.manejadores.ManejadorUsuario;

public class ControladorBeneficioAnual implements IControladorBeneficioAnual {
	@Override
	public void altaBeneficioAnual(String nicknamePaciente, int anio, int cantMed, int cantOrd)
			throws AnioBeneficioAnualRepetidoException {
		if (!esValidoAnioBeneficioAnual(nicknamePaciente, anio)) {
			throw new AnioBeneficioAnualRepetidoException("Ya existe un beneficio anual para el paciente en este año");
		}

		BeneficioAnual beneficioAnual = new BeneficioAnual(anio, cantMed, cantOrd);
		Paciente paciente = ManejadorUsuario.getinstance().getPaciente(nicknamePaciente);
		paciente.addBeneficioAnual(beneficioAnual);
	}

	@Override
	public Set<Integer> listarBeneficiosAnuales(String nicknamePaciente) {
		Paciente paciente = ManejadorUsuario.getinstance().getPaciente(nicknamePaciente);
		Map<Integer, BeneficioAnual> beneficiosAnuales = paciente.getBeneficiosAnuales();
		return beneficiosAnuales.keySet();
	}

	@Override
	public DTBeneficioAnual consultaBeneficioAnual(String nicknamePaciente, int anio) {
		Paciente paciente = ManejadorUsuario.getinstance().getPaciente(nicknamePaciente);
		Map<Integer, BeneficioAnual> beneficiosAnuales = paciente.getBeneficiosAnuales();
		BeneficioAnual beneficioAnual = beneficiosAnuales.get(anio);
		return beneficioAnual.getDTBeneficioAnual();
	}

	private boolean esValidoAnioBeneficioAnual(String nicknamePaciente, int anio) {
		Paciente paciente = ManejadorUsuario.getinstance().getPaciente(nicknamePaciente);
		Map<Integer, BeneficioAnual> beneficiosAnuales = paciente.getBeneficiosAnuales();
		return !beneficiosAnuales.containsKey(anio);
	}

}
