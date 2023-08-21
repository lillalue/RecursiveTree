package com.oblig1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;

public class ControlPane extends VBox {
    private static final ControlPane instance = getPane();
    private static Slider stemLength;
    private static Slider numGenerations;
    private static Slider sizeReduction;
    private static Slider angle;
    private static Slider lengthDeviation;
    private static Slider angleDeviation;
    private static Slider existanceProb;
    private static Slider existanceProbFromGeneration;

    private ControlPane(){}

    public static ControlPane getInstance(){ return instance; }
    public static ControlPane getPane(){
        ControlPane vb = new ControlPane();
        vb.setPrefSize(500, 500);

        Label numGenerationsLabel = new Label("Number of generations:");
        numGenerations = new Slider(1, 10, 4);
        numGenerations.setSnapToTicks(true);
        numGenerations.setShowTickLabels(true);
        numGenerations.setMajorTickUnit(1);
        numGenerations.setBlockIncrement(1);


        Label stemLengthLabel = new Label("Stem length:");
        stemLength = new Slider(50, 500, 100);
        stemLength.setSnapToTicks(true);
        stemLength.setShowTickLabels(true);
        stemLength.setMajorTickUnit(100);
        stemLength.setBlockIncrement(10);

        Label sizeReductionLabel = new Label("Size reduction per generation:");
        sizeReduction = new Slider(10, 90, 50);
        sizeReduction.setShowTickLabels(true);
        sizeReduction.setMajorTickUnit(10);

        Label angleLabel = new Label("Angle difference per generation:");
        angle = new Slider(1, 360, 45);
        angle.setShowTickLabels(true);
        angle.setMajorTickUnit(45);

        Label lengthDeviationLabel = new Label("Length deviation per generation [%]:");
        lengthDeviation = new Slider(0, 20, 0);
        lengthDeviation.setSnapToTicks(true);
        lengthDeviation.setShowTickLabels(true);
        lengthDeviation.setMajorTickUnit(5);
        lengthDeviation.setBlockIncrement(1);

        Label angleDeviationLabel = new Label("Angle deviation per generation [%]:");
        angleDeviation = new Slider(0, 180, 0);
        angleDeviation.setSnapToTicks(true);
        angleDeviation.setShowTickLabels(true);
        angleDeviation.setMajorTickUnit(20);
        angleDeviation.setBlockIncrement(1);

        Label existanceProbLabel = new Label("Probability of branches existing [%]:");
        existanceProb = new Slider(10, 100, 100);
        existanceProb.setSnapToTicks(true);
        existanceProb.setShowTickLabels(true);
        existanceProb.setMajorTickUnit(20);
        existanceProb.setBlockIncrement(10);

        Label existanceProbFromGenerationLabel = new Label("Generation from which a branch might not exist:");
        existanceProbFromGeneration = new Slider(1, 10, 1);
        existanceProbFromGeneration.setSnapToTicks(true);
        existanceProbFromGeneration.setShowTickLabels(true);
        existanceProbFromGeneration.setMajorTickUnit(1);
        existanceProbFromGeneration.setBlockIncrement(1);


        vb.getChildren().addAll(
                numGenerationsLabel,
                numGenerations,
                stemLengthLabel,
                stemLength,
                sizeReductionLabel,
                sizeReduction,
                angleLabel,
                angle,
                lengthDeviationLabel,
                lengthDeviation,
                angleDeviationLabel,
                angleDeviation,
                existanceProbLabel,
                existanceProb,
                existanceProbFromGenerationLabel,
                existanceProbFromGeneration,
                createBottomPane()
        );


        return vb;
    }
    private static HBox createBottomPane() {
        Button ok = new Button("Ok");

        ok.setOnAction(event -> {
            RecursiveTree tree = new RecursiveTree(
                    Oblig1.getSize(),
                    (int)stemLength.getValue(),
                    (int)numGenerations.getValue(),
                    (int)sizeReduction.getValue(),
                    (int)angle.getValue(),
                    (int)lengthDeviation.getValue(),
                    (int)angleDeviation.getValue(),
                    (int)existanceProb.getValue(),
                    (int)existanceProbFromGeneration.getValue()
            );

            Oblig1.setLeft(tree.drawTree());
        });

        HBox bottomPane = new HBox();
        bottomPane.getChildren().add(ok);

        return bottomPane;
    }
}
