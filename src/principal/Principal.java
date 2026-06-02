package principal;

import GUI.HBoxTop;

import GUI.VBoxBienvenido;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.PlanEstudio;

public class Principal extends Application{
	
	PlanEstudio planEstudio ;
	
	
	public void init() {
		planEstudio =  new PlanEstudio("Datos");
		
		
		
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
