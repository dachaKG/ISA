var services = angular.module('guest.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('guestService', ['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/GuestController/checkRights");
	}
	
	this.getLoggedUser = function(){
		return $http.get("/commonController/getLoggedUser");
	}
	
}]);