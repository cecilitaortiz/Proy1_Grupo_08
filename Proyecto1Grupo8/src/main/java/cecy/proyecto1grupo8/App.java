package cecy.proyecto1grupo8;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathImg="src/main/resources/images/";
    public static String pathImagenes = "src/main/resources/imagenes/";
    public static String pathArchivos = "src/main/resources/archivos/";
    public static String pathDescripciones = "src/main/resources/descripciones/";
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"), 600, 400);
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