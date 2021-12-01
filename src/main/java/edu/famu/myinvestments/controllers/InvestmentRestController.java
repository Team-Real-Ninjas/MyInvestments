package edu.famu.myinvestments.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.famu.myinvestments.models.*;
import edu.famu.myinvestments.services.InvestmentService;
import edu.famu.myinvestments.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/investments")
public class InvestmentRestController {
        public InvestmentService investmentService;

        @Autowired
        public InvestmentRestController(InvestmentService investmentService)
        {
            this.investmentService = investmentService;
        }

        @GetMapping("/{id}")
        //GET SPECIFIC POST BASED OFF OF POSTID
        public Investments getInvestmentById(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
            return investmentService.getInvestmentById(id);
        }

        @PostMapping(path = "/")
        //CREATE NEW POST
        public String createInvestment(@RequestBody RestInvestments investments) throws ExecutionException, InterruptedException {
            return investmentService.createInvestment(investments);
        }

        //HOW TO MAP AN UPDATE

        @PutMapping("/")
        //UPDATE INVESTMENT BASED OFF OF ID
        public boolean updateInvestment(@RequestBody String investments) throws ExecutionException, InterruptedException, JsonProcessingException {
            return investmentService.updateInvestment(investments);
        }
}
