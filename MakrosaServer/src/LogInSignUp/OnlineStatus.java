/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInSignUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author user
 */
public class OnlineStatus {
    String username;
    public boolean on;
    
    public OnlineStatus(String username)    
    {
        this.username=username;
        this.on=false;
    }
    
    
    public boolean isOnline() throws IOException    
    {
        FileReader fr= new FileReader("src/LogInSignUp/onlineInfo.txt");
        BufferedReader br= new BufferedReader(fr); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            String[] up=line.split(" ");
            if(up[0].equals(username) && up[1].equals("online"))    {
                on=true;
                break;
            }
        }
        return on;
    }
    
    public void setOnline()throws IOException
    {
        FileReader fr= new FileReader("src/LogInSignUp/onlineInfo.txt");
        BufferedReader br= new BufferedReader(fr); 
        
        FileWriter fw= new FileWriter("src/LogInSignUp/onlineInfo_tmp.txt");
        BufferedWriter bw= new BufferedWriter(fw); 
        boolean isFound=false;
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            String[] up=line.split(" ");
            if(up[0].equals(username))    {
                line=up[0]+" "+"online";
                isFound=true;
            }
            bw.write(line);
            bw.newLine();
        }
        
        if(!isFound)    {
            String line=username+" "+"online";
            bw.write(line);
            bw.newLine();
        }
        
        br.close();
        fr.close();
        bw.close();
        fw.close();
        
        fr= new FileReader("src/LogInSignUp/onlineInfo_tmp.txt");
        br= new BufferedReader(fr);
        fw= new FileWriter("src/LogInSignUp/onlineInfo.txt");
        bw= new BufferedWriter(fw); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            bw.write(line);
            bw.newLine();
        }
        
        br.close();
        fr.close();
        bw.close();
        fw.close();
        on=true;
        
        new File("src/LogInSignUp/onlineInfo_tmp.txt").delete();
        
       
    }
    public void setOffline()throws IOException
    {
        FileReader fr= new FileReader("src/LogInSignUp/onlineInfo.txt");
        BufferedReader br= new BufferedReader(fr); 
        
        FileWriter fw= new FileWriter("src/LogInSignUp/onlineInfo_tmp.txt");
        BufferedWriter bw= new BufferedWriter(fw); 
        boolean isFound=false;
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            String[] up=line.split(" ");
            if(up[0].equals(username))    {
                line=up[0]+" "+"offline";
                isFound=true;
            }
            bw.write(line);
            bw.newLine();
        }
        
        if(!isFound)    {
            String line=username+" "+"offline";
            bw.write(line);
            bw.newLine();
        }
        br.close();
        fr.close();
        bw.close();
        fw.close();
        fr= new FileReader("src/LogInSignUp/onlineInfo_tmp.txt");
        br= new BufferedReader(fr);
        fw= new FileWriter("src/LogInSignUp/onlineInfo.txt");
        bw= new BufferedWriter(fw); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            bw.write(line);
            bw.newLine();
        }
        
        br.close();
        fr.close();
        bw.close();
        fw.close();
        on=false;
        
        new File("src/LogInSignUp/onlineInfo_tmp.txt").delete();
    }
}
