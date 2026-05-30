package modelo;

import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;

public class Dictamen {
	private boolean aprobado;
	private Lista1DLinkedL resultados;
	private Lista1DLinkedL motivos;
	
	public Dictamen() {
		this.aprobado = true;
        this.resultados = new ListaDoubleLinkedL();
        this.motivos = new ListaDoubleLinkedL();
	}
	
	public void setAprobado(boolean aprobado) { 
		this.aprobado=aprobado;
	}
	
	public boolean fueAprobado() {
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
	
	public void registrarResultado(ResultadoRegla resultado) {
		agregarResultado(resultado);

		if(!resultado.cumpleRegla()) {
		    aprobado = false;
		    agregarMotivo(resultado.getMensaje());
		}
	}
	
}
