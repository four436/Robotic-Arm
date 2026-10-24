package org.four436;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionPanel extends JPanel {
    private JTextField ipField = new JTextField();
    private JButton connectionButton = new JButton("Connect");

    public ConnectionPanel(){
        setLayout(new GridLayout(2,1));
        paintInputTextField();
        paintConnectionButton();
        JPanel ipPanel = new JPanel(new FlowLayout());
        ipPanel.add(ipField);
        ipPanel.setBorder(new EmptyBorder(50,10,10,10));
        add(ipPanel);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(connectionButton);
        add(buttonPanel);
    }

    private void paintConnectionButton(){
        connectionButton.addActionListener(new ConnectionButtonListener());
    }

    private void paintInputTextField(){
        ipField.setBorder(new LineBorder(Color.BLACK,3, true));
        ipField.setHorizontalAlignment(JTextField.CENTER);
        ipField.setEditable(true);
        ipField.setFont(new Font("Arial", Font.PLAIN, 16));
        ipField.setToolTipText( "Enter IP Address");
        ipField.setPreferredSize(new Dimension(150, 40));
    }

   private class ConnectionButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button Pressed");
            String text = ipField.getText();
            System.out.println(text);
            if(ConnectionComms.startComms(text)){
                System.out.println("Connection Successful");
                Main.changePanel();
            }else{
                System.out.println("Connection Failed");
            }
        }
    }

}
