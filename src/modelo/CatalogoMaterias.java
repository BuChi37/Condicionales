package modelo;

import infraestructura.Lista2DLinkedL;


public class CatalogoMaterias extends Lista2DLinkedL{

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		
		return     (  ((Materia)elemento1).getCodigo() == ((Materia)elemento2).getCodigo()   );
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		
		return ((Materia)elemento1).getCodigo() < ((Materia)elemento2).getCodigo();
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		
		return ((Materia)elemento1).getCodigo() > ((Materia)elemento2).getCodigo();
	}

}
