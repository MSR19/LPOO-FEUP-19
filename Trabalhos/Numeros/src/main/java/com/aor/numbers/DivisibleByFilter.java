package com.aor.numbers;

public class DivisibleByFilter implements IListFilter {
    int number;

    DivisibleByFilter (int number) {
        this.number = number;
    }

    @Override
    public boolean accept(Integer number) {
        if (number%this.number == 0) return true;
        else return false;
    }
}
