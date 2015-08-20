package com.mbh.math;

public class Complex extends Vector<Complex> {
    public double re, im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public Complex getCopyOf() {
        return new Complex(re, im);
    }

    @Override
    public double getLengthSquared() {
        return re * re + im * im;
    }

    @Override
    public void scale(double scalar) {
        re *= scalar;
        im *= scalar;
    }

    @Override
    public void rotate(double dRadians) {
        Complex rotation = new Complex(Math.cos(dRadians), Math.sin(dRadians));
        this.multiply(rotation);
    }

    @Override
    public void add(Complex that) {
        this.re += that.re;
        this.im += that.im;
    }

    @Override
    public void sub(Complex that) {
        this.re -= that.re;
        this.im -= that.im;
    }

    @Override
    public double dot(Complex that) {
        return this.re * that.re + this.im * that.im;
    }

    public void multiply(Complex that) {
        double temp = this.re * that.re - this.im * that.im;
        this.im = this.re * that.im + this.im * that.re;
        this.re = temp;
    }

    public Complex getMultiply(Complex that) {
        Complex temp = this.getCopyOf();
        temp.multiply(that);
        return temp;
    }
}
