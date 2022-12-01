package com.servlets.web2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.*;

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
            if (request.getSession().getAttribute("table") instanceof Table table){
                sendTable(table, response);
            }
            else {
                sendTable(new Table(), response);
            }
        }
        else {
            final Instant startTime = Instant.now();
            double x = Double.parseDouble(request.getParameter("x"));
            double y = Double.parseDouble(request.getParameter("y"));
            double R = Double.parseDouble(request.getParameter("R"));

        try {
            if (isValid(x,y,R)){
                TableRow tableRow = new TableRow(x, y, R,
                        Instant.now().toEpochMilli(),
                        Duration.between(startTime, Instant.now()).toNanos(),
                        inArea(x,y,R));

                final Table table;
                if (request.getSession().getAttribute("table") instanceof Table localTable){
                    table = localTable;
                } else {
                    table = new Table();
                }

                table.addTableRow(tableRow);

                request.getSession().setAttribute("table", table);
                request.getSession().setAttribute("check", tableRow);
                sendBean(tableRow, response);
            }else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                throw new NumberFormatException("плохой ввод");
            }

        } catch (NumberFormatException e){
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }}
    private boolean isValid(final double x, final double y, final double R){
        final DecimalFormat form = new DecimalFormat("0.000");
        return ((x >= -8) && (x <= 8) && (y > -5.7) && (y < 5.7) &&
                ((R == 1)||(R==2)||(R==3)||(R==4)||(R==5)) &&
                (x == Double.parseDouble(form.format(x))) &&
                (y == Double.parseDouble(form.format(y))) &&
                (R == Double.parseDouble(form.format(R))));
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
    private void sendBean(TableRow answer, HttpServletResponse response) throws IOException {
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(answer.getJson());
    }
    private void sendTable(Table answer, HttpServletResponse response) throws IOException {
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(answer.getJson());
    }

}

