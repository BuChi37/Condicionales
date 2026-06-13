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

}
