package com.Tools;

public class BankManager {
    private static int length = 5;
    public static final String[] bankList = {
            "BANK BCA",
            "BANK BNI",
            "BANK BRI",
            "BANK MANDIRI",
            "BANK MUAMALAT"
    };
    private static final String[] kodeBank = {
            "014",
            "009",
            "002",
            "008",
            "147"
    };
    public static String getCodeBank(String bankName){
        String kode = "";
        if(getIndex(bankName) != length){
            kode = kodeBank[getIndex(bankName)];
        }
        return kode;
    }

    public static String getNameBank(String bankCode){
        String name = "";
        if(getIndexFromCode(bankCode) != length){
            name = bankList[getIndexFromCode(bankCode)];
        }
        return name;
    }
    private static int getIndex(String bankName){
        int i = 0;
        for(i=0; i<length; i++){
            if(bankName.equals(bankList[i])){
                break;
            }
        }
        return i;
    }

    private static int getIndexFromCode(String bankCode){
        int i;
        for(i=0; i<length; i++){
            if(bankCode.equals(kodeBank[i])){
                break;
            }
        }
        return i;
    }
}
