package edu.famu.myinvestments.services;


import edu.famu.myinvestments.models.News;
import edu.famu.myinvestments.models.NewsResponse;
import edu.famu.myinvestments.models.Ticker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


//  Connect to external api
@Service
public class ExternalAPIService {

    WebClient webClient;

    //MAPS VALUE TO VALUE IN THE PROPERTIES FILE
    @Value("${my.apiKey}")
    String apiKey;

    public ExternalAPIService(){
        this.webClient = WebClient.create("https://api.polygon.io");
    }

    /*
           HOME PAGE --> Retrieve one Ticker based off of Ticker symbol
    Get a single ticker supported by Polygon.io.
    This response will have detailed information about the ticker and the company behind it.
    //GET  /vX/reference/tickers/{ticker}
     */

    public Mono<Ticker> getTickerById(String ticker){
        return this.webClient.get()
                .uri( "/vX/reference/tickers/{ticker}?apiKey=" + apiKey, ticker)
                .retrieve().bodyToMono(Ticker.class);
    }



    /*
            HOME PAGE --> STOCK CHART (Minute and Day
            Get the current minute, day, and previous day’s aggregate,
            as well as the last trade and quote for a single traded currency symbol.
            /v2/snapshot/locale/global/markets/forex/tickers/{ticker}
     */





    //Get the current trading status of the exchanges and overall financial markets
    //GET	/v1/marketstatus/now


    /*
     Get the most recent news articles relating to a stock ticker symbol,
     including a summary of the article and a link to the original source

    GET	v2/reference/news
    https://api.polygon.io/v2/reference/news?apiKey=*

     */

    public Flux<NewsResponse> getNews()
    {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/reference/news")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToFlux(NewsResponse.class);

    }

    /*
    public
    UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
    RequestBodySpec bodySpec = uriSpec.uri("/v1/marketstatus/now");

    */


}

// EXTRA ... NOT NEEDED RN
// Query all ticker symbols which are
// supported by Polygon.io. This API currently includes Stocks/Equities, Crypto, and Forex.
//GET	/v3/reference/tickers

/*

             *
             * Creating a WebClient Instance with Timeouts
             *
             *   Oftentimes, the default HTTP timeouts of 30 seconds are too slow for our needs, to customize this behavior,
             *  we can create an HttpClient instance and configure our WebClient to use it.


    @Bean
    public WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create()
                //set the connection timeout via the ChannelOption.CONNECT_TIMEOUT_MILLIS option
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn
                        //set the read and write timeouts using a ReadTimeoutHandler and a WriteTimeoutHandler,
                        // respectively
                        .addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);


                 Creating a WebClient Instance
                 The third option (and the most advanced one) is building a client by using the
                 DefaultWebClientBuilder class, which allows full customization


        return WebClient.builder()
                .baseUrl("https://api.polygon.io/")
                //configure a response timeout using the responseTimeout directive
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.AUTHORIZATION, apiKey)
                .defaultUriVariables(Collections.singletonMap("https://api.polygon.io/", "url"))
                .build();
    }

 */