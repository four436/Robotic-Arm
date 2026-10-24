package org.four436;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlPanel extends JPanel {
    JButton[] up = new JButton[6];
    JButton[] down = new JButton[6];
    JTextField[] value = new JTextField[6];
    int[] angles = new int[6];
    int previosangle = 0;
    boolean closed = true;
    boolean stepperPress = false;

    public ControlPanel(){
        for(int i = 0; i < 6; i++){
            up[i] = new JButton("Up");
            up[i].setActionCommand(String.valueOf(i+1));
            up[i].addActionListener(new UpButtonListener());
            down[i] = new JButton("Down");
            down[i].setActionCommand(String.valueOf(i+1));
            down[i].addActionListener(new DownButtonListener());
            value[i] = new JTextField("0");
            value[i].setHorizontalAlignment(JTextField.CENTER);
            value[i].setActionCommand(Integer.toString(i+1));
            value[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if(!Character.isDigit(c)){
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            value[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int servo = Integer.parseInt(e.getActionCommand());
                    int angle = Integer.parseInt(value[servo-1].getText());
                    if(servo<6) {
                        if (angle >= 0 && angle <= 180) {
                            angles[servo - 1] = angle;
                        } else if (angle > 180) {
                            value[servo - 1].setText("180");
                            angles[servo - 1] = 180;
                        } else {
                            value[servo - 1].setText("0");
                            angles[servo - 1] = 0;
                        }
                        ConnectionComms.sendAngle(servo, angles[servo - 1]);
                    }else{
                        System.out.println("stepper"+servo);
                        angles[servo-1]=angle;
                        int currentstep = angles[servo-1] - previosangle;
                        previosangle = angles[servo-1];
                        ConnectionComms.sendAngle(servo, currentstep);
                    }
                }
            });
            value[i].setFont(new Font("Arial", Font.PLAIN, 16));
            value[i].setBorder(new LineBorder(Color.BLACK,3, false));
        }

        init();

        setLayout(new GridLayout(3,6));
        for(int i = 0; i < 6; i++){
            add(up[i]);
        }
        for(int i = 0; i < 6; i++){
            add(value[i]);
        }
        for(int i = 0; i < 6; i++){
            add(down[i]);
        }
        ControllerHandler handler = new ControllerHandler();
        handler.start();
    }

    public void init(){
        angles[0]=90;
        angles[1]= 0;
        angles[2] = 90;
        angles[3] = 90;
        angles[4] = 0;
        for(byte i =0; i<5; i++){
            value[i].setText(String.valueOf(angles[i]));
            ConnectionComms.sendAngle(i+1, angles[i]);
        }
    }

    public class UpButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int command = Integer.parseInt(e.getActionCommand());
            if(command<6) {
                if (angles[command - 1] < 180) {
                    angles[command - 1]++;
                    value[command - 1].setText(String.valueOf(angles[command - 1]));
                }
                ConnectionComms.sendAngle(command, angles[command - 1]);
            }else{
                angles[command-1]++;
                int currentstep = angles[command-1] - previosangle;
                previosangle = angles[command-1];
                value[command - 1].setText(String.valueOf(angles[command - 1]));
                ConnectionComms.sendAngle(command, currentstep);
            }
        }
    }

    public class DownButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int command = Integer.parseInt(e.getActionCommand());
            if(command<6) {
                if (angles[command - 1] > 0) {
                    angles[command - 1]--;
                    value[command - 1].setText(String.valueOf(angles[command - 1]));
                }
                ConnectionComms.sendAngle(command, angles[command - 1]);
            }else{
                angles[command-1]--;
                int currentstep = angles[command-1] - previosangle;
                previosangle = angles[command-1];
                value[command - 1].setText(String.valueOf(angles[command - 1]));
                ConnectionComms.sendAngle(command, currentstep);
            }
        }
    }

    public class ControllerHandler extends Thread {
        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(200);
                } catch (Exception e){
                    e.printStackTrace();
                }
                ControllerPanel.refresh();
                if(ControllerPanel.dUpButton()){
                    angles[0]++;
                    if(angles[0] > 180){
                        angles[0] = 180;
                    } else if (angles[0]<0) {
                        angles[0] = 0;
                    }
                    value[0].setText(String.valueOf(angles[0]));
                    ConnectionComms.sendAngle(1, angles[0]);
                }else if(ControllerPanel.dDownButton()){
                    angles[0]--;
                    if(angles[0] > 180){
                        angles[0] = 180;
                    } else if (angles[0]<0) {
                        angles[0] = 0;
                    }
                    value[0].setText(String.valueOf(angles[0]));
                    ConnectionComms.sendAngle(1, angles[0]);
                }

                if(ControllerPanel.dLeftButton()){
                    angles[1]++;
                    if(angles[1] > 180){
                        angles[1] = 180;
                    } else if (angles[1]<0) {
                        angles[1] = 0;
                    }
                    value[1].setText(String.valueOf(angles[1]));
                    ConnectionComms.sendAngle(2, angles[1]);
                } else if(ControllerPanel.dRightButton()){
                    angles[1]--;
                    if(angles[1] > 180){
                        angles[1] = 180;
                    } else if (angles[1]<0) {
                        angles[1] = 0;
                    }
                    value[1].setText(String.valueOf(angles[1]));
                    ConnectionComms.sendAngle(2, angles[1]);
                }

                if(ControllerPanel.xButtonJustPressed()){
                    closed = !closed;
                    if(closed){
                        value[4].setText("180");
                        angles[4] = 180;
                        ConnectionComms.sendAngle(5, angles[4]);
                    } else {
                        value[4].setText("0");
                        angles[4] = 0;
                        ConnectionComms.sendAngle(5, angles[4]);
                    }
                }

                if(ControllerPanel.getRightStickX()!=0){
                    angles[3]+=ControllerPanel.getRightStickX();
                    if(angles[3] > 180){
                        angles[3] = 180;
                    } else if (angles[3]<0) {
                        angles[3] = 0;
                    }
                    value[3].setText(String.valueOf(angles[3]));
                    ConnectionComms.sendAngle(4, angles[3]);
                }

                if(ControllerPanel.lbButton()){
                    angles[3]++;
                    if(angles[3] > 180){
                        angles[3] = 180;
                    } else if (angles[3]<0) {
                        angles[3] = 0;
                    }
                    value[3].setText(String.valueOf(angles[3]));
                    ConnectionComms.sendAngle(4, angles[3]);
                } else if(ControllerPanel.rbButton()){
                    angles[3]--;
                    if(angles[3] > 180){
                        angles[3] = 180;
                    } else if (angles[3]<0) {
                        angles[3] = 0;
                    }
                    value[3].setText(String.valueOf(angles[3]));
                    ConnectionComms.sendAngle(4, angles[3]);
                }

                if(ControllerPanel.yButton()){
                    angles[2]++;
                    if(angles[2] > 180){
                        angles[2] = 180;
                    } else if (angles[2]<0) {
                        angles[2] = 0;
                    }
                    value[2].setText(String.valueOf(angles[2]));
                    ConnectionComms.sendAngle(3, angles[2]);
                } else if(ControllerPanel.aButton()){
                    angles[2]--;
                    if(angles[2] > 180){
                        angles[2] = 180;
                    } else if (angles[2]<0) {
                        angles[2] = 0;
                    }
                    value[2].setText(String.valueOf(angles[2]));
                    ConnectionComms.sendAngle(3, angles[2]);
                }

                if(ControllerPanel.getLeftStickX()!=0){
                    angles[0]+=ControllerPanel.getLeftStickX();
                    if(angles[0] > 180){
                        angles[0] = 180;
                    } else if (angles[0]<0) {
                        angles[0] = 0;
                    }
                    value[0].setText(String.valueOf(angles[0]));
                    ConnectionComms.sendAngle(1, angles[0]);
                }

                if(ControllerPanel.getLeftStickY()!=0){
                    angles[1]+=ControllerPanel.getLeftStickY();
                    if(angles[1] > 180){
                        angles[1] = 180;
                    } else if (angles[1]<0) {
                        angles[1] = 0;
                    }
                    value[1].setText(String.valueOf(angles[1]));
                    ConnectionComms.sendAngle(2, angles[1]);
                }

                if(ControllerPanel.getRightStickY()!=0){
                    angles[2]+=ControllerPanel.getRightStickY()*(-1);
                    if(angles[2] > 180){
                        angles[2] = 180;
                    } else if (angles[2]<0) {
                        angles[2] = 0;
                    }
                    value[2].setText(String.valueOf(angles[2]));
                    ConnectionComms.sendAngle(3, angles[2]);
                }

                if(ControllerPanel.lTrigger() != 0 && ControllerPanel.rTrigger() < ControllerPanel.lTrigger()){
                    stepperPress = true;
                    int check = angles[5];
                    angles[5] = (200 * ControllerPanel.lTrigger())/10;
                    value[5].setText(String.valueOf(angles[5]));
                    if(angles[5] != check)
                        ConnectionComms.sendAngle(6,angles[5]);
                }else if(ControllerPanel.rTrigger() != 0 && ControllerPanel.lTrigger() < ControllerPanel.rTrigger() ){
                    stepperPress = true;
                    int check = angles[5];
                    angles[5] = (200 * ControllerPanel.rTrigger())/10 * -1;
                    value[5].setText(String.valueOf(angles[5]));
                    if(angles[5] != check)
                        ConnectionComms.sendAngle(6,angles[5]);
                }else if( (stepperPress == true && ControllerPanel.rTrigger() == 0 && ControllerPanel.lTrigger() == 0 ) || (ControllerPanel.lTrigger() == ControllerPanel.rTrigger() && ControllerPanel.lTrigger() != 0 && ControllerPanel.rTrigger() != 0)){
                    stepperPress = false;
                    int check = angles[5];
                    angles[5] = 0;
                    value[5].setText(String.valueOf(angles[5]));
                    if(angles[5] != check)
                        ConnectionComms.sendAngle(6,angles[5]);
                }


            }
        }
    }

}
