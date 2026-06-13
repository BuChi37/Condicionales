package modelo;

import infraestructura.ListaDoubleLinkedL;

public class RegistroSolicitudes {
	private ListaDoubleLinkedL solicitudes;
	
	public RegistroSolicitudes() {
		this.solicitudes = new ListaDoubleLinkedL();
	}
	public RegistroSolicitudes(ListaDoubleLinkedL reg) {
		this.solicitudes = reg;
	}
	public boolean estaVacio() {
	    return solicitudes.tamanio() == 0;
	}
	
	public int cantidadSolicitudes() {
	    return solicitudes.tamanio();
	}
	
	public void agregarSolicitud(SolicitudCondicional solicitud) {
		solicitudes.insertar(solicitud, solicitudes.tamanio() );
	}

	public ListaDoubleLinkedL obtenerSolicitudes() { //todas
		return this.solicitudes;
	}

	public ListaDoubleLinkedL obtenerSolicitudesAlumno(Alumno alumno) {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.getAlumno().equals(alumno)) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	public ListaDoubleLinkedL obtenerSolicitudesMateria(Materia materia) {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud = (SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.getMateriaSolicitada().equals(materia)) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}

	public ListaDoubleLinkedL obtenerAprobadas() {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.fueAprobada()) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}

	public ListaDoubleLinkedL obtenerRechazadas() {
		
		ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.fueEvaluada() && solicitud.getEstado().equalsIgnoreCase("Rechazado")) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	public ListaDoubleLinkedL obtenerPendientes() {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0;i < solicitudes.tamanio();i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.fueEvaluada() && solicitud.getEstado().equalsIgnoreCase("pendiente")) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
}
