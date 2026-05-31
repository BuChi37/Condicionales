package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HBoxTopPadre extends HBox{
	
	private BorderPane principal;
	
	public HBoxTopPadre(BorderPane principal) {
		
		this.principal = principal;
		mostrar();
	}
	
	
	public void mostrar() {
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
			
			VBox panel = new VBox();
			Button hool = new Button("hola");
			
			panel.setPrefHeight(600);
			panel.setPrefWidth(600);
			
			panel.setMaxWidth(1200);
			panel.setMaxWidth(900);
			
			panel.setMinWidth(600);
			
			panel.getChildren().add(hool);
			this.principal.setCenter(panel);
			
		} );
		
		
		return boton;
	}
	
	
}
