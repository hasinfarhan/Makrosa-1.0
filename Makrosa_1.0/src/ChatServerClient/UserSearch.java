/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatServerClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class UserSearch {
    public String username;
    public String searchedUsername;
    public String relation;
    
    public UserSearch(String username,String searchedUsername)
    {
        this.username=username;
        this.searchedUsername=searchedUsername;
        relation="NULL";
        
    }
    
    
    public String RunCheck()throws IOException
    {
        FileReader fr= new FileReader("src/UserFriends/"+username+".txt");
        BufferedReader br= new BufferedReader(fr); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            if(line.equals(searchedUsername))    {
                relation="Friend";
                br.close();
                fr.close();
                return relation;
            }
           
        }
        fr=new FileReader("src/LogInSignUp/MakrosaLogInInfo.txt");
        br= new BufferedReader(fr); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            String name=line.split(" ")[0];
            
            if(name.equals(searchedUsername))    {
                relation="notFriend";
                br.close();
                fr.close();
                return relation;
            }
           
        }
        br.close();
        fr.close();
        return relation;
    }
    
    
    
    public void AddSearchedUser() throws IOException
    {
        FileWriter fw= new FileWriter("src/UserFriends/"+username+".txt",true);
        BufferedWriter bw= new BufferedWriter(fw); 
        bw.write(searchedUsername+"\n");
        bw.close();
        fw.close();
        
    }
    
  
}
