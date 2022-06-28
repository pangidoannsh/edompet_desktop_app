package com.UIResource;

import javax.swing.JPanel;
import java.awt.*;

public class cGradientPanel extends JPanel {

    public cGradientPanel(int x, int y, int width, int height, Color color1, Color color2){
        super(null);
        setBounds(x,y,width,height);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(55,74,97);
        Color color2 = new Color(34,46,63);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
