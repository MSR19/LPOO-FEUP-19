import java.util.ArrayList;
import java.util.List;

public class StringTransformerGroup implements StringTransformer {
    private List<StringTransformer> stringTransformers = new ArrayList<>();

    StringTransformerGroup (List<StringTransformer> stringTransformers) {
        this.stringTransformers = stringTransformers;
    }

    @Override
    public void execute() {
        for (StringTransformer stringTransformer: stringTransformers) {
            stringTransformer.execute();
        }
    }

    @Override
    public void undo() {
        for (StringTransformer stringTransformer: stringTransformers) {
            stringTransformer.undo();
        }
    }
}
