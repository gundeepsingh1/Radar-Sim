
package radar_sim;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class PathCurve implements Runnable{
    int c =0;
    
    long duration;
    
    double X_value,
           Y_value,
           XZ_value,
           YZ_value;
    
    Path path2 = new Path();
    
    double x[] = {0,0,0,0};
    double vx[] = {0,0,0};
    double ax[] =  {0,0};
    double Ax=0; 
    double y[] = {0,0,0,0};
    double vy[] = {0,0,0};
    double ay[] =  {0,0};
    double Ay=0;
    double z[] = {0,0,0,0};
    double vz[] = {0,0,0};
    double az[] =  {0,0};
    double Az=0;
    
    long time[] = {0,0,0,0};
    
    double del;
    
    double radar_x,
           radar_y,
           radar_radius;
    
    void setRadar(double x ,double y , double r){
        radar_x =x;
        radar_y =y;
        radar_radius=r;
    }
   

    void setPoints(double r , double u, double f , long timex) {
            
            if(c ==0){
            x[0] = r   ; y[0] = u ; z[0] =f;
            time[0] = timex;
            duration = 0;          
            }
            
            if(c==1){
            time[1] = timex;
            x[1] = r ; y[1] = u; z[1]=f;
            vx[0] = (x[1]-x[0])/(time[1]-time[0]);  vy[0] = (y[1]-y[0])/(time[1]-time[0]); vz[0] = (z[1]-z[0])/(time[1]-time[0]);
            del = (time[1]-time[0])/100;            
            }
            if(c==2){time[2] = timex;
            x[2] = r; y[2] = u; z[2] = f;
            vx[0] = (x[1]-x[0])/(time[1]-time[0]);  vy[0] = (y[1]-y[0])/(time[1]-time[0]); vz[0] = (z[1]-z[0])/(time[1]-time[0]);
            vx[1] = (x[2]-x[1])/(time[2]-time[1]);  vy[1] = (y[2]-y[1])/(time[2]-time[1]); vz[1] = (z[2]-z[1])/(time[2]-time[1]);
            ax[0] = (vx[1]-vx[0])/(time[2]-time[0]); ay[0] = (vy[1]-vy[0])/(time[2]-time[0]); az[0]=(vz[1]-vz[0])/(time[2]-time[0]);
            del = (time[2]-time[1])/100;
            
            }
            if(c==3){time[3] = timex;
            x[3] = r   ; y[3] = u   ; z[3] =f;
            
            vx[0] = (x[1]-x[0])/(time[1]-time[0]);  vy[0] = (y[1]-y[0])/(time[1]-time[0]); vz[0] = (z[1]-z[0])/(time[1]-time[0]);
            vx[1] = (x[2]-x[1])/(time[2]-time[1]);  vy[1] = (y[2]-y[1])/(time[2]-time[1]); vz[1] = (z[2]-z[1])/(time[2]-time[1]);
            vx[2] = (x[3]-x[2])/(time[3]-time[2]);  vy[2] = (y[3]-y[2])/(time[3]-time[2]); vz[2] = (z[3]-z[2])/(time[3]-time[2]);
            
            ax[0] = (vx[1]-vx[0])/(time[2]-time[0]); ay[0] = (vy[1]-vy[0])/(time[2]-time[0]); az[0] = (vz[1]-vz[0])/(time[2]-time[0]);
            ax[1] = (vx[2]-vx[1])/(time[3]-time[1]); ay[1] = (vy[2]-vy[1])/(time[3]-time[1]); az[1] = (vz[2]-vz[1])/(time[3]-time[1]);  
            
            Ax = (ax[1] - ax[0])/(time[3]-time[0]); Ay = (ay[1] - ay[0])/(time[3]-time[0]); Az = (az[1] - az[0])/(time[3]-time[0]);
            del = (time[3]-time[2])/100;
          
            }
            if(c>3){ 
            x[0] = x[1]; y[0] = y[1]; z[0] = z[1];
            x[1] = x[2]; y[1] = y[2]; z[1] = z[2];
            x[2] = x[3]; y[2] = y[3]; z[2] = z[3];
            x[3] = r   ; y[3] = u   ; z[3] = f;
            
            time[0] = time[1]; 
            time[1] = time[2]; 
            time[2] = time[3]; 
            time[3] = timex; 
            
            vx[0] = (x[1]-x[0])/(time[1]-time[0]);  vy[0] = (y[1]-y[0])/(time[1]-time[0]); vz[0] = (z[1]-z[0])/(time[1]-time[0]);
            vx[1] = (x[2]-x[1])/(time[2]-time[1]);  vy[1] = (y[2]-y[1])/(time[2]-time[1]); vz[1] = (z[2]-z[1])/(time[2]-time[1]);
            vx[2] = (x[3]-x[2])/(time[3]-time[2]);  vy[2] = (y[3]-y[2])/(time[3]-time[2]); vz[2] = (z[3]-z[2])/(time[3]-time[2]);
            
            ax[0] = (vx[1]-vx[0])/(time[2]-time[0]); ay[0] = (vy[1]-vy[0])/(time[2]-time[0]); az[0] = (vz[1]-vz[0])/(time[2]-time[0]);
            ax[1] = (vx[2]-vx[1])/(time[3]-time[1]); ay[1] = (vy[2]-vy[1])/(time[3]-time[1]); az[1] = (vz[2]-vz[1])/(time[3]-time[1]);  
            
            Ax = (ax[1] - ax[0])/(time[3]-time[0]); Ay = (ay[1] - ay[0])/(time[3]-time[0]); Az = (az[1] - az[0])/(time[3]-time[0]);
            del = (time[3]-time[2])/100;
            
            }
           
          c++;
    }
   
    void setCurve(double a , double b){ 
       
        if(c==1){
        path2.getElements().add(new MoveTo(a,b));
        XZ_value =-1;
        YZ_value=-1;
        duration = 0;
        }
        else{  
         
     double r = a;
     double u = b;
     long t = 0 ;
     
   Path path = new Path();
     double xa=0, ya=0, za=0;
     double distance; 
      
       if(c==2) { t=time[1];
       xa = x[0]
           +vx[0]*(t-time[0]);
       ya = y[0]
           +vy[0]*(t-time[0]);
       za = z[0]
           +vz[0]*(t-time[0]);
       
       }
       if(c==3){ t=time[2];
       xa = x[0]
           +vx[0]*(t-time[0])
           +ax[0]*(t-time[0])*(t-time[1]);
       
       ya = y[0]
           +vy[0]*(t-time[0])
           +ay[0]*(t-time[0])*(t-time[1]);
       
       za = z[0]
           +vz[0]*(t-time[0])
           +az[0]*(t-time[0])*(t-time[1]);
       
       }
       if(c>3) {
           t=time[3];
       
       xa= x[0]
          +vx[0]*(t-time[0])
          +ax[0]*(t-time[0])*(t-time[1])
          +Ax*(t-time[0])*(t-time[1])*(t-time[2]);
           
       ya= y[0]
          +vy[0]*(t-time[0])
          +ay[0]*(t-time[0])*(t-time[1])
          +Ay*(t-time[0])*(t-time[1])*(t-time[2]);
       
       za= z[0]
          +vz[0]*(t-time[0])
          +az[0]*(t-time[0])*(t-time[1])
          +Az*(t-time[0])*(t-time[1])*(t-time[2]);
       }
       duration =t;
       distance = Math.sqrt((xa-radar_x)*(xa-radar_x) + (ya - radar_y)*(ya-radar_y));
       
       path.getElements().add(new MoveTo(r,u));
       X_value = xa;
       Y_value = ya;
       XZ_value = -1;
       YZ_value = -1;
       
       double xin = xa;
       double yin = ya;
       
       while(distance<radar_radius){   
         
        if(c==2) {
       xa = x[0]
           +vx[0]*(t-time[0]);
       ya = y[0]
           +vy[0]*(t-time[0]);
       za = z[0]
           +vz[0]*(t-time[0]);       
       }
       if(c==3){
       xa = x[0]
           +vx[0]*(t-time[0])
           +ax[0]*(t-time[0])*(t-time[1]);
       
       ya = y[0]
           +vy[0]*(t-time[0])
           +ay[0]*(t-time[0])*(t-time[1]);
       
       za = z[0]
           +vz[0]*(t-time[0])
           +az[0]*(t-time[0])*(t-time[1]);
       
       }
       if(c>3){
       
       xa= x[0]
          +vx[0]*(t-time[0])
          +ax[0]*(t-time[0])*(t-time[1])
          +Ax*(t-time[0])*(t-time[1])*(t-time[2]);
           
       ya= y[0]
          +vy[0]*(t-time[0])
          +ay[0]*(t-time[0])*(t-time[1])
          +Ay*(t-time[0])*(t-time[1])*(t-time[2]);
       
       za= z[0]
          +vz[0]*(t-time[0])
          +az[0]*(t-time[0])*(t-time[1])
          +Az*(t-time[0])*(t-time[1])*(t-time[2]);
       }
        if(Math.abs(X_value-xin)<Math.abs(xa- xin)){X_value =xa;}
        if(Math.abs(Y_value-yin)<Math.abs(ya- yin)){Y_value =ya;}
        if(Math.abs(za)<1){XZ_value =xa; YZ_value=ya;}
        
        distance = Math.sqrt((xa-radar_x)*(xa-radar_x) + (ya-radar_y)*(ya-radar_y));
        t +=del; 
        path.getElements().add(new LineTo(xa,ya));
       
   } 
       duration = (long) (t-del-duration);
       path2 = path;
        }
   }
   
    double X_value(double t){
    double xa = 0;
    
    if(c==2) { 
       xa = x[0]
           +vx[0]*(t*(duration)+time[1]-time[0]);
       if((X_value-x[1])==0 ) xa =0;
       else  xa = (xa-x[1])/(X_value - x[1]);
       }
       if(c==3){ 
       xa = x[0]
           +vx[0]*(t*(duration)+time[2]-time[0])
           +ax[0]*(t*(duration)+time[2]-time[0])*(t*(duration)+time[2]-time[1]);
       if((X_value-x[2])==0 ) xa =0;
       else  xa = (xa-x[2])/(X_value - x[2]);      
       }
       if(c>3) {
   
       xa= x[0]
          +vx[0]*(t*(duration)+time[3]-time[0])
          +ax[0]*(t*(duration)+time[3]-time[0])*(t*(duration)+time[3]-time[1])
          +Ax*(t*(duration)+time[3]-time[0])*(t*(duration)+time[3]-time[1])*(t*(duration)+time[3]-time[2]);
       if((X_value-x[3])==0 ) xa =0;
       else  xa = (xa-x[3])/(X_value - x[3]);
       }
       return xa;
    }
    
    double Y_value(double t){
    double ya = 0;
   
    if(c==2) { 
       ya = y[0]
           +vy[0]*(t*(duration)+time[1]-time[0]);
       if((Y_value-y[1]) == 0) ya =0;
       else ya = (ya - y[1])/(Y_value-y[1]);
       }
       if(c==3){ 
       ya = y[0]
           +vy[0]*(t*(duration)+time[2]-time[0])
           +ay[0]*(t*(duration)+time[2]-time[0])*(t*(duration)+time[2]-time[1]);
        if((Y_value-y[2]) == 0) ya =0;
       else ya = (ya - y[2])/(Y_value-y[2]); 
       }
       if(c>3) {
   
       ya= y[0]
          +vy[0]*(t*(duration)+time[3]-time[0])
          +ay[0]*(t*(duration)+time[3]-time[0])*(t*(duration)+time[3]-time[1])
          +Ay*(t*(duration)+time[3]-time[0])*(t*(duration)+time[3]-time[1])*(t*(duration)+time[3]-time[2]);
       if((Y_value-y[3]) == 0) ya =0;
       else ya = (ya - y[3])/(Y_value-y[3]);
       } 
      
       return ya;
       
    }
   
   double getDuration(){
     
     return duration;
   }
   
   Path getCurve() {
       return path2;
   }

    @Override
    public void run() {
      
    }

}
