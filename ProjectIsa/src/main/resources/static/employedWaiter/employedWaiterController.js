var app = angular.module('employedWaiter.controllers', []);

app.controller('employedWaiterController', [ '$scope', 'employedWaiterService','$location',
	function($scope, employedWaiterService, $location) {
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
		}
		
}]);