package com.example.biorythmservlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BiorythmServlet extends HttpServlet {

    private Date provided_date;
    private Date today;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        boolean skip_request = false;
        today = new Date();

        if (request.getParameter("birthday") == null || Objects.equals(request.getParameter("birthday"), ""))
            skip_request = true;


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            this.provided_date = formatter.parse(request.getParameter("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (provided_date.getTime() > today.getTime())
            skip_request = true;

        if (!skip_request)
            request.setAttribute("biorythmsList",
                    this.getBiorythmsList());


        request.getRequestDispatcher("/index")
                .forward(request, response);
    }


    private static Calendar getCalendarWithoutTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private List<Biorythm> getBiorythmsList() {
        List<Biorythm> Biorythms = new ArrayList<>();

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Calendar calendar = getCalendarWithoutTime(new Date(today.getTime() - (5 * DAY_IN_MS)));
        Calendar endCalendar = getCalendarWithoutTime(new Date(today.getTime() + (27 * DAY_IN_MS)));

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            Biorythms.add(new Biorythm(result, this.provided_date));
            calendar.add(Calendar.DATE, 1);
        }

        System.out.println("Bio size" + Biorythms.size());
        return Biorythms;
    }
}