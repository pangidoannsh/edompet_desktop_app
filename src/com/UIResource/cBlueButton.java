package com.UIResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cBlueButton extends JButton {
    private int radius = 2;
    private  int stroke = 0;
    private boolean isAddBorder = false;

    public JLabel label;

    public cBlueButton(int radius, String textLabel, int x, int y, int width, int height, Font fontLabel,
                       boolean isAddBorder){
        super();
        this.radius = radius*2;
        this.isAddBorder = isAddBorder;
        setBounds(x,y,width,height);
        setLayout(null);
        setOpaque(false);
        setBorder(null);
        setBackground(cColor.BLUE);

        label = new JLabel(textLabel);
        label.setSize(width,height);
        label.setFont(fontLabel);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(cColor.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent){
                if(isAddBorder){
                    setBorder(new LineBorder(cColor.BRIGHT_BLUE,2,true));
                }

            }
            public void mouseExited(MouseEvent mouseEvent){
                setBorder(null);
            }

            public void mousePressed(MouseEvent mouseEvent) {
                //setBorder(null);
                setBackground(cColor.DARK_BLUE);
            }

            public void mouseClicked(MouseEvent mouseEvent) {

            }

            public void mouseReleased(MouseEvent mouseEvent) {
                setBorder(null);
                setBackground(cColor.BLUE);
            }
        });
        add(label);
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
        graphics.setColor(cColor.BRIGHT_BLUE);
        //graphics.setStroke(new BasicStroke(stroke));
        //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }

}
