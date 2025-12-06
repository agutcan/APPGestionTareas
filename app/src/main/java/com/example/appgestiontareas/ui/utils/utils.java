package com.example.appgestiontareas.ui.utils;

import android.icu.util.Calendar;

public class utils {
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
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
