package app.multichain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
// Multichain Imports
import multichain.command.*;
import multichain.object.*;


public class ConsultarController implements Initializable {
    public ListView lista_vendedores;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*try {
            ArrayList<StreamKeyItem> vendedores = (ArrayList<StreamKeyItem>)App.commandManager.invoke(CommandElt.LISTSTREAMKEYITEMS, "vendedores");
            for (StreamKeyItem vendedor : vendedores) {
                lista_vendedores.getItems().add(vendedor);
            }
        } catch (MultichainException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("views/menu_principal");
    }
}
