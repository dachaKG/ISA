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
			
			employedWaiterService.restaurant().then(
				function(response){
					$scope.restaurant = response.data;
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
			
			employedWaiterService.reservations().then(
				function(response){
					$scope.reservations = response.data;
				}
			);
			
			employedWaiterService.newOrder().then(
				function(response){
					$scope.newOrder = response.data;
				}
			)
			
			$scope.reservation = "";
		}
		
		$scope.changedValue = function(reservationId) {
			//findAll();
		    $scope.reservation = reservationId.id;
		    employedWaiterService.newOrder().then(
					function(response){
						$scope.newOrder = response.data;
					}
				)
		    
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
		
		$scope.changeOrder = function(order){
			employedWaiterService.changeOrder(order.id).then(
					function(response){
						$scope.changedOrder = response.data;
						$location.path('loggedIn/waiter/changeOrder');
			});				
		}
		
		$scope.orderFood = function(dish){
			employedWaiterService.orderFood(dish.id,$scope.changedOrder).then(
				function(response){
					$scope.changedOrder = response.data;
					$scope.state = undefined;
					findAll();
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.orderDrink = function(drink){
			employedWaiterService.orderDrink(drink.id, $scope.changedOrder).then(
				function(response){
					$scope.changedOrder = response.data;
					$scope.state = undefined;
					findAll();
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.removeDish = function(dish){
			employedWaiterService.removeFood(dish.id, $scope.changedOrder.id).then(
				function(response){
					$scope.changedOrder.food.splice($scope.changedOrder.food.indexOf(dish),1);
					
					$scope.state = undefined;
					findAll();
				}
			)
		}
		
		$scope.removeDrink = function(drink){
			employedWaiterService.removeDrink(drink.id, $scope.changedOrder.id).then(
				function(response){
					$scope.changedOrder.drinks.splice($scope.changedOrder.drinks.indexOf(drink),1);
					//$scope.changeOrder
					$scope.state = undefined;
					findAll();
				}
			)
		}
		
		$scope.makeOrder = function(){
			$location.path('loggedIn/waiter/orders');
		}
		
		$scope.newOrderDrink = function(drink){
			employedWaiterService.newOrderDrink(drink.id, $scope.newOrder).then(
				function(response){
					$scope.newOrder = response.data;
					$scope.state = undefined;
					//findAll();
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.newOrderFood = function(dish){
			employedWaiterService.newOrderFood(dish.id, $scope.reservation, $scope.newOrder).then(
				function(response){
					$scope.newOrder = response.data;
					$scope.state = undefined;
					//findAll();
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.newOrderDrink = function(drink){
			employedWaiterService.newOrderDrink(drink.id, $scope.reservation, $scope.newOrder).then(
				function(response){
					$scope.newOrder = response.data;
					$scope.state = undefined;
					//findAll();
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.makeNewOrder = function(){
			employedWaiterService.makeNewOrder($scope.reservation, $scope.newOrder).then(
				function(response){
					$location.path('loggedIn/waiter/home');
					findAll();
					
				}
			)
		}
}]);