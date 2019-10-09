package Model;

public class RandomNumberGenerator {
    public int createRandomNumber(int max, int min) {
        int range = max - min +1;

        return (int) (Math.random() * range) + min;
    }

    RandomNumberGenerator() {}
}
