var app = angular.module('login', [ 'ngMaterial', 'ngMessages' ]);

app.controller('loginController', [
		'$scope',
		'$rootScope',
		'$mdSidenav',
		'LoginService',
		function($scope, $rootScope, $mdSidenav, LoginService) {

			$scope.isSideNavShown = function() {
				return $mdSidenav("leftSideBox").isOpen();
			}

			$scope.getFirstLocation = function() {
				$rootScope.leftSideBoxTemplateUrl = "../../first.html";
				$mdSidenav("leftSideBox").toggle();

			}

			$scope.showUser = function(user) {

				alert("Hello " + user.username);
				$rootScope.leftSideBoxTemplateUrl = "../../last.html";
				$rootScope.leftSideBoxTemplateUrl;
				// $mdSidenav("leftSideBox").close();

				// $mdSidenav("leftSideBox").toggle();
			}

			$scope.showUserfirst = function(user) {

				alert("Hello " + user.username);
				$rootScope.leftSideBoxTemplateUrl = "../../first.html";
				$rootScope.leftSideBoxTemplateUrl;
				// $mdSidenav("leftSideBox").close();

				// $mdSidenav("leftSideBox").toggle();
			}

			$scope.updateUser = function() {
				LoginService.updateUser($scope.user.username,
						$scope.user.password).then(function success(response) {
					$scope.message = 'User data updated!';
					$scope.errorMessage = '';
				}, function error(response) {
					$scope.errorMessage = 'Error updating user!';
					$scope.message = '';
				});
			}

			$scope.login = function() {
				var username = $scope.user.username;
				LoginService
						.getUser($scope.user.username, $scope.user.password)
						.then(function success(response) {
							$scope.user = response.data;
							$scope.user.username = username;
							$scope.message = 'login success';
							$scope.errorMessage = '';
							window.location = "/chat.html";

						}, function error(response) {
							$scope.message = '';
							if (response.status === 404) {
								$scope.errorMessage = 'USER NOT FOUND';
							} else {
								$scope.errorMessage = "WRONG PASSWORD";
							}
						});
			}

			$scope.signup = function() {
				window.location = "/signup.html";
			}
			
			$scope.loginPage = function() {
				window.location = "/login.html";
			}
			
			$scope.register = function() {
				if ($scope.user && $scope.user.username && $scope.user.password) {
					LoginService.addUser($scope.user.username,
							$scope.user.password).then(
							function success(response) {
								$scope.message = 'User added!';
								$scope.errorMessage = '';
							}, function error(response) {
								$scope.errorMessage = 'Error adding user!';
								$scope.message = '';
							});
				} else {
					$scope.errorMessage = 'Please enter name and password!';
					$scope.message = '';
				}
			}

			$scope.addUser = function() {
				if ($scope.user != null && $scope.user.username) {
					LoginService.addUser($scope.user.username,
							$scope.user.password).then(
							function success(response) {
								$scope.message = 'User added!';
								$scope.errorMessage = '';
							}, function error(response) {
								$scope.errorMessage = 'Error adding user!';
								$scope.message = '';
							});
				} else {
					$scope.errorMessage = 'Please enter a name!';
					$scope.message = '';
				}
			}

			$scope.deleteUser = function() {
				LoginService.deleteUser($scope.user.username).then(
						function success(response) {
							$scope.message = 'User deleted!';
							$scope.user = null;
							$scope.errorMessage = '';
						}, function error(response) {
							$scope.errorMessage = 'Error deleting user!';
							$scope.message = '';
						})
			}

			$scope.last = function() {
				$rootScope.leftSideBoxTemplateUrl = "../../last.html";
				$mdSidenav("leftSideBox").open();
			}

			$scope.first = function() {
				$rootScope.leftSideBoxTemplateUrl = "../../first.html";

			}

			$scope.getAllUsers = function() {
				LoginService.getAllUsers().then(function success(response) {
					$scope.users = response.data;
					$scope.message = '';
					$scope.errorMessage = '';
					// $scope.leftSideBoxTemplateUrl = "../../first.html";
					// $mdSidenav("leftSideBox").toggle();
				}, function error(response) {
					// $scope.leftSideBoxTemplateUrl = "../../last.html";
					// $mdSidenav("leftSideBox").toggle();
					$scope.message = '';
					$scope.errorMessage = 'Error getting users!';
				});
			}

			$scope.logOut = function() {
				window.location = "/login.html";
			}

		} ]);

app.controller('secondController', [ '$scope', '$rootScope', '$mdSidenav',
		'LoginService', function($scope, $rootScope, $mdSidenav, LoginService) {

			$scope.showUserfirst = function(user) {

				alert("Hello " + user.username);
				$rootScope.leftSideBoxTemplateUrl = "../../first.html";
				$rootScope.leftSideBoxTemplateUrl;
				// $mdSidenav("leftSideBox").close();

				// $mdSidenav("leftSideBox").toggle();
			}

			$scope.getAllUsers = function() {
				LoginService.getAllUsers().then(function success(response) {
					$scope.users = response.data;
					$scope.message = '';
					$scope.errorMessage = '';
					// $scope.leftSideBoxTemplateUrl = "../../first.html";
					// $mdSidenav("leftSideBox").toggle();
				}, function error(response) {
					// $scope.leftSideBoxTemplateUrl = "../../last.html";
					// $mdSidenav("leftSideBox").toggle();
					$scope.message = '';
					$scope.errorMessage = 'Error getting users!';
				});
			}

		} ]);