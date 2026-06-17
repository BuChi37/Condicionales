package reglas;

import modelo.PlanEstudio;
import modelo.ResultadoRegla;
import modelo.SolicitudCondicional;


/**
 * Evalúa si el motivo declarado por el alumno
 * corresponde a una causa excepcional contemplada
 * por el sistema.
 *
 * Actualmente se consideran válidos los motivos
 * relacionados con salud o trabajo.
 *
 * Esta regla puede utilizarse para derivar una
 * solicitud a revisión manual aun cuando otras
 * condiciones académicas no se cumplan.
 */
public class ReglaMotivos implements ReglaAcademica{
	
	/**
	 * Verifica si el motivo declarado corresponde a
	 * una excepción académica contemplada.
	 *
	 * @param soli solicitud a evaluar.
	 * @param plan plan de estudios (no utilizado por
	 *             esta regla).
	 * @return resultado de la evaluación.
	 */
	public ResultadoRegla evaluar(SolicitudCondicional soli, PlanEstudio plan) {
		boolean cumple=false;
		if(soli.getMotivo().equalsIgnoreCase("salud") || soli.getMotivo().equalsIgnoreCase("trabajo")) {
			cumple=true;
		}

		return new ResultadoRegla(cumple, "Motivo: "+soli.getMotivo());
	}
}
