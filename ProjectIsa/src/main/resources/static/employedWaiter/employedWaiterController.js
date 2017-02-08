var app = angular.module('employedWaiter.controllers', []);

app.controller('employedWaiterController', [ '$scope', 'employedWaiterService','$location',
	function($scope, employedWaiterService, $location) {
	
	  	$scope.today = function() {
		    $scope.dt = new Date();
		  };
		$scope.today();
		function checkRights(){
			employedWaiterService.checkRights().then(
				function(response){
					if(response.data === 'true')
						findAll();
					else{
						$location.path('login')
						alert("Access denied");
					}
				}
			);
		}
		checkRights();
		
		function findAll(){
			employedWaiterService.findWaiter().then(
				function(response){
					$scope.waiter = response.data;
				}
			);
			
			employedWaiterService.orders().then(
				function(response){
					$scope.orders = response.data;
				}
			)
			
			employedWaiterService.readyDrinks().then(
				function(response){
					$scope.readyDrinks = response.data;
				}
			);
			
			employedWaiterService.readyFood().then(
				function(response){
					$scope.readyFood = response.data;
				}
			);
			
			employedWaiterService.readyOrders().then(
				function(response){
					$scope.readyOrders = response.data;
				}
			);
		}
		
		$scope.makeBill = function(order){
			employedWaiterService.makeBill(order).then(
				function(response){
					$scope.readyOrders.splice($scope.readyOrders.indexOf(order),1);
				},
				function(response){
					alert("Error while signal");
				}
			);
		}	
		
		$scope.showShift = function(){
			$scope.shift = datePicker();
		}
		
		$scope.sendToEmployed = function(order){
			employedWaiterService.sendToEmployed(order.id).then(
					function(response){
						$scope.orders.splice($scope.orders.indexOf(order),1);
					},
					function(response){
						alert("Error while signal");
					}
			);
		}
}]);