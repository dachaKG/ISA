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
	
		function findAll() {
			restaurantManagerService.findRestaurant().then(
				function (response) {
					$scope.restaurant = response.data;
				},
                function (response) {
                    alert("Greska pri dodavanju.");
                }
			);
		}
		checkRights();
		
		$scope.saveDrink = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			restaurantManagerService.saveDrink($scope.drink).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
			);
		}
		   
		
}]);