import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements OrderingStrategy {
    private List<StringRecipe> stringRecipes = new ArrayList<>();

    @Override
    public void wants(StringRecipe recipe, StringBar bar) {
        stringRecipes.add(recipe);
        if (bar.isHappyHour())
            for (StringRecipe stringRecipe: stringRecipes) {
                bar.order(stringRecipe);
            }
    }

    @Override
    public void happyHourStarted(StringBar bar) {
        for (StringRecipe stringRecipe: stringRecipes) {
            bar.order(stringRecipe);
        }
    }

    @Override
    public void happyHourEnded(StringBar bar) {

    }
}
