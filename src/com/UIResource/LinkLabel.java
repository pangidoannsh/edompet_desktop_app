package com.UIResource;

import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LinkLabel extends JLabel{
    private int size;
    public LinkLabel(String text, int x,int y, int width, int height, int size){
        super();
        setText(text);
        this.size = size;
        setBounds(x,y,width,height);
        setForeground(cColor.WHITE20);
        setFont(cFont.PoppinsBold(size));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setForeground(cColor.WHITE60);
            }
            public void mouseExited(MouseEvent e){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setFont(cFont.PoppinsBold(size));
                setForeground(cColor.WHITE20);
            }
            public void mouseClicked(MouseEvent mouseEvent) {

            }
            public void mousePressed(MouseEvent mouseEvent){
                setFont(cFont.PoppinsBold(size-1));
            }

            public void mouseReleased(MouseEvent mouseEvent){
                setFont(cFont.PoppinsBold(size));
            }
        });
    }
    public LinkLabel(String text, int x,int y, int width, int height, Font font){
        super();
        setText(text);
        setBounds(x,y,width,height);
        setForeground(cColor.WHITE60);
        setFont(font);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setForeground(cColor.WHITE80);
            }
            public void mouseExited(MouseEvent e){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setForeground(cColor.WHITE60);
                setFont(font);
            }
        });
    }
}
