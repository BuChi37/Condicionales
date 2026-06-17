package modelo;


/**
 * Representa el resultado obtenido al evaluar
 * una regla académica.
 *
 * Contiene el cumplimiento de la regla y un
 * mensaje descriptivo que explica el resultado.
 *
 * Es utilizado por el MotorCondicionalidad para
 * construir el Dictamen final de una solicitud.
 */
public class ResultadoRegla {
	
	private boolean cumple; //Indica si la regla fue satisfecha.
	private String mensaje; //Mensaje descriptivo asociado al resultado.
	
	
	/**
	 * Crea un resultado para una regla evaluada.
	 *
	 * @param cumple indica si la regla se cumple.
	 * @param mensaje explicación del resultado.
	 */
	public ResultadoRegla(boolean cumple, String mensaje) {
	    this.cumple = cumple;
	    this.mensaje = mensaje;
	}
	
	public boolean cumpleRegla() {
	    return this.cumple;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	
	@Override
	public String toString() {
	    return "ResultadoRegla [cumple=" + cumple +", mensaje=" + mensaje + "]";
	}
	
}
