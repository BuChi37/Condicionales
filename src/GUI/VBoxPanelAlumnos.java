package GUI;





import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.Alumno;
import modelo.PlanEstudio;


public class VBoxPanelAlumnos extends VBox{
	
	private PlanEstudio plan;
	private Alumno alumnoActual = null;
	
	public VBoxPanelAlumnos(PlanEstudio plan) {
		this.plan= plan;
		
		mostrar();
	}
	
	public void mostrar() {
		
		setAlignment(Pos.CENTER);
		setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox buscador = HboxBuscador();
		
		
		getChildren().add(buscador);
		
	}
	
	
	
	public HBox HboxBuscador() {
		HBox buscador = new HBox();
		
		
		TextField txtBuscador = new TextField();
		
		txtBuscador.setPrefWidth(500);
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
		
		
		HBox alumnoPanel= new HBox();
		
		TextField legajo= new TextField();
		legajo.setText(this.alumnoActual.getLegajo()+"" );
		
		TextField nombre=new TextField();
		nombre.setText(this.alumnoActual.getNombre());
		
		alumnoPanel.getChildren().addAll(legajo,nombre);
		
		
		return panelVBox;
	}
	
}
