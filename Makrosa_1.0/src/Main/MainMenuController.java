/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import ChatServerClient.UserSearch;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import LogInSignUp.CurrentUserdata;
import java.io.File;
import ChatServerClient.AddUser;
import javafx.scene.input.MouseEvent;
import Client.Client;
import Client.Reader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public Button search;
    public Button add;
    public Button chat;
    public TextField searchArea;
    public String username;
    public Label notFound;
    public Label greetings;
    public Label newMessage;
    public Label onlineStatus;
    public Client chatClient;
    public Reader reader;
    public TextArea showArea;
    int userId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        add.setDisable(true);
        chat.setDisable(true);
        notFound.setText("");
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
        username=line;
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
        
        greetings.setText("Welcome to Makrosa,"+username+"!\n");
    
        
         
    }    
    
    public void setChatClient(Client chatClient)
    {
        this.chatClient=chatClient;
        this.reader=new Reader(chatClient.connection,this);
        
    }
    public void SearchClicked(ActionEvent event)throws IOException
    {
        
        String input=searchArea.getText();
        if(input.equals(""))    return;
        String searchedUsername=input.split(" ")[0];
        UserSearch userSearch=new UserSearch(username,searchedUsername);
        if(userSearch.RunCheck().equals("Friend"))    {
            notFound.setText("Friend already!");
            chat.setDisable(false);
            add.setDisable(true);
        }
        else if(userSearch.RunCheck().equals("notFriend"))   {
            notFound.setText("Found!");
            add.setDisable(false);
            chat.setDisable(true);
        }
        else    {
            notFound.setText("Not found!");
            chat.setDisable(true);
            add.setDisable(true);
        }
        
    }
    
    public void SearchAreaTouched(MouseEvent event)
    {
        add.setDisable(true);
        chat.setDisable(true);
        notFound.setText("");
    }
    
    public void AddClicked(ActionEvent event) throws IOException
    {
        new AddUser(username,searchArea.getText().split(" ")[0]).Add();
        notFound.setText("Added!");
        add.setDisable(true);
        chat.setDisable(false);
    }
    
    public void ChatClicked(ActionEvent event)throws IOException
    {
  
        String searchedUsername=searchArea.getText().split(" ")[0];
        chatClient.connection.write("CHATIN"+"%%"+searchedUsername);
        
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
        bw.write(username+" "+searchedUsername+"\n");
        bw.close();
        fw.close();

        fw= new FileWriter("src/LogInSignUp/userCount.txt");
        bw= new BufferedWriter(fw);
        bw.write(Integer.toString(userCount));
        bw.close();
        fw.close();
        
        
        
        Parent ChatWindowParent = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatWindow.fxml"));
        ChatWindowParent=loader.load();
        Scene ChatWindowScene=new Scene(ChatWindowParent);
        Stage ChatWindowStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        ChatWindowStage.setTitle("Chat with : "+searchedUsername);
        
        //logInStage.setResizable(false);
        
        ChatWindowStage.setScene(ChatWindowScene);
        ChatWindowController controller=loader.<ChatWindowController>getController();
        controller.setChatClient(chatClient);
        ChatWindowStage.show();
        
        
    }
    
    
    public void LogOutClicked(ActionEvent event) throws IOException
    {
        chatClient.Disconnect();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent logInParent =loader.load();
        Scene logInScene=new Scene(logInParent);
        Stage logInStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        logInStage.setTitle("LogIn/SignUp");
        logInStage.setResizable(false);
        logInStage.setScene(logInScene);
        
        logInStage.show();
    }
    
    public void CloseClicked(ActionEvent event)throws IOException
    {
        chatClient.Disconnect();
        Platform.exit();
        
    }
    
    public void FriendsClicked(ActionEvent event) throws IOException
    {
        FileReader fr=new FileReader("src/UserFriends/"+username+".txt");
        BufferedReader br=new BufferedReader(fr);
        
        String show="";
        showArea.clear();
        showArea.appendText("FRIENDS :-\n");
        while(true)    {
            String line="";
            line=br.readLine();
            if(line==null)    break;
            show+=line+"\n";
        }
        
        
        
        showArea.appendText(show);
    }
    
    public void AddReqsClicked(ActionEvent event)throws IOException
    {
        FileReader fr=new FileReader("src/UserFriendRequests/"+username+".txt");
        BufferedReader br=new BufferedReader(fr);
        
        String show="";
        showArea.clear();
        showArea.appendText("FRIEND REQUESTS :-\n");
        while(true)    {
            String line="";
            line=br.readLine();
            if(line==null)    break;
            show+=line+"\n";
        }
        br.close();
        fr.close();
        
        
        
        showArea.appendText(show);
        
        
    }
    public void NotificationsClicked(ActionEvent event)
    {
        
    }
    
    
    
}
