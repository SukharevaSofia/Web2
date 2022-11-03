package com.servlets.web2;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import beans.DataBean;

@WebServlet(name = "areaCheckServlet", value = "/area-check-servlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/oopsies.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        Double x = Double.parseDouble(request.getParameter("x"));
        Double y = Double.parseDouble(request.getParameter("y"));
        Double R = Double.parseDouble(request.getParameter("R"));
        String st = request.getParameter("workingTime");

        DataBean dataBean = new DataBean();

        dataBean.setDataX(x);
        dataBean.setDataY(y);
        dataBean.setDataR(R);

        if (isValid(x,y,R)){

        }
        //TODO: обработка данных, передача из бина дальше
        //TODO: хранение бинов, передача в таблицу

    }
    private boolean isValid(Double x, Double y, Double R){
        if((x >= -3) && (x <= 5) && (y > -3) && (y < 5) &&
                ((R == 1)||(R==2)||(R==3)||(R==4)||(R==5))){
            return true;
        }
        return false;
    }
    private boolean isArea(Double x, Double y, Double R){
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
