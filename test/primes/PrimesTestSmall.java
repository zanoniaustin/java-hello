
package primes;


import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class PrimesTestSmall {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { 0, false }, { 1, false }, { 2, true }, { 3, true }, { 4, false } 
           });
    }
    
    Primes primes = new Primes();
    int x;
    boolean result;
    PrimesTestSmall(int _x, boolean _result){
        x = _x;
        result = _result;
    }
    
    @Test
    void testIsPrime(){
        assertEquals(primes.isPrime(x), result);
    }
}
