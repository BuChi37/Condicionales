package servicios;

import infraestructura.ListaDoubleLinkedL;
import modelo.Alumno;
import modelo.Dictamen;
import modelo.SolicitudCondicional;
import modelo.PlanEstudio;
import modelo.ResultadoRegla;
import reglas.ReglaAcademica;
import reglas.ReglaCorrelativasAprobadas;
import reglas.ReglaCorrelativasRegularizadas;
import reglas.ReglaMotivos;
import reglas.ReglaPrimerAnio;

public class MotorCondicionalidad {
	
	private ListaDoubleLinkedL reglas;
	private PlanEstudio plan;
	
	public MotorCondicionalidad (PlanEstudio plan) {
		
		this.plan=plan;
		reglas= new ListaDoubleLinkedL();
		
		agregarRegla(new ReglaCorrelativasAprobadas());
		agregarRegla(new ReglaCorrelativasRegularizadas());
		agregarRegla(new ReglaPrimerAnio());
		agregarRegla(new ReglaMotivos());
	}
	public Dictamen evaluarSolicitud(Alumno alumno, SolicitudCondicional solicitud){
		Dictamen dictamen=new Dictamen();
		ResultadoRegla resul;
		boolean tieneExcepcion=false;
		
		for (int i=0; i<reglas.tamanio();i++) {
			
			resul=((ReglaAcademica)reglas.devolver(i)).evaluar(solicitud, plan);
				
			if(!resul.cumpleRegla()) {
				if(resul.getMensaje().equalsIgnoreCase("salud") || resul.getMensaje().equalsIgnoreCase("trabajo")) {
					tieneExcepcion=true;
					dictamen.agregarMotivo(resul.getMensaje());
				}else {
					dictamen.setAprobado("Desaprobado");
					dictamen.agregarMotivo(resul.getMensaje());
				}
			}
			dictamen.agregarResultado(resul);
		}
		
		if (tieneExcepcion) {
	        dictamen.setAprobado("Pendiente");
	    }else if(dictamen.getEstado().equalsIgnoreCase("Aprobado")) {
			dictamen.agregarMotivo("Alumno aprobado porque cumplio con todos los requerimientos minimos para ser un alumno condicional");
		}
		return dictamen;
	}
	public void agregarRegla(ReglaAcademica regla){
		reglas.insertar(regla, reglas.tamanio());
	}
	public void removerRegla(ReglaAcademica regla) {
		reglas.eliminar(reglas.buscar(regla));
	}
}
