var app = angular.module('myApp', []);
app.controller('FormCtrl', function ($scope, $http) {
    $scope.reset = function() {
        $scope.formData = {};
    };

    $scope.submitForm = function() {
        console.log("posting data....");

        var req = {
            method: 'POST',
            url: '/public/user/add',
            headers: {
              'Content-Type': 'application/json'
            },
            data: {
                "name"  : $scope.formData.name,
                "email" : $scope.formData.email
            }
        };

        $http(req)
            .then(function errorCallback(response) {
                console.log('ERROR');
                $scope.messageText = 'ERROR';
                $scope.messageType = 'alert-danger';
            },function successCallback(response) {
                console.log('OK');
                $scope.messageText = 'OK';
                $scope.messageType = 'alert-info';
                $scope.reset();
            })
            .catch(function (err) {
                console.log(err);
            });
    };
});