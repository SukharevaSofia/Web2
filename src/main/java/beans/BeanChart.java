package beans;



import java.util.Deque;
import java.util.LinkedList;

public class BeanChart {
    private Deque<DataBean> chartEl;

    public BeanChart() {
        chartEl = new LinkedList<>();
    }

    public Deque<DataBean> getRaws() {
        return chartEl;
    }
}