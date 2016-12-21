var app = angular.module('loginRegistration.controllers', []);
 
var firstLoginId = null;

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
                    else if(response.data === "guest")
                    	$location.path('loggedIn/guest/home');
                    else if(response.data === "bidder")
                    	$location.path('loggedIn/bidder/home');
                    else if(response.data === "bartender")
                    	$location.path('loggedIn/bartender/home');
                    else if(response.data === "cook")
                    	$location.path('login');
                    else if(response.data === "waiter")
                    	$location.path('login');
                    else {
                    	firstLoginId = response.data;
                    	$location.path('firstLogin');
                    }
				},
                function (response) {
                    alert("Ne postoji korisnik sa tim parametrima.");
                }
			);
		}
		
		$scope.submitRegistration = function () {  
			loginRegistrationService.save($scope.user).then(
				function (response) {
                    alert("Uspesno registrovan.");
                },
                function (response) {
                    alert("Greska pri registraciji.");
                }
            ); 	
		};
		
		$scope.firstLogin = function () {  
			$scope.user.firstname = "zz";
			$scope.user.lastname = "zz";
			loginRegistrationService.firstLogin(firstLoginId,$scope.user).then(
				function (response) {
					$location.path('login');
	            }
            ); 	
		};
		
		
		$scope.logOut = function() {
			loginRegistrationService.logOut().then(
				function (response) {
					$location.path('login/logout');
	            }		
			)
		}
		
		
		
}]);