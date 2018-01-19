package gui;

import arm.Arm;
import com.martinleopold.pui.Button;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;


public class Window extends PApplet {

    Arm genericArm;

    Controls controls;
    PShape grid;
    int maxX = 200;
    PVector gridPos = new PVector(0, 0, 0);

    PShape apoyo, base, brazo1, brazo2, end;
    float rotX, rotY;
    float posX = 1, posY = 90, posZ = 50;
    float alpha, beta, gamma;
    PVector origin;


    float[] Xsphere = new float[99];
    float[] Ysphere = new float[99];
    float[] Zsphere = new float[99];

    @Override
    public void settings() {
        size(1280, 800, P3D);
        //fullScreen(P3D);
        origin = new PVector(width / 2, height / 2);
    }

    @Override
    public void setup() {
        genericArm  = new Arm(this);
        //colorMode(HSB, 100, 100, 100, 100);
        //frameRate(60);
        controls = new Controls(this, 500, height);
    }

    @Override
    public void draw() {


        hint(ENABLE_DEPTH_TEST);
        background(170);
        smooth();
        lights();
        directionalLight(51, 102, 126, -1, 0, 0);

        genericArm.drawArm();



    }

    private void drawAxis() {
        translate(0, 0, 0);
        stroke(0, 0, 250);
        line(0, origin.y, width, origin.y);// y
        stroke(250, 0, 0);
        line(origin.x, 0, origin.x, height);//x
        stroke(0, 250, 0);
        line(-origin.x, height, origin.x, origin.y);//z
        line(origin.x, origin.y, width * 2, -origin.y);//z
    }

    public void keyPressed() {
        switch (key) {
            case ' ':
                controls.ui.toggle();
                break;
            case 'g':
                controls.ui.toggleGrid();
                break;
        }
    }

    void buttonClick(Button b) {
        println("clicked " + b.getId());
    }

    float F = 50;
    float T = 70;
    float millisOld, gTime, gSpeed = 8;

    void IK() {
        float X = posX;
        float Y = posY;
        float Z = posZ;
        float L = (float) Math.sqrt(Y * Y + X * X);
        float dia = (float) Math.sqrt(Z * Z + L * L);

        alpha = (float) (Math.PI / 2 - (Math.atan2(L, Z) + Math.acos((T * T - F * F - dia * dia) / (-2 * F * dia))));
        beta = (float) (-Math.PI + Math.acos((dia * dia - T * T - F * F) / (-2 * F * T)));
        gamma = (float) Math.atan2(Y, X);

    }

    void setTime() {
        gTime += ((float) millis() / 1000 - millisOld) * (gSpeed / 4);
        if (gTime >= 4) gTime = 0;
        millisOld = (float) millis() / 1000;
    }

    void writePos() {
        IK();
        setTime();
        posX = (float) (Math.sin(gTime * Math.PI / 2) * 20);
        posZ = (float) (Math.sin(gTime * Math.PI) * 10);
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
}
