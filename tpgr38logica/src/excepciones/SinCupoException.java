package excepciones;

@SuppressWarnings("serial")
public class SinCupoException extends Exception {
	public SinCupoException() {
		super("No hay cupos disponibles para esta consulta");
	}
}
