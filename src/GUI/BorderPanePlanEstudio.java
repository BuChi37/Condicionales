package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import modelo.PlanEstudio;

public class BorderPanePlanEstudio extends BorderPane {

    private PlanEstudio plan;

    public BorderPanePlanEstudio(PlanEstudio plan) {

        this.plan = plan;

        setPadding(new Insets(20));
        
        setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setStyle("-fx-background-color: #121824;");
        //setStyle("-fx-background-color: red;");

        mostrar();
    }

    private void mostrar() {
/*
        Label titulo = new Label("Plan de Estudios");

        titulo.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        BorderPane.setAlignment(titulo, Pos.CENTER);*/

        VBoxPlanEstudio vistaPlan = new VBoxPlanEstudio(plan);

       // setTop(titulo);
        setCenter(vistaPlan);
    }
}