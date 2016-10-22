/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author user
 */
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Makrosa extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Loading...");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
