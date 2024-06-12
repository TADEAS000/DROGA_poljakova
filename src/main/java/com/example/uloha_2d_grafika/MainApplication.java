package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1900;
    public static final int SCREEN_HEIGHT = 980;
    public boolean stop = false;
    public int speed = 10;
    public int counter = 0;

    double mouseX;
    double mouseY;

    Baloon baloon;
    Random random = new Random();

    GraphicsContext graphicsContext;


    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Klasifikovaná úloha");
        stage.show();

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });

        baloon = new Baloon(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT));

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;
            @Override
            public void handle(long now) {
                if (now - lastTick > 10000000l/speed) {
                    lastTick = now;
                    tick();
                }
            }
        };
        animationTimer.start();
    }
    private void tick() {
        if (stop) {
            return;
        }

        if (counter == 20){
            baloon.direction = baloon.changeDirection();
            counter = 0;
        }

        clearScreen();

        switch (baloon.direction){
            case 1: baloon.decreaseY();
                break;
            case 2: baloon.increaseY();
                break;
            case 3: baloon.decreaseX();
                break;
            case 4: baloon.increaseX();
                break;
        }

        if(mouseX >= baloon.x && mouseX <= baloon.x + baloon.width && mouseY >= baloon.y && mouseY <= baloon.y + baloon.height){
            mouseX = 0;
            mouseY = 0;
            baloon.x = random.nextInt(SCREEN_WIDTH - baloon.width);
            baloon.y = random.nextInt(SCREEN_HEIGHT - baloon.height);
        }

        counter++;
    Image currentBaloonTexture = baloon.getImage();
        graphicsContext.drawImage(currentBaloonTexture,baloon.x, baloon.y, baloon.height, baloon.height);
    }
    private void clearScreen() {
        graphicsContext.clearRect(0,0, SCREEN_WIDTH, SCREEN_WIDTH);
    }
    public static void main(String[] args) {
        launch();
    }
}