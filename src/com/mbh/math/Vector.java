package com.mbh.math;

public abstract class Vector<T extends Vector> {


    public abstract T getCopyOf();

    public double getLength() {
        return Math.sqrt(this.getLengthSquared());
    }

    public abstract double getLengthSquared();

    public abstract void scale(double scalar);

    public T getScaled(double scalar) {
        T temp = getCopyOf();
        temp.scale(scalar);
        return temp;
    }

    public void normalize() {
        this.scale(1.0 / this.getLength());
    }

    public T getNormalized() {
        T temp = this.getCopyOf();
        temp.normalize();
        return temp;
    }

    public abstract void rotate(double dRadians);

    public T getRotated(double dRadians) {
        T temp = this.getCopyOf();
        temp.rotate(dRadians);
        return temp;
    }

    public void rotateAround(double dRadians, T anchor) {
        this.sub(anchor);
        this.rotate(dRadians);
        this.add(anchor);
    }

    public T getRotatedAround(double dRadians, T anchor) {
        T temp = this.getCopyOf();
        temp.rotateAround(dRadians, anchor);
        return temp;
    }

    public abstract void add(T that);

    public T getAdd(T that) {
        T temp = this.getCopyOf();
        temp.add(that);
        return temp;
    }

    public abstract void sub(T that);

    public T getSub(T that) {
        T temp = this.getCopyOf();
        temp.sub(that);
        return temp;
    }

    public abstract double dot(T that);

    public static float getSinBetween(Vector a, Vector b) {
        double dot = a.dot(b);
        return (float) Math.sqrt(1.0f - (dot * dot) / (a.getLengthSquared() * b.getLengthSquared()));
    }
}
