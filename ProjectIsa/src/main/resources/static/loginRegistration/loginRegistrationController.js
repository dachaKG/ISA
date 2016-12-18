var app = angular.module('loginRegistration.controllers', []);


app.controller('loginRegistrationController', ['$scope','loginRegistrationService', '$location',
  	function ($scope, loginRegistrationService, $location) {
	
		$scope.submitLogin = function () {            
        //   alert("Uspesno dodat.");
           $location.path('loggedIn/home');
		};
		
		
}]);