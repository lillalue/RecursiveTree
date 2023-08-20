package com.oblig1;

import Model.Branch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Oblig1 extends Application {
    private static ArrayList<Branch> lastGenBranches;
    private static ArrayList<Branch> newBranches;
    private static Scene scene;
    private static int numGenerations;
    private static Pane pane;
    private static final int SIZE = 500;

    public static void initialize(){
        numGenerations = 10;
        pane = new Pane();
        scene = new Scene(ControlPane.getInstance(), 2*SIZE, SIZE);
        lastGenBranches = new ArrayList<>();
        newBranches = new ArrayList<>();
    }
    @Override
    public void start(Stage stage) {
        initialize();
        //drawStem();
        //drawBranches(numGenerations);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private static void drawBranches(int numGeneration){

        drawBranch(lastGenBranches.size()*2);

        lastGenBranches = new ArrayList<>(newBranches);
        newBranches.clear();

        if (numGeneration <= 1 || lastGenBranches.get(0).getLength() < 2)
            return;
        drawBranches(--numGeneration);
    }
    private static void drawBranch(int numBranches){

        // Two new branches needs to be drawn on each of branches in the
        // last generation. This makes sure new branch number one and two
        // will be drawn on last gen branch one.
        int targetBranchIndex = (int)numBranches/2;

        if( numBranches % 2 != 0)
            targetBranchIndex++;

        // The branch to draw from
        Branch targetBranch = lastGenBranches.get(targetBranchIndex-1);

        if(numBranches % 2 == 0)
            newBranches.add(createNewBranch(targetBranch, 1));
        else
            newBranches.add(createNewBranch(targetBranch, -1));

        pane.getChildren().add(newBranches.get(newBranches.size()-1));

        // Basecase for recursion
        if (numBranches <= 1)
            return;

        drawBranch(--numBranches);
    }
    private static Branch createNewBranch(Branch targetBranch, int branchType) {

        int newBranchLength = targetBranch.getLength()/2;
        int angleCorrection = 45 * branchType;
        Branch newBranch = new Branch(newBranchLength, targetBranch.getAngle() + angleCorrection);

        int startX = (int)targetBranch.getEndX();
        int startY = (int)targetBranch.getEndY();

        int endX = (int)(startX + newBranchLength * Math.cos(Math.toRadians(newBranch.getAngle())));
        int endY = (int)(startY + newBranchLength * Math.sin(Math.toRadians(newBranch.getAngle())));

        newBranch.setStartX(startX);
        newBranch.setStartY(startY);
        newBranch.setEndX(endX);
        newBranch.setEndY(endY);

        return newBranch;
    }
    private static void drawStem(){
        Branch stem = new Branch(200, -90);
        stem.setStartX(SIZE/2);
        stem.setStartY(SIZE);
        stem.setEndX((int)(stem.getStartX() + Math.cos(Math.toRadians(stem.getAngle())) * stem.getLength()));
        stem.setEndY((int)(stem.getStartY() + Math.sin(Math.toRadians(stem.getAngle())) * stem.getLength()));

        lastGenBranches.add(stem);
        pane.getChildren().add(stem);
    }
    private static int getLengthOfLine(Line line){
        double lineStartX = line.getStartX();
        double lineStartY = line.getStartY();

        double lineEndX = line.getEndX();
        double lineEndY = line.getEndY();

        double differenceX = lineStartX - lineEndX;
        double differenceY = lineStartY - lineEndY;

        double lineLength = Math.sqrt(      Math.pow(differenceX, 2) + Math.pow(differenceY, 2)     );

        return (int)lineLength;
    }
    public static void main(String[] args) {
        launch();
    }
}