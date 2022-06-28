package com.Program;

public class DataRegister {
    public static String name;
    public static String phoneNum;
    public static String password;
    public static String id;

    public static void enterData(String nama,String nomor,String inputID, String inputPass){
        name = nama;
        phoneNum = nomor;
        id = inputID;
        password = inputPass;
    }
}
