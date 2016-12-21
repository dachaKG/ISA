var services = angular.module('employedBartender.services',['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('employedBartenderService',['$http', function($http){
		
	this.checkRights = function(){
		return $http.get("/bartender/checkRights");
	}
	
	this.findRestaurant = function(){
		return $http.get("/bartender/restaurant");
	}
	
	
}]);