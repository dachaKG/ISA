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
		
		
		$scope.findFriends = function(){
			var inputStr = $("#inputStr").val();
			guestService.findFriends(inputStr).then(
					function(response){
						$scope.guests = response.data;
					}
			)
		}
		
		
		$scope.update = function() {
			guestService.updateGuestProfile($scope.loggedUser).then(
				function (response) {
                    alert("Successfully change.");
                    $scope.state = undefined;
                    $location.path('loggedIn/guest/profile');
                },
                function (response) {
                    alert("Error in changing.");
                }
			);
		}
		
		
		
		
}]);