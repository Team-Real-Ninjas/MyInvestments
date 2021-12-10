package edu.famu.myinvestments.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

public class RestStockChart extends BaseStockChart{
    private DocumentReference ticker;

    RestStockChart(){

    }

    public RestStockChart(String id, DocumentReference ticker, Number close,
                          Number high, Number low, Number open) {
        this.id = id;
        this.ticker = ticker;
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
    }

    public RestStockChart(String id, String ticker, Number close,
                          Number high, Number low, Number open) {
        this.id = id;
        this.setTicker(ticker);
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
    }

    public DocumentReference getTicker() {
        return ticker;
    }

    //Connects document reference to database
    //Needed for updating
    public void setTicker(String ticker) {
        Firestore db = FirestoreClient.getFirestore();
        this.ticker = db.collection("Ticker").document(ticker);
    }


}
