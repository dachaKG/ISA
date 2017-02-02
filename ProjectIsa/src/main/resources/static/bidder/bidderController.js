var app = angular.module('bidder.controllers', []);
 
app.controller('bidderController', ['$scope','bidderService', '$location',
  	function ($scope, bidderService, $location) {
	
		//provera da li je logovan ponudjac
		function checkRights() {
			bidderService.checkRights().then(
				function (response) {
					if(response.status == 200) {
						$scope.bidder = response.data;
						getOffers();
						getActiveOffers();
					}
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();

		// izlistavanje svih ponuda na koje je do sada konkurisao logovani ponudjac
		function getOffers() {
			bidderService.getOffers().then(
				function (response) {
					data = response.data;
					result = []
					correctOrder = null
					//izlistavanje ponuda samo za tog ponudjaca od svih mogucih, iz restorana gde je taj ponudjac dao ponudu
					for(i = 0;i<data.length;i++) {
						for(j = 0;j<data[i].offers.length;j++) {
							if(data[i].offers[j].bidder.id === $scope.bidder.id) {
								correctOffer = data[i].offers[j]
								correctOrder = data[i]
								correctOrder.offers = correctOffer
								result.push(correctOrder)
							}
						}
					}
					
					//konverzija vremena za lep prikaz, jer daje u ms
					$scope.restaurantOrders = result
					for(w = 0;w<$scope.restaurantOrders.length;w++) {
						$scope.restaurantOrders[w].startDate = new Date($scope.restaurantOrders[w].startDate).toISOString();
						$scope.restaurantOrders[w].endDate = new Date($scope.restaurantOrders[w].endDate).toISOString();
					}
				}
	        );
		}
		

		// izlistavanje svih ponuda za logovanog ponudjaca od svih restorana gde
		// moze da konkurise
		function getActiveOffers() {
			bidderService.getActiveOffers().then(
				function (response) {
					$scope.restaurantOrdersFromAllRestaurants = response.data;
					//konverzija vremena za lep prikaz, jer daje u ms
					for(w = 0;w<$scope.restaurantOrdersFromAllRestaurants.length;w++) {
						$scope.restaurantOrdersFromAllRestaurants[w].startDate = new Date($scope.restaurantOrdersFromAllRestaurants[w].startDate).toISOString();
						$scope.restaurantOrdersFromAllRestaurants[w].endDate = new Date($scope.restaurantOrdersFromAllRestaurants[w].endDate).toISOString();
					}
				}
	        );
		}
		
		//kliknuto je na neku startu ponudu ponudjaca za promenu, prikazati je u formi
		$scope.change = function(restaurantOrder){
			x = document.getElementById("artical");
			if(restaurantOrder.dish != null)
				x.setAttribute("value", restaurantOrder.dish.name);
			else if(restaurantOrder.drink != null)
				x.setAttribute("value", restaurantOrder.drink.name);
			y = document.getElementById("price");
			price = restaurantOrder.offers.price;
			y.setAttribute("value", price);
			$scope.restaurantOrderForChange = restaurantOrder;
		}
		
		//kliknuto je na neku ponudu restorana za takmicenje, prikazati je u formi
		$scope.compete = function(restaurantOrder){
			x = document.getElementById("articalForCompete");
			if(restaurantOrder.dish != null)
				x.setAttribute("value", restaurantOrder.dish.name);
			else if(restaurantOrder.drink != null)
				x.setAttribute("value", restaurantOrder.drink.name);
			$scope.restaurantOrderForCompete = restaurantOrder;
		}
		
		//probati da se promeni vrednost ponude koja je prethodno odabrana
		$scope.changeValueOfPrice = function(){
			price = document.getElementById("price").value;
			offers = $scope.restaurantOrderForChange.offers;
			//glupost :D al ne znam kako da posalje 2 parametra :D 
			offers.bidder.registrated = price;
			bidderService.changeValueOfPrice(offers).then(
				function (response) {
					if(response.status == 200) {
	                	location.reload(true);
					}
					else
						alert('Erorr.Maybe is offer closed.');
				}
		    );
		}
		
		//dodavanje nove ponude u neki restoran
		$scope.competeWithInsertedValue = function(){
			price = document.getElementById("priceForCompete").value;
			restaurantOrderForCompete = $scope.restaurantOrderForCompete;
			//glupost :D al ne znam kako da posalje 2 parametra :D 
			restaurantOrderForCompete.idFromChoosenBidder = price;
			bidderService.competeWithInsertedValue(restaurantOrderForCompete).then(
				function (response) {
					if(response.status ==  200) {
	                	location.reload(true);
					}
					else
						alert('Forbidden operation.Maybe you have one offer for that order or maybe oreder is closed.');
				}
		    );
		}
	}
]);