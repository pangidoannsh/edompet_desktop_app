package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuContent extends JPanel{
    public JLabel label;
    public JLabel label2;
    public JLabel icon;

    private int width = 80, height = 80;

    public MenuContent(String urlLocationIcon, String textLabel, int x, int y){
        super();
        setBounds(x,y,width,height);
        setLayout(null);
        setOpaque(false);

        icon = new JLabel(new ImageIcon(urlLocationIcon),JLabel.CENTER);
        icon.setBounds(0,8,width,40);


        label = new JLabel(textLabel);
        label.setFont(cFont.PoppinsMed(12));
        label.setForeground(cColor.WHITE);
        label.setBounds(0,48, width,18);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        mouseListener();

        add(icon);
        add(label);
    }

    public MenuContent(String urlLocationIcon, String textLabel1, String textLabel2, int x, int y){
        super();
        setBounds(x,y,width,height);
        setLayout(null);
        setOpaque(false);

        icon = new JLabel(new ImageIcon(urlLocationIcon),JLabel.CENTER);
        icon.setBounds(0,8,width,40);

        label = new JLabel(textLabel1);
        label.setFont(cFont.PoppinsMed(12));
        label.setForeground(cColor.WHITE);
        label.setBounds(0,48, width,18);
        label.setHorizontalAlignment(JLabel.CENTER);

        label2 = new JLabel(textLabel2);
        label2.setFont(cFont.PoppinsMed(12));
        label2.setForeground(cColor.WHITE);
        label2.setBounds(0,63, width,18);
        label2.setHorizontalAlignment(JLabel.CENTER);

        mouseListener();

        add(icon);
        add(label);
        add(label2);
    }

    public void mouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent){
                setLocation(getX(),getY()-2);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent mouseEvent){
                setLocation(getX(),getY()+2);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
