package modelo;

public class Alumno {
	private int legajo;
	private String nombre;
	private HistorialAlumnos historial;
	
	
	public Alumno(int legajo, String nombre) {
		
		this.legajo = legajo;
		this.nombre = nombre;
	}
	
	public void agregarEstado(EstadoMateria estado) {
		
		this.historial.insertar(estado);
	}
	
	
	public int getLegajo() {
		
		return this.legajo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public HistorialAlumnos getHistorial(){
		return this.historial;
	}
	
	public EstadoMateria getEstado(int codigoMateria) {
		
		return this.historial.getEstado(codigoMateria);
	}
	
}
