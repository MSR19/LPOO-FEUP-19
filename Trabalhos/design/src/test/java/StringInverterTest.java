import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringInverterTest {
    @Test
    public void stringInverter() {
        StringDrink drink = new StringDrink("ABCD");
        StringInverter si = new StringInverter(drink);
        si.execute();
        assertEquals("DCBA", drink.getText());
    }
}
