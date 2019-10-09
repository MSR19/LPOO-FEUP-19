public class HumanClient implements Client{
    public OrderingStrategy strategy;

    public HumanClient(OrderingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void wants(StringRecipe recipe, StringBar bar) {
        strategy.wants(recipe,bar);
    }

    @Override
    public void happyHourStarted(Bar bar) {
        StringBar stringBar = new StringBar(bar);
        strategy.happyHourStarted(stringBar);
    }

    @Override
    public void happyHourEnded(Bar bar) {
        StringBar stringBar = new StringBar(bar);
        strategy.happyHourEnded(stringBar);

    }
}
