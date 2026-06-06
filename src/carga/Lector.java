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


public class Lector {
	
	
	
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
	            System.out.println(e);
	        }
		
		
	}
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
				String cuatrimestre = datos[3];
				Materia materia = new Materia(codigoMateria, nombre, anio,cuatrimestre);
				
				lista.AgregarMateria( materia);
				
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
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
            System.out.println(e);
        }
    }
	
	private void leerHistorial (String ruta, Alumno alumno) {
		
        try(BufferedReader brA= new BufferedReader(new FileReader(ruta) ) ){
            String linea;
         
            
            while(  (linea= brA.readLine())!= null ){
                  
                
                if(linea.startsWith("\uFEFF")){
                    linea = linea.substring(1);
                }
                String[] datos = linea.split(";");
                
                int Codigo = Integer.parseInt(datos[0].trim());
                int estado = Integer.parseInt(datos[1].trim());
                
                EstadoAcademico estadoAcademico = EstadoAcademico.values()[estado];
                
                EstadoMateria estadoMateria = new EstadoMateria(Codigo, estadoAcademico);
                
                alumno.agregarEstado(estadoMateria);
                
            }
        
        }catch(Exception e){
            System.out.println(e);
        }
		
		
	}
}
