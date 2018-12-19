'use strict';

var appModule = angular.module('Login', [ 'ngMaterial', 'ngMessages' ]);
appModule.controller('loginController',
				['$scope',
				'$location',
				'$state',
				'notificationChannelService',
				'authenticationService',
				'Base64',
				'$rootScope','messageProcessingService','dialogservice',
				function($scope, $location, $state, notificationChannelService,
						authenticationService, Base64,$rootScope,messageProcessingService,dialogservice) {
					
					$scope.openNotificationChannel = function() {
						
						if($scope.name == undefined || $scope.name == ""){
							$scope.errorMessage = "*Please enter username.";
							return;
						}else if($scope.password == undefined || $scope.password == ""){
							$scope.errorMessage = "*Please enter password.";
							return;
						}
						$scope.user = $scope.name.toLowerCase();

						var isOk = notificationChannelService
								.openNotificationChannel($scope.user,
										$scope.password, Base64);
						isOk.then(function(data,status) {
							authenticationService.setCredentials($scope.user,
									$scope.password);
							messageProcessingService.loadContacts();
							dialogservice.shouldWaitingOpen = false;
							$location.path('/');
							$state.go('home');
						}, function(error) {
							$scope.errorMessage = "*Username or password is incorrect.";
							console.log('Failed: ' + error);
						}, function(update) {
							console.log('Got notification: ' + update);
						});

					};
				} ]);