/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import NetUtil.ConnectionUtillities;
import java.io.IOException;
import javafx.application.Platform;


/**
 * FXML Controller class
 *
 * @author user
 */
public class Client  {

    /**
     * Initializes the controller class.
     */
   
    
    public String clientName;
    public ConnectionUtillities connection;
    public int port;
    public String tcpServerAddress;
    
    public Client(String address,int port,String clientName)
    {
        tcpServerAddress=address;
        this.port=port;
        this.clientName=clientName;
        connection=new ConnectionUtillities(tcpServerAddress ,port);
        connection.write(clientName);
    }
    
    public void Disconnect() throws IOException
    {
        connection.write("EXIT");
    }
    public void SendText(String text)
    {
        connection.write(text);
        
    }
    
    
}
