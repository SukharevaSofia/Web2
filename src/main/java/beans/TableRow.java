package beans;

import com.google.gson.Gson;

import java.io.Serializable;

public final class TableRow implements Serializable{
    private double dataX;
    private double dataY;
    private double dataR;
    private boolean result;
    public long currentTime;
    private long workingTime;

    public TableRow(){}
    public TableRow(double dataX, double dataY, double dataR, long current, long working, boolean result){
        this.dataX = dataX;
        this.dataY = dataY;
        this.dataR = dataR;
        this.currentTime = current;
        this.workingTime = working;
        this.result = result;
    }

    public double getDataX(){
        return dataX;
    }

    public double getDataY() {
        return dataY;
    }

    public double getDataR() {
        return dataR;
    }

    public boolean getResult() {
        return result;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getWorkingTime() {
        return workingTime;
    }

    public String getJson(){
        return new Gson().toJson(this);
    }

}
