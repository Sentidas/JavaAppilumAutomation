import org.junit.Assert;
import org.junit.Test;
import sun.applet.Main;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int actual = mainClass.getLocalNumber();
        int  expected = 14;

        Assert.assertTrue("возвращается не число " + expected, actual == expected);


    }





}
