package excepciones;

@SuppressWarnings("serial")
public class BeneficioSinOrdenesException extends Exception {
	public BeneficioSinOrdenesException() {
		super("El beneficio correspondiente no posee ordenes de consulta disponibles");
	}
	
}
