package com.company;

import java.util.Optional;

public class Plane implements Object {
    Vector3 normal, origin;
    double distance, intersectionDistance;
    Colour colour;

    public Plane() {
        normal = new Vector3(1, 0, 0);
        distance = 0;
        colour = new Colour(0.5, 0.5, 0.5, 0);
        intersectionDistance = -1;
        origin = normal.multiply(distance);
    }

    public Plane(Vector3 normal, double dist, Colour col) {
        this.normal = normal;
        distance = dist;
        colour = col;
        intersectionDistance = -1;
        origin = normal.multiply(distance);
    }

    public Vector3 getNormal() { return normal; }
    public double getDistance() { return distance; }
    public Colour getColour() { return colour; }
    public double getIntersectionDistance() { return intersectionDistance; }
    public Vector3 getOrigin() { return origin; }

    public Vector3 getNormalAt(Vector3 point) { //overbodige functie??
        return (normal);
    }

    /* returns dist from ray origin to intersection with plane */
    public Optional<Intersection> findIntersection(Ray ray) {
        Intersection intersection = new Intersection(this);
        Vector3 rayDirection = ray.getRayDirection();
        double a = rayDirection.dotProduct(normal);

        if (a == 0) {
            // ray is parallel to plane
            intersection.setDistance(-1);
            return Optional.empty();
        }
        else {
            double b = normal.dotProduct(origin.subtract(ray.getRayOrigin()));
            intersection.setDistance(b / a);
            if ((b / a) > 0) {
                intersection.setPoint(ray.getRayOrigin().add(ray.getRayDirection().multiply(b / a)));
                if (a > 0) {
                    intersection.setNormal(normal.multiply(-1));
                } else {
                    intersection.setNormal(normal);
                }
            }
            else { // intersection behind camera
                return Optional.empty();
            }
        }
        return Optional.of(intersection);
    }
}
