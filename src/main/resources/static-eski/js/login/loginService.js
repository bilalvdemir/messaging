app.service('LoginService', ['$http', function ($http) {

    this.getUser = function getUser(username, password) {
        return $http({
            method: 'GET',
            url: 'user/' + username + "/" + password
        });
    }

    this.addUser = function addUser(username, password) {
        return $http({
            method: 'POST',
            url: 'user',
            data: { username: username, password: password }
        });
    }

    this.deleteUser = function deleteUser(username) {
        return $http({
            method: 'DELETE',
            url: 'user/' + username
        })
    }

    this.updateUser = function updateUser(username, password) {
        return $http({
            method: 'PUT',
            url: 'user/' + username,
            data: { username: username, password: password }
        })
    }

    this.getAllUsers = function getAllUsers() {
        return $http({
            method: 'GET',
            url: 'user'
        });
    }

}]);