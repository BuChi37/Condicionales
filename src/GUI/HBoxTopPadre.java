package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Alumno;
import modelo.PlanEstudio;

public class HBoxTopPadre extends HBox{
	
	private BorderPane principal;
	
	private PlanEstudio planEstudio;
	
	public HBoxTopPadre(BorderPane principal, PlanEstudio plan) {
		
		this.principal = principal;
		this.planEstudio = plan;
		
		mostrar();
	}
	
	
	public void mostrar() {
		this.setStyle("-fx-background-color: #23001e;");
		setPrefHeight(60);
		setPrefWidth(Double.MAX_VALUE);
		
		setPadding(new Insets(10,10,10,10));
		setSpacing(10);
		setAlignment(Pos.CENTER_RIGHT);
		
		Button boton1 = btnAlumnos();
		
		Button boton2 = new Button();
		StyleButton(boton1);
		StyleButton(boton2);
		
		getChildren().addAll(boton1,boton2);
		
	}
	
	public void StyleButton(Button boton) {
		
		boton.setPrefHeight(Double.MAX_VALUE);
		boton.setPrefWidth(100);
	}
	
	public Button btnAlumnos() {
		
		Button boton = new Button("alumnos");
		StyleButton(boton);
		
		boton.setOnAction(evenet -> {
			
			VBox panel = new VBoxPanelAlumnos(planEstudio);
			
			
			this.principal.setCenter(panel);
			
		} );
		
		
		return boton;
	}
	
	
}
