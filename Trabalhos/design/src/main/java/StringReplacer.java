public class StringReplacer implements  StringTransformer {
    private StringDrink stringDrink;
    private char old;
    private char change;

    StringReplacer (StringDrink stringDrink, char old, char change) {
        this.stringDrink = stringDrink;
        this.old = old;
        this.change = change;
    }

    @Override
    public void execute() {
        String temp = stringDrink.getText();
        String newS = new String();
        for (int i = 0; i != temp.length(); i++) {
            if (temp.charAt(i) == old) {
                newS = newS + change;
            }
            else {
                newS = newS + temp.charAt(i);
            }
        }
        stringDrink.setText(newS);
    }

    @Override
    public void undo() {
        StringReplacer stringReplacer = new StringReplacer(stringDrink, change, old);
        stringReplacer.execute();
    }
}
