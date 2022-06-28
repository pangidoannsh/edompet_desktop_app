package com.UIResource;

import javax.swing.*;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseLabel extends JLabel {
    private int size;
    public CloseLabel(int x,int size){
        super("Close");
        setBounds(x,10,100,40);
        this.size = size;
        setFont(cFont.PoppinsBold(size));
        setForeground(cColor.WHITE60);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setForeground(cColor.WHITE);
                setLocation(getX(),getY()-2);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e){
                setForeground(cColor.WHITE60);
                setLocation(getX(),getY()+2);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mouseClicked(MouseEvent mouseEvent) {
                Object[] option = {"OK","CANCEL"};
                int confirm = JOptionPane.showOptionDialog(null,
                        "Click OK to Close App","Warning",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                        null,option,option[0]);
                if(confirm == 0){
                    System.exit(0);
                }
            }
            public void mousePressed(MouseEvent mouseEvent){
                setFont(cFont.PoppinsBold(size-1));
            }

            public void mouseReleased(MouseEvent mouseEvent){
                setFont(cFont.PoppinsBold(size));
            }
        });
    }
}
