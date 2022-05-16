package com.example.biorythmservlet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.lang.Math.sin;

public class Biorythm {

    private final Date day_date;
    private final Date birthday;
    private double physical;
    private double emotional;
    private double intellectual;

    public Biorythm(Date day_date, Date birthday) {
        this.day_date = day_date;
        this.birthday = birthday;
        this.calculate_day();
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    private void calculate_day() {
        LocalDate dateBefore = convertToLocalDateViaSqlDate(this.birthday);
        LocalDate dateAfter = convertToLocalDateViaSqlDate(this.day_date);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        this.physical = sin(Math.PI * 2 * noOfDaysBetween / 23);
        this.emotional = sin(Math.PI * 2 * noOfDaysBetween / 28);
        this.intellectual = sin(Math.PI * 2 * noOfDaysBetween / 33);

    }


    public long getDay_date() {
        return day_date.getTime();
    }

    public double getPhysical() {
        return physical;
    }

    public double getEmotional() {
        return emotional;
    }

    public double getIntellectual() {
        return intellectual;
    }
}
