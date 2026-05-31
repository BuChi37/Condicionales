package modelo;

import carga.Lector;
import infraestructura.GrafoDirigido;
import infraestructura.Lista1DLinkedL;
import infraestructura.ListaDoubleLinkedL;

public class PlanEstudio {
	private GrafoDirigido correlativas;
	private CatalogoMaterias catMaterias;
	private ListaAlumnos alumnos;
	private RegistroSolicitudes regSolicitudes; //todavia no la use
	
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
	    
	}
	
	/*
	public void inicializarCorrelativas() {
	    this.correlativas =new GrafoDirigido(this.catMaterias.tamanio());
	}
	*/
	
	public CatalogoMaterias getCatalogoMaterias() {
        return this.catMaterias;
    }

    public RegistroSolicitudes getRegistroSolicitudes() {
        return this.regSolicitudes;
    }
    
    /*
	public void agregarMateria(Materia materia) {
		if(!catMaterias.existe(materia)) {
	        catMaterias.insertar(materia);
	    }
	}

	public void agregarAlumno(Alumno alumno) {
	    alumnos.insertar(alumno,alumnos.tamanio() );
	}
	*/
	
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
	
	/*
	public void agregarCorrelatividad(Materia origen,Materia destino,TipoCondicion tipo) {
	    int i = catMaterias.obtenerIndice(origen);
	    int j = catMaterias.obtenerIndice(destino);

	    correlativas.agregarArco(i,j,tipo);
	}
	*/
	
	public TipoCondicion obtenerCondicionRequerida( Materia origen,Materia destino) {

	    int i = catMaterias.obtenerIndice(origen);
	    int j = catMaterias.obtenerIndice(destino);

	    return (TipoCondicion)correlativas.obtenerArco(i,j);
	}
	
	public boolean existeCorrelatividad( Materia origen, Materia destino) {

		int i = catMaterias.obtenerIndice(origen);
		int j = catMaterias.obtenerIndice(destino);

	    return correlativas.existeArco(i,j); 
	}
	
	public ListaDoubleLinkedL obtenerCorrelativasDirectas(Materia materia) {

	    ListaDoubleLinkedL resultado = new ListaDoubleLinkedL();

	    int destino = catMaterias.obtenerIndice(materia);
	    
	    if(destino == -1) {
	        return resultado;
	    }

	    for(int origen=0; origen<catMaterias.tamanio();origen++) {

	        if(correlativas.existeArco(origen,destino)) { 

	        	resultado.insertar(catMaterias.obtenerMateria(origen),resultado.tamanio());
	        }
	    }

	    return resultado;
	}
	
	
}
