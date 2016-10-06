package helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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

    class Reverse implements Comparator<Integer> {

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

        for (int age : ages) {
            println(age);
        }

        ages.sort(new IntegerReverse()); // class
        ages.sort(new Reverse());// inner class
        ages.sort(new Comparator<Integer>() { // anonymous inner class
            @Override
            public int compare(Integer a, Integer b) {
                return -a.compareTo(b);
            }
        });
        ages.sort((a, b) -> -a.compareTo(b)); // lambda(anonymous function)

        for (int age : ages) {
            println(age);
        }
    }

    void testCollection() {
        Collection<Integer> c = new LinkedList<Integer>();
        c.add(3);
        c.add(1);
        c.add(7);
        c.add(10);
        assert c.contains(3) == true;

        c.remove(3);

        assert c.contains(3) == false;

        try (Close out = outExpect(1, EOL, 7, EOL, 10, EOL)) {
            java.util.Iterator<Integer> i = c.iterator();
            while (i.hasNext()) {
                Integer value = i.next();
                println(value);
            }

        }
        try (Close out = outExpect(1, EOL, 7, EOL, 10, EOL)) {
            for (Integer value : c) {
                println(value);
            }
        }
    }

    void testHashSet() {
        Set<String> pets = new HashSet<String>();
        pets.add("fluffy");
        pets.add("pookie");
        pets.add("pupper");
        pets.add("doge");
        pets.add("pepe");

        try (Close out = outExpect("pupper", EOL, "doge", EOL, "pookie", EOL, "fluffy", EOL, "pepe", EOL)) {
            for (String pet : pets) {
                println(pet);
            }
        }
    }

    void testTreeSet() {
        Set<String> pets = new TreeSet<String>();
        pets.add("fluffy");
        pets.add("pookie");
        pets.add("pupper");
        pets.add("doge");
        pets.add("pepe");

        try (Close out = outExpect("doge", EOL, "fluffy", EOL, "pepe", EOL, "pookie", EOL, "pupper", EOL)) {
            for (String pet : pets) {
                println(pet);
            }
        }
    }

    void testHashMap() {
        Map<String, Integer> petAges = new HashMap<String, Integer>();

        petAges.put("fluffy", 7);
        petAges.put("pookie", 2);
        petAges.put("pupper", 100);
        petAges.put("doge", 3);
        petAges.put("pepe", 83);

        try (Close out = outExpect("petAges[pupper]=100", EOL,
                "petAges[doge]=3", EOL,
                "petAges[pookie]=2", EOL,
                "petAges[fluffy]=7", EOL,
                "petAges[pepe]=83", EOL)) {
            for (String key : petAges.keySet()) {
                println("petAges[" + key + "]=" + petAges.get(key));
            }
        }

        for (String key : petAges.keySet()) {
            petAges.put(key, petAges.get(key) + 1);
        }
        try (Close out = outExpect("petAges[pupper]=101", EOL,
                "petAges[doge]=4", EOL,
                "petAges[pookie]=3", EOL,
                "petAges[fluffy]=8", EOL,
                "petAges[pepe]=84", EOL)) {
            for (String key : petAges.keySet()) {
                println("petAges[" + key + "]=" + petAges.get(key));
            }
        }
    }

    void testTreeMap() {
        Map<String, Integer> petAges = new TreeMap<String, Integer>();

        petAges.put("fluffy", 7);
        petAges.put("pookie", 2);
        petAges.put("pupper", 100);
        petAges.put("doge", 3);
        petAges.put("pepe", 83);

        try (Close out = outExpect("petAges[doge]=3", EOL,
                "petAges[fluffy]=7", EOL,
                "petAges[pepe]=83", EOL,
                "petAges[pookie]=2", EOL,
                "petAges[pupper]=100", EOL)) {
            for (String key : petAges.keySet()) {
                println("petAges[" + key + "]=" + petAges.get(key));
            }
        }

        for (String key : petAges.keySet()) {
            petAges.put(key, petAges.get(key) + 1);
        }
        try (Close out = outExpect("petAges[doge]=4", EOL,
                "petAges[fluffy]=8", EOL,
                "petAges[pepe]=84", EOL,
                "petAges[pookie]=3", EOL,
                "petAges[pupper]=101", EOL)) {
            for (String key : petAges.keySet()) {
                println("petAges[" + key + "]=" + petAges.get(key));
            }
        }
    }

    void doStuff() throws IOException {
        throw new IOException("bad stuff happened");
    } // throws means an exception may happen
    void doOtherStuff(){}

    void testException() {
        try {
            doStuff();

        } catch (IOException ex) {
            doOtherStuff();
        }
    }
    
    void testException2() throws IOException{
        doStuff();
    }

    void testLinkedList() {
        List<String> names = new LinkedList<String>();

        boolean pass = false;
        try {
            println(names.get(0));
        } catch (IndexOutOfBoundsException ex) {
            pass = true;
        }
        assert pass == true;

        names.add("doge");
        names.add("fluffy");
        names.add("pepe");
        assert names.get(names.size() - 1).equals("pepe");

        String[] a = new String[3];
        a[0] = "doge";
        assert (a[0].equals("doge"));
        assert (a[1] == null);
        assert (a[2] == null);
    }

}
