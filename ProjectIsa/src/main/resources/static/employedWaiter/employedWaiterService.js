var services = angular.module('employedWaiter.services',['ngResource']);

services.service('employedWaiterService',['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/waiter/checkRights");
	}
	
	this.findWaiter = function(){
		return $http.get("/waiter");
	}
	
}]);