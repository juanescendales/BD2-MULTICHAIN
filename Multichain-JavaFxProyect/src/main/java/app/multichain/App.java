package app.multichain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Multichain Imports
import multichain.command.*;
import multichain.object.*;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static CommandManager commandManager;
    @Override
    public void start(Stage stage) throws IOException {
        // Conexion a la blockchain
        commandManager = new CommandManager("localhost", "4266","multichainrpc","84QwcQRkg7w3SVcpHJFbjC7tLSG2c2wLUWcCfGXazgAs");
        try{
            commandManager.invoke(CommandElt.SUBSCRIBE,"vendedores");
            commandManager.invoke(CommandElt.SUBSCRIBE,"ganancias");
        }catch(MultichainException e){
            e.printStackTrace();
            return;
        }
        // Interfaz grafica
        scene = new Scene(loadFXML("menu_principal"), 672, 438);
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
}