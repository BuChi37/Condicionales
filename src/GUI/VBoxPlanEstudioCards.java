package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.CatalogoMaterias;
import modelo.Materia;
import modelo.PlanEstudio;

public class VBoxPlanEstudioCards extends VBox {

    public VBoxPlanEstudioCards(PlanEstudio plan) {

        setSpacing(10);
        setPadding(new Insets(10));

        VBox contenido = new VBox(3);
        contenido.setFillWidth(true);
        
        contenido.setStyle("-fx-background-color: #121824;");

        Label titulo = new Label("TECNICATURA UNIVERSITARIA EN PROGRAMACIÓN");

        titulo.getStyleClass().add("titulo-plan");
        titulo.setMaxWidth(Double.MAX_VALUE);

        contenido.getChildren().add(titulo);

        agregarAnio(contenido, plan, 1, "Primer Año");
        agregarAnio(contenido, plan, 2, "Segundo Año");
        agregarAnio(contenido, plan, 3, "Tercer Año");

        ScrollPane scroll = new ScrollPane(contenido);
        scroll.getStyleClass().add("ScrollPanePlan");
        
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        VBox.setVgrow(scroll, Priority.ALWAYS);

        getChildren().add(scroll);
        
        
        /*
        scroll.setStyle(
        	    "-fx-background: #121824;" +
        	    "-fx-background-color: #121824;" +
        	    "-fx-border-color: red;"
        	);*/
    }

    private void agregarAnio(VBox contenido, PlanEstudio plan,int anio, String tituloAnio) {

        Label lblAnio = new Label(tituloAnio);

        lblAnio.getStyleClass().add("titulo-anio");
        lblAnio.setMaxWidth(Double.MAX_VALUE);

        contenido.getChildren().add(lblAnio);
        

        CatalogoMaterias catalogo = plan.getCatalogoMaterias();

        for(int i=0;i<catalogo.tamanio();i++) {

            Materia materia =(Materia) catalogo.devolver(i);

            if(materia.getAnio() == anio) {

                contenido.getChildren().add(new PanelMateria(materia,plan));
            }
        }
    }
}
