package modelo;

public class Materia {
	private int codigo;
	private String nombre;
	private int anio;
	private int cuatrimestre;
	
	
	public Materia(int codigo, String nombre, int anio, int cuatrimestre ) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.anio  	= anio;
		this.cuatrimestre= cuatrimestre;
	}
	
	
	public int getCodigo() {	return this.codigo;	}
	public String getNombre() {	return this.nombre;	}
	public int getAnio() {		return this.anio;	}
	public int getCuatrimestre() {	return this.cuatrimestre;	}
	
	public boolean equals(Object elemento) {
		
		return (   ((Materia)elemento).getCodigo() == this.codigo);
	}
	// no c para que usar el equals por ahora 
}
