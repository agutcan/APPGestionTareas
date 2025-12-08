package com.example.appgestiontareas.ui.utils;

import android.icu.util.Calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class utils {
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static CalendarDay convertirFecha(String fecha) {
        String[] partes = fecha.split("[-/]");
        int year = Integer.parseInt(partes[0]);
        int month = Integer.parseInt(partes[1]) - 1;
        int day = Integer.parseInt(partes[2]);
        return CalendarDay.from(year, month, day);
    }

    public static String normalizarFecha(String fecha) {

        // Reemplazar "/" por "-"
        String f = fecha.replace("/", "-");

        // Separar partes
        String[] partes = f.split("-");

        // partes[0] = día
        // partes[1] = mes
        // partes[2] = año

        String dia = partes[0];
        String mes = partes[1];
        String año = partes[2];

        // Devolver en formato YYYY-MM-DD
        return año + "-" + mes + "-" + dia;
    }


    public static int obtenerSemanaActual() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    // ================================
    //      SEGUNDOS ↔ MINUTOS
    // ================================
    public static int secondsToMinutes(int seconds) {
        return seconds / 60;
    }

    public static int minutesToSeconds(int minutes) {
        return minutes * 60;
    }

    // ================================
    //      MINUTOS ↔ HORAS
    // ================================
    public static int minutesToHours(int minutes) {
        return minutes / 60;
    }

    public static int hoursToMinutes(int hours) {
        return hours * 60;
    }

    // ================================
    //      SEGUNDOS ↔ HORAS
    // ================================
    public static int secondsToHours(int seconds) {
        return seconds / 3600;
    }

    public static int hoursToSeconds(int hours) {
        return hours * 3600;
    }
}
