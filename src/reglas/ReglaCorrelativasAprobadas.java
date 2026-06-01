package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;

public class ReglaCorrelativasAprobadas implements ReglaAcademica{
	
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan) {
		int materiasAdeudadas=0;
		boolean cumple=true;
		String motivo="",materiaA="";
		
		ListaDoubleLinkedL correlativas= plan.obtenerCorrelativasDirectas(solic.getMateriaSolicitada());
		HistorialAlumnos historial=solic.getAlumno().getHistorial();
		Materia materia; 
		
		for(int i=0;i<correlativas.tamanio();i++) {
			materia=(Materia)correlativas.devolver(i);
			
			if(plan.obtenerCondicionRequerida(materia,solic.getMateriaSolicitada()) == TipoCondicion.APROBADO) {
				if( !historial.estaAprobado(materia.getCodigo())) {
					materiasAdeudadas++;
					
					materiaA+=materia.getNombre()+", ";
					if(materiasAdeudadas==1) {
						if(!historial.estaRegular(materia.getCodigo())){
							cumple=false;
							motivo="No Aprobo ni regularizo :"+materiaA;
						}
					}else {
						cumple=false;
					}
				}
			}
		}
		if (cumple) {
			if(materiasAdeudadas==0) {
				
				motivo="Cumple con todas las correlativas Aprobadas";
			}else {
				if (materiaA.endsWith(", ")) {
	                materiaA = materiaA.substring(0, materiaA.length() - 2);
	            }
				motivo="El alumno solo adeuda: "+materiaA+", por lo tanto cumple con la regla";
			}
			
		}else if(motivo.isEmpty()) {
			if (materiaA.endsWith(", ")) {
                materiaA = materiaA.substring(0, materiaA.length() - 2);
            }
			motivo="No cumplio con aprobar: "+materiaA;
		}
		
		return new ResultadoRegla(cumple,motivo);
	}
}
