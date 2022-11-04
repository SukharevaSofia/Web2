package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBean implements Serializable {
    public Double dataX;
    public Double dataY;
    public Double dataR;
    public Boolean result;
    public String currentTime;
    public String workingTime;

    public DataBean(){}

    public Double getDataX(){
        return dataX;
    }

    public void setDataX(Double dataX) {
        this.dataX = dataX;
    }

    public Double getDataY() {
        return dataY;
    }

    public void setDataY(Double dataY) {
        this.dataY = dataY;
    }

    public Double getDataR() {
        return dataR;
    }

    public void setDataR(Double dataR) {
        this.dataR = dataR;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }



}
