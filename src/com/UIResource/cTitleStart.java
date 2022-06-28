package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cTitleStart extends JPanel {
    public JLabel title;
    public JPanel bottomLine;
    private boolean isActived;
    public cTitleStart(String textLabel, int x, int y, int width, int heigth){
        super(null);
        setOpaque(false);
        setBounds(x,y,width,heigth);


        title = new JLabel(textLabel);
        title.setBounds(0,0,width,heigth);
        title.setOpaque(false);
        title.setForeground(cColor.WHITE20);
        title.setFont(cFont.PoppinsRegular(32));

        bottomLine = new JPanel();
        bottomLine.setBounds(0,60,width,2);
        bottomLine.setBackground(cColor.BLUE);
        bottomLine.setOpaque(false);
//        if(isSelected){
//            title.setForeground(cColor.WHITE);
//            add(new Line(0,59,width,2,cColor.BLUE));
//        }

        add(title);
        add(bottomLine);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public void setActive(){
        isActived = true;
        title.setForeground(cColor.WHITE);
        bottomLine.setOpaque(true);
    }

    public void setNotActive(){
        isActived = false;
        title.setForeground(cColor.WHITE20);
        bottomLine.setOpaque(false);

    }
}
