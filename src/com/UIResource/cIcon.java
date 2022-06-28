package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cIcon extends JLabel {
    public cIcon(String url,int x, int y, int width, int height){
        super(new ImageIcon(url),JLabel.CENTER);
        setBounds(x,y,width,height);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
