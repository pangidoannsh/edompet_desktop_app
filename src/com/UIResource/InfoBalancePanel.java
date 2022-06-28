package com.UIResource;

public class InfoBalancePanel extends cRoundedPanel{
    private cLabel label = new cLabel("Saldo",10,6,178,25,cColor.WHITE,cFont.PoppinsRegular(15));
    private cLabel rp = new cLabel("Rp",10,49,25,25,cColor.WHITE80,cFont.PoppinsRegular(14));
    private cLabel balanceInfo;

    public InfoBalancePanel(String balance){
        super(3);
        setLayout(null);
        setBounds(0,0,298,90);

        balanceInfo = new cLabel(balance,33,41,155,35,cColor.WHITE,cFont.PoppinsRegular(20));
        add(label);
        add(new cLine(10,31,178,1,cColor.WHITE20));
        add(rp);
        add(balanceInfo);

    }
}
