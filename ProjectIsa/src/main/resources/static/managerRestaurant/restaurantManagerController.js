var app = angular.module('restaurantManager.controllers', []);

app.controller('restaurantManagerController', ['$scope','restaurantManagerService', '$location',
	function ($scope, restaurantManagerService, $location) {
		function findAll() {
			restaurantManagerService.findRestaurant().then(
				function (response) {
					$scope.restaurant = response.data;
				}
			);
		}
		findAll();
		
		$scope.saveDrink = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			restaurantManagerService.saveDrink($scope.drink).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('restaurantManager/info');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
			);
		}
		   
		
}]);