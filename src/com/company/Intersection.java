package com.company;

public class Intersection {
    private double distance;
    private Object object;
    private Vector3 point, normal;

    public Intersection(int distance, Object object, Vector3 point, Vector3 normal) {
        this.distance = distance;
        this.object = object;
        this.point = point;
        this.normal = normal;
    }
    public Intersection(Object object) {
        this.object = object;
    }

    public double getDistance() { return distance; }
    public Object getObject() { return object; }
    public Vector3 getPoint() { return point; }
    public Vector3 getNormal() { return normal; }

    public void setDistance(double distance) { this.distance = distance; }
    public void setObject(Object object) { this.object = object; }
    public void setPoint(Vector3 point) { this.point = point; }
    public void setNormal(Vector3 normal) { this.normal = normal; }
}
