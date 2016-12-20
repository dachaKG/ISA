var services = angular.module('guest.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('guestService', ['$http', function($http){
	
	this.getLoggedUser = function(){
		return $http.get("/commonController/getLoggedUser");
	}
	
}]);