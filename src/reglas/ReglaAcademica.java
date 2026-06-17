package reglas;
import modelo.*;


/**
 * Define el contrato común para todas las reglas
 * académicas utilizadas por el MotorCondicionalidad.
 *
 * Cada implementación evalúa un criterio específico
 * y devuelve un ResultadoRegla con el resultado
 * correspondiente.
 */
public interface ReglaAcademica {
	
	/**
	 * Evalúa una solicitud académica según la regla
	 * implementada.
	 *
	 * @param solicitud solicitud a evaluar.
	 * @param plan plan de estudios utilizado durante
	 *             la evaluación.
	 * @return resultado de la regla evaluada.
	 */
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan);
}
