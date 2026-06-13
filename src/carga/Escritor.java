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
                // --- INICIO DEL AJUSTE DE ESCALA ---
                
                // 1. Obtener la configuración de la página actual (Márgenes, tamaño de papel, etc.)
                PageLayout pageLayout = job.getJobSettings().getPageLayout();
                
                // 2. Calcular cuánto espacio imprimible real tenemos de ancho y alto
                double anchoImprimible = pageLayout.getPrintableWidth();
                double altoImprimible = pageLayout.getPrintableHeight();
                
                // 3. Obtener el tamaño actual del nodo de JavaFX (en píxeles)
                double anchoNodo = nodoAImprimir.getBoundsInParent().getWidth();
                double altoNodo = nodoAImprimir.getBoundsInParent().getHeight();
                
                // 4. Calcular el factor de escala necesario para el ancho
                double factorEscala = 1.0;
                if (anchoNodo > anchoImprimible) {
                    factorEscala = anchoImprimible / anchoNodo;
                }
                
                // 5. Aplicar la escala al nodo (Lo "encogemos" temporalmente)
                Scale escala = new Scale(factorEscala, factorEscala);
                nodoAImprimir.getTransforms().add(escala);
                
                // --- FIN DEL AJUSTE DE ESCALA ---

                // 6. Mandar a imprimir el nodo ya escalado
                boolean exito = job.printPage(pageLayout, nodoAImprimir);

                // 7. RESTAURAR el tamaño original del nodo en la pantalla
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


