package com.oblig1;

import Model.Branch;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class RecursiveTree extends Pane {

    private final int SIZE;
    private final int stemLength,
            totNumGenerations,
            sizeReduction,
            angle,
            lengthDeviation,
            angleDeviation,
            existenceProb,
            existenceProbFromGeneration;
    private ArrayList<Branch> lastGenBranches = new ArrayList<>();
    private ArrayList<Branch> newBranches = new ArrayList<>();

    public RecursiveTree(int size, int stemLength, int totNumGenerations, int sizeReduction, int angle,
                         int lengthDeviation, int angleDeviation, int existenceProb, int existenceProbFromGeneration){
        this.SIZE = size;
        this.stemLength = stemLength;
        this.totNumGenerations = totNumGenerations;
        this.sizeReduction = sizeReduction;
        this.angle = angle;
        this.lengthDeviation = lengthDeviation;
        this.angleDeviation = angleDeviation;
        this.existenceProb = existenceProb;
        this.existenceProbFromGeneration = existenceProbFromGeneration;


    }
    public Pane drawTree() {
        drawStem();
        drawBranches(1);
        return this;
    }
    private void drawStem(){
        Branch stem = new Branch(stemLength, -90);
        stem.setStartX(SIZE/2);
        stem.setStartY(SIZE);
        stem.setEndX((int)(stem.getStartX() + Math.cos(Math.toRadians(stem.getAngle())) * stem.getLength()));
        stem.setEndY((int)(stem.getStartY() + Math.sin(Math.toRadians(stem.getAngle())) * stem.getLength()));

        lastGenBranches.add(stem);
        this.getChildren().add(stem);
    }
    private void drawBranches(int generationNum){

        drawBranch(lastGenBranches.size()*2, generationNum);

        lastGenBranches = new ArrayList<>(newBranches);
        newBranches.clear();

        if(lastGenBranches.size() == 0)
            return;


        if (generationNum >= totNumGenerations || lastGenBranches.get(0).getLength() < 2)
            return;
        drawBranches(++generationNum);
    }
    private void drawBranch(int numBranches, int generationNum){

        boolean gen = generationNum < existenceProbFromGeneration;
        boolean branchExistence = Math.random() * 100 <= existenceProb;

        if(gen || branchExistence) {

            // Two new branches needs to be drawn on each of branches in the
            // last generation. This makes sure new branch number one and two
            // will be drawn on last gen branch one.
            int targetBranchIndex = (int) numBranches / 2;

            if (numBranches % 2 != 0)
                targetBranchIndex++;

            // The branch to draw from
            Branch targetBranch = lastGenBranches.get(targetBranchIndex - 1);

            if (numBranches % 2 == 0)
                newBranches.add(createNewBranch(targetBranch, 1));
            else
                newBranches.add(createNewBranch(targetBranch, -1));

            this.getChildren().add(newBranches.get(newBranches.size()-1));
        }

        // Basecase for recursion
        if (numBranches <= 1)
            return;

        drawBranch(--numBranches, generationNum);
    }
    private Branch createNewBranch(Branch targetBranch, int branchType) {

        int newBranchLength = targetBranch.getLength() * sizeReduction / 100;
        newBranchLength += (int)(Math.random() * 2 * lengthDeviation - lengthDeviation);

        int angleCorrection = angle * branchType;
        angleCorrection += (int)(Math.random() * 2 * angleDeviation - angleDeviation);

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
}
