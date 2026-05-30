package modelo;

public class ResultadoRegla {
	private boolean cumple;
	private String mensaje;
	
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
