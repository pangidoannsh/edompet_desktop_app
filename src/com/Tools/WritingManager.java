package com.Tools;

import java.util.StringTokenizer;

public class WritingManager {
    public static String moneyWriting(long money){
        String moneyStr = "";
        char[] charMoney = ("" + money).toCharArray();
        int j=1;
        for(int i=charMoney.length-1; i >=0; i--){
            moneyStr += charMoney[i];
            if(j % 3 == 0 && i != 0){
                moneyStr += ".";
            }
            j++;
        }
        char[] revers = (moneyStr).toCharArray();
        moneyStr = "";
        for(int i=revers.length-1; i >=0; i--){
            moneyStr += revers[i];
        }
        return moneyStr;
    }

    public static String convertToNumeric(String text){
        String money = "";
        char[] charText = text.toCharArray();
        for(int i=0; i<charText.length; i++){
            if(charText[i] == '.'){
                continue;
            }
            money += charText[i];
        }
        return money;
    }
}
