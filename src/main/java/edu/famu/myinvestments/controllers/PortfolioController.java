package edu.famu.myinvestments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.famu.myinvestments.models.Comment;
import edu.famu.myinvestments.models.Investments;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.User;
import edu.famu.myinvestments.services.InvestmentService;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
//@RequestMapping("/portfolio")
public class PortfolioController {

    private InvestmentService investmentService;
    private UserService userService;

    @Autowired
    public PortfolioController(InvestmentService investmentService, UserService userService) {
        this.investmentService = investmentService;
        this.userService = userService;
    }

    /*
     * Home Page
     * @param user id
     * @param model. Front end value passed by default to the controller
     * @return name of the view (html file)
     */

    @GetMapping("/portfolio/{user}")
    public String getInvestments(@PathVariable("user") String user, Model model) throws ExecutionException, InterruptedException {
        List<Investments> investments = userService.getInvestmentByUserId(user);
        model.addAttribute("investments", investments);
        return "portfolio";
    }


    /*
     * Post Detail Page
     * @param post id
     * @param model
     * @return name of the view
     */

    //HOW DOES THIS WORK?
    //USES getInvestmentById function that then model attribute mspd
    @GetMapping("/investments/{id}")
    public String getInvestment(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        Investments investments = investmentService.getInvestmentById(id);
        model.addAttribute("investments", investments);
        return "portfolio"; //ADD A ?
    }





    /*
    @GetMapping("/investments/{id}")
    public String updateInvestment(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException, JsonProcessingException {
        Boolean investmentStatus = investmentService.updateInvestment(id);
        model.addAttribute("investments", investmentStatus);
        return "investments";
    }

     */

}
