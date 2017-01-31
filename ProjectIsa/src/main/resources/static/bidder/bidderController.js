var app = angular.module('bidder.controllers', []);
 
app.controller('bidderController', ['$scope','bidderService', '$location',
  	function ($scope, bidderService, $location) {
	
		function checkRights() {
			bidderService.checkRights().then(
				function (response) {
					if(response.data === 'true') {
						findBidder();
						getOffers();
					}
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();
		
		function findBidder() {
			bidderService.findBidder().then(
				function (response) {
					$scope.bidder = response.data;
				}
	        );
		}
		
		function getOffers() {
			bidderService.getOffers().then(
				function (response) {
					data = response.data;
					result = []
					correctOrder = null
					for(i = 0;i<data.length;i++) {
						for(j = 0;j<data.length;j++) {
							if(data[i].offers[j].bidderid !== $scope.bidder.id) {
								correctOffer = data[i].offers[j]
								correctOrder = data[i]
								correctOrder.offers = correctOffer
								result.push(correctOrder)
							}
						}
					}
					$scope.restaurantOrders = result
					for(w = 0;w<$scope.restaurantOrders.length;w++) {
						$scope.restaurantOrders[w].startDate = new Date($scope.restaurantOrders[w].startDate).toDateString();
						$scope.restaurantOrders[w].endDate = new Date($scope.restaurantOrders[w].endDate).toDateString();
					}
				}
	        );
		}
	}
]);