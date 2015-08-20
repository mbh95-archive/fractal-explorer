package com.mbh.math;

public class Vector2d extends Vector<Vector2d> {

    public double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Vector2d getCopyOf() {
        return new Vector2d(x, y);
    }

    @Override
    public double getLengthSquared() {
        return x * x + y * y;
    }

    @Override
    public void scale(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    @Override
    public void rotate(double dRadians) {
        Vector2d rotation = new Vector2d(Math.cos(dRadians), Math.sin(dRadians));
        this.complexMultiply(rotation);
    }

    public Vector2d getLeftHand() {
        return new Vector2d(-y, x);
    }

    public Vector2d getRightHand() {
        return new Vector2d(y, -x);
    }

    @Override
    public void add(Vector2d that) {
        this.x += that.x;
        this.y += that.y;
    }

    @Override
    public void sub(Vector2d that) {
        this.x -= that.x;
        this.y -= that.y;
    }

    @Override
    public double dot(Vector2d that) {
        return this.x * that.x + this.y * that.y;
    }

    public void complexMultiply(Vector2d that) {
        double temp_x = this.x;
        this.x = this.x * that.x - this.y * that.y;
        this.y = temp_x * that.y + this.y * that.x;
    }

    public Vector2d getComplexProduct(Vector2d that) {
        Vector2d temp = this.getCopyOf();
        temp.complexMultiply(that);
        return temp;
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }
}
