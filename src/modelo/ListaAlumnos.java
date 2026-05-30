package modelo;

import infraestructura.Lista2DLinkedL;

public class ListaAlumnos extends Lista2DLinkedL{

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() ==  ((Alumno)elemento2).getLegajo()  );
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() <  ((Alumno)elemento2).getLegajo()  );
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		return  (  ((Alumno)elemento1).getLegajo() >  ((Alumno)elemento2).getLegajo()  );
	}
	
	
	
}
