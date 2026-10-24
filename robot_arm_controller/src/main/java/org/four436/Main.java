package org.four436 ;                            

import javax.swing.JFrame;



public class Main extends JFrame {
    static public JFrame mainFrame; 
    static ConnectionPanel connectionPanel = new ConnectionPanel();
    static ControlPanel controlPanel;

    public Main(){
        setSize(720,405);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(connectionPanel);
        setResizable(false);
        revalidate();
        repaint();
    }

    static public void changePanel(){
        mainFrame.remove(connectionPanel);
        controlPanel = new ControlPanel();
        mainFrame.add(controlPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void main(String[] args) {
        ControllerPanel.init();
        mainFrame = new Main();

    }
}