package com.UIResource;

import javax.swing.*;

public class cSmallLabel extends JLabel {
    public cSmallLabel(String text, int x,int y, int width, int heigth, int size){
        super(text);
        setFont(cFont.PoppinsRegular(size));
        setForeground(cColor.WHITE20);
        setBounds(x,y,width,heigth);
    }
}
