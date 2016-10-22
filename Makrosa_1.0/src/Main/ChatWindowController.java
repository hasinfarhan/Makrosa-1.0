/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Client.Client;
import Client.Reader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class ChatWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public Button send;
    public TextField message;
    public TextArea chatWindow;
    public Label friendName;
    public String username;
    public String friendUsername;
    public Client chatClient;
    public Reader reader;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        FileReader fr=null;
         try {
            fr=new FileReader("src/LogInSignUp/userCount.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedReader br= new BufferedReader(fr);
        
        String line=null;
        try {
            line=br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int userCount=Integer.parseInt(line);
        int userId;
        userId=userCount;
        
        try {
            fr = new FileReader("src/Main/userdata_"+userId+"_tmp.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        br= new BufferedReader(fr);
        
        line=null;
        try {
            line=br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] up=line.split(" ");
        username=up[0];
        friendUsername=up[1];
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new File("src/Main/userdata_"+userId+"_tmp.txt").delete();
        friendName.setText("You and "+friendUsername);
        
        
    }    
    public void setChatClient(Client chatClient)
    {
        this.chatClient=chatClient;
        this.reader=new Reader(chatClient.connection,this);
    }
    
    public void SendClicked(ActionEvent event)
    {
        String s=message.getText();
        if(s.equals(""))    return;
        
        String head=friendUsername+"%%MAKROSA%%";
        String tail=s+"\n";
        String packet=head+tail;
        chatClient.connection.write(packet);
        chatWindow.appendText(username+" :-\n"+tail+"\n");
        message.clear();
    }
    
    
    public void HomeClicked(ActionEvent event)throws IOException 
    {
        chatClient.connection.write("CHATOUT"+"%%"+friendUsername);
        int userId;
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
        bw.write(username+"\n");
        bw.close();
        fw.close();

        fw= new FileWriter("src/LogInSignUp/userCount.txt");
        bw= new BufferedWriter(fw);
        bw.write(Integer.toString(userCount));
        bw.close();
        fw.close();
        
        
        
        
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
    
    public void EraseAllClicked(ActionEvent event)
    {
        
    }
    public void BlockClicked(ActionEvent event)
    {
        
    }
}
