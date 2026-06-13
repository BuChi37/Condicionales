package GUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import infraestructura.ListaDoubleLinkedL;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Alumno;
import modelo.CatalogoMaterias;
import modelo.Materia;
import modelo.PlanEstudio;
import modelo.RegistroSolicitudes;
import modelo.SolicitudCondicional;
import servicios.MotorCondicionalidad;

public class BorderPaneNuevaSolicitud extends BorderPane{
	private SolicitudCondicional soli;
	private Label TNombre, TNroLegajo,TFecha, TMateria, TMotivo,error;
	private TextField nombre,nroLegajo;
	private ComboBox<String> cmbMateria;
	private DatePicker fecha;
	private ToggleGroup groups;
	private RadioButton btn1,btn2,btn3;
	private Button buttonCrear;

	private Runnable accionAlCargar;
	
	public void mostar(PlanEstudio plan) {
		Stage nuevaV=new Stage();
		nuevaV.setTitle("Cargar Solicitud");
		
		Scene scene = new Scene(cargarScene(plan));
		nuevaV.setScene(scene);
        nuevaV.show();
	}
	
	public BorderPane cargarScene(PlanEstudio plan) {
		
        
		BorderPane panelSoli= new BorderPane();
		panelSoli.setPrefSize(450, 400);
		
		String css = this.getClass().getResource("../resource/CssSolicitud.css").toExternalForm();
		panelSoli.getStyleClass().add("pantalla-solicitud");
        panelSoli.getStylesheets().add(css);
        panelSoli.setId("panel-nueva");
        
        this.error = new Label("");
        this.error.setStyle("-fx-text-fill: #ff6b6b; -fx-font-weight: bold;");
        
		this.TNombre= new Label("Nombre: ");
		this.nombre= new TextField();
		this.nombre.setPromptText("Nombre del alumno");
		
		this.TNroLegajo= new Label("Nro de Legajo: ");
		this.nroLegajo= new TextField();
		this.nroLegajo.setPromptText("Nro de Legajo del alumno");

		this.TMateria= new Label("Materia: ");
		cmbMateria = new ComboBox<>();
		CatalogoMaterias listaMaterias = plan.getCatalogoMaterias();
	    
	    for (int i = 0; i < listaMaterias.tamanio(); i++) {
	        Materia mat = (Materia) listaMaterias.devolver(i);
	        cmbMateria.getItems().add(mat.getNombre());
	    }
	    cmbMateria.setPromptText("Seleccione una materia");
	    
		this.TFecha= new Label("Fecha: ");
		this.fecha = new DatePicker();
	    this.fecha.setPromptText("Seleccione una fecha");
	    fecha.getStyleClass().add("combo-oscuro");
	    
	
		this.TMotivo= new Label("Motivo: ");
		groups = new ToggleGroup();
		
		btn1= new RadioButton("Salud");
		btn1.setToggleGroup(groups);
		btn2= new RadioButton("Trabajo");
		btn2.setToggleGroup(groups);
		btn3= new RadioButton("Otro");
		btn3.setToggleGroup(groups);
		
		buttonCrear = new Button("Cargar Solicitud");
		cargarSolicitud(buttonCrear,plan,plan.getRegistroSolicitudes());
		buttonCrear.getStyleClass().add("boton-cargar");
		
		VBox cont= new VBox();
		cont.getChildren().addAll(TNombre,nombre,TNroLegajo,nroLegajo,TMateria,cmbMateria,TFecha,fecha,TMotivo,btn1,btn2,btn3,buttonCrear,error);
		cont.setId("formulario-carga");
		
		panelSoli.setCenter(cont);
		return panelSoli;
	}
	public void setOnSolicitudCargada(Runnable accion) {
        this.accionAlCargar = accion;
    }
	public void cargarSolicitud(Button boton,PlanEstudio plan,RegistroSolicitudes registro) {
		boton.setOnAction(EventLayoutSoli->{
			
			MotorCondicionalidad motor=new  MotorCondicionalidad(plan);
			ListaDoubleLinkedL errores = new ListaDoubleLinkedL();
			
			RadioButton seleccionado= (RadioButton)this.groups.getSelectedToggle();
			LocalDate fechaCalendario = fecha.getValue();
			String legajoTexto = nroLegajo.getText().trim();
			String nombreTexto = nombre.getText().trim(); 
			String materiaTexto= cmbMateria.getValue();

			// === 🛡️ SECCIÓN 1: VALIDACIONES DE CAMPOS VACÍOS (Con simples IF) ===

			if (nombreTexto.isEmpty()) errores.insertar("Ingrese el nombre.", errores.tamanio());
			if (fechaCalendario == null) errores.insertar("Seleccione una fecha.", errores.tamanio());
			if (seleccionado == null) errores.insertar("Seleccione un motivo.", errores.tamanio());
			
			try {
				int legajoNumero = Integer.parseInt(legajoTexto);
				Alumno alu= plan.buscarAlumno(legajoNumero);
				
				if (alu == null) {
			        errores.insertar("El legajo no corresponde a ningún alumno.", errores.tamanio());
			    } else if (!alu.getNombre().equalsIgnoreCase(nombreTexto)) {
			        errores.insertar("El legajo no coincide con el nombre.", errores.tamanio());
			    }
				
				
				
			}catch (NumberFormatException e) {
			    errores.insertar("El legajo debe ser solo números.",errores.tamanio());
			}
			if (!errores.estaVacia()) {
				
				String mensajeCompleto = "Por favor: ";
				for(int i=0;i<errores.tamanio();i++) {
					mensajeCompleto  += " "+(String)errores.devolver(i)+"\n";
				}
			    
			    error.setText(mensajeCompleto);
			    return;
			}
			this.error.setText("");
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaS = fechaCalendario.format(formateador);
			
			this.soli = new SolicitudCondicional(plan.buscarAlumno(Integer.parseInt(nroLegajo.getText())),plan.getCatalogoMaterias().devolverMateria(materiaTexto),fechaS,seleccionado.getText());
			
			soli.setDictamen(motor.evaluarSolicitud(plan.buscarAlumno(Integer.parseInt(nroLegajo.getText())), soli));
			registro.agregarSolicitud(soli);
			
			if (this.accionAlCargar != null) {
                this.accionAlCargar.run();
            }
		} );
	}
}
