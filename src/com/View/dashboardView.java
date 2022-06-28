package com.View;

import com.Program.Controller;
import com.Program.HistoryData;
import com.Tools.BankManager;
import com.Tools.PopUp;
import com.Tools.WritingManager;
import com.Templates.DashboardFrame;
import com.UIResource.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;

enum SIDEBAR_MENU{
    BERANDA,
    TRANSFER,
    PEMBAYARAN,
    TARIK_TUNAI,
    PROFILE,
    RIWAYAT,
    LOGOUT
}

public class dashboardView extends DashboardFrame {
    private final String[] viaList = {"BCA - Bank Central Asia","BNI - Bank Negara Indonesia",
            "BRI - Bank Rakyat Indonesia","Bank Danamon","indomaret","Alfamart"};

    //header component
    private final cLabel greeting = new cLabel(null,60,0,470,40,cColor.WHITE,cFont.PoppinsRegular(18));
    private final cLabel currency = new cLabel("Rp",60,47,20,21,cColor.WHITE80,cFont.PoppinsRegular(14));
    private cLabel userBalance = new cLabel(null,83,34,340,40,cColor.WHITE,cFont.PoppinsMed(24));
    private final cBlueButton btnTarik = new cBlueButton(5,"Tarik",191,36,75,28,cFont.
            PoppinsMed(12),false);
    private final cIcon profileIcon = new cIcon(null,730,0,40,40);

    //content component beranda
    private MenuContent isiSaldo_MenuContent = new MenuContent("assets//icon//addIcon32.PNG", "Isi Saldo",68,
            54);
    private MenuContent transferBank_MenuContent = new MenuContent("assets//icon//bankIcon.PNG", "Transfer via",
            "Bank", 198,54);
    private MenuContent kirimTeman_MenuContent = new MenuContent("assets//icon//transferIcon.PNG", "Kirim ke",
            "Teman", 328,54);
    private MenuContent pembayaran_MenuContent = new MenuContent("assets//icon//paymentIcon.PNG", "Pembayaran",
            458,54);
    private MenuContent bayarMakan_MenuContent = new MenuContent("assets//icon//foodIcon.PNG", "Bayar",
            "Makan", 588,54);

    //Menu in Menu Transfer
    private cMenuTransfer transferBank_MenuTransfer = new cMenuTransfer("assets//icon//bankIcon.PNG","Transfer via Bank",47);
    private cMenuTransfer kirimTeman_MenuTransfer = new cMenuTransfer("assets//icon//friend.PNG","Kirim ke Teman",166);

    //Component of Kirim_ke_Teman
    private cInputField inputNoPenerima = new cInputField("Nomor Telepon Penerima",40,50,653,65);
    private cInputField noteToReciver = new cInputField(40,128,658,35,"Tuliskan Catatan");
    private cInputJumlahUang inputJumlahUang_KirimTeman = new cInputJumlahUang("Jumlah Kirim",19);
    private cBlueButton btnKirim_KirimTeman = new cBlueButton(10,"Kirim",493,225,200,50,
            cFont.PoppinsMed(24),false);
    public cRoundedPanel addPanel_kirimTeman = new cRoundedPanel(8);

    //Component of transfer via Bank
    private JComboBox listChooseBank = new JComboBox(BankManager.bankList);
    private cInputField inputNoRek = new cInputField("Nomor Rekening",38,99,653,65);
    private cBlueButton btnCariNoRek = new cBlueButton(10,"Cari",493,0,200,50,
            cFont.PoppinsMed(24),false);

    //at method initsinputJumlahUang_viaBank
    private cInputJumlahUang inputJumlahUang_viaBank = new cInputJumlahUang("Jumlah Kirim",136);
    private cBlueButton btnKirim_viaBank = new cBlueButton(10,"Kirim",491,0,200,50,
            cFont.PoppinsMed(24),true);
    private InfoBalancePanel infoSaldoPanel;

    //Component of Pembayaran
    private cInputField inputKodePembayaran = new cInputField("Kode Pembayaran",40,50,653,65);
    private cBlueButton  btnCariKodePembayaran = new cBlueButton(10,"Cari",493,0,200,50,
            cFont.PoppinsMed(24),false);
    private cBlueButton btnBayar_pembayaran = new cBlueButton(10,"Kirim",533,22,200,50,cFont.PoppinsMed(24),
            false);

