package carga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import infraestructura.GrafoDirigido;
import infraestructura.MatrizGrafo;
import modelo.Alumno;
import modelo.CatalogoMaterias;
import modelo.EstadoAcademico;
import modelo.EstadoMateria;
import modelo.HistorialAlumnos;
import modelo.ListaAlumnos;
import modelo.Materia;
import modelo.TipoCondicion;

/**
 * Responsable de cargar la información académica desde archivos CSV.
 *
 * Permite construir las estructuras principales del sistema:
 * - Catálogo de materias.
 * - Grafo de correlatividades.
 * - Alumnos e historiales académicos.
 *
 * Actúa como capa de acceso a datos durante la inicialización
 * del PlanEstudio.
 */
public class Lector {
	
	/**
	 * Cuenta la cantidad de materias registradas en el archivo.
	 *
	 * El valor obtenido se utiliza para determinar el tamaño
	 * necesario del grafo de correlatividades.
	 *
	 * @param ruta directorio donde se encuentra Materias.csv.
	 * @return cantidad de materias encontradas.
	 */
	public int  leerTamanio(String ruta) {
		
		String rutaMaterias = ruta +"/Materias.csv";
        int tamanio = 0;
                
        try(BufferedReader br = new BufferedReader(new FileReader(rutaMaterias))){
        
            String linea;
            
            while(  (linea = br.readLine() ) != null){
                
                tamanio++;
            }
        
        }catch(Exception e){
            System.out.println(e);
        }
        
        return tamanio;
		
	}
	
	/**
	 * Carga las correlatividades académicas dentro del grafo.
	 *
	 * Cada registro del archivo representa una relación entre
	 * dos materias y una condición requerida:
	 * - R: Regularizada.
	 * - A: Aprobada.
	 *
	 * La información se transforma en un arco dirigido dentro
	 * del grafo de correlatividades.
	 *
	 * @param ruta directorio donde se encuentra el archivo.
	 * @param grafo grafo que recibirá las correlatividades.
	 */
	public void leerCorrelativa(String ruta, GrafoDirigido grafo) {
		String rutaCorrelativa = ruta + "/TUP.csv";
		
		 try(BufferedReader brA= new BufferedReader(new FileReader(rutaCorrelativa) ) ){
	            String linea;
	                     
	            while(  (linea= brA.readLine())!= null ){
	            	
	                if(linea.startsWith("\uFEFF")){
	                    linea = linea.substring(1);
	                }
	                String[] datos = linea.split(";");
	                
	                
	                if(datos.length == 3) {
	                	
	                	int id = Integer.parseInt(datos[0].trim());
		                char estadoChar = datos[1].charAt(0);
		                int idOtra = Integer.parseInt(datos[2].trim());
		                
		                int estadoInt = 0;
		                
		                if(estadoChar == 'R') estadoInt =1;
		                else estadoInt=2;
		                
		                grafo.agregarArco(idOtra, id, estadoInt);
		                
	                }else System.out.println("error");
	                
	               
	            }
	        
	        }catch(Exception e){
	            System.out.println(e +" 1");
	        }
		
		
	}
	
	/**
	 * Carga las materias del plan de estudios desde archivo
	 * y las incorpora al catálogo de materias.
	 *
	 * @param ruta directorio donde se encuentra Materias.csv.
	 * @param lista catálogo donde se almacenarán las materias.
	 */
	public void leerMateria(String ruta, CatalogoMaterias lista) {
		
		String rutaMateria = ruta +"/Materias.csv";
		
		try(BufferedReader br = new BufferedReader( new FileReader(rutaMateria) ) ){
			
			String linea;
			
			while( (linea = br.readLine())!= null ) {
				
				if(linea.startsWith("\uFEFF")){
                    linea = linea.substring(1);
                }
				
				String[] datos = linea.split(";");
				
				int codigoMateria  = Integer.parseInt(datos[0].trim());
				String nombre = datos[1];
				int anio = Integer.parseInt(datos[2].trim());
				int cuatrimestre = Integer.parseInt(datos[3].trim());
				
				Materia materia = new Materia(codigoMateria, nombre, anio,cuatrimestre);
				
				lista.AgregarMateria( materia);
				
			}
			
			
		}catch (Exception e) {
			System.out.println(e+" 2");
		}
		
	}
	
	/**
	 * Carga los alumnos registrados en el sistema.
	 *
	 * Por cada alumno leído se carga también su historial
	 * académico correspondiente.
	 *
	 * @param ruta directorio donde se encuentran los datos.
	 * @param listaAlumnos colección donde se almacenarán
	 *                     los alumnos cargados.
	 */
	public void leerAlumnos(String ruta, ListaAlumnos listaAlumnos){
        String rutaAlumno= ruta+"/alumnos.csv";
        
        try(BufferedReader brA= new BufferedReader(new FileReader(rutaAlumno) ) ){
            String linea;
         
            
            while(  (linea= brA.readLine())!= null ){
                  
            	
                if(linea.startsWith("\uFEFF")){
                    linea = linea.substring(1);
                }
                String[] datos = linea.split(";");
                
                
                int id = Integer.parseInt(datos[0].trim());
                
                String nombre = datos[1];
                
                Alumno alumno = new Alumno(id, nombre);
              
                String rutaHistorial = ruta+"/Historiales"+"/"+id+"H.csv"; 
                
                
                leerHistorial(rutaHistorial, alumno);
                
                listaAlumnos.insertar(alumno);
               
            }
        
        }catch(Exception e){
            System.out.println(e+" 3");
        }
    }
	
	/**
	 * Carga el historial académico de un alumno.
	 *
	 * Cada registro del archivo indica una materia y el estado
	 * académico alcanzado por el alumno en dicha materia.
	 *
	 * @param ruta archivo de historial a procesar.
	 * @param alumno alumno que recibirá los estados académicos.
	 */
	private void leerHistorial (String ruta, Alumno alumno) {
		
        try(BufferedReader brA= new BufferedReader(new FileReader(ruta) ) ){
            String linea;
          
            while(  (linea= brA.readLine())!= null ){
                               
                if(linea.startsWith("\uFEFF")){
                    linea = linea.substring(1);
                }
                String[] datos = linea.split(";");
                
                int codigo = Integer.parseInt(datos[0].trim());
                int estado = Integer.parseInt(datos[1].trim());
                
                EstadoAcademico estadoAcademico = EstadoAcademico.values()[estado];
                
                EstadoMateria estadoMateria = new EstadoMateria(codigo, estadoAcademico);
                
                alumno.agregarEstado(estadoMateria);
                
            }
        
        }catch(Exception e){
            System.out.println(e+" 4");
        }
		
		
	}
}
