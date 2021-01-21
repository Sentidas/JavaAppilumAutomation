import org.junit.Assert;
import org.junit.Test;
import sun.applet.Main;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassNumber() {
        int actual = mainClass.getClassNumber();
        int  expected = 45;

        Assert.assertTrue("возвращаемое число меньше " + expected, actual > expected);


    }





}
