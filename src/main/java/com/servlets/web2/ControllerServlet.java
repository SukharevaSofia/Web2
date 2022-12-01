package com.servlets.web2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controllerServlet", value = "/controller-servlet")
public final class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        //getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("workingTime", System.nanoTime());

        if (request.getParameter("restore") instanceof String ||
                (request.getParameter("x") instanceof String &&
                        request.getParameter("y") instanceof String &&
                        request.getParameter("R") instanceof String)){
            getServletContext().getRequestDispatcher("/area-check-servlet").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}