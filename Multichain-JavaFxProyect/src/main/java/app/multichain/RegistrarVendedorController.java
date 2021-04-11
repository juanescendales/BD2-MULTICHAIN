package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class RegistrarVendedorController {
    @FXML
    public TextField cedula_textfield;
    @FXML
    public TextField nombre_textfield;
    @FXML
    public TextField telefono_textfield;

    @FXML
    public Label warning_message;


    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("menu_principal");
    }

    @FXML
    private void clean(){
        cedula_textfield.setText("");
        nombre_textfield.setText("");
        telefono_textfield.setText("");
        cleanWarnings();
    }

    @FXML
    private void cleanWarnings(){
        warning_message.setText("");
    }
}
