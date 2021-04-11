module org.example {
    requires MultiChainJavaAPI;

    requires javafx.controls;
    requires javafx.fxml;


    opens app.multichain to javafx.fxml;
    exports app.multichain;
}