var app = angular.module('bidder.controllers', []);
 
app.controller('bidderController', ['$scope','bidderService', '$location',
  	function ($scope, bidderService, $location) {
	
		//provera da li je logovan ponudjac
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
		
		//trazenje na serveru ponudjaca koji je logovan
		function findBidder() {
			bidderService.findBidder().then(
				function (response) {
					$scope.bidder = response.data;
				}
	        );
		}
		
		//izlistavanje svih ponuda za tog ponudjaca
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
		
		//odabrana ponuda za promenu
		$scope.change = function(restaurantOrder){
			x = document.getElementById("artical");
			x.setAttribute("value", restaurantOrder.dish.name);
			y = document.getElementById("price");
			price = restaurantOrder.offers.price;
			y.setAttribute("value", price);
			$scope.restaurantOrderForChange = restaurantOrder;
		}
		
		//probati da se promeni vrednost ponude koja je prethodno odabrana
		$scope.changeValueOfPrice = function(){
			price = document.getElementById("price").value;
			offers = $scope.restaurantOrderForChange.offers;
			//glupost :D al ne znam kako da posalje 2 parametra :D 
			offers.bidder.registrated = price;
			bidderService.changeValueOfPrice(offers).then(
				function (response) {
					if(response.data ===  "1") {
	                	location.reload(true);
					}
					else
						alert('Erorr.Maybe is offer closed.');
				}
		    );
		}
	}
]);