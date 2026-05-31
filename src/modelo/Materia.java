package modelo;

public class Materia {
	private int codigo;
	private String nombre;
	private int anio;
	
	
	public Materia(int codigo, String nombre, int anio) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.anio  	= anio;
	}
	
	public int getCodigo() {	return this.codigo;	}
	public String getNombre() {	return this.nombre;	}
	public int getAnio() {		return this.anio;	}
	
	
	public boolean equals(Object elemento) {
		
		return (   ((Materia)elemento).getCodigo() == this.codigo);
	}
	// no c para que usar el equals por ahora 
}
