package org.four436;

import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.net.Socket;

public class ConnectionComms {
    static Socket comms;
    static PrintWriter printer;
    static public boolean startComms(String ip){
        try{
            comms = new Socket(ip, 9541);
            printer = new PrintWriter(comms.getOutputStream(), true);
            return true;
        }catch(Exception e) {
        }
        return false;
    }

    static public void stopComms(){
        try{
            comms.close();
        }catch(Exception e){
        }
    }

    static public boolean sendAngle(int servo, int angle){
        printer.println(servo);
        printer.println(angle);
        return false;
    }

}
