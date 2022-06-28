package com.UIResource;

import javax.swing.JPanel;
import java.awt.*;

public class cRoundedPanel extends JPanel {
    private int radius;
    private int stroke = 0;
    private Color borderColor;

    public cRoundedPanel(int radius){
        super();
        this.radius = radius*2;
        setOpaque(false);

    }

    public cRoundedPanel(int radius, int stroke, Color borderColor){
        super();
        this.borderColor = borderColor;
        this.radius = radius*2;
        this.stroke = stroke;
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(radius,radius); //Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(borderColor);
        graphics.setStroke(new BasicStroke(stroke));
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }

    public void setBorderColor(Color color){
        borderColor = color;
    }

}
