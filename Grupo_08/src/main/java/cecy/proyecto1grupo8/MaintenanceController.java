/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import cecy.proyecto1grupo8.App;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author Saikotek
 */
public class MaintenanceController implements Initializable {


    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView<String> tblCar;
    @FXML
    private TableColumn<?, ?> idCar;
    @FXML
    private TableColumn<?, ?> idBrand;
    @FXML
    private TableColumn<?, ?> idModel;
    @FXML
    private TableColumn<?, ?> idColor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.SetButtons();
        this.LoadCar();    
        
    }    
    
    void SetButtons(){
           btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        });
        
        btnAdd.setOnAction(e -> {
            try {
                App.setRoot("car");
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        });
        
        btnEdit.setOnAction(e -> {
            try {
                App.setRoot("car");
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        });
        
        btnDelete.setOnAction(e -> {
            try {
                App.setRoot("car");
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
        });
   
    }
    
    void LoadCar(){
        try {
            
        } catch (Exception e) {
        }
    }
    
}
