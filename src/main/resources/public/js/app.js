var app = angular.module('myApp', []);
app.controller('FormCtrl', function ($scope, $http) {
    $scope.reset = function() {
        $scope.formData = {};
    };

    $scope.submitForm = function(event) {
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
                $scope.reset();
                $scope.messageText = 'ERROR';
                $scope.messageType = 'alert-danger';
            },function successCallback(response) {
                console.log('OK');
                console.log(response);
                $scope.reset();
                $scope.messageText = 'OK. New password: ' + response;
                $scope.messageType = 'alert-info';
            })
            .catch(function (err) {
                console.log(err);
            });
        
        event.stopPropagation();
        event.preventDefault();
    };
});