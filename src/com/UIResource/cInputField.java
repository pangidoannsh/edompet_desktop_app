package com.UIResource;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class cInputField extends JPanel {
    public JTextField field;
    public JLabel label;
    public cLine line;
    public String intruction = "Masukkan ";

    public cInputField(String text, int x,int y, int width, int height){
        super(null);
        setBounds(x,y,width,height);
        setBackground(cColor.BLUE);

        label = new JLabel(text);
        label.setBounds(0,0,width,30);
        label.setFont(cFont.PoppinsMed(18));
        label.setForeground(cColor.WHITE);

        this.intruction += text;
        field = new JTextField(intruction);
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
                if(field.getText().equals(intruction)){
                    field.setText("");
                }
            }
            public void focusLost(FocusEvent focusEvent){
                field.setFont(cFont.PoppinsRegular(17));
                if(field.getText().equals("")){
                    field.setFont(cFont.PoppinsRegular(16));
                    field.setForeground(cColor.WHITE20);
                    field.setText(intruction);
                }
            }
        });

        line = new cLine(0,60,width,1, cColor.WHITE20);

        setOpaque(false);

        add(label);
        add(field);
        add(line);
    }
    public cInputField(int x,int y, int width, int height,String statement){
        super(null);
        setBounds(x,y,width,height);
        setBackground(cColor.BLUE);

        this.intruction = statement;

        field = new JTextField(intruction);
        field.setCaretColor(cColor.WHITE);
        field.setBounds(0,0,width,27);
        field.setFont(cFont.PoppinsRegular(16));
        field.setForeground(cColor.WHITE20);// at first, the color of text is White20, if user enter the text and then color change to White60
        field.setBorder(null);
        field.setOpaque(false);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                field.setFont(cFont.PoppinsRegular(18));
                field.setForeground(cColor.WHITE60);
                if(field.getText().equals(intruction)){
                    field.setText("");
                }
            }
            public void focusLost(FocusEvent focusEvent){
                field.setFont(cFont.PoppinsRegular(17));
                if(field.getText().equals("")){
                    field.setFont(cFont.PoppinsRegular(16));
                    field.setForeground(cColor.WHITE20);
                    field.setText(intruction);

                }

            }
        });

        line = new cLine(0,30,width,1, cColor.WHITE20);

        setOpaque(false);

        add(field);
        add(line);
    }
    public cInputField(String text, int x,int y, int width, int height,boolean dif){
        super(null);
        setBounds(x,y,width,height);
        setBackground(cColor.BLUE);

        label = new JLabel(text);
        label.setBounds(0,0,width,30);
        label.setFont(cFont.PoppinsMed(18));
        label.setForeground(cColor.WHITE);

        this.intruction += text;
        field = new JTextField(intruction);
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
                if(field.getText().equals(intruction)){
                    field.setText("");
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
        field.setText(intruction);
    }
}
