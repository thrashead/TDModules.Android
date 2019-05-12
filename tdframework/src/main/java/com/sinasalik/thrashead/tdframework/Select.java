package com.sinasalik.thrashead.tdframework;

public class Select {
    public Integer top;
    public String orderColumn;
    public OrderBy orderBy;
    public String[] columns;

    public Integer getTop() {
        return top;
    }
    public String getOrderColumn() {
        return orderColumn;
    }
    public OrderBy getOrderBy() {
        return orderBy;
    }
    public String[] getColumns() {
        return columns;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }
    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public enum OrderBy {
        Asc,
        Desc
    }
}
