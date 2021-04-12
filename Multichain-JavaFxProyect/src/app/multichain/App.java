package app.multichain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Multichain Imports
import multichain.command.*;
import multichain.object.*;



/**
 * JavaFX App
 */
public class App extends Application {
    public static HashMap<String, ArrayList<Float>> vendedores;
    private static Scene scene;
    public static CommandManager commandManager;
    @Override
    public void start(Stage stage) throws IOException {
        // Conexion a la blockchain
        commandManager = new CommandManager("localhost", "6838","multichainrpc","Fu1UErQMN8FwqGSLHpgPrK3u3Ju4cYrCTgva73LVqrwa");
        try{
            commandManager.invoke(CommandElt.SUBSCRIBE,"vendedores");
            commandManager.invoke(CommandElt.SUBSCRIBE,"ganancias");
        }catch(MultichainException e){
            e.printStackTrace();
            return;
        }
        // Interfaz grafica

        scene = new Scene(loadFXML("views/menu_principal"), 672, 438);
        stage.setScene(scene);
        stage.show();

    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }

    public static void cargarDatos(){
        App.vendedores = new HashMap<>();
        try {
            ArrayList<StreamKeyItem> vendedores = (ArrayList<StreamKeyItem>)App.commandManager.invoke(CommandElt.LISTSTREAMITEMS, "vendedores");
            ArrayList<StreamKeyItem> ganancias = (ArrayList<StreamKeyItem>)App.commandManager.invoke(CommandElt.LISTSTREAMITEMS, "ganancias");
            for (StreamKeyItem vendedor : vendedores) {
                if(! vendedor.getKeys().isEmpty()){
                    App.vendedores.put(vendedor.getKeys().get(0),new ArrayList<>());
                }
            }
            for (StreamKeyItem ganancia : ganancias) {
                if(!ganancia.getKeys().isEmpty()){
                    if(App.vendedores.containsKey(ganancia.getKeys().get(0))){
                        String datos = ganancia.getData().toString();
                        Pattern p = Pattern.compile("-?\\d+(,\\d+)*?\\.?\\d+?");
                        List<String> numbers = new ArrayList<String>();
                        Matcher m = p.matcher(datos);
                        while (m.find()) {
                            numbers.add(m.group());
                        }
                        float valor = Float.parseFloat(numbers.get(0));
                        App.vendedores.get(ganancia.getKeys().get(0)).add(valor);
                    }
                }
            }


        } catch (MultichainException e) {
            e.printStackTrace();
        }
    }

}