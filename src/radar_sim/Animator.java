/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radar_sim;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author gunde
 */
public class Animator  implements Runnable{

    @Override
    public void run() {
         Path path = new Path();
         path.getElements().add(new MoveTo(100,100));
         path.getElements().add(new LineTo(120,120));
         
    }
    
}
