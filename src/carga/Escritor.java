package carga;


import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Escritor {
	
	public void imprimirComponente(Node nodoAImprimir, Stage ventanaPadre) {
		PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null) {
            boolean proceder = job.showPrintDialog(ventanaPadre);

            if (proceder) {
                
                PageLayout pageLayout = job.getJobSettings().getPageLayout();
                
                
                double anchoImprimible = pageLayout.getPrintableWidth();
                double altoImprimible = pageLayout.getPrintableHeight();
                
              
                double anchoNodo = nodoAImprimir.getBoundsInParent().getWidth();
                double altoNodo = nodoAImprimir.getBoundsInParent().getHeight();
                
              
                double factorEscala = 1.0;
                if (anchoNodo > anchoImprimible) {
                    factorEscala = anchoImprimible / anchoNodo;
                }
                
                
                Scale escala = new Scale(factorEscala, factorEscala);
                nodoAImprimir.getTransforms().add(escala);
                
                
                boolean exito = job.printPage(pageLayout, nodoAImprimir);

               
                nodoAImprimir.getTransforms().remove(escala);

                if (exito) {
                    job.endJob();
                } else {
                    mostrarAlerta("Error", "No se pudo completar la impresión.");
                }
            }
        } else {
            mostrarAlerta("Error", "No se detectaron impresoras instaladas.");
        }
    }
	
	
	    

	private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
	    }
}


