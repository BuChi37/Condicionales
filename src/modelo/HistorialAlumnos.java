package modelo;

import infraestructura.Lista2DLinkedL;

public class HistorialAlumnos extends Lista2DLinkedL{

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() ==  ((EstadoMateria)elemento1).getCodigo()  )  ;
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() <  ((EstadoMateria)elemento1).getCodigo()  )  ;
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigo() >  ((EstadoMateria)elemento1).getCodigo()  )  ;
	}

}
