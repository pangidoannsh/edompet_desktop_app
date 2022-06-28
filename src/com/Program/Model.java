package com.Program;

import com.Tools.PopUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Model {
    private static final String URL = "jdbc:mysql://localhost:3306/edompet_db?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection conn;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    //get connected to database
    private static void connection(){
        try{
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = conn.createStatement();

        }catch (Exception e){
            System.out.println("Error in connection database");
            System.exit(0);
        }
    }

    public static boolean accountIsLogged(){
        connection();
        boolean isLogin = false;
        try{
            resultSet = statement.executeQuery("SELECT idLogin FROM isLogin");
            if(resultSet.next()){
                Controller.userID = resultSet.getString("idLogin");
                isLogin = true;
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the ID from Database\n" + e,"Error import Database");
        }
        return isLogin;
    }
    public static void enterDataLogin(){
        connection();
        String query = "INSERT INTO isLogin VALUES ('" + Controller.userID + "')";
        try{
            statement.executeUpdate(query);
        }catch (Exception e){
            PopUp.errorMessage("Gagal saat Log In\n" + e,"Error import Database");
        }
    }
    public static void deleteDataLogin(){
        connection();
        String query = "DELETE FROM isLogin WHERE idLogin = '" + Controller.userID + "'";
        try{
            statement.executeUpdate(query);
        }catch (Exception e){
            PopUp.errorMessage("Gagal saat Log Out\n" + e,"Error import Database");
        }
    }
    public static ArrayList<String> getAll_ID(){
        ArrayList<String> allID = new ArrayList<>();
        connection();
        try{
            resultSet = statement.executeQuery("SELECT id FROM userdata");
            while(resultSet.next()){
                allID.add(resultSet.getString("id"));
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the ID from Database\n" + e,"Error import Database");
        }
        return allID;
    }
    public static ArrayList<String> getAllPhoneNum(){
        ArrayList<String> phoneNum = new ArrayList<>();
        connection();
        try{
            resultSet = statement.executeQuery("SELECT phoneNum FROM userdata");
            while(resultSet.next()){
                phoneNum.add(resultSet.getString("phoneNum"));
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the phoneNum from Database\n" + e,"Error import Database");
        }
        return phoneNum;
    }

    public static void insertData(String id, String nama, String rekeningNum, String phoneNum, String password, String bankCode){
        connection();
        String query = "INSERT INTO userdata VALUES('" + id + "','" + nama + "','" + rekeningNum + "','" + phoneNum + "'," + 0 +
                        ",'" + password + "','" + bankCode + "')";
        try{
            statement.executeUpdate(query);
            Controller.showInfoRegisterSukses();
        }catch (Exception e){
            PopUp.errorMessage("Error when insert data to database","Database Error");
        }
    }

    public static String getID_PhoneNum(String phoneNum){
        String id = "";
        connection();
        try{
            resultSet = statement.executeQuery("SELECT id FROM userdata WHERE phoneNum = '" + phoneNum + "'");
            if(resultSet.next()){
                id = resultSet.getString("id");
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the ID from Database\n" + e,"Error import Database");
        }
        return id;
    }

    public static String getName_rekeningNum(String rekeningNum){
        String name = "";
        connection();
        try{
            resultSet = statement.executeQuery("SELECT name FROM userdata WHERE rekeningNum = '" + rekeningNum + "'");
            if(resultSet.next()){
                name = resultSet.getString("name");
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the ID from Database\n" + e,"Error import Database");
        }
        return name;
    }

    public static boolean phoneNumIsExist(String phoneNum){
        connection();
        boolean check = false;
        try{
            resultSet = statement.executeQuery("SELECT phoneNum FROM userdata");
            while (resultSet.next()){
                if(phoneNum.equals(resultSet.getString("phoneNum"))){
                    check = true;
                    break;
                }
                else{
                    check = false;
                }
            }
        }catch (Exception e){
            PopUp.errorMessage("Error phoneNum db","Error Database");
        }
        return check;
    }
    public static boolean rekeningIsExist(String numRek, String bankCode){
        connection();
        boolean check = false;
        try{
            resultSet = statement.executeQuery("SELECT rekeningNum,bankCode FROM userdata");
            while(resultSet.next()){
                String db_numRek = resultSet.getString("rekeningNum");
                String db_bankCode = resultSet.getString("bankCode");
                if(numRek.equals(db_numRek)){
                    if(bankCode.equals(db_bankCode)){
                        check = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            //nothing
        }
        return check;
    }

    public static String getUserPass(String ID){
        String pass = null;
        connection();
        try{
            resultSet = statement.executeQuery("SELECT password FROM userdata WHERE id = '" + ID + "'");
            if(resultSet.next()){
                pass = resultSet.getString("password");
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import the PasswordD from Database\n" + e,"Error import Database");
        }
        return pass;
    }

    public static void loadUserData(){
        connection();
        try{
            resultSet = statement.executeQuery("SELECT * FROM userdata WHERE id = '" + Controller.userID + "'" );
            if(resultSet.next()){
                Controller.userName = resultSet.getString("name");
                Controller.userBalance = resultSet.getLong("userBalance");
                Controller.userPhoneNum = resultSet.getString("phoneNum");
            }
        }catch (Exception e){
            PopUp.errorMessage("Error when import userdata from Database\n" + e,"Error import Database");
        }
    }

    //get name
    public static String getUserName(){
        String userName = null;
        if(Controller.userID != null){
            try{
                resultSet = statement.executeQuery("SELECT name FROM userdata WHERE id = '" + Controller.userID + "'");
                if(resultSet.next()){
                    userName = resultSet.getString("name");
                }
            }catch (Exception e){
                PopUp.errorMessage("Error when import the User Name from Database\n" + e,"Error import Database");
            }
        }

        return userName;
    }
    public static String getReceiverName(String phoneNum){
        String receiverName = null;
        if(phoneNum != null){
            try{
                resultSet = statement.executeQuery("SELECT name FROM userdata WHERE phoneNum = '" + phoneNum + "'");
                if(resultSet.next()){
                    receiverName = resultSet.getString("name");
                }
            }catch (Exception e){
                PopUp.errorMessage("Error when import the User Name from Database\n" + e,"Error import Database");
            }
        }

        return receiverName;
    }

    //get balance
    public static long getUserBalance(){
        connection();
        long balance = 0;
        if(Controller.userID != null){
            try{
                resultSet = statement.executeQuery("SELECT userBalance FROM userdata WHERE id = '" + Controller.userID + "'");
                if(resultSet.next()){
                    balance = resultSet.getLong("userBalance");

                }
            }catch (Exception e){
                PopUp.errorMessage("Error when import the User Balance from Database\n" + e,"Error import Database");
            }
        }

        return balance;
    }
    public static long getReceiverBalance(String phoneNum){
        long balance = 0;
        if(Controller.userID != null){
            try{
                resultSet = statement.executeQuery("SELECT userBalance FROM userdata WHERE phoneNum = '" + phoneNum + "'");
                if(resultSet.next()){
                    balance = resultSet.getLong("userBalance");
                }
            }catch (Exception e){
                PopUp.errorMessage("Error when import the User Balance from Database\n" + e,"Error import Database");
            }
        }

        return balance;
    }

    //transfer
    public static void transferMoney(String receiverPhoneNum, long moneyTransfer){
        connection();
        long updateUserBalance = getUserBalance() - moneyTransfer;
        long updateReceiverBalance = getReceiverBalance(receiverPhoneNum) + moneyTransfer;

        String updateSender = "UPDATE userdata SET userBalance = " + updateUserBalance + " WHERE id = '" + Controller.userID + "'";
        String updateReceiver = "UPDATE userdata SET userBalance = " + updateReceiverBalance + " WHERE phoneNum = '" + receiverPhoneNum + "'";
        try{
            statement.executeUpdate(updateSender);
            statement.executeUpdate(updateReceiver);
            Controller.showInfoTransferSukses("Kirim ke Teman","Pengiriman Berhasil !!!",
                    "Jumlah Kirim",moneyTransfer,"Penerima",getReceiverName(receiverPhoneNum));

        }catch (Exception e){
            PopUp.errorMessage("database error when transfering\n" + e,"Error import Database");
        }
    }

    //withdraw balance
    public static void withdrawBalance(long moneyOut, String bankName){
        connection();
        long updateUserBalance = getUserBalance() - moneyOut;

        String query = "UPDATE userdata SET userBalance = " + updateUserBalance + " WHERE id = '" + Controller.userID + "'";
        try{
            statement.executeUpdate(query);
            Controller.showInfoTarikTunaiSukses("Penarikan Saldo Berhasil",
                    "Jumlah Penarikan",moneyOut, "Nama dan Kode ATM",bankName);
        }catch (Exception e){
            PopUp.errorMessage("database error when tarik tunai\n" + e,"Error import Database");
        }
    }

    public static void addBalance(long moneyIn,String via){
        connection();
        long updateUserBalance = getUserBalance() + moneyIn;

        String query = "UPDATE userdata SET userBalance = " + updateUserBalance + " WHERE id = '" + Controller.userID + "'";
        try{
            statement.executeUpdate(query);
            Controller.showInfoIsiSaldoSuskes("Pengisian Berhasil",
                    "Jumlah Penarikan",moneyIn, "Diisi Via",via);
        }catch (Exception e){
            PopUp.errorMessage("database error when isi saldo\n" + e,"Error import Database");
        }
    }
    //change password
    public static void changePassword(String newPass){
        connection();

        String query = "UPDATE userdata SET password = '" + newPass + "' WHERE id = '" + Controller.userID + "'";
        try{
            statement.executeUpdate(query);
            PopUp.information("Berhasil Mengganti Password");
        }catch (Exception e){
            PopUp.errorMessage("database error when change password\n" + e,"Error Database");
        }
    }

    //change name
    public static void changeName(String newName){
        connection();

        String query = "UPDATE userdata SET name = '" + newName + "' WHERE id = '" + Controller.userID + "'";
        try{
            statement.executeUpdate(query);
            PopUp.information("Berhasil Mengganti Nama");
            loadUserData();
        }catch (Exception e){
            PopUp.errorMessage("database error when change password\n" + e,"Error Database");
        }
    }
}