    //Component of Tarik Tunai
    private cInputField inputKodeMesinATM = new cInputField("Kode Mesin ATM",38,99,653,65);
    private cBlueButton btnCariMesinATM = new cBlueButton(10,"Cari",477,0,200,50,cFont.PoppinsMed(24),
            false);
    private cInputJumlahUang nominalPenarikan;
    private cBlueButton tarikButton = new cBlueButton(10,"Tarik",477,0,200,50,cFont.PoppinsMed(24)
            ,false);

    //Component Isi Saldo
    private JComboBox listVia = new JComboBox(viaList);
    private  cInputJumlahUang inputAddSaldo = new cInputJumlahUang(118,"Nominal Penambahan Saldo",10_000);
    private cBlueButton btnIsiSaldo = new cBlueButton(10,"Isi Saldo",487,61,200,50,cFont.PoppinsMed(24),
            false);

    //Component of Profile
    private cIcon photoProfile = new cIcon(null,35,20,90,90);
    private cLabel nameProfile = new cLabel(null,140,30,480,40,cColor.WHITE,cFont.PoppinsRegular(24));
    private cBlueButton editName = new cBlueButton(6,"edit nama",540,35,110,30,
            cFont.PoppinsMed(14),false);
    private cLabel numPhoneProfile = new cLabel(null,140,65,150,35,cColor.WHITE80,cFont.PoppinsRegular(20));
    private  cLabel rp_profile = new cLabel("Rp",140,118,25,25,cColor.WHITE80,cFont.PoppinsRegular(16));
    private cLabel userBalanceProfile = new cLabel(null,165,105,340,40,cColor.WHITE,cFont.PoppinsMed(26));
    private cMotionLabel gantiPassword = new cMotionLabel("Ganti Password",35,186,145,30,cColor.WHITE,
            cFont.PoppinsRegular(18),cMotionLabel.moveHoriz);
    private cMotionLabel riwayat = new cMotionLabel("Riwayat",35,222,145,30,cColor.WHITE,
            cFont.PoppinsRegular(18),cMotionLabel.moveHoriz);
    private cMotionLabel pusatBantuan = new cMotionLabel("Pusat Bantuan",35,258,145,30,cColor.WHITE,
            cFont.PoppinsRegular(18),cMotionLabel.moveHoriz);
    //component ganti password
    private cInputPassword oldPassword = new cInputPassword("Password Lama",40,40,653,70);
    private cInputPassword newPassword = new cInputPassword("Password Baru",40,130,653,70);
    private cBlueButton confirmChangePass = new cBlueButton(10,"Ganti Password",477,70,200,50,
            cFont.PoppinsMed(24),true);

    //Component of Riwayat
    private cScrollPane scrollPane;
    private HistoryPanel historyPanel;
    private ArrayList<HistoryContainer> historyContain = new ArrayList<HistoryContainer>();

