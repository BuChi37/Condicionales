package GUI;





import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modelo.PlanEstudio;

public class HBoxTop extends HBox{
	private PlanEstudio pla;
	private BorderPane principal;
	
	// panel 1
	// panel 2
	
	public HBoxTop(PlanEstudio plan, BorderPane principal) {
		this.pla=plan;
		this.principal=principal;
		setAlignment(Pos.CENTER_RIGHT);
		setStyle("-fx-background-color: #0c062e;");
		
		
		mostrar();
		
	}
	
	
	public void mostrar() {
		cargarHBoxBotones();
		
	}
	
	
	
	public void cargarHBoxBotones() {
		HBox panelBotones = new HBox();
		panelBotones.setStyle("-fx-background-color: #190d5d;"+"-fx-border-radius: 0px 0px 0px 20px;"+"-fx-background-radius: 0 0 0 20;");
		panelBotones.setAlignment(Pos.TOP_RIGHT);
		panelBotones.setPadding( new Insets(10,10,10,60));
		panelBotones.setSpacing(20);
		
		Button boton1 = new Button();
		cargarBotonAlumno(boton1)
		;
		Button boton2 = new Button();
		styleButton(boton2);
		Button boton3 = new Button();
		styleButton(boton3);
		
		
		
		
		
		
		panelBotones.getChildren().addAll(boton1, boton2, boton3);
		getChildren().add(panelBotones);
		
	}
	
	
	//crear los botones 
	
	//primer boton:
	public void cargarBotonAlumno(Button boton) {
		boton.setText("Alumnos");
		styleButton(boton);
		boton.setOnAction(EventLayoutAlumno ->{
			
			BorderPane panelAlumnos= new BorderPaneAlumno(pla);
			
			principal.setCenter(panelAlumnos);
			
		} );
		
	}
	
	//segundo boton
	public void cargarBotonPlan(Button boton) {
		
		
	}
	
	
	//tercer boton
	public void cargarBotonSoli(Button boton) {
		
		
	}
		
	
	
	
	
	
	//para esterilizar los botones
	public void styleButton(Button button) {
		
		button.setPrefSize(160,40);
		button.setStyle(
				"-fx-background-color: #23108b;"
				+"-fx-border-radius: 20px;"
				+"-fx-background-radius: 20px;"	
				+"-fx-text-fill: white;"
				+"-fx-border-color: #0c062e ;"
				+"-fx-border-width : 4px"
				);
		button.setCursor(Cursor.HAND);
		
		//eventos para cuando el cursor este sobre el botont
		button.setOnMouseEntered(e -> {
	        button.setTranslateY(-5); 
	        button.setEffect(new javafx.scene.effect.DropShadow(20,0,5, javafx.scene.paint.Color.BLACK));
	    });

	    // para cuando saco el cursor del boton
	    button.setOnMouseExited(e -> {
	        button.setTranslateY(0);
	        button.setEffect(null); 
	    });
		
	}
	
}
