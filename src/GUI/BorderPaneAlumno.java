package GUI;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import modelo.Alumno;
import modelo.PlanEstudio;

public class BorderPaneAlumno extends BorderPane{
	
	private PlanEstudio plan;
	private Alumno AlumnoActual;
	private TextField txtBuscador;
	private StackPane panelAlumno;
	
	
	public BorderPaneAlumno(PlanEstudio plan) {
		
		this.plan = plan;
		this.AlumnoActual= null;
		
		
		this.panelAlumno = new StackPane();
		
		setCenter(panelAlumno);
		
		mostrar();
		
	}
	
	
	public void mostrar() {
		
		panelAlumno.setStyle("-fx-background-color: #0c062e;");
		
		
		HBox panelBuscador= new HBox();
		crearPanelBuscador(panelBuscador);
		
		
		panelAlumno.getChildren().add(panelBuscador);
		
		
		
	}
	
	
	public void crearPanelBuscador(HBox panelHBox) {
		panelHBox.setStyle(	"-fx-background-color: #190d5d;" 
				+ "-fx-border-radius: 20px 20px 20px 20px;"
				+"-fx-background-radius: 20 20 20 20;");

		panelHBox.setMaxSize(700, 50);
		
		
		this.txtBuscador  = new TextField();
		creadorTxtBuscador(txtBuscador);
		
		Button btnBuscar = new Button();
		creadorBtnBuscador(btnBuscar);
		
		
		HBox.setHgrow(txtBuscador, Priority.ALWAYS);
		panelHBox.getChildren().addAll(txtBuscador,btnBuscar);
		panelHBox.setPadding(new Insets(10,10,10,10));
		panelHBox.setSpacing(10);
		
		
		
		
		
	}
	public void creadorBtnBuscador(Button btnBus) {
		btnBus.setMinWidth(40);
		btnBus.setPrefHeight(Double.MAX_VALUE);
		
		btnBus.setOnAction(evento ->{
			String textoIngresado = this.txtBuscador.getText();
			
		} );
	}
	public void creadorTxtBuscador(TextField txtbus) {
		txtbus.setPrefHeight(Double.MAX_VALUE);
		txtbus.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
		txtbus.setPromptText("ingresar nuemro de legajo");
		
		
	}
	
}
