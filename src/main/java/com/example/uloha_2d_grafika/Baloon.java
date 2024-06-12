package com.example.uloha_2d_grafika;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Random;


public class Baloon extends Rectangle {
    public static final String STANDARD_TEXTURES = "/images/pohyb20.png";
    Image image;
    Random random = new Random();
    int direction = 2;

    public Baloon (int x, int y) {
        super(x, y);
        loadTexture();
    }

    public Baloon(int x, int y, int height, int width) {
        super(x, y, height, width);
        loadTexture();
    }

    public void loadTexture(){
        URL url = getClass().getResource(STANDARD_TEXTURES);
        if (url != null){
            image = new Image(url.toString());
            setWidth((int)image.getWidth()/2);
            setHeight((int)image.getHeight()/2);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int changeDirection(){
        direction = random.nextInt(5);
        return direction;
    }
}
