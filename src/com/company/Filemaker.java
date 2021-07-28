package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filemaker {
    private String header = "";
    private String body = "";
    private Colour[][] pixels;
    private int width, height;

    // makes the file, names the file and writes the header
    public Filemaker(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Colour[height][width];
    }

    public void createFile() {
        try {
            File image = new File("raytracer.ppm");
            if (image.createNewFile()) {
                System.out.println("File created: " + image.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void makeHeader(int width, int height, int colours) {
        header = "P3 " + width + " " + height + " " + colours;
    }

    public void fillGray(int width, int height, int colours) {
        int gray = colours/2;
        String grayString = Integer.toString(gray);
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                body = body + " " + grayString + " " + grayString + " " + grayString;
            }
        }
    }

    public void addToBody(String bodyString) {
        body = body + bodyString + " ";
    }

    public void addPixel(int x, int y, Colour colour) {
        pixels[y][x] = colour;
    }

    public void graydient(int width, int height, int colours) {
        int step = (int) (colours / height);
        int curColour = colours;

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String blob = Integer.toString(curColour);
                body = body + " " + blob + " " + blob + " " + blob;
            }
            curColour -= step;
        }
    }

    public void buildImage() {
        createFile();
        makeHeader(width, height, 255);
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(pixels[y][x].colourToString());
                builder.append(" ");
            }
            builder.append("\n");
        }
        body = builder.toString();
        writeToFile();
    }

//
//    public void fillSunset(int width, int height, int colours) {
//        Colour top = new Colour(0.216, 0.0314, 0.431, 0);
//        Colour bottom = new Colour(0.118, 0.235, 0.784, 0);
//        Colour curColour = top.copy();
//
//        for(int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                body = body + " " + curColour.colourToString();
//            }
//            // top + (((bottom - top)/height * i)
//            System.out.println(i + " " + ((bottom.red - top.red) * i));
//            curColour.red = top.red + (((bottom.red - top.red) * i) / height);
//            curColour.green = top.green + (((bottom.green - top.green) * i) / height);
//            curColour.blue = top.blue + (((bottom.blue - top.blue) * i) / height);
//            body = body + "\n";
//        }
//    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("raytracer.ppm");
            myWriter.write(header + "\n" + body);
            //System.out.println(header + "\n" + body);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
