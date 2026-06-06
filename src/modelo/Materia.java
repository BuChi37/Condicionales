package modelo;

public class Materia {
	private int codigo;
	private String nombre;
	private int anio;
	private String cuatrimestre;
	
	
	public Materia(int codigo, String nombre, int anio, String cuatrimestre ) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.anio  	= anio;
		this.cuatrimestre= cuatrimestre;
	}
	
	
	public int getCodigo() {	return this.codigo;	}
	public String getNombre() {	return this.nombre;	}
	public int getAnio() {		return this.anio;	}
	public String getCuatrimestre() {	return this.cuatrimestre;	}
	
	public boolean equals(Object elemento) {
		
		return (   ((Materia)elemento).getCodigo() == this.codigo);
	}
	// no c para que usar el equals por ahora 
}
