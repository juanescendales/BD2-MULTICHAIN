package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
// Multichain Imports
import multichain.command.*;
import multichain.object.*;

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
    private void registrarVendedores(ActionEvent event) throws IOException {
        String jsonString = "{\"json\":{\"nombre\": \""+nombre_textfield.getText().trim()+"\",\"telefono\": "+telefono_textfield.getText().trim()+"}}";
        warning_message.setText(jsonString);
        /*try {
            App.commandManager.invoke(CommandElt.PUBLISH,"vendedores",cedula_textfield.getText().trim());
        } catch (MultichainException e) {
            e.printStackTrace();
        }*/
    }


    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("views/menu_principal");
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
