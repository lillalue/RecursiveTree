package com.oblig1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Oblig1 extends Application {
    public static final int SIZE = 500;
    private static BorderPane bp;
    public static void setLeft(Pane pane){
        bp.setLeft(pane);
    }
    public static int getSize() { return SIZE; }
    @Override
    public void start(Stage stage) {
        bp = new BorderPane();
        bp.setRight(ControlPane.getInstance());
        Scene scene = new Scene(bp, 2*SIZE, SIZE);

        stage.setTitle("Recursive Tree!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}