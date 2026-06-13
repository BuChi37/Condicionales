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


        mostrar();
    }

    private void mostrar() {

        VBoxPlanEstudioCards vistaPlan = new VBoxPlanEstudioCards(plan);

        setCenter(vistaPlan);
    }
}