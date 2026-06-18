package GUI;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Alumno;
import modelo.CatalogoMaterias;
import modelo.HistorialAlumnos;
import modelo.Materia;
import modelo.PlanEstudio;
import modelo.RegistroSolicitudes;
import modelo.SolicitudCondicional;
import servicios.MotorCondicionalidad;

public class BorderPaneNuevaSolicitud extends BorderPane{
	private SolicitudCondicional soli;
	private Label TNombre, Enombre, TNroLegajo,ENroLegajo,TFecha, Efecha, TMateria, EMateria, TMotivo,EMotivo;
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
		panelSoli.setPrefWidth(500);
		
		String css = this.getClass().getResource("../resource/CssSolicitud.css").toExternalForm();
		panelSoli.getStyleClass().add("pantalla-solicitud");
        panelSoli.getStylesheets().add(css);
        panelSoli.setId("panel-nueva");
        
		this.TNombre= new Label("Nombre: ");
		this.nombre= new TextField();
		this.nombre.setPromptText("Nombre del alumno");
		this.Enombre= new Label("");
		this.Enombre.getStyleClass().add("error-campo");
		
		this.TNroLegajo= new Label("Nro de Legajo: ");
		this.nroLegajo= new TextField();
		this.nroLegajo.setPromptText("Nro de Legajo del alumno");
		this.ENroLegajo= new Label("");
		this.ENroLegajo.getStyleClass().add("error-campo");
		
		this.TMateria= new Label("Materia: ");
		cmbMateria = new ComboBox<>();
		CatalogoMaterias listaMaterias = plan.getCatalogoMaterias();
	    
	    for (int i = 0; i < listaMaterias.tamanio(); i++) {
	        Materia mat = (Materia) listaMaterias.devolver(i);
	        cmbMateria.getItems().add(mat.getNombre());
	    }
	    cmbMateria.setPromptText("Seleccione una materia");
	    this.EMateria= new Label("");
	    this.EMateria.getStyleClass().add("error-campo");
	    
		this.TFecha= new Label("Fecha: ");
		this.fecha = new DatePicker();
	    this.fecha.setPromptText("Seleccione una fecha");
	    fecha.getStyleClass().add("combo-oscuro");
	    this.Efecha= new Label("");
	    this.Efecha.getStyleClass().add("error-campo");
	
		this.TMotivo= new Label("Motivo: ");
		groups = new ToggleGroup();
		
		btn1= new RadioButton("Salud");
		btn1.setToggleGroup(groups);
		btn2= new RadioButton("Trabajo");
		btn2.setToggleGroup(groups);
		btn3= new RadioButton("Otro");
		btn3.setToggleGroup(groups);
		this.EMotivo= new Label("");
		this.EMotivo.getStyleClass().add("error-campo");
		
		buttonCrear = new Button("Cargar Solicitud");
		cargarSolicitud(buttonCrear,plan,plan.getRegistroSolicitudes(),panelSoli);
		buttonCrear.getStyleClass().add("boton-cargar");
		
		VBox cont= new VBox();
		cont.getChildren().addAll(TNombre,nombre,Enombre,TNroLegajo,nroLegajo,ENroLegajo,TMateria,cmbMateria,EMateria,TFecha,fecha,Efecha,TMotivo,btn1,btn2,btn3,EMotivo,buttonCrear);
		cont.setId("formulario-carga");
		
		panelSoli.setCenter(cont);
		return panelSoli;
	}
	
	public void setOnSolicitudCargada(Runnable accion) {
        this.accionAlCargar = accion;
    }
	
	public void cargarSolicitud(Button boton,PlanEstudio plan,RegistroSolicitudes registro, BorderPane panelSoli) {
		boton.setOnAction(EventLayoutSoli->{
			
			MotorCondicionalidad motor=new  MotorCondicionalidad(plan);
			boolean errores=false;
			
			
			RadioButton seleccionado= (RadioButton)this.groups.getSelectedToggle();
			LocalDate fechaCalendario = fecha.getValue();
			String legajoTexto = nroLegajo.getText().trim();
			String nombreTexto = nombre.getText().trim(); 
			String materiaTexto= cmbMateria.getValue();


			if (nombreTexto.isEmpty()) { this.Enombre.setText("Ingrese el nombre.");  errores=true;}else this.Enombre.setText("");
			if (fechaCalendario == null) { this.Efecha.setText("Seleccione una fecha."); errores=true;}else this.Efecha.setText("");
			if (seleccionado == null) { this.EMotivo.setText("Seleccione un motivo."); errores=true;}else this.EMotivo.setText("");
			if (materiaTexto==null) { this.EMateria.setText("Ingrese una materia.");  errores=true;}else this.EMateria.setText("");
			Materia mat=plan.getCatalogoMaterias().getMateria(materiaTexto);
			
			try {
				int legajoNumero = Integer.parseInt(legajoTexto);
				
				Alumno alu= plan.buscarAlumno(legajoNumero);
				
				if (alu == null) {
					this.ENroLegajo.setText("El legajo no corresponde a ningún alumno.");
					errores=true;
			    } else if (!alu.getNombre().equalsIgnoreCase(nombreTexto)) {
			    	this.Enombre.setText("El nombre y el legajo no coinciden.");
			    	this.ENroLegajo.setText("El legajo y el nombre no coinciden.");
			    	errores=true;
			    }else {
			    	this.ENroLegajo.setText("");
					HistorialAlumnos historial =alu.getHistorial();
					
					if(mat!=null) {
						if(historial.estaAprobado(mat.getCodigo()) || historial.estaRegular(mat.getCodigo())) {
							this.EMateria.setText("Materia ya cursada ingrese otra materia.");
							errores=true;
						}else this.EMateria.setText("");
					}
			    }
			}catch (NumberFormatException e) {
				if(legajoTexto.isEmpty()) this.ENroLegajo.setText("Ingrese número de legajo.");
				else this.ENroLegajo.setText("El legajo debe ser solo números.");
				errores=true;
			}
			
			if (errores) {
				return;
			}
			
			
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaS = fechaCalendario.format(formateador);
			
			this.soli = new SolicitudCondicional(plan.buscarAlumno(Integer.parseInt(nroLegajo.getText())),mat,fechaS,seleccionado.getText());
			
			soli.setDictamen(motor.evaluarSolicitud(plan.buscarAlumno(Integer.parseInt(nroLegajo.getText())), soli));
			registro.agregarSolicitud(soli);
			
			if (this.accionAlCargar != null) {
                this.accionAlCargar.run();
            }
			Stage actual = (Stage) boton.getScene().getWindow();
			actual.close();
		} );
	}
}
