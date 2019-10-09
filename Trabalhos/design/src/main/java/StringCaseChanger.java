public class StringCaseChanger implements StringTransformer {
    private StringDrink stringDrink;

    StringCaseChanger (StringDrink stringDrink) {
        this.stringDrink = stringDrink;
    }

    @Override
    public void execute() {
        String temp = stringDrink.getText();
        String newS = new String();
        for (int i = 0; i != temp.length(); i++) {
            if (Character.isLowerCase(temp.charAt(i))) {
                newS = newS + Character.toUpperCase(temp.charAt(i));
            }
            else {
                newS = newS + Character.toLowerCase(temp.charAt(i));
            }
        }
        stringDrink.setText(newS);
    }

    @Override
    public void undo() {
        execute();
    }
}
