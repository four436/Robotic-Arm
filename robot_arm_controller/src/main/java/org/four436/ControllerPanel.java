package org.four436;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class ControllerPanel {
    private static ControllerManager controllerManager = new ControllerManager();
    private static ControllerState controller;
    private static int i = 0;
    
    public static void init(){
        controllerManager.initSDLGamepad();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
    }

    public static void refresh(){
        controller = controllerManager.getState(i);
    }

    public static boolean xButton(){ return controller.x; }

    public static boolean yButton(){
        return controller.y;
    }

    public static boolean aButton(){
        return controller.a;
    }

    public static boolean bButton(){
        return controller.b;
    }

    public static boolean dUpButton(){ return controller.dpadUp; }

    public static boolean dDownButton(){
        return controller.dpadDown;
    }

    public static boolean dLeftButton(){
        return controller.dpadLeft;
    }

    public static boolean dRightButton(){
        return controller.dpadRight;
    }

    public static int getLeftStickX(){
        return (int) (controller.leftStickX * 10);
    }

    public static float getLeftStickY(){
        return (int) (controller.leftStickY * 10);
    }

    public static float getRightStickX(){
        return (int) (controller.rightStickX * 10);
    }

    public static float getRightStickY(){
        return (int) (controller.rightStickY * 10);
    }

    public static boolean startButton(){
        return controller.start;
    }

    public static boolean backButton(){
        return controller.back;
    }

    public static boolean xButtonJustPressed(){
        return controller.xJustPressed;
    }

    public static boolean yButtonJustPressed(){
        return controller.yJustPressed;
    }

    public static boolean aButtonJustPressed(){
        return controller.aJustPressed;
    }

    public static boolean bButtonJustPressed(){
        return controller.bJustPressed;
    }

    public static boolean dUpButtonJustPressed(){
        return controller.dpadUpJustPressed;
    }

    public static boolean dDownButtonJustPressed(){
        return controller.dpadDownJustPressed;
    }

    public static boolean dLeftButtonJustPressed(){
        return controller.dpadLeftJustPressed;
    }

    public static boolean dRightButtonJustPressed(){
        return controller.dpadRightJustPressed;
    }

    public static boolean startButtonJustPressed(){
        return controller.startJustPressed;
    }

    public static boolean backButtonJustPressed(){
        return controller.backJustPressed;
    }

    public static boolean leftStickButton(){
        return controller.leftStickClick;
    }

    public static boolean rightStickButton(){
        return controller.rightStickClick;
    }

    public static boolean leftTriggerJustPressed(){
        return controller.leftStickJustClicked;
    }

    public static boolean rightTriggerJustPressed(){
        return controller.rightStickJustClicked;
    }

    public static boolean lbButton(){
        return controller.lb;
    }

    public static boolean rbButton(){
        return controller.rb;
    }

    public static boolean lbButtonJustPressed(){
        return controller.lbJustPressed;
    }

    public static boolean rbButtonJustPressed(){
        return controller.rbJustPressed;
    }

    public static int lTrigger(){
        return (int) (controller.leftTrigger * 10);
    }

    public static int rTrigger(){
        return (int) (controller.rightTrigger * 10);
    }

}
