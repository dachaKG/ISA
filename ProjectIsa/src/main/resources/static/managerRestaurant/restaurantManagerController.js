var app = angular.module('restaurantManager.controllers', []);

app.controller('restaurantManagerController', ['$scope','$window','restaurantManagerService', '$location',
	function ($scope,$window, restaurantManagerService, $location) {
		function checkRights() {
			restaurantManagerService.checkRights().then(
				function (response) {
					if(response.status == 200) {
						$scope.restaurantManager = response.data;
						findAll();
						showFreeBidders();
					}
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();
		
		function findAll() {
			restaurantManagerService.findRestaurant().then(
				function (response) {
					$scope.restaurant = response.data;
					$scope.restaurantOrders = response.data.restaurantOrders;
					for(w = 0;w<$scope.restaurantOrders.length;w++) {
						$scope.restaurantOrders[w].startDate = new Date($scope.restaurantOrders[w].startDate).toDateString();
						$scope.restaurantOrders[w].endDate = new Date($scope.restaurantOrders[w].endDate).toDateString();
					}
					$scope.waiters = response.data.waiters;
					$scope.cooks = response.data.cooks;
					$scope.bartenders = response.data.bartenders;
					$scope.bidders = response.data.bidders;

					$scope.averageRate = calculateAverageRate(response.data.rateRestaurant);
					myMap();
					
				}
	        );
		}
		
		function calculateAverageRate (rates) {
			var rate = 0;
			if(rates.length > 0)
				for (var i =0;i<rates.length;i++) {
					rate += rates[i].rate;
				}
			else
				return "/";
			return rate/rates.length;
		}
		
		function showFreeBidders() {
			restaurantManagerService.showFreeBidders().then(
				function (response) {
					$scope.freeBidders = response.data;
				}
	        );
		}
		
		$scope.saveDrink = function() {
			restaurantManagerService.saveDrink($scope.drink).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.saveDish = function() {
			restaurantManagerService.saveDish($scope.dish).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.demissionBartender = function(bartender) {
			restaurantManagerService.deleteBartender(bartender).then(
				function (response) {
                    alert("Successfully removed.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in deleting.");
                }
			);
		}
		
		$scope.demissionWaiter = function(waiter) {
			restaurantManagerService.deleteWaiter(waiter).then(
				function (response) {
                    alert("Successfully removed.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in deleting.");
                }
			);
		}
		
		$scope.demissionCook = function(cook) {
			restaurantManagerService.deleteCook(cook).then(
				function (response) {
                    alert("Successfully removed.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in deleting.");
                }
			);
		}
		
		$scope.update = function() {
			restaurantManagerService.updateMangerProfile($scope.restaurant.restaurantManager).then(
				function (response) {
                    alert("Successfully change.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in changing.");
                }
			);
		}
		
		$scope.saveBidder = function() {
			restaurantManagerService.saveBidder($scope.bidder).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		$scope.connectBidder = function(bidder) {
			restaurantManagerService.connectBidder(bidder).then(
				function (response) {
                    alert("Successfully added.");
                    $scope.state = undefined;
                    findAll();
                    $location.path('loggedIn/restaurantManager/info');
                },
                function (response) {
                    alert("Error in adding.");
                }
			);
		}
		
		
		
		
		$scope.saveEmployed = function() {
			//$scope.drink.restaurant = $scope.restaurant;
			if($scope.employedType == 'Waiter') {
				restaurantManagerService.saveWaiter($scope.employed).then(
					function (response) {
	                    alert("Successfully added.");
	                    $scope.state = undefined;
	                    findAll();
	                    $location.path('loggedIn/restaurantManager/info');
	                },
	                function (response) {
	                    alert("Error in adding.");
	                }
				);
			}
			else if($scope.employedType == 'Cook') {
				restaurantManagerService.saveCook($scope.employed).then(
					function (response) {
						alert("Successfully added.");
		                $scope.state = undefined;
		                findAll();
		                $location.path('loggedIn/restaurantManager/info');
		            },
		            function (response) {
		            	alert("Error in adding.");
		            }
				);
			}
			else if($scope.employedType == 'Bartender') {
				restaurantManagerService.saveBartender($scope.employed).then(
					function (response) {
						alert("Successfully added.");
		                $scope.state = undefined;
		                findAll();
		                $location.path('loggedIn/restaurantManager/info');
					},
		            function (response) {
						alert("Error in adding.");
		            }
				);
			}
		}	
		
		$scope.offerDetails = function(restaurantOrder) {
			$scope.restaurantOrderDetails = restaurantOrder;
            $location.path('loggedIn/restaurantManager/offerDetails');
		}
		
		$scope.acceptRestaurantOrder = function(offer) {
			restaurantOrderr = $scope.restaurantOrderDetails;
			if(restaurantOrderr.orderActive == "open") {
				restaurantOrderr.idFromChoosenBidder = offer.bidder.id;
				restaurantOrderr.startDate = new Date(restaurantOrderr.startDate).getTime();
				restaurantOrderr.endDate = new Date(restaurantOrderr.endDate).getTime();
				restaurantManagerService.acceptRestaurantOrder(restaurantOrderr).then(
					function (response) {
	                    alert("Successfully added.");
	                    $scope.state = undefined;
	                    findAll();
	                    $location.path('loggedIn/restaurantManager/info');
	                },
	                function (response) {
	                    alert("Error in adding.");
	                }
				);
			}else
				alert('Offer is closed.')
			
		}
		
		$scope.makeConfig = function(){
			var xaxis = $("input[name='xaxis']").val();
			var yaxis = $("input[name='yaxis']").val();
			restaurantManagerService.makeConfig(xaxis, yaxis).then(
				function(response){
					$window.location.reload();
				});
		}
		
		$scope.loadTables = function(){
			restaurantManagerService.getTables().then(
					function(response){
						//tables = response.data;
						
						var stolovi = [];
						var red = [];
						var lastXPos = 0;
						var counter = 0;
						angular.forEach(response.data, function(value, key){	// punjenje matrice stolova
							counter++;
							if(value.xpos == lastXPos){	
								red.push(value);
							}
							if((value.xpos != lastXPos) || counter==response.data.length ) {
								stolovi.push(red);
								red =[];
								red.push(value);
							}
							lastXPos = value.xpos;
						});
						$scope.tables = stolovi;
					});
		}
		
		$scope.addSegment = function(){
			restaurantManagerService.addSegment($scope.segment).then(
				function(response){
					$("input[name='segName']").val("");
				});
		}
		
		
		$scope.openModal = function(id){
			restaurantManagerService.getSegments().then(
				function(response){
					$scope.segments = response.data;
					$scope.tableId = id;
					var modal = document.getElementById('myModal');
					modal.style.display = "block";
				});
		}
		
		
		
		$scope.closeModal = function(){
			restaurantManagerService.updateTable($scope.tableId, $scope.table).then(
				function(response){
					var modal = document.getElementById('myModal');
					modal.style.display = "none";
					$window.location.reload();
				});
		}
		
		$scope.createNewOffer = function() {
			drink = $scope.newRestaurantOrder.drink
			dish = $scope.newRestaurantOrder.dish
			$scope.newRestaurantOrder.startDate = new Date($scope.newRestaurantOrder.startDate).toISOString();
			$scope.newRestaurantOrder.endDate = new Date($scope.newRestaurantOrder.endDate).toISOString();
		
			if(((dish === undefined || dish.id === "") && drink.id !== "") || (dish.id !== "" && (drink == null || drink.id === ""))) {
				restaurantManagerService.createNewOffer($scope.newRestaurantOrder).then(
					function (response) {
	                    alert("Successfully added.");
	                    $scope.state = undefined;
	                    findAll();
	                    $location.path('loggedIn/restaurantManager/info');
	                },
	                function (response) {
	                    alert("Error in adding.");
	                }
				);
			}
			else
				alert("Only one select field can be choose.");
		}
		
		function myMap() {
			var mapProp= {
			    zoom:15,
			    mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			pos = [];
			if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(function(position) {
			    	pos = {
			            lat: position.coords.latitude,
			            lng: position.coords.longitude
			        };
			        var marker = new google.maps.Marker({
			        	position:pos,
			        });

			        marker.setMap(map);
			        map.setCenter(pos);
			    }, function() {
			        handleLocationError(true, infoWindow, map.getCenter());
			    });
			}
			
			map=new google.maps.Map(document.getElementById("googleMap1"),mapProp);
			geocoder = new google.maps.Geocoder();
			address = $scope.restaurant.street + " " + $scope.restaurant.number + " , " + $scope.restaurant.city + " , " + $scope.restaurant.country; 
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
		
		$scope.changeShiftCookAction = function(){
			restaurantManagerService.changeShiftCookAction($scope.changeShiftCook).then(
				function(response){
					alert("Successfully added.");
                   });
		}
		
		$scope.changeShiftBartenderAction = function(){
			restaurantManagerService.changeShiftBartenderAction($scope.changeShiftBartender).then(
				function(response){
					alert("Successfully added.");
                   });
		}
		
		$scope.changeShiftWaiterAction = function(){
			restaurantManagerService.changeShiftWaiterAction($scope.changeShiftWaiter).then(
				function(response){
					alert("Successfully added.");
                   });
		}
		
		$scope.getWaiterWithInputName = function(){
			restaurantManagerService.getWaiterWithInputName($scope.waiterName).then(
				function(response){
					if(response.data != "") {
						alert("Successfully find waiter.");
						$scope.waiterRate = response.data.rate;
					} else
						alert("Waiter doesn't exist.");
		   });
		}
		
		$scope.geDishWithInputName = function(){
			restaurantManagerService.geDishWithInputName($scope.dishName).then(
					function(response){
						if(response.data != "-1.0") {
							alert("Successfully find dish.");
							$scope.dishRate = response.data;
						} else
							alert("Dish doesn't exist.");
			   });
		}
		
		$scope.calculateRevenues = function(){
			$scope.restaurantRevenues = 0;
			for(var i=0;i<$scope.restaurant.waiters.length;i++) {
				for(var j=0;j<$scope.restaurant.waiters[i].bills.length;j++) {
					if(new Date($scope.fromDateRevenues).getTime() < new Date($scope.restaurant.waiters[i].bills[j].date).getTime() < new Date($scope.toDateRevenues).getTime())
						$scope.restaurantRevenues += $scope.restaurant.waiters[i].bills[j].total;
				}
			}
		}
		
		$scope.showRevenuesForWaiters = function(){
			$scope.revenuesWaiter = [];
			for(var i=0;i<$scope.restaurant.waiters.length;i++) {
				revenues = 0;
				for(var j=0;j<$scope.restaurant.waiters[i].bills.length;j++) {
					revenues += $scope.restaurant.waiters[i].bills[j].total;
				}
				var waiter = {name:"", revenues:"0"};
				waiter['name'] = $scope.restaurant.waiters[i].firstname;
				waiter['revenues'] = revenues;
				$scope.revenuesWaiter.push(waiter);
			}
		}
}]);