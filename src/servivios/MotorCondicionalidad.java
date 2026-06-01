package servivios;

import infraestructura.ListaDoubleLinkedL;
import modelo.Alumno;
import modelo.Dictamen;
import modelo.SolicitudCondicional;
import modelo.PlanEstudio;
import modelo.ResultadoRegla;
import reglas.ReglaAcademica;
import reglas.ReglaCorrelativasAprobadas;
import reglas.ReglaCorrelativasRegularizadas;
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
	}
	public Dictamen evaluarSolicitud(Alumno alumno, SolicitudCondicional solicitud){
		Dictamen dictamen=new Dictamen();
		ResultadoRegla resul;
		for (int i=0; i<reglas.tamanio();i++) {
			
			resul=((ReglaAcademica)reglas.devolver(i)).evaluar(solicitud, plan);
			if(!resul.cumpleRegla()) {
				dictamen.setAprobado(false);
				dictamen.agregarMotivo(resul.getMensaje());
			}
			dictamen.agregarResultado(resul);
		}
		if(dictamen.fueAprobado()) {
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
