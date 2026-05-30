package carga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	
	private void leerCorrelativa(String ruta, MatrizGrafo matriz) {
		String rutaCorrelativa = ruta + "/TUP.csv";
		
		 try(BufferedReader brA= new BufferedReader(new FileReader(rutaCorrelativa) ) ){
	            String linea;
	         
	            
	            while(  (linea= brA.readLine())!= null ){
	                  
	                
	                if(linea.startsWith("\uFEFF")){
	                    linea = linea.substring(1);
	                }
	                String[] datos = linea.split(";");
	                
	                
	                int id = Integer.parseInt(datos[0].trim());
	                char estadoChar = datos[1].charAt(0);
	                int idOtra = Integer.parseInt(datos[2].trim());
	                
	                int estadoInt = 0;
	                if(estadoChar == 'R') estadoInt =1;
	                else estadoInt=2;
	                
	                TipoCondicion tipoCondicion = TipoCondicion.values()[estadoInt];
	                matriz.actualizar(tipoCondicion,idOtra , id);
	                
	                
	               
	            }
	        
	        }catch(Exception e){
	            System.out.println(e);
	        }
		
		
	}
	private void leerMateria(String ruta, CatalogoMaterias lista) {
		
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
				
				Materia materia = new Materia(codigoMateria, nombre, anio);
				
				lista.AgregarMateria( materia);
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	private void leerAlumnos(String ruta, ListaAlumnos listaAlumnos){
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
              
                String rutaHistorial = ruta+".Historiales"+"/"+id+"H.csv"; 

                
                
                
                leerHistorial(rutaHistorial, alumno);
                
                listaAlumnos.insertar(alumno);
               
            }
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
	
	private void leerHistorial (String ruta, Alumno alumno) {
		String rutaAlumno= ruta+"/alumnos.csv";
        try(BufferedReader brA= new BufferedReader(new FileReader(rutaAlumno) ) ){
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
