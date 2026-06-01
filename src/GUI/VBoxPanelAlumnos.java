package GUI;





import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Alumno;
import modelo.EstadoAcademico;
import modelo.EstadoMateria;
import modelo.Materia;
import modelo.PlanEstudio;


public class VBoxPanelAlumnos extends VBox{
	
	private PlanEstudio plan;
	private Alumno alumnoActual = null;
	
	public VBoxPanelAlumnos(PlanEstudio plan) {
		this.plan= plan;
		setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: #23001e;");
		setSpacing(40);
		mostrar();
	}
	
	public void mostrar() {
		
		
		setPrefSize(1300,800);
		
		getChildren().clear();
		HBox buscador = HboxBuscador();
		
		
		getChildren().add(buscador);
		if(this.alumnoActual!=null) {
			VBox alumno = crearVBoxAlumnos();
			getChildren().add(alumno);
			}
		
		
		
		
	}
	
	
	
	public HBox HboxBuscador() {
		HBox buscador = new HBox();
		
		
		TextField txtBuscador = new TextField();
		
		txtBuscador.setPrefWidth(600);
		txtBuscador.setPromptText("ingresar numero de legajo");
		
		Button btnBuscador = new Button();
		
		
		
		buscador.setAlignment(Pos.CENTER);
		
		buscador.getChildren().addAll(txtBuscador,btnBuscador);
		
		btnBuscador.setOnAction(events -> {
			String textoIngresdo= txtBuscador.getText();
			if(!textoIngresdo.trim().isEmpty()) {
				
				try {
					
					int legajo = Integer.parseInt(textoIngresdo);
					Alumno alumno = plan.buscarAlumno( legajo);
					if(alumno != null) {
						this.alumnoActual=alumno;
						setAlignment(Pos.TOP_CENTER);
						mostrar();
						
					}
					
				}catch (NumberFormatException e) {
					{
						System.out.println(e);
					}
				}
				
				
			}
			
			
		} );
		
		
		return buscador;
	}
	
	
	
	public VBox crearVBoxAlumnos() {
		VBox panelVBox = new VBox();
		panelVBox.setStyle("-fx-background-color: #66417e;");
		panelVBox.setPadding(new Insets(30,0,30,0));
		panelVBox.setMaxWidth(800);
		
		
		HBox alumnoPanel= new HBox();
		alumnoPanel.setAlignment(Pos.CENTER);
		
		
		TextField legajo= new TextField();
		legajo.setText(this.alumnoActual.getLegajo()+"" );
		
		TextField nombre=new TextField();
		nombre.setText(this.alumnoActual.getNombre());
		
		alumnoPanel.getChildren().addAll(legajo,nombre);
		
		
		
		ScrollPane histotrial = new ScrollPane();
	
		VBox listaHistorial = new VBox();
		listaHistorial.setSpacing(20);
		
		listaHistorial.setAlignment(Pos.CENTER);
		histotrial.setContent(listaHistorial);
		
		for(int i =0; i< this.alumnoActual.getHistorial().tamanio(); i++) {
			
			EstadoMateria estado =(EstadoMateria) this.alumnoActual.getHistorial().devolver(i);
			
			if(estado.getEstado() != EstadoAcademico.NO_CURSADA) {
				
				Label lbMateria = new Label();
				
				Materia materia = (Materia)this.plan.getCatalogoMaterias().getMateria(estado.getCodigoMateria());
				lbMateria.setText(  materia.getNombre() +"  "+ estado.getEstado().name() );
				listaHistorial.getChildren().add(lbMateria);
			}
		}
		
		
		
		panelVBox.getChildren().addAll(alumnoPanel, listaHistorial);
		panelVBox.setAlignment(Pos.CENTER);
		return panelVBox;
	}
	
}
