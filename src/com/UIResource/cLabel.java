package com.UIResource;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class cLabel extends JLabel{
    public cLabel(String text, int x, int y, int width, int heigth, Color color, Font font){
        super(text);
        setBounds(x,y,width,heigth);
        setForeground(color);
        setFont(font);
    }
}
