package com.Program;

import com.Tools.BankManager;
import com.Tools.PopUp;
import com.Tools.Time;
import com.Tools.WritingManager;
import com.View.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class Controller {
    private static ArrayList<String> allID = new ArrayList<String>();
    private static startView start = new startView();
    private static dashboardView view = new dashboardView();

    public static String userID;
    public static String userName;
    public static long userBalance = 0;
    public static String userPhoneNum;

    public static boolean photoIsExist = false;

    public static void RunApp(){
        if(Model.accountIsLogged()){
            refreshApp();
            showBeranda();
        }else {
            showSignIn();
        }
    }

    public static void showSignUp(){
        allID = Model.getAll_ID();
        start.content.setVisible(false);
        start.initsSignUpFrame();
        start.content.setVisible(true);
        start.setVisible(true);

    }
    public static void showSignIn(){
        allID = Model.getAll_ID();
        start.content.setVisible(false);
        start.initsSignInFrame();
        start.content.setVisible(true);
        start.setVisible(true);
        start.btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(checkIDisExist(start.getInputID())){
                    if(passwordIsCorrect(start.getInputPass())){
                        refreshApp();
                        Model.enterDataLogin();
                        deleteListID();
                        showBeranda();
                    }
                    else{
                        PopUp.errorMessage("ID atau Password salah","Kesalahan Login");
                    }
                }
                else{
                    PopUp.errorMessage("ID atau Password salah","Kesalahan Login");
                }
            }
        });
    }

    public static void showBeranda(){
        loadPhoto();
        view.setVisible(false);
        view.initsBeranda();
        view.setVisible(true);
        start.setVisible(false);
    }

    public static void registerNewUser(String rekeningNumWithCode){
        String bankCode = "";
        String rekeningNum = "";

        if(rekeningNumWithCode.equals("")){
            rekeningNum = "(kosong)";
            bankCode = "(kosong)";
        }else{
            char[] charNum = rekeningNumWithCode.toCharArray();
            for(int i=0; i<charNum.length; i++){
                if(i<3){
                    bankCode += charNum[i];
                }else{
                    rekeningNum += charNum[i];
                }
            }
        }
        Model.insertData(DataRegister.id, DataRegister.name,rekeningNum, DataRegister.phoneNum,DataRegister.password,bankCode);
    }

    //show info
    public static void showInfoTransferSukses(String navigation, String info,String labelMoneyOut, long moneyOut, String labelReceiver,
                                              String receiverName){
        reloadView();
        String moneyStr = String.valueOf(moneyOut);
        String balanceLabel = WritingManager.moneyWriting(Model.getUserBalance());
        view.initsInformasiAkhir(2,navigation,info,labelMoneyOut,moneyStr,labelReceiver,receiverName,
                "Sisa Saldo",balanceLabel);
    }
    public static void showInfoTarikTunaiSukses(String info,String labelMoneyOut, long moneyOut, String labelBank,
                                              String bankName){
        reloadView();
        String moneyStr = String.valueOf(moneyOut);
        String balanceLabel = WritingManager.moneyWriting(Model.getUserBalance());
        view.initsInformasiAkhir(4,"Tarik Tunai",info,labelMoneyOut,moneyStr,labelBank,bankName,
                "Sisa Saldo",balanceLabel);
    }
    public static void showInfoIsiSaldoSuskes(String info,String labelMoneyOut, long moneyOut, String labelBank,
                                              String bankName){
        reloadView();
        String moneyStr = String.valueOf(moneyOut);
        String balanceLabel = WritingManager.moneyWriting(Model.getUserBalance());
        view.initsInformasiAkhir(1,"Isi Saldo",info,labelMoneyOut,moneyStr,labelBank,bankName,
                "Saldo Anda",balanceLabel);
    }
    public static void showInfoRegisterSukses(){
        start.setVisible(false);
        start = new startView();
        start.initsRegisterSukses();
    }

    public static void logout(){
        Model.deleteDataLogin();
        //reset data
        userID = null;
        userName = null;
        userBalance = 0;
        HistoryData.cleanData();
        deleteListID();
        //re-inits startView
        start = new startView();
        showSignIn();
        //close view
        view.setVisible(false);
        view = new dashboardView();
        photoIsExist = false;
    }

    public static boolean checkPhoneNum(String phoneNum){
        boolean phoneNumIsExist = false;
        ArrayList<String> allPhoneNum = Model.getAllPhoneNum();

        for(int i=0; i<allPhoneNum.size(); i++){
            if(phoneNum.equals(allPhoneNum.get(i))){
                phoneNumIsExist = true;
                break;
            }
        }
        return  phoneNumIsExist;
    }
    public static boolean checkID(String id){
        boolean idIsExist = false;
        ArrayList<String> allPhoneNum = Model.getAll_ID();

        for(int i=0; i<allPhoneNum.size(); i++){
            if(id.equals(allPhoneNum.get(i))){
                idIsExist = true;
                break;
            }
        }
        return  idIsExist;
    }

    public static void validationReceiverData(String noRekening, String bankCode){
        if(Model.rekeningIsExist(noRekening,bankCode)){
            String bankName = BankManager.getNameBank(bankCode);
            view.initsinputJumlahUang_viaBank(Model.getName_rekeningNum(noRekening),bankName);
        }
        else {
            PopUp.information("Maaf, data calon penerima tidak ditemukan\n" +
                    "Silahkan cek kembali nomor rekning dan nama bank");
        }
    }

    //change name
    public static void changeName(){
        String newName = PopUp.input("Masukkan Nama Baru");
        if(newName == null){
            return;
        }
        if(!userName.equalsIgnoreCase(newName)){
            Model.changeName(newName);

        }else{
            PopUp.information("Nama Baru yang anda masukkan tidak berbeda dari sebelumnya!");
        }
    }

    //change password
    public static void changePassword(String oldPass, String newPass){
        if(Model.getUserPass(userID).equals(oldPass)){
            if(oldPass.equals(newPass)){
                PopUp.information("Gagal\nPassword Baru tidak boleh sama dengan Password lama");
            }else{
                Model.changePassword(newPass);
            }
        }else{
            PopUp.information("Gagal\nPassword Lama Salah");
        }

    }

    private static  boolean checkIDisExist(String inputID){
        boolean isExist = false;
        for(int i=0; i<allID.size(); i++){
            if(allID.get(i).equals(inputID)){
                isExist = true;
                userID = allID.get(i);
                break;
            }
        }
        return isExist;
    }
    private static boolean passwordIsCorrect(String inputPass){
        boolean isCorrect = false;
        if(Model.getUserPass(userID) != null){
            if(Model.getUserPass(userID).equals(inputPass)){
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    //kirim uang pada menu kirim uang ke teman
    public static void KirimUang(long moneyTransfer, String receiverPhoneNum, String notes){
        if(Model.phoneNumIsExist(receiverPhoneNum)){
            if(sufficientBalance(moneyTransfer)){
                Model.transferMoney(receiverPhoneNum,moneyTransfer);
                HistoryData.write_KirimUang(Model.getID_PhoneNum(receiverPhoneNum),Time.dateNow(),moneyTransfer,
                        Model.getReceiverName(receiverPhoneNum),notes);
                refreshApp();
            }
            else{
                PopUp.information("saldo anda tidak mencukupi");
            }
        }
        else{
            PopUp.information("Penerima tidak ditemukan\nPeriksa Kembali nomor tujuan");

        }
    }

    private static void loadPhoto(){
        File file = new File("assets//image//" + userID);
        if(file.exists()) photoIsExist = true;
    }
    //tarik uang pada menu tarik tunai
    public static void TarikUang(long moneyOut, String nameBank){
        if(moneyOut <= userBalance){
            Model.withdrawBalance(moneyOut, nameBank);
            HistoryData.write_reduceBalance(Time.dateNow(),moneyOut,nameBank);
            refreshApp();
        }else{
            PopUp.information("Saldo anda tidak mencukupi");
        }
    }

    public static void IsiSaldo(long moneyIn,String via){
        Model.addBalance(moneyIn,via);
        HistoryData.write_increaseBalance(Time.dateNow(),moneyIn,via);
        refreshApp();
    }

    public static boolean checkPhoneNumReceiver(String receiverNum){
        boolean numIsExist = false;
        if( !Model.getID_PhoneNum(receiverNum).equals("")){
            numIsExist = true;
        }
        return numIsExist;
    }
    public static boolean sufficientBalance(long moneyTransfer){
        boolean isEnough = false;
        if(Model.getUserBalance() >= moneyTransfer ){
            isEnough = true;
        }
        return isEnough;
    }

    public static void deleteListID(){
        for(int i=0; i<allID.size(); i++){
            allID.remove(i);
            i=-1;
        }
    }

    public static void reloadView(){
        view.setVisible(false);
        view = new dashboardView();
        view.setVisible(true);
    }

    public static void refreshApp(){
        Model.loadUserData();
        HistoryData.loadHistoryUser(userID);
    }

}
