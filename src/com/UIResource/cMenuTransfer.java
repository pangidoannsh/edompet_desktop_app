package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cMenuTransfer extends cRoundedPanel{

    private cLabel label;
    private JLabel icon;
    public cMenuTransfer(String urlIcon,String text, int y){
        super(10);
        setBounds(40,y,306,89);
        setBackground(cColor.DARK_GRAY);
        setLayout(null);

        label = new cLabel(text,77,27,250,40,cColor.WHITE,cFont.PoppinsRegular(24));

        icon = new JLabel(new ImageIcon(urlIcon),JLabel.CENTER);
        icon.setBounds(27,27,32,32);

        add(label);
        add(icon);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent me){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }
}
