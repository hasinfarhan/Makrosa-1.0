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
import java.io.IOException;

/**
 *
 * @author user
 */
public class LogInCheck {
    String username;
    String password;
    public boolean logInTrue;
    
    public LogInCheck(String username,String password)    
    {
        this.username=username;
        this.password=password;
        logInTrue=false;
    }
    
    public boolean runCheck() throws IOException    
    {
        if(username.split(" ").length>1||password.split(" ").length>1)    return logInTrue;
        
        if(username.equals("")||password.equals(""))    return logInTrue;
        FileReader fr= new FileReader("src/LogInSignUp/MakrosaLogInInfo.txt");
        BufferedReader br= new BufferedReader(fr); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            String[] up=line.split(" ");
            if(up[0].equals(username) && up[1].equals(password))    {
                logInTrue=true;
                break;
            }
        }
        br.close();
        fr.close();
        return logInTrue;
    }

    
}
