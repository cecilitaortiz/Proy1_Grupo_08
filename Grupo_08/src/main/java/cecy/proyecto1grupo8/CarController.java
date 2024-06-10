/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Saikotek
 */
public class CarController implements Initializable {

    @FXML
    private Label lblConfirma;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAction;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtKim;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ImageView imgCar;

    public static String[] selected = null;

    /**
     * Initializes the controller class.
     */

    // {}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.SetButtons();
        Image img;

        if (selected == null) {
            try {
                img = new Image(new FileInputStream(App.pathImg + "auto.png"));
                imgCar.setImage(img);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            txtType.setText(selected[0]);
            txtMarca.setText(selected[1]);
            txtModelo.setText(selected[2]);
            txtColor.setText(selected[3]);
            txtKim.setText(selected[4]);
            txtPrice.setText(selected[5]);
            txtYear.setText(selected[6]);
            txtDescription.setText(selected[8]);
            try {
                img = new Image(new FileInputStream(App.pathImagenes + selected[7]));
                imgCar.setImage(img);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }

    }

    @SuppressWarnings("empty-statement")
    public void SetButtons() {
        //btnAction.setOnAction(e -> App.setRoot());

        btnBack.setOnAction(e -> {
            if (Action("Confirmation Dialog", "¿Desea salir?") == true) {
                ExitWindow();
            }
        });

        btnAction.setOnAction(e -> {
            String tipo = txtType.getText();
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            String color = txtColor.getText();
            String anio = txtYear.getText();
            String kim = txtKim.getText();
            String precio = txtPrice.getText();
            String desc = txtDescription.getText();
            String[] line = {tipo, marca, modelo, color, anio, kim, precio, App.pathImg + "auto.png", desc};
            Fichero.escribir(App.pathArchivos + "autos.txt", String.join(", ", line));
            lblConfirma.setText("Ingreso con éxito");
            SaveCar();
        });
    }

    ;
        
    boolean Action(String title, String message) {
        boolean result = false;

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        //alert.setContentText(message);

        Optional<ButtonType> buttonSelected = alert.showAndWait();
        if (buttonSelected.get() == ButtonType.OK) {
            // ... user chose OK
            result = true;
        } else {
            // ... user chose CANCEL or closed the dialog
            result = false;
        };

        return result;
    }

    ;
    
    boolean SaveCar() {
        boolean result = false;
        try {
            //archivo

            result = true;
        } catch (Exception e) {
            //mostrar error
        }
        // Save car to db

        return result;
    }

    ;
    
    void ExitWindow() {
        try {
            App.setRoot("basedatos");
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }

}
