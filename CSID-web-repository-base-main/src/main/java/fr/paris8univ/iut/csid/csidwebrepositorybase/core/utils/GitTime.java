package fr.paris8univ.iut.csid.csidwebrepositorybase.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GitTime {

    public static String getActualDate(){
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = formatter.format(ldt);
        return formatDateTime;
    }

    public static LocalDateTime getActualLocalDate(){
        return convertStringToLocalDateTime(getActualDate());
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateToConvert){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateToConvert, formatter);
    }

    public static int compareTwoDates(LocalDateTime before, LocalDateTime after){
        long tmp = ChronoUnit.MINUTES.between(before,after);
        return Math.toIntExact(tmp);
    }

}
