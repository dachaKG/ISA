var services = angular.module('bidder.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('bidderService', ['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/bidder/checkRights");
	}

	this.getOffers = function(){
		return $http.get("/bidder/getOffers");
	}
	
	this.changeValueOfPrice = function(restaurantOrderForChange){
		return $http.post("/bidder/changeValueOfPrice",restaurantOrderForChange);
	}
	
	this.getActiveOffers = function(){
		return $http.get("/bidder/getActiveOffers");
	}
	
	this.competeWithInsertedValue = function(restaurantOrderForChange){
		return $http.post("/bidder/competeWithInsertedValue",restaurantOrderForChange);
	}
}]);