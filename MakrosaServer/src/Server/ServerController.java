/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import NetUtil.ConnectionUtillities;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ServerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //public Button Initialize;
    public TextArea textArea;
    public int clientId;
    Initialize init;
    Pop x;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initializeClicked() throws IOException
    {
        System.out.println("Server is going to Initialize");
        init=new Initialize(this,4321);
    }
    
    public void terminateClicked() throws IOException
    {
        ConnectionUtillities con=new ConnectionUtillities("127.0.0.1",4321); 
        con.write("TERMINATE");
        con.Close();
        Platform.exit();
    }
    
    
    

    
    
}
