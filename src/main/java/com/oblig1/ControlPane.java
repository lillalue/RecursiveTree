package com.oblig1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ControlPane extends BorderPane {
    private static ControlPane instance = getPane();
    private static int stemLength;
    private static int numGenerations;
    private static int sizeReduction;
    private static int angle;
    private static int lengthDeviation;
    private static int angleDeviation;
    private static int existanceProb;
    private static int existanceProbFromGeneration;

    private ControlPane(){}

    public static ControlPane getInstance(){ return instance; }
    public static int getStemLength() { return stemLength; }
    public static int getNumGenerations() { return numGenerations; }
    public static int getSizeReduction() { return sizeReduction; }
    public static int getAngle() { return angle; }
    public static int getLengthDeviation() { return lengthDeviation; }
    public static int getAngleDeviation() { return angleDeviation; }
    public static int getExistanceProb() { return existanceProb; }
    public static int getExistanceProbFromGeneration() { return existanceProbFromGeneration; }

    public static ControlPane getPane(){
        ControlPane bp = new ControlPane();



        bp.setBottom(createBottomPane());
        return bp;
    }
    private static HBox createLeftPane() {

        

        HBox leftPane = new HBox();
        leftPane.getChildren().addAll();

        return leftPane;
    }
    private static VBox createBottomPane() {
        Button ok = new Button("Ok");

        VBox bottomPane = new VBox();
        bottomPane.getChildren().add(ok);

        return bottomPane;
    }

}
