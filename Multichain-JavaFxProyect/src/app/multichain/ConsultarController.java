package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ConsultarController implements Initializable {
    public ListView lista_vendedores;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.cargarDatos();
        for (String cedula : App.vendedores.keySet()) {
            float gananacias = 0;
            for(float ganancia : App.vendedores.get(cedula)){
                gananacias+= ganancia;
            }
            String formato = "----------------------\n"+"Vendedor: " + cedula + " - " + "Ganancias: " + String.valueOf(gananacias) + "\n";
            lista_vendedores.getItems().add(new Label(formato));
        }

    }
    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("views/menu_principal");
    }
}
