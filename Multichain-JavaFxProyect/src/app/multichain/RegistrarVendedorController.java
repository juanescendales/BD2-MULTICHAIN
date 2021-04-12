package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import org.json.simple.JSONObject;

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
    public Label success_message;


    @FXML
    private void registrarVendedores(ActionEvent event) throws IOException {
        cleanWarnings();

        String cedula = cedula_textfield.getText().trim();
        String nombre = nombre_textfield.getText().trim();
        long telefono = 0;

        try{
            int integer_cedula = Integer.parseInt(cedula);
            if(integer_cedula <= 0){
                warning_message.setText("La cedula ingresada no es valida , solo debe tener digitos");
                return;
            }
        }catch (Exception e){
            warning_message.setText("La cedula ingresada no es valida , solo debe tener digitos");
            return;
        }

        if(nombre.length() == 0){
            warning_message.setText("Nombre no valido");
            return;
        }

        try{
            telefono = Long.parseLong(telefono_textfield.getText().trim());
            if(telefono <= 0){
                warning_message.setText("Telefono no valido");
                return;
            }
        }catch (Exception e){
            warning_message.setText("Telefono no valido");
            return;
        }



        JSONObject inner_json = new JSONObject();
        inner_json.put("telefono",telefono);
        inner_json.put("nombre",nombre);
        JSONObject json = new JSONObject();
        json.put("json",inner_json);
        try {
           String result =  (String) App.commandManager.invoke(CommandElt.PUBLISH,"vendedores",cedula,json);
           System.out.println(result);
        } catch (MultichainException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            warning_message.setText(e.getMessage());
            return;
        }
        success_message.setText("Vendedor registrado satisfactoriamente!");
        clean();
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
