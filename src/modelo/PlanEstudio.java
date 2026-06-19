package modelo;

import carga.Lector;
import infraestructura.GrafoDirigido;
import infraestructura.ListaDoubleLinkedL;

/**
 * Representa el plan de estudios de la carrera.
 * Gestiona el catálogo de materias, las correlatividades,
 * los alumnos y las solicitudes académicas.
 */

public class PlanEstudio {
	
	private GrafoDirigido correlativas; //Grafo dirigido que almacena las correlatividades entre materias.
	
	private CatalogoMaterias catMaterias; //Catálogo que contiene todas las materias del plan de estudios.
	
	private ListaAlumnos alumnos; //Colección de alumnos registrados en el sistema.
	
	private RegistroSolicitudes regSolicitudes; //Registro de solicitudes de condicionalidad realizadas por alumnos.
	
	
	/**
	 * Construye un PlanEstudio a partir de los archivos ubicados en la ruta directorio indicada.
	 */
	public PlanEstudio(String ruta) {
		Lector lector = new Lector();
		
		
	    this.catMaterias = new CatalogoMaterias();
	    String rutaCatalogo = ruta;
	    lector.leerMateria(rutaCatalogo, this.catMaterias);
	    
	    
	    this.alumnos = new ListaAlumnos();
	    String rutaAlumnos = ruta+"/Alumnos";
	    lector.leerAlumnos(rutaAlumnos, alumnos);
	    
	    
	    this.regSolicitudes = new RegistroSolicitudes();
	    
	    this.correlativas = new GrafoDirigido(lector.leerTamanio(ruta) +1);
	    this.correlativas.cargarGrafo(ruta);
	    lector.leerCorrelativa(ruta, correlativas);
	    
	}
	
	
	public CatalogoMaterias getCatalogoMaterias() {
        return this.catMaterias;
    }
	

    public RegistroSolicitudes getRegistroSolicitudes() {
        return this.regSolicitudes;
    }
    
    /* Busca un alumno por número de legajo.*/
	public Alumno buscarAlumno(int legajo) {
		
	    Alumno resultado = null;
	    int i = 0;

	    while(i < alumnos.tamanio() && resultado == null) {
	        Alumno alumno =(Alumno) alumnos.devolver(i);

	        if(alumno.getLegajo() == legajo) {
	            resultado = alumno;
	        } 
	        i++;
	    }

	    return resultado;
	}
	
	/**
	 * Obtiene la condición académica exigida para cursar
	 * una materia destino que requiere la correlatividad
	 * a partir de una materia origen correlativa
	 */
	public TipoCondicion obtenerCondicionRequerida( Materia origen, Materia destino) {

	    int i = catMaterias.obtenerIndice(origen);
	    int j = catMaterias.obtenerIndice(destino);

	    return TipoCondicion.values()[correlativas.obtenerArco(i,j)];
	}
	
	/**
	 * Determina si existe una correlatividad directa entre
	 * dos materias.
	 */
	public boolean existeCorrelatividad( Materia origen, Materia destino) {

		int i = catMaterias.obtenerIndice(origen);
		int j = catMaterias.obtenerIndice(destino);

	    return correlativas.existeArco(i,j); 
	}
	
	/**
	 * Obtiene todas las materias que constituyen correlatividades
	 * directas de la materia indicada.
	 */
	public ListaDoubleLinkedL obtenerCorrelativasDirectas(Materia materia) {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    int destino = catMaterias.obtenerIndice(materia);
	    
	    if(destino == -1) {
	        return resultado;
	    }

	    for(int origen=0; origen<catMaterias.tamanio();origen++) {

	        if(correlativas.existeArco(origen,destino)) { 

	        	resultado.insertar(catMaterias.getMateria(origen),resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	/**
	 * Obtiene todas las materias alcanzables desde la materia
	 * indicada mediante un recorrido en anchura (BEA).
	 */
	public ListaDoubleLinkedL obtenerMateriasRelacionadas(Materia materiaOrigen) {
	    // 1. Traducimos la materia al índice numérico
	    int ind = catMaterias.obtenerIndice(materiaOrigen);
	    
	    
	    
	    // 2. Ejecutamos el BEA una SOLA vez y retornamos toda la lista de marcas (true/false)
	    return correlativas.devolverBEA(ind);
	}
}
