package com.company;

public class Camera {
    private Vector3 camPos, camDir, camRight, camDown, horizontal, vertical, screenDL;

    public Camera() {
        camPos = new Vector3(0, 0, 0);
        camDir = new Vector3(0, 0, 0);
        camRight = new Vector3(0, 0, 0);
        camDown = new Vector3(0, 0, 0);
    }
    public Camera(Vector3 pos, double ratio, Vector3 lookAt) {
        camPos = pos;
        camDir = camPos.subtract(lookAt).normalize();
        Vector3 Y = new Vector3(0, 1, 0);
        camRight = Y.crossProduct(camDir).normalize(); // vector vanuit camPos?? loodrecht op y (de vloer) en camDir (met lengte 1)
        camDown = camRight.crossProduct(camDir).normalize(); //recht op dir en right zodat je een scherm krijgt waardoorheen je de scene ziet
        double height = 2;
        double width = ratio * height;
        horizontal = camRight.multiply(width);
        vertical = camDown.multiply(height);
        screenDL = camPos.subtract(camRight.multiply(width/2)).subtract(camDown.multiply(height/2)).subtract(camDir);
    }

    public Vector3 getCamPos() { return camPos; }
    public Vector3 getCamDir() { return camDir; }
    public Vector3 getCamRight() { return camRight; }
    public Vector3 getCamDown() { return camDown; }

    // methode voor maken van array
    public Ray createRay(double x, double y) {
        Vector3 rayDir = (screenDL.add(horizontal.multiply(x)).add(vertical.multiply(y)).subtract(camPos)).normalize();
        return (new Ray(camPos, rayDir));
    }
}
