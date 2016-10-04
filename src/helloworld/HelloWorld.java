package helloworld;

import java.util.ArrayList;
import java.util.Comparator;
import static kiss.API.*;


public class HelloWorld {

    void testClock() {
        Clock clock = test(new Clock());
    }
    
    void testTimeZoneClock() {
        TimezoneClock tzClock = test(new TimezoneClock());
    }
       void testBoxing() {
        Integer x = new Integer(3);
        Integer y = x;
        ++x;
        //x = new Integer(x.intValue() +1);
        assert x.intValue() == 4;
        assert y.intValue() == 3;
        assert y != new Integer(3);
        assert y.equals(new Integer(3));
        assert y.compareTo(new Integer(3)) == 0;
    }
       
   class Reverse implements Comparator<Integer>{
       @Override
        public int compare(Integer a, Integer b) {
        return -a.compareTo(b);
    }
   }
       
   void testArrayListInt() {
       ArrayList<Integer> ages = new ArrayList<Integer>();
       ages.add(3);
       ages.add(17);
       ages.add(100);
       
       for(int age : ages) {
           println(age);
       }
       
       ages.sort(new IntegerReverse()); // class
       ages.sort(new Reverse());// inner class
       ages.sort(new Comparator<Integer>(){ // anonymous inner class
           @Override
        public int compare(Integer a, Integer b) {
        return -a.compareTo(b);
        }
       }); 
       ages.sort((a,b) -> -a.compareTo(b)); // lambda(anonymous function)
       
       for (int age :ages){
           println(age);
       }
   }
}
