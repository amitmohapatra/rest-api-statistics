# rest-api-statistics

## General Description

API is supposed to calculate real-time statistics summary based on transaction data records received for the last 60 seconds. Calculation of statistic is made in constant time and memory (O(1)).


## API endpoints

#### POST /transactions

This endpoint is called every time a new transaction happened.

Request:

    {
    	"amount": 12.3,
    	"timestamp": 2018-08-05T13:15:30.000Z
    }
where:
 - amount: double specifying the amount 
 - time: long specifying UTC time

Response: empty body with HTTP status 201

#### DELETE /transactions

Response: empty body with HTTP status 204

#### GET /statistics

This endpoint calculates and returns statistics in O(1) constant time and memory based on transaction data for the last 60 seconds.

Response:

    {
    	"sum": "1000",
    	"avg": "100",
    	"max": "200",
    	"min": "50",
    	"count": 10
    }
    

where:
 - sum: String specifying total sum of transaction values
 - avg: String specifying average of all transaction values
 - max: String specifying highest transaction value
 - max: String specifying lowest transaction value
 - count: long specifying total number of transactions happened
 


