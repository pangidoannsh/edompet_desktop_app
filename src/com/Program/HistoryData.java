package com.Program;

import com.Tools.PopUp;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HistoryData {
    public static int muchHistory = 0;
    public static ArrayList<String> metode = new ArrayList<>();
    public static ArrayList<String> dateTime = new ArrayList<>();
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> time = new ArrayList<>();
    public static ArrayList<Long> money = new ArrayList<>();
    public static ArrayList<String> transactWith_name = new ArrayList<>();
    public static ArrayList<String> notes = new ArrayList<>();

    public static void loadHistoryUser(String id){
        cleanData();
        FileReader fileReader;
        BufferedReader bufferedReader;
        String url = "db//history//" + id + ".txt";

        ArrayList<String> data = new ArrayList<>();

        try{
            fileReader = new FileReader(url);
            bufferedReader = new BufferedReader(fileReader);

            String temp = bufferedReader.readLine();
            int i = 0;
            while(temp != null){
                data.add(temp);
                StringTokenizer st = new StringTokenizer(data.get(i),";");
                metode.add(st.nextToken());
                dateTime.add(st.nextToken());
                money.add(Long.parseLong(st.nextToken()));
                transactWith_name.add(st.nextToken());
                notes.add(st.nextToken());

                temp = bufferedReader.readLine();
                i++;
            }
            filterDateAndTime();


        }catch(FileNotFoundException e){//jika file db history belum ada
            try{
                FileWriter fileWriter = new FileWriter(url);
                fileWriter.close();
            }catch (Exception ex){

            }
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        muchHistory = metode.size();
    }

    public static void write_KirimUang(String receiverId, String dateTime, long money, String name, String note){
        String url = "db//history//" + Controller.userID + ".txt";
        String urlReceiver = "db//history//" + receiverId + ".txt";

        String historyUser = "Kirim Uang" + ";" + dateTime + ";" + money + ";" + name + ";" + note + "\n";
        String historyReceiver = "Terima Uang" + ";" + dateTime + ";" + money + ";" + Controller.userName + ";" + note + "\n";

        try {
            FileWriter fileWriter = new FileWriter(url,true);
            fileWriter.write(historyUser);
            fileWriter.close();

            fileWriter = new FileWriter(urlReceiver,true);
            fileWriter.write(historyReceiver);
            fileWriter.close();

        }catch (Exception e){
            PopUp.errorMessage("Error when read history","Error History");
        }

    }

    public static void write_reduceBalance(String dateTime, long money, String bankName){
        String url = "db//history//" + Controller.userID + ".txt";

        String history = "Tarik Uang" + ";" + dateTime + ";" + money + ";" + bankName + ";" + "null" + "\n";

        try {
            FileWriter fileWriter = new FileWriter(url,true);
            fileWriter.write(history);
            fileWriter.close();

        }catch (Exception e){
            PopUp.errorMessage("Error when write history","Error History");
        }

    }

    public static void write_increaseBalance(String dateTime, long money, String via){
        String url = "db//history//" + Controller.userID + ".txt";

        String history = "Isi Saldo" + ";" + dateTime + ";" + money + ";" + via + ";" + "null" + "\n";

        try {
            FileWriter fileWriter = new FileWriter(url,true);
            fileWriter.write(history);
            fileWriter.close();

        }catch (Exception e){
            PopUp.errorMessage("Error when write history","Error History");
        }
    }

    public static void cleanData(){
        for(int i=0; i<metode.size(); i++){
            metode.remove(i);
            dateTime.remove(i);
            date.remove(i);
            time.remove(i);
            money.remove(i);
            transactWith_name.remove(i);
            notes.remove(i);
            i=-1;
        }
        muchHistory = 0;
    }

    public static void filterDateAndTime(){
        for(int i=0; i<dateTime.size(); i++){
            StringTokenizer st = new StringTokenizer(dateTime.get(i),"-");
            date.add(st.nextToken());
            time.add(st.nextToken());
        }
    }
}
