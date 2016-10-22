/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author user
 */
import LogInSignUp.OnlineStatus;
import NetUtil.ConnectionUtillities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author uesr
 */
public class Reader implements Runnable{
    public ConnectionUtillities connection;
    public Thread thread;
    public ServerController sc;
    public String clientUsername;
    public Initialize init;
    public Reader(ConnectionUtillities con,ServerController sc,String clientUsername,Initialize init){
        connection=con;
        this.sc=sc;
        this.clientUsername=clientUsername;
        this.init=init;
        //sc.textArea.appendText("Makrosa server has been initialized successfully again:)\n");
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        FileWriter fw=null;
        BufferedWriter bw=null;
        FileReader fr=null;
        BufferedReader br=null;
        String history;
        while(true){
            Object o=connection.read();
            String s=o.toString();
            if(s.equals("EXIT"))    {
                connection.write("DIE");
                OnlineStatus onlineStatus= new OnlineStatus(clientUsername);
                try {
                    onlineStatus.setOffline();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            
            String command=s.split("%%")[0];
            String arg=s.split("%%")[1];
            //System.out.println(command);
            
            if(command.equals("CHATIN"))    {
                String A=clientUsername;
                String B=arg;
                String tmp;
                if(A.compareTo(B)>0)    {
                    tmp=A;
                    A=B;
                    B=tmp;
                }
                
                try {
                    fw = new FileWriter("src/ChatServerClient/"+A+"_"+B+"_chathistory.txt",true);
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                bw= new BufferedWriter(fw);
                
                try {
                    fr = new FileReader("src/ChatServerClient/"+A+"_"+B+"_chathistory.txt");
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                br=new BufferedReader(fr);
                history="";
                while(true)    {
                    String line="";
                    try {
                        line=br.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(line==null)    break;
                    history+=line+"\n";
                    
                }
                
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                sc.init.facetofaceMap.put(clientUsername,arg);
                connection.write("DIE");
                //prev mainmenu reader thread dies and new chat reader thread in client starts
                connection.write(history);
                continue;
            }
            if(command.equals("CHATOUT"))    {
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                sc.init.facetofaceMap.put(clientUsername,null);
                connection.write("DIEE");
                continue;
            }
            
            
            
            
            String head=s.split("%%MAKROSA%%")[0];
            String tail=s.split("%%MAKROSA%%")[1];
            ConnectionUtillities target=sc.init.connectionMap.get(head);
            if(target!=null)    {
                //System.out.println("wwwwwwwww");
                if(sc.init.facetofaceMap.get(head).equals(clientUsername))    {
                    target.write(clientUsername+" :-\n"+tail+"\n");
                }
                
                else    {
                    
                }
                
                try {
                    bw.write(head+" :-\n"+tail+"\n");
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                continue;
                
            }
            else    {
                try {
                    bw.write(head+" :-\n"+tail+"\n");
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                continue;
            }
            
            
            /*Platform.runLater(new Runnable(){
                @Override
                public void run()
                {
                   sc.textArea.appendText(head+"..."+tail);

                }
            });*/
            //System.out.println(o.toString());
            
            
            
        }
        //System.out.println("yoooooo");
        Platform.runLater(new Runnable(){
            @Override
            public void run()
            {
               sc.textArea.appendText("Client-"+clientUsername+" has Disconnected\n");
               sc.init.connectionMap.put(clientUsername,null);
            }
        });
        try {
            connection.Close();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.clientId--;
        //System.out.println("hoi"+sc.clientId);
    }
    
}