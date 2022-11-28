package com.servlets.web2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import beans.*;

@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public final class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("restore") != null) {
            if (request.getSession().getAttribute("table") instanceof TableBean tableBean){
                sendTable(tableBean, response);
            }
            else {
                sendTable(new TableBean(), response);
            }
        }
        else {
        final Instant startTime = Instant.now();
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        double R = Double.parseDouble(request.getParameter("R"));
        try {
            if (isValid(x,y,R)){
                TableRowBean tableRowBean = new TableRowBean(x, y, R,
                        Instant.now().toEpochMilli(),
                        Duration.between(startTime, Instant.now()).toNanos(),
                        inArea(x,y,R));

                final TableBean tableBean;
                if (request.getSession().getAttribute("table") instanceof TableBean localTableBean){
                    tableBean = localTableBean;
                } else {
                    tableBean = new TableBean();
                }

                tableBean.addTableRowBean(tableRowBean);

                request.getSession().setAttribute("table", tableBean);
                request.getSession().setAttribute("check", tableRowBean);
                sendBean(tableRowBean, response);
            }else {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e){
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }}
    private boolean isValid(final double x, final double y, final double R){
        return ((x >= -3) && (x <= 5) && (y > -3) && (y < 5) &&
                ((R == 1)||(R==2)||(R==3)||(R==4)||(R==5)));
    }
    private boolean inArea(final double x, final double y, final double R){
        if((Math.abs(x) <= R)&&(Math.abs(y) <= R)){
            if((x > 0)&&(y > 0)){
                return false;
            }else if ((x <= 0)&&(y>0)){
                return ((x*x + y*y) <= (R*R));
            } else if ((x <= 0)&&(y<=0)) {
                return ((x + y) >= (-R));
            } else return (x > 0) && (y <= 0);
        }
        return false;
    }
    private void sendBean(TableRowBean answer, HttpServletResponse response) throws IOException {
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(answer.getJson());
    }
    private void sendTable(TableBean answer, HttpServletResponse response) throws IOException {
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(answer.getJson());
    }

}

