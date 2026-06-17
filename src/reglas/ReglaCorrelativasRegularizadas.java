package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;

/**
 * Verifica el cumplimiento de las correlativas que
 * requieren condición de regularidad.
 *
 * La regla exige que todas las materias requeridas
 * como regularizadas se encuentren al menos regulares
 * o aprobadas en el historial académico del alumno.
 */
public class ReglaCorrelativasRegularizadas implements ReglaAcademica{
	
	/**
	 * Evalúa las correlativas de regularidad requeridas
	 * para cursar la materia solicitada.
	 *
	 * @param solic solicitud a evaluar.
	 * @param plan plan de estudios utilizado para obtener
	 *             correlatividades y requisitos.
	 * @return resultado de la evaluación.
	 */
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan) {
		
		boolean cumple=true;
		String motivo="";
		
		ListaDoubleLinkedL correlativas= plan.obtenerCorrelativasDirectas(solic.getMateriaSolicitada());
		HistorialAlumnos historial=solic.getAlumno().getHistorial();
		Materia materia;
		
		for(int i=0;i<correlativas.tamanio();i++) {
			materia=(Materia)correlativas.devolver(i);
			System.out.println(materia.getNombre());
			if(plan.obtenerCondicionRequerida(materia,solic.getMateriaSolicitada()) == TipoCondicion.REGULAR) {
				
				if(!historial.estaRegular(materia.getCodigo()) && !historial.estaAprobado(materia.getCodigo())) {
					cumple=false;
					if(motivo.isEmpty()) {
						motivo+="Faltan regularizar las siguientes correlativas: "+materia.getNombre();
					}else {
						motivo+=", "+materia.getNombre();
					}
				}
			}
		}
		if (cumple) {
			motivo="Cumple con todas las correlativas regularizadas";
		}
		motivo+=".";
		return new ResultadoRegla(cumple,motivo);
	}
}
