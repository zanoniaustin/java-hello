package helloworld;

import static kiss.API.*;

public class HelloWorld {

    void testRun(){
        outExpect("Hello!");
        run();
        outClose();
    }
    
    void run(){
        println("Hello!");
        
    }   
}
