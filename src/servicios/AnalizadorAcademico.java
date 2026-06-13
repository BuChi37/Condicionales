package servicios;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;

public class AnalizadorAcademico {
	
	private PlanEstudio plan;
	
	public AnalizadorAcademico(PlanEstudio plan) {
		this.plan=plan;
	}
	public ListaDoubleLinkedL obtenerCorrelativasIndirectas(Materia materia) {
		//se utiliza mas que nada para las optativas, si quiero saber todas las materias que debo hacer para poder hacer la correlativa
		ListaDoubleLinkedL correlativasI= new ListaDoubleLinkedL();
		Materia materiaEvaluada;
		int indiceM = plan.getCatalogoMaterias().obtenerIndice(materia); //cambio obtenerIndice por getCodigo
		
		for (int i = 0; i < indiceM; i++) {
	        materiaEvaluada = plan.getCatalogoMaterias().getMateria(i);
	       
	        ListaDoubleLinkedL marcasDesdeEvaluada = plan.obtenerMateriasRelacionadas(materiaEvaluada);
	        
	        if ((boolean) marcasDesdeEvaluada.devolver(indiceM)) {
	            correlativasI.insertar(materiaEvaluada, correlativasI.tamanio());
	        }
	    }
		
		return null;
		
	}
	public ListaDoubleLinkedL materiasBloqueadas(Materia materia) {
		ListaDoubleLinkedL materiasB=new ListaDoubleLinkedL();
		//origen ami y destino ayed
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
	public ListaDoubleLinkedL materiasHabilitadas(Materia materia) {
		ListaDoubleLinkedL materiasH=new ListaDoubleLinkedL();
		//origen ami y destino ayed
		Materia materia2;
		
		for(int i=materia.getCodigo()+1;i<this.plan.getCatalogoMaterias().tamanio();i++) {
			materia2=plan.getCatalogoMaterias().getMateria(i);
			if(plan.existeCorrelatividad(materia,materia2)) {
				materiasH.insertar(materia2,materiasH.tamanio());
			}
		}
		
		return materiasH;
	}
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