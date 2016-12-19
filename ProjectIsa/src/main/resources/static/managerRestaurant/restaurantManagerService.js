var services = angular.module('restaurantManager.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('restaurantManagerService', ['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/restaurantManager/checkRights");
	}
	
	this.findRestaurant = function(){
		return $http.get("/restaurantManager/restaurant");
	}
	
	this.saveDrink = function(drink){
		return $http.post("/restaurantManager/restaurant/saveDrink",drink);
	}
	
	this.saveDish = function(dish){
		return $http.post("/restaurantManager/restaurant/saveDish",dish);
	}	
	
	this.saveWaiter = function(waiter){
		return $http.post("/restaurantManager/restaurant/saveWaiter",waiter);
	}
	
	this.saveCook = function(cook){
		return $http.post("/restaurantManager/restaurant/saveCook",cook);
	}
	
	this.findAllWaitresInRestaurant = function(){
		return $http.get("/restaurantManager/restaurant/waitres");
	}
	
	this.findAllCooksInRestaurant = function(){
		return $http.get("/restaurantManager/restaurant/cooks");
	}
	
	
}]);
