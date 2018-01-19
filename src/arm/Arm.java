package arm;

import processing.core.PApplet;
import processing.core.PShape;

public class Arm {

    PApplet context;
    PShape base, shoulder, upArm, loArm, end;
    public float rotX, rotY;
    float posX=1, posY=90, posZ=50;
    float alpha, beta, gamma;


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



    public void drawArm(){

        context.noStroke();

        context.translate(context.width/2,context.height/2);
        context.rotateX(rotX);
        context.rotateY(-rotY);
        context.scale(-4);

        context.fill(255, 227, 8);
        context.translate(0,-40,0);
        context.shape(base);

        context.translate(0, 4, 0);
        context.rotateY(gamma);
        context.shape(shoulder);
        //#42f483
        context.fill(66, 244, 131);
        context.translate(0, 25, 0);
        context.rotateY(context.PI);
        context.rotateX(alpha);
        context.shape(upArm);

        context.translate(0, 0, 50);
        context.rotateY(context.PI);
        context.rotateX(beta);
        context.shape(loArm);

        context.translate(0, 0, -50);
        context.rotateY(context.PI);
        context.shape(end);
    }


}
