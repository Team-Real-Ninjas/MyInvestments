package edu.famu.myinvestments.controllers;


import edu.famu.myinvestments.models.StockChart;
import edu.famu.myinvestments.models.User;
import edu.famu.myinvestments.services.StockService;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/mysmartinvestments/home")
public class HomeController {

    private StockService stockService;


    @Autowired
    public HomeController(StockService stockService) {

        this.stockService = stockService;
    }


    @GetMapping("/Stock/{tickerId}")
    public String getUserInformation(@PathVariable("tickerId")String id, Model model) throws ExecutionException, InterruptedException {
        List<StockChart> chart = stockService.getStockChartByTickerId(id);
        model.addAttribute("chart", chart);
        return "StockChartsById";
    }
}
