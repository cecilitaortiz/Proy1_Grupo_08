package cecy.proyecto1grupo8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private HBox hb;
    @FXML
    private Label lbltitulo;

    public static String[] datosBusqueda;
    public static ArrayListAuto<Auto> autos=App.crearArrayList("autos.txt");
    public static ArrayListAuto<Auto> favoritos=App.crearArrayList("favoritos.txt");
    public static AnchorPane seleccionado;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {
            LlenarDatos(hb, autos, datosBusqueda);
        } catch (FileNotFoundException ex) {
        }
    }

    @FXML
    public void atras(ActionEvent event) {

        try {
            App.setRoot("buscador");
        } catch (IOException ex) {
        }

    }
    
   
    @FXML
    public static void mostrar(MouseEvent event) {
        for (Auto a : autos) {
            if (CompararAutoSeleccionado(a, (AnchorPane) event.getSource())) {
                AnchorPane ap = new AnchorPane();
                StringBuilder contenido = new StringBuilder();

                try (BufferedReader br = new BufferedReader(
                        new FileReader(App.pathDescripciones + a.getDescripcion()))) {
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
    public static void LlenarDatos(HBox hbox, ArrayListAuto<Auto> ar, String[] datos) throws FileNotFoundException {
        
        VBox vb = new VBox();
        vb.prefWidth(150);
        vb.prefHeight(300);
        vb.setSpacing(15);
        ArrayListAuto<Auto> autosBusqueda = new ArrayListAuto<>();
        if (datos.length == 1) {
            for (Auto a : ar)
                autosBusqueda.add(a);
        } else {
            for (Auto a : ar) {
                if (datos[0].equals(a.getMarca())
                        && datos[1].equals(a.getModelo())
                        && Integer.parseInt(datos[2]) <= a.getKilometraje()
                        && Integer.parseInt(datos[3]) >= a.getKilometraje()
                        && Double.parseDouble(datos[4]) <= a.getPrecio()
                        && Double.parseDouble(datos[5]) >= a.getPrecio()) {
                    autosBusqueda.add(a);
                }
            }
        }

        for (int i = 0; i < autosBusqueda.size(); i++) {
            if (vb.getChildren().size() < 2) {
                LlenarVbox(vb, autosBusqueda.get(i));
            } else {
                hbox.getChildren().add(vb);
                vb = new VBox();
                vb.prefWidth(150);
                vb.prefHeight(300);
                vb.setSpacing(15);
                LlenarVbox(vb, autosBusqueda.get(i));
            }
        }

        if (!vb.getChildren().isEmpty()) {
            hbox.getChildren().add(vb);
        }
    }

    @FXML
    public static void LlenarVbox(VBox vb, Auto a) throws FileNotFoundException {
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
        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                mostrar(e);
                ap.setStyle("-fx-border-color: black; -fx-border-width: 5px;");
                seleccionado=ap;
            }
        });

        vb.getChildren().add(ap);
    }

    @FXML
    public static boolean CompararAutoSeleccionado(Auto a, AnchorPane anchorPane) {
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
    
    @FXML
    public void marcarFavorito(ActionEvent event) {
        if(seleccionado.equals(null)){
            lbltitulo.setText("Debes seleccionar un auto.");
        }else{
            Stage stage = new Stage();
            AnchorPane rootmensaje=new AnchorPane();
            VBox vbh=new VBox();
            vbh.setSpacing(60);
            vbh.setPadding(new Insets(100, 20, 0, 20));
            Label lbl=new Label();
            lbl.setText("¿Está seguro de añadir este auto a favoritos?");
            lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            lbl.setTextFill(Color.web("#b74113"));
            HBox hb=new HBox();
            hb.setPrefSize(150,200);
            hb.setSpacing(150);
            Button btnaceptar=new Button();
            btnaceptar.setPrefSize(100, 40);
            btnaceptar.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b74113;");
            btnaceptar.setText("Aceptar");
            btnaceptar.setTextFill(Color.web("#faa632"));
            btnaceptar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Button btncancelarm=new Button();
            btncancelarm.setPrefSize(100, 40);
            btncancelarm.setStyle("-fx-background-color: #ffffff; -fx-border-color: #b74113;");
            btncancelarm.setText("Cancelar");
            btncancelarm.setTextFill(Color.web("#faa632"));
            btncancelarm.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            Scene scene = new Scene(rootmensaje,475,300);
            rootmensaje.getChildren().add(vbh);
            vbh.getChildren().add(lbl);
            vbh.getChildren().add(hb);
            hb.getChildren().add(btnaceptar);
            hb.getChildren().add(btncancelarm);
            vbh.setMargin(hb,new Insets(0,50,0,50));
            stage.setScene(scene);
            stage.show();
            btnaceptar.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    stage.close();
                    Stage s=(Stage)((Button) event.getSource()).getScene().getWindow();
                    s.close();
                    for(Auto a:autos){
                        if(CompararAutoSeleccionado(a, seleccionado) && !favoritos.contiene(a)){
                            Fichero.escribir(App.pathArchivos+"favoritos.txt",a.toWrite());
                        }
                    }
                }
            });
            btncancelarm.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    stage.close();
                }
            });
        }

    }    
}
