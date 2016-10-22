/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Client.Client;
import LogInSignUp.CurrentUserdata;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import LogInSignUp.LogInCheck;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import LogInSignUp.OnlineStatus;
import javafx.scene.control.PasswordField;
/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Button signUp;
    public Button retrieve;
    public Label wrongLogIn;
    public TextField username;
    public PasswordField password;
    int userId;
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO]
        wrongLogIn.setText("");
        
    }  
    
    
    public void LogInClicked(ActionEvent event)throws IOException 
    {
        String u=username.getText();
        String p=password.getText();
        
        LogInCheck logInCheck=new LogInCheck(u,p); 
        
        if(logInCheck.runCheck())    {
            OnlineStatus onlineStatus= new OnlineStatus(u);
            if(onlineStatus.isOnline())    {
                wrongLogIn.setText("User Already LoggedIn!\n");
                return;
            }
            System.out.println("login successfull");
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
            bw.write(username.getText()+"\n");
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
            
            mainMenuStage.show();
        }
        
        else    {
            wrongLogIn.setText("Wrong username or password\n");
            username.clear();
            password.clear();
            return;
        }
        
    }
    
    
    public void SignUpClicked(ActionEvent event)
    {
        Parent signUpParent = null;
        try {
            signUpParent=FXMLLoader.load(getClass().getResource("signup.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene signUpScene=new Scene(signUpParent);
        Stage signUpStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        signUpStage.setTitle("SignUp");
        signUpStage.setResizable(false);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }
    
    public void RetrieveClicked(ActionEvent event)
    {
        
    }
    
    
    
    
    
    
}
