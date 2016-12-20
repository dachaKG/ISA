var app = angular.module('employedBartender.controllers', []);

app.controller('employedBartenderController', [ '$scope', 'employedBartenderService','$location',
	function($scope, employedBartenderService, $location) {
		function checkrights(){
			employedbartenderService.checkRights().then(
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
			employedbartenderService.findRestaurant().then(
				function(response){
					$scope.restaurant = response.data;
				}
			);
		}
}]);