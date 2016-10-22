/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author user
 */
public class Pop implements Runnable{
    public ServerSocket sSocket;
    public ServerController sc;
    Thread thread;
    
    public Pop(ServerController sc,int port) throws IOException
    {
        this.sc=sc;
        sSocket=new ServerSocket(port);
        sc.clientId=0;
        sc.textArea.appendText("POP server has been initialized successfully :)\n");
        System.out.println("Server is going to PoP");
        thread = new Thread(this);
        thread.start();
    }
    
    public void run() 
    {
        try{
            while(!Thread.currentThread().isInterrupted())    {
                System.out.println("Server is going to Pok");
                Socket clientSocket=sSocket.accept();
                sc.clientId++;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
