package modelo;

import infraestructura.Lista2DLinkedL;

public class HistorialAlumnos extends Lista2DLinkedL{

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() ==  ((EstadoMateria)elemento2).getCodigo()  )  ;
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() <  ((EstadoMateria)elemento2).getCodigo()  )  ;
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() >  ((EstadoMateria)elemento2).getCodigo()  )  ;
	}
	
	
	public EstadoMateria getEstado(int codigo) {
		
		EstadoMateria estado  = new EstadoMateria(codigo, null);
		
		int pos = buscar(estado);
		
		if(pos != -1) {
			EstadoMateria materiaDevuelta = (EstadoMateria) devolver(pos);
			
			return materiaDevuelta;
		}
		
		return null;
	}

	
	public boolean estaAprobado(int codigo) {
		
		EstadoMateria estado = getEstado(codigo); 
		
		if(estado != null) {
			
			return (estado.getEstado() == EstadoAcademico.APROBADA);
		}
		
		System.out.println("esa materia no pertenece al alumno");
		
		return false;
	}
}
