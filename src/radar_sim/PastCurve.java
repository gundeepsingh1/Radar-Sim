
package radar_sim;

import java.util.ArrayList;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class PastCurve {
    ArrayList<ArrayList<Double>> ix = new ArrayList<ArrayList<Double>>();
    ArrayList<ArrayList<Double>> iy = new ArrayList<ArrayList<Double>>();
    double xx , yy;
    double del;
    ArrayList<Long>time = new ArrayList<Long>();
    Path path = new Path();
    
    int c =0;
    private double radar_x;
    private double radar_y;
    private double radar_radius;
    
    
   void setRadar(double x ,double y , double r){
        radar_x =x;
        radar_y =y;
        radar_radius=r;
    }
   
    Path returnCurve(){        
        return path;
    }
    
    void getCurve(double x , double y , long t){
       
       xx = x;
       yy = y;
       ArrayList<Double>jx = new ArrayList<Double>(); 
       ArrayList<Double>jy = new ArrayList<Double>();
       c=c+1;      
       time.add(t);
       
       if(c==1){
           jx.add(xx);
           jy.add(yy);
           ix.add(jx);
           iy.add(jy);
           
       }
       
       else{
       
       ix.get(0).add(xx);
       iy.get(0).add(yy);
       Double conx[] = new Double[c-1];
       Double cony[] = new Double[c-1];
       for(int i=0; i<c-1 ; i++){
       conx[i] = (ix.get(i).get(c-1-i) - ix.get(i).get(c-2-i))/(time.get(c-1)-time.get(c-2-i));
       cony[i] = (iy.get(i).get(c-1-i) - iy.get(i).get(c-2-i))/(time.get(c-1)-time.get(c-2-i));
       
       if(i==c-2){
       jx.add((double)conx[i]);
       ix.add((ArrayList<Double>)jx);
       jy.add((double)cony[i]);
       iy.add((ArrayList<Double>)jy);
       }
       
       else{
       ix.get(i+1).add((double)conx[i]);
       iy.get(i+1).add((double)cony[i]);
       } 
       }
       
       }
       
      
     
    }
        double Py(long t){
        double px = 0;double rx=1;
        for(int i=0 ; i<c ; i++){rx=1;
            for(int j=0 ; j<i ; j++){
               rx = rx*(t-time.get(j)) ;
            }
        px = px + iy.get(i).get(0)*rx;         
        }
        return px;
    }
    
    double Px(long t){
        double px = 0;
        double rx=1;
              
        for(int i=0 ; i<c ; i++){rx=1;
            for(int j=0 ; j<i ; j++){
               rx = rx*(t-time.get(j)) ;
            }
        px = px + ix.get(i).get(0)*rx;         
        }
        return px;
        
    }
    
     void setCurve(){ 
         if(c==1){
                   path.getElements().add(new MoveTo(xx,yy));
                 }
         else{
       del = (time.get(1)-time.get(0))/100;
       double xa , ya;
       long t=time.get(0);
       double xx = Px(time.get(time.size()-1));
       double yy = Py(time.get(time.size()-1));
       
       xa = Px(t);
       ya = Py(t);
       Path path2 = new Path();
       path2.getElements().add(new MoveTo(xa,ya));
       double distance = Math.sqrt((xa-xx)*(xa-xx) + (ya-yy)*(ya-yy));
       double distance2 = Math.sqrt((xa-radar_x)*(xa-radar_x) + (ya-radar_y)*(ya-radar_y));
       while(t<time.get(time.size()-1) && (distance2 < radar_radius)){   
        
        xa = Px(t);
        ya = Py(t);
        
        t += del;
        distance2 = Math.sqrt((xa-radar_x)*(xa-radar_x) + (ya-radar_y)*(ya-radar_y));
        path2.getElements().add(new LineTo(xa,ya));
         
   } 
      path = path2;  
         }
     }
    
    
    
}
