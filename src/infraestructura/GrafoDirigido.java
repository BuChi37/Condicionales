package infraestructura;

import java.util.Scanner;

public class GrafoDirigido extends AbsGrafoD{
	public GrafoDirigido(int ordenGrafo){
		super(ordenGrafo);
	}
	
	@Override
	public void cargarGrafo(){ //se modificara para asociarla al lector
		double currCost;		
		Scanner scanner = new Scanner(System.in);
		
		for (int i=0; i<getOrden();i++){
			for (int j=0;j<getOrden();j++){
				if (i!=j){
					System.out.println("Ingrese costo[" + i + "," + j + "] (sino -1)");
					currCost=scanner.nextDouble();
					if (currCost!=-1){
						this.matrizCosto.actualizar(currCost, i, j);	
					}else{
						this.matrizCosto.actualizar(infinito, i, j);
					}					
				}else{
					this.matrizCosto.actualizar(infinito, i, j);
				}
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

