'use strict';

angular.module('routerApp', ['ui.router', 
	'systemManager.services', 'systemManager.controllers', 
	'restaurantManager.services', 'restaurantManager.controllers',
	'bossManager.services', 'bossManager.controllers',])
.config(function($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/home');
        
        $stateProvider

        .state('home', {
        	url : '/home',
          	templateUrl : 'home.html'
         })
         
         .state('bossManager', {
        	url : '/bossManager',
          	templateUrl : 'managerBoss/bossManagerPartial.html',
            controller : 'bossManagerController'
         })
        .state('bossManager.list', {
        	url : '/list',
          	templateUrl : 'managerBoss/bossManagerList.html'
        })
         .state('bossManager.new', {
        	url : '/new',
        	templateUrl : 'managerBoss/bossManagerNew.html'
        })
        .state('systemManager', {
        	url : '/systemManager',
          	templateUrl : 'managerSystem/systemManagerPartial.html',
            controller : 'systemManagerController'
        })
        .state('systemManager.list', {
        	url : '/list',
          	templateUrl : 'managerSystem/systemManagerList.html'
        })
        .state('systemManager.newRestaurantManager', {
        	url : '/newRestaurantManager',
        	templateUrl : 'managerSystem/systemManagerNewRestaurantManager.html'
        })
         .state('systemManager.newRestaurant', {
        	url : '/newRestaurant',
        	templateUrl : 'managerSystem/systemManagerNewRestaurant.html'
        })
        
        
        .state('restaurantManager', {
        	url : '/restaurantManager',
          	templateUrl : 'managerRestaurant/restaurantManagerPartial.html',
            controller : 'restaurantManagerController'
         })
         .state('restaurantManager.info', {
        	url : '/info',
          	templateUrl : 'managerRestaurant/restaurantManagerInfo.html'
        })
        .state('restaurantManager.newDrink', {
			url : '/newDrink',
			templateUrl : 'managerRestaurant/restaurantManagerNewDrink.html'
		})
		.state('restaurantManager.newDish', {
			url : '/newDish',
			templateUrl : 'managerRestaurant/restaurantManagerNewDish.html'
		})
	        
        
        
  
});