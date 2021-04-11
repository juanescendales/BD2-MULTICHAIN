package app.multichain;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalController {
    public Button close_button;

    @FXML
    private void routeRegistrarGanancia(ActionEvent event) throws IOException {
        App.setRoot("registrar_ganancia");
    }
    @FXML
    private void routeRegistrarVendedor(ActionEvent event) throws IOException {
        App.setRoot("registrar_vendedor");
    }
    @FXML
    private void routeConsultar(ActionEvent event) throws IOException {
        App.setRoot("consultar");
    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
}
