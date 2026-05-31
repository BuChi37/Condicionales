package GUI;





import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Alumno;
import modelo.PlanEstudio;


public class VBoxPanelAlumnos extends VBox{
	
	private PlanEstudio plan;
	private Alumno alumnoActual = null;
	
	public VBoxPanelAlumnos(PlanEstudio plan) {
		this.plan= plan;
		
		
	}
	
	public void mostrar() {
		
		setAlignment(Pos.CENTER);
		setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}
	
	
	
	public HBox HboxBuscador() {
		HBox buscador = new HBox();
		
		
		TextField txtBuscador = new TextField();
		txtBuscador.setPrefWidth(500);
		
		Button btnBuscador = new Button();
		
		buscador.setAlignment(Pos.CENTER);
		
		
		btnBuscador.setOnAction(events -> {
			
			Alumno alumno = plan.buscarAlumno(Integer.parseInt(txtBuscador.getText()));
			if(alumno != null) {
				this.alumnoActual=alumno;
				
			}
			
		} );
		
		
		return buscador;
	}
	
	public ScrollPane ScrollPaneAlumno() {
		ScrollPane panel = new ScrollPane();
		if(this.alumnoActual != null) {
			
			
			
		}
		return panel;
	}
	
	
	
}
