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
public class AddUser {
    public String username;
    public String addUser;
    
    public AddUser(String username,String addUser)
    {
        this.username=username;
        this.addUser=addUser;
    }
    
    
    public void Add()throws IOException
    {
        /*FileReader fr=new FileReader("src/UserFriendRequests/"+username+".txt");
        BufferedReader br=new BufferedReader(fr);
        FileWriter fw=new FileWriter("src/UserFriendRequests/"+username+"_tmp.txt",true);
        BufferedWriter bw=new BufferedWriter(fw);
        boolean isFound=false;
        while(true)
        {
            String line=br.readLine();
            if(line==null)    break;
            if(line.equals(addUser))    {
                isFound=true;
                bw.write("");
            }
            else    {
                bw.write(line+"\n");
            }
        }
        
        if(!isFound)    {
            bw.write(addUser+"\n");
        }*/
        
        
        
        
        FileWriter fw= new FileWriter("src/UserFriends/"+username+".txt",true);
        BufferedWriter bw= new BufferedWriter(fw);
        bw.write(addUser+"\n");
        bw.close();
        fw.close();
        
    }
    
}
