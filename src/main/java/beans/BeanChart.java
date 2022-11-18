package beans;

import java.util.Deque;
import java.util.LinkedList;

import java.io.Serializable;
import com.google.gson.Gson;
public class BeanChart implements Serializable {
    private Deque<DataBean> chartEl;

    public BeanChart() {
        chartEl = new LinkedList<>();
    }

    public Deque<DataBean> getChartEl() {
        return chartEl;
    }
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this.chartEl);
    }
}