    public dashboardView(){
        super();

        //MENU SIDEBAR
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsBeranda();
            }
        });

        transfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsTransfer();
            }
        });

        payment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsPembayaran();
            }
        });

        tarikTunai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsTarikTunai();
            }
        });
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsProfile();
            }
        });
        history_sidebar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsHisory();
            }
        });
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(PopUp.confirmation("Yakin ingin Log Out ?")){
                    Controller.logout();
                }
            }
        });
        //MENU CONTENT
        isiSaldo_MenuContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsIsiSaldo();
            }
        });
        transferBank_MenuContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsViaBank_Transfer();
            }
        });
        kirimTeman_MenuContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsKirimTeman_Transfer();
            }
        });
        pembayaran_MenuContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }
        });
        bayarMakan_MenuContent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }
        });
        //Transfer
        btnCariNoRek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                String bankCode = BankManager.getCodeBank((String)listChooseBank.getSelectedItem());
                Controller.validationReceiverData(inputNoRek.field.getText(),bankCode);
            }
        });
        //Button Transfer at Transfer via Bank Menu
        btnKirim_viaBank.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

        });
        //Header
        btnTarik.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsTarikTunai();
            }
        });
        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsProfile();
            }
        });

        //Tarik Tunai
        btnCariMesinATM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsInputNominal_TarikTunai();
            }
        });
        tarikButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(Long.parseLong(nominalPenarikan.inputMoney) >= 50_000){
                    String bank = listChooseBank.getSelectedItem() + " - " + inputKodeMesinATM.field.getText();
                    Controller.TarikUang(Long.parseLong(nominalPenarikan.inputMoney),bank);
                }

            }
        });

        //Isi Saldo
        btnIsiSaldo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                String via = listVia.getSelectedItem() + "";
                Controller.IsiSaldo(Long.parseLong(inputAddSaldo.inputMoney),via);
            }
        });

        //Profile Menu
        editName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Controller.changeName();
            }
        });
        gantiPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                viewGantiPassword();
            }
        });
        riwayat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsHisory();
            }
        });
        confirmChangePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Controller.changePassword(String.valueOf(oldPassword.field.getPassword()),String.valueOf(newPassword.field.getPassword()));
            }
        });
    }

    //breanda
    public void initsBeranda(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.BERANDA);
        StringTokenizer nick = new StringTokenizer(Controller.userName," ");
        greeting.setText("Hai, " + nick.nextToken());
        String strUserBalance = WritingManager.moneyWriting(Controller.userBalance);
        userBalance.setText(strUserBalance);
        char[] moneyChar = strUserBalance.toCharArray();
        if(moneyChar.length <= 3){
            btnTarik.setLocation(153,36);
        }else{
            btnTarik.setLocation(83+(moneyChar.length * 14),36);
        }

        //set icon profile
        String url = "assets//image//profileIcon.PNG";
        if(Controller.photoIsExist){
            url = "assets//image//" + Controller.userID + "//icon.PNG";
        }
        profileIcon.setIcon(new ImageIcon(url));
        content.add(isiSaldo_MenuContent);
        content.add(transferBank_MenuContent);
        content.add(kirimTeman_MenuContent);
        content.add(pembayaran_MenuContent);
        content.add(bayarMakan_MenuContent);

        btnTarik.setBackground(cColor.BLUE);
        header.add(profileIcon);
        header.add(greeting);
        header.add(currency);
        header.add(userBalance);
        header.add(btnTarik);

        setVisible(true);

    }

    //Transfer
    public void initsTransfer(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TRANSFER);

        header.add(new cLabel("Transfer",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        content.setSize(733,292);
        content.add(transferBank_MenuTransfer);
        content.add(kirimTeman_MenuTransfer);

        transferBank_MenuTransfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsViaBank_Transfer();
            }
        });
        kirimTeman_MenuTransfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                initsKirimTeman_Transfer();
            }
        });
        setVisible(true);
    }
    public void initsViaBank_Transfer(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TRANSFER);

        header.add(new cLabel("Transfer via Bank",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        listChooseBank.setBackground(cColor.GRAY);
        listChooseBank.setBounds(38,31,653,40);
        listChooseBank.setForeground(cColor.WHITE);
        listChooseBank.setFont(cFont.PoppinsRegular(18));

        //set content panel
        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);
        //add at component in content panel
        content.add(listChooseBank);
        content.add(inputNoRek);
        //add at additional panel
        additionPanel.add(btnCariNoRek);
        setVisible(true);
    }
    public void initsinputJumlahUang_viaBank(String receiver, String bankName){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TRANSFER);

        header.add(new cLabel("Transfer",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.setSize(733,277);
        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);

        content.add(new cLabel(receiver,40,40,653,30,cColor.WHITE,cFont.PoppinsRegular(18)));
        content.add(new cLabel(bankName,40,70,653,30,cColor.WHITE60,cFont.PoppinsRegular(18)));
        content.add(new cLine(0,118,733,1,cColor.WHITE20));
        content.add(inputJumlahUang_viaBank);
        content.add(new cLine(40,247,653,1,cColor.WHITE20));
        additionPanel.setBounds(273,455,734,300);
        additionPanel.add(btnKirim_viaBank);

    }

    public void initsKirimTeman_Transfer(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TRANSFER);

        header.add(new cLabel("Kirim ke Teman",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.add(inputNoPenerima);
        //add new panel
        addPanel_kirimTeman.setBounds(0,12,733,186);
        addPanel_kirimTeman.setBackground(cColor.DARK_GRAY);
        addPanel_kirimTeman.setLayout(null);
        //add component to additional panel
        addPanel_kirimTeman.add(inputJumlahUang_KirimTeman);
        addPanel_kirimTeman.add(noteToReciver);

        additionPanel.add(btnKirim_KirimTeman);
        additionPanel.add(addPanel_kirimTeman);

        btnKirim_KirimTeman.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(!inputJumlahUang_KirimTeman.isError){
                    long moneyTransfer = Long.parseLong(inputJumlahUang_KirimTeman.inputMoney);
                    if(noteToReciver.field.getText().equals("Tuliskan Catatan")){
                        noteToReciver.field.setText("null");
                    }
                    Controller.KirimUang(moneyTransfer,inputNoPenerima.field.getText(),
                            noteToReciver.field.getText());
                }
            }
        });
        setVisible(true);

    }

    //Pembayaran
    public void initsPembayaran(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.PEMBAYARAN);

        //additionPanel.setBounds(273,374,733,186);
        header.add(new cLabel("Pembayaran",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.add(inputKodePembayaran);

        additionPanel.add(btnCariKodePembayaran);
        setVisible(true);
    }
    public void initsKonfirmasiPembayaran(String userName, String shopName, String moneyOut,String userBalance){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.PEMBAYARAN);
        infoSaldoPanel = new InfoBalancePanel(userBalance);
        infoSaldoPanel.setBounds(0,0,198,91);
        infoSaldoPanel.setBackground(cColor.DARK_GRAY);

        String finalMoneyOut = "Rp ";
        finalMoneyOut += moneyOut;

        header.add(new cLabel("Pembayaran",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.setSize(733,259);

        content.add(new cLabel(shopName ,40,25,653,30,cColor.WHITE60,cFont.PoppinsRegular(18)));
        content.add(new cLabel(userName,40,73,653,30,cColor.WHITE,cFont.PoppinsRegular(18)));
        content.add(new cLine(0,111,733,1,cColor.WHITE20));
        content.add(new cLabel("Total Pembayaran",40,126,653,30,cColor.WHITE60,cFont.PoppinsRegular(16)));
        content.add(new cLabel(finalMoneyOut,40,169,653,60,cColor.WHITE,cFont.PoppinsRegular(36)));
        content.add(new cLine(40,247,653,1,cColor.WHITE20));

        additionPanel.setBounds(273,455,734,300);
        additionPanel.add(infoSaldoPanel);
        additionPanel.add(btnBayar_pembayaran);

    }

    //Tarik Tunai
    public  void initsTarikTunai(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TARIK_TUNAI);

        header.add(new cLabel("Tarik Tunai",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);

        listChooseBank.setBackground(cColor.GRAY);
        listChooseBank.setBounds(38,31,653,40);
        listChooseBank.setForeground(cColor.WHITE);
        listChooseBank.setFont(cFont.PoppinsRegular(18));

        content.add(listChooseBank);
        content.add(inputKodeMesinATM);
        additionPanel.add(btnCariMesinATM);
        setVisible(true);
    }
    public void initsInputNominal_TarikTunai(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.TARIK_TUNAI);
        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);

        header.add(new cLabel("Tarik Tunai",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        nominalPenarikan = new cInputJumlahUang(20,"Nominal Penarikan",50_000);

        content.add(nominalPenarikan);
        content.add(new cLine(38,135,653,1,cColor.WHITE20));
        additionPanel.add(tarikButton);

    }

    //Isi Saldo
    public void initsIsiSaldo(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.BERANDA);
        header.add(new cLabel("Isi Saldo",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);
        content.setSize(733,251);

        listVia.setBackground(cColor.GRAY);
        listVia.setBounds(38,31,653,40);
        listVia.setForeground(cColor.WHITE);
        listVia.setFont(cFont.PoppinsRegular(18));

        lineContent.setBackground(cColor.WHITE20);
        content.add(listVia);
        content.add(new cLine(40,100,653,1,cColor.WHITE20));
        content.add(inputAddSaldo);
        content.add(new cLine(40,230,653,1,cColor.WHITE20));
        additionPanel.add(btnIsiSaldo);
        setVisible(true);

    }

    //profile menu
    public void initsProfile(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.PROFILE);
        header.add(new cLabel("Profile",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.setSize(733,320);
        //set component
        String urlPhoto = "assets//image//photoProfile.PNG";
        if(Controller.photoIsExist){
            urlPhoto = "assets//image//" + Controller.userID + "//PP.PNG";
        }
        photoProfile.setIcon(new ImageIcon(urlPhoto));
        nameProfile.setText(Controller.userName);
        numPhoneProfile.setText(Controller.userPhoneNum);
        userBalanceProfile.setText(WritingManager.moneyWriting(Controller.userBalance));
        lineContent.setBounds(35,153,683,1);
        //add component to content
        content.add(photoProfile);
        content.add(nameProfile);
        content.add(editName);
        content.add(numPhoneProfile);
        content.add(rp_profile);
        content.add(userBalanceProfile);
        content.add(lineContent);
        content.add(gantiPassword);
        content.add(riwayat);
        content.add(pusatBantuan);
    }
    public void viewGantiPassword(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.PROFILE);
        content.setSize(733,250);
        header.add(new cLabel("Ganti Password",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        content.add(oldPassword);
        content.add(newPassword);
        additionPanel.add(confirmChangePass);
    }

    //hisory || riwayat
    public void initsHisory(){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.RIWAYAT);
        header.add(new cLabel("Riwayat Transaksi",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        content.setSize(733,600);
        content.setBorderColor(cColor.GRAY);
        content.setBackground(cColor.DARK_GRAY);
        //set content history
        historyContain = new ArrayList<>();
        for(int i=0; i< HistoryData.muchHistory; i++){

            String date = HistoryData.date.get(i);
            String time = HistoryData.time.get(i);
            long money = HistoryData.money.get(i);
            String transactWith = HistoryData.transactWith_name.get(i);
            String typeTransact = HistoryData.metode.get(i);
            String notes = HistoryData.notes.get(i);
            historyContain.add(new HistoryContainer(typeTransact ,date, money));

            historyContain.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
// parameter String date, String time, long money, String transactWith, String typeTransact, String note
                        initsDetailHistory(
                                date,
                                time,
                                money,
                                transactWith,
                                typeTransact,
                                notes
                        );
                }
            });
        }
        if(HistoryData.muchHistory == 0){
            content.setBounds(273,161,733,186);
            cLabel labelEmpty = new cLabel("Belum ada Riwayat Trasaksi apapun",0,50,723,
                    30,cColor.WHITE60,cFont.PoppinsMed(24));
            labelEmpty.setHorizontalAlignment(JLabel.CENTER);
            content.add(labelEmpty);
        }
        //set Panel
        historyPanel = new HistoryPanel(historyContain);
        //set ScollPane
        scrollPane =  new cScrollPane(historyPanel);
        scrollPane.setBounds(5,5,723,580);
        scrollPane.setBorder(null);
        content.add(scrollPane);
    }

    public void initsDetailHistory(String date, String time, long money, String transactWith, String typeTransact, String note){
        refreshSidebar();
        activeSidebar(SIDEBAR_MENU.RIWAYAT);
        header.add(new cLabel("Riwayat Transaksi",50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));

        boolean useNote;
        String aim = "";
        if(typeTransact.equalsIgnoreCase("Terima Uang")) {
            aim = " dari ";
            useNote = true;
        }
        else if(typeTransact.equalsIgnoreCase("Kirim Uang")) {
            aim = " ke ";
            useNote = true;
        }
        else if(typeTransact.equalsIgnoreCase("Tarik Tunai")) {
            aim = " melalui ATM ";
            useNote = false;
        }
        else{
            aim = " melalui ";
            useNote = false;
        }

        int heigthContent = 415;
        if(!useNote) heigthContent = 290;
        content.setSize(733,heigthContent);
        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);
        content.add(new cLabel("Detail Transaksi",270,10,200,40,cColor.WHITE,cFont.PoppinsMed(24)));
        content.add(new cLabel(date + " | " + time,40,56,115,20,cColor.WHITE60,cFont.PoppinsRegular(12)));
        content.add(new cLabel("ID " + Controller.userID,560,56,120,20,cColor.WHITE60,cFont.PoppinsRegular(12)));
        content.add(new cLine(40,86,653,1,cColor.WHITE20));
        content.add(new cLabel(typeTransact + " Rp" + WritingManager.moneyWriting(money) + aim + transactWith,
                40,98,653,30,cColor.WHITE80,cFont.PoppinsRegular(16)));

        JPanel panelType = new JPanel();
        panelType.setBounds(40,141,120,32);
        panelType.setBackground(cColor.WHITE20);
        panelType.add(new cLabel(typeTransact,11,4,120,25,cColor.WHITE,cFont.PoppinsMed(16)));

        content.add(panelType);

        JPanel panelTotal = new JPanel(null);
        panelTotal.setBounds(40,189,653,42);
        panelTotal.setBackground(cColor.BLUE60);

        panelTotal.add(new cLabel("Total",23,3,70,40,cColor.WHITE,cFont.PoppinsMed(24)));
        char[] charMoney = WritingManager.moneyWriting(money).toCharArray();
        int length = charMoney.length;
        int defaultKor = 565;
        panelTotal.add(new cLabel("Rp " + WritingManager.moneyWriting(money),defaultKor - (length*9),3,200,40,
                cColor.WHITE,cFont.PoppinsMed(24)));

        content.add(panelTotal);
        content.add(new cLine(40,259,653,1,cColor.WHITE20));
        if(useNote){
            content.add(new cLabel("Catatan",40,288,120,30,cColor.WHITE60,cFont.PoppinsRegular(20)));
            if(note.equals("null")){
                note = "";
            }
            content.add(new cLabel(note,40,338,500,30,cColor.WHITE80,cFont.PoppinsRegular(20)));
            content.add(new cLine(40,378,653,1,cColor.WHITE20));
        }

        setVisible(true);

    }

    //Informasi Akhir
    public void initsInformasiAkhir(int select_sidebar, String navigation, String information, String labelMoneyOut,
                                    String moneyOut, String labelReceiver, String receiverName, String labelBalance, String userBalance)
    {

        refreshSidebar();
        activeSidebar(selectSidebar(select_sidebar));
        String finalMoneyOut = "Rp ";
        finalMoneyOut += moneyOut;
        String finalBalance = "Rp ";
        finalBalance += userBalance;
        header.add(new cLabel(navigation,50,32,342,30,cColor.WHITE,cFont.PoppinsMed(20)));
        content.setSize(733,418);
        content.setBackground(cColor.DARK_GRAY);
        content.setBorderColor(cColor.GRAY);

        content.add(new cLabel(information,38,20,670,60,cColor.WHITE,cFont.PoppinsRegular(36)));
        content.add(new cLabel(labelMoneyOut,38,104,670,30,cColor.WHITE60,cFont.PoppinsRegular(16)));
        content.add(new cLabel(finalMoneyOut,38,140,670,40,cColor.WHITE,cFont.PoppinsRegular(24)));
        content.add(new cLabel(labelReceiver,38,197,670,30,cColor.WHITE60,cFont.PoppinsRegular(16)));
        content.add(new cLabel(receiverName,38,233,670,40,cColor.WHITE,cFont.PoppinsRegular(20)));
        content.add(new cLabel(labelBalance,38,297,670,30,cColor.WHITE80,cFont.PoppinsRegular(18)));
        content.add(new cLabel(finalBalance,38,328,670,60,cColor.WHITE,cFont.PoppinsRegular(36)));

    }

    public void refreshSidebar(){
//        setVisible(false);
        content.setVisible(false);
        content.removeAll();
        content.setBounds(273,161,733,186);
        content.setBackground(cColor.GRAY);
        content.setBorderColor(cColor.DARK_GRAY);
        content.setVisible(true);

        additionPanel.setVisible(false);
        additionPanel.removeAll();
        additionPanel.setBounds(273,374,734,300);
        additionPanel.setVisible(true);

        header.setVisible(false);
        header.removeAll();
        header.setVisible(true);
        
        home.setNotActive();
        transfer.setNotActive();
        payment.setNotActive();
        tarikTunai.setNotActive();
        profile.setNotActive();
        history_sidebar.setNotActive();
    }

    public void activeSidebar(SIDEBAR_MENU sidebar){
        switch (sidebar) {
            case BERANDA:
                home.setActive();
                break;
            case TRANSFER:
                transfer.setActive();
                break;
            case PEMBAYARAN:
                payment.setActive();
                break;
            case TARIK_TUNAI:
                tarikTunai.setActive();
                break;
            case PROFILE:
                profile.setActive();
                break;
            case RIWAYAT:
                history_sidebar.setActive();
                break;
            case LOGOUT:
                logOut.setActive();
                break;
        }
    }

    private SIDEBAR_MENU selectSidebar(int x){
        SIDEBAR_MENU select;
        switch (x){
            case 1:
                select = SIDEBAR_MENU.BERANDA;
                break;
            case 2:
                select = SIDEBAR_MENU.TRANSFER;
                break;
            case 3:
                select = SIDEBAR_MENU.PEMBAYARAN;
                break;
            case 4:
                select = SIDEBAR_MENU.TARIK_TUNAI;
                break;
            case 5:
                select = SIDEBAR_MENU.PROFILE;
                break;
            case 6:
                select = SIDEBAR_MENU.RIWAYAT;
            case 7:
                select = SIDEBAR_MENU.LOGOUT;
                break;
            default:
                select = SIDEBAR_MENU.BERANDA;
        }
        return select;
    }


}
