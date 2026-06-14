package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;
public class ReglaPrimerAnio implements ReglaAcademica{
	
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan) {
		boolean cumple=true;
		String motivo="";
		String materiasA="", materiasR="";
		
		ListaDoubleLinkedL correlativas= plan.obtenerCorrelativasDirectas(solic.getMateriaSolicitada());
		HistorialAlumnos historial=solic.getAlumno().getHistorial();
		Materia materia;
		
		for(int i=0;i<correlativas.tamanio();i++) {
			
			materia=(Materia)correlativas.devolver(i);
			if(materia.getAnio()==1) {
				
				if(plan.obtenerCondicionRequerida(materia, solic.getMateriaSolicitada()) == TipoCondicion.REGULAR) {
					if(!historial.estaRegular(materia.getCodigo()) && !historial.estaAprobado(materia.getCodigo())) {
						cumple=false;
						materiasR+=materia.getNombre()+", ";
					}
				}else {
					if(!historial.estaAprobado(materia.getCodigo())) {
						cumple=false;
						materiasA+=materia.getNombre()+", ";
					}
				}
			}
		}
		if (cumple) {
			motivo="Cumple con todas las correlativas del primer anio";
		}else {
			if(!materiasR.isEmpty()) {
				materiasR= materiasR.substring(0, materiasR.length() - 2);
				motivo+="No regularizada las materias: "+materiasR+". ";
			}
			if(!materiasA.isEmpty()) {
				materiasA= materiasA.substring(0, materiasA.length() - 2);
				motivo+="No aprobo las materias: "+materiasA+". ";
			}
		}
		return new ResultadoRegla(cumple,motivo);
	}
}
