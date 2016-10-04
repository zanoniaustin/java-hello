package helloworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static kiss.API.*;


public class HelloWorld {

    void untestClock() {
        Clock clock = test(new Clock());
    }
    
    void untestTimeZoneClock() {
        TimezoneClock tzClock = test(new TimezoneClock());
    }
       void untestBoxing() {
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

    private static class Iterator<T> {

        public Iterator() {
        }
    }
       
   class Reverse implements Comparator<Integer>{
       @Override
        public int compare(Integer a, Integer b) {
        return -a.compareTo(b);
    }
   }
       
   void untestArrayListInt() {
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
   
    void testCollection(){
       Collection<Integer> c = new LinkedList<Integer>();
       c.add(3);
       c.add(1);
       c.add(7);
       c.add(10);
       assert c.contains(3) == true;
       
       c.remove(3);
       
       assert c.contains(3) == false;
       
       try (Close out = outExpect(1, EOL, 7, EOL, 10, EOL)){
            java.util.Iterator<Integer> i = c.iterator();
            while(i.hasNext()){
              Integer value = i.next();
             println(value);
             }
        
        }
       try (Close out = outExpect(1, EOL, 7, EOL, 10, EOL)){
           for (Integer value : c){
               println(value);
           }
       }
   }
   
   
   void testSet(){
       Set<String> pets = new HashSet<String>();
       pets.add("fluffy");
       pets.add("pookie");
       pets.add("pupper");
       pets.add("doge");
       pets.add("pepe");
       
       try(Close out = outExpect("pupper",EOL,"doge",EOL,"pookie",EOL,"fluffy",EOL,"pepe",EOL)){
            for(String pet : pets)
                println(pet);
       }
   }
   
   void testTreeSet(){
       Set<String> pets = new TreeSet<String>();
       pets.add("fluffy");
       pets.add("pookie");
       pets.add("pupper");
       pets.add("doge");
       pets.add("pepe");
       
       try(Close out = outExpect("doge",EOL,"fluffy",EOL,"pepe",EOL,"pookie",EOL,"pupper",EOL)){
            for(String pet : pets)
                println(pet);
       }
   }
   
   void testMap(){
       Map<String, Integer> petAges = new HashMap<String, Integer>();
       
       petAges.put("fluffy", 7);
       petAges.put("pookie", 2);
       petAges.put("pupper", 100);
       petAges.put("doge", 3);
       petAges.put("pepe", 83);
       
   }
  
   
   
}
