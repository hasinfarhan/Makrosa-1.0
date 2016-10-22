/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import LogInSignUp.OnlineStatus;
import NetUtil.ConnectionUtillities;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author user
 */
public class Initialize implements Runnable {
    public ServerSocket sSocket;
    public ServerController sc;
    ConnectionUtillities connection;
    public HashMap<String,ConnectionUtillities> connectionMap=new HashMap<>();
    public HashMap<String,String>facetofaceMap=new HashMap<>();
    Thread thread;
    
    public Initialize(ServerController sc,int port) throws IOException
    {
        this.sc=sc;
        sSocket=new ServerSocket(port);
        this.sc.textArea.appendText("Makrosa server has been initialized successfully :)\n");
        thread = new Thread(this);
        thread.start();
    }
    
    
    public void run() 
    {
        try{
           while(true)    {
               Socket clientSocket=sSocket.accept();
               connection=new ConnectionUtillities(clientSocket);
               
               String clientUsername=connection.read().toString();
               
               if(clientUsername.equals("TERMINATE"))    break;
               else    {
                   connectionMap.put(clientUsername,connection);
                   OnlineStatus onlineStatus= new OnlineStatus(clientUsername);
                   onlineStatus.setOnline();
               }
               
               sc.clientId++;
               Platform.runLater(new Runnable(){
                    @Override
                    public void run()
                    {
                       sc.textArea.appendText("Client-"+clientUsername+" has Connected\n");
                    }
                });
               
               new Reader(connection,sc,clientUsername,this);
               
               
               
           }
           System.out.println("off and go\n");
           connection.Close();
        }
        catch(Exception ex)    {
            ex.printStackTrace();
        }
    }
}
