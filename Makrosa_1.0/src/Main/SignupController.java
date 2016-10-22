/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Client.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import LogInSignUp.SignUp;
import Effects.Loading;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import LogInSignUp.CurrentUserdata;
import LogInSignUp.OnlineStatus;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * FXML Controller class
 *
 * @author user
 */
public class SignupController implements Initializable {

    
    
    public TextField username;
    public TextField password;
    public TextField repassword;
    public TextField fullname;
    public TextField mail;
    public TextField dateOfBirth;
    public Label alert;
    public Button signUp;
    int userId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alert.setText("");
        
    }    
    
    public void SignUpClicked(ActionEvent event) throws IOException
    {
        SignUp sign=new SignUp(username.getText(),password.getText(),repassword.getText());
        if(sign.Check().equals("OK"))    {
            /*System.out.println("Signup and login successfull");
            OnlineStatus onlineStatus= new OnlineStatus(username.getText());
            //onlineStatus.setOnline();
            FileReader fr=new FileReader("src/LogInSignUp/userCount.txt");
            BufferedReader br= new BufferedReader(fr);
            int userCount=Integer.parseInt(br.readLine());
            userCount=(userCount+1)%10007;
            userId=userCount;
            
            br.close();
            fr.close();
            
            new File("src/Main/userdata_"+userId+"_tmp.txt").createNewFile();
            FileWriter fw= new FileWriter("src/Main/userdata_"+userId+"_tmp.txt");
            BufferedWriter bw= new BufferedWriter(fw);
            bw.write(username.getText()+" "+password.getText()+"\n");
            bw.close();
            fw.close();
            fw= new FileWriter("src/LogInSignUp/userCount.txt");
            bw= new BufferedWriter(fw);
            bw.write(Integer.toString(userCount));
            bw.close();
            fw.close();
            
            Client chatClient=new Client("127.0.0.1",4321,username.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent mainMenuParent = loader.load();
            MainMenuController controller=loader.<MainMenuController>getController();
            controller.setChatClient(chatClient);
            Scene mainMenuScene=new Scene(mainMenuParent);
            Stage mainMenuStage=(Stage)((Node)event.getSource()).getScene().getWindow();
            mainMenuStage.setTitle("Main Menu");
            //logInStage.setResizable(false);
            mainMenuStage.setScene(mainMenuScene);
            
            mainMenuStage.show();*/
            
            new File("src/UserFriendRequests/"+username.getText()+".txt").createNewFile();
            
            Parent logInParent = null;
            try {
                logInParent=FXMLLoader.load(getClass().getResource("Login.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene logInScene=new Scene(logInParent);
            Stage logInStage=(Stage)((Node)event.getSource()).getScene().getWindow();
            logInStage.setTitle("LogIn/SignUp");
            logInStage.setResizable(false);
            logInStage.setScene(logInScene);
            logInStage.show();
        }
        else if(sign.Check().equals("Fill username!\n"))    {
            repassword.clear();
            alert.setText(sign.Check());
            return;
        }
         else if(sign.Check().equals("Fill password!\n"))    {
            repassword.clear();
            alert.setText(sign.Check());
            return;
        }
        else if(sign.Check().equals("No Space Allowed\n"))   {
            username.clear();
            password.clear();
            repassword.clear();
            alert.setText(sign.Check());
            return;
        }
        else if(sign.Check().equals("Username Exists!\n"))   {
            username.clear();
            repassword.clear();
            alert.setText(sign.Check());
            return;
        }
        else if(sign.Check().equals("Re-enter Password Correctly\n"))   {
            repassword.clear();
            alert.setText(sign.Check());
            return;
        }
    }
    
    public void BackClicked(ActionEvent event)
    {
        Parent logInParent = null;
        try {
            logInParent=FXMLLoader.load(getClass().getResource("Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene logInScene=new Scene(logInParent);
        Stage logInStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        logInStage.setTitle("LogIn/SignUp");
        logInStage.setResizable(false);
        logInStage.setScene(logInScene);
        logInStage.show();
        
        
    }
    
    
}
