var services = angular.module('employedWaiter.services',['ngResource']);

services.service('employedWaiterService',['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/waiter/checkRights");
	}
	
	this.findWaiter = function(){
		return $http.get("/waiter");
	}
	
	this.readyFood = function(id){
		return $http.get("/waiter/readyFood");
	}
	
	this.readyDrinks = function(id){
		return $http.get("/waiter/readyDrinks");
	}
	
	this.readyOrders = function(id){
		return $http.get("/waiter/readyOrders");
	}
	
	this.orders = function(){
		return $http.get("/waiter/orders");
	}
	
	this.sendToEmployed = function(id){
		return $http.put("/waiter/sendToEmployed/" + id);
	}
	
	this.makeBill = function(order){
		return $http.post("/waiter/makeBill", order);
	}
	
	this.changeOrder = function(id){
		return $http.get("/waiter/changeOrder/" + id);
	}
	
	this.restaurant = function(){
		return $http.get("/waiter/getRestaurant");
	}
	
	this.orderFood = function(id, order){
		return $http.put("/waiter/addDish/"+id, order);
	}
	
	this.orderDrink = function(id, order){
		return $http.put("/waiter/addDrink/"+id, order);
	}
	
	this.removeFood = function(id, order){
		return $http.get("/waiter/removeDish/"+id+"/"+order);
	}
	
	this.removeDrink = function(id, order){
		return $http.get("/waiter/removeDrink/"+id+"/"+order);
	}
	
	this.reservations = function(){
		return $http.get("/waiter/getReservations")		
	}
	
	this.newOrderFood = function(id, reservationId, order){
		return $http.put("/waiter/newOrderDish/"+id+"/"+reservationId, order)
	}
	
	this.newOrderDrink = function(id, reservationId, order){
		return $http.put("/waiter/newOrderDrink/"+id+"/"+reservationId, order)
	}
	
	this.newOrder = function(){
		return $http.get("/waiter/newOrder");
	}
	
	this.makeNewOrder = function(reservationId, order){
		return $http.put("/waiter/makeNewOrder/"+reservationId,order);
	}
	
	
}]);