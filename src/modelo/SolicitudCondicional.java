package modelo;

import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;

public class SolicitudCondicional {
	private Alumno alumno;
	private Materia materiaSolicitada;
	private String fecha;
	private Lista1DLinkedL correlativasDeclaradas;
	private Dictamen dictamenFinal;
	
	public SolicitudCondicional(Alumno alumno, Materia materiaSolicitada, String fecha) {
	    this.alumno = alumno;
	    this.materiaSolicitada = materiaSolicitada;
	    this.fecha = fecha; 
	    
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
	
	public void agregarCorrelativaDeclarada(EstadoMateria estadoMateria){
	    correlativasDeclaradas.insertar(estadoMateria,correlativasDeclaradas.tamanio());
	}
	
	public Lista1DLinkedL obtenerCorrelativasDeclaradas() {
	    return this.correlativasDeclaradas;
	}
	
	public boolean fueEvaluada() {
		return this.dictamenFinal != null;
	}
	
	public boolean fueAprobada() {

	    if (!fueEvaluada()) {
	        return false;
	    }
	    return dictamenFinal.fueAprobado();
	}
	
	@Override
	public String toString() {
	    return "SolicitudCondicional [alumno="+ alumno +", materiaSolicitada="+ materiaSolicitada +
	            ", fecha="+ fecha +"]";
	}
}
