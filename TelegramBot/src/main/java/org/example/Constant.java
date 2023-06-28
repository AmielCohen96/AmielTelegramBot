package org.example;

public class Constant {
    public static final int X_START = 25;
    public static final int Y_START = 30;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 30;
    public static final int FONT_SIZE = 30;
    public static final int CHECKBOX_FONT_SIZE = 20;
    public static final int CHECKBOX_WIDTH = 400;


    public static void sleep (int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

