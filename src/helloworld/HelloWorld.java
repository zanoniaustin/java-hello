package helloworld;

import static kiss.API.*;

public class HelloWorld {

    void untestRun(){
        outExpect("Hello!");
        run();
        outClose();
    }
    
    void run(){
        println("Hello!");
        
    }   
}
