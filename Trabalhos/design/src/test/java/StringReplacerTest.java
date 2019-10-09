import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringReplacerTest {
    @Test
    public void stringReplacer() {
        StringDrink drink = new StringDrink("ABCDABCD");
        StringReplacer sr = new StringReplacer(drink, 'A', 'X');
        sr.execute();
        assertEquals("XBCDXBCD", drink.getText());
    }

}
