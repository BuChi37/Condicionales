package modelo;

import infraestructura.ListaDoubleLinkedL;


/**
 * Administra el conjunto de solicitudes de condicionalidad
 * registradas en el sistema.
 *
 * Permite almacenar solicitudes y realizar consultas
 * filtradas por alumno, materia o estado de evaluación.
 *
 * Actúa como repositorio central de solicitudes dentro
 * del plan de estudios.
 */
public class RegistroSolicitudes {
	
	private ListaDoubleLinkedL solicitudes; //Lista que almacena todas las solicitudes registradas.
	
	/**
	 * Crea un registro de solicitudes vacío.
	 */
	public RegistroSolicitudes() {
		this.solicitudes = new ListaDoubleLinkedL();
	}
	
	/**
	 * Crea un registro utilizando una colección de
	 * solicitudes previamente cargada.
	 *
	 * @param reg lista de solicitudes inicial.
	 */
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

	/**
	 * Obtiene todas las solicitudes registradas.
	 *
	 * @return lista completa de solicitudes.
	 */
	public ListaDoubleLinkedL obtenerSolicitudes() { 
		return this.solicitudes;
	}

	/**
	 * Obtiene todas las solicitudes realizadas por
	 * un alumno determinado.
	 *
	 * @param alumno alumno a consultar.
	 * @return lista de solicitudes asociadas al alumno.
	 */
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
	
	/**
	 * Obtiene todas las solicitudes realizadas para
	 * una materia determinada.
	 *
	 * @param materia materia a consultar.
	 * @return lista de solicitudes asociadas a la materia.
	 */
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

	/**
	 * Obtiene las solicitudes cuyo dictamen final
	 * fue aprobado.
	 *
	 * @return lista de solicitudes aprobadas.
	 */
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

	/**
	 * Obtiene las solicitudes evaluadas cuyo
	 * resultado final fue rechazado.
	 *
	 * @return lista de solicitudes rechazadas.
	 */
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
	
	/**
	 * Obtiene las solicitudes que fueron derivadas
	 * a revisión pendiente.
	 *
	 * Estas solicitudes suelen corresponder a casos
	 * excepcionales que requieren intervención humana.
	 *
	 * @return lista de solicitudes pendientes.
	 */
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
