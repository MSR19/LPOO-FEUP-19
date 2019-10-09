package com.aor.refactoring.example1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderLine> lines = new ArrayList<>();

    public void add(Product product, int quantity) {
        lines.add(new OrderLine(product, quantity));
    }

    public boolean isElegibleForFreeDelivery() {
        double total = 0;

        total = getTotal(total);

        if (total > 100) return true;
        else return false;
    }

    public String printOrder() {
        StringBuffer printBuffer = new StringBuffer();

        for (OrderLine line : lines)
            printBuffer.append(line.printOrder());

        double total = 0;

        total = getTotal(total);

        printBuffer.append("Total: " + total);

        return printBuffer.toString();
    }

    private double getTotal(double total) {
        for (OrderLine line : lines)
            total += line.getProduct().getPrice() * line.getQuantity();
        return total;
    }
}