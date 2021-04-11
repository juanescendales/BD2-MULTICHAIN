package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrarGananciaController {
    @FXML
    public ComboBox cedula_combo_box;
    @FXML
    public TextField cantidad_textfield;

    @FXML
    public Label warning_message;


    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("menu_principal");
    }

    @FXML
    private void clean(){
        cantidad_textfield.setText("");
        cleanWarnings();
    }

    @FXML
    private void cleanWarnings(){
        warning_message.setText("");
    }
}
