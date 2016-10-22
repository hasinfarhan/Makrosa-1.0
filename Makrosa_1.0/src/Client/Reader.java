/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import NetUtil.ConnectionUtillities;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import Main.ChatWindowController;
import Main.MainMenuController;
/**
 *
 * @author uesr
 */
public class Reader implements Runnable{
    public ConnectionUtillities connection;
    public Thread thread;
    //public Client c;
    public ChatWindowController cc;
    public MainMenuController mc;
    public int version;
    public Reader(ConnectionUtillities con,MainMenuController mc){
        version=1;
        connection=con;
        this.mc=mc;
        cc=null;
        thread=new Thread(this);
        thread.start();
    }
    
    public Reader(ConnectionUtillities con,ChatWindowController cc){
        version=2;
        connection=con;
        this.cc=cc;
        mc=null;
        thread=new Thread(this);
        thread.start();
    }
    
     

    @Override
    public void run() {
        if(version==1)    {
            while(true){
                //System.out.println("v1:S");
                Object o=connection.read();
                String s=o.toString();
                if(s.equals("DIE"))    {
                    break;
                }
            }
            //System.out.println("v1:E");
        }
        
        else if(version==2)    {
            while(true){
                //System.out.println("v2:S");
                Object o=connection.read();
                String s=o.toString();
                if(s.equals("DIEE"))    {
                    break;
                }
                Platform.runLater(new Runnable(){
                    @Override
                    public void run()
                    {
                       cc.chatWindow.appendText(s);
                    }
                });
                
                
                
            }
            //System.out.println("v2:E");
        }
        
    }
        //System.out.println("oeeeeeeeeeeeeeeee");
    
        /*while(true){
            Object o=connection.read();
            String s=o.toString();
            if(s.equals("EXIT"))    {
                break;
            }*/
            
            
            
            /*FileWriter fw=null;
            try {
               fw=new FileWriter("src/Client/online_chatstream_"+cc.clientName+"_tmp.txt");
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }

            BufferedWriter bw= new BufferedWriter(fw);

            try {
                bw.write(s);
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        
}
    
