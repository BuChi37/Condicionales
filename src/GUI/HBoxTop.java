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
	private Button btnAlumno;
	private Button btnPlan;
	private Button btnSoli;
	
	// panel 1
	// panel 2
	
	public HBoxTop(PlanEstudio plan, BorderPane principal) {
		this.pla=plan;
		this.principal=principal;
		setAlignment(Pos.CENTER_RIGHT);
		
		setPadding(new Insets(0,10,0,0));
		
		mostrar();
		
	}
	
	
	public void mostrar() {
		cargarHBoxBotones();
		javafx.application.Platform.runLater(() -> {
	        this.btnSoli.fire();
	        
	    });
		
	    
	}
	
	
	
	public void cargarHBoxBotones() {
		HBox panelBotones = new HBox();
		
		panelBotones.setAlignment(Pos.TOP_RIGHT);
		panelBotones.setPadding( new Insets(10,0,0,0));
		panelBotones.setSpacing(20);
		
		this.btnAlumno = new Button();
		cargarBotonAlumno(this.btnAlumno);
		
		this.btnPlan = new Button();
		cargarBotonPlan(this.btnPlan);
		
		this.btnSoli= new Button();
		cargarBotonSoli(this.btnSoli);
		
		
		
		
		
		
		panelBotones.getChildren().addAll(btnAlumno, btnPlan, btnSoli);
		getChildren().add(panelBotones);
		
	}
	
	
	//crear los botones 
	
	//primer boton:
	
	public void cargarBotonAlumno(Button boton) {
		boton.setText("Alumnos");
		boton.getStyleClass().add("btn-Menu");
		
		boton.setOnAction(EventLayoutAlumno ->{
			
			BorderPane panelAlumnos= new BorderPaneAlumno(pla);
			
			principal.setCenter(panelAlumnos);
			
			btnAlumno.setDisable(false);
		    btnPlan.setDisable(false);
		    btnSoli.setDisable(false);
		    
		    btnAlumno.setDisable(true);
			
		} );
		
	}
	
	//segundo boton
	public void cargarBotonPlan(Button boton) {
		 boton.setText("Plan de Estudio");
		    boton.getStyleClass().add("btn-Menu");

		    /*
		    boton.setOnAction(event -> {

		    	BorderPanePlanEstudio panelPlan =new BorderPanePlanEstudio(pla);

		        principal.setCenter(panelPlan);
		        

		    } ); */
		    
		    //prueba nueva interfaz:
		    boton.setOnAction(event -> {

		        VBoxPlanEstudioCards vista =new VBoxPlanEstudioCards(pla);

		        principal.setCenter(vista);
		        btnAlumno.setDisable(false);
			    btnPlan.setDisable(false);
			    btnSoli.setDisable(false);
			    
			    btnPlan.setDisable(true);

		    });
		
	}
	
	
	//tercer boton
	public void cargarBotonSoli(Button boton) {
		boton.setText("Solicitudes");
		boton.getStyleClass().add("btn-Menu");
		
		boton.setOnAction(EventLayoutSoli->{
			
			BorderPane panelSoli= new BorderPaneSolicitud(pla);
			
			principal.setCenter(panelSoli);
			
			btnAlumno.setDisable(false);
		    btnPlan.setDisable(false);
		    btnSoli.setDisable(false);
		    
		    btnSoli.setDisable(true);
			
		} );
		
	}
		
	
	
	
	
	
	//para esterilizar los botones
	public void styleButton(Button button) {
		
		
		
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
