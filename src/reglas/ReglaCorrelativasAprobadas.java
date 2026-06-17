package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;


/**
 * Verifica el cumplimiento de las correlativas que
 * requieren condición de aprobación.
 *
 * La regla permite que el alumno adeude como máximo
 * una correlativa de este tipo, siempre que dicha
 * materia se encuentre al menos regularizada.
 *
 * Devuelve un ResultadoRegla indicando si el alumno
 * cumple la condición y el motivo correspondiente.
 */
public class ReglaCorrelativasAprobadas implements ReglaAcademica{
	
	
	/**
	 * Evalúa las correlativas de aprobación exigidas por
	 * la materia solicitada.
	 *
	 * @param solic solicitud a evaluar.
	 * @param plan plan de estudios utilizado para consultar
	 *             correlatividades y condiciones requeridas.
	 * @return resultado de la evaluación.
	 */
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
