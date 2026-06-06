package GUI;

import infraestructura.ListaDoubleLinkedL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.CatalogoMaterias;
import modelo.Materia;
import modelo.PlanEstudio;
import modelo.TipoCondicion;

public class VBoxPlanEstudio extends VBox {

    public VBoxPlanEstudio(PlanEstudio plan) {
    	
    	setSpacing(10);
    	setPadding(new Insets(10));
    	
        setAlignment(Pos.TOP_LEFT);
        setFillWidth(true);
        
        TreeItem<String> raiz =new TreeItem<>("TECNICATURA UNIVERSITARIA EN PROGRAMACION - Plan de estudios 2012");

        CatalogoMaterias catalogo =plan.getCatalogoMaterias();
        
        TreeItem<String> uno =new TreeItem<>("Primer Año");
        raiz.getChildren().add(uno);
        
        TreeItem<String> dos =new TreeItem<>("Segundo Año");
        raiz.getChildren().add(dos);
        
        TreeItem<String> tres =new TreeItem<>("Tercer Año");
        raiz.getChildren().add(tres);

        //setStyle("-fx-background-color: blue;");

        for(int i=0; i<catalogo.tamanio(); i++) {

            Materia materia =(Materia) catalogo.devolver(i); 
            
            String text="";
            
            switch(materia.getCuatrimestre()) {
            case 1: text= "1C - "+materia.getNombre(); break;
            case 2: text= "2C - "+materia.getNombre(); break;
            case 3: text= "A - "+materia.getNombre(); break;
            case 10: text= "Opt. 1C - "+materia.getNombre(); break;
            case 20: text= "Opt. 2C - "+materia.getNombre(); break;
            }
            
           
            TreeItem<String> nodoMateria =new TreeItem<>(text);
            
            switch(materia.getAnio()) {
            case 1: uno.getChildren().add(nodoMateria); break;
            case 2: dos.getChildren().add(nodoMateria); break;
            case 3: tres.getChildren().add(nodoMateria); break;
            }
            
            
            TreeItem<String> requiere = new TreeItem<>("Requiere: ");
            
            ListaDoubleLinkedL correlativas =plan.obtenerCorrelativasDirectas(materia);

            if(correlativas.tamanio() > 0) {
                nodoMateria.getChildren().add(requiere);
            }

            for(int j=0; j<correlativas.tamanio(); j++) {

                Materia correlativa =(Materia) correlativas.devolver(j);
                
                TipoCondicion condicion =plan.obtenerCondicionRequerida(correlativa,materia);
                
                String tipo = "";

                if(condicion == TipoCondicion.REGULAR) tipo = "Regular";

                if(condicion == TipoCondicion.APROBADO) tipo = "Aprobada";

                String texto =correlativa.getNombre()+ " (" + tipo + ")";
                    
                requiere.getChildren().add( new TreeItem<>(texto));
               
            }
        }

        raiz.setExpanded(true);
        
        
        TreeView<String> arbol =new TreeView<>(raiz);
        
        arbol.getStyleClass().add("TreePlan");
        
        arbol.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        /*
        //prueba
        arbol.setStyle(
        	    "-fx-background-color: #1E2640;" +
        	    "-fx-control-inner-background: #1E2640;"
        	);*/
        
       
        ScrollPane scroll = new ScrollPane(arbol);
        
        scroll.getStyleClass().add("ScrollPanePlan");
        
        setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    	setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    	
    	scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        
        /*
        
        //prueba
        scroll.setStyle(
        	    "-fx-background: #1E2640;" +
        	    "-fx-background-color: #1E2640;"
        	);
        
         */
    	
    	VBox.setVgrow(scroll, Priority.ALWAYS);
        
        getChildren().add(scroll);

        
    }
}
