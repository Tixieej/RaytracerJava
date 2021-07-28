package com.company;

public class Colour {
    double red, green, blue, special;

    public Colour() {
        red = 0.5;
        green = 0.5;
        blue = 0.5;
    }

    public Colour(double r, double g, double b, double s) {
        red = r;
        green = g;
        blue = b;
        special = s;
    }

    public double getRed() { return red; }
    public double getGreen() { return green; }
    public double getBlue() { return blue; }
    public double getSpecial() { return special; }

    public void setRed(double r) { red = r; }
    public void setGreen(double g) { green = g; }
    public void setBlue(double b) { blue = b; }
    public void setSpecial(double s) { special = s; }

    public String colourToString() {
        return ((int) (255 * red) + " " + (int) (255 * green) + " " + (int) (255 * blue));
    }

    public Colour copy() {
        return new Colour(red, green, blue, special);
    }

    public Colour multiply(double factor) {
        return new Colour(red * factor, green * factor, blue * factor, special);
    }

    public Colour add(Colour c) {
        return new Colour(Math.min(red + c.red, 1), Math.min(green + c.green, 1), Math.min(blue + c.blue, 1), special);
    }

    public Colour multiply(Colour c) {
        return new Colour(Math.min(red * c.red, 1), Math.min(green * c.green, 1), Math.min(blue * c.blue, 1), special);
    }

}
