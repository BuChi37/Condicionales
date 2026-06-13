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
	
	public int obtenerIndice(Materia materia) { //getCodigo
		
		return buscar(materia)+1; //mas 1 para correspondencia con los indices de la matriz
	}

	public boolean existe(Materia materia) {
		return buscar(materia) != -1;
	}
	
	public Materia devolverMateria(String nombre) {
		
		for(int i=1;i<=super.tamanio();i++) {
			if (this.getMateria(i).esIgual(nombre)) {
	            return this.getMateria(i); // ¡La encontramos! La devolvemos inmediatamente
	        }
		}
		return null;
	}
	
	public Materia getMateria(int codigo) { //obtiene materia por codigo
		
		Materia  materia = new Materia( codigo, " ", 0,0);
		
		int pos = buscar(materia);
		
		if(pos != -1) {
			
			Materia materiaDevuelta = (Materia) devolver(pos);
			
			return materiaDevuelta;
		}
		
		else return null;
	}
	
	public void AgregarMateria( Materia materia) {
		
		insertar(materia);
		
	}
}
