package arm;

import processing.core.PApplet;
import processing.core.PShape;

public class Arm {


    double F = 50;
    double T = 70;

    PApplet context;
    PShape base, shoulder, upArm, loArm, end;
    public double rotX, rotY;
    double posX=1, posY=90, posZ=50;
    double alpha, beta, gamma;


    public Arm(PApplet pApplet){
        context     = pApplet;

        base        = context.loadShape("r5.obj");
        shoulder    = context.loadShape("r1.obj");
        upArm       = context.loadShape("r2.obj");
        loArm       = context.loadShape("r3.obj");
        end         = context.loadShape("r4.obj");

        shoulder.disableStyle();
        upArm.disableStyle();
        loArm.disableStyle();
    }



    public void drawArm(double _alpha,double _beta,double _gamma){
        alpha = _alpha;
        beta  = _beta;
        gamma = _gamma;
        drawArm();
    }

    public void drawArm(){

        context.noStroke();

        context.translate(context.width/2,context.height/2);
        context.rotateX((float) rotX);
        context.rotateY((float)-rotY);
        context.scale(-4);

        context.fill(255, 227, 8);
        context.translate(0,-40,0);
        context.shape(base);

        context.translate(0, 4, 0);
        context.rotateY((float) gamma);
        context.shape(shoulder);
        //#42f483
        context.fill(66, 244, 131);
        context.translate(0, 25, 0);
        context.rotateY(context.PI);
        context.rotateX((float) alpha);
        context.shape(upArm);

        context.translate(0, 0, 50);
        context.rotateY(context.PI);
        context.rotateX((float) beta);
        context.shape(loArm);

        context.translate(0, 0, -50);
        context.rotateY(context.PI);
        context.shape(end);
    }


    public double getF() {
        return F;
    }

    public double getT() {
        return T;
    }
}
