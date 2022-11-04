package com.servlets.web2;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
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
        getServletContext().getRequestDispatcher("/oopsies.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        long startTime = System.nanoTime();
        Double x = Double.parseDouble(request.getParameter("x"));
        Double y = Double.parseDouble(request.getParameter("y"));
        Double R = Double.parseDouble(request.getParameter("R"));
        try {
            String st = request.getParameter("workingTime");

            DataBean dataBean = new DataBean();
            /*
            dataBean.setDataX(x);
            dataBean.setDataY(y);
            dataBean.setDataR(R);
            */
            if (isValid(x,y,R)){
                if(inArea(x,y,R)){
                    dataBean.setResult(true);
                } else if (inArea(x,y,R)) {
                    dataBean.setResult(false);
                }
                //dataBean.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());

                OffsetDateTime currentTimeObject = OffsetDateTime.now(ZoneOffset.UTC);
                String currentTime;
                try {
                    currentTimeObject = currentTimeObject.minusMinutes(Long.parseLong(request.getParameter("timezone")));
                    currentTime = currentTimeObject.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                } catch (Exception exception) {
                    currentTime = "no info";
                }
                String executionTime = String.format("%.9f",(Double)((System.nanoTime() - startTime)/100000.0));

                BeanChart chartEl = (BeanChart) request.getSession().getAttribute("table");
                if (chartEl == null) chartEl == new BeanChart();


            }else {
                throw new NumberFormatException();
            }

            //TODO: хранение бинов, передача в таблицу
            request.getSession().setAttribute("chartBean", dataBean);

        }catch (NumberFormatException e){

        }


    }
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

}
