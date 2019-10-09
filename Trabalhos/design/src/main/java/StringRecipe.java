import java.util.ArrayList;
import java.util.List;

public class StringRecipe {
    private List<StringTransformer> stringTransformers = new ArrayList<>();

    StringRecipe (List<StringTransformer> stringTransformers) {
        this.stringTransformers = stringTransformers;
    }

    public void mix() {
        for (StringTransformer stringTransformer: stringTransformers) {
            stringTransformer.execute();
        }
    }
}
