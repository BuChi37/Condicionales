package GUI;

import infraestructura.ListaDoubleLinkedL;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.ResultadoRegla;
import modelo.SolicitudCondicional;

public class Solicitud extends BorderPane{
	
	private Label titulo, nombre, nroLegajo, fecha,materia, motivo, dictamen;
	
	
	
	public void mostar(SolicitudCondicional solicitud) {
		Stage nuevaV=new Stage();
		nuevaV.setTitle("Cargar Solicitud");
		
		Scene scene = new Scene(cargarScene(solicitud));
		nuevaV.setScene(scene);
        nuevaV.show();
	}
	
	public BorderPane cargarScene(SolicitudCondicional solicitud) {
		BorderPane panelSoli= new BorderPane();
		String css = this.getClass().getResource("../resource/CssSolicitud.css").toExternalForm();
        panelSoli.getStylesheets().add(css);
		
        this.titulo = new Label("Detalle de la Solicitud Condicional");
        this.titulo.getStyleClass().add("titulo-detalle");

		this.nombre= new Label("Nombre: "+solicitud.getAlumno().getNombre());
		this.nombre.getStyleClass().add("label-dato");
		
		this.nroLegajo= new Label("Nro de legajo: "+solicitud.getAlumno().getLegajo());
		this.nroLegajo.getStyleClass().add("label-dato");
		
		this.fecha=new Label("Fecha: "+solicitud.getFecha());
		this.fecha.getStyleClass().add("label-dato");
		
		this.materia= new Label("Materia solicitada: "+solicitud.getMateriaSolicitada().getNombre());
		this.materia.getStyleClass().add("label-dato");
		
		this.motivo=new Label("Motivo del incumplimiento: "+solicitud.getMotivo());
		this.motivo.getStyleClass().add("label-dato");
		
		this.dictamen=new Label("Solicitud "+solicitud.getEstado());
		this.dictamen.getStyleClass().add("badge-estado-detalle");
		
		VBox contenedor=new VBox();
		contenedor.getChildren().addAll(titulo,nombre,nroLegajo,fecha,materia,motivo,dictamen);

        contenedor.setId("detalle-solicitud-root");
		
		ListaDoubleLinkedL dictamenR=(ListaDoubleLinkedL) solicitud.getDictamenFinal().obtenerResultados();
		ResultadoRegla resultado; 
				
		for(int i=0;i<dictamenR.tamanio();i++) {
			Label regla;
			
			resultado=(ResultadoRegla)dictamenR.devolver(i);
			if(resultado.cumpleRegla()) {
				regla=new Label("Cumple con la regla "+(i+1)+" "+resultado.getMensaje());
				regla.getStyleClass().add("regla-item");
				regla.getStyleClass().add("regla-cumple");
			}else {
				regla=new Label("No cumple con la regla "+(i+1)+" "+resultado.getMensaje());
				regla.getStyleClass().add("regla-item");
				regla.getStyleClass().add("regla-no-cumple");
			}
			regla.setWrapText(true);
			contenedor.getChildren().add(regla);
		}
		
		
		
		
		panelSoli.setCenter(contenedor);
		return panelSoli;
	}
}
