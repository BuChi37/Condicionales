package modelo;

import infraestructura.Lista2DLinkedL;
import infraestructura.ListaDoubleLinkedL;

public class HistorialAlumnos extends Lista2DLinkedL{

	@Override
	public boolean iguales(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() ==  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
	}

	@Override
	public boolean esMenor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() <  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
	}

	@Override
	public boolean esMayor(Object elemento1, Object elemento2) {
		
		return   (   ((EstadoMateria)elemento1).getCodigoMateria() >  ((EstadoMateria)elemento2).getCodigoMateria()  )  ;
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

	public void ModificarEstado(EstadoAcademico estado , int codigo) {
		
		EstadoMateria estadoMateria = getEstado(codigo);
		estadoMateria.setEstado(estado);
	}
	
	
	public boolean estaAprobado(int codigo) {
		
		EstadoMateria estado = getEstado(codigo); 
		
		if(estado != null) {
			
			return (estado.getEstado() == EstadoAcademico.APROBADA);
		}
		
		System.out.println("esa materia no pertenece al alumno");
		
		return false;
	}
	public int getContadorAprobadas() {
		int aprobadas=0;
		for(int i=0;i<this.tamanio();i++) {
			if(this.estaAprobado(i+1)) {
				aprobadas++;
			}
		}
		return aprobadas;
	}
	
	public boolean estaRegular(int codigo) {
		
		EstadoMateria estado = getEstado(codigo); 
		
		if(estado != null) {
			
			return (estado.getEstado() == EstadoAcademico.REGULAR);
		}
		
		System.out.println("esa materia no pertenece al alumno");
		
		return false;
	}
	
	
	public String toString() {
		
		String cadena="";
		for(int i=0 ; i< tamanio() ; i++) {
			
			cadena+= devolver(i).toString();
			cadena+= "  ";
		}
		
		return cadena;
	}
	
}
