package Model;

import javafx.scene.shape.Line;

public class Branch extends Line {
    private int length;
    private int angle;

    public Branch(int length, int angle){
        this.length = length;
        this.angle = angle;
    }

    public int getLength(){ return this.length; }
    public int getAngle() { return this.angle; }
}
