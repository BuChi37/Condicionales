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
import reglas.ReglaRendimientoAcademico;

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
		agregarRegla(new ReglaRendimientoAcademico());
	}
	public Dictamen evaluarSolicitud(Alumno alumno, SolicitudCondicional solicitud){
		Dictamen dictamen=new Dictamen();
		ResultadoRegla resul;
		boolean tieneExcepcion=false,acep=true;
		
		for (int i=0; i<reglas.tamanio();i++) {
			
			resul=((ReglaAcademica)reglas.devolver(i)).evaluar(solicitud, plan);
				
			if(resul.cumpleRegla()) {
				if(resul.getMensaje().toLowerCase().contains("salud") || resul.getMensaje().toLowerCase().contains("trabajo")) {
					tieneExcepcion=true;
					dictamen.agregarMotivo(resul.getMensaje());
					
				}
			}else if(!resul.getMensaje().toLowerCase().contains("otro")){
				dictamen.setAprobado("Rechazado");
				dictamen.agregarMotivo(resul.getMensaje());
				acep=false;
			}
			dictamen.agregarResultado(resul);
		}
		
		if (tieneExcepcion && !acep) {
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
