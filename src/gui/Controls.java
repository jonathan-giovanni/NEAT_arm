package gui;

import com.martinleopold.pui.Button;
import com.martinleopold.pui.PUI;
import com.martinleopold.pui.Theme;
import processing.core.PApplet;
import processing.event.KeyEvent;

import static com.martinleopold.pui.Theme.color;


public class Controls {
    public PUI ui;
    public PApplet pApplet;
    public Button btn1,btn2,btn3;
    public Theme t = new Theme(
            color( 205, 205, 205,  105 ),//background         ok
            color( 255, 255, 255, 220 ),//text               ok
            color( 250, 250, 250,  75 ),//outline            ok
            color( 250, 250, 250, 120 ),//outlineHighlight   ok
            color( 150, 150, 150, 150 ),//fill               ok
            color( 255, 255, 255, 250 ),//fillHighlight      ok
            color( 160, 160, 160, 60 ));//overlay           ok
    
    public Controls(PApplet p,int w,int h){
        this.pApplet = p;
        ui = PUI.init(this.pApplet).size(w,h);
        ui.font("NewMedia Fett.ttf"); // set font
        ui.addLabel("Buttons");
        ui.newRow();
        btn1 = ui.addButton().label("b1").setId("btn1").calls("buttonClick");
        btn2 = ui.addButton().label("b2").setId("btn2").calls("buttonClick").sets("b2Value");
        btn3 = ui.addButton().label("b3").setId("btn3").calls("buttonClick").onMouse("mouseEvent");
        ui.addButton().label("b4").onDraw("drawEvent");
        ui.addDivider();
        ui.theme(t);
    }


}
