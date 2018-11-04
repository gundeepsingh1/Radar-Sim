/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radar_sim;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


/**
 *
 * @author gunde
 */
public class FXMLDocumentController extends Thread implements Initializable{
    
    PathCurve pc = new PathCurve();
    PastCurve ptc = new PastCurve();
    Timeline tml = new Timeline();
    Animator ani = new Animator();
    
    Path path1 = new Path();
    Path path3 = new Path();
    
    KeyValue kvx,
             kvy;
    KeyFrame kf;
            
    long time,
         time2 ;
    
    Shape shape,
          shape2;
    
     double xx,
            yy,
            zz;
    @FXML
    TextField x,
              y,
              z;
    @FXML
    Circle radar,
           target,
           target1,
           hit;
    
    @FXML
    Rectangle rect;
    @FXML
    Button go;
    @FXML
    Label crash;
    @FXML
    AnchorPane ap;
    @FXML     
    Scene scene ;
    

     Interpolator InterpolatorX = new Interpolator() {
            @Override
            protected double curve(double t) {
         
            return (pc.X_value(t));
                     }
                };
            
    Interpolator InterpolatorY = new Interpolator() {
            @Override
            protected double curve(double t) {
        
            return (pc.Y_value(t));  
                     };            
                };  
    public void run(){System.out.print("before");
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(" After ");
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        target.setVisible(false);
        target1.setVisible(false);
        hit.setVisible(false);
        shape = Shape.subtract(rect, radar);
        
        pc.setRadar(radar.getLayoutX(),radar.getLayoutY(),radar.getRadius());
        ptc.setRadar(radar.getLayoutX(),radar.getLayoutY() , radar.getRadius());
        target.setLayoutX(0);
        target.setLayoutY(0);
        target1.setLayoutX(0);
        target1.setLayoutY(0);
        hit.setLayoutX(0);
        hit.setLayoutY(0);
           
        go.setOnAction(e ->{ run();
        
        ap.getChildren().removeAll(path3,shape2);
            target.setVisible(true);
            target1.setVisible(true);
            time = System.nanoTime();
            time2= System.currentTimeMillis();
            
            xx = Double.parseDouble(x.getText())+radar.getLayoutX();
            yy = radar.getLayoutY()-Double.parseDouble(y.getText());
            zz = Double.parseDouble(z.getText());
                    
            target1.setCenterX(xx);
            target1.setCenterY(yy);
            target.setCenterX(xx);
            target.setCenterY(yy);
                      
            pc.setPoints(xx,yy,zz,time);
            pc.setCurve(xx, yy);
            path1 = pc.getCurve();
            
            kvx = new KeyValue(target.centerXProperty(),pc.X_value,InterpolatorX);
            kvy = new KeyValue(target.centerYProperty(),pc.Y_value,InterpolatorY);            
            kf = new KeyFrame(Duration.millis(pc.getDuration()*0.000001),kvx,kvy);
            
            tml.stop();
            tml.getKeyFrames().setAll(kf);
            tml.play();
            
            ptc.getCurve(xx,yy,time2);  
            ptc.setCurve();
            path3 = ptc.returnCurve();
            shape2 = Shape.subtract(path1,shape);
            ap.getChildren().addAll(shape2,path3);
         
            if(pc.XZ_value!=-1 && pc.YZ_value!=-1){
                hit.setVisible(true);
                hit.setCenterX(pc.XZ_value); 
                hit.setCenterY(pc.YZ_value);
            }
            else{
                hit.setVisible(false);
            }  
            
            
        });
       
      
     
        
        
        
    }    

    

}
