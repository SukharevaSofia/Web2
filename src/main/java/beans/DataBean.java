package beans;

import com.google.gson.Gson;

import java.io.Serializable;

public class DataBean implements Serializable {
    private Double dataX;
    private Double dataY;
    private Double dataR;
    private Boolean result;
    private String currentTime;
    private String workingTime;

    public DataBean(){}
    public DataBean(double dataX, double dataY, double dataR, String current, String working, boolean result){
        this.dataX = dataX;
        this.dataY = dataY;
        this.dataR = dataR;
        this.currentTime = current;
        this.workingTime = working;
        this.result = result;
    }

    public Double getDataX(){
        return dataX;
    }

    public Double getDataY() {
        return dataY;
    }

    public Double getDataR() {
        return dataR;
    }

    public Boolean getResult() {
        return result;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
