package edu.famu.myinvestments.models;
/*
{
 "count": 1,
 "request_id": "31d59dda-80e5-4721-8496-d0d32a654afe",
 "results": {
  "active": true,
  "address": {
   "address1": "One Apple Park Way",
   "city": "Cupertino",
   "state": "CA"
  },
  "cik": "0000320193",
  "composite_figi": "BBG000B9XRY4",
  "currency_name": "usd",
  "last_updated_utc": "2020-12-27T00:00:00Z",
  "locale": "us",
  "market": "stocks",
  "market_cap": 2082042128180,
  "name": "Apple Inc.",
  "outstanding_shares": 17001800000,
  "phone_number": "(408) 996-1010",
  "primary_exchange": "XNAS",
  "share_class_figi": "BBG001S5N8V8",
  "sic_code": "3571",
  "sic_description": "ELECTRONIC COMPUTERS",
  "ticker": "AAPL",
  "type": "CS"
 },
 "status": "OK"
}
 */

//CREATE A CLASS FOR EVERY CALL TO API DATABASE
//MAP REQUEST 1-> 1 INCLUDING ALL VARIABLESSSSSSSSS
 class Address{

    //address class
}
public class Ticker {

    protected String name;
    protected String sic_description;
    //enum [stocks, crypto, fx]
    protected String  market;
    protected String currency_name;
    protected String primary_exchange;


    public Ticker() {
    }

    public Ticker(String name, String currency_name, String market, String primary_exchange, String sic_description) {
        this.name = name;
        this.currency_name = currency_name;
        this.primary_exchange = primary_exchange;
        this.market = market;
        this.sic_description = sic_description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getPrimary_exchange() {
        return primary_exchange;
    }

    public void setPrimary_exchange(String primary_exchange) {
        this.primary_exchange = primary_exchange;
    }

    public String getSic_description() {
        return sic_description;
    }

    public void setSic_description(String sic_description) {
        this.sic_description = sic_description;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
