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

import lombok.Data;

//CREATE A CLASS FOR EVERY CALL TO API DATABASE
//MAP REQUEST 1-> 1 INCLUDING ALL VARIABLESSSSSSSSS

@Data
public class Ticker {

    protected String name;
    protected String sic_description;
    //enum [stocks, crypto, fx]
    protected String  market;
    protected String currency_name;
    protected String primary_exchange;
}
