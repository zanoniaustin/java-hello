package helloworld;

import static kiss.API.*;


enum Timezone{
    MST,
    MDT,
    UTC
};

public class TimezoneClock extends Clock {

    
    TimezoneClock(Timezone tz){
        setTimezone(tz);
    }
    
    TimezoneClock(){
        this.setTimezone(Timezone.UTC);
    }
    
    void setTimezone(Timezone tz){
        super.setHours(time()/3600);
        super.start();
        switch(tz){
            case MST : timezoneShift = -7; break;
            case MDT : timezoneShift = -6; break;
            case UTC : timezoneShift = 0; break;
            default : throw new UnsupportedOperationException("unknown timezone");
        }
    }
    
    double mod(double a, double b){
        double u = a/b;
        return b * (u-Math.floor(u));
    }
   
    double timezoneShift = 0.0;
    @Override
    double getHours() {
        return mod(super.getHours()+timezoneShift, 12.0);
    }
    
    @Override
    void setHours(double _hours){
        super.setHours(mod(_hours - timezoneShift, 12.0));
    }
    
    void untestGetTime(){
        Clock clock = new TimezoneClock();
        double hours = clock.getHours();
        double minutes = clock.getMinutes();
        double seconds = clock.getSeconds(); 
    }
    
    void untestGetCorrectTime() {
        Clock clock = new TimezoneClock();
        clock.setHours(6.50);
        assert clock.getHours() == 6.50;
        assert clock.getMinutes() == 30.0;
        assert clock.getSeconds() == 0.0;
    }
    
    void untestGetFlowingTime() {
        Clock clock = new TimezoneClock();
        clock.setHours(1.00);
        clock.start();
        pause(1.0);
        double now = clock.getHours();
        double shouldBe = 1.00 + 1.0/3500.0;
        assert abs(now - shouldBe) < (0.1/3600.0);
    }
    
    void untestMST(){
        TimezoneClock clock = new TimezoneClock(Timezone.MDT);
        println("tzShift: " + clock.timezoneShift);
        println("Time: " + asInt(clock.getHours()) + ":" + asInt(clock.getMinutes()));
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof TimezoneClock){
            return timezoneShift == ((TimezoneClock)object).timezoneShift 
                    && super.equals(object);
        }else
            return false;
    }
}
