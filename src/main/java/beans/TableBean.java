package beans;

import java.util.ArrayList;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
public final class TableBean implements Serializable {
    private final ArrayList<TableRowBean> tableRowsBean;

    public TableBean() {
        tableRowsBean = new ArrayList<TableRowBean>();
    }

    public void addTableRowBean(TableRowBean tableRowBean){
        this.tableRowsBean.add(tableRowBean);
    }
    public String getJson(){
        return new Gson().toJson(this.tableRowsBean);
    }


}