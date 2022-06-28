package com.View;

import com.Program.Controller;
import com.Program.DataRegister;
import com.Templates.StartFrame;
import com.Tools.PopUp;
import com.UIResource.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class startView extends StartFrame {

    //Sign In Component
    private cTitleStart titleSignIn = new cTitleStart("Sign In",50,120,110,65);
    private cInputField inputID  = new cInputField("ID",50,217,425,70);
    private cInputPassword inputPassword = new cInputPassword("Password", 50,310,425,70);
    public cBlueButton btnLogin = new cBlueButton(6,"LOGIN",50,430,425,47,cFont.PoppinsBold(25),
            true);

    //Sign Up Component
    private cTitleStart titleSignUp = new cTitleStart("Sign Up",210,120,123,65);
    private cInputField inputName = new cInputField("Nama",50,217,425,70);
    private cInputField inputPhoneNum = new cInputField("Nomor Telepon", 50,310,425,70);
    private cInputField inputNewID = new cInputField("ID Baru",50,403,425,70);
    private cInputPassword inputNewPassword = new cInputPassword("Password", 50,496,425,70);
    public cBlueButton btnNext = new cBlueButton(6,"NEXT",50,600,425,47,cFont.PoppinsBold(25),
            true);

    private cInputField inputNoRek = new cInputField("Nomor Rekenig Bank",50,320,425,70,true);
    private cLabel linkLewati = new cLabel("anda dapat melewati, dengan mengosongkan kolom",50,400,
            425,25,cColor.WHITE60,cFont.PoppinsRegular(14));

    private LinkLabel helpLabel = new LinkLabel("need help ?",50,385,120,20,14);
    public  startView() {
        super();
        titleSignIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Controller.showSignIn();
            }
        });
        titleSignUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Controller.showSignUp();
            }
        });
    }

    public void initsSignInFrame(){
        content.removeAll();
        titleSignIn.setActive();
        titleSignUp.setNotActive();

        content.add(titleSignIn);
        content.add(titleSignUp);
        content.add(inputID);
        content.add(inputPassword);
        content.add(helpLabel);
        content.add(btnLogin);

    }

    public void initsSignUpFrame(){
        content.removeAll();
        titleSignUp.setActive();
        titleSignIn.setNotActive();

        content.add(titleSignIn);
        content.add(titleSignUp);
        content.add(inputName);
        content.add(inputPhoneNum);
        content.add(inputNewID);
        content.add(inputNewPassword);
        content.add(btnNext);
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                char[] charPhone = inputPhoneNum.field.getText().toCharArray();
                if(inputPhoneNum.field.getText().matches("[0-9]*") && charPhone.length == 12){
                    if(!Controller.checkPhoneNum(inputPhoneNum.field.getText())){
                        if(!Controller.checkID(inputNewID.field.getText())){
                            DataRegister.enterData(
                                    inputName.field.getText(),
                                    inputPhoneNum.field.getText(),
                                    inputNewID.field.getText(),
                                    String.valueOf(inputNewPassword.field.getPassword())
                            );
                            initsRegister();
                        }else{
                            PopUp.information("ID sudah digunakan pada Akun eDompet Lainnya!");
                        }

                    }
                    else{
                        PopUp.information("Nomor Telepon sudah digunakan pada Akun eDompet Lainnya!");
                    }
                }
            }
        });
    }

    public void initsRegister(){
        setVisible(false);
        content.removeAll();
        titleSignUp.setActive();
        titleSignIn.setNotActive();

        content.add(titleSignIn);
        content.add(titleSignUp);
        content.add(inputNoRek);
        content.add(new cLabel("Masukkan kode Bank diikuti Nomor Rekening Jika",
                        50,195,425,50, cColor.WHITE60, cFont.PoppinsRegular(16)));
        content.add(new cLabel("ingin mengaitkan dengan akun Bank Anda",50,220,425,50,
                cColor.WHITE60, cFont.PoppinsRegular(16)));
        content.add(new cLabel("format : (code bank)(nomor rekening)",50,253,425,50,
                cColor.DARK_BLUE, cFont.PoppinsRegular(14)));
        content.add(new cLabel("contoh : 333999999999",50,275,425,50,
                cColor.DARK_BLUE, cFont.PoppinsRegular(14)));
        content.add(linkLewati);
        cBlueButton btnRegister = new cBlueButton(6,"REGISTER",50,450,425,47,
                cFont.PoppinsBold(25),true);
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(inputNoRek.field.getText().equals("")){
                    Controller.registerNewUser("");
                }
                else if(!inputNoRek.field.getText().matches("[0-9]*")){
                    PopUp.information("Nomor Rekening hanya dapat berupa angka");
                    return;
                }
                else {
                    Controller.registerNewUser(inputNoRek.field.getText());
                }
            }
        });
        content.add(btnRegister);

        setVisible(true);

    }

    public void initsRegisterSukses(){
        setVisible(false);
        content.removeAll();
        titleSignUp.setActive();
        titleSignIn.setNotActive();

        content.add(titleSignIn);
        content.add(titleSignUp);

        content.add(new cLabel("SELAMAT REGISTRASI BERHASIL!!!",50,200,425,
                40,cColor.WHITE,cFont.PoppinsMed(24)));
        content.add(new cLabel("anda dapat pergi ke menu Sign In untuk masuk",50,256,425,
                40,cColor.WHITE,cFont.PoppinsRegular(18)));

        setVisible(true);
    }

    public String getInputID(){
        return inputID.field.getText();
    }
    public String getInputPass(){
        return String.valueOf(inputPassword.field.getPassword());
    }


}
