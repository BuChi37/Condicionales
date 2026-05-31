package infraestructura;

import carga.Lector;

public class GrafoDirigido extends AbsGrafoD{
	public GrafoDirigido(int ordenGrafo){
		super(ordenGrafo);
		
		
		
		
	}
	
	
	public void cargarGrafo(String ruta){ 
	
		Lector lector = new Lector();
		
		String rutaCorrelativas = ruta;
		lector.leerCorrelativa(rutaCorrelativas, this.matrizCosto);
		
		
	}

	public void agregarArco(int origen,int destino,Object costo) { //metodo agregado
	    matrizCosto.actualizar(costo,origen,destino);
	}
	
	public boolean existeArco(int origen,int destino) { //metodo agregado
		 return matrizCosto.areConnected(origen,destino);
	}
	
	public Object obtenerArco(int origen,int destino) { //metodo agregado
	    return matrizCosto.devolver( origen,destino);
	}
	
}

