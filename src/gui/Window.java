package gui;

import com.martinleopold.pui.Button;
import processing.core.PApplet;

public class Window extends PApplet{

    Controls controls;

    @Override
    public void settings() {
        size(600, 600, P3D);
        //fullScreen(P3D);
    }

    @Override
    public void setup() {
        colorMode(HSB, 100, 100, 100, 100);
        frameRate(60);
        controls = new Controls(this,500,height);
    }

    @Override
    public void draw() {
        if (!mousePressed) {
            hint(ENABLE_DEPTH_SORT);
        } else {
            hint(DISABLE_DEPTH_SORT);
        }

        noStroke();

        background(0);

        translate(width/2, height/2, -300);
        scale(2);

        int rot = frameCount;

        rotateZ(radians(90));
        rotateX(radians(rot/60.0f * 10));
        rotateY(radians(rot/60.0f * 30));

        blendMode(ADD);

        for (int i = 0; i < 100; i++) {
            fill(map(i % 10, 0, 10, 0, 100), 100, 100, 30);

            beginShape(TRIANGLES);
            vertex(200, 50, -50);
            vertex(100, 100, 50);
            vertex(100, 0, 20);
            endShape();

            rotateY(radians(270.0f/100));
        }
        //if (frameCount % 30 == 0) println(frameRate);
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
        println("clicked "+b.getId());
    }
}
