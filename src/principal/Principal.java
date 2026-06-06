package principal;

import GUI.HBoxTop;

import GUI.VBoxBienvenido;
import infraestructura.ListaDoubleLinkedL;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.CatalogoMaterias;
import modelo.Materia;
import modelo.PlanEstudio;

public class Principal extends Application{
	
	PlanEstudio planEstudio ;
	
	/*
	public void init() {
		planEstudio =  new PlanEstudio("Datos");
		
	}*/
	 
	//prueba vista PlanEstudio:
	@Override
	public void init() {

	    planEstudio = new PlanEstudio("Datos");

	    Materia materia =planEstudio.getCatalogoMaterias().getMateria(1);
	    System.out.println("Materia: " + materia.getNombre());

	    CatalogoMaterias catalogo = planEstudio.getCatalogoMaterias();
	    System.out.println("Materias: " + catalogo.tamanio());
	    
	    ListaDoubleLinkedL lista = planEstudio.obtenerCorrelativasDirectas(materia);

	    for(int i=0; i<lista.tamanio(); i++) {

	        Materia m =(Materia) lista.devolver(i);

	        System.out.println("Correlativa: " + m.getNombre());
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane principal = new BorderPane();
		principal.setPrefSize(1280, 720);
		principal.getStyleClass().add("bg-Main");
		
		
		HBox topBotones = new HBoxTop(planEstudio, principal);
		
		VBox Bienvenido  = new VBoxBienvenido();
		
		
		principal.setTop(topBotones);
		principal.setCenter(Bienvenido);
		
		
		String css = this.getClass().getResource("/resource/estilos.Css").toExternalForm();
		
		Scene scene = new Scene(principal);
		scene.getStylesheets().add(css);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	

	public static void main(String[] args) {
		
		launch(args);
	}

}
