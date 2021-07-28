package com.company;

import java.util.Optional;

public interface Object {
    public Colour getColour();
    public double getIntersectionDistance();
    public Vector3 getNormalAt(Vector3 point);
    public Optional<Intersection> findIntersection(Ray ray);
}
