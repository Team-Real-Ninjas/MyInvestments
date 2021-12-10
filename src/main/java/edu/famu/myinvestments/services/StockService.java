package edu.famu.myinvestments.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import edu.famu.myinvestments.models.News;
import edu.famu.myinvestments.models.Ticker;
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
}
