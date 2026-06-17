package infraestructura;

/**
 * Implementación concreta de un grafo dirigido mediante
 * una matriz de adyacencia.
 *
 * * Se utiliza para modelar las correlatividades del plan
 * de estudios, donde cada vértice representa una materia
 * y cada arco una condición académica entre materias.
 * 
 * Cada posición de la matriz representa una correlatividad
 * entre dos materias:
 *
 * 0 -> no existe correlatividad.
 * 1 -> se requiere regularidad.
 * 2 -> se requiere aprobación.
 *
 * Extiende la infraestructura de grafos provista por la
 * cátedra y agrega operaciones específicas utilizadas
 * por el proyecto.
 */

public class GrafoDirigido extends AbsGrafoD{
	
	/**
	 * Crea un grafo dirigido con el orden indicado.
	 */
	public GrafoDirigido(int ordenGrafo){
		super(ordenGrafo);
	}
	
	/**
	 * Inicializa la matriz de adyacencia del grafo.
	 *
	 * Todas las posiciones se cargan con el valor 0,
	 * indicando que inicialmente no existen
	 * correlatividades entre las materias.
	 *
	 * @param ruta parámetro requerido por la jerarquía
	 *             de grafos. No es utilizado durante
	 *             la inicialización.
	 */
	public void cargarGrafo(String ruta){ 

		for(int fila = 1; fila < getOrden() ; fila++) {
			
			for(int columna =1; columna < getOrden(); columna++) {
				
				agregarArco(fila, columna, 0);
			}
		}
	}
	
	
	/**
	 * Registra una correlatividad entre dos materias.
	 *
	 * @param origen materia correlativa.
	 * @param destino materia que requiere la correlatividad.
	 * @param costo condición académica asociada:
	 *              1 = regularidad.
	 *              2 = aprobación.
	 */
	public void agregarArco(int origen,int destino,Object costo) { 
	    matrizCosto.actualizar(costo,origen,destino);
	}
	
	/**
	 * Determina si existe una correlatividad directa
	 * entre dos materias.
	 *
	 * @param origen materia correlativa.
	 * @param destino materia dependiente.
	 * @return true si existe un arco entre ambas materias.
	 */
	public boolean existeArco(int origen,int destino) { 
		 return matrizCosto.areConnected(origen,destino);
	}
	
	/**
	 * Obtiene la condición académica almacenada en una
	 * correlatividad.
	 *
	 * @param origen materia correlativa.
	 * @param destino materia dependiente.
	 * @return valor almacenado en el arco.
	 */
	public int obtenerArco(int origen,int destino) { 
	    return (int)matrizCosto.devolver( origen,destino);
	}
	
}

