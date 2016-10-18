/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primes;

import static kiss.API.*;
/**
 *
 * @author austi
 */
public class Primes {
 
    boolean isPrime(int x){
        for(int factor = 2; factor <= sqrt(x); factor++){
            if (x % factor == 0) return false;
           return true; 
        }
        
        return true;
    }
}
