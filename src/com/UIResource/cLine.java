package com.UIResource;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;

public class cLine extends JPanel {
    public cLine(int x, int y, int width, int thicknes, Color color){
        super();
        setBounds(x,y,width,thicknes);
        setBackground(color);
        add(new JSeparator());

    }

}
