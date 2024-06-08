/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.*;
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
    private ComboBox<String> cbKim1;
    @FXML
    private ComboBox<String> cbKim2;
    @FXML
    private ComboBox<String> cbPrecio1;
    @FXML
    private ComboBox<String> cbPrecio2;
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

        cargarItems(cbMarca, cbColor, cbKim1, cbKim2, cbPrecio1, cbPrecio2, cbModelo);

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

        Queue<Auto> autos;
        autos = Fichero.cargarAutos();
        getMarcas(autos, cb1);
        getColores(autos, cb2);
        getKim(cb3);
        getKim(cb4);
        getPrecio(cb5);
        getPrecio(cb6);
        cbMarca.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String seleccion = cbMarca.getValue();
                getModelo(autos, seleccion, cb7);
            }
        });

    }

    public void getMarcas(Queue<Auto> list, ComboBox<String> cb) {
        Set<String> marcas = new HashSet<>();
        for (Auto auto : list) {
            marcas.add(auto.getMarca());
        }
        for (String string : marcas) {
            cb.getItems().add(string);
        }

    }

    public void getColores(Queue<Auto> list, ComboBox<String> cb) {
        Set<String> colores = new HashSet<>();
        for (Auto auto : list) {
            colores.add(auto.getColor());
        }
        for (String string : colores) {
            cb.getItems().add(string);
        }
    }

    public void getTipo(Queue<Auto> list, ComboBox<String> cb) {
        Set<String> tipo = new HashSet<>();
        for (Auto auto : list) {
            tipo.add(auto.getTipo());
        }
        for (String string : tipo) {
            cb.getItems().add(string);
        }
    }

    public void getKim(ComboBox<String> cb) {
        cb.getItems().add("0");
        cb.getItems().add("30000");
        cb.getItems().add("60000");
        cb.getItems().add("9000");

    }

    public void getPrecio(ComboBox<String> cb) {
        cb.getItems().add("0");
        cb.getItems().add("5000");
        cb.getItems().add("10000");
        cb.getItems().add("15000");
        cb.getItems().add("20000");
        cb.getItems().add("25000");
        cb.getItems().add("30000");

    }

    public void getModelo(Queue<Auto> list, String marca, ComboBox<String> cbModelo) {
        Set<String> modelos = new HashSet<>();
        for (Auto auto : list) {
            if (marca != null && auto.getMarca().equals(marca)) {
                modelos.add(auto.getModelo());
            }

        }
        cbModelo.getItems().clear();
        for (String string : modelos) {

            cbModelo.getItems().add(string);
        }
    }
}
