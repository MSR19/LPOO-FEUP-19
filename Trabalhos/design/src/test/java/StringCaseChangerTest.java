import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringCaseChangerTest {
    @Test
    public void stringCaseChanger() {
        StringDrink drink = new StringDrink("aBcD");
        StringCaseChanger cc = new StringCaseChanger(drink);
        cc.execute();
        assertEquals("AbCd", drink.getText());
    }

}
