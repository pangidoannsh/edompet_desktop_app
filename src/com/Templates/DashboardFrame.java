package com.Templates;

import javax.swing.*;

import com.UIResource.*;

public abstract  class DashboardFrame extends JFrame {
    //main panel
    public JPanel background;
    public JPanel topSide;
    public JPanel sidebar;
    public JPanel header;
    public cRoundedPanel content;
    public JPanel additionPanel;

    //sidebar component
    public MenuSidebar home = new MenuSidebar("assets//icon//homeIcon24.PNG","Beranda",145);
    public MenuSidebar transfer = new MenuSidebar("assets//icon//transferIcon24.PNG","Transfer",195);
    public MenuSidebar payment = new MenuSidebar("assets//icon//paymentIcon24.PNG","Pembayaran",245);
    public MenuSidebar tarikTunai = new MenuSidebar("assets//icon//tarikIcon.PNG","Tarik Tunai",295);
    private JPanel line = new JPanel();
    private cLabel typeAkun = new cLabel("Akun",30,370,50,20,cColor.WHITE60,cFont.PoppinsRegular(14));
    public MenuSidebar profile = new MenuSidebar("assets//icon//profileIcon24.PNG","Profile",405);
    public MenuSidebar history_sidebar = new MenuSidebar("assets//icon//historyIcon.PNG","Riwayat",455);
    public MenuSidebar logOut = new MenuSidebar("assets//icon//logOutIcon.PNG","Log Out",505);

    //LOGO
    private cIcon logoApp = new cIcon("assets//icon//Logo_eDompet.PNG",0,34,230,60);
//    private cIcon logoApp = new cIcon("assets//icon//Logo_eD.PNG",0,34,230,60);

    //content component
    public JPanel lineContent = new JPanel();

    public DashboardFrame(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050,765);
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);

        background = new JPanel();
        background.setBounds(0,0,1050,765);
        background.setBackground(cColor.GRAY);
        background.setLayout(null);

        //============================TOP_SIDE================================
        topSide = new JPanel();
        topSide.setBounds(0,0,1050,86);
        topSide.setBackground(cColor.DARK_GRAY);
        topSide.setLayout(null);
        //add component
        topSide.add(logoApp);
        topSide.add(new CloseLabel(990,16));

        //============================SIDEBAR================================
        sidebar = new JPanel();
        sidebar.setBounds(0,0,230,765);
        sidebar.setBackground(cColor.DARK_GRAY);
        sidebar.setLayout(null);

        //set component
        line.setBounds(25,360,180,1);
        line.setBackground(cColor.WHITE20);

        //add component
        sidebar.add(home);
        sidebar.add(transfer);
        sidebar.add(payment);
        sidebar.add(tarikTunai);
        sidebar.add(line);
        sidebar.add(typeAkun);
        sidebar.add(profile);
        sidebar.add(history_sidebar);
        sidebar.add(logOut);
        //============================HEADER================================
        header = new JPanel();
        header.setBounds(230,86,820,174);
        header.setBackground(cColor.DARK_GRAY);
        header.setLayout(null);

        // ============================CONTENT================================
        content = new cRoundedPanel(8,2,cColor.DARK_GRAY);
        content.setBackground(cColor.GRAY);
        content.setBounds(273,161,733,186);
        content.setLayout(null);

        // ============================AdditionPanel================================
        additionPanel = new JPanel();
        additionPanel.setBounds(273,374,734,300);
        additionPanel.setLayout(null);
        additionPanel.setBackground(background.getBackground());

        //add to background panel
        background.add(topSide);
        background.add(sidebar);
        background.add(content);
        background.add(additionPanel);
        background.add(header);

        //add background panel to Frame
        add(background);
    }

}
