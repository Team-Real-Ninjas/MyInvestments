package edu.famu.myinvestments.controllers;


import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.StockChart;
import edu.famu.myinvestments.models.Ticker;
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
@RequestMapping("/Home")
public class HomeController {

    private StockService stockService;
    private UserService userService;


    @Autowired
    public HomeController(StockService stockService, UserService userService) {

        this.stockService = stockService;
        this.userService = userService;
    }
    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/{id}")
    public String getUsers(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByID(id);

        //The model is essentially a Map with unique keys. You really should define unique keys:
        //These keys will be
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstName());
        return "home";
    }

    /*
    @GetMapping("/"){


        return
    }
     */

    @GetMapping("/{tickerId}")
    public String getTickerChartInformation(@PathVariable("tickerId")String id, Model model) throws ExecutionException, InterruptedException {
        List<StockChart> chart = stockService.getStockChartByTickerId(id);
        model.addAttribute("chart", chart);
        return "home";
    }
}
