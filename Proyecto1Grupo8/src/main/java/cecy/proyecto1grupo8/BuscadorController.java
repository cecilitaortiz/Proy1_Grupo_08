/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
    private ComboBox<String> cbTipo;
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

        cargarItems(cbMarca, cbColor, cbPrecio1, cbPrecio2, cbAnio1, cbAnio2, cbTipo);

        btnRegresar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                }
            }
        });
        btnBuscar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("SeleccionaTuAuto");
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
            ComboBox<String> cb6,
            ComboBox<String> cb7
    ) {

        ArrayList<Auto> autos;
        autos = Fichero.cargarAutos();

        ObservableList<String> itemsMarca = FXCollections.observableArrayList(getMarcas(autos));
        //falta agregar los elementos en la caja tipo
        ObservableList<String> itemsTipo = FXCollections.observableArrayList(getTipo(autos));
        ObservableList<String> itemsColor = FXCollections.observableArrayList(getColores(autos));
        ObservableList<String> itemsPrecio1 = FXCollections.observableArrayList(getPrecio(autos));
        ObservableList<String> itemsPrecio2 = FXCollections.observableArrayList(getPrecio(autos));
        ObservableList<String> itemsAnio1 = FXCollections.observableArrayList(getAnio(autos));
        ObservableList<String> itemsAnio2 = FXCollections.observableArrayList(getAnio(autos));

        cb1.getItems().addAll(itemsMarca);
        cb2.getItems().addAll(itemsColor);
        cb3.getItems().addAll(itemsPrecio1);
        cb4.getItems().addAll(itemsPrecio2);
        cb5.getItems().addAll(itemsAnio1);
        cb6.getItems().addAll(itemsAnio2);
        //cb7.getItems().addAll(itemsTipo);
    }

    public List<String> getMarcas(ArrayList<Auto> list) {
        Set<String> marcas = new HashSet<>();
        for (Auto auto : list) {
            marcas.add(auto.getMarca());
        }
        return new ArrayList<>(marcas);
    }

    public List<String> getColores(ArrayList<Auto> list) {
        Set<String> col = new HashSet<>();
        for (Auto auto : list) {
            col.add(auto.getColor());
        }
        return new ArrayList<>(col);
    }

    public List<String> getTipo(ArrayList<Auto> list) {
        Set<String> tipos = new HashSet<>();
        for (Auto auto : list) {
            tipos.add(auto.getTipo());
        }
        return new ArrayList<>(tipos);
    }

    public List<String> getAnio(ArrayList<Auto> list) {
        Set<String> anios = new HashSet<>();
        for (Auto auto : list) {
            anios.add(auto.getAnio().toString());
        }
        return new ArrayList<>(anios);
    }
    public List<String> getPrecio(ArrayList<Auto> list) {
        Set<String> precios = new HashSet<>();
        for (Auto auto : list) {
            precios.add(String.valueOf(auto.getPrecio()));
        }
        return new ArrayList<>(precios);
    }
    

}
