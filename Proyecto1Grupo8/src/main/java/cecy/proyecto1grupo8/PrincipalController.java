/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cecy.proyecto1grupo8;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Cecy
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button buttonAutos;
    @FXML
    private Button buttonRegitros;
    @FXML
    private ImageView imagePrincipal;
    @FXML
    private ImageView imageLupa;
    @FXML
    private ImageView ImageLista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarImagenes();
        
        buttonAutos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    App.setRoot("buscador");
                } catch (IOException ex) {
                }
            }
        });
    }    
    public void cargarImagenes(){
        try{
            Image img1 = new Image(new FileInputStream(App.pathImg+"lupa.png"));
            Image img2 = new Image(new FileInputStream(App.pathImg+"visto.png"));
            imageLupa.setImage(img1);
            ImageLista.setImage(img2);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
       
    }
}
