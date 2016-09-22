package helloworld;

import static kiss.API.abs;
import static kiss.API.pause;

public class TimezoneClock extends Clock {

    double timezoneShift = 0.0;
    @Override
    double getHours() {
        return 0;
    }
    
    void testGetTime(){
        Clock clock = new TimezoneClock();
        double hours = clock.getHours();
        double minutes = clock.getMinutes();
        double seconds = clock.getSeconds(); 
    }
    
    void testGetCorrectTime() {
        Clock clock = new TimezoneClock();
        clock.setHours(6.50);
        assert clock.getHours() == 6.50;
        assert clock.getMinutes() == 30.0;
        assert clock.getSeconds() == 0.0;
    }
    
    void testGetFlowingTime() {
        Clock clock = new TimezoneClock();
        clock.setHours(1.00);
        clock.start();
        pause(1.0);
        double now = clock.getHours();
        double shouldBe = 1.00 + 1.0/3500.0;
        assert abs(now - shouldBe) < (0.1/3600.0);
    }
}
