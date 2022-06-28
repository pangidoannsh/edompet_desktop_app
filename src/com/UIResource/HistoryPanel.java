package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HistoryPanel extends JPanel {
    private cLabel labelEmpty;
    public HistoryPanel(ArrayList<HistoryContainer> hc){
        super();
        setLayout(null);

        setBackground(cColor.DARK_GRAY);
        int locY = 20;

        for(int i=hc.size()-1; i>=0;i--){
            hc.get(i).setBounds(20,locY,683,70);
            add(hc.get(i));
            locY += 75;
        }


        setPreferredSize(new Dimension(723,locY));

    }
}
