/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import static kiss.API.*;



        
public class App {
    void run(){
            //using lambda to do very small and simple thread 
        new Thread(()->{ pause(2); println("Hi"); }).start(); 
        
        // whole class that extends thread
        ConsoleClock clock = new ConsoleClock();
        clock.start();
        pause(4.0);
        clock.close();
        println("done.");
    }
    
}
