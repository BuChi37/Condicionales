package GUI;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import carga.Escritor;
import infraestructura.ListaDoubleLinkedL;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modelo.CatalogoMaterias;
import modelo.Materia;
import modelo.PlanEstudio;
import modelo.RegistroSolicitudes;
import modelo.SolicitudCondicional;
import servicios.AnalizadorAcademico;

public class BorderPaneSolicitud extends BorderPane {
	private AnalizadorAcademico analizador;
	private TextField TAlumno;
	private Button filtrar;
	private ComboBox<String> cmbMateria;
	private ComboBox<String> cmbEstado;
	private Button nueva;
	private VBox contenedorListado = new VBox(10);
	private ScrollPane scroll= new ScrollPane();
	
	public BorderPaneSolicitud(PlanEstudio plan) {
		this.analizador = new AnalizadorAcademico(plan);
		
		String css = this.getClass().getResource("../resource/CssSolicitud.css").toExternalForm();
		this.getStyleClass().add("pantalla-solicitud");
        this.getStylesheets().add(css);
		this.setPadding(new Insets(15));
		
		crearPanelFiltro(plan);
		crearButtonCargaSoli(plan);
		crearPanelGrid();
		
		this.scroll = new ScrollPane();
        this.contenedorListado = new VBox();

		contenedorListado.setStyle("-fx-background-color: #0c111c;");
        this.scroll.setContent(contenedorListado);
        this.scroll.setFitToWidth(true);
        this.scroll.setFitToHeight(true);
        this.setCenter(this.scroll);
		
		crearPanelScroll(plan.getRegistroSolicitudes());
		
		
		
		
	}
	public void crearPanelFiltro(PlanEstudio plan) {
		
		TAlumno=new TextField();
		TAlumno.setPromptText("Nro de legajo del Alumno");
		
		cmbMateria = new ComboBox<>();
		
		CatalogoMaterias listaMaterias = plan.getCatalogoMaterias();
	    
	    // Pasamos los elementos de tu lista personalizada al ComboBox
		cmbMateria.getItems().add("Ninguna");
	    for (int i = 0; i < listaMaterias.tamanio(); i++) {
	        Materia mat = (Materia) listaMaterias.devolver(i);
	        cmbMateria.getItems().add(mat.getNombre());
	    }
	    cmbMateria.setPromptText("Seleccione una materia");
	    
		cmbEstado = new ComboBox<>();
	    
		cmbEstado.getItems().addAll("Todos", "Pendiente", "Aprobado", "Rechazado");
	    cmbEstado.setPromptText("Estado");
		
	    filtrar = new Button("Filtrar");
		accionBotonFiltro(filtrar, analizador);
		
	}
	
	public void crearButtonCargaSoli(PlanEstudio plan) {
		this.nueva=new Button("Nueva Solicitud");
		nueva.setStyle("-fx-background-color: #28A745; -fx-text-fill: white; -fx-font-weight: bold;");
		accionButtonNueva(nueva,plan);
	}
	
	public void crearPanelGrid() {
		GridPane panel=new GridPane();
		panel.setPadding(new Insets(15));
	    panel.setHgap(20); // Espacio horizontal entre columnas
	    panel.setVgap(10);
	    panel.setAlignment(Pos.CENTER);
	    
		panel.add(TAlumno, 1, 1);
		panel.add(cmbMateria, 2, 1);
		panel.add(cmbEstado, 3, 1);
		panel.add(filtrar, 5, 1);
		
		panel.add(nueva, 8, 1);
		panel.alignmentProperty();
		this.setTop(panel);
	}
	
	public void accionButtonNueva(Button boton,PlanEstudio plan) {
		boton.setOnAction(ev ->{
					
			BorderPaneNuevaSolicitud paneFormulario  = new BorderPaneNuevaSolicitud();
			
			paneFormulario.mostar(plan);
			paneFormulario.setOnSolicitudCargada(() -> {
	            this.crearPanelScroll(plan.getRegistroSolicitudes());
			});
		} );
	}
	
