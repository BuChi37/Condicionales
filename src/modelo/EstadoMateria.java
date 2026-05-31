package modelo;

public class EstadoMateria {
	private int codigoMateria;
	private EstadoAcademico estado;
	
	
	public EstadoMateria(int codigoMateria , EstadoAcademico estado) {
		
		this.codigoMateria = codigoMateria;
		this.estado = estado;
	}
	
	public int getCodigoMateria() {
		
		return this.codigoMateria;
	}
	
	public EstadoAcademico getEstado() {
		
		return this.estado;
	}
	
	public void setEstado(EstadoAcademico estado) {
		
		this.estado = estado;
	}
	
	
	public String toString() {
		
		return "codigo : "+this.codigoMateria +"  estado : "+ estado;
		
	}
	
}
