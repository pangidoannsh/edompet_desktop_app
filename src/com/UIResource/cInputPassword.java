package com.UIResource;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class cInputPassword extends JPanel {
    public JPasswordField field;
    public JLabel label;
    public cLine line;

    public cInputPassword(String text, int x, int y, int width, int heigth){
        super(null);
        setBounds(x,y,width,heigth);
//        setBackground(cColor.BLUE);

        label = new JLabel(text);
        label.setBounds(0,0,width,30);
        label.setFont(cFont.PoppinsRegular(18));
        label.setForeground(cColor.WHITE);


        field = new JPasswordField("Masukkan Password");
        char charPass = field.getEchoChar();//to accommodate shape of echoChar
        field.setEchoChar((char)0);
        field.setCaretColor(cColor.WHITE);
        field.setBounds(0,30,width,30);
        field.setFont(cFont.PoppinsRegular(16));
        field.setForeground(cColor.WHITE20);// at first, the color of text is White20, if user enter the text and then color change to White60
        field.setBorder(null);
        field.setOpaque(false);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                field.setFont(cFont.PoppinsRegular(18));
                field.setForeground(cColor.WHITE60);
                if(field.getText().equals("Masukkan Password")){
                    field.setText("");
                    field.setEchoChar(charPass);
                }

            }
            public void focusLost(FocusEvent focusEvent){
                if(field.getText().equals("")){
                    field.setFont(cFont.PoppinsRegular(16));
                    field.setForeground(cColor.WHITE20);
                    field.setText("Masukkan Password");
                    field.setEchoChar((char)0);
                }

            }
        });

        line = new cLine(0,60,width,1, cColor.WHITE20);

        setOpaque(false);

        add(label);
        add(field);
        add(line);
    }
    public void resetField(){
        field.setFont(cFont.PoppinsRegular(16));
        field.setForeground(cColor.WHITE20);
        field.setText("Masukkan Password");
        field.setEchoChar((char)0);
    }
}
