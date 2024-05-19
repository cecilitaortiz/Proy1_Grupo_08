/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Cecy
 */
public class BuscadorController implements Initializable {

    @FXML
    private ComboBox<String> cbMarca;
    @FXML
    private RadioButton rbNuevo;
    @FXML
    private RadioButton rbUsado;
    @FXML
    private ComboBox<String> cbColor;
    @FXML
    private ComboBox<String> cbModelo;
    @FXML
    private ComboBox<String> cbPrecio1;
    @FXML
    private ComboBox<String> cbPrecio2;
    @FXML
    private ComboBox<String> cbAnio1;
    @FXML
    private ComboBox<String> cbAnio2;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cargarItems(cbMarca, cbColor, cbPrecio1, cbPrecio2, cbAnio1, cbAnio2);
        
        btnRegresar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                }
            }
        });
        
        final ToggleGroup group = new ToggleGroup();
        rbNuevo.setToggleGroup(group);
        rbUsado.setToggleGroup(group);
    }

    public void cargarItems(
            ComboBox<String> cb1,
            ComboBox<String> cb2,
            ComboBox<String> cb3,
            ComboBox<String> cb4,
            ComboBox<String> cb5,
            ComboBox<String> cb6
    ) {
        ObservableList<String> itemsMarca = FXCollections.observableArrayList("Chevrolet", "Nissan", "Ford");
        // ObservableList<String> itemsModelo = FXCollections.observableArrayList("Chevrolet", "Nissan", "Ford");
        ObservableList<String> itemsColor = FXCollections.observableArrayList("Blanco", "Negro", "Rojo");
        ObservableList<String> itemsPrecio1 = FXCollections.observableArrayList("$1000", "$5000", "$10000", "$15000");
        ObservableList<String> itemsPrecio2 = FXCollections.observableArrayList("$5000", "$10000", "$15000", "$20000");
        ObservableList<String> itemsAnio1 = FXCollections.observableArrayList("2000", "2010", "2020", "2024");
        ObservableList<String> itemsAnio2 = FXCollections.observableArrayList("2000", "2010", "2020", "2024");

        cb1.getItems().addAll(itemsMarca);
        cb2.getItems().addAll(itemsColor);
        cb3.getItems().addAll(itemsPrecio1);
        cb4.getItems().addAll(itemsPrecio2);
        cb5.getItems().addAll(itemsAnio1);
        cb6.getItems().addAll(itemsAnio2);

    }

}
