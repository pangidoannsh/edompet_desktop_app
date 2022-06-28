package com.UIResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseButton extends JPanel {
    private JLabel icon;
    public CloseButton(int x, Color color){
        super();
        setLayout(null);
        setBounds(x,0,44,32);
        setBackground(color);
        setBorder(null);

        icon = new JLabel(new ImageIcon("assets//icon//closeIcon.PNG"));
        icon.setBounds(0,0,44,32);
        icon.setHorizontalAlignment(JLabel.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent){
                setBackground(cColor.RED);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setBackground(color);
            }

            public void mouseClicked(MouseEvent mouseEvent) {
                setBackground(cColor.DARK_RED);
                Object[] option = {"OK","CANCEL"};
                int confirm = JOptionPane.showOptionDialog(null,
                        "Click OK to Close App","Warning",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                        null,option,option[0]);
                if(confirm == 0){
                    System.exit(0);
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                setBackground(cColor.DARK_RED);
            }
        });
        add(icon);
    }


}
