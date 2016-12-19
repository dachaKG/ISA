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
	
}]);
