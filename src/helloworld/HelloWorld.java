package helloworld;

import static kiss.API.*;

public class HelloWorld {

    void testRun() {
        try (Close out = outExpect("Hello World!")) {
            run();
        }
    }

    void run() {
        println("Hello World!");
    }

    void untestBooleans() {
        assert true != false;
        assert true == true;
        assert false == false;
        try (Close out = outExpect("true")) {
            println(true);
        }
        try (Close out = outExpect("false")) {
            println(false);
        }

        // not
        assert !true == false;
        assert !false == true;

        // and
        assert (true && true) == true;
        assert (true && false) == false;
        assert (false && true) == false;
        assert (false && false) == false;

        assert (false && (random(0, 1)) == 1) == false;
        assert (false && (random(0, 1)) == 1) == false;
        assert (false && (random(0, 1)) == 1) == false;
        assert (false && (random(0, 1)) == 1) == false;

        assert (false && (1 / 0 == 3)) == false;

        // or
        assert (true || true) == true;
        assert (true || false) == true;
        assert (false || true) == true;
        assert (false || false) == false;

        assert (true || (random(0, 1)) == 1) == true;
        assert (true || (random(0, 1)) == 1) == true;
        assert (true || (random(0, 1)) == 1) == true;
        assert (true || (random(0, 1)) == 1) == true;

        assert (true || (1 / 0 == 3)) == true;
    }

    void sideEffect(int y) {
        println(y);
    }

    void untestInts() {
        assert Integer.MAX_VALUE == Math.pow(2, 31) - 1;
        //println(Integer.MAX_VALUE);
        assert Integer.MAX_VALUE == 2_147_483_647;
        assert Integer.MAX_VALUE == 0b0111_1111_1111_1111_1111_1111_1111_1111;

        assert Integer.MIN_VALUE == -Math.pow(2, 31);
        //println(Integer.MIN_VALUE);
        assert Integer.MIN_VALUE == -2_147_483_648;
        assert Integer.MIN_VALUE == 0b1000_0000_0000_0000_0000_0000_0000_0000;

        assert Integer.MAX_VALUE + 1 == Integer.MIN_VALUE;
        assert Integer.MIN_VALUE - 1 == Integer.MAX_VALUE;

        int x = 3;
        int y = 0xff_ff;
        int z = 0b10_01;

        assert x == 3;
        assert y == 65_535;
        assert z == 9;

        assert Integer.toString(x, 10).equals("3");
        assert Integer.toString(y, 16).equals("ffff");
        assert Integer.toString(z, 2).equals("1001");

        assert 17 / 5 == 3;
        assert ((double) 17) / ((double) 5) == 3.4;
        assert ((double) (17 / 5)) == 3.0;

        assert 17 % 5 == 2;
        assert -7 % 5 == -2;
        assert -7 % -5 == -2;

        int a = 1;
        ++a;
        try (Close out = outExpect("2")) {
            println(a);
        }

        try (Close out = outExpect("3")) {
            println(++a);
        }
        try (Close out = outExpect("3")) {
            println(a);
        }

        try (Close out = outExpect("3")) {
            println(a++);
        }
        try (Close out = outExpect("4")) {
            println(a);
        }

        //Bitwise not
        assert ~0b0000_0000_0000_0000_0000_0000_1111_0000
                == 0b1111_1111_1111_1111_1111_1111_0000_1111;
        assert ~0b1111_0000
                == 0b1111_1111_1111_1111_1111_1111_0000_1111;

        // Bitwise and
        assert (0b1111_0000 & 0b1010_1010) == 0b1010_0000;

        // Bitwise or
        assert (0b1111_0000 | 0b1010_1010) == 0b1111_1010;

        //Bit-shift operator, moving the decimal point
        assert (0b1010_1111_0000 >> 4) == 0b1010_1111;
        assert (0b1010_1111 << 4) == 0b1010_1111_0000;

        //seed(); this allows random num generator to give different numbers
        int b = random(0, 1_000_000);
        println(b);
        assert ((b >> 1) == b / 2);
        assert ((-b >> 1) == -b / 2);
        assert ((b << 1) == 2 * b);
        assert ((-b << 1) == 2 * (-b));

        int c = random(-1_000_000, 1_000_000);
        assert ((~c) + 1) == -c;

        //When using negatives you shift all the numbers down then add a 1
        //to the beggining because you have to keep the sign the same.
        //right shifts shift the sign bit in (highest order bit)
        assert -2 == 0b1111_1111_1111_1111_1111_1111_1111_1110;
        assert (-2 >> 1) == 0b1111_1111_1111_1111_1111_1111_1111_1111;
    }

    void testConvert() {
        byte x = -1;
        assert x == (byte) 0b1111_1111;
        int y = x;
        assert y == -1;
        assert 0b1111_1111_1111_1111_1111_1111_1111_1111
                == (int) (byte) 0b1111_1111;
    }

    void untestLoops() {

        try (Close out = outExpect(0, EOL, 1, EOL, 3, EOL)) {
            int n = 4;
            int i = 0;
            while (i < n) {
                if (i == 2) {
                    i++;
                    continue;
                } //continue bumps back up to top of loop
                println(i);
                i++;
            }
        }
        try (Close out = outExpect(0, EOL, 1, EOL, 2, EOL, 3, EOL)) {
            int n = 4;
            for (int i = 0; i < n; i++) {
                println(i);
            }
        }
        try (Close out = outExpect(0, EOL, 1, EOL, 3, EOL)) {
            int n = 4;
            for (int i = 0; i < n; i++) {
                if (i == 2) {
                    continue;
                }
                println(i);
            }
        }

        try (Close out = outExpect(0, EOL, 1, EOL)) {
            int n = 4;
            int i = 0;
            while (i < n) {
                if (i == 2) {
                    i++;
                    break;
                } //break exits the loop
                println(i);
                i++;
            }
        }
        try (Close out = outExpect(0, EOL, 1, EOL)) {
            int n = 4;
            for (int i = 0; i < n; i++) {
                if (i == 2) {
                    break;
                }
                println(i);
            }
        }

        String[] words = new String[]{"this", "that", "other"};
        assert words[0].equals("this");
        assert words[1].equals("that");
        assert words[2].equals("other");
        assert words.length == 3;

        String[] nouns = new String[2];
        assert nouns[0] == null;
        assert nouns[1] == null;
        assert nouns.length == 2;

        try (Close out = outExpect("this", EOL, "that", EOL, "other", EOL)) {
            for (int i = 0; i < words.length; i++) {
                println(words[i]);
            }
        }

        try (Close out = outExpect("this", EOL, "that", EOL, "other", EOL)) {
            for (String word : words) {
                println(word);
            }
        }
    }

    int instanceValue = 0;

    void methodExample() {
        int localValue = 0;
        ++instanceValue;
        ++localValue;
        println("I", instanceValue, "L", localValue);
    }

    long factorial(int n) {
        if (n > 1)
            return n * factorial(n-1);
        else
            return 1;   
    }
    
    void testFunction() {
        try (Close out = outExpect("I", 1, "L", 1,EOL,
                                   "I", 2, "L", 1,EOL,
                                   "I", 3, "L", 1,EOL)) {
            methodExample();
            methodExample();
            methodExample();
        }
        
        assert factorial(1) == 1;
        assert factorial(5) == 120;
    }
    
    void testString() {
        String hi = "hello";
        assert hi.length() == 5;
        assert hi.substring(1,3).equals("el");
        
        hi = hi + " world";
        
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append(" world");
        sb.append(" #");
        sb.append(13);
        
        String hw = sb.toString();
        
        println(hw);
    }
    
    
}
