package reglas;
import infraestructura.ListaDoubleLinkedL;
import modelo.*;
public class ReglaCorrelativasRegularizadas implements ReglaAcademica{
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan) {
		
		boolean cumple=true;
		String motivo="";
		
		ListaDoubleLinkedL correlativas= plan.obtenerCorrelativasDirectas(solic.getMateriaSolicitada());
		HistorialAlumnos historial=solic.getAlumno().getHistorial();
		Materia materia;
		
		for(int i=0;i<correlativas.tamanio();i++) {
			materia=(Materia)correlativas.devolver(i);
			
			if(plan.obtenerCondicionRequerida(materia,solic.getMateriaSolicitada()) == TipoCondicion.REGULAR) {
				if(!historial.estaRegular(materia.getCodigo()) && !historial.estaAprobado(materia.getCodigo())) {
					cumple=false;
					if(motivo.isEmpty()) {
						motivo+="Faltan regularizar las siguientes correlativas: "+materia.getNombre();
					}else {
						motivo+=", "+materia.getNombre();
					}
				}
			}
		}
		if (cumple) {
			motivo="Cumple con todas las correlativas regularizadas";
		}
		motivo+=".";
		return new ResultadoRegla(cumple,motivo);
	}
}
