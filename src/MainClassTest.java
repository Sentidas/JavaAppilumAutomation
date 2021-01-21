import org.junit.Assert;
import org.junit.Test;
import sun.applet.Main;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetClassString() {
        String actual = mainClass.getClassString ();
        String expected1 = "Hello";
        String expected2 = "hello";
        Assert.assertTrue("фраза не содержит " + expected1 + " или " + expected2,
                actual.contains(expected1) || actual.contains(expected2));



    }





}
