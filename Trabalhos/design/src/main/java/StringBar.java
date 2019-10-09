public class StringBar extends Bar {
    StringBar() {
        super();
    }

    StringBar (Bar bar) {
        if (bar.isHappyHour())
            this.happyHour = true;
        else
            this.happyHour = false;
    }



    public void order(StringRecipe recipe) {
        recipe.mix();
    }
}
