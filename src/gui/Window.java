package gui;

import arm.Arm;
import com.martinleopold.pui.Button;
import kinematic.Ik;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.util.Scanner;


public class Window extends PApplet {


    //input by the user
    Scanner scanner = new Scanner( System.in );

    //arm 3d
    Arm genericArm;
    //inverse kinematic
    Ik ik;
    //graphical user interface
    Controls controls;
    PShape grid;
    int maxX = 200;
    PVector gridPos = new PVector(0, 0, 0);

    double posX = 1, posY = 60, posZ = 60;
    float alpha, beta, gamma;
    PVector origin;


    @Override
    public void settings() {
        size(1280, 800, P3D);
        //fullScreen(P3D);
        origin = new PVector(width / 2, height / 2);

    }

    @Override
    public void setup() {
        //surface.setResizable(true);
        genericArm  = new Arm(this);
        //colorMode(HSB, 100, 100, 100, 100);
        //frameRate(60);
        controls = new Controls(this, 500, height);

        ik = new Ik(genericArm.getF(),genericArm.getT());
        IkToArm(posX,posY,posZ);
    }

    @Override
    public void draw() {





        hint(ENABLE_DEPTH_TEST);
        background(170);
        smooth();
        lights();
        directionalLight(51, 102, 126, -1, 0, 0);

        drawAxis();
        genericArm.drawArm();
    }

    private void drawAxis() {
        translate(0, 0, 0);
        stroke(0, 0, 250);
        line(0, origin.y, width, origin.y);//azul y
        stroke(250, 0, 0);
        line(origin.x, 0, origin.x, height);//rojo x
        stroke(0, 250, 0);
        line(-origin.x, height, origin.x, origin.y);//verde z
        line(origin.x, origin.y, width * 2, -origin.y);//z
    }

    public void keyPressed() {
        switch (key) {
            case 'w':
                posY++;
                break;
            case 's':
                posY--;
                break;
            case 'a':
                posX--;
                break;
            case 'd':
                posX++;
                break;
            case 'r':
                posZ--;
                break;
            case 'f':
                posZ++;
                break;
            case ' ':
                controls.ui.toggle();
                break;
            case 'g':
                controls.ui.toggleGrid();
                break;
            case 'i':
                System.out.print( "Pos x: " );
                posX = scanner.nextDouble();
                System.out.print( "Pos y: " );
                posY = scanner.nextDouble();
                System.out.print( "Pos z: " );
                posZ = scanner.nextDouble();
                break;
        }
        IkToArm(posX,posY,posZ);
    }

    void buttonClick(Button b) {
        println("clicked " + b.getId());
    }

    public void mouseDragged() {
        genericArm.rotY -= (mouseX - pmouseX) * 0.01;
        genericArm.rotX -= (mouseY - pmouseY) * 0.01;
    }



    void world() {

        pushMatrix();
        translate(gridPos.x, gridPos.y, gridPos.z);
        rectGrid(25, 100);//draw the grid

        popMatrix();

    }

    void rectGrid(int size, int tilesize) {

        noFill();//i only want the outline of the rectangles
        for (float x = -size / 2; x <= size / 2; x++) {
            for (float z = -size / 2; z <= size / 2; z++) {
                //run two for loops, cycling through 10 different positions of rectangles
                pushMatrix();

                stroke(0, 255, 0, map(dist(-gridPos.x, -gridPos.z, x * tilesize, z * tilesize), 0, size / 2 * tilesize, 255, 0));//the rectangles close to you, are clear, while the ones farther from you, are much fainter

                translate(x * tilesize, 0, z * tilesize);//move the rectangles to where they shall be
                rotateX(HALF_PI);
                rect(0, 0, tilesize, tilesize);
                popMatrix();
            }
        }
    }

    private void IkToArm(double x,double y,double z){

        System.out.println( "Pos X : " + x);
        System.out.println( "Pos Y : " + y);
        System.out.println( "Pos Z : " + z);

        double angles[] = ik.evaluate(x,y,z);


        genericArm.drawArm(angles[0],angles[1],angles[2]);

        // 4. Now, you can do anything with the input string that you need to.
        // Like, output it to the user.
        System.out.println( "alpha : " + angles[0]);
        System.out.println( "beta  : " + angles[1]);
        System.out.println( "gamma : " + angles[2]);
    }


}
