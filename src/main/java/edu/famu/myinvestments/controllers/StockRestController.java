package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.Investments;
import edu.famu.myinvestments.models.News;
import edu.famu.myinvestments.models.StockChart;
import edu.famu.myinvestments.models.Ticker;
import edu.famu.myinvestments.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/stock")
public class StockRestController {

    private StockService stockService;

    @Autowired
    public StockRestController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping(path = "/")
    public List<Ticker> getAllTickers() throws ExecutionException, InterruptedException {
        return this.stockService.getAllTickers();
    }

    @GetMapping({"/{id}"})
    public Ticker getTickerById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return this.stockService.getTickerById(id);
    }

    @GetMapping(path = "/news/")
    public List<News> getAllStockNews() throws ExecutionException, InterruptedException {
        return this.stockService.getAllStockNews();
    }



    @GetMapping("/StockChart/{tickerId}")
        public List<StockChart> getStockChartByTickerId(@PathVariable(name="tickerId") String tickerId) throws ExecutionException, InterruptedException {
        return stockService.getStockChartByTickerId(tickerId);
    }

}
