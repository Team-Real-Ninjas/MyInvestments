package edu.famu.myinvestments.models;

import com.google.cloud.firestore.annotation.DocumentId;

public class BaseStockChart {


    @DocumentId
    protected String id;
    protected Number close;
    protected Number high;
    protected Number low;
    protected Number open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getClose() {
        return close;
    }

    public void setClose(Number close) {
        this.close = close;
    }

    public Number getHigh() {
        return high;
    }

    public void setHigh(Number high) {
        this.high = high;
    }

    public Number getLow() {
        return low;
    }

    public void setLow(Number low) {
        this.low = low;
    }

    public Number getOpen() {
        return open;
    }

    public void setOpen(Number open) {
        this.open = open;
    }

}
