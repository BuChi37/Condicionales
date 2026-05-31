package GUI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VBoxBienvenido extends VBox{
	
	public VBoxBienvenido() {
		
		setAlignment(Pos.CENTER);
		setPrefSize(1000, 600);
		
		Image logo = new Image("file:Datos/Fondo.png");
		
		ImageView vistaImagen = new ImageView(logo);
		
		vistaImagen.setFitWidth(500);
	    vistaImagen.setPreserveRatio(true);
	    
	    getChildren().add(vistaImagen);
	}
	
	

}
