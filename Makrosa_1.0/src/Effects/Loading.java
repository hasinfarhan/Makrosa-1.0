/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Effects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
/**
 *
 * @author user
 */
public class Loading implements Runnable {
    public int pause;
    public Circle circle;
    public Text text;
    public Thread thread;
    double radius;
    public Button button;
    public Label alert;
    int  version;
    String s;
    public Loading(int pause,Circle circle,double radius,Text text,String s,Button button)
    {
        version=1;
        this.pause=pause;
        this.circle=circle;
        this.text=text;
        this.radius=radius;
        this.s=s;
        this.button=button;
        thread=new Thread(this);
        thread.start();
    }
    public Loading(int pause,Label alert,String s)
    {
        version=2;
        this.pause=pause;
        this.alert=alert;
        this.text=text;
        
        this.s=s;
        
        thread=new Thread(this);
        thread.start();
    }
    public  void run()
    {
        
        Platform.runLater(new Runnable(){
            @Override
            public void run()
            {
                //System.out.println("yo");
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(version==1)    circle.setRadius(radius);
                if(version==1)    text.setText(s);
                if(version==2)    alert.setText(s);
                if(version==1)    button.setDisable(false);
                //System.out.println("oy");
            }
        });
    }
    
}
