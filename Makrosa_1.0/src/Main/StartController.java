/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.TextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import Effects.Loading;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author user
 */
public class StartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public ImageView imageView;
    public Text text;
    public Circle circle;
    public Button proceed;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        File file = new File("src/Main/SpiderIcon.jpg");
        Image image=new Image(file.toURI().toString());
        imageView.setImage(image);
        circle.setRadius(0);
        text.setText("Loading...");
        proceed.setDisable(true);
        new Loading(1100,circle,8,text,"Ready",proceed);
        
         
    }    
    public void ProceedTouched(MouseEvent event)
    {
        proceed.setUnderline(true);
    }
    public void ProceedUntouched(MouseEvent event)
    {
        proceed.setUnderline(false);
    }
    
    public void ProceedClicked(ActionEvent event)
    {
        Parent logInParent = null;
        try {
            logInParent=FXMLLoader.load(getClass().getResource("Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logInScene=new Scene(logInParent);
        Stage logInStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        logInStage.setTitle("LogIn/SignUp");
        logInStage.setResizable(false);
        logInStage.setScene(logInScene);
        logInStage.show();
    }
    
   
    
    
}
