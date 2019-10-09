package com.aor.refactoring.example2;

public class Shape {
    protected double x;
    protected double y;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getArea() throws Exception {
        throw new Exception("Shape with no area");
    }

    public double getPerimeter() throws Exception {
        throw new Exception("Shape with no perimeter");
    }

    public void draw(GraphicFramework graphics) { };
}
