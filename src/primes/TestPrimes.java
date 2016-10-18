/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primes;

import kiss.API;
import static kiss.API.EOL;
import static kiss.API.inOpen;
import static kiss.API.inProvide;
import static kiss.API.outExpect;
import static kiss.API.outOpen;
import static kiss.API.outVerify;
import static kiss.API.println;
import static kiss.API.readInteger;
import static kiss.API.time;

/**
 *
 * @author austi
 */
public class TestPrimes {
    void testIO(){
        try(API.Close out = outOpen("sum.dat")){
            println("x+y= 35");
        }
        try(API.Close vfy = outVerify("sum.dat")){
            int x = 11;
            int y = 24;
            println("x+y= " + (x+y));
        }
    }
    
    void testSum(){
        try( API.Close in = inProvide(13, EOL, 22, EOL)){
            try ( API.Close out = outExpect("x+y= 35")){
                printSum();
            }
        }
    }
    
    void testFunctionalSum(){
        assert sum(3,5) == 8;
    }
    
    void printSum(){
        int x = readInteger();
        int y = readInteger();
        println("x+y= " + sum(x,y));
    }
    
    int sum(int x, int y){return x+y;}
    
    void testSmallPrimes(){
        Primes primes = new Primes();
        assert primes.isPrime(2) == true;
        assert primes.isPrime(3) == true;
        assert primes.isPrime(5) == true;   
    }
    
    void testSmallComposites(){
        Primes primes = new Primes();
        assert primes.isPrime(4) == false;
        assert primes.isPrime(6) == false;
    }
    
    void testBigPrimes() {
        Primes primes = new Primes();
        double t0 = time();
        assert primes.isPrime(1_000_003) == true;
        double smallTime = time() - t0;
        
        double t1 = time();
        assert primes.isPrime(1_000_000_007) == true;
        double bigTime = time() - t1;
        
        //assert (bigTime < (100*smallTime));
    }
}
