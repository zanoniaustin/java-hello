package helloworld;

import static kiss.API.*;

public class HelloWorld {

    void testClock() {
        Clock clock = test(new Clock());
    }
    
    void testTimeZoneClock() {
        TimezoneClock tzClock = test(new TimezoneClock());
    }
}
