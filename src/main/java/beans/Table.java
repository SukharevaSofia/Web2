package beans;

import java.util.ArrayList;

import java.io.Serializable;

import com.google.gson.Gson;
public final class Table implements Serializable {
    private final ArrayList<TableRow> tableRows;

    public Table() {
        tableRows = new ArrayList<TableRow>();
    }

    public void addTableRow(TableRow tableRow){
        this.tableRows.add(tableRow);
    }
    public String getJson(){
        return new Gson().toJson(this.tableRows);
    }


}