package com.UIResource;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class cInputJumlahUang extends JPanel{
    public JTextField field;
    public cLabel label;
    private cLabel rp;
    private cLabel errorLabel;
    public boolean isError;

    public String inputMoney;
    private long money;
    public cInputJumlahUang(String text,int y){
        super();
        setOpaque(false);
        setLayout(null);
        setBounds(40,y,400,105);

        label = new cLabel(text,0,0,110,30,cColor.WHITE60,cFont.PoppinsRegular(16));

        field = new JTextField("Rp0");
        field.setBounds(50,30,400,60);
        field.setFont(cFont.PoppinsRegular(32));
        field.setForeground(cColor.WHITE20);
        field.setOpaque(false);
        field.setBorder(null);

        rp = new cLabel("",0,30,50,60,cColor.WHITE,cFont.PoppinsRegular(36));
        errorLabel = new cLabel("",10,80,350,30,cColor.ERROR,cFont.Roboto(17));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                field.setFont(cFont.PoppinsRegular(36));
                field.setForeground(cColor.WHITE);
                if(field.getText().equals("Rp0")){
                    rp.setText("Rp");
                    field.setText("");
                }
                if(!field.getText().equals("")){
                    field.setText(com.Tools.WritingManager.convertToNumeric(field.getText()));
                }
            }
            public void focusLost(FocusEvent focusEvent){
                field.setFont(cFont.PoppinsRegular(36));
                rp.setText("Rp");
                if(field.getText().equals("")){
                    field.setFont(cFont.PoppinsRegular(32));
                    field.setForeground(cColor.WHITE20);
                    rp.setText("");
                    field.setText("Rp0");
                }
                if(!field.getText().matches("[0-9]*") || field.getText().matches("0[a-zA-Z_0-9]")){
                    setTextError("Kesalahan Dalam Input !!!");
                }else{
                    setTextError("");
                    if(field.getText().equals("0")){
                        setTextError("Jumlah minimal pengiriman adalah Rp 1");
                    }else{
                        money = Long.parseLong(field.getText());
                        field.setText(com.Tools.WritingManager.moneyWriting(money));
                    }

                }
                inputMoney = money + "";
            }
        });

        add(label);
        add(rp);
        add(errorLabel);
        add(field);
    }

    public cInputJumlahUang(int y,String labelText, long minimal){

        super();
        isError = true;
        setOpaque(false);
        setLayout(null);
        setBounds(40,y,400,120);

        label = new cLabel(labelText,0,0,400,30,cColor.WHITE60,cFont.PoppinsRegular(16));

        field = new JTextField("Rp0");
        field.setBounds(50,40,400,60);
        field.setFont(cFont.PoppinsRegular(32));
        field.setForeground(cColor.WHITE20);
        field.setOpaque(false);
        field.setBorder(null);

        rp = new cLabel("",0,40,50,60,cColor.WHITE,cFont.PoppinsRegular(36));
        errorLabel = new cLabel("",10,90,350,30,cColor.ERROR,cFont.Roboto(17));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                field.setFont(cFont.PoppinsRegular(36));
                field.setForeground(cColor.WHITE);
                if(field.getText().equals("Rp0")){
                    rp.setText("Rp");
                    field.setText("");
                }
                if(!field.getText().equals("")){
                    field.setText(com.Tools.WritingManager.convertToNumeric(field.getText()));
                }

            }
            public void focusLost(FocusEvent focusEvent){
                field.setFont(cFont.PoppinsRegular(36));
                rp.setText("Rp");
                if(field.getText().equals("")){
                    field.setFont(cFont.PoppinsRegular(32));
                    field.setForeground(cColor.WHITE20);
                    rp.setText("");
                    field.setText("Rp0");
                }
                if(!field.getText().matches("[0-9]*") || field.getText().matches("0[a-zA-Z_0-9]")){
                    setTextError("Kesalahan Dalam Input !!!");
                    isError = true;

                }else{
                    setTextError("");

                    if(Long.parseLong(field.getText()) < minimal){
                        setTextError("Jumlah minimal Penarikan adalah Rp 50.000");
                        isError = true;
                    }
                    else{
                        isError = false;
                    }
                    money = Long.parseLong(field.getText());
                    field.setText(com.Tools.WritingManager.moneyWriting(money));

                }
                inputMoney = money + "";

            }
        });

        add(label);
        add(rp);
        add(errorLabel);
        add(field);
    }

    public void setTextError(String text){
        errorLabel.setText(text);
    }

}
