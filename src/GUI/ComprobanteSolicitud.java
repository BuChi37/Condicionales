package GUI;

import infraestructura.ListaDoubleLinkedL;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.ResultadoRegla;
import modelo.SolicitudCondicional;

public class ComprobanteSolicitud extends VBox{
	public ComprobanteSolicitud(SolicitudCondicional soli) {
		this.getStyleClass().add("comprobante");
		this.setPrefSize(475, 842);
		
		String css = this.getClass().getResource("../resource/CssSolicitud.css").toExternalForm();
        this.getStylesheets().add(css);
		
		Label titulo=new Label("Solicitud Condicional");
		titulo.getStyleClass().add("tit");
		
		GridPane tabla= new GridPane();
		tabla.getStyleClass().add("tabla");
		
		Label nombre= new Label("Nombre: "+soli.getAlumno().getNombre());
		Label legajo= new Label("Legajo: "+soli.getAlumno().getLegajo());
		Label fecha= new Label("Fecha: "+soli.getFecha());
		Label materia= new Label("Materia: "+soli.getMateriaSolicitada().getNombre());
		Label motivo= new Label("Motivo: "+soli.getMotivo());
		
		tabla.add(nombre, 0, 0, 1, 1);
		tabla.add(legajo, 1, 0, 1, 1);
		tabla.add(fecha, 0, 1, 2, 1);
		tabla.add(materia, 0, 2, 2, 1);
		tabla.add(motivo, 0, 3, 2, 1);
		
		nombre.getStyleClass().add("estilo-celda");
		nombre.getStyleClass().add("celda-dividida");
		legajo.getStyleClass().add("estilo-celda");
		legajo.getStyleClass().add("celda-normal");
		fecha.getStyleClass().add("estilo-celda");
		materia.getStyleClass().add("estilo-celda");
		motivo.getStyleClass().add("estilo-celda");
		
		javafx.scene.layout.ColumnConstraints col1 = new javafx.scene.layout.ColumnConstraints();
	    col1.setPercentWidth(50);
	    javafx.scene.layout.ColumnConstraints col2 = new javafx.scene.layout.ColumnConstraints();
	    col2.setPercentWidth(50);
	    tabla.getColumnConstraints().addAll(col1, col2);
	    
	    VBox cuadroReglas= new VBox();
	    cuadroReglas.getStyleClass().add("cuadro");
	    
	    Label estado=new Label("Solicitud "+soli.getEstado());
	    estado.getStyleClass().add("estado");
	    
	    cuadroReglas.getChildren().add(estado);
	    
	    ListaDoubleLinkedL reglas=(ListaDoubleLinkedL)soli.getDictamenFinal().obtenerResultados();
	    ResultadoRegla resultado; 
	    
	    for(int i=0;i<reglas.tamanio();i++) {
	    	
	    	Label r;
	    	resultado=(ResultadoRegla)reglas.devolver(i);
	    	
			if(resultado.cumpleRegla()) {
				r=new Label("Cumple con la regla "+(i+1)+" "+resultado.getMensaje());
				r.getStyleClass().add("regla-item");
				r.getStyleClass().add("regla-cumple");
			}else {
				r=new Label("No cumple con la regla "+(i+1)+" "+resultado.getMensaje());
				r.getStyleClass().add("regla-item");
				r.getStyleClass().add("regla-no-cumple");
			}
			
			r.setWrapText(true);
			
			cuadroReglas.getChildren().add(r);
	    }
	    
	    this.getChildren().addAll(titulo,tabla,cuadroReglas);
	}
	
}
