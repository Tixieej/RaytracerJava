package com.company;

public class Light {
    private Vector3 position;
    private Colour colour;

    public Light() {
        position = new Vector3(0, 0, 0);
        colour = new Colour(1, 1, 1, 0);
    }
    public Light(Vector3 pos, Colour c) {
        position = pos;
        colour = c;
    }

    public Vector3 getPosition() { return position; }
    public Colour getColour() { return colour; }

}
