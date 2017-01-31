var app = angular.module('restaurantManager.controllers', []);

app.controller('restaurantManagerController', ['$scope','restaurantManagerService', '$location',
	function ($scope, restaurantManagerService, $location) {
		function checkRights() {
			restaurantManagerService.checkRights().then(
				function (response) {
					if(response.data === 'true') {
						findAll();
						showFreeBidders();
					}
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();
		
		function findAll() {
			restaurantManagerService.findRestaurant().then(
				function (response) {
					$scope.restaurant = response.data;
					$scope.restaurantOrders = response.data.restaurantOrders;
					for(w = 0;w<$scope.restaurantOrders.length;w++) {
						$scope.restaurantOrders[w].startDate = new Date($scope.restaurantOrders[w].startDate).toDateString();
						$scope.restaurantOrders[w].endDate = new Date($scope.restaurantOrders[w].endDate).toDateString();
					}
					$scope.waiters = response.data.waiters;
					$scope.cooks = response.data.cooks;
					$scope.bartenders = response.data.bartenders;
					$scope.bidders = response.data.bidders;
				}
	        );
		}
		
		function showFreeBidders() {
			restaurantManagerService.showFreeBidders().then(
				function (response) {
					$scope.freeBidders = response.data;
				}
	        );
		}
		
		$scope.saveDrink = function() {
			restaurantManagerService.saveDrink($scope.drink).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.saveDish = function() {
			restaurantManagerService.saveDish($scope.dish).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.update = function() {
			restaurantManagerService.updateMangerProfile($scope.restaurant.restaurantManager).then(
				function (response) {
                    alert("Successfully change.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in changing.");
                }
			);
		}
		
		$scope.saveBidder = function() {
			restaurantManagerService.saveBidder($scope.bidder).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.connectBidder = function(bidder) {
			restaurantManagerService.connectBidder(bidder).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		
		
		
		$scope.saveEmployed = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			if($scope.employedType == 'Waiter') {
				restaurantManagerService.saveWaiter($scope.employed).then(
					function (response) {
	                    alert("Successfully added.");
	                    $scope.state = undefined;
	                    findAll();
	                    $location.path('loggedIn/restaurantManager/info');
	                },
	                function (response) {
	                    alert("Error in adding.");
	                }
				);
			}
			else if($scope.employedType == 'Cook') {
				restaurantManagerService.saveCook($scope.employed).then(
					function (response) {
						alert("Successfully added.");
		                $scope.state = undefined;
		                findAll();
		                $location.path('loggedIn/restaurantManager/info');
		            },
		            function (response) {
		            	alert("Error in adding.");
		            }
				);
			}
			else if($scope.employedType == 'Bartender') {
				restaurantManagerService.saveBartender($scope.employed).then(
					function (response) {
						alert("Successfully added.");
		                $scope.state = undefined;
		                findAll();
		                $location.path('loggedIn/restaurantManager/info');
					},
		            function (response) {
						alert("Error in adding.");
		            }
				);
			}
		}	
		
		$scope.offerDetails = function(restaurantOrder) {
			$scope.restaurantOrderDetails = restaurantOrder;
            $location.path('loggedIn/restaurantManager/offerDetails');
		}
		
		$scope.acceptRestaurantOrder = function(offer) {
			restaurantOrderr = $scope.restaurantOrderDetails;
			restaurantOrderr.idFromChoosenBidder = offer.bidder.id;
			restaurantOrderr.startDate = new Date(restaurantOrderr.startDate).getTime();
			restaurantOrderr.endDate = new Date(restaurantOrderr.endDate).getTime();
			restaurantManagerService.acceptRestaurantOrder(restaurantOrderr).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.createNewOffer = function() {
			drink = $scope.newRestaurantOrder.drink
			dish = $scope.newRestaurantOrder.dish
			if(((dish === undefined || dish.id === "") && drink.id !== "") || (dish.id !== "" && (drink == null || drink.id === ""))) {
				restaurantManagerService.createNewOffer($scope.newRestaurantOrder).then(
					function (response) {
	                    alert("Successfully added.");
	                    $scope.state = undefined;
	                    findAll();
	                    $location.path('loggedIn/restaurantManager/info');
	                },
	                function (response) {
	                    alert("Error in adding.");
	                }
				);
			}
			else
				alert("Only one select field can be choose.");
		}
		
}]);