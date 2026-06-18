package reglas;

import modelo.PlanEstudio;
import modelo.ResultadoRegla;
import modelo.SolicitudCondicional;


/**
 * Evalúa el rendimiento académico global del alumno.
 *
 * Para materias ubicadas en la primera mitad de la
 * carrera, el requisito de porcentaje mínimo aprobado
 * no se aplica.
 *
 * Para materias de la segunda mitad de la carrera,
 * el alumno debe poseer al menos el 60% de las
 * materias aprobadas.
 */
public class ReglaRendimientoAcademico implements ReglaAcademica{

	
	/**
	 * Determina si el alumno cumple con el requisito
	 * mínimo de avance académico para solicitar la
	 * condicionalidad.
	 *
	 * El criterio aplicado depende de la ubicación de
	 * la materia dentro del plan de estudios.
	 *
	 * @param solic solicitud a evaluar.
	 * @param plan plan de estudios utilizado para
	 *             calcular el avance académico.
	 * @return resultado de la evaluación.
	 */
	@Override
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan) {
	
		// 1. Calculamos el total de cuatrimestres de la carrera
		int totalCuatrimestresCarrera = plan.getCatalogoMaterias().getMateria(plan.getCatalogoMaterias().tamanio()).getAnio()* 2;
		    
		// 2. Cortamos la carrera a la mitad exacta
		int cuatrimestreMitad = totalCuatrimestresCarrera / 2;
		    
		// 3. Calculamos en qué cuatrimestre neto está la materia que piden
		// Ej: 2° año, 1° cuatri -> (2-1)*2 + 1 = Cuatrimestre 3
		// Ej: 2° año, 2° cuatri -> (2-1)*2 + 2 = Cuatrimestre 4
		int cuatrimestreMateriaNeto = (solic.getMateriaSolicitada().getAnio() - 1) * 2 + solic.getMateriaSolicitada().getCuatrimestre();
		    
		
		// Si la materia está en la primera mitad de la carrera, queda exenta del 60%
		if (cuatrimestreMateriaNeto <= cuatrimestreMitad) {
		   	return new ResultadoRegla(true, "Exento: El requisito del 60% aprobado no aplica para materias de la primera mitad de la carrera.");
		}
		    
		    //Si pasó la mitad de la carrera, recién ahí exigimos el 60% real:
		int totalMaterias = plan.getCatalogoMaterias().tamanio();
		int aprobadas = solic.getAlumno().getHistorial().getContadorAprobadas();
		double porcentaje = ((double) aprobadas / totalMaterias) * 100;
		
		if (porcentaje >= 60.0) {
		   	return new ResultadoRegla(true, "El alumno posee el " + String.format("%.1f", porcentaje) + "% de la carrera aprobada (Mínimo: 60%).");
		}else {
		   	return new ResultadoRegla(false, "Al ser una materia del ciclo avanzado, se requiere el 60% aprobado. El alumno posee el " + String.format("%.1f", porcentaje) + "%.");
		}
		
	}

}
