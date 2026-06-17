package modelo;

/**
 * Representa el estado académico de una materia
 * dentro del historial de un alumno.
 *
 * Asocia el código de una materia con su condición
 * académica actual (aprobada, regularizada, cursando,
 * etc.), permitiendo registrar el avance del alumno
 * en el plan de estudios.
 */
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
