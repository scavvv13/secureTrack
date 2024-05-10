/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BC0_POS;

import java.awt.Color;

/**
 *
 * @author acer
 */
public class Gsoft_pos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Progress p = new Progress();
        p.setVisible(true);
        
        
        try{
            for(int i = 0; i <= 100; i++){
                Thread.sleep(50);
                p.percent.setText(Integer.toString(i)+"%");
                
                Login log = new Login();
                if(i == 70){
                   p.system_load.setText("System ready. . .");
                }else if(i == 100){
                    log.show();
                    p.setVisible(false);  
                   
                }
            }
               }catch(Exception x){
             }
    }
    
}
