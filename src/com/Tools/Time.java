package com.Tools;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

//format
//dd -> tanggal, EEEE -> hari, HH -> jam, MM -> bulan, yyyy -> tahun, mm -> menit, ss -_ detik

public class Time {
    //to return time
    public static String timeNow(){
        String time;
        LocalTime timeNow = LocalTime.now();
        StringTokenizer st = new StringTokenizer(timeNow.format(DateTimeFormatter.ofPattern("HH:mm")),":");

        int numeric = Integer.parseInt(st.nextToken());

        if(numeric >= 6 && numeric < 10){
            time = "Pagi";
        }
        else if(numeric >= 10 && numeric < 16){
            time = "Siang";
        }
        else if(numeric >= 16 && numeric < 18){
            time = "Sore";
        }
        else{
            time = "Malam";
        }

        return time;
    }

    //to return day
    public static String dayNow(){
        String day;
        LocalDateTime dayNow = LocalDateTime.now();
        String[] dayIng = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        String[] dayInd = {"Senin","Selasa","Rabu","Kamis","Juma'at","Sabtu","Minggu"};
        day = dayNow.format(DateTimeFormatter.ofPattern("E"));

        for(int i = 0; i<dayIng.length; i++){
            if(day.equalsIgnoreCase(dayIng[i])){
                day = dayInd[i];
                break;
            }
        }

        return day;
    }

    //to return date
    public static String dateNow(){
        LocalDateTime date = LocalDateTime.now();

        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy-HH:mm"));
    }
}
