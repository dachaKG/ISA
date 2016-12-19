var app = angular.module('loginRegistration.controllers', []);
 
app.controller('loginRegistrationController', ['$scope','loginRegistrationService', '$location',
  	function ($scope, loginRegistrationService, $location) {
	
		$scope.submitLogin = function () {            
			loginRegistrationService.logIn($scope.user).then(
				function (response) {
                    $scope.state = undefined;
                    if(response.data === "boss")
                    	$location.path('loggedIn/bossManager/list');
                    else if(response.data === "system")
                    	$location.path('loggedIn/systemManager/list');
                    else if(response.data === "restaurant")
                    	$location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Ne postoji korisnik sa tim parametrima.");
                }
			);
		}
}]);