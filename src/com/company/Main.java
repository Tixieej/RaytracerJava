package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class Main {

    public static final Colour BLACK = new Colour(0, 0, 0, 0);

    Optional<Intersection> winningObject(Vector<Object> shapes, Ray ray) {
        return shapes.stream()
                .map(o -> o.findIntersection(ray))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min(Comparator.comparing(Intersection::getDistance));
    }

    // TODO: Materiaal van een object toevoegen
    Colour computeLight(Intersection intersection, Vector<Object> shapes, List<Light> lights) {
        // make ray from intersection to lightsource
        Colour colour = BLACK;
        for (Light light : lights) {
            Vector3 shadowRayDir = light.getPosition().subtract(intersection.getPoint()).normalize();
            Ray shadowRay = new Ray(intersection.getPoint().add(shadowRayDir.multiply(0.0001)), shadowRayDir);
            // find intersection of new ray with all shapes
            colour = colour.add(winningObject(shapes, shadowRay)
                    .flatMap(i -> {
                        if ((i.getDistance() * i.getDistance()) < intersection.getPoint().subtract(light.getPosition()).normSquared()) {
                            return Optional.of(BLACK);
                        } else {
                            return Optional.empty();
                        }
                    })
                    .orElseGet(() -> {
                        Colour c = intersection.getObject().getColour();
                        double scalingFactor = shadowRayDir.dotProduct(intersection.getNormal());
                        return c.multiply(scalingFactor).multiply(light.getColour());
                    }));
        }
        return colour;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();

        Main main = new Main();
        int width = 640;
        int height = 480;
//        int colours = 255;
        int n = width*height;

        double aspectRatio = ((double) width / (double) height);

        Vector3 O = new Vector3(0, 0, 0);
        Vector3 X = new Vector3(1, 0, 0);
        Vector3 Y = new Vector3(0, 1, 0);
        Vector3 Z = new Vector3(0, 0, 1);

        // camera
        Vector3 camPos = new Vector3(0, 0, -4);
        Vector3 lookAt = new Vector3(0, 0, 0); // je kijkt naar origin
        Camera camera = new Camera(camPos, aspectRatio, lookAt);

        // colours
        Colour white = new Colour(1.0, 1.0, 1.0, 0.0);
        Colour green = new Colour(0.35, 0.7, 0.3, 0.3);
        Colour cyan = new Colour(0.3, 0.6, 0.5, 0.3);
        Colour maroon = new Colour(0.5, 0.25, 0.25, 0);
        Colour gray = new Colour(0.5, 0.5, 0.5, 0);
        Colour salmon = new Colour (0.7, 0.4, 0.35, 0);
        Colour blue = new Colour (0.3, 0.3, 0.65, 0);
        Colour sky = new Colour (0.5, 0.6, 0.85, 0);
        Colour yellow = new Colour(1.0, 1.0, 0.4, 0.0);
        Colour red = new Colour(1.0, 0.2, 0.2, 0);

        // lights
        Vector3 lightPos = new Vector3(-2, 3, -3);
        Light sceneLight = new Light(lightPos, white);
        Vector3 sunPos = new Vector3(-8, 10, -1);
        Light sun = new Light(sunPos, yellow);
        Vector3 stageLightPos = new Vector3(5, 3, 1);
        Light stageLight = new Light(stageLightPos, red);
        List<Light> lights = List.of(sun, sceneLight);

        // scene objects
        Sphere greenBall = new Sphere(O, 1, green);
        Vector3 redCenter = new Vector3(-1,-2,-1);
        Sphere redSphere = new Sphere(redCenter, 2, salmon);
        Vector3 thirdBallPos = new Vector3(3,1,2);
        Sphere thirdBall = new Sphere(thirdBallPos, 1, cyan);
        Plane scenePlane = new Plane(Y, -1, blue);
        Plane skyPlane = new Plane(Z, 20, sky);

        Vector<Object> sceneObjects = new Vector(2);
        sceneObjects.add(greenBall);
        sceneObjects.add(redSphere);
        sceneObjects.add(thirdBall);
        sceneObjects.add(scenePlane);
        sceneObjects.add(skyPlane);

        Filemaker image = new Filemaker(width, height);

        /* loop trough all pixels of image */
        for(int y = 0; y < height; y++) {
            System.out.println(y);
            for (int x = 0; x < width; x++) {
                Ray camRay = camera.createRay(((double)x)/width, ((double)y)/height);
                Colour colour = main.winningObject(sceneObjects, camRay)
                        .map(intersection -> main.computeLight(intersection, sceneObjects, lights))
                        .orElse(BLACK);
                image.addPixel(x, y, colour);
            }
        }
        image.buildImage();
        long end = System.nanoTime();
        System.out.println("Loop Time: " + (end-start)/1000000000.0F + " seconds.");
    }
}
