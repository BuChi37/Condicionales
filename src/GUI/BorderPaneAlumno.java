package GUI;







import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Alumno;
import modelo.EstadoAcademico;
import modelo.EstadoMateria;
import modelo.HistorialAlumnos;
import modelo.Materia;
import modelo.PlanEstudio;

public class BorderPaneAlumno extends BorderPane{
	private  int contracenia= 12345;
	private PlanEstudio plan;
	private Alumno AlumnoActual;
	private TextField txtBuscador;
	private StackPane panelAlumnoCenter;
	private StackPane panelAlumnoTop;
	private Materia materiaActual;
	private EstadoAcademico nuevoEstadoCambio;
	
	private HBox HBoxBuscador;
	private BorderPane BorderPaneAlumno;
	
	
	public BorderPaneAlumno(PlanEstudio plan) {
		
		this.nuevoEstadoCambio=null;
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

		panelHBox.setMaxSize(700, 50);
		
		panelHBox.getStyleClass().add("Hbox-buscador");
		
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
		
		btnBus.setMaxHeight(Double.MAX_VALUE);
		btnBus.setMinWidth(40);
		btnBus.setMinHeight(40);
		btnBus.getStyleClass().add("btnBuscar");
		btnBus.setDefaultButton(true);
		
		
		
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
		txtbus.getStyleClass().add("txtField");
		
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
			
			numLegajo.setText("# "+this.AlumnoActual.getLegajo()); 
			nombreAlumno.setText(""+this.AlumnoActual.getNombre());
			
			HBox HBoxnombre = new HBox();
			HBoxnombre.setPadding(new Insets(5));
			HBoxnombre.setSpacing(15);
			HBoxnombre.setAlignment(Pos.CENTER_LEFT);
			StackPane imagenContener= new StackPane();
			imagenContener.getStyleClass().add("stack-pane-user");
			HBoxnombre.getChildren().addAll(imagenContener,nombreAlumno,numLegajo);
			
			alumnoInfo.setTop(HBoxnombre);
			
			
			ScrollPane paneContenedorHisortial = new ScrollPane();
			paneContenedorHisortial.setFitToWidth(true);
			paneContenedorHisortial.getStyleClass().add("ScrollPane");
			
			
			
			
			
	        
	        
	        
			VBox VBoxHistorail = new VBox();
			
			paneContenedorHisortial.setContent(VBoxHistorail);
			
	
			
			
			
			
			HistorialAlumnos historial= this.AlumnoActual.getHistorial();
			Boolean bandera = false;
			for(int i=0; i < historial.tamanio(); i++) {
				
				EstadoMateria estadoMateria =(EstadoMateria) historial.devolver(i);
				Materia materia =(Materia) this.plan.getCatalogoMaterias().getMateria(estadoMateria.getCodigoMateria() ) ;
				
				Label lblId = new Label();		lblId.setText(materia.getCodigo()+"");	lblId.setMinWidth(30);	lblId.getStyleClass().add("txt-Secondary");
				Label lblNombre = new Label();	lblNombre.setText(materia.getNombre()); lblNombre.setMaxWidth(Double.MAX_VALUE);	lblNombre.getStyleClass().add("txt-Secondary");
				Label lblEstado	= new Label();	lblEstado.setText(estadoMateria.getEstado().name()); lblEstado.setMinWidth(100);	lblEstado.getStyleClass().add("txt-Secondary");
				
				HBox borderPaneMateria = new HBox();
				
				
				borderPaneMateria.setPadding( new Insets(7));
				borderPaneMateria.setAlignment(Pos.CENTER_LEFT);
				
				Button btnModificador = new Button();
				crearBtnModificar(btnModificador,materia);
				
				
				if(estadoMateria.getEstado() != EstadoAcademico.NO_CURSADA) {
					
					borderPaneMateria.getChildren().addAll(lblId, lblNombre,lblEstado,btnModificador);
					VBoxHistorail.getChildren().add(borderPaneMateria);
					
					if(estadoMateria.getEstado() == EstadoAcademico.APROBADA) {
						lblEstado.setStyle("-fx-text-fill: #2ecc71;");
					}
					if(estadoMateria.getEstado() == EstadoAcademico.REGULAR) {
						
						lblEstado.setStyle("-fx-text-fill: #3498db;");
					}
					
					if(bandera == false) {
						borderPaneMateria.setStyle("-fx-background-color: #1E2640;"+"-fx-border-radius: 10px;"+"-fx-background-radius: 10px;");
						bandera =true;
					}else {
						bandera = false;
					}
				}
				
				VBoxHistorail.setAlignment(Pos.TOP_CENTER);
				
				HBox.setHgrow(lblNombre, Priority.ALWAYS);
			}
			
			alumnoInfo.setCenter(paneContenedorHisortial);
			this.panelAlumnoCenter.getChildren().add(alumnoInfo);
		}
		
		
		
		
	}
	
	

	
	
	public void crearBtnModificar(Button btnModificar,Materia materia) {
		
		btnModificar.setMaxHeight(Double.MAX_VALUE);
		btnModificar.setMinWidth(30);
		btnModificar.getStyleClass().add("btn-modificar");
		btnModificar.setOnAction(event ->{
			this.materiaActual=materia;
			crearStageCofig();
			
		} );
		
		
	}
	
	//for the stage 2
	public void crearStageCofig() {
		
		Stage ventanaFlotante = new Stage();
		ventanaFlotante.initStyle(StageStyle.UNDECORATED);
		ventanaFlotante.setResizable(false);
		ventanaFlotante.setTitle("modificarHisotial");
		
		StackPane pantallaBloqueo = new StackPane();		pantallaBloqueo.getStyleClass().add("pantalla-bLoqueo");
		HBox subPantallaBloqueo = new HBox();				subPantallaBloqueo.getStyleClass().add("sub-pantalla-bloqueo");
		subPantallaBloqueo.setMaxSize(HBox.USE_PREF_SIZE, HBox.USE_PREF_SIZE);
		
		
		Button btnPreguntar = new Button();
		Button btnDevolver = new Button();
		PasswordField campoContracenia = new PasswordField();
		subPantallaBloqueo.getChildren().addAll(btnDevolver,campoContracenia,btnPreguntar);
		
		pantallaBloqueo.getChildren().add(subPantallaBloqueo);
		
		//btnDevolver.setOnAction(evento -> );
		btnPreguntar.setOnAction(event -> {
			
			if(campoContracenia.getText()!= null ) {
				
				try {
					
					int contraceniaIngresada= Integer.parseInt(campoContracenia.getText());
					if(this.contracenia == contraceniaIngresada) {
						
						pantallaBloqueo.getChildren().clear();
						HBox panelZonas = gridPaneModificar();
						pantallaBloqueo.getChildren().add( panelZonas);
						
					}
					
				}catch (NumberFormatException e) {
					System.out.println(e);
				}
			}
		});
		
		
		
		
		String css = this.getClass().getResource("/resource/CssAlumno.css").toExternalForm();
		Scene sceneFlotante = new Scene(pantallaBloqueo,500,200);
		sceneFlotante.getStylesheets().add(css);
		ventanaFlotante.setScene(sceneFlotante);
		ventanaFlotante.initModality(Modality.APPLICATION_MODAL);
		ventanaFlotante.showAndWait();
		
		
	}
	
	
	public HBox gridPaneModificar() {
		HBox paneHBox = new HBox();
		paneHBox.setAlignment(Pos.CENTER);
		paneHBox.setSpacing(100);
		
		VBox datos = new VBox();
		
		
		datos.setAlignment(Pos.TOP_LEFT);
		paneHBox.getChildren().add(datos);
		datos.setSpacing(20);
		
		
		
		Label alumno = new Label();						alumno.getStyleClass().add("label");
		Label materia = new Label();					materia.getStyleClass().add("label");
		Label estadoActual= new Label();				estadoActual.getStyleClass().add("label");
		Label nuevoEstado = new Label();				nuevoEstado.getStyleClass().add("label");
		Button btnAceptar = new Button("aplicar ");	btnAceptar.getStyleClass().add("btn-aceptar");
		
		
		ComboBox<EstadoAcademico> comboMaterias = new ComboBox<>();
		
		
		alumno.setText(this.AlumnoActual.getNombre()+ "   #"+this.AlumnoActual.getLegajo()  );
		materia.setText( this.materiaActual.getCodigo() +"  "+this.materiaActual.getNombre());
		EstadoMateria  estado = this.AlumnoActual.getEstado(this.materiaActual.getCodigo());
		estadoActual.setText(  estado.getEstado().name()  );
		nuevoEstado.setText("sin cambios");
		
		
		
		comboMaterias.setMinWidth(100);
		comboMaterias.getItems().addAll(EstadoAcademico.values());
		comboMaterias.setPromptText("elegir Estado");
		comboMaterias.setOnAction(event -> {
		    
		    this.nuevoEstadoCambio = comboMaterias.getValue();
		    
		    if (nuevoEstadoCambio != null) {
		        
		        nuevoEstado.setText(nuevoEstadoCambio.name());
		        
		       
		    }
		});
		
		
		btnAceptar.setOnAction(event ->{
			
			if(this.nuevoEstadoCambio != null) {
				
				this.AlumnoActual.getHistorial().ModificarEstado(nuevoEstadoCambio, this.materiaActual.getCodigo());
				
			}
			
			Stage ventana = (Stage) btnAceptar.getScene().getWindow();
			ventana.close();
		} );
		
		datos.getChildren().addAll(alumno,materia,estadoActual,nuevoEstado,btnAceptar);
		
		VBox VBoxComboBox = new VBox();
		VBoxComboBox.setAlignment(Pos.TOP_CENTER);
		VBoxComboBox.getChildren().add(comboMaterias);
		paneHBox.getChildren().add(VBoxComboBox);
		
		
		
		return paneHBox;
	}
	
	
	public void asignarColorLabel(Label label, EstadoAcademico estado ) {
		if(estado == EstadoAcademico.NO_CURSADA) {
			
			
		}
		
		if(estado == EstadoAcademico.CURSANDO) {
			
		}
		
		if(estado == EstadoAcademico.CURSANDO) {
			
			
		}
		if(estado == EstadoAcademico.APROBADA) {
			
		}
		
		if(estado == EstadoAcademico.DESAPROBADA) {
			
		}
		
		if(estado== EstadoAcademico.LIBRE) {
			
		}
	}
	
}
