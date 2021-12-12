package edu.famu.myinvestments.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StockService {



    public List<Ticker> getAllTickers() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore(); // Connecting to db
        List<Ticker> Tickers = new ArrayList(); // create's an empty List
        ApiFuture<QuerySnapshot> queryTicker = db.collection("Ticker").get();

        for (DocumentSnapshot doc : queryTicker.get().getDocuments()) { //Iterate through list

            Tickers.add(doc.toObject(Ticker.class)); // Covert Data to Json
        }
        return Tickers;
    }

    public Ticker getTickerById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore(); // Connecting to db
        Ticker TickerResult;

        DocumentReference tickerRef = db.collection("Ticker").document(id);
        // DocumentSnapshot contains data read from a document in a Firestore database , invalid ref = null
        DocumentSnapshot doc = (DocumentSnapshot) tickerRef.get().get();

        return (doc.toObject(Ticker.class));
    }


    public List<News> getAllStockNews() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore(); // Connecting to db

        List<News> StockNews = new ArrayList();
        ApiFuture<QuerySnapshot> queryTicker = db.collection("News").get();

        for (DocumentSnapshot doc : queryTicker.get().getDocuments()) { //Iterate through list

            StockNews.add(doc.toObject(News.class)); // Covert Data to Json
        }
        return StockNews;
    }

    public List<StockChart> getStockChartByTickerId(String tickerId) throws ExecutionException, InterruptedException {
        List<StockChart> StockByTickers = new ArrayList();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference tickerRef = db.collection("Ticker").document(tickerId);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = tickerRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        //Convert JSON into User class object
        Ticker ticker = userDoc.toObject(Ticker.class);

        //Query for posts by user
        Query postQuery = db.collectionGroup("StockChart").whereEqualTo("ticker", ticker);
        ApiFuture<QuerySnapshot> querySnapshot = postQuery.get();

        //loop over results and create Post objects
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {

            StockByTickers.add(new StockChart(
                    doc.getId(),
                    ticker,
                    doc.getDouble("close"),
                    doc.getDouble("high"),
                    doc.getDouble("low"),
                    doc.getDouble("open")));

        }

        return StockByTickers;
    }
}
