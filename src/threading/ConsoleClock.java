/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import java.util.logging.Level;
import java.util.logging.Logger;
import static kiss.API.*;
/**
 *
 * @author austi
 */
public class ConsoleClock extends Thread{
    double startTime;
    volatile boolean running = false;
    // warning to the compiler, that there are 2 threads using same value
    
    public void start() {
        startTime=kiss.API.time();
        super.start(); // extends the definition of start from Thread
    }
    
    public void close() {
        running = false;
        try {
            join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsoleClock.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void run() {
        running = true;
        while(running){
            pause(1.0);
            println("t = " + (time()-startTime));
        }
    }
}
