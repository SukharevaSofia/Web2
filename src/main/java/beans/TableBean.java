package beans;

import java.util.ArrayList;

import java.io.Serializable;
import com.google.gson.Gson;
public final class TableBean implements Serializable {
    private final ArrayList<TableRowBean> tableRowBeans;

    public TableBean() {
        tableRowBeans = new ArrayList<TableRowBean>();
    }

    public void addTableRowBean(TableRowBean tableRowBean){
        this.tableRowBeans.add(tableRowBean);
    }
    public String getJson(){
        return new Gson().toJson(this.tableRowBeans);
    }
}