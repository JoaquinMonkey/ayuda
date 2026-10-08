package logica.clases;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.BeneficioSinOrdenesException;
import excepciones.PacienteYaReservadoException;
import excepciones.ReservaFueraDeFechaException;
import excepciones.SinCupoException;
import logica.datatypes.DTConsulta;
import logica.datatypes.EstadoConsulta;

public class Consulta {
	private String nombre;
	private LocalDate fecha;
	private EstadoConsulta estado;
	private int nro_sala;
	private int nros_disp;
	private Map<String, Reserva> reservas;
	private Medico medico;
	private Dia_consulta diaConsulta;

	public Consulta(String nombre, LocalDate fecha, int nro_sala, int nros_disp) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.nro_sala = nro_sala;
		this.nros_disp = nros_disp;
		this.reservas = new HashMap<>();
		this.estado = EstadoConsulta.INGRESADA;
	}

	public Consulta(String nombre, LocalDate fecha, int nro_sala, int nros_disp, Medico medico,
			Dia_consulta diaConsulta) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.nro_sala = nro_sala;
		this.nros_disp = nros_disp;
		this.reservas = new HashMap<>();
		this.medico = medico;
		this.diaConsulta = diaConsulta;
		this.estado = EstadoConsulta.INGRESADA;
	}

	public String getNombre() {
		return this.nombre;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public int getNroSala() {
		return this.nro_sala;
	}

	public int getNrosDisp() {
		return this.nros_disp;
	}
	public EstadoConsulta getEstado(){
		return this.estado;
	}

	public float getCosto() {
		return ((1 + (medico.getPorcentajeAd() / 100)) * diaConsulta.getCosto());
	}

	public Map<String, Reserva> getReservas() {
		return this.reservas;
	}

	public String getNickMedico() {
		return medico.getNickname();
	}

	public String getNombreClinica() {
		return diaConsulta.getNombreClinica();
	}

	public boolean hayCupo() {
		return reservas.size() < nros_disp;
	}

	public boolean existeReserva(Paciente p) {
		return reservas.containsKey(p.getNickname());
	}

	public int getNumeroAsignado() {
		Set<Integer> usados = new HashSet<>();
		for (Reserva r : reservas.values()) {
			usados.add(r.getNumero());
		}
		int num = 1;
		while (usados.contains(num)) {
			num++;
		}
		return num;
	}

	public float calcularCostoRes(int edad) {
		if (edad > 60) {
			return (float) (getCosto() * 0.8);
		}
		return getCosto();

	}

	public Reserva reservarPaciente(Paciente p)
			throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException {
		if (this.fecha.isBefore(LocalDate.now())) {
			throw new ReservaFueraDeFechaException();
		}
		boolean h = hayCupo();
		if (!h) {
			throw new SinCupoException();
		}
		boolean ex = existeReserva(p);
		if (ex) {
			throw new PacienteYaReservadoException();
		}
		int num = getNumeroAsignado();
		float costo = calcularCostoRes(p.getEdad());
		Reserva rsv = new Reserva(LocalDate.now(), num, costo, p, this, null);
		p.agregarReserva(rsv);
		reservas.put(p.getNickname(), rsv);
		return rsv;
	}

	public DTConsulta getDTConsulta() {
		Set<Integer> numerosReserva = new HashSet<>();
		for (Reserva r : reservas.values()) {
			numerosReserva.add(r.getNumero());
		}
		return new DTConsulta(nombre, fecha, getNickMedico(), nro_sala, nros_disp, getCosto(), numerosReserva);
	}

	// sobrecarga para la carga de datos
	public Reserva reservarPaciente(Paciente p, LocalDate fechaReserva)
			throws SinCupoException, PacienteYaReservadoException {
		boolean h = hayCupo();
		if (!h) {
			throw new SinCupoException();
		}
		boolean ex = existeReserva(p);
		if (ex) {
			throw new PacienteYaReservadoException();
		}
		int num = getNumeroAsignado();
		float costo = calcularCostoRes(p.getEdad());
		Reserva rsv = new Reserva(fechaReserva, num, costo, p, this, null);
		p.agregarReserva(rsv);
		reservas.put(p.getNickname(), rsv);
		return rsv;
	}
	public Reserva reservarPaciente(Paciente p, BeneficioAnual beneficio) 
	        throws SinCupoException, PacienteYaReservadoException, ReservaFueraDeFechaException, BeneficioSinOrdenesException{
	    if (this.fecha.isBefore(LocalDate.now()))
	        throw new ReservaFueraDeFechaException();
	    boolean h = hayCupo();
	    if (!h)
	        throw new SinCupoException();
	    boolean ex = existeReserva(p);
	    if (ex)
	        throw new PacienteYaReservadoException();
	    
	    int num = getNumeroAsignado();
	    float costo;
	    if (beneficio != null) {
	    	if (beneficio.getCantOrd() == 0)
	    		throw new BeneficioSinOrdenesException();
	        costo = 0f;
	        beneficio.descontarUso();
	    } else {
	        costo = calcularCostoRes(p.getEdad());
	    }
	    Reserva rsv = new Reserva(LocalDate.now(), num, costo, p, this, beneficio);
	    p.agregarReserva(rsv);
	    reservas.put(p.getNickname(), rsv);
	    return rsv;
	}

}
