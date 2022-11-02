package com.servlets.web2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/oopsies.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("scriptTime", System.nanoTime());
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String R = request.getParameter("R");

        if(x == null && y == null && R == null){
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }else {
            getServletContext().getRequestDispatcher("/area-check-servlet").forward(request, response);
        }
    }



}