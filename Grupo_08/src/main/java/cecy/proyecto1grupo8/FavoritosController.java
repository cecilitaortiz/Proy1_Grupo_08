/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Cecy
 */
public class FavoritosController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnRegresar;
    @FXML
    private HBox hb;

    public static String[] fav = { "Favorito" };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        try {
            SeleccionaTuAutoController.LlenarDatos(hb, App.crearArrayList("favoritos.txt"), fav);
        } catch (FileNotFoundException ex) {
        }
        btnRegresar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("principal");
                } catch (IOException ex) {
                }
            }
        });
    }
}
