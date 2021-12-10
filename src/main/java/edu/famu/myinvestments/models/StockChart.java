package edu.famu.myinvestments.models;

import com.google.cloud.firestore.annotation.DocumentId;

public class StockChart extends BaseStockChart {
    private Ticker ticker;

    public StockChart(){

    }

    public StockChart(String id, Ticker ticker, Number close, Number high, Number low, Number open) {
        this.id = id;
        this.ticker = ticker;
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
