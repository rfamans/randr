import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestPolynoom
{
    @Test
    public void toStringTest()
    {
        assertEquals("ToString test", "0.0" ,new Polynoom(0, 0).toString());
        assertEquals("ToString test", "2.0 x^8 + 3.0 x^3" ,new Polynoom(2, 8, 3, 3).toString());
        assertEquals("ToString test", "8.0" ,new Polynoom(8, 0, 0, 0).toString());
        assertEquals("ToString test", "x^3 + 3.0 x" ,new Polynoom(1, 3, 3, 1).toString());
        assertEquals("ToString test", "0.0 + x" ,new Polynoom(0, 5, 1, 1).toString());
        assertEquals("ToString test", "5.0 x^5 + 2.0" ,new Polynoom(2, 0, 5, 5).toString());
        assertEquals("ToString test", "5.0 x^3" ,new Polynoom(2, 3, 3, 3).toString());

    }

    @Test
    public void telopTest()
    {
        assertEquals("telop test", "5.0 x^8 + 3.0 x^4 + 3.0 x^3", new Polynoom(2, 8, 3, 3).telop
                                                                (new Polynoom(3, 8, 3, 4)).toString());

    }

    @Test
    public void differentieerTest()
    {
        assertEquals("differienteer test", "12.0 x^2 + 4.0 x", new Polynoom(2, 2, 4, 3).differentieer().toString());
    }

    @Test
    public void equalsTest()
    {
        assertEquals("equals test", true, new Polynoom(2, 2, 4, 3).equals(new Polynoom(2, 2, 4, 3)));
    }

    @Test
    public void primitiveerTest()
    {
        Polynoom p4 = new Polynoom(6, 2, 4, 1);
        p4 = p4.integreer();
        assertEquals("differentieer test", "2.0 x^3 + 2.0 x^2", p4.toString());
    }

    @Test
    public void AftrekTest()
    {
        assertEquals("Aftrek test", "-3.0 x^8 + 5.0 x^4 + x^3", new Polynoom(5, 4, 3, 3).trekaf
                                                                  (new Polynoom(3, 8, 2, 3)).toString());
    }

    @Test
    public void vermenigvuldigTest()
    {
        assertEquals("vermenigvuldig test", "6.0 x^12 + 4.0 x^7", new Polynoom(2, 4).vermenigvuldig
                                                                 (new Polynoom(3, 8, 2, 3)).toString());
    }

    public static void main(String[] args)
    {

        System.out.println("Opgave 5 Tester\n---------------");

        Result result = JUnitCore.runClasses(TestPolynoom.class);

        if(result.wasSuccessful())
        {
            System.out.println("Hoera, alle tests zijn geslaagd!");
        }
        else
        {
            if(result.getFailureCount() > 1)
                System.out.println("Er zijn " + result.getFailureCount() + " fouten opgetreden:");
            else
                System.out.println("Er is 1 fout opgetreden:");

            for(Failure failure : result.getFailures())
            {
                System.out.println(failure.toString());
            }
        }

        System.out.println("\nAantal tests uitgevoerd: " + result.getRunCount());
        System.out.println("Het uitvoeren van de tests duurde " + (result.getRunTime() / 1000.0) + " seconden.");
    }

}
