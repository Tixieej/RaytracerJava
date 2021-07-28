package com.company;

public class Ray {
    private Vector3 origin, direction;

    public Ray() {
        origin = new Vector3(0, 0, 0);
        direction = new Vector3(1, 0, 0);
    }
    public Ray(Vector3 o, Vector3 d) {
        origin = o;
        direction = d;
    }

    Vector3 getRayOrigin () { return origin; }
    Vector3 getRayDirection () { return direction; }
}
