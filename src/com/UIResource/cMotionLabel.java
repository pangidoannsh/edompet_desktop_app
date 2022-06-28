package com.UIResource;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class cMotionLabel extends cLabel{
    public static final int moveHoriz = 0;
    public static final int moveVerti = 1;

    public cMotionLabel(String text, int x, int y, int width, int heigth, Color color, Font font,int direction){
        super(text,x,y,width,heigth,color,font);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent){
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                if(direction == moveHoriz){
                    setLocation(getX()+2,getY());
                }else{
                    setLocation(getX(),getY()-1);
                }
            }
            public void mouseExited(MouseEvent me){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if(direction == moveHoriz){
                    setLocation(getX()-2,getY());
                }else{
                    setLocation(getX(),getY()+1);
                }
            }
        });
    }
}
