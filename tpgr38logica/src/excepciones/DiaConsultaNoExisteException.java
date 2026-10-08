package excepciones;

@SuppressWarnings("serial")
public class DiaConsultaNoExisteException extends Exception {
	public DiaConsultaNoExisteException() {
		super("El dia de consulta no existe");
	}
}
