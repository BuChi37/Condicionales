package GUI;

import infraestructura.ListaDoubleLinkedL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.Materia;
import modelo.PlanEstudio;
import modelo.TipoCondicion;
import servicios.AnalizadorAcademico;

public class PanelMateria extends VBox {
		
    public PanelMateria( Materia materia,PlanEstudio plan) {
    	
    	getStyleClass().add("panel-materia");
    	
        setSpacing(5);
        
        setFillWidth(true);
        setMaxWidth(Double.MAX_VALUE);

        HBox fila = new HBox();
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setPrefWidth(Double.MAX_VALUE);
        fila.setMaxWidth(Double.MAX_VALUE);

        Label nombre = new Label(obtenerTextoMateria(materia));
        nombre.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(nombre, Priority.ALWAYS);
        
        nombre.getStyleClass().add("materia");
        
        fila.getChildren().add(nombre);
        
        //crear panel y class
        AnalizadorAcademico analisador = new AnalizadorAcademico(plan);
        ListaDoubleLinkedL listaMaterias = new ListaDoubleLinkedL();
        listaMaterias=analisador.materiasBloqueadas(materia);
        
        Button btnBlock = new Button("bloqueado");
        VBox panelBlock = new VBox();
        
        for(int i =0; i< listaMaterias.tamanio();i++ ) {
        	
        	Label lblMateriaBlock = new Label();
        	lblMateriaBlock.setText(((Materia)listaMaterias.devolver(i)).getNombre());
        	panelBlock.getChildren().add(lblMateriaBlock);
        }
        panelBlock.setVisible(false);
        panelBlock.setManaged(false);
        
        
        
        
        
        
        btnBlock.setOnAction(block ->{
        	
        	boolean visible =panelBlock.isVisible();

            panelBlock.setVisible(!visible);
            panelBlock.setManaged(!visible);
            
            if(visible)
                btnBlock.setText("bloqueadas");
            else
                btnBlock.setText("Ocultar");
        	
        } );
        
        
        //fin
        
        Button btn =new Button("Requisitos");

        btn.getStyleClass().add("btn-requisitos");
        
        VBox requisitos = new VBox();
        
        requisitos.getStyleClass().add("panel-requisitos");

        requisitos.setSpacing(3);
        requisitos.setPadding(new Insets(12,20,12,20));

        requisitos.setVisible(false);
        requisitos.setManaged(false);

        ListaDoubleLinkedL correlativas = plan.obtenerCorrelativasDirectas( materia);
        
        boolean tieneRequisitos = correlativas.tamanio() > 0;

        if(tieneRequisitos) {
            fila.getChildren().addAll(btnBlock,btn);
        }

        for(int i=0;i<correlativas.tamanio();i++) {

            Materia corr =(Materia) correlativas.devolver(i);

            TipoCondicion condicion =plan.obtenerCondicionRequerida(corr,materia);

            String tipo = "";

            if(condicion == TipoCondicion.REGULAR)
                tipo = "Regular";

            if(condicion == TipoCondicion.APROBADO)
                tipo = "Aprobada";

            Label lbl =new Label("• " + corr.getNombre()+ " ("+ tipo+ ")");
            
            lbl.getStyleClass().add("texto-requisito"); //?

            requisitos.getChildren().add(lbl);
        }

        if(tieneRequisitos) {

            btn.setOnAction(e -> {

                boolean visible =requisitos.isVisible();

                requisitos.setVisible(!visible);
                requisitos.setManaged(!visible);
                
                if(visible)
                    btn.setText("Requisitos");
                else
                    btn.setText("Ocultar");

            });

        }

        getChildren().addAll(fila,requisitos,panelBlock);//ingreso del nuevo layout
        
        setStyle(
        	    "-fx-effect: dropshadow("
        	    + "gaussian,"
        	    + "rgba(0,0,0,0.35),"
        	    + "10,0,0,2);"
        	);
        
    }
    private String obtenerTextoMateria(Materia materia) {

        switch(materia.getCuatrimestre()) {

            case 1:
                return "1C - " + materia.getNombre();

            case 2:
                return "2C - " + materia.getNombre();

            case 3:
                return "A - " + materia.getNombre();

            case 10:
                return "Opt. 1C - " + materia.getNombre();

            case 20:
                return "Opt. 2C - " + materia.getNombre();

            default:
                return materia.getNombre();
        }
    }
    
    
    
}
