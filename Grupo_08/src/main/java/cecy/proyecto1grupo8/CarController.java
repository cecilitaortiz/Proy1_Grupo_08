/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import cecy.proyecto1grupo8.App;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Saikotek
 */
public class CarController implements Initializable {

    @FXML
    private Label lblMessage;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAction;
    @FXML
    private TextField txtYear;
    @FXML
    private ComboBox<?> cboType;
    @FXML
    private ComboBox<?> cboBrand;
    @FXML
    private ComboBox<?> cboModel;
    @FXML
    private ComboBox<?> cboColor;
    @FXML
    private Button btnAddColor;
    @FXML
    private Button btnAddType;
    @FXML
    private Button btnAddBrand;
    @FXML
    private Button btnAddModel;
    @FXML
    private TextField txtMiliage;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ImageView imgCar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.SetButtons();
       
    }    
    
    void SetButtons(){
         //btnAction.setOnAction(e -> App.setRoot());
        
        btnBack.setOnAction(e -> {
            if (Action("Confirmation Dialog","¿Desea salir?")== true){
               ExitWindow();
            }
        });
              
        btnAction.setOnAction(e -> {
            if (Action("Confirmation Dialog","¿Está correcta la información?") == true){
                if (SaveCar()==true)
                    ExitWindow();
                };
        });
    };
        
    boolean Action(String title, String message){
        boolean result = false;
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        //alert.setContentText(message);

        Optional<ButtonType> buttonSelected = alert.showAndWait();
        if (buttonSelected.get() == ButtonType.OK){
            // ... user chose OK
            result = true;
        } else {
            // ... user chose CANCEL or closed the dialog
            result = false;
        };
        
        return result;
    };
    
    boolean SaveCar(){
        boolean result = false;
        try {
            //archivo
            
            
            
            result = true;
        } catch (Exception e) {
            //mostrar error
        }
        // Save car to db
        
        
        return result;  
    };
    
    void ExitWindow(){
        try {
            App.setRoot("maintenance");
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }
}
