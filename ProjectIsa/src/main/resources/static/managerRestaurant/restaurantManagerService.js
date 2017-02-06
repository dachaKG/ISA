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
	
	this.saveBidder = function(bidder){
		return $http.post("/restaurantManager/restaurant/saveBidder",bidder);
	}
	
	this.saveBartender = function(bartender){
		return $http.post("/restaurantManager/restaurant/saveBartender",bartender);
	}

	this.deleteBartender = function(bartender){
		return $http.post("/restaurantManager/restaurant/deleteBartender",bartender);
	}
	
	this.deleteCook = function(cook){
		return $http.post("/restaurantManager/restaurant/deleteCook",cook);
	}
	
	this.deleteWaiter = function(waiter){
		return $http.post("/restaurantManager/restaurant/deleteWaiter",waiter);
	}
	
	this.showFreeBidders = function(){
		return $http.get("/restaurantManager/showFreeBidders");
	}
	
	this.connectBidder = function(bidder){
		return $http.post("/restaurantManager/restaurant/connectBidder",bidder);
	}
	
	
	this.createNewOffer = function(offer){
		return $http.post("/restaurantManager/restaurant/createNewOffer",offer);
	}
	
	this.acceptRestaurantOrder = function(restaurantOrderr){
		return $http.post("/restaurantManager/restaurant/acceptRestaurantOrder",restaurantOrderr);
	}
	
	this.updateMangerProfile = function(restaurantManager){
		return $http.put("/restaurantManager/"+restaurantManager.id,restaurantManager);
	}
	
	this.makeConfig = function(xaxis,yaxis){
		return $http.put("/restaurantManager/restaurant/makeConfig/"+xaxis+"/"+yaxis);
	}
	
	this.getTables = function(){
		return $http.get("/restaurantManager/restaurant/getTables");
	}
	
	this.addSegment = function(segment){
		return $http.post("/restaurantManager/restaurant/addSegment", segment);
	}
	
	this.getSegments = function(){
		return $http.get("/restaurantManager/restaurant/getSegments");
	}
	
	this.updateTable = function(id, table){
		return $http.put("/restaurantManager/restaurant/table/"+id, table);
	}
	
}]);