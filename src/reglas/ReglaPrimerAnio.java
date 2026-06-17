package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;

/**
 * Verifica que el alumno haya cumplido todas las
 * correlativas correspondientes al primer año de
 * la carrera.
 *
 * Esta regla aplica un criterio más estricto sobre
 * las materias básicas del plan de estudios,
 * exigiendo el cumplimiento completo de las
 * correlatividades requeridas.
 */
public class ReglaPrimerAnio implements ReglaAcademica{
	
	/**
	 * Evalúa el cumplimiento de las correlativas de
	 * primer año asociadas a la materia solicitada.
	 *
	 * @param solic solicitud a evaluar.
	 * @param plan plan de estudios utilizado para
	 *             consultar materias y requisitos.
	 * @return resultado de la evaluación.
	 */
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
