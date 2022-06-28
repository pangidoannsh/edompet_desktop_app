package com.Templates;

import com.UIResource.*;

import javax.swing.*;

public abstract class StartFrame extends JFrame{

    public JPanel leftContent;
    public cGradientPanel content;

    public StartFrame(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050,765);
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);

        leftContent = new JPanel();
        leftContent.setBounds(0,0,525,765);
        leftContent.setBackground(cColor.BLUE);

        content = new cGradientPanel(525,0,525,765, cColor.GRAY, cColor.BLACK);

        add(leftContent);
        add(new cSmallLabel("or",700,140,30,30,20));
        add(new CloseLabel(980,16));
        add(content);


    }
}
