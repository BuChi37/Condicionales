package servivios;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;

public class AnalizadorAcademico {
	
	private PlanEstudio plan;
	
	public AnalizadorAcademico(PlanEstudio plan) {
		this.plan=plan;
	}
	public ListaDoubleLinkedL obtenerCorrelativasIndirectas(Materia materia) {
		return null;
		
	}
	public ListaDoubleLinkedL materiasBloqueadas(Materia materia) {
		return null;
	}
	public ListaDoubleLinkedL materiasHabilitadas(Materia materia) {
		return null;
	}
	public boolean existeCamino(Materia matUno,Materia matDos) {
		return false;
	}
	public ListaDoubleLinkedL obtenerCamino(Materia matUno,Materia matDos) {
		return null;
	}
}
