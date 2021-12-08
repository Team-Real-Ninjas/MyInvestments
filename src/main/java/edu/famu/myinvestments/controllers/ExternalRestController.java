package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.NewsResponse;
import edu.famu.myinvestments.models.Ticker;
import edu.famu.myinvestments.services.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/external")
public class ExternalRestController {

    private ExternalAPIService eService;

    @Autowired
    public ExternalRestController(ExternalAPIService eService){
        this.eService = eService;
    }

    @GetMapping("/news")
    public Flux<NewsResponse> getNewsInfo()
    {
        return eService.getNews();
    }

    @GetMapping("/{id}")
    public Mono<Ticker> getTickerByIdNo(@PathVariable("id") String id){
        return eService.getTickerById(id);
    }

}
