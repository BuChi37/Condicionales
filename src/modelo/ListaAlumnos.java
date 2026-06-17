package modelo;

import infraestructura.Lista2DLinkedL;


/**
 * Colección especializada para almacenar alumnos.
 *
 * Extiende la estructura Lista2DLinkedL y define
 * los criterios de comparación utilizando el
 * número de legajo como identificador único.
 *
 * Esto permite realizar búsquedas, inserciones
 * y operaciones de ordenamiento sobre alumnos
 * utilizando su legajo.
 */
public class ListaAlumnos extends Lista2DLinkedL{

	/**
	 * Determina si dos alumnos poseen el mismo legajo.
	 *
	 * @param elemento1 primer alumno.
	 * @param elemento2 segundo alumno.
	 * @return true si ambos alumnos tienen el mismo legajo.
	 */
	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() ==  ((Alumno)elemento2).getLegajo()  );
	}

	/**
	 * Determina si el legajo del primer alumno es
	 * menor que el del segundo.
	 *
	 * @param elemento1 primer alumno.
	 * @param elemento2 segundo alumno.
	 * @return true si el primer legajo es menor.
	 */
	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() <  ((Alumno)elemento2).getLegajo()  );
	}

	/**
	 * Determina si el legajo del primer alumno es
	 * mayor que el del segundo.
	 *
	 * @param elemento1 primer alumno.
	 * @param elemento2 segundo alumno.
	 * @return true si el primer legajo es mayor.
	 */
	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() >  ((Alumno)elemento2).getLegajo()  );
	}
	
	
	
}
