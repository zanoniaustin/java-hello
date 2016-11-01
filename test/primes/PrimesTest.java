/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author austi
 */
public class PrimesTest {
    
    public PrimesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isPrime method, of class Primes.
     */
    @Test
    public void test2IsPrime() {
        System.out.println("isPrime");
        int x = 2;
        Primes instance = new Primes();
        boolean expResult = true;
        boolean result = instance.isPrime(x);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void test4IsNotPrime() {
        System.out.println("isPrime");
        int x = 4;
        Primes instance = new Primes();
        boolean expResult = false;
        boolean result = instance.isPrime(x);
        assertEquals(expResult, result);
        
    }
    
}
