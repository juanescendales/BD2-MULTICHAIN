package app.multichain;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import multichain.command.CommandElt;
import multichain.command.MultichainException;
import multichain.object.StreamKeyItem;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarGananciaController implements Initializable {
    @FXML
    public ComboBox cedula_combo_box;
    @FXML
    public TextField cantidad_textfield;

    @FXML
    public Label warning_message;

    @FXML
    public Label success_message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.cargarDatos();
        cedula_combo_box.setItems(FXCollections.observableList(new ArrayList<>(App.vendedores.keySet())));
    }

    @FXML
    private void registrarGanancias(ActionEvent event) throws IOException {
        cleanWarnings();

        String cedula = String.valueOf(cedula_combo_box.getValue());
        float valor = 0;

        try{
            int integer_cedula = Integer.parseInt(cedula);
            if(integer_cedula <= 0){
                warning_message.setText("La cedula ingresada no es valida , solo debe tener digitos positivos");
                return;
            }
        }catch (Exception e){
            warning_message.setText("La cedula ingresada no es valida , solo debe tener digitos y no debe estar vacia");
            return;
        }


        try{
            valor = Float.parseFloat(cantidad_textfield.getText().trim());
            if(valor <= 0){
                warning_message.setText("El valor ingresado debe ser un numero positivo");
                return;
            }
        }catch (Exception e){
            warning_message.setText("El valor ingresado no es un numero valido");
            return;
        }


        JSONObject inner_json = new JSONObject();
        inner_json.put("valor",valor);
        JSONObject json = new JSONObject();
        json.put("json",inner_json);


        warning_message.setText(json.toString());
        try {
            String result =  (String) App.commandManager.invoke(CommandElt.PUBLISH,"ganancias",cedula,json);
            System.out.println(result);
        } catch (MultichainException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            warning_message.setText(e.getMessage());
            return;
        }
        success_message.setText("Ganancia registrada satisfactoriamente!");
        clean();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("views/menu_principal");
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
