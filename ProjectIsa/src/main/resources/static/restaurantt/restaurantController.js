var app = angular.module('restaurant.controllers', []);

app.controller('restaurantController', ['$scope','restaurantService', 'restaurantManagerService', '$location',
	function ($scope, restaurantService,restaurantManagerService, $location) {
		function findAll() {
			restaurantService.findAll().then(
				function (response) {
					$scope.restaurants = response.data;
				}
			);
			restaurantManagerService.findAllFreeManagers().then(
				function (response) {
					$scope.restaurantManagers = response.data;
				}
			);
		}
		findAll();
		
		
		$scope.save = function () {            
			restaurantService.save($scope.restaurant).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    $location.path('restaurant/list');
                }
            ); 	
		};
	    $scope.delete = function (restaurant){
	    	restaurantService.delete(restaurant.id).then(
	    		function (response) {
	    		     $scope.restaurants.splice($scope.restaurants.indexOf(restaurant), 1);
			    }
	    	);
		};		 
}]);