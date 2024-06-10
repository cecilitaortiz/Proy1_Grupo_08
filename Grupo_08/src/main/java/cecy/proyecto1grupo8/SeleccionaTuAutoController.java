package cecy.proyecto1grupo8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dario Anchundia Cobo
 */
public class SeleccionaTuAutoController implements Initializable {

    @FXML
    private Button btnatras;
    @FXML
    private AnchorPane ap;
    @FXML
    private AnchorPane root;
    @FXML
    private HBox hb;

    public static String[] datosBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        try {
            LlenarDatos(App.crearArrayList());
        } catch (FileNotFoundException ex) {
        }

    }

    @FXML
    private void atras(ActionEvent event) {

        try {
            App.setRoot("buscador");
        } catch (IOException ex) {
        }

    }

    @FXML
    private void mostrar(MouseEvent event) {
        for (Auto a : App.crearArrayList()) {
            if (CompararAutoSeleccionado(a, (AnchorPane) event.getSource())) {
                AnchorPane ap = new AnchorPane();
                StringBuilder contenido = new StringBuilder();

                try (BufferedReader br = new BufferedReader(new FileReader(App.pathDescripciones + a.getDescripcion()))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Label label = new Label(contenido.toString());
                label.setPrefWidth(600);
                label.setAlignment(Pos.TOP_LEFT);
                label.setLayoutX(10);
                label.setLayoutY(10);
                label.setWrapText(true);
                ap.getChildren().add(label);

                Scene scene = new Scene(ap);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Descripción del Auto");
                stage.show();
            }
        }
    }

    @FXML
    private void LlenarDatos(ArrayListAuto<Auto> ar) throws FileNotFoundException {
        VBox vb = new VBox();
        vb.prefWidth(150);
        vb.prefHeight(300);
        vb.setSpacing(15);
        ArrayListAuto<Auto> autosBusqueda = new ArrayListAuto<>();
        for (Auto a : ar) {
            if (datosBusqueda[0].equals(a.getMarca())
                    &&datosBusqueda[1].equals(a.getModelo())
                    && Integer.parseInt(datosBusqueda[2])<=a.getKilometraje()
                    && Integer.parseInt(datosBusqueda[3])>=a.getKilometraje()
                    && Double.parseDouble(datosBusqueda[4])<=a.getPrecio()
                    && Double.parseDouble(datosBusqueda[5])>=a.getPrecio()
                    ) {
                autosBusqueda.add(a);
            }
        }

        for (int i = 0; i < autosBusqueda.size(); i++) {
            if (vb.getChildren().size() < 2) {
                LlenarVbox(vb, autosBusqueda.get(i));
            } else {
                hb.getChildren().add(vb);
                vb = new VBox();
                vb.prefWidth(150);
                vb.prefHeight(300);
                vb.setSpacing(15);
                LlenarVbox(vb, autosBusqueda.get(i));
            }
        }

        if (!vb.getChildren().isEmpty()) {
            hb.getChildren().add(vb);
        }
    }

    @FXML
    private void LlenarVbox(VBox vb, Auto a) throws FileNotFoundException {
        AnchorPane ap = new AnchorPane();
        ap.setPrefSize(150, 132);
        ap.setStyle("-fx-border-color: #808080; -fx-border-width: 2;");

        ImageView iv = new ImageView();
        iv.setLayoutX(5);
        iv.setLayoutY(5);
        iv.setFitHeight(50);
        iv.setFitWidth(140);
        iv.setSmooth(true);
        iv.setCache(true);
        iv.setPickOnBounds(true);
        iv.setPreserveRatio(false);

        Image image = new Image(new FileInputStream(App.pathImagenes + a.getImagen()));
        iv.setImage(image);

        Label lblmodelo = new Label(a.getModelo());
        lblmodelo.setLayoutX(5);
        lblmodelo.setLayoutY(57);
        lblmodelo.setFont(new Font("System Bold", 12));

        Label lblanio = new Label(String.valueOf(a.getAnio()));
        lblanio.setLayoutX(5);
        lblanio.setLayoutY(74);
        lblanio.setStyle("-fx-border-color: #808080;");

        Label lblmarca = new Label(a.getMarca());
        lblmarca.setLayoutX(37);
        lblmarca.setLayoutY(75);

        Label lbltipo = new Label(a.getTipo());
        lbltipo.setLayoutX(5);
        lbltipo.setLayoutY(93);

        Label lblprecio = new Label("$ " + String.valueOf(a.getPrecio()));
        lblprecio.setLayoutX(5);
        lblprecio.setLayoutY(110);
        lblprecio.setFont(new Font("System Bold", 12));

        ap.getChildren().addAll(iv, lblmodelo, lblanio, lblmarca, lbltipo, lblprecio);
        ap.setOnMouseClicked(this::mostrar);

        vb.getChildren().add(ap);
    }

    @FXML
    private boolean CompararAutoSeleccionado(Auto a, AnchorPane anchorPane) {
        Label lblmodelo = (Label) anchorPane.getChildren().get(1);
        Label lblanio = (Label) anchorPane.getChildren().get(2);
        Label lblmarca = (Label) anchorPane.getChildren().get(3);
        Label lbltipo = (Label) anchorPane.getChildren().get(4);
        Label lblprecio = (Label) anchorPane.getChildren().get(5);

        return a.getModelo().equals(lblmodelo.getText())
                && a.getAnio() == Integer.parseInt(lblanio.getText())
                && a.getMarca().equals(lblmarca.getText())
                && a.getTipo().equals(lbltipo.getText())
                && a.getPrecio() == Double.parseDouble(lblprecio.getText().substring(2));

    }

    
}