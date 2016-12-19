'use strict';

angular.module('routerApp', ['ui.router', 
	'systemManager.services', 'systemManager.controllers', 
	'restaurantManager.services', 'restaurantManager.controllers',
	'bossManager.services', 'bossManager.controllers','loginRegistration.services', 'loginRegistration.controllers'])
.config(function($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/login');
        
        $stateProvider
        
        .state('login', {
        	url : '/login',
          	templateUrl : 'loginRegistration/login.html',
          	controller : 'loginRegistrationController'
         })
         
         .state('registration', {
        	url : '/registration',
        	templateUrl : 'loginRegistration/registration.html'	
         })
         
         
         .state('loggedIn', {
        	url : '/loggedIn',
        	templateUrl : 'loggedIn.html'
         })

        .state('loggedIn.home', {
        	url : '/home',
          	templateUrl : 'home.html'
         })
         
         .state('loggedIn.bossManager', {
        	url : '/bossManager',
          	templateUrl : 'managerBoss/bossManagerPartial.html',
            controller : 'bossManagerController'
         })
        .state('loggedIn.bossManager.list', {
        	url : '/list',
          	templateUrl : 'managerBoss/bossManagerList.html'
        })
         .state('loggedIn.bossManager.new', {
        	url : '/new',
        	templateUrl : 'managerBoss/bossManagerNew.html'
        })
        
        
        .state('loggedIn.systemManager', {
        	url : '/systemManager',
          	templateUrl : 'managerSystem/systemManagerPartial.html',
            controller : 'systemManagerController'
        })
        .state('loggedIn.systemManager.list', {
        	url : '/list',
          	templateUrl : 'managerSystem/systemManagerList.html'
        })
        .state('loggedIn.systemManager.newRestaurantManager', {
        	url : '/newRestaurantManager',
        	templateUrl : 'managerSystem/systemManagerNewRestaurantManager.html'
        })
         .state('loggedIn.systemManager.newRestaurant', {
        	url : '/newRestaurant',
        	templateUrl : 'managerSystem/systemManagerNewRestaurant.html'
        })
        
        
        .state('loggedIn.restaurantManager', {
        	url : '/restaurantManager',
          	templateUrl : 'managerRestaurant/restaurantManagerPartial.html',
            controller : 'restaurantManagerController'
         })
         .state('loggedIn.restaurantManager.info', {
        	url : '/info',
          	templateUrl : 'managerRestaurant/restaurantManagerInfo.html'
        })
        .state('loggedIn.restaurantManager.newDrink', {
			url : '/newDrink',
			templateUrl : 'managerRestaurant/restaurantManagerNewDrink.html'
		})
		.state('loggedIn.restaurantManager.newDish', {
			url : '/newDish',
			templateUrl : 'managerRestaurant/restaurantManagerNewDish.html'
		})
	        
        
        
  
});