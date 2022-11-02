package com.example.web2;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public class ControllerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        getServletContext().getRequestDispatcher("/oops.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        request.setAttribute("scriptTime", System.nanoTime()*1000);
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String R = request.getParameter("R");
    }

    public void destroy() {
    }
}