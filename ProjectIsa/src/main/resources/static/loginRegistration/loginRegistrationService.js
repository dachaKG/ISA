var services = angular.module('loginRegistration.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('loginRegistrationService', ['$http', function($http){
	
	this.findAll = function(){
		return $http.get("/restaurantManager");
	}
	
}]);