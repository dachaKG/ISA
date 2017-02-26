var app = angular.module('employedCook.controllers',[]);

app.controller('employedCookController',['$scope','employedCookService','$location',
	function($scope, employedCookService, $location){
		function checkRights(){
			employedCookService.checkRights().then(
				function(response){
					if(response.data === 'true')
						findAll();
					else{
						location.path('login');
						alert("Access denied");
					}
				}
			);
		}
		checkRights();
		
		function findAll(){
			employedCookService.findCook().then(
				function(response){
					$scope.cook = response.data;
				}
			);
			
			employedCookService.employedCooks().then(
				function(response){
					$scope.employedCooks = response.data;
				}
			)
			
			employedCookService.findAllOrders().then(
				function(response){
					$scope.orders = response.data;
				}
			);
			
			employedCookService.receivedFood().then(
				function(response){
					$scope.receivedFood = response.data;
				}
			);
			
			/*employedCookService.readyFood().then(
				function(response){
					$scope.readyFood = response.data;
				}
			);*/
		}
		
		$scope.received = function(foodOrder){
			employedCookService.received(foodOrder.id).then(
				function(response){
					$scope.orders.splice($scope.orders.indexOf(foodOrder),1);
					findAll();
				},
				function(response){
					alert("Error while signal");
				}
			);
		}
		
		$scope.ready = function(order){
			employedCookService.ready(order.id).then(
				function(response){
					$scope.receivedFood.splice($scope.receivedFood.indexOf(order),1);
					findAll();
				},
				function(response){
					alert("Error while signal");
				}
			);
		}
		
		$scope.changeProfile = function(){
			employedCookService.changeProfile($scope.cook.id,$scope.cook).then(
				function(response){
					alert("successfully changed profile");
					$scope.state = undefined;
					findAll();
					$location.path('loggedIn/cook/home');
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.changePassword = function(){
			employedCookService.changePassword($scope.cook.id,$scope.cook).then(
				function(response){
					alert("successfully changed password");
					$scope.state = undefined;
					findAll();
					$location.path('loggedIn/cook/home');
				},
				function(response){
					alert("Error in changing");
				}
			);
		}
		
		$scope.changedShift = function(cook) {
		    var today = Date.now();
		    var tomorrow = new Date(Date.now() + 86400000 * 1);
			var step = 1;
			var datesArr = [];
			var temp = 0;
			if(cook.defaultShift != "First") 
				temp = 1;
			else
				temp = 0;
			for(var i = 0;i<300;i++) {
				s = i % 14;
				if(s >= 7){
					day = new Date(Date.now() +temp * 86400000 + 86400000 *  i*step);
					datesArr.push(day);
				}
			}
			
			
			$('#date').multiDatesPicker('destroy');
			$('#date').multiDatesPicker({
		        numberOfMonths: 1,
		        addDates: datesArr
			});
			if(temp == 1)
				$('#date').multiDatesPicker('toggleDate', new Date());
		}
		
}]);