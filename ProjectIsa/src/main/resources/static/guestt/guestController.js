var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','$window','guestService', '$location',
  	function ($scope,$window, guestService, $location) {
		function checkRights() {			
			guestService.checkRights().then(
				function (response) {
					if(response.data === 'true'){
						findAll();
					}
					else {
						$location.path('login');
					}
				}
			);
		}
		checkRights();	
		
		function findAll(){
			guestService.restaurants().then(
				function(response){
					$scope.restaurants = response.data;
					
				}
			);
			
			
		}
	
		$scope.getLoggedUser = function() {
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }		
			)
		}
		
		$scope.findFriends = function(){
			if($scope.inputStr !== '') {
				guestService.findFriends($scope.inputStr).then(
					function(response){
						$scope.guests = response.data;
					}
				)
			}
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
		
		$scope.sendRequest = function(id) {
			guestService.sendRequest(id);
			$window.location.reload();
		}
		
		$scope.listFriends = function(){
			guestService.listFriends().then(
				function (response) {
					$scope.friends = [];	//pravim niz trojki: Guest za prikaz, status(pending, friends, rejected), id prijateljstva
					angular.forEach(response.data, function(value, key){
						if(value.friendSendRequest.id == $scope.loggedUser.id){	
							var guestAndStatus = {guest: value.friendReciveRequest, status: value.status, id:value.id };
							$scope.friends.push(guestAndStatus);
						}
						else{
							if(value.status == "Friends"){	//jer ne zelim da stoji pending ili rejected status kod primaoca zahteva
								var guestAndStatus = {guest: value.friendSendRequest, status: value.status,  id:value.id };
								$scope.friends.push(guestAndStatus);
							}
						}
					});
		        }		
			);
		}
		
		$scope.getRequests = function(){
			guestService.findAllRecivedPendingRequests().then(
					function (response) {
						$scope.recivedPending = response.data;
					}
			)
		}
		
		$scope.acceptRequest = function(id){
			guestService.acceptFriendRequest(id).then(
					function() {
						alert("Friend request acccepted.");
						$window.location.reload();
					}
			)
		}
		
		$scope.rejectRequest = function(id){
			guestService.rejectFriendRequest(id).then(
					function() {
						alert("Friend request rejected.");
						$window.location.reload();
					}
			)
		}
		
		$scope.unfriend = function(id){
			guestService.unfriend(id).then(
			function(){
				alert("Unfriend Successful.");
				$window.location.reload();
			})
		}
		
		$scope.menu = function(restaurant){
			guestService.find(restaurant.id).then(
					function(response){
					//	$window.location.path = "guestRestaurantMenu.html" 
					//	$scope.restaurant = response.data;
					},
					function(response){
						alert("Error while signal");
					}
			)
		}
		
		$scope.find = function(restaurant){
			guestService.find(restaurant.id).then(
				function(response){
					myMap(restaurant);
					
				},
				function(response){
					alert("Error while signal");
				}
			);
		}
		
		
		function myMap(restaurant) {
			var mapProp= {
			    zoom:15,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			
			var map=new google.maps.Map(document.getElementById("googleMap1"),mapProp);
			pos = [];
						
			geocoder = new google.maps.Geocoder();
			address = restaurant.street + " " + restaurant.number + " , " + restaurant.city + " , " + restaurant.country; 
			geocoder.geocode( { 'address': address}, function(results, status) {
			      if (status == 'OK') {
			        map.setCenter(results[0].geometry.location);
			        var marker = new google.maps.Marker({
			            map: map,
			            position: results[0].geometry.location
			        });
			        
			        var flightPath = new google.maps.Polyline({
					    path: [results[0].geometry.location, pos],
					    strokeColor: "#0000FF",
					    strokeOpacity: 0.8,
					    strokeWeight: 2
					  });
			        flightPath.setMap(map);
			      } else {
			        alert('Geocode was not successful for the following reason: ' + status);
			      }
			    });
		}	
		
}]);