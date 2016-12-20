var app = angular.module('restaurantManager.controllers', []);

app.controller('restaurantManagerController', ['$scope','restaurantManagerService', '$location',
	function ($scope, restaurantManagerService, $location) {
		function checkRights() {
			restaurantManagerService.checkRights().then(
				function (response) {
					if(response.data === 'true')
						findAll();
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
				}
	        );
			
			restaurantManagerService.findAllWaitresInRestaurant().then(
				function (response) {
					$scope.waiters = response.data;
				}
	         );
			restaurantManagerService.findAllCooksInRestaurant().then(
				function (response) {
					$scope.cooks = response.data;
				}
		     );
			restaurantManagerService.findAllBiddersInRestaurant().then(
				function (response) {
					$scope.bidders = response.data;
				}
		    );
		}
		
		$scope.saveDrink = function() {
			//$scope.drink.restaurant = $scope.restaurant;
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
			//$scope.drink.restaurant = $scope.restaurant;
			restaurantManagerService.saveDrink($scope.dish).then(
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
		
		$scope.saveWaiter = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			restaurantManagerService.saveWaiter($scope.waiter).then(
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
		
		$scope.saveCook = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			restaurantManagerService.saveCook($scope.cook).then(
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
		$scope.saveBidder = function() {
			//$scope.drink.restaurant = $scope.restaurant;
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
		
}]);