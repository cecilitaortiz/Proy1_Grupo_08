/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import cecy.proyecto1grupo8.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Saikotek
 */
public class BaseDatosController implements Initializable {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView<Auto> tblCar;
    @FXML
    private TableColumn<Auto, String> idTipo;
    @FXML
    private TableColumn<Auto, String> idMarca;
    @FXML
    private TableColumn<Auto, String> idModelo;
    @FXML
    private TableColumn<Auto, String> idColor;
    @FXML
    private TableColumn<Auto, Integer> idKim;
    @FXML
    private TableColumn<Auto, Double> idPrecio;
    @FXML
    private TableColumn<Auto, Integer> idAnio;
    public static String[] autoSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.SetButtons();

        ArrayListAuto<Auto> autos;
        autos = App.crearArrayList("autos.txt");
        cargarAutos(autos);

    }

    void SetButtons() {
        btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

        btnAdd.setOnAction(e -> {
            try {
                CarController.selected = null;
                App.setRoot("car");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

        btnEdit.setOnAction(e -> {
            try {
                Auto a = tblCar.getSelectionModel().getSelectedItem();
                String[] datosSeleccionados = {a.getTipo(), a.getMarca(), a.getModelo(), a.getColor(),
                    String.valueOf(a.getKilometraje()), String.valueOf(a.getPrecio()), String.valueOf(a.getAnio()),
                     a.getImagen(), a.getDescripcion()};
                CarController.selected = datosSeleccionados;
                App.setRoot("car");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });

        btnDelete.setOnAction(e -> {
            Auto a = tblCar.getSelectionModel().getSelectedItem();
            String[] datosSeleccionados = {a.getTipo(), a.getMarca(), a.getModelo(), a.getColor(),
                String.valueOf(a.getKilometraje()), String.valueOf(a.getPrecio()), String.valueOf(a.getAnio()),
                 a.getImagen(), a.getDescripcion()};
            try (BufferedReader br = new BufferedReader(
                    new FileReader(App.pathArchivos + "autos.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.equals(String.join(",", datosSeleccionados))) {
                        
                    }
                }
            } catch (IOException eio) {
                eio.printStackTrace();
            }
        });

    }

    void cargarAutos(ArrayListAuto<Auto> autos) {

        ObservableList<Auto> list = FXCollections.observableArrayList(autos);
        idTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        idMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        idModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        idColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        idKim.setCellValueFactory(new PropertyValueFactory<>("kilometraje"));
        idPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        idAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));

        tblCar.setItems(list);

    }

}
