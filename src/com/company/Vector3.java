package com.company;

import java.lang.Math;

public class Vector3 {
    private double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    double getVectX() { return x; }
    double getVectY() { return y; }
    double getVectZ() { return z; }

    public Vector3 normalize() {
        double norm = Math.sqrt(x * x + y * y + z * z);
        return (new Vector3(x/norm, y/norm, z/norm));
    }

    Vector3 negative() { return (new Vector3(-x, -y, -z)); }

    double dotProduct(Vector3 v) {
        return (x * v.getVectX() + y * v.getVectY() + z * v.getVectZ());
    }

    Vector3 crossProduct(Vector3 v) {
        return (new Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x));
    }

    Vector3 add(Vector3 v) {
        return (new Vector3(x + v.x, y + v.y, z + v.z));
    }

    Vector3 subtract(Vector3 v) {
        return (new Vector3(x - v.x, y - v.y, z - v.z));
    }

    Vector3 multiply(double scalar) {
        return (new Vector3(x * scalar, y * scalar, z * scalar));
    }

    double normSquared() { return x * x + y * y + z * z; }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
