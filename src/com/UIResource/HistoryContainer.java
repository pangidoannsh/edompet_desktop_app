package com.UIResource;

import com.Tools.WritingManager;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoryContainer extends JPanel {
    private cIcon iconTransaction;
    private cLabel typeTransaction;
    private cLabel dateTransaction;
    private cLabel moneyLabel;

    private String urlIcon = "assets//icon//";

    public HistoryContainer(String type, String date, long money){
        super();
        setLayout(null);
        setBackground(cColor.DARK_GRAY);

        urlIcon += type + ".PNG";

        iconTransaction = new cIcon(urlIcon,5,0, 24,24);
        typeTransaction = new cLabel(type,40,0,270,35,cColor.WHITE,cFont.PoppinsMed(20));
        dateTransaction = new cLabel(date,5,35,140,25,cColor.WHITE80,cFont.PoppinsMed(14));
        char[] moneyChar = WritingManager.moneyWriting(money).toCharArray();
        int length = moneyChar.length;
        String moneyStr;
        int defaultKordinat = 590;

        if(type.equals("Kirim Uang") || type.equals("Tarik Uang")){
            moneyStr = "-Rp " + WritingManager.moneyWriting(money);
            defaultKordinat -= 10;
        }
        else{
            moneyStr = "Rp " + WritingManager.moneyWriting(money);
        }
        moneyLabel = new cLabel(moneyStr,defaultKordinat - (length * 10),12,300,40,cColor.WHITE,cFont.PoppinsMed(24));

        JPanel line = new JPanel();
        line.setBounds(0,66,683,2);

        add(iconTransaction);
        add(typeTransaction);
        add(dateTransaction);
        add(moneyLabel);
        add(new cLine(0,66,683,2,cColor.WHITE20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent mouseEvent){
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
}
