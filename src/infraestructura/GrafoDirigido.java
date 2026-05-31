package infraestructura;



public class GrafoDirigido extends AbsGrafoD{
	public GrafoDirigido(int ordenGrafo){
		super(ordenGrafo);
		
		
		
		
	}
	
	
	public void cargarGrafo(String ruta){ 

		for(int fila = 1; fila < getOrden() ; fila++) {
			
			for(int columna =1; columna < getOrden(); columna++) {
				
				agregarArco(fila, columna, 0);
			}
		}
		
		
		
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