	public void accionBotonFiltro(Button filtrar, AnalizadorAcademico analizador) {
		filtrar.setOnAction(event -> {
		    // Capturamos y limpiamos espacios con trim()
		    String legajo = this.TAlumno.getText().trim();
		    String materia = this.cmbMateria.getValue();
		    String estado = this.cmbEstado.getValue();
		    if (estado == null) estado = "Todos";
		    if(materia == null || materia.equalsIgnoreCase("ninguna")) materia ="";
		    // Le mandamos todo al analizador. ¡No importa si van vacíos!
		    ListaDoubleLinkedL resultados = analizador.filtrar(legajo, materia, estado);
		    
		    // Acá limpiás tu contenedor visual y dibujás los resultados...
		    actualizarPantallaConResultados(resultados);
		});
		
	}

	private void actualizarPantallaConResultados(ListaDoubleLinkedL resultados) {
		RegistroSolicitudes regFiltrado=new RegistroSolicitudes(resultados);
		crearPanelScroll(regFiltrado);
	}

	public void crearPanelScroll(RegistroSolicitudes lista) {
		
		contenedorListado.getChildren().clear();

	    if (lista.cantidadSolicitudes() == 0) {
	        
	        contenedorListado.setAlignment(javafx.geometry.Pos.CENTER);
	        
	        Label mensajeVacio = new Label("No hay solicitudes cargadas todavía.\nPresioná 'Nueva Solicitud' para empezar.");
	        
	        mensajeVacio.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic; -fx-text-alignment: center;");
	        
	        contenedorListado.getChildren().add(mensajeVacio);
	        
	    } else {
	    	contenedorListado.setAlignment(javafx.geometry.Pos.TOP_LEFT);
	        contenedorListado.setSpacing(5);
	        
			SolicitudCondicional soli;
			Label nombre, materia, resultado;
			
			for(int i=lista.cantidadSolicitudes()-1;i>=0;i--) {
				
				HBox filaSolicitud = new HBox(20); 
				filaSolicitud.setAlignment(javafx.geometry.Pos.CENTER_LEFT); // Todo centrado verticalmente
				
				if(i%2==0) {
					filaSolicitud.getStyleClass().add("panel-sol2");
				}else {
					filaSolicitud.getStyleClass().add("panel-sol1");
					
				}
				soli = (SolicitudCondicional)lista.obtenerSolicitudes().devolver(i);
				
				nombre = new Label(soli.getAlumno().getNombre());
				materia = new Label(soli.getMateriaSolicitada().getNombre());
				resultado = new Label(soli.getDictamenFinal().getEstado());
				
				resultado.getStyleClass().add("badge-estado");
				
				
				if (soli.getDictamenFinal().getEstado().equalsIgnoreCase("Pendiente")) {
				    resultado.setStyle("-fx-background-color: #3e351c; -fx-text-fill: #f1c40f;");
				    
				} else if (soli.getDictamenFinal().getEstado().equalsIgnoreCase("Aprobado")) {
				    resultado.setStyle("-fx-background-color: #1b3a24; -fx-text-fill: #2ecc71;");
				    
				} else {
				    resultado.setStyle("-fx-background-color: #3a1b1b; -fx-text-fill: #e74c3c;");
				}
				
				
				Button ver=new Button();
				ver.getStyleClass().add("boton-ver");
				accionBotonVer(ver,soli);
				
				Region separador = new Region();
				HBox.setHgrow(separador, javafx.scene.layout.Priority.ALWAYS);
				
				
		        filaSolicitud.getChildren().addAll(nombre, materia, separador , resultado, ver);
		        
				contenedorListado.getChildren().add(filaSolicitud);
				
				
			}
		}
		
		scroll.setContent(contenedorListado);
		
	}
	public void accionBotonVer(Button btn, SolicitudCondicional soli) {
		btn.setOnAction(vent -> {
		    
			Solicitud indiv=new Solicitud();
		    indiv.mostar(soli);
		});
	}
	
	
}
