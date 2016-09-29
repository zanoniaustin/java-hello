/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.util.ArrayList;
import static kiss.API.*;

/**
 *
 * @author austi
 */
public class Clock implements Comparable<Clock>{
    // instance variables (1 for each object/instance)
    private double hours = 0;
    private boolean started = false;
    private double t0;
    void start() {
        started = true;
        t0 = time();
    }
    
    @Override
    public int compareTo(Clock clock){
        double delta = getHours()-clock.getHours();
        if (delta < 0) return -1;
        if (delta == 0) return 0;
        return 1;
    }
    
    // overload for equals, must have different types
    public boolean equals(Clock clock){
      return compareTo(clock) == 0;  
    }
    
    @Override
    public boolean equals(Object object){
        return(object instanceof Clock && equals((Clock) object));
    }
    
    
    void setHours(double _hours) {
        hours = _hours;
    }
    
    Clock(){ setHours(0);}
    Clock(double _hours) {setHours(_hours); }
    Clock(double _hours, double _minutes) {setHours(_hours + _minutes/60.0); }
    
    double getHours() {
        return started ? (hours + (time() - t0) / 3600.0) : hours;
    }
    
    double getMinutes() {
        double _hours = getHours();
        return (_hours - Math.floor(_hours)) * 60.0;
    }
    
    double getSeconds() {
        double _minutes = getMinutes();
        return (_minutes - Math.floor(_minutes)) * 60.0;
    }
    
    void testGetTime(){
        Clock clock = new Clock();
        double hours = clock.getHours();
        double minutes = clock.getMinutes();
        double seconds = clock.getSeconds(); 
    }
    
    void testGetCorrectTime() {
        Clock clock = new Clock();
        clock.setHours(6.50);
        assert clock.getHours() == 6.50;
        assert clock.getMinutes() == 30.0;
        assert clock.getSeconds() == 0.0;
    }
    
    void testGetFlowingTime() {
        Clock clock = new Clock();
        clock.setHours(1.00);
        clock.start();
        pause(1.0);
        double now = clock.getHours();
        double shouldBe = 1.00 + 1.0/3500.0;
        assert abs(now - shouldBe) < (0.1/3600.0);
    }
    
    void testEquals(){
        Clock clock1 = new Clock();
        Clock clock2 = clock1;
        Clock clock3 = new Clock();
        
        clock1.setHours(1.00);
        clock3.setHours(1.00);
        assert clock2.getHours() == 1.00;
        assert clock1.equals(clock2) == true;
        assert(clock1 == clock2) == true;
        assert clock1.equals(clock3) == true;
        assert (clock1 == clock3) == false;
    }
    
    @Override
    public String toString(){
        return String.format("%02d:%02d", (int) getHours(), (int) getMinutes());
    }
    
    void testSortClocks(){
        ArrayList<Clock> clocks = new ArrayList<Clock>();
        clocks.add(new Clock(1));
        clocks.add(new Clock(1, 30));
        clocks.add(new Clock(2));
        clocks.add(new Clock(1, 15));
        clocks.sort(null);
        for (Clock clock : clocks) {
            println(clock);
        }
    }
}
