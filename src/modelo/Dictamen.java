package modelo;

import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;

/**
 * Representa el resultado de la evaluación de una solicitud
 * de condicionalidad.
 *
 * Almacena el estado final del análisis, los resultados
 * obtenidos para cada regla evaluada y los motivos que
 * justifican un eventual rechazo.
 *
 * Durante la evaluación, cada resultado parcial es registrado
 * y el dictamen actualiza automáticamente su estado.
 */

public class Dictamen {
	
	private String aprobado; //Estado final del dictamen. Puede tomar los valores "Aprobado" o "Rechazado".
	private Lista1DLinkedL resultados; //Resultados obtenidos para cada regla evaluada.
	private Lista1DLinkedL motivos; //Motivos asociados a las reglas incumplidas.
	
	
	/**
	 * Crea un dictamen inicialmente aprobado.
	 *
	 * El estado podrá cambiar a rechazado si alguna
	 * regla evaluada no se cumple.
	 */
	public Dictamen() {
		this.aprobado = "Aprobado";
        this.resultados = new ListaDoubleLinkedL();
        this.motivos = new ListaDoubleLinkedL();
	}
	
	public void setAprobado(String aprobado) { 
		this.aprobado=aprobado;
	}
	
	public String getEstado() {
		return this.aprobado;
	}
	
	public void agregarResultado(ResultadoRegla resultado) {
	    resultados.insertar(resultado,resultados.tamanio());
	}
	
	public void agregarMotivo(String motivo) {
	    motivos.insertar(motivo, motivos.tamanio());
	}
	
	public Lista1DLinkedL obtenerResultados() {
		return this.resultados;
	}
	
	public Lista1DLinkedL obtenerMotivos() {
	    return this.motivos;
	}
	
	
	/**
	 * Registra el resultado de una regla evaluada.
	 *
	 * Si la regla no se cumple, el dictamen pasa
	 * automáticamente al estado "Rechazado" y se
	 * almacena el motivo correspondiente.
	 *
	 * @param resultado resultado obtenido para una regla.
	 */
	public void registrarResultado(ResultadoRegla resultado) {
		agregarResultado(resultado);

		if(!resultado.cumpleRegla()) {
		    aprobado = "Rechazado";
		    agregarMotivo(resultado.getMensaje());
		}
	}
	
}
