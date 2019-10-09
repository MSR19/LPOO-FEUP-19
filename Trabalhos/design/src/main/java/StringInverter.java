public class StringInverter implements StringTransformer{
    private  StringDrink stringDrink;

    StringInverter (StringDrink stringDrink) {
        this.stringDrink = stringDrink;
    }

    @Override
    public void execute() {
        StringBuffer stringBuffer = new StringBuffer(stringDrink.getText());
        stringBuffer.reverse();
        stringDrink.setText(stringBuffer.toString());
    }

    @Override
    public void undo() {
        execute();
    }
}
