package com.example.biorythmservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In servlet");

        String userName = request.getParameter("userName").trim();
        if(userName == null || "".equals(userName)){
            userName = "Guest";
        }

        String greetings = "Hello " + userName;

        response.setContentType("text/plain");
        response.getWriter().write(greetings);
    }

}