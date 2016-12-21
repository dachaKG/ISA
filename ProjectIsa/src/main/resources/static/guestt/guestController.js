var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','guestService', '$location',
  	function ($scope, guestService, $location) {
	
		$scope.getLoggedUser = function() {
			
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }		
			)
		}		
}]);