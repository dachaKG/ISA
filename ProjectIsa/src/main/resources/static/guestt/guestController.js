var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','guestService', '$location',
  	function ($scope, guestService, $location) {
		function checkRights() {			
			guestService.checkRights().then(
					function (response) {
						if(response.data === 'true'){
							
						}
						else {
							$location.path('login');
						}
					}
			);
		}
		checkRights();	
	
	
		$scope.getLoggedUser = function() {
			
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }		
			)
		}		
}]);