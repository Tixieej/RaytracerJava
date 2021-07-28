package com.company;

import java.lang.Math;
import java.util.Optional;

public class Sphere implements Object {
    private Vector3 center;
    private double radius, intersectionDistance;
    private Colour colour;

    public Sphere() {
        center = new Vector3(0, 0, 0);
        radius = 1.0;
        colour = new Colour(0.5, 0.5, 0.5, 0);
        intersectionDistance = -1;
    }
    public Sphere(Vector3 center, double rad, Colour col) {
        this.center = center;
        radius = rad;
        colour = col;
        intersectionDistance = -1;
    }

    public Vector3 getCenter() { return center; }
    public double getRadius() { return radius; }
    public Colour getColour() { return colour; }
    public double getIntersectionDistance() { return intersectionDistance; }

    public Vector3 getNormalAt(Vector3 point) {
        // normal always points away from center of sphere
        return (point.subtract(center).normalize());
    }

    public Optional<Intersection> findIntersection(Ray ray) {
        Intersection intersection = new Intersection(this);
        double rayOriginX = ray.getRayOrigin().getVectX();
        double rayOriginY = ray.getRayOrigin().getVectY();
        double rayOriginZ = ray.getRayOrigin().getVectZ();

        double rayDirectionX = ray.getRayDirection().getVectX();
        double rayDirectionY = ray.getRayDirection().getVectY();
        double rayDirectionZ = ray.getRayDirection().getVectZ();

        double sphereCenterX = center.getVectX();
        double sphereCenterY = center.getVectY();
        double sphereCenterZ = center.getVectZ();

        double b = -1 * (((rayOriginX - sphereCenterX) * rayDirectionX) +
                ((rayOriginY - sphereCenterY) * rayDirectionY) +
                ((rayOriginZ - sphereCenterZ) * rayDirectionZ));

        if (b < 0) {
            return Optional.empty();
        }

        double c = Math.pow(sphereCenterX - rayOriginX, 2) +
            Math.pow(sphereCenterY - rayOriginY, 2) +
            Math.pow(sphereCenterZ - rayOriginZ, 2) - (b * b);
        if (c > radius * radius) {
            return Optional.empty();
        }

        double d = Math.sqrt(radius * radius - c);
        double t = (b - d) < 0 ? b + d : b - d;
        intersection.setDistance(t);
        intersection.setPoint(ray.getRayOrigin().add(ray.getRayDirection().multiply(t)));
        intersection.setNormal(intersection.getPoint().subtract(center).normalize());
        return Optional.of(intersection);
    }
}
