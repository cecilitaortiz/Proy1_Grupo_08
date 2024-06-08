package cecy.proyecto1grupo8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Button btnmostrar;
    @FXML
    private Label lblnoseleccionado;
    @FXML
    private AnchorPane root;
    @FXML
    private HBox hb;
    private Auto auto1;
    public static String pathImg = "src/main/resources/images/";
    public static String pathImagenes = "src/main/resources/imagenes/";
    public static String pathArchivos = "src/main/resources/archivos/";
    public static String pathDescripciones = "src/main/resources/descripciones/";
    
    public static String[] datosBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            LlenarDatos(crearArrayList());
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
    private void seleccionar(MouseEvent event) {

        AnchorPane anchorPane = (AnchorPane) event.getSource();
        for (Auto a : crearArrayList()) {
            if (CompararAutoSeleccionado(a, (AnchorPane) event.getSource())) {
                if (auto1 == null) {
                    auto1 = new Auto(a.getTipo(), a.getMarca(), a.getModelo(), a.getColor(), a.getKilometraje(), a.getPrecio(), a.getAnio(), a.getImagen(), a.getDescripcion());
                } else {
                    auto1.setTipo(a.getTipo());
                    auto1.setMarca(a.getMarca());
                    auto1.setModelo(a.getModelo());
                    auto1.setColor(a.getColor());
                    auto1.setKilometraje(a.getKilometraje());
                    auto1.setPrecio(a.getPrecio());
                    auto1.setAnio(a.getAnio());
                    auto1.setImagen(a.getImagen());
                    auto1.setDescripcion(a.getDescripcion());
                }
                break;
            }
        }
    }

    @FXML
    private void mostrar(ActionEvent event) {
        if (auto1 == null || auto1.getPrecio() == 0) {
            lblnoseleccionado.setText("Debes Seleccionar un auto.");
        } else {
            lblnoseleccionado.setText("");
            AnchorPane ap = new AnchorPane();
            StringBuilder contenido = new StringBuilder();

            try (BufferedReader br = new BufferedReader(new FileReader(pathDescripciones + auto1.getDescripcion()))) {
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

    @FXML
    private void LlenarDatos(ArrayList<Auto> ar) throws FileNotFoundException {
        VBox vb = new VBox();
        vb.prefWidth(150);
        vb.prefHeight(300);
        vb.setSpacing(15);

        for (int i = 0; i < ar.size(); i++) {
            if (vb.getChildren().size() < 2) {
                LlenarVbox(vb, ar.get(i));
            } else {
                hb.getChildren().add(vb);
                vb = new VBox();
                vb.prefWidth(150);
                vb.prefHeight(300);
                vb.setSpacing(15);
                LlenarVbox(vb, ar.get(i));
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
        ap.setOnMouseClicked(this::seleccionar);

        ImageView iv = new ImageView();
        iv.setLayoutX(5);
        iv.setLayoutY(5);
        iv.setFitHeight(50);
        iv.setFitWidth(140);
        iv.setSmooth(true);
        iv.setCache(true);
        iv.setPickOnBounds(true);
        iv.setPreserveRatio(false);

        Image image = new Image(new FileInputStream(pathImagenes + a.getImagen()));
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

    public ArrayList<Auto> crearArrayList() {
        ArrayList<Auto> autos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathArchivos + "autos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] atributos = linea.split(",");
                Auto auto = new Auto(
                        atributos[0], // tipo
                        atributos[1], // marca
                        atributos[2], // modelo
                        atributos[3], // color
                        Integer.valueOf(atributos[4]), // kilometraje
                        Double.parseDouble(atributos[5]), // precio
                        Integer.parseInt(atributos[6]), // anio
                        atributos[7], // imagen (ruta)
                        atributos[8] // descripcion (ruta de txt)

                );
                autos.add(auto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return autos;
    }
}
