package servicios;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;


/**
 * Proporciona servicios de análisis académico sobre un PlanEstudio.
 *
 * Actúa como una capa de lógica académica por encima de las estructuras
 * de datos del sistema.
 */

public class AnalizadorAcademico {
	
	private PlanEstudio plan; //Plan de estudios sobre el cual se realizan los análisis.
	
	/**
	 * Crea un analizador asociado a un plan de estudios.
	 */
	public AnalizadorAcademico(PlanEstudio plan) {
		this.plan=plan;
	}
	
	/**
	 * Obtiene las materias desde las cuales puede alcanzarse
	 * la materia indicada a través de una cadena de correlatividades.
	 *
	 * El resultado puede incluir correlatividades directas e indirectas.
	 */
	public ListaDoubleLinkedL obtenerCorrelativasIndirectas(Materia materia) {
		//se utiliza principalmente para las optativas, si quiero saber todas las materias que debo hacer para poder cursarla
		ListaDoubleLinkedL correlativasI= new ListaDoubleLinkedL();
		Materia materiaEvaluada;
		int indiceM = plan.getCatalogoMaterias().obtenerIndice(materia); 
		
		for (int i = 0; i < indiceM; i++) {
	        materiaEvaluada = plan.getCatalogoMaterias().getMateria(i);
	       
	        ListaDoubleLinkedL marcasDesdeEvaluada = plan.obtenerMateriasRelacionadas(materiaEvaluada);
	        
	        if ((boolean) marcasDesdeEvaluada.devolver(indiceM)) {
	            correlativasI.insertar(materiaEvaluada, correlativasI.tamanio());
	        }
	    }
		
		return null; //!!
		
	}
	
	/**
	 * Obtiene las materias que dependen directa o indirectamente
	 * de la materia indicada.
	 *
	 * Si la materia no pudiera cursarse o aprobarse, las materias
	 * devueltas por este método quedarían académicamente bloqueadas.
	 */
	public ListaDoubleLinkedL materiasBloqueadas(Materia materia) {
		ListaDoubleLinkedL materiasB=new ListaDoubleLinkedL();
		
		Materia materia2;
		ListaDoubleLinkedL marcas = plan.obtenerMateriasRelacionadas(materia);
		
		for(int i=materia.getCodigo()+1;i<this.plan.getCatalogoMaterias().tamanio();i++) {
			materia2=plan.getCatalogoMaterias().getMateria(i);
			
			if((boolean) marcas.devolver(i)) {
				materiasB.insertar(materia2,materiasB.tamanio());
			}
		}
		
		return materiasB;
	}
	
	/**
	 * Obtiene las materias que poseen una correlatividad directa
	 * con la materia indicada.
	 */
	public ListaDoubleLinkedL materiasHabilitadas(Materia materia) {
		ListaDoubleLinkedL materiasH=new ListaDoubleLinkedL();
		
		Materia materia2;
		
		for(int i=materia.getCodigo()+1;i<this.plan.getCatalogoMaterias().tamanio();i++) {
			materia2=plan.getCatalogoMaterias().getMateria(i);
			if(plan.existeCorrelatividad(materia,materia2)) {
				materiasH.insertar(materia2,materiasH.tamanio());
			}
		}
		
		return materiasH;
	}
	
	/**
	 * Filtra las solicitudes de condicionalidad según los criterios
	 * ingresados por el usuario.
	 *
	 * Los filtros son acumulativos:
	 * - Estado de la solicitud.
	 * - Legajo del alumno.
	 * - Nombre de la materia.
	 *
	 * Si un criterio no se especifica, no se aplica restricción
	 * sobre ese campo.
	 *
	 * @param legajo legajo a buscar o cadena vacía.
	 * @param materia nombre de materia a buscar o cadena vacía.
	 * @param estado estado de solicitud o "Todos".
	 * @return lista de solicitudes que cumplen los filtros.
	 */
	public ListaDoubleLinkedL filtrar(String legajo, String materia, String estado) {
	   
	    ListaDoubleLinkedL resultado = plan.getRegistroSolicitudes().obtenerSolicitudes();

	    if (!estado.isEmpty() && !estado.equalsIgnoreCase("Todos")) {
	        if (estado.equalsIgnoreCase("Pendiente")) resultado =  plan.getRegistroSolicitudes().obtenerPendientes();
	        if (estado.equalsIgnoreCase("Aprobado"))  resultado =  plan.getRegistroSolicitudes().obtenerAprobadas();
	        if (estado.equalsIgnoreCase("Rechazado"))  resultado =  plan.getRegistroSolicitudes().obtenerRechazadas();
	    }

	    // 3. ¿El usuario además puso un Legajo? Pasamos el resultado por el segundo colador
	    if (!legajo.isEmpty()) {
	        ListaDoubleLinkedL aux = new ListaDoubleLinkedL();
	        for (int i = 0; i < resultado.tamanio(); i++) {
	            SolicitudCondicional sol = (SolicitudCondicional) resultado.devolver(i);
	            if (legajo.equals(String.valueOf(sol.getAlumno().getLegajo()))) {
	                aux.insertar(sol, aux.tamanio());
	            }
	        }
	        resultado = aux; // La lista se va achicando
	    }

	    // 4. ¿El usuario además puso una Materia? Pasamos el residuo por el tercer colador
	    if (!materia.isEmpty()) {
	        ListaDoubleLinkedL aux = new ListaDoubleLinkedL();
	        for (int i = 0; i < resultado.tamanio(); i++) {
	            SolicitudCondicional sol = (SolicitudCondicional) resultado.devolver(i);
	            if (sol.getMateriaSolicitada().getNombre().toLowerCase().contains(materia.toLowerCase())) {
	                aux.insertar(sol, aux.tamanio());
	            }
	        }
	        resultado = aux;
	    }

	    // Al final, devuelve la lista que sobrevivió a los filtros activos. 
	    // Si no se llenó ningún campo, devuelve todo el catálogo intacto.
	    return resultado;
	}
}