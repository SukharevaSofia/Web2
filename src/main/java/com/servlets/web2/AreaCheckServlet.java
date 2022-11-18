package com.servlets.web2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import beans.*;

@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("restore") instanceof String) {
            BeanChart chartEl = (BeanChart) request.getSession().getAttribute("table");
            if (chartEl == null) {
                new BeanChart();
            }
            sendRestore(chartEl.getJson(), response);
        }
        else {
        long startTime = System.nanoTime();
        boolean result = false;
        Double x = Double.parseDouble(request.getParameter("x"));
        Double y = Double.parseDouble(request.getParameter("y"));
        Double R = Double.parseDouble(request.getParameter("R"));
        try {
            String st = request.getParameter("workingTime");

            if (isValid(x,y,R)){
                if(inArea(x,y,R)){
                    result = true;
                }
                //dataBean.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());

                OffsetDateTime currentTimeObject = OffsetDateTime.now(ZoneOffset.UTC);
                String currentTime;
                try {
                    currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString();

                } catch (Exception exception) {
                    currentTime = "no info";
                }
                String executionTime = String.format("%.9f",(Double)((System.nanoTime() - startTime)/100000000.0));

                BeanChart chartEl = (BeanChart) request.getSession().getAttribute("table");
                if (chartEl == null) {
                    new BeanChart();
                }

                DataBean dataBean = new DataBean(x, y, R, currentTime, executionTime, result);
                chartEl.getChartEl().add(dataBean);
                request.getSession().setAttribute("table", chartEl);
                request.getSession().setAttribute("check", dataBean);
                send(dataBean, response);


            }else {
                throw new NumberFormatException();
            }

        }catch (NumberFormatException e){
            request.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }}
    private boolean isValid(Double x, Double y, Double R){
        if((x >= -3) && (x <= 5) && (y > -3) && (y < 5) &&
                ((R == 1)||(R==2)||(R==3)||(R==4)||(R==5))){
            return true;
        }
        return false;
    }
    private boolean inArea(Double x, Double y, Double R){
        if((Math.abs(x) <= R)&&(Math.abs(y) <= R)){
            if((x > 0)&&(y > 0)){
                return false;
            }else if ((x <= 0)&&(y>0)){
                return ((x*x + y*y) <= (R*R));
            } else if ((x <= 0)&&(y<=0)) {
                return ((x + y) >= (-R));
            } else if ((x>0)&&(y<=0)) {
                return true;
            }
        }
        return false;
    }
    private void send(DataBean answer, HttpServletResponse response) throws IOException {
        String json = answer.getJson();
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(json);
    }
    private void sendRestore(String answer, HttpServletResponse response) throws IOException {
        String json = answer;
        response.setHeader("CacheCache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(json);
    }

}
