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


/**
 * Coordina el proceso de evaluación de solicitudes
 * de condicionalidad.
 *
 * Mantiene un conjunto de reglas académicas y las
 * ejecuta sobre una solicitud para generar un
 * dictamen final.
 *
 * Cada regla se evalúa de forma independiente y
 * contribuye al resultado global de la evaluación.
 */
public class MotorCondicionalidad {
	
	private ListaDoubleLinkedL reglas; // Conjunto de reglas académicas utilizadas durante la evaluación de solicitudes.
	private PlanEstudio plan; // Plan de estudios utilizado como fuente de información académica durante las evaluaciones.
	
	
	/**
	 * Crea un motor de evaluación asociado a un plan
	 * de estudios e inicializa las reglas académicas
	 * que participarán del análisis.
	 *
	 * @param plan plan de estudios utilizado durante las evaluaciones.
	 */
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
