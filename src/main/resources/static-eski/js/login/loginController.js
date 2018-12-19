var app = angular.module('login', []);

app.controller('loginController', ['$scope', 'LoginService', function ($scope, LoginService) {

    $scope.updateUser = function () {
        LoginService.updateUser($scope.user.username, $scope.user.password)
            .then(function success(response) {
                $scope.message = 'User data updated!';
                $scope.errorMessage = '';
            },
                function error(response) {
                    $scope.errorMessage = 'Error updating user!';
                    $scope.message = '';
                });
    }

    $scope.getUser = function () {
        var username = $scope.user.username;
        LoginService.getUser($scope.user.username, $scope.user.password)
            .then(function success(response) {
                $scope.user = response.data;
                $scope.user.username = username;
                $scope.message = 'login success';
                $scope.errorMessage = '';
                window.location = "/chat.html";

            },
                function error(response) {
                    $scope.message = '';
                    if (response.status === 404) {
                        $scope.errorMessage = 'USER NOT FOUND';
                    }
                    else {
                        $scope.errorMessage = "WRONG PASSWORD";
                    }
                });
    }

    $scope.addUser = function () {
        if ($scope.user != null && $scope.user.username) {
            LoginService.addUser($scope.user.username, $scope.user.password)
                .then(function success(response) {
                    $scope.message = 'User added!';
                    $scope.errorMessage = '';
                },
                    function error(response) {
                        $scope.errorMessage = 'Error adding user!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }

    $scope.deleteUser = function () {
        LoginService.deleteUser($scope.user.username)
            .then(function success(response) {
                $scope.message = 'User deleted!';
                $scope.user = null;
                $scope.errorMessage = '';
            },
                function error(response) {
                    $scope.errorMessage = 'Error deleting user!';
                    $scope.message = '';
                })
    }

    $scope.getAllUsers = function () {
        LoginService.getAllUsers()
            .then(function success(response) {
                $scope.users = response.data;
                $scope.message = '';
                $scope.errorMessage = '';
            },
                function error(response) {
                    $scope.message = '';
                    $scope.errorMessage = 'Error getting users!';
                });
    }

}]);