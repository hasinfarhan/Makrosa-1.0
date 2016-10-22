/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInSignUp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class SignUp {
    public String username;
    public String password;
    public String repassword;
    public String signUpAlert;
    public SignUp(String username,String password,String repassword)
    {
        this.username=username;
        this.password=password;
        this.repassword=repassword;
        signUpAlert="";
    }
    
    public String Check()throws IOException
    {
        String[] ud=username.split(" ");
        String[] pd=password.split(" ");
        String[] rpd=repassword.split(" ");
        if(ud.length>1 || pd.length>1)    {
            signUpAlert="No Space Allowed\n";
            return signUpAlert;
        }
        if(username.equals(""))    {
            signUpAlert="Fill username!\n";
            return signUpAlert;
        }
        if(password.equals(""))    {
            signUpAlert="Fill password!\n";
            return signUpAlert;
        }
        
        FileReader fr= new FileReader("src/LogInSignUp/MakrosaLogInInfo.txt");
        BufferedReader br= new BufferedReader(fr); 
        while(true)    {
            String line=br.readLine();
            if(line==null)    {
                break;
            }
            
            String[] up=line.split(" ");
            if(up[0].equals(username))    {
                signUpAlert="Username Exists!\n";
                return signUpAlert;
            }
        }
        if(!password.equals(repassword))    {
            signUpAlert="Re-enter Password Correctly\n";
            return signUpAlert;
        }
        br.close();
        fr.close();
        
        FileWriter fw= new FileWriter("src/LogInSignUp/MakrosaLogInInfo.txt",true);
        BufferedWriter bw= new BufferedWriter(fw); 
        bw.write(username+" "+password+"\n");
        bw.close();
        fw.close();
        new File("src/UserFriends/"+username+".txt").createNewFile();
        signUpAlert="OK";
        return signUpAlert;
        
    }
    
    
    
}
