package modelo;

import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;


/**
 * Representa una solicitud de cursado condicional
 * realizada por un alumno.
 *
 * Almacena la materia solicitada, la información
 * del alumno, el motivo de la solicitud y el
 * dictamen resultante de la evaluación.
 *
 * Constituye la entidad central del proceso de
 * gestión de condicionalidades.
 */
public class SolicitudCondicional {
	
	private Alumno alumno; //Alumno que realiza la solicitud.
	private Materia materiaSolicitada; //Materia para la cual se solicita la condicionalidad.
	private String fecha; //Fecha de creación de la solicitud.
	private String motivo; //Motivo declarado por el alumno.
	private Lista1DLinkedL correlativasDeclaradas; // Correlativas declaradas por el alumno al momento de realizar la solicitud.
	private Dictamen dictamenFinal; // Resultado final de la evaluación. Permanece nulo mientras la solicitud no haya sido procesada.
	
	public SolicitudCondicional(Alumno alumno, Materia materiaSolicitada, String fecha, String motivo) {
	    this.alumno = alumno;
	    this.materiaSolicitada = materiaSolicitada;
	    this.fecha = fecha; 
	    this.motivo = motivo;
	    
	    this.dictamenFinal = null;
	    
	    this.correlativasDeclaradas = new ListaDoubleLinkedL();
	}
	
	public Alumno getAlumno() {
		return this.alumno;
	}

	public Materia getMateriaSolicitada() {
		return this.materiaSolicitada;
	}
	
	public String getFecha() {
	    return this.fecha;
	}
	
	public Dictamen getDictamenFinal() {
		return this.dictamenFinal;
	}
	
	public void setDictamen(Dictamen dictamen){
	    this.dictamenFinal = dictamen;
	}
	
	public String getMotivo() {
		return this.motivo;
	}
	
	public void agregarCorrelativaDeclarada(EstadoMateria estadoMateria){
	    correlativasDeclaradas.insertar(estadoMateria,correlativasDeclaradas.tamanio());
	}
	
	public Lista1DLinkedL obtenerCorrelativasDeclaradas() {
	    return this.correlativasDeclaradas;
	}
	
	
	/**
	 * Indica si la solicitud ya fue evaluada.
	 *
	 * @return true si existe un dictamen asociado.
	 */
	public boolean fueEvaluada() {
		return this.dictamenFinal != null;
	}
	
	
	/**
	 * Indica si la solicitud fue aprobada.
	 *
	 * Una solicitud no evaluada se considera
	 * automáticamente no aprobada.
	 *
	 * @return true si el dictamen es aprobado.
	 */
	public boolean fueAprobada() {

	    if (!fueEvaluada()) {
	        return false;
	    }
	    return dictamenFinal.getEstado().equalsIgnoreCase("Aprobado");
	}
	
	public String getEstado() {

	    if(!fueEvaluada()) {
	        return "Pendiente";
	    }

	    return dictamenFinal.getEstado();
	}
	
	@Override
	public String toString() {
	    return "SolicitudCondicional [alumno="+ alumno +", materiaSolicitada="+ materiaSolicitada +
	            ", fecha="+ fecha +"]";
	}
}
