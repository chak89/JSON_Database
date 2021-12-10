# JSON Database

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
A client-server application that support parallelization and processing multiple requests at the same time, allows the clients to store their data on the server in JSON format.  
https://hyperskill.org/projects/65
	
## Technologies
Project is created with:
* Java 11.0.8
* Gradle 7.1.1
* Jcommander
* Gson
	
## Setup
1. Start server first.  
2. Star client with arguments.
3. Consult project webpage for usage

Starting the server:
```
> java Main
Server started!
```

Starting the clients:
```
> java Main -in setFile.json 
Client started!
Sent:
{
   "type":"set",
   "key":"person",
   "value":{
      "name":"Elon Musk",
      "car":{
         "model":"Tesla Roadster",
         "year":"2018"
      },
      "rocket":{
         "name":"Falcon 9",
         "launches":"87"
      }
   }
}
Received: {"response":"OK"}
```

