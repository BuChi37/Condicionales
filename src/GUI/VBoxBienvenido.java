package GUI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VBoxBienvenido extends VBox{
	
	public VBoxBienvenido() {
		
		setAlignment(Pos.CENTER);
		setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		
		Image logo = new Image("");
		
		ImageView vistaImagen = new ImageView(logo);
		
		vistaImagen.setFitWidth(800);
	    vistaImagen.setPreserveRatio(true);
	    
	    getChildren().add(vistaImagen);
	}
	
	

}
