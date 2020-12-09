This project contains below modules

1. 	Config server:
	This is the server which stores the centralize configuration for project
	
2. 	eureka-naming-server:
	This is the service registration server with which all other project register so that other microservices can call each other 

3. 	currency-conversion-service:
	This is the microservice which will get the details from user like amount, from currency, to currency etc and call currency-exchange-service to get the exchange rate
	This will have feign client to call exchange rate service
	
4. 	currency-exchange-service:
	This will return the actual exchange rate based on passed information
	
5. 	limits-service:
	This is the dummy service which is created for the purpose of showing use of config server. 
	
6. 	zuul-api-gateway-server:
	This is the gateway-server and as name suggests it is the API gateway for all other microservices in the application
	

Attached diagram for system architecture which we have developed in this project.

