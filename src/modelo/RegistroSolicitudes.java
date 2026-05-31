package modelo;

import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;

public class RegistroSolicitudes {
	private Lista1DLinkedL solicitudes;
	
	public RegistroSolicitudes() {
		this.solicitudes = new ListaDoubleLinkedL();
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

	public Lista1DLinkedL obtenerSolicitudes() { //todas
		return this.solicitudes;
	}

	public Lista1DLinkedL obtenerSolicitudesAlumno(Alumno alumno) {

	    Lista1DLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.getAlumno().equals(alumno)) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	public Lista1DLinkedL obtenerSolicitudesMateria(Materia materia) {

	    Lista1DLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud = (SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.getMateriaSolicitada().equals(materia)) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}

	public Lista1DLinkedL obtenerAprobadas() {

	    Lista1DLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.fueAprobada()) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}

	public Lista1DLinkedL obtenerRechazadas() {
		
		Lista1DLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0; i < solicitudes.tamanio(); i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(solicitud.fueEvaluada() &&!solicitud.fueAprobada()) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	public Lista1DLinkedL obtenerPendientes() {

	    Lista1DLinkedL resultado = new ListaDoubleLinkedL();

	    for(int i = 0;i < solicitudes.tamanio();i++) {

	        SolicitudCondicional solicitud =(SolicitudCondicional)solicitudes.devolver(i);

	        if(!solicitud.fueEvaluada()) {

	            resultado.insertar(solicitud,resultado.tamanio());
	        }
	    }

	    return resultado;
	}
}
