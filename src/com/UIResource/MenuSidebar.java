package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuSidebar extends JPanel {
    public JLabel label;
    public JLabel icon;
    public JPanel verLine;

    public boolean isActived = false;

    public MenuSidebar(String urlIcon, String textLabel, int y){
        super();
        setBounds(0,y,230,45);
        setLayout(null);

        verLine = new JPanel();
        verLine.setBounds(0,0,5,getHeight());


        icon = new JLabel(new ImageIcon(urlIcon),JLabel.CENTER);
        icon.setBounds(40,7,24,24);

        label = new JLabel(textLabel);
        label.setFont(cFont.PoppinsMed(18));
        label.setBounds(70,9, 120,30);

        setBackground(cColor.DARK_GRAY);
        label.setForeground(cColor.WHITE60);

        verLine.setBackground(getBackground());//get color from main panel

        add(icon);
        add(label);
        add(verLine);
//        addMouseListener(sidebarNotActived);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                //set location
                label.setLocation(label.getX()+2, label.getY());
                icon.setLocation(icon.getX()+2, icon.getY());
                //coloring vertikal line
                if(isActived){
                    verLine.setBackground(cColor.WHITE);
                }else{
                    verLine.setBackground(cColor.BLUE);
                }

            }
            public void mouseExited(MouseEvent me){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                //set location
                label.setLocation(label.getX()-2, label.getY());
                icon.setLocation(icon.getX()-2, icon.getY());
                //coloring vertikal line
                verLine.setBackground(getBackground());
            }
        });

    }

    public void setActive(){
        isActived = true;
        setBackground(cColor.BLUE);
        label.setForeground(cColor.WHITE);
        verLine.setBackground(cColor.WHITE);
    }

    public void setNotActive(){
        isActived = false;
        setBackground(cColor.DARK_GRAY);
        label.setForeground(cColor.WHITE60);
        verLine.setBackground(cColor.DARK_GRAY);
//        try{
//            removeMouseListener(sidebarActived);
//        }catch (Exception e){ }
//        addMouseListener(sidebarNotActived);
    }


}
