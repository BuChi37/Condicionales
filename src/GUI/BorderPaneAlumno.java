package GUI;





import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.Alumno;
import modelo.EstadoAcademico;
import modelo.EstadoMateria;
import modelo.HistorialAlumnos;
import modelo.Materia;
import modelo.PlanEstudio;

public class BorderPaneAlumno extends BorderPane{
	
	private PlanEstudio plan;
	private Alumno AlumnoActual;
	private TextField txtBuscador;
	private StackPane panelAlumnoCenter;
	private StackPane panelAlumnoTop;
	
	private HBox HBoxBuscador;
	private BorderPane BorderPaneAlumno;
	
	
	public BorderPaneAlumno(PlanEstudio plan) {
		
		this.plan = plan;
		this.AlumnoActual= null;
		
		
		this.panelAlumnoCenter = new StackPane();
		this.panelAlumnoTop = new StackPane();
		
		setCenter(panelAlumnoCenter);
		setTop(panelAlumnoTop);
		this.setPadding(new Insets(20,0,0,0));
		
		
		
		
		mostrar();
		
	}
	
	
	public void mostrar() {
		
		
		panelAlumnoCenter.setPadding(new Insets(20));
		panelAlumnoTop.setPadding(new Insets(20));
		
		
		this.HBoxBuscador= new HBox();
		crearPanelBuscador(this.HBoxBuscador);
		
		
		panelAlumnoCenter.getChildren().add(this.HBoxBuscador);
		
		
		
	}
	
	
	
	
	
	
	
	
	public void crearPanelBuscador(HBox panelHBox) {
		//panelHBox.setStyle(	"-fx-background-color: #190d5d;" 
		//		+ "-fx-border-radius: 20px 20px 20px 20px;"
		//		+"-fx-background-radius: 20 20 20 20;");

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
			
			try {
				
				int legajo= Integer.parseInt(textoIngresado);
				
				this.AlumnoActual = plan.buscarAlumno(legajo);
				
				if(this.AlumnoActual != null) {
					
					this.panelAlumnoCenter.getChildren().clear();
					this.panelAlumnoTop.getChildren().clear();
					
					this.panelAlumnoTop.getChildren().add(this.HBoxBuscador);
					
					createBorderPaneAlumno();
					
				}
				
			}catch (NumberFormatException e) {
				System.out.println(e);
			}
			
		} );
	}
	public void creadorTxtBuscador(TextField txtbus) {
		txtbus.setPrefHeight(Double.MAX_VALUE);
		txtbus.setPadding(new Insets( 10,10,10,10));
		txtbus.getStyleClass().add("TxtField");
		
		txtbus.setPromptText("ingresar nuemro de legajo");
		
		
	}
	
	// crear el panel que contiene a los alumnos 
	public void createBorderPaneAlumno() {
		BorderPane alumnoInfo = new BorderPane();
		
		alumnoInfo.setMaxWidth(800);
		alumnoInfo.setMaxHeight(Double.MAX_VALUE);
		
		
		
		if(this.AlumnoActual !=null) {
			
			Label numLegajo = new Label();	numLegajo.getStyleClass().add("txt-Prymari");
			Label nombreAlumno = new Label();nombreAlumno.getStyleClass().add("txt-Prymari");
			
			numLegajo.setText("numero de legajo: "+this.AlumnoActual.getLegajo()); 
			nombreAlumno.setText("nombre: "+this.AlumnoActual.getNombre());
			
			HBox HBoxnombre = new HBox();
			HBoxnombre.setPadding(new Insets(5));
			HBoxnombre.setSpacing(30);
			HBoxnombre.setAlignment(Pos.CENTER_LEFT);
			HBoxnombre.getChildren().addAll(numLegajo,nombreAlumno);
			
			alumnoInfo.setTop(HBoxnombre);
			
			
			ScrollPane paneContenedorHisortial = new ScrollPane();
			paneContenedorHisortial.setFitToWidth(true);
			paneContenedorHisortial.getStyleClass().add("ScrollPane");
			
			
			
			
			
	        
	        
	        
			VBox VBoxHistorail = new VBox();
			
			paneContenedorHisortial.setContent(VBoxHistorail);
			
			VBoxHistorail.setSpacing(10);
			
			
			
			
			HistorialAlumnos historial= this.AlumnoActual.getHistorial();
			for(int i=0; i < historial.tamanio(); i++) {
				
				EstadoMateria estadoMateria =(EstadoMateria) historial.devolver(i);
				Materia materia =(Materia) this.plan.getCatalogoMaterias().getMateria(estadoMateria.getCodigoMateria() ) ;
				Label lblId = new Label();		lblId.setText(materia.getCodigo()+"");	lblId.setMinWidth(30);	lblId.getStyleClass().add("txt-Secondary");
				Label lblNombre = new Label();	lblNombre.setText(materia.getNombre()); lblNombre.setMaxWidth(Double.MAX_VALUE);	lblNombre.getStyleClass().add("txt-Secondary");
				Label lblEstado	= new Label();	lblEstado.setText(estadoMateria.getEstado().name()); lblEstado.setMinWidth(100);	lblEstado.getStyleClass().add("txt-Secondary");
				
				HBox borderPaneMateria = new HBox();
				
				
				borderPaneMateria.setPadding( new Insets(10));
				
				
				
				if(estadoMateria.getEstado() != EstadoAcademico.NO_CURSADA) {
					borderPaneMateria.getChildren().addAll(lblId, lblNombre,lblEstado);
					VBoxHistorail.getChildren().add(borderPaneMateria);
					
					if(estadoMateria.getEstado() == EstadoAcademico.APROBADA) {
						lblEstado.setStyle("-fx-text-fill: #2ecc71;");
					}
					if(estadoMateria.getEstado() == EstadoAcademico.REGULAR) {
						
						lblEstado.setStyle("-fx-text-fill: #3498db;");
					}
				}
				
				VBoxHistorail.setAlignment(Pos.TOP_CENTER);
				
				HBox.setHgrow(lblNombre, Priority.ALWAYS);
			}
			
			alumnoInfo.setCenter(paneContenedorHisortial);
			this.panelAlumnoCenter.getChildren().add(alumnoInfo);
		}
		
		
		
		
	}
	
	

	
	
	public void StyleScrollPane(ScrollPane scrolPane) {
		
		
		
		
		
		
		
	}
	
}
