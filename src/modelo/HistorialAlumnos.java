package modelo;

import infraestructura.Lista2DLinkedL;
import infraestructura.ListaDoubleLinkedL;


/**
 * Representa el historial académico de un alumno.
 *
 * Almacena los estados académicos asociados a las
 * materias cursadas por el estudiante y permite
 * consultar si una materia se encuentra aprobada,
 * regularizada o en otro estado académico.
 *
 * Extiende Lista2DLinkedL y define los criterios
 * de comparación utilizando el código de materia
 * como identificador único.
 */
public class HistorialAlumnos extends Lista2DLinkedL{

	/**
	 * Determina si dos registros de estado corresponden
	 * a la misma materia.
	 *
	 * La comparación se realiza utilizando el código
	 * de materia.
	 *
	 * @param elemento1 primer estado académico.
	 * @param elemento2 segundo estado académico.
	 * @return true si representan la misma materia.
	 */
	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() ==  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
	}

	/**
	 * Determina si el código de la primera materia es
	 * menor que el de la segunda.
	 *
	 * Se utiliza para mantener los criterios de orden
	 * definidos por la lista base.
	 *
	 * @param elemento1 primer estado académico.
	 * @param elemento2 segundo estado académico.
	 * @return true si el primer código es menor.
	 */
	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() <  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
	}

	/**
	 * Determina si el código de la primera materia es
	 * mayor que el de la segunda.
	 *
	 * @param elemento1 primer estado académico.
	 * @param elemento2 segundo estado académico.
	 * @return true si el primer código es mayor.
	 */
	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() >  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
	}
	
	/**
	 * Busca el estado académico asociado a una materia.
	 *
	 * @param codigo código de la materia.
	 * @return objeto EstadoMateria correspondiente o
	 *         null si la materia no figura en el historial.
	 */
	public EstadoMateria getEstado(int codigo) {
		
		EstadoMateria estado  = new EstadoMateria(codigo, null);
		
		int pos = buscar(estado);
		
		if(pos != -1) {
			EstadoMateria materiaDevuelta = (EstadoMateria) devolver(pos);
			
			return materiaDevuelta;
		}
		
		return null;
	}

	public void ModificarEstado(EstadoAcademico estado , int codigo) {
		
		EstadoMateria estadoMateria = getEstado(codigo);
		estadoMateria.setEstado(estado);
	}
	
	public boolean estaRegular(int codigo) {
		
		EstadoMateria estado = getEstado(codigo); 
		
		if(estado != null) {
			
			return (estado.getEstado() == EstadoAcademico.REGULAR);
		}
		
		System.out.println("esa materia no pertenece al alumno");
		
		return false;
	}
	
	public boolean estaAprobado(int codigo) {
		
		EstadoMateria estado = getEstado(codigo); 
		
		if(estado != null) {
			
			return (estado.getEstado() == EstadoAcademico.APROBADA);
		}
		
		System.out.println("esa materia no pertenece al alumno");
		
		return false;
	}
	
	/**
	 * Cuenta la cantidad de materias aprobadas por
	 * el alumno.
	 *
	 * Este valor es utilizado por algunas reglas
	 * académicas para calcular el avance dentro
	 * del plan de estudios.
	 *
	 * @return cantidad de materias aprobadas.
	 */
	public int getContadorAprobadas() {
		int aprobadas=0;
		
		/*sugerencia:
		for(int i=0; i<tamanio(); i++) {
		    EstadoMateria em =
		        (EstadoMateria) devolver(i);

		    if(em.getEstado() ==
		       EstadoAcademico.APROBADA) {
		        aprobadas++;
		    }
		}*/
		for(int i=0;i<this.tamanio();i++) {
			if(this.estaAprobado(i+1)) {
				aprobadas++;
			}
		}
		return aprobadas;
	}
	
	public String toString() {
		
		String cadena="";
		for(int i=0 ; i< tamanio() ; i++) {
			
			cadena+= devolver(i).toString();
			cadena+= "  ";
		}
		
		return cadena;
	}
	
}
