package com.Tools;

import javax.swing.*;

public class PopUp {

    public static void information(String msg){
        JOptionPane.showMessageDialog(null,msg,"Information",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorMessage(String message, String title){
        JOptionPane.showMessageDialog(null,message,title, JOptionPane.ERROR_MESSAGE);
    }
    public static String input(String msg){
        String input = JOptionPane.showInputDialog(msg);
        return input;
    }

    public static boolean YesOrNO(String msg){
        int point = JOptionPane.showOptionDialog(null, msg,"ATM",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(point == 0) return true;
        else return false;
    }

    public static boolean confirmation(String message){
        boolean isOk = false;
        Object[] option = {"OK","CANCEL"};
        int confirm = JOptionPane.showOptionDialog(null,
                message,"Warning",
                JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                null,option,option[0]);
        if(confirm == 0){
            isOk = true;
        }
        return  isOk;
    }
}